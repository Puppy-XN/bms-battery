package com.server.manager;



import com.alibaba.fastjson2.JSONArray;
import com.server.constant.CacheConstant;
import com.server.model.entity.BatteryWarningRules;
import com.server.utils.spring.SpringUtils;
import com.server.utils.string.StringUtils;
import java.util.Collection;
import java.util.List;

/**
 * 参数配置代理类
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/2/16 16:22
 */
public class BatteryWarningRulesManager {

    /**
     * 设置规则缓存
     *
     * @param key       参数键
     * @param rules 规则数据列表
     */
    public static void setRuleCache(String key, List<BatteryWarningRules> rules) {
        SpringUtils.getBean(RedisManager.class).setCacheObject(getCacheKey(key), rules);
    }

    /**
     * 获取规则缓存
     *
     * @param key 参数键
     * @return rules 规则数据列表
     */
    public static List<BatteryWarningRules> getRuleCache(String key) {
        JSONArray arrayCache = SpringUtils.getBean(RedisManager.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(arrayCache)) {
            return arrayCache.toList(BatteryWarningRules.class);
        }
        return null;
    }


    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private static String getCacheKey(String configKey) {
        return CacheConstant.BATTERY_WARNING_RULES + configKey;
    }
}
