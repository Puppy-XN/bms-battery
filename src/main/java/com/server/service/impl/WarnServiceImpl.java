package com.server.service.impl;

import com.alibaba.fastjson2.JSON;
import com.server.constant.CacheConstant;
import com.server.manager.BatteryWarningRulesManager;
import com.server.mapper.BatteryWarningRulesMapper;
import com.server.mapper.VehicleInfoMapper;
import com.server.model.entity.BatteryWarningRules;
import com.server.model.entity.VehicleInfo;
import com.server.model.vo.SignalVo;
import com.server.service.WarnService;
import com.server.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预警业务实现类
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午4:36
 */
@Service
public class WarnServiceImpl implements WarnService {


    @Autowired
    private BatteryWarningRulesMapper batteryWarningRulesMapper;

    @Autowired
    private VehicleInfoMapper vehicleInfoMapper;

    public List<Map<String, Object>> warn(List<Map<String, Object>> params) {

        List<Map<String, Object>> warnings = new ArrayList<>();

        // 遍历逐个处理
        for (Map<String, Object> param : params) {

            // 车辆编号
            Integer carId = (Integer) param.get("carId");
            // 规则编号
            Integer warnId = (Integer) param.get("warnId");
            // 信号
            String signal = param.get("signal").toString();

            // 信号实体类
            SignalVo signalVo = parseSignal(signal);

            // 查询车辆信息
            VehicleInfo vehicleInfo = vehicleInfoMapper.selectVehicleInfoByFrameNumber(Long.valueOf(carId));

            // 获取指定规则, 如果规则编号为空, redis 缓存中获取规则
            List<BatteryWarningRules> rulesList;
            if (StringUtils.isNotNull(warnId)) {
                BatteryWarningRules rules = new BatteryWarningRules();
                rules.setRuleNumber(Long.valueOf(warnId));
                rulesList = batteryWarningRulesMapper.selectBatteryWarningRulesList(rules);
            } else {
                rulesList = BatteryWarningRulesManager.getRuleCache(CacheConstant.BATTERY_WARNING_RULES_KEY);
            }


            for (BatteryWarningRules batteryWarningRules : rulesList) {
                Map<String, Object> warning = new HashMap<>();
                warning.put("车架编号", carId);
                warning.put("电池类型", batteryWarningRules.getBatteryType());

                if (vehicleInfo.getBatteryType().equals(batteryWarningRules.getBatteryType()) && batteryWarningRules.getRuleType().equals("M") && checkVoltageDiff(signalVo, batteryWarningRules.getMinDiff(), batteryWarningRules.getMaxDiff())) {
                    warning.put("warnName", batteryWarningRules.getName());
                    warning.put("warnLevel", batteryWarningRules.getWarningLevel());
                    warnings.add(warning);
                } else if (vehicleInfo.getBatteryType().equals(batteryWarningRules.getBatteryType()) && batteryWarningRules.getRuleType().equals("I") && checkCurrentDiff(signalVo, batteryWarningRules.getMinDiff(), batteryWarningRules.getMaxDiff())) {
                    warning.put("warnName", batteryWarningRules.getName());
                    warning.put("warnLevel", batteryWarningRules.getWarningLevel());
                    warnings.add(warning);
                }
            }
        }

        return warnings;
    }

    /**
     * 信号字符串转实体类
     *
     * @param signal
     * @return
     */
    private static SignalVo parseSignal(String signal) {
        return JSON.parseObject(signal, SignalVo.class);
    }

    /**
     * 检查当前信号电压是否满足规则
     *
     * @param signalVo 当前车辆信号
     * @param minDiff  最小值
     * @param maxDiff  最大值
     * @return 结果
     */
    private static boolean checkVoltageDiff(SignalVo signalVo, Double minDiff, Double maxDiff) {
        if (StringUtils.isNotNull(signalVo.getMx()) && StringUtils.isNotNull(signalVo.getMi())) {
            if (StringUtils.isNull(maxDiff)) {
                return (signalVo.getMx() - signalVo.getMi()) >= minDiff;
            } else if (StringUtils.isNull(minDiff)) {
                return signalVo.getMx() - signalVo.getMi() <= maxDiff;
            } else {
                return (signalVo.getMx() - signalVo.getMi()) >= minDiff && (signalVo.getMx() - signalVo.getMi()) <= maxDiff;
            }
        }
        return false;
    }

    /**
     * 检查当前信号电流是否满足规则
     *
     * @param signalVo 当前车辆信号
     * @param minDiff  最小值
     * @param maxDiff  最大值
     * @return 结果
     */
    private static boolean checkCurrentDiff(SignalVo signalVo, Double minDiff, Double maxDiff) {
        if (StringUtils.isNotNull(signalVo.getIx()) && StringUtils.isNotNull(signalVo.getIi())) {
            if (StringUtils.isNull(maxDiff)) {
                return (signalVo.getIx() - signalVo.getIi()) >= minDiff;
            } else if (StringUtils.isNull(minDiff)) {
                return (signalVo.getIx() - signalVo.getIi()) <= maxDiff;
            } else {
                return (signalVo.getIx() - signalVo.getIi()) >= minDiff && (signalVo.getIx() - signalVo.getIi()) <= maxDiff;
            }
        }
        return false;
    }


}
