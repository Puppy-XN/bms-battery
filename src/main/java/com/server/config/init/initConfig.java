package com.server.config.init;

import com.server.service.BatteryWarningRulesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 项目初始化操作
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/2/19 21:14
 */
@Component
public class initConfig {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private BatteryWarningRulesService batteryWarningRulesService;

    /**
     * 项目启动时，初始化数据（如缓存字典和参数配置）
     */
    @PostConstruct
    public void init() {
        logger.info("项目启动，初始化数据开始！");

        logger.info("初始化规则到缓存开始！");
        batteryWarningRulesService.loadingRuleCache();
        logger.info("初始化规则到缓存结束！");

        logger.info("项目启动，初始化数据结束！");
    }

}
