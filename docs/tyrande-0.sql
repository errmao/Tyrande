/*
 Navicat Premium Data Transfer

 Source Server         : docker-local
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:60006
 Source Schema         : tyrande-0

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 03/08/2020 14:00:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_key
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_key`;
CREATE TABLE `sys_dict_key`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典项',
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典名',
  `dict_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典描述',
  `dict_status` tinyint(0) NULL DEFAULT NULL COMMENT '字典状态（1-启用 2-停用）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统字典项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_key
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_value`;
CREATE TABLE `sys_dict_value`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_key_id` bigint(0) NULL DEFAULT NULL COMMENT '字典项编号',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '展示值',
  `dict_real_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实值',
  `dict_order` tinyint(0) NULL DEFAULT NULL COMMENT '字典顺序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统字典值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_value
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_level` tinyint(0) NULL DEFAULT NULL COMMENT '菜单层级',
  `pid` bigint(0) NULL DEFAULT 0 COMMENT '上级',
  `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求路径',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 1, 0, '/system', '2020-07-30 09:40:22', '2020-07-30 09:40:25');
INSERT INTO `sys_menu` VALUES (2, '用户管理', 2, 1, 'sysuser', '2020-07-30 09:40:55', '2020-07-30 09:40:59');
INSERT INTO `sys_menu` VALUES (3, '角色管理', 2, 1, 'sysrole', '2020-07-30 09:41:22', '2020-07-30 09:41:25');
INSERT INTO `sys_menu` VALUES (4, '菜单管理', 2, 1, 'sysmenu', '2020-07-30 09:41:47', '2020-07-30 09:41:49');
INSERT INTO `sys_menu` VALUES (5, '参数管理', 2, 1, 'sysparams', '2020-07-31 10:09:52', '2020-07-31 10:09:55');

-- ----------------------------
-- Table structure for sys_params
-- ----------------------------
DROP TABLE IF EXISTS `sys_params`;
CREATE TABLE `sys_params`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数中文名',
  `param_en_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数英文名',
  `param_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数值',
  `param_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_params
-- ----------------------------
INSERT INTO `sys_params` VALUES (1, '参数1', 'value3', '4000', '参数2', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (2, '参数2', 'value6', '8000', '参数4', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (3, '参数3', 'value9', '12000', '参数6', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (4, '参数4', 'value12', '16000', '参数8', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (5, '参数5', 'value15', '20000', '参数10', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (6, '参数6', 'value18', '24000', '参数12', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (7, '参数7', 'value21', '28000', '参数14', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (8, '参数8', 'value24', '32000', '参数16', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (9, '参数9', 'value27', '36000', '参数18', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (10, '参数10', 'value30', '40000', '参数20', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (11, '参数11', 'value33', '44000', '参数22', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (12, '参数12', 'value36', '48000', '参数24', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (13, '参数13', 'value39', '52000', '参数26', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (14, '参数14', 'value42', '56000', '参数28', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (15, '参数15', 'value45', '60000', '参数30', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (16, '参数16', 'value48', '64000', '参数32', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (17, '参数17', 'value51', '68000', '参数34', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (18, '参数18', 'value54', '72000', '参数36', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (19, '参数19', 'value57', '76000', '参数38', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (20, '参数20', 'value60', '80000', '参数40', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (21, '参数21', 'value63', '84000', '参数42', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (22, '参数22', 'value66', '88000', '参数44', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (23, '参数23', 'value69', '92000', '参数46', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (24, '参数24', 'value72', '96000', '参数48', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (25, '参数25', 'value75', '100000', '参数50', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (26, '参数26', 'value78', '104000', '参数52', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (27, '参数27', 'value81', '108000', '参数54', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (28, '参数28', 'value84', '112000', '参数56', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (29, '参数29', 'value87', '116000', '参数58', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (30, '参数30', 'value90', '120000', '参数60', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (31, '参数31', 'value93', '124000', '参数62', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (32, '参数32', 'value96', '128000', '参数64', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (33, '参数33', 'value99', '132000', '参数66', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (34, '参数34', 'value102', '136000', '参数68', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (35, '参数35', 'value105', '140000', '参数70', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (36, '参数36', 'value108', '144000', '参数72', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (37, '参数37', 'value111', '148000', '参数74', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (38, '参数38', 'value114', '152000', '参数76', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (39, '参数39', 'value117', '156000', '参数78', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (40, '参数40', 'value120', '160000', '参数80', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (41, '参数41', 'value123', '164000', '参数82', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (42, '参数42', 'value126', '168000', '参数84', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (43, '参数43', 'value129', '172000', '参数86', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (44, '参数44', 'value132', '176000', '参数88', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (45, '参数45', 'value135', '180000', '参数90', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (46, '参数46', 'value138', '184000', '参数92', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (47, '参数47', 'value141', '188000', '参数94', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (48, '参数48', 'value144', '192000', '参数96', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (49, '参数49', 'value147', '196000', '参数98', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (50, '参数50', 'value150', '200000', '参数100', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (51, '参数51', 'value153', '204000', '参数102', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (52, '参数52', 'value156', '208000', '参数104', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (53, '参数53', 'value159', '212000', '参数106', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (54, '参数54', 'value162', '216000', '参数108', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (55, '参数55', 'value165', '220000', '参数110', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (56, '参数56', 'value168', '224000', '参数112', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (57, '参数57', 'value171', '228000', '参数114', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (58, '参数58', 'value174', '232000', '参数116', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (59, '参数59', 'value177', '236000', '参数118', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (60, '参数60', 'value180', '240000', '参数120', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (61, '参数61', 'value183', '244000', '参数122', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (62, '参数62', 'value186', '248000', '参数124', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (63, '参数63', 'value189', '252000', '参数126', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (64, '参数64', 'value192', '256000', '参数128', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (65, '参数65', 'value195', '260000', '参数130', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (66, '参数66', 'value198', '264000', '参数132', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (67, '参数67', 'value201', '268000', '参数134', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (68, '参数68', 'value204', '272000', '参数136', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (69, '参数69', 'value207', '276000', '参数138', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (70, '参数70', 'value210', '280000', '参数140', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (71, '参数71', 'value213', '284000', '参数142', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (72, '参数72', 'value216', '288000', '参数144', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (73, '参数73', 'value219', '292000', '参数146', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (74, '参数74', 'value222', '296000', '参数148', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (75, '参数75', 'value225', '300000', '参数150', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (76, '参数76', 'value228', '304000', '参数152', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (77, '参数77', 'value231', '308000', '参数154', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (78, '参数78', 'value234', '312000', '参数156', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (79, '参数79', 'value237', '316000', '参数158', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (80, '参数80', 'value240', '320000', '参数160', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (81, '参数81', 'value243', '324000', '参数162', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (82, '参数82', 'value246', '328000', '参数164', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (83, '参数83', 'value249', '332000', '参数166', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (84, '参数84', 'value252', '336000', '参数168', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (85, '参数85', 'value255', '340000', '参数170', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (86, '参数86', 'value258', '344000', '参数172', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (87, '参数87', 'value261', '348000', '参数174', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (88, '参数88', 'value264', '352000', '参数176', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (89, '参数89', 'value267', '356000', '参数178', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (90, '参数90', 'value270', '360000', '参数180', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (91, '参数91', 'value273', '364000', '参数182', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (92, '参数92', 'value276', '368000', '参数184', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (93, '参数93', 'value279', '372000', '参数186', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (94, '参数94', 'value282', '376000', '参数188', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (95, '参数95', 'value285', '380000', '参数190', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (96, '参数96', 'value288', '384000', '参数192', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (97, '参数97', 'value291', '388000', '参数194', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (98, '参数98', 'value294', '392000', '参数196', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (99, '参数99', 'value297', '396000', '参数198', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (100, '参数100', 'value300', '400000', '参数200', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (101, '参数101', 'value303', '404000', '参数202', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (102, '参数102', 'value306', '408000', '参数204', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (103, '参数103', 'value309', '412000', '参数206', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (104, '参数104', 'value312', '416000', '参数208', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (105, '参数105', 'value315', '420000', '参数210', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (106, '参数106', 'value318', '424000', '参数212', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (107, '参数107', 'value321', '428000', '参数214', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (108, '参数108', 'value324', '432000', '参数216', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (109, '参数109', 'value327', '436000', '参数218', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (110, '参数110', 'value330', '440000', '参数220', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (111, '参数111', 'value333', '444000', '参数222', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (112, '参数112', 'value336', '448000', '参数224', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (113, '参数113', 'value339', '452000', '参数226', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (114, '参数114', 'value342', '456000', '参数228', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (115, '参数115', 'value345', '460000', '参数230', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (116, '参数116', 'value348', '464000', '参数232', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (117, '参数117', 'value351', '468000', '参数234', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (118, '参数118', 'value354', '472000', '参数236', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (119, '参数119', 'value357', '476000', '参数238', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (120, '参数120', 'value360', '480000', '参数240', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (121, '参数121', 'value363', '484000', '参数242', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (122, '参数122', 'value366', '488000', '参数244', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (123, '参数123', 'value369', '492000', '参数246', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (124, '参数124', 'value372', '496000', '参数248', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (125, '参数125', 'value375', '500000', '参数250', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (126, '参数126', 'value378', '504000', '参数252', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (127, '参数127', 'value381', '508000', '参数254', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (128, '参数128', 'value384', '512000', '参数256', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (129, '参数129', 'value387', '516000', '参数258', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (130, '参数130', 'value390', '520000', '参数260', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (131, '参数131', 'value393', '524000', '参数262', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (132, '参数132', 'value396', '528000', '参数264', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (133, '参数133', 'value399', '532000', '参数266', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (134, '参数134', 'value402', '536000', '参数268', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (135, '参数135', 'value405', '540000', '参数270', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (136, '参数136', 'value408', '544000', '参数272', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (137, '参数137', 'value411', '548000', '参数274', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (138, '参数138', 'value414', '552000', '参数276', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (139, '参数139', 'value417', '556000', '参数278', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (140, '参数140', 'value420', '560000', '参数280', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (141, '参数141', 'value423', '564000', '参数282', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (142, '参数142', 'value426', '568000', '参数284', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (143, '参数143', 'value429', '572000', '参数286', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (144, '参数144', 'value432', '576000', '参数288', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (145, '参数145', 'value435', '580000', '参数290', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (146, '参数146', 'value438', '584000', '参数292', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (147, '参数147', 'value441', '588000', '参数294', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (148, '参数148', 'value444', '592000', '参数296', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (149, '参数149', 'value447', '596000', '参数298', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (150, '参数150', 'value450', '600000', '参数300', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (151, '参数151', 'value453', '604000', '参数302', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (152, '参数152', 'value456', '608000', '参数304', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (153, '参数153', 'value459', '612000', '参数306', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (154, '参数154', 'value462', '616000', '参数308', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (155, '参数155', 'value465', '620000', '参数310', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (156, '参数156', 'value468', '624000', '参数312', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (157, '参数157', 'value471', '628000', '参数314', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (158, '参数158', 'value474', '632000', '参数316', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (159, '参数159', 'value477', '636000', '参数318', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (160, '参数160', 'value480', '640000', '参数320', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (161, '参数161', 'value483', '644000', '参数322', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (162, '参数162', 'value486', '648000', '参数324', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (163, '参数163', 'value489', '652000', '参数326', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (164, '参数164', 'value492', '656000', '参数328', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (165, '参数165', 'value495', '660000', '参数330', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (166, '参数166', 'value498', '664000', '参数332', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (167, '参数167', 'value501', '668000', '参数334', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (168, '参数168', 'value504', '672000', '参数336', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (169, '参数169', 'value507', '676000', '参数338', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (170, '参数170', 'value510', '680000', '参数340', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (171, '参数171', 'value513', '684000', '参数342', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (172, '参数172', 'value516', '688000', '参数344', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (173, '参数173', 'value519', '692000', '参数346', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (174, '参数174', 'value522', '696000', '参数348', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (175, '参数175', 'value525', '700000', '参数350', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (176, '参数176', 'value528', '704000', '参数352', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (177, '参数177', 'value531', '708000', '参数354', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (178, '参数178', 'value534', '712000', '参数356', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (179, '参数179', 'value537', '716000', '参数358', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (180, '参数180', 'value540', '720000', '参数360', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (181, '参数181', 'value543', '724000', '参数362', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (182, '参数182', 'value546', '728000', '参数364', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (183, '参数183', 'value549', '732000', '参数366', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (184, '参数184', 'value552', '736000', '参数368', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (185, '参数185', 'value555', '740000', '参数370', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (186, '参数186', 'value558', '744000', '参数372', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (187, '参数187', 'value561', '748000', '参数374', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (188, '参数188', 'value564', '752000', '参数376', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (189, '参数189', 'value567', '756000', '参数378', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (190, '参数190', 'value570', '760000', '参数380', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (191, '参数191', 'value573', '764000', '参数382', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (192, '参数192', 'value576', '768000', '参数384', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (193, '参数193', 'value579', '772000', '参数386', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (194, '参数194', 'value582', '776000', '参数388', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (195, '参数195', 'value585', '780000', '参数390', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (196, '参数196', 'value588', '784000', '参数392', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (197, '参数197', 'value591', '788000', '参数394', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (198, '参数198', 'value594', '792000', '参数396', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (199, '参数199', 'value597', '796000', '参数398', '2020-07-31 16:00:02', '2020-07-31 16:00:06');
INSERT INTO `sys_params` VALUES (200, '参数200', 'value600', '800000', '参数400', '2020-07-31 16:00:02', '2020-07-31 16:00:06');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色编号',
  `menu_id` bigint(0) NULL DEFAULT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3);
INSERT INTO `sys_role_menu` VALUES (4, 1, 4);
INSERT INTO `sys_role_menu` VALUES (5, 1, 5);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `login_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登陆账户',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `sex` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `valid_email` tinyint(0) NULL DEFAULT NULL COMMENT '邮箱是否验证 0-否 1-是',
  `tel` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `valid_tel` tinyint(0) NULL DEFAULT NULL COMMENT '电话是否验证 0-否 1-是',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '用户状态 0-未使用 1-正常 2-锁定 3-过期',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'caomengde', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '曹孟德', 1, 25, NULL, '浙江省海宁市', 'errmao@aliyun.com', 0, '15990040088', 0, 1, '2020-05-01 00:00:00', '2020-05-01 00:00:00');
INSERT INTO `sys_user` VALUES (2, 'zhangyide', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '张翼德', 1, 22, NULL, '浙江省桐乡市', 'tiny@163.com', 0, '15990030081', 0, 1, '2020-05-01 00:00:00', '2020-05-01 00:00:00');
INSERT INTO `sys_user` VALUES (3, 'admin', '$2a$10$rsaFbdiwf8igcVhUMpyp/eW5vj6Y2EZ3Q4X3kY56yXjbgxs09AwZS', 'admin', 2, 21, NULL, '浙江省嘉兴市', '222@163.com', 0, '1588552369', 0, 1, '2020-05-07 13:16:50', '2020-05-07 13:16:52');
INSERT INTO `sys_user` VALUES (4, 'test1', '$2a$10$c4Pz/6WR1BmGedZk1WXtge33RONfTbUT9IsmfhEHwGzEilTJf6Ylu', '测试用户1', 2, 11, NULL, '浙江省嘉兴市', '11dds12@163.com', 1, '1588552369', 1, 2, '2020-05-07 13:27:21', '2020-05-07 13:27:23');
INSERT INTO `sys_user` VALUES (5, 'test2', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户2', 1, 13, NULL, '浙江省嘉兴市', '21321@163.com', 0, '1588552369', 0, 1, '2020-05-13 16:08:13', '2020-05-13 16:08:11');
INSERT INTO `sys_user` VALUES (6, 'test3', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户3', 2, 74, NULL, '浙江省嘉兴市', 'asdada@163.com', 0, '1588552369', 1, 2, '2020-05-13 16:08:16', '2020-05-13 16:08:09');
INSERT INTO `sys_user` VALUES (7, 'test4', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户4', 1, 32, NULL, '浙江省嘉兴市', 'dqweqw@163.com', 0, '1588512312', 1, 3, '2020-05-13 16:08:18', '2020-05-13 16:08:06');
INSERT INTO `sys_user` VALUES (8, 'test5', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户5', 1, 53, NULL, '浙江省嘉兴市', 'oooo@163.com', 0, '1588512309', 1, 1, '2020-05-13 16:08:21', '2020-05-13 16:08:04');
INSERT INTO `sys_user` VALUES (10, 'test7', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户7', 2, 23, NULL, '浙江省嘉兴市', 'asd@163.com', 0, '1588523133', 1, 1, '2020-05-13 16:07:58', '2020-05-13 16:07:53');
INSERT INTO `sys_user` VALUES (11, 'test8', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户8', 2, 64, NULL, '浙江省嘉兴市', '1112@qq.com', 1, '1588233333', 1, 1, '2020-05-13 16:08:00', '2020-05-13 16:07:50');
INSERT INTO `sys_user` VALUES (12, 'test9', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户9', 2, 54, NULL, '浙江省嘉兴市', '11eqwe2@126.com', 0, '1584444444', 1, 1, '2020-05-13 16:08:02', '2020-05-13 16:07:47');
INSERT INTO `sys_user` VALUES (13, 'test6', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户6', 2, 57, NULL, '浙江省嘉兴市', '11qqqq12@163.com', 0, '1588666666', 1, 1, '2020-05-13 16:07:39', '2020-05-13 16:07:33');
INSERT INTO `sys_user` VALUES (14, 'test10', '$2a$10$rf4GAiq6/11gKyZENY2H6.Q7GiV/BS60lCvk.C5jhM5R9r06HqHBC', '测试用户10', 1, 44, NULL, '浙江省嘉兴市', '112212@163.com', 0, '1588555555', 1, 1, '2020-05-13 16:07:41', '2020-05-13 16:07:44');
INSERT INTO `sys_user` VALUES (19, 'hhhhhhhh', '$2a$10$PM3xTb8XcJsOJG/nmNnoI.MpdguhyNhUFkWeXnqSdkTSWPpDbrwDG', 'dddddd', 1, 1231, NULL, '123123', '', 0, '', 0, 1, '2020-05-14 11:23:35', '2020-05-14 11:23:35');
INSERT INTO `sys_user` VALUES (20, 'gggggggg', '$2a$10$C2uEj30tvdcpegFVpCDSkOlJEvbjvohhps1TzBmB/bBniyQtqs6Ga', '12312', 1, 213, NULL, 'ddddd1111111', '', 0, '', 0, 1, '2020-05-14 12:07:43', '2020-05-14 12:07:50');
INSERT INTO `sys_user` VALUES (21, 'fusheng', '$2a$10$KJbAWUjp36yqFtZA9RvoGuvQVnZ5WYh9qPOyZdx.a8uDXfZgFsAam', '浮生', 1, 18, NULL, '浙江杭州余杭区', '888@aliyun.com', 0, '19855699366', 0, 1, '2020-07-03 10:25:04', '2020-07-03 10:33:14');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户编号',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户角色管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1, 2);

-- ----------------------------
-- Procedure structure for proc_initData
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_initData`;
delimiter ;;
CREATE PROCEDURE `proc_initData`()
BEGIN
	DECLARE
		i INT DEFAULT 1;
	WHILE
			i <= 200 DO
			INSERT INTO `tyrande-0`.`sys_params` ( `id`, `param_name`, `param_en_name`, `param_value`, `param_desc`, `create_time`, `update_time` )
		VALUES
			(
				i,
				concat( '参数', i ),
				concat( 'value', i * 3 ),
				concat( i * 4, '000' ),
				concat( '参数', i * 2 ),
				'2020-07-31 16:00:02',
				'2020-07-31 16:00:06' 
			);
		
		SET i = i + 1;
		
	END WHILE;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
