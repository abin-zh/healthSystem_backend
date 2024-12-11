/*
 Navicat Premium Data Transfer

 Source Server         : abin
 Source Server Type    : MySQL
 Source Server Version : 50737 (5.7.37-log)
 Source Host           : localhost:3306
 Source Schema         : healthsystem

 Target Server Type    : MySQL
 Target Server Version : 50737 (5.7.37-log)
 File Encoding         : 65001

 Date: 31/08/2024 17:59:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for balanceflow
-- ----------------------------
DROP TABLE IF EXISTS `balanceflow`;
CREATE TABLE `balanceflow`  (
  `flow_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  `flow_user_id` int(11) NULL DEFAULT NULL COMMENT '病人ID',
  `flow_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  `flow_type` int(2) NULL DEFAULT NULL COMMENT '类型 0消费 1充值 2退款',
  `flow_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `flow_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '说明',
  `flow_order_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '交易订单号',
  `flow_status` tinyint(4) NULL DEFAULT 0 COMMENT '交易状态 0未支付 1已支付',
  PRIMARY KEY (`flow_id`) USING BTREE,
  INDEX `fk_flow_user_id`(`flow_user_id`) USING BTREE,
  CONSTRAINT `fk_flow_user_id` FOREIGN KEY (`flow_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of balanceflow
-- ----------------------------
INSERT INTO `balanceflow` VALUES (5, 1, '2024-08-25 00:00:00', 1, 123.00, '13986845277充值了123.00元', '1827448609443659776', 1);
INSERT INTO `balanceflow` VALUES (6, 1, NULL, 0, 380.00, NULL, '1825511268676227072', 0);
INSERT INTO `balanceflow` VALUES (7, 1, '2024-08-25 00:00:00', 0, 300.00, '13986845277支付了1827612619891765248订单300.00元', '1827612619891765248', 1);
INSERT INTO `balanceflow` VALUES (8, 1, '2024-08-25 15:49:30', 0, 380.00, '13986845277支付了1827614264423784448订单380.00元', '1827614264423784448', 1);
INSERT INTO `balanceflow` VALUES (9, 1, '2024-08-26 00:30:36', 1, 10.00, NULL, '1827745402966474752', 0);
INSERT INTO `balanceflow` VALUES (10, 1, '2024-08-26 00:00:00', 1, 1000.00, '13986845277充值了1000.00元', '1827913887221133312', 1);
INSERT INTO `balanceflow` VALUES (11, 1, '2024-08-26 00:00:00', 1, 1000.00, '13986845277充值了1000.00元', '1827927230308790272', 1);
INSERT INTO `balanceflow` VALUES (12, 1, '2024-08-26 12:33:50', 0, 300.00, '13986845277支付了1827927408281497600订单300.00元', '1827927408281497600', 1);
INSERT INTO `balanceflow` VALUES (13, 1, '2024-08-26 12:36:50', 1, 1000.00, NULL, '1827928166209007616', 0);
INSERT INTO `balanceflow` VALUES (14, 1, '2024-08-26 12:44:05', 1, 1000.00, NULL, '1827929990454423552', 0);
INSERT INTO `balanceflow` VALUES (15, 1, '2024-08-26 12:48:06', 1, 1000.00, NULL, '1827930999486525440', 0);
INSERT INTO `balanceflow` VALUES (16, 1, '2024-08-26 00:00:00', 1, 1000.00, '13986845277充值了1000.00元', '1827931184954454016', 1);
INSERT INTO `balanceflow` VALUES (17, 1, '2024-08-26 12:49:28', 0, 300.00, '13986845277支付了1827931343058743296订单300.00元', '1827931343058743296', 1);
INSERT INTO `balanceflow` VALUES (18, 1, '2024-08-26 12:50:58', 1, 1000.00, NULL, '1827931722232213504', 0);
INSERT INTO `balanceflow` VALUES (19, 1, '2024-08-26 00:00:00', 1, 1000.00, '13986845277充值了1000.00元', '1827931878423900160', 1);
INSERT INTO `balanceflow` VALUES (20, 1, '2024-08-26 00:00:00', 1, 1000.00, '13986845277充值了1000.00元', '1827932164110528512', 1);
INSERT INTO `balanceflow` VALUES (21, 1, '2024-08-26 12:53:16', 0, 200.00, '13986845277支付了1827932299464912896订单200.00元', '1827932299464912896', 1);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '科室ID',
  `dept_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '科室名称',
  `dept_parent_id` int(11) NULL DEFAULT 0 COMMENT '父级科室ID',
  `dept_is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 (1: 已删除, 0: 未删除)',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '行政科', 0, 0);
INSERT INTO `department` VALUES (2, '内科', 0, 0);
INSERT INTO `department` VALUES (3, '外科', 0, 0);
INSERT INTO `department` VALUES (4, '儿科', 0, 0);
INSERT INTO `department` VALUES (5, '妇产科', 0, 0);
INSERT INTO `department` VALUES (6, '眼科', 0, 0);
INSERT INTO `department` VALUES (7, '耳鼻喉科', 0, 0);
INSERT INTO `department` VALUES (8, '口腔科', 0, 0);
INSERT INTO `department` VALUES (9, '皮肤科', 0, 0);
INSERT INTO `department` VALUES (10, '骨科', 0, 0);
INSERT INTO `department` VALUES (11, '心脏科', 0, 0);
INSERT INTO `department` VALUES (26, '精神科', 0, 1);
INSERT INTO `department` VALUES (28, '神经外科', 0, 0);
INSERT INTO `department` VALUES (29, '神经内科', 0, 0);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `log_staff_id` int(11) NULL DEFAULT NULL COMMENT '管理员ID',
  `log_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  `log_operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, 3, NULL, 'admin3执行了添加/编辑菜单');
INSERT INTO `log` VALUES (2, 3, '2024-08-22 01:42:14', 'admin3执行了添加/编辑菜单');
INSERT INTO `log` VALUES (3, 3, '2024-08-22 01:44:21', 'admin3执行了添加/编辑菜单');
INSERT INTO `log` VALUES (4, 3, '2024-08-22 01:45:54', 'admin3执行了添加/编辑菜单');
INSERT INTO `log` VALUES (5, 3, '2024-08-22 01:55:51', 'admin3执行了添加/编辑菜单');
INSERT INTO `log` VALUES (6, 3, '2024-08-22 01:56:53', 'admin3执行了添加/编辑菜单');
INSERT INTO `log` VALUES (7, 3, '2024-08-26 00:46:11', 'admin3执行了添加/编辑工作人员(管理员)');
INSERT INTO `log` VALUES (8, 3, '2024-08-26 00:55:46', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (9, 3, '2024-08-26 00:55:59', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (10, 3, '2024-08-26 01:03:39', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (11, 1, '2024-08-26 12:49:59', 'admin执行了编辑细项结论');
INSERT INTO `log` VALUES (12, 1, '2024-08-26 12:50:05', 'admin执行了编辑细项结论');
INSERT INTO `log` VALUES (13, 1, '2024-08-26 12:50:11', 'admin执行了编辑细项结论');
INSERT INTO `log` VALUES (14, 1, '2024-08-26 12:50:23', 'admin执行了编辑细项结论');
INSERT INTO `log` VALUES (15, 1, '2024-08-26 12:50:29', 'admin执行了编辑细项结论');
INSERT INTO `log` VALUES (16, 1, '2024-08-26 12:53:44', 'admin执行了编辑细项结论');
INSERT INTO `log` VALUES (17, 1, '2024-08-26 12:53:53', 'admin执行了编辑细项结论');
INSERT INTO `log` VALUES (18, 1, '2024-08-26 12:54:06', 'admin执行了编辑体检小结');
INSERT INTO `log` VALUES (19, 2, '2024-08-26 12:54:38', 'admin1执行了编辑体检总结');
INSERT INTO `log` VALUES (20, 3, '2024-08-27 21:06:03', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (21, 3, '2024-08-27 21:06:18', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (22, 3, '2024-08-27 21:07:02', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (23, 3, '2024-08-27 21:07:08', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (24, 3, '2024-08-27 21:07:36', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (25, 3, '2024-08-27 21:07:53', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (26, 3, '2024-08-27 21:08:07', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (27, 3, '2024-08-27 21:08:20', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (28, 3, '2024-08-27 21:08:40', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (29, 3, '2024-08-27 21:08:56', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (30, 3, '2024-08-27 21:12:13', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (31, 3, '2024-08-27 21:12:19', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (32, 3, '2024-08-27 21:15:15', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (33, 3, '2024-08-27 21:16:00', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (34, 3, '2024-08-27 21:48:04', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (35, 3, '2024-08-27 21:48:34', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (36, 3, '2024-08-27 21:48:43', 'admin3执行了添加/编辑角色');
INSERT INTO `log` VALUES (37, 3, '2024-08-27 21:50:02', 'admin3执行了添加/编辑角色');

-- ----------------------------
-- Table structure for medical_checkup_summary
-- ----------------------------
DROP TABLE IF EXISTS `medical_checkup_summary`;
CREATE TABLE `medical_checkup_summary`  (
  `cs_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '总结ID',
  `cs_order_id` int(11) NOT NULL COMMENT '订单ID',
  `cs_summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '结果说明',
  `cs_staff_id` int(11) NULL DEFAULT NULL COMMENT '医生ID',
  `cs_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `cs_status` int(1) NULL DEFAULT 0 COMMENT '体检总结状态 0未评 1待评价 2已评价',
  PRIMARY KEY (`cs_id`) USING BTREE,
  UNIQUE INDEX `cs_order_id`(`cs_order_id`) USING BTREE,
  INDEX `cs_staff_id`(`cs_staff_id`) USING BTREE,
  CONSTRAINT `medical_checkup_summary_ibfk_1` FOREIGN KEY (`cs_order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_checkup_summary_ibfk_2` FOREIGN KEY (`cs_staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_checkup_summary
-- ----------------------------
INSERT INTO `medical_checkup_summary` VALUES (5, 15, '总体正常，请注意休息时间', 2, '2024-08-23 15:21:29', 0);
INSERT INTO `medical_checkup_summary` VALUES (6, 16, NULL, NULL, '2024-08-19 20:32:57', 0);
INSERT INTO `medical_checkup_summary` VALUES (7, 17, NULL, NULL, '2024-08-25 12:03:46', 0);
INSERT INTO `medical_checkup_summary` VALUES (8, 18, NULL, NULL, '2024-08-25 12:05:09', 0);
INSERT INTO `medical_checkup_summary` VALUES (9, 19, NULL, NULL, '2024-08-25 12:10:13', 0);
INSERT INTO `medical_checkup_summary` VALUES (10, 20, NULL, NULL, '2024-08-25 15:42:58', 0);
INSERT INTO `medical_checkup_summary` VALUES (11, 21, NULL, NULL, '2024-08-25 15:49:30', 0);
INSERT INTO `medical_checkup_summary` VALUES (12, 22, NULL, NULL, '2024-08-26 12:33:50', 0);
INSERT INTO `medical_checkup_summary` VALUES (13, 23, NULL, NULL, '2024-08-26 12:49:28', 0);
INSERT INTO `medical_checkup_summary` VALUES (14, 24, '总体正常，请注意休息', 2, '2024-08-26 12:54:38', 2);

-- ----------------------------
-- Table structure for medical_item
-- ----------------------------
DROP TABLE IF EXISTS `medical_item`;
CREATE TABLE `medical_item`  (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '细项ID',
  `item_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '细项名称',
  `item_unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '单位',
  `item_upper_limit` decimal(10, 2) NOT NULL COMMENT '上限',
  `item_lower_limit` decimal(10, 2) NOT NULL COMMENT '下限',
  `item_is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 (1: 已删除, 0: 未删除)',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_item
-- ----------------------------
INSERT INTO `medical_item` VALUES (1, '血小板', '10^9/L', 400.00, 150.00, 0);
INSERT INTO `medical_item` VALUES (2, '白细胞', '10^9/L', 10.00, 4.00, 0);
INSERT INTO `medical_item` VALUES (3, '红细胞压积', '%', 50.00, 35.00, 0);
INSERT INTO `medical_item` VALUES (4, '血红蛋白', 'g/L', 180.00, 120.00, 0);
INSERT INTO `medical_item` VALUES (5, '尿蛋白', 'mg/dL', 15.00, 15.00, 0);
INSERT INTO `medical_item` VALUES (6, '男性睾酮质量', 'ng/dL', 1022.00, 300.00, 1);
INSERT INTO `medical_item` VALUES (7, '睾酮', 'ng/dl', 1000.00, 300.00, 1);
INSERT INTO `medical_item` VALUES (8, '女性睾酮', 'ng/dl', 1000.00, 300.00, 1);
INSERT INTO `medical_item` VALUES (9, '血氧浓度', '%', 100.00, 70.00, 0);

-- ----------------------------
-- Table structure for medical_item_check_results
-- ----------------------------
DROP TABLE IF EXISTS `medical_item_check_results`;
CREATE TABLE `medical_item_check_results`  (
  `icr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '检查结果ID',
  `icr_item_id` int(11) NOT NULL COMMENT '细项ID',
  `icr_result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '结果(数值)',
  `icr_order_id` int(11) NOT NULL COMMENT '订单ID',
  `icr_staff_id` int(11) NULL DEFAULT NULL COMMENT '医生ID',
  `icr_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `icr_project_summary_id` int(11) NULL DEFAULT NULL COMMENT '检查项目id',
  `icr_status` int(1) NULL DEFAULT 0 COMMENT '细项结果状态 0未评 1已评',
  `icr_summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '细项结果',
  PRIMARY KEY (`icr_id`) USING BTREE,
  INDEX `icr_item_id`(`icr_item_id`) USING BTREE,
  INDEX `icr_order_id`(`icr_order_id`) USING BTREE,
  INDEX `icr_staff_id`(`icr_staff_id`) USING BTREE,
  INDEX `medical_item_check_results_ibfk_4`(`icr_project_summary_id`) USING BTREE,
  CONSTRAINT `medical_item_check_results_ibfk_1` FOREIGN KEY (`icr_item_id`) REFERENCES `medical_item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_item_check_results_ibfk_2` FOREIGN KEY (`icr_order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_item_check_results_ibfk_3` FOREIGN KEY (`icr_staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_item_check_results_ibfk_4` FOREIGN KEY (`icr_project_summary_id`) REFERENCES `medical_project_summary` (`ps_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_item_check_results
-- ----------------------------
INSERT INTO `medical_item_check_results` VALUES (13, 4, '121', 15, 1, '2024-08-20 11:02:16', 8, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (14, 1, '151', 15, 1, '2024-08-20 11:02:18', 8, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (15, 5, '15', 15, 1, '2024-08-20 11:47:43', 9, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (16, 4, '121', 15, 1, '2024-08-20 11:48:31', 10, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (17, 1, '150', 15, 1, '2024-08-20 11:48:54', 10, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (18, 5, NULL, 16, NULL, '2024-08-19 20:32:57', 11, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (19, 4, NULL, 16, NULL, '2024-08-19 20:32:57', 12, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (20, 1, NULL, 16, NULL, '2024-08-19 20:32:57', 12, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (21, 4, NULL, 17, NULL, '2024-08-25 12:03:46', 13, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (22, 1, NULL, 17, NULL, '2024-08-25 12:03:46', 13, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (23, 4, NULL, 17, NULL, '2024-08-25 12:03:46', 14, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (24, 1, NULL, 17, NULL, '2024-08-25 12:03:46', 14, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (25, 5, NULL, 18, NULL, '2024-08-25 12:05:09', 15, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (26, 4, NULL, 18, NULL, '2024-08-25 12:05:09', 16, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (27, 1, NULL, 18, NULL, '2024-08-25 12:05:09', 16, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (28, 4, NULL, 19, NULL, '2024-08-25 12:10:13', 17, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (29, 1, NULL, 19, NULL, '2024-08-25 12:10:13', 17, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (30, 4, NULL, 19, NULL, '2024-08-25 12:10:13', 18, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (31, 1, NULL, 19, NULL, '2024-08-25 12:10:13', 18, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (32, 4, NULL, 20, NULL, '2024-08-25 15:42:58', 19, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (33, 1, NULL, 20, NULL, '2024-08-25 15:42:58', 19, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (34, 4, NULL, 20, NULL, '2024-08-25 15:42:58', 20, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (35, 1, NULL, 20, NULL, '2024-08-25 15:42:58', 20, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (36, 4, NULL, 21, NULL, '2024-08-25 15:49:30', 21, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (37, 1, NULL, 21, NULL, '2024-08-25 15:49:30', 21, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (38, 5, NULL, 21, NULL, '2024-08-25 15:49:30', 22, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (39, 4, NULL, 21, NULL, '2024-08-25 15:49:30', 23, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (40, 1, NULL, 21, NULL, '2024-08-25 15:49:30', 23, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (41, 4, NULL, 22, NULL, '2024-08-26 12:33:50', 24, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (42, 1, NULL, 22, NULL, '2024-08-26 12:33:50', 24, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (43, 4, NULL, 22, NULL, '2024-08-26 12:33:50', 25, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (44, 1, NULL, 22, NULL, '2024-08-26 12:33:50', 25, 0, NULL);
INSERT INTO `medical_item_check_results` VALUES (45, 4, '120', 23, 1, '2024-08-26 12:49:59', 26, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (46, 1, '150', 23, 1, '2024-08-26 12:50:11', 26, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (47, 4, '120', 23, 1, '2024-08-26 12:50:23', 27, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (48, 1, '150', 23, 1, '2024-08-26 12:50:29', 27, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (49, 4, '120', 24, 1, '2024-08-26 12:53:44', 28, 1, '正常');
INSERT INTO `medical_item_check_results` VALUES (50, 1, '150', 24, 1, '2024-08-26 12:53:53', 28, 1, '正常');

-- ----------------------------
-- Table structure for medical_package
-- ----------------------------
DROP TABLE IF EXISTS `medical_package`;
CREATE TABLE `medical_package`  (
  `package_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '套餐ID',
  `package_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '套餐名称',
  `package_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `package_is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 (1: 已删除, 0: 未删除)',
  PRIMARY KEY (`package_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_package
-- ----------------------------
INSERT INTO `medical_package` VALUES (1, '基础体检套餐', 200.00, 0);
INSERT INTO `medical_package` VALUES (2, '高级体检套餐', 300.00, 0);
INSERT INTO `medical_package` VALUES (3, '全面体检套餐', 400.00, 0);
INSERT INTO `medical_package` VALUES (4, '儿童体检套餐', 150.00, 0);
INSERT INTO `medical_package` VALUES (5, '女性健康套餐', 250.00, 0);
INSERT INTO `medical_package` VALUES (19, '套餐测试11', 123333.00, 1);
INSERT INTO `medical_package` VALUES (20, '体检套餐测试1', 123333.00, 1);
INSERT INTO `medical_package` VALUES (21, '体检套餐测试11', 123333.00, 1);

-- ----------------------------
-- Table structure for medical_package_project
-- ----------------------------
DROP TABLE IF EXISTS `medical_package_project`;
CREATE TABLE `medical_package_project`  (
  `pp_content_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '套餐项目ID',
  `pp_package_id` int(11) NOT NULL COMMENT '套餐ID',
  `pp_project_id` int(11) NOT NULL COMMENT '项目ID',
  PRIMARY KEY (`pp_content_id`) USING BTREE,
  INDEX `pp_package_id`(`pp_package_id`) USING BTREE,
  INDEX `pp_project_id`(`pp_project_id`) USING BTREE,
  CONSTRAINT `medical_package_project_ibfk_1` FOREIGN KEY (`pp_package_id`) REFERENCES `medical_package` (`package_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_package_project_ibfk_2` FOREIGN KEY (`pp_project_id`) REFERENCES `medical_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_package_project
-- ----------------------------
INSERT INTO `medical_package_project` VALUES (1, 1, 1);
INSERT INTO `medical_package_project` VALUES (5, 2, 1);
INSERT INTO `medical_package_project` VALUES (8, 19, 3);
INSERT INTO `medical_package_project` VALUES (9, 19, 4);
INSERT INTO `medical_package_project` VALUES (10, 20, 1);
INSERT INTO `medical_package_project` VALUES (11, 20, 2);
INSERT INTO `medical_package_project` VALUES (12, 21, 1);
INSERT INTO `medical_package_project` VALUES (13, 21, 3);

-- ----------------------------
-- Table structure for medical_project
-- ----------------------------
DROP TABLE IF EXISTS `medical_project`;
CREATE TABLE `medical_project`  (
  `project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `project_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目名称',
  `project_price` decimal(10, 2) NOT NULL COMMENT '价格',
  `project_is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 (1: 已删除, 0: 未删除)',
  `project_dept_id` int(11) NOT NULL COMMENT '项目所属科室',
  PRIMARY KEY (`project_id`) USING BTREE,
  INDEX `fk_dept_id`(`project_dept_id`) USING BTREE,
  CONSTRAINT `fk_dept_id` FOREIGN KEY (`project_dept_id`) REFERENCES `department` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_project
-- ----------------------------
INSERT INTO `medical_project` VALUES (1, '血常规', 100.00, 0, 2);
INSERT INTO `medical_project` VALUES (2, '尿常规', 80.00, 0, 2);
INSERT INTO `medical_project` VALUES (3, '肝功能检查', 120.00, 0, 2);
INSERT INTO `medical_project` VALUES (4, '血糖检测', 50.00, 0, 2);
INSERT INTO `medical_project` VALUES (11, '随机检测', 1288.00, 1, 1);
INSERT INTO `medical_project` VALUES (24, '随机检测2-2', 1288.00, 1, 3);
INSERT INTO `medical_project` VALUES (25, '随机检测2020', 122.00, 1, 3);

-- ----------------------------
-- Table structure for medical_project_item
-- ----------------------------
DROP TABLE IF EXISTS `medical_project_item`;
CREATE TABLE `medical_project_item`  (
  `pi_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目细项关联ID',
  `pi_project_id` int(11) NOT NULL COMMENT '项目ID',
  `pi_item_id` int(11) NOT NULL COMMENT '细项ID',
  PRIMARY KEY (`pi_id`) USING BTREE,
  INDEX `pi_project_id`(`pi_project_id`) USING BTREE,
  INDEX `pi_item_id`(`pi_item_id`) USING BTREE,
  CONSTRAINT `medical_project_item_ibfk_1` FOREIGN KEY (`pi_project_id`) REFERENCES `medical_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_project_item_ibfk_2` FOREIGN KEY (`pi_item_id`) REFERENCES `medical_item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_project_item
-- ----------------------------
INSERT INTO `medical_project_item` VALUES (1, 1, 4);
INSERT INTO `medical_project_item` VALUES (2, 1, 1);
INSERT INTO `medical_project_item` VALUES (6, 24, 4);
INSERT INTO `medical_project_item` VALUES (7, 24, 5);
INSERT INTO `medical_project_item` VALUES (8, 25, 2);
INSERT INTO `medical_project_item` VALUES (9, 25, 3);
INSERT INTO `medical_project_item` VALUES (10, 25, 4);
INSERT INTO `medical_project_item` VALUES (11, 2, 5);

-- ----------------------------
-- Table structure for medical_project_summary
-- ----------------------------
DROP TABLE IF EXISTS `medical_project_summary`;
CREATE TABLE `medical_project_summary`  (
  `ps_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '小结ID',
  `ps_order_id` int(11) NOT NULL COMMENT '订单ID',
  `ps_project_id` int(11) NOT NULL COMMENT '项目ID',
  `ps_summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '小结',
  `ps_staff_id` int(11) NULL DEFAULT NULL COMMENT '医生id',
  `ps_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `ps_status` int(1) NULL DEFAULT 0 COMMENT '项目小结状态 0未评 1已评',
  PRIMARY KEY (`ps_id`) USING BTREE,
  INDEX `ps_order_id`(`ps_order_id`) USING BTREE,
  INDEX `ps_project_id`(`ps_project_id`) USING BTREE,
  INDEX `ps_staff_id`(`ps_staff_id`) USING BTREE,
  CONSTRAINT `medical_project_summary_ibfk_1` FOREIGN KEY (`ps_order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_project_summary_ibfk_2` FOREIGN KEY (`ps_project_id`) REFERENCES `medical_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_project_summary_ibfk_3` FOREIGN KEY (`ps_staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_project_summary
-- ----------------------------
INSERT INTO `medical_project_summary` VALUES (8, 15, 1, '血常规检测正常', 1, '2024-08-20 11:46:54', 1);
INSERT INTO `medical_project_summary` VALUES (9, 15, 2, '尿常规检测正常', 1, '2024-08-20 11:47:56', 1);
INSERT INTO `medical_project_summary` VALUES (10, 15, 1, '血常规正常', 1, '2024-08-20 11:49:13', 1);
INSERT INTO `medical_project_summary` VALUES (11, 16, 2, NULL, NULL, '2024-08-19 20:32:57', 0);
INSERT INTO `medical_project_summary` VALUES (12, 16, 1, NULL, NULL, '2024-08-19 20:32:57', 0);
INSERT INTO `medical_project_summary` VALUES (13, 17, 1, NULL, NULL, '2024-08-25 12:03:46', 0);
INSERT INTO `medical_project_summary` VALUES (14, 17, 1, NULL, NULL, '2024-08-25 12:03:46', 0);
INSERT INTO `medical_project_summary` VALUES (15, 18, 2, NULL, NULL, '2024-08-25 12:05:09', 0);
INSERT INTO `medical_project_summary` VALUES (16, 18, 1, NULL, NULL, '2024-08-25 12:05:09', 0);
INSERT INTO `medical_project_summary` VALUES (17, 19, 1, NULL, NULL, '2024-08-25 12:10:13', 0);
INSERT INTO `medical_project_summary` VALUES (18, 19, 1, NULL, NULL, '2024-08-25 12:10:13', 0);
INSERT INTO `medical_project_summary` VALUES (19, 20, 1, NULL, NULL, '2024-08-25 15:42:58', 0);
INSERT INTO `medical_project_summary` VALUES (20, 20, 1, NULL, NULL, '2024-08-25 15:42:58', 0);
INSERT INTO `medical_project_summary` VALUES (21, 21, 1, NULL, NULL, '2024-08-25 15:49:30', 0);
INSERT INTO `medical_project_summary` VALUES (22, 21, 2, NULL, NULL, '2024-08-25 15:49:30', 0);
INSERT INTO `medical_project_summary` VALUES (23, 21, 1, NULL, NULL, '2024-08-25 15:49:30', 0);
INSERT INTO `medical_project_summary` VALUES (24, 22, 1, NULL, NULL, '2024-08-26 12:33:50', 0);
INSERT INTO `medical_project_summary` VALUES (25, 22, 1, NULL, NULL, '2024-08-26 12:33:50', 0);
INSERT INTO `medical_project_summary` VALUES (26, 23, 1, NULL, NULL, '2024-08-26 12:49:28', 0);
INSERT INTO `medical_project_summary` VALUES (27, 23, 1, NULL, NULL, '2024-08-26 12:49:28', 0);
INSERT INTO `medical_project_summary` VALUES (28, 24, 1, '整体正常，注意休息', 1, '2024-08-26 12:54:06', 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单路由名称',
  `menu_component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单组件',
  `menu_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单路径',
  `menu_icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单图标',
  `menu_parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级菜单ID',
  `menu_is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 (1: 已删除, 0: 未删除)',
  `menu_status` tinyint(1) NULL DEFAULT 1 COMMENT '使用状态 (1: 活跃, 0: 非活跃)',
  `menu_sort` int(11) NULL DEFAULT 0 COMMENT '权重',
  `menu_redirect` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '重定向路径',
  `menu_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单名称',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'Top', 'Layout', '/', NULL, 0, 0, 1, 0, 'index', '首页布局');
INSERT INTO `menu` VALUES (2, 'Index', 'index/index', 'index', 'home', 1, 0, 1, 0, NULL, '首页');
INSERT INTO `menu` VALUES (3, 'System', 'Layout', '/system', 'hospital', 0, 0, 1, 0, '	\r\nnoRedirect', '系统管理');
INSERT INTO `menu` VALUES (4, 'User', 'systemManage/userManage/', '/user', 'user', 3, 0, 1, 0, NULL, '用户管理');
INSERT INTO `menu` VALUES (5, 'Staff', 'systemManage/staffManage', '/staff', 'user-nurse', 3, 0, 1, 0, NULL, '工作人员管理');
INSERT INTO `menu` VALUES (6, 'Dept', 'systemManage/deptManage', '/dept', 'building', 3, 0, 1, 0, NULL, '科室管理');
INSERT INTO `menu` VALUES (7, 'Medical_Item', 'systemManage/medicalItemManage/index', '/medical_item', 'boxes', 3, 0, 1, 0, NULL, '细项管理');
INSERT INTO `menu` VALUES (8, 'Medical_Project', 'systemManage/medicalProjectManage/index', '/medical_project', 'box-open', 3, 0, 1, 0, NULL, '项目配置');
INSERT INTO `menu` VALUES (9, 'Medical_Package', 'systemManage/medicalPackageManage/index', '/medical_package', 'box', 3, 0, 1, 0, NULL, '套餐配置');
INSERT INTO `menu` VALUES (10, 'CheckUp', 'Layout', '/checkup', 'check-square', 0, 0, 1, 0, '	\r\nnoRedirect', '体检工作');
INSERT INTO `menu` VALUES (11, 'Bill', 'checkupManage/billManage/index', '/bill', 'envelope-open-text', 10, 0, 1, 0, NULL, '用户开单');
INSERT INTO `menu` VALUES (12, 'Project_Summary', 'checkupManage/psManage/index', '/project_summary', 'bookmark', 10, 0, 1, 0, NULL, '体检小结');
INSERT INTO `menu` VALUES (13, 'CheckUp_Summary', 'checkupManage/csManage/index', '/checkup_summary', 'book-medical', 10, 0, 1, 0, NULL, '体检总结');
INSERT INTO `menu` VALUES (14, 'CheckUp_Detail', 'checkupManage/detail/index', '/checkup_detail', 'book-open', 10, 0, 1, 0, NULL, '体检综合查询');
INSERT INTO `menu` VALUES (15, 'Perm', 'Layout', '/perm', 'mask', 0, 0, 1, 0, '	\r\nnoRedirect', '权限管理');
INSERT INTO `menu` VALUES (16, 'Role', 'permManage/roleManage/index', '/role', 'mask', 15, 0, 1, 0, NULL, '角色管理');
INSERT INTO `menu` VALUES (17, 'Menu', 'permManage/menuManage/index', '/menu', 'list', 15, 0, 1, 0, NULL, '菜单管理');
INSERT INTO `menu` VALUES (18, 'System_Tools', 'Layout', '/system_tools', 'toolbox', 0, 0, 1, 0, '	\r\nnoRedirect', '系统工具');
INSERT INTO `menu` VALUES (19, 'Config', NULL, '/config', NULL, 18, 0, 1, 0, NULL, '参数管理');
INSERT INTO `menu` VALUES (20, 'Log', 'toolManage/logManage/index', '/log', NULL, 18, 0, 1, 0, NULL, '日志记录');
INSERT INTO `menu` VALUES (21, 'TestMenu', NULL, '/test', NULL, 2, 1, 1, 0, NULL, '测试菜单');

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `od_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情ID',
  `od_order_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '订单编号',
  `od_project_id` int(11) NULL DEFAULT NULL COMMENT '项目/套餐ID',
  `od_type` int(1) NULL DEFAULT NULL COMMENT '类型 0项目 1套餐',
  PRIMARY KEY (`od_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES (42, '1824868614691065856', 1, 1);
INSERT INTO `order_details` VALUES (43, '1824868614691065856', 1, 0);
INSERT INTO `order_details` VALUES (44, '1824868614691065856', 2, 0);
INSERT INTO `order_details` VALUES (45, '1825511268676227072', 2, 1);
INSERT INTO `order_details` VALUES (46, '1825511268676227072', 2, 0);
INSERT INTO `order_details` VALUES (47, '1827557457131114496', 1, 1);
INSERT INTO `order_details` VALUES (48, '1827557457131114496', 1, 0);
INSERT INTO `order_details` VALUES (49, '1827557805040242688', 1, 1);
INSERT INTO `order_details` VALUES (50, '1827557805040242688', 2, 0);
INSERT INTO `order_details` VALUES (51, '1827559080607780864', 1, 1);
INSERT INTO `order_details` VALUES (52, '1827559080607780864', 1, 0);
INSERT INTO `order_details` VALUES (53, '1827612619891765248', 1, 1);
INSERT INTO `order_details` VALUES (54, '1827612619891765248', 1, 0);
INSERT INTO `order_details` VALUES (55, '1827614264423784448', 1, 1);
INSERT INTO `order_details` VALUES (56, '1827614264423784448', 1, 0);
INSERT INTO `order_details` VALUES (57, '1827614264423784448', 2, 0);
INSERT INTO `order_details` VALUES (58, '1827927408281497600', 1, 1);
INSERT INTO `order_details` VALUES (59, '1827927408281497600', 1, 0);
INSERT INTO `order_details` VALUES (60, '1827931343058743296', 1, 1);
INSERT INTO `order_details` VALUES (61, '1827931343058743296', 1, 0);
INSERT INTO `order_details` VALUES (62, '1827932299464912896', 1, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '订单编号（体检编号）',
  `order_user_id` int(11) NULL DEFAULT NULL COMMENT '病人ID',
  `order_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付总金额',
  `order_status` int(1) NULL DEFAULT 0 COMMENT '支付状态 0未支付 1已支付',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `order_number`(`order_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (15, '1824868614691065856', 1, '2024-07-18 01:59:16', 380.00, 1);
INSERT INTO `orders` VALUES (16, '1825511268676227072', 1, '2024-06-19 20:32:57', 380.00, 1);
INSERT INTO `orders` VALUES (17, '1827557457131114496', 1, '2024-08-25 12:03:46', 300.00, 1);
INSERT INTO `orders` VALUES (18, '1827557805040242688', 1, '2024-08-25 12:05:09', 280.00, 1);
INSERT INTO `orders` VALUES (19, '1827559080607780864', 1, '2024-08-25 12:10:13', 300.00, 1);
INSERT INTO `orders` VALUES (20, '1827612619891765248', 1, '2024-08-25 15:42:58', 300.00, 1);
INSERT INTO `orders` VALUES (21, '1827614264423784448', 1, '2024-08-25 15:49:30', 380.00, 1);
INSERT INTO `orders` VALUES (22, '1827927408281497600', 1, '2024-08-26 12:33:50', 300.00, 1);
INSERT INTO `orders` VALUES (23, '1827931343058743296', 1, '2024-08-26 12:49:28', 300.00, 1);
INSERT INTO `orders` VALUES (24, '1827932299464912896', 1, '2024-08-26 12:53:16', 200.00, 1);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `perm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `perm_menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `perm_role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`perm_id`) USING BTREE,
  INDEX `perm_menu_id`(`perm_menu_id`) USING BTREE,
  INDEX `perm_role_id`(`perm_role_id`) USING BTREE,
  CONSTRAINT `permission_ibfk_1` FOREIGN KEY (`perm_menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `permission_ibfk_2` FOREIGN KEY (`perm_role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 195 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (21, 12, 2);
INSERT INTO `permission` VALUES (23, 10, 2);
INSERT INTO `permission` VALUES (24, 1, 2);
INSERT INTO `permission` VALUES (25, 2, 2);
INSERT INTO `permission` VALUES (26, 1, 3);
INSERT INTO `permission` VALUES (27, 2, 3);
INSERT INTO `permission` VALUES (28, 13, 3);
INSERT INTO `permission` VALUES (29, 10, 3);
INSERT INTO `permission` VALUES (32, 3, 5);
INSERT INTO `permission` VALUES (33, 4, 5);
INSERT INTO `permission` VALUES (34, 5, 5);
INSERT INTO `permission` VALUES (35, 6, 5);
INSERT INTO `permission` VALUES (36, 7, 5);
INSERT INTO `permission` VALUES (37, 8, 5);
INSERT INTO `permission` VALUES (38, 9, 5);
INSERT INTO `permission` VALUES (39, 10, 5);
INSERT INTO `permission` VALUES (40, 11, 5);
INSERT INTO `permission` VALUES (41, 12, 5);
INSERT INTO `permission` VALUES (42, 13, 5);
INSERT INTO `permission` VALUES (43, 14, 5);
INSERT INTO `permission` VALUES (46, 1, 5);
INSERT INTO `permission` VALUES (47, 2, 5);
INSERT INTO `permission` VALUES (164, 1, 1);
INSERT INTO `permission` VALUES (165, 2, 1);
INSERT INTO `permission` VALUES (166, 3, 1);
INSERT INTO `permission` VALUES (167, 4, 1);
INSERT INTO `permission` VALUES (168, 5, 1);
INSERT INTO `permission` VALUES (169, 6, 1);
INSERT INTO `permission` VALUES (170, 7, 1);
INSERT INTO `permission` VALUES (171, 8, 1);
INSERT INTO `permission` VALUES (172, 9, 1);
INSERT INTO `permission` VALUES (173, 15, 1);
INSERT INTO `permission` VALUES (174, 16, 1);
INSERT INTO `permission` VALUES (175, 17, 1);
INSERT INTO `permission` VALUES (176, 18, 1);
INSERT INTO `permission` VALUES (177, 19, 1);
INSERT INTO `permission` VALUES (178, 20, 1);
INSERT INTO `permission` VALUES (179, 10, 1);
INSERT INTO `permission` VALUES (180, 14, 1);
INSERT INTO `permission` VALUES (188, 1, 4);
INSERT INTO `permission` VALUES (189, 2, 4);
INSERT INTO `permission` VALUES (190, 3, 4);
INSERT INTO `permission` VALUES (191, 4, 4);
INSERT INTO `permission` VALUES (192, 10, 4);
INSERT INTO `permission` VALUES (193, 11, 4);
INSERT INTO `permission` VALUES (194, 14, 4);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `role_is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '角色是否删除(0未删除 1已删除)',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 0);
INSERT INTO `role` VALUES (2, '分检医生', 0);
INSERT INTO `role` VALUES (3, '总检医生', 0);
INSERT INTO `role` VALUES (4, '收费员', 0);
INSERT INTO `role` VALUES (5, '测试管理员', 1);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作人员ID',
  `staff_account` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账号',
  `staff_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `staff_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `staff_role_id` int(11) NULL DEFAULT NULL COMMENT '所属角色ID',
  `staff_dept_id` int(11) NULL DEFAULT NULL COMMENT '所属科室ID',
  `staff_is_status` tinyint(1) NULL DEFAULT 1 COMMENT '使用状态 (1: 活跃, 0: 非活跃)',
  `staff_is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 (1: 已删除, 0: 未删除)',
  `staff_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '邮箱',
  `staff_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`staff_id`) USING BTREE,
  UNIQUE INDEX `staff_account`(`staff_account`) USING BTREE,
  INDEX `staff_role_id`(`staff_role_id`) USING BTREE,
  INDEX `staff_dept_id`(`staff_dept_id`) USING BTREE,
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`staff_role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `staff_ibfk_2` FOREIGN KEY (`staff_dept_id`) REFERENCES `department` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '陈医生', 2, 2, 1, 0, '863030357@qq.com', 'https://pic.imgdb.cn/item/658ea07dc458853aef163552.jpg');
INSERT INTO `staff` VALUES (2, 'admin1', 'e10adc3949ba59abbe56e057f20f883e', '陈主任', 3, 1, 1, 0, '863030357@qq.com', 'https://pic.imgdb.cn/item/658ea07dc458853aef163552.jpg');
INSERT INTO `staff` VALUES (3, 'admin3', 'e10adc3949ba59abbe56e057f20f883e', '陈院长', 1, 1, 1, 0, '863030357@qq.com', 'https://pic.imgdb.cn/item/658ea07dc458853aef163552.jpg');
INSERT INTO `staff` VALUES (4, 'monkey', 'e10adc3949ba59abbe56e057f20f883e', '黑猴', 4, 1, 1, 0, '863030357@qq.com', 'https://c-ssl.dtstatic.com/uploads/blog/202008/24/20200824165211_chyfb.thumb.1000_0.jpg');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
  `user_id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '身份证号',
  `user_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '姓名',
  `user_gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `user_birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `user_balance` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `user_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `user_is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除(0未删除 1已删除)',
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '邮箱',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '13986845277', '410000198509202557', '易霞辰', 0, '2001-02-11', 8353.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', 'https://pic.imgdb.cn/item/658ea07dc458853aef163552.jpg');
INSERT INTO `users` VALUES (2, '18130155822', '350000198512314633', '武涛', 1, '2001-02-11', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (3, '18107982582', '150000199810276185', '段强', 1, '2001-02-11', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (4, '13763653714', '460000197201081124', '周娜', 0, '2001-02-11', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (5, '19803765877', '110000200610134922', '邹艳', 0, '2001-02-11', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (6, '18696066597', '330000198609220592', '乔强', 1, '2001-02-11', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (7, '18130141783', '710000201709218022', '范勇', 1, '2002-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (8, '18184173349', '710000202202148429', '黎秀兰', NULL, '1998-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (9, '18639688779', '640000200809017778', '易丽', NULL, '1997-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (10, '18176904458', '220000199401073115', '黄洋', NULL, '1996-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (11, '13787161329', '520000200307091831', '林磊', NULL, '1985-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (12, '19827336921', '410000197501142225', '孔勇', NULL, '1964-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (13, '18638175749', '430000201103145319', '李丽', NULL, '1972-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (14, '13159676174', '820000201202216306', '苏娟', NULL, '1993-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (15, '18624341368', '350000197612064884', '汪秀兰', NULL, '1977-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (16, '13558565926', '65000019881224632X', '叶平', NULL, '1965-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (17, '18688371836', '640000198408294803', '曾艳', NULL, '1972-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (18, '18603418284', '410000202407303989', '郭平', NULL, '1955-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (19, '18667705553', '350000198707183442', '金明', NULL, '1990-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', 'https://pic.imgdb.cn/item/658ea07dc458853aef163552.jpg');
INSERT INTO `users` VALUES (20, '18654826665', '510000200104028080', '段杰', NULL, '1980-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (21, '18649245445', '460000200503310740', '赵秀英', NULL, '1964-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (22, '18698704977', '540000197210177208', '范刚', NULL, '1954-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (23, '18117152436', '220000202009183218', '常秀兰', NULL, '1978-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (24, '18175209772', '54000019971023655X', '黎敏', NULL, '1965-02-15', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (25, '17812294558', '350802200015161515', '陈威威', 0, '2001-02-08', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (26, '17689244456', '350802200504183111', '陈啊啊', 1, '2001-12-11', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (27, '17689244466', '350802200504193121', '王四五', 1, '2001-12-12', 6730.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', NULL);
INSERT INTO `users` VALUES (30, '17689151239', '11010219840406970X', '陈阿斌', 1, '1984-04-06', 0.00, 'e10adc3949ba59abbe56e057f20f883e', 0, '863030357@qq.com', 'https://pic.imgdb.cn/item/658ea07dc458853aef163552.jpg');

SET FOREIGN_KEY_CHECKS = 1;
