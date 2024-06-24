package com.server.service;

import java.util.List;
import java.util.Map;

/**
 * 预警业务接口
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午4:36
 */
public interface WarnService {

   public List<Map<String, Object>> warn(List<Map<String, Object>> params);
}
