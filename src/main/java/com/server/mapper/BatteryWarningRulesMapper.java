package com.server.mapper;

import com.server.model.entity.BatteryWarningRules;

import java.util.List;

/**
 * 规则Mapper接口
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午5:01
 */
public interface BatteryWarningRulesMapper {

    /**
     * 查询规则列表
     *
     * @param batteryWarningRules 规则
     * @return 规则集合
     */
    public List<BatteryWarningRules> selectBatteryWarningRulesList(BatteryWarningRules batteryWarningRules);
}
