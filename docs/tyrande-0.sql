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

 Date: 30/07/2020 14:09:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_key
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_key`;
CREATE TABLE `sys_dict_key`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
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
  `id` bigint(0) NOT NULL COMMENT '主键',
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
  `id` bigint(0) NOT NULL COMMENT '主键',
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

-- ----------------------------
-- Table structure for sys_params
-- ----------------------------
DROP TABLE IF EXISTS `sys_params`;
CREATE TABLE `sys_params`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
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

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色编号',
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
  `id` bigint(0) NOT NULL COMMENT '主键',
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

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL COMMENT '用户编号',
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
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户编号',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户角色管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
