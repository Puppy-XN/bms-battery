

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for battery_warning_rules
-- ----------------------------
DROP TABLE IF EXISTS `battery_warning_rules`;
CREATE TABLE `battery_warning_rules`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编号',
  `rule_number` int NULL DEFAULT NULL COMMENT '规则编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则名称',
  `battery_type` enum('三元电池','铁锂电池') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电池类型',
  `rule_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则类型',
  `min_diff` decimal(5, 2) NULL DEFAULT NULL COMMENT '通用字段，用于最小差值（电压或电流）',
  `max_diff` decimal(5, 2) NULL DEFAULT NULL COMMENT '通用字段，用于最大差值（电压或电流）',
  `warning_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of battery_warning_rules
-- ----------------------------
INSERT INTO `battery_warning_rules` VALUES ('1', 1, '电压差报警', '三元电池', 'M', 5.00, NULL, '0');
INSERT INTO `battery_warning_rules` VALUES ('2', 1, '电压差报警', '三元电池', 'M', 3.00, 5.00, '1');
INSERT INTO `battery_warning_rules` VALUES ('3', 1, '电压差报警', '三元电池', 'M', 1.00, 3.00, '2');
INSERT INTO `battery_warning_rules` VALUES ('4', 1, '电压差报警', '三元电池', 'M', 0.60, 1.00, '3');
INSERT INTO `battery_warning_rules` VALUES ('5', 1, '电压差报警', '三元电池', 'M', 0.20, 0.60, '4');
INSERT INTO `battery_warning_rules` VALUES ('6', 1, '电压差报警', '三元电池', 'M', 0.00, 0.20, '不报警');
INSERT INTO `battery_warning_rules` VALUES ('7', 1, '电压差报警', '铁锂电池', 'M', 2.00, NULL, '0');
INSERT INTO `battery_warning_rules` VALUES ('8', 1, '电压差报警', '铁锂电池', 'M', 1.00, 2.00, '1');
INSERT INTO `battery_warning_rules` VALUES ('9', 1, '电压差报警', '铁锂电池', 'M', 0.70, 1.00, '2');
INSERT INTO `battery_warning_rules` VALUES ('10', 1, '电压差报警', '铁锂电池', 'M', 0.40, 0.70, '3');
INSERT INTO `battery_warning_rules` VALUES ('11', 1, '电压差报警', '铁锂电池', 'M', 0.20, 0.40, '4');
INSERT INTO `battery_warning_rules` VALUES ('12', 1, '电压差报警', '铁锂电池', 'M', 0.00, 0.20, '不报警');
INSERT INTO `battery_warning_rules` VALUES ('13', 2, '电流差报警', '三元电池', 'I', 3.00, NULL, '0');
INSERT INTO `battery_warning_rules` VALUES ('14', 2, '电流差报警', '三元电池', 'I', 1.00, 3.00, '1');
INSERT INTO `battery_warning_rules` VALUES ('15', 2, '电流差报警', '三元电池', 'I', 0.20, 1.00, '2');
INSERT INTO `battery_warning_rules` VALUES ('16', 2, '电流差报警', '三元电池', 'I', 0.00, 0.20, '不报警');
INSERT INTO `battery_warning_rules` VALUES ('17', 2, '电流差报警', '铁锂电池', 'I', 1.00, NULL, '0');
INSERT INTO `battery_warning_rules` VALUES ('18', 2, '电流差报警', '铁锂电池', 'I', 0.50, 1.00, '1');
INSERT INTO `battery_warning_rules` VALUES ('19', 2, '电流差报警', '铁锂电池', 'I', 0.20, 0.50, '2');
INSERT INTO `battery_warning_rules` VALUES ('20', 2, '电流差报警', '铁锂电池', 'I', 0.00, 0.20, '不报警');

-- ----------------------------
-- Table structure for vehicle_info
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_info`;
CREATE TABLE `vehicle_info`  (
  `vid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车辆识别码',
  `frame_number` int NULL DEFAULT NULL COMMENT '车架编号',
  `battery_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电池类型',
  `total_mileage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '总里程',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电池健康状态',
  UNIQUE INDEX `frame_number`(`frame_number` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车辆信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle_info
-- ----------------------------
INSERT INTO `vehicle_info` VALUES ('GHIJKL1234567890A', 2, '铁锂电池', '600', '95');
INSERT INTO `vehicle_info` VALUES ('BCDEFGH1234567890', 3, '三元电池', '300', '98');
INSERT INTO `vehicle_info` VALUES ('ZCDEFGH1234567892', 1, '三元电池', '300', '98');
INSERT INTO `vehicle_info` VALUES ('20240621131945A0', 4, '三元电池', '300', '98');

SET FOREIGN_KEY_CHECKS = 1;
