package com.server.service.impl;

import com.server.constant.CacheConstant;
import com.server.manager.BatteryWarningRulesManager;
import com.server.mapper.BatteryWarningRulesMapper;
import com.server.model.entity.BatteryWarningRules;
import com.server.service.BatteryWarningRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/21 下午5:30
 */
@Service
public class BatteryWarningRulesServiceImpl implements BatteryWarningRulesService {

    @Autowired
    private BatteryWarningRulesMapper batteryWarningRulesMapper;

    @Override
    public void loadingRuleCache() {
        List<BatteryWarningRules>  batteryWarningRulesList = batteryWarningRulesMapper.selectBatteryWarningRulesList(new BatteryWarningRules());
        BatteryWarningRulesManager.setRuleCache(CacheConstant.BATTERY_WARNING_RULES_KEY, batteryWarningRulesList);
    }
}
