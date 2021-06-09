/*
 Navicat Premium Data Transfer

 Source Server         : loca-3308
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3308
 Source Schema         : lab_booking_db

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 09/06/2021 21:52:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户之管理员';

-- ----------------------------
-- Records of t_admin
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_booking_record
-- ----------------------------
DROP TABLE IF EXISTS `t_booking_record`;
CREATE TABLE `t_booking_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lab_id` int NOT NULL DEFAULT '0' COMMENT '实验室id',
  `course_id` int NOT NULL DEFAULT '0' COMMENT '实验课程id',
  `memo` text COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_username` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人name',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user_id` int NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_username` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人name',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预约记录表';

-- ----------------------------
-- Records of t_booking_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_booking_record_time
-- ----------------------------
DROP TABLE IF EXISTS `t_booking_record_time`;
CREATE TABLE `t_booking_record_time` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_record_id` int NOT NULL DEFAULT '0' COMMENT '预约记录id',
  `week_no` tinyint NOT NULL DEFAULT '0' COMMENT '周编号 1-18',
  `day_no` tinyint NOT NULL DEFAULT '0' COMMENT '周几',
  `section_no` tinyint NOT NULL DEFAULT '0' COMMENT '节编号 1-5',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '1.等待上课 2.已下课 3.被取消',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预约记录所选时间表';

-- ----------------------------
-- Records of t_booking_record_time
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '课程名称',
  `teacher_id` int NOT NULL DEFAULT '0' COMMENT '上课教师id',
  `student_amount` int NOT NULL DEFAULT '0' COMMENT '上课人数',
  `class_hours` int NOT NULL DEFAULT '0' COMMENT '课时数',
  `memo` text COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_username` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人name',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user_id` int NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_username` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人name',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='实验课程表';

-- ----------------------------
-- Records of t_course
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_lab
-- ----------------------------
DROP TABLE IF EXISTS `t_lab`;
CREATE TABLE `t_lab` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '实验室名称',
  `max_capacity` int NOT NULL DEFAULT '0' COMMENT '最大容纳人数',
  `instrument_amount` int NOT NULL DEFAULT '0' COMMENT '仪器数量',
  `instrument_memo` text COMMENT '仪器备注',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态',
  `location` varchar(200) NOT NULL DEFAULT '' COMMENT '实验室位置',
  `open_time` varchar(5) NOT NULL DEFAULT '00:00' COMMENT '开放时间 24小时制 02:00',
  `close_time` varchar(5) NOT NULL DEFAULT '23:59' COMMENT '关闭时间 24小时制 19:00',
  `memo` text COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_username` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人name',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user_id` int NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_username` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人name',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='实验室表';

-- ----------------------------
-- Records of t_lab
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` tinyint NOT NULL DEFAULT '0' COMMENT '类型 1.菜单 2.API',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '资源名',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '父id',
  `route` varchar(100) NOT NULL DEFAULT '' COMMENT 'page route.',
  `method` varchar(10) DEFAULT '' COMMENT '请求方法',
  `path` varchar(250) NOT NULL DEFAULT '' COMMENT 'API path.',
  `contain_path_var` tinyint NOT NULL DEFAULT '0' COMMENT '包含path variable.',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `memo` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='RBAC 资源表';

-- ----------------------------
-- Records of t_resource
-- ----------------------------
BEGIN;
INSERT INTO `t_resource` VALUES (1, 2, '校验验证码', 0, '', 'get', 'v1/check-verify-code/{account}/{verifyCode}', 1, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (2, 2, '登录', 0, '', 'post', 'v1/login', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (3, 2, '登出', 0, '', 'post', 'v1/logout', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (4, 2, '注册', 0, '', 'post', 'v1/register', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (5, 2, '获取验证码', 0, '', 'get', 'v1/verify-code/{account}/{registerType}/{mode}', 1, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (6, 2, '搜索API', 0, '', 'get', 'v1/rbac/api', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (7, 2, '保存API', 0, '', 'post', 'v1/rbac/api', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (8, 2, '更新API', 0, '', 'put', 'v1/rbac/api', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (9, 2, '菜单树', 0, '', 'get', 'v1/rbac/menu', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (10, 2, '保存菜单', 0, '', 'post', 'v1/rbac/menu', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (11, 2, '更新菜单', 0, '', 'put', 'v1/rbac/menu', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (12, 2, '菜单绑定资源', 0, '', 'post', 'v1/rbac/menu/bind', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (13, 2, '删除资源', 0, '', 'delete', 'v1/rbac/resource/delete/{id}', 1, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (14, 2, '更新角色', 0, '', 'put', 'v1/rbac/role', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (15, 2, '删除角色', 0, '', 'delete', 'v1/rbac/role/{id}', 1, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (16, 2, '保存角色', 0, '', 'post', 'v1/rbac/role/{name}', 1, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (17, 2, '角色绑定资源', 0, '', 'post', 'v1/rbac/role/bind', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (18, 2, '用户绑定角色', 0, '', 'post', 'v1/rbac/user/bind', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (19, 2, '搜索实验室', 0, '', 'get', 'v1/lab', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (20, 2, '保存实验室', 0, '', 'post', 'v1/lab', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (21, 2, '更新实验室', 0, '', 'put', 'v1/lab', 0, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (22, 2, '获取某实验室', 0, '', 'get', 'v1/lab/{id}', 1, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
INSERT INTO `t_resource` VALUES (23, 2, '删除实验室', 0, '', 'delete', 'v1/lab/{id}', 1, 0, '', '2021-06-09 13:42:30', '2021-06-09 13:42:30', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='RBAC 角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, '普通管理员', '2021-06-09 13:45:40', '2021-06-09 13:45:40', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL DEFAULT '0' COMMENT '角色id',
  `resource_id` int NOT NULL DEFAULT '0' COMMENT '资源id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='RBAC 角色资源表';

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------
BEGIN;
INSERT INTO `t_role_resource` VALUES (1, 1, 1, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (2, 1, 2, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (3, 1, 3, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (4, 1, 4, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (5, 1, 5, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (6, 1, 6, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (7, 1, 7, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (8, 1, 8, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (9, 1, 9, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (10, 1, 10, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (11, 1, 11, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (12, 1, 12, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (13, 1, 13, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (14, 1, 14, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (15, 1, 15, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (16, 1, 16, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (17, 1, 17, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (18, 1, 18, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (19, 1, 19, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (20, 1, 20, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (21, 1, 21, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (22, 1, 22, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
INSERT INTO `t_role_resource` VALUES (23, 1, 23, '2021-06-09 13:50:36', '2021-06-09 13:50:36', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `academy` varchar(50) NOT NULL DEFAULT '' COMMENT '学院',
  `department` varchar(50) NOT NULL DEFAULT '' COMMENT '系',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户之教师';

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '登录账户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '登录密码 SHA1',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '绑定的邮箱',
  `wechat` varchar(50) NOT NULL DEFAULT '' COMMENT '绑定的微信',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '绑定的手机号',
  `type` tinyint NOT NULL DEFAULT '1' COMMENT '用户类型',
  `ref_id` int NOT NULL DEFAULT '0' COMMENT '用户详细id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='RBAC 用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` int NOT NULL DEFAULT '0' COMMENT '角色id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='RBAC 用户角色表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
