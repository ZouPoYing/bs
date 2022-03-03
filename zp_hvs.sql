/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : zp_hvs

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 03/03/2022 20:40:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `router` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES (1, 'bookConfig', 'bookConfig', 0);
INSERT INTO `app` VALUES (2, 'giveMedi', 'giveMedi', 1);
INSERT INTO `app` VALUES (3, 'mediManagement', 'mediManagement', NULL);
INSERT INTO `app` VALUES (4, 'payAndVform', 'payAndVform', 0);
INSERT INTO `app` VALUES (5, 'pInfoManagement', 'pInfoManagement', 1);
INSERT INTO `app` VALUES (6, 'reception', 'reception', 0);
INSERT INTO `app` VALUES (7, 'receptionManagement', 'receptionManagement', NULL);
INSERT INTO `app` VALUES (8, 'registration', 'registration', 0);
INSERT INTO `app` VALUES (9, '首页', 'home', NULL);
INSERT INTO `app` VALUES (11, 'statistics', 'statistics', 1);
INSERT INTO `app` VALUES (12, 'userInfoManagement', 'userInfoManagement', NULL);
INSERT INTO `app` VALUES (13, 'viewCf', 'viewCf', NULL);

-- ----------------------------
-- Table structure for audit
-- ----------------------------
DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit`  (
  `auditid` int(0) NOT NULL AUTO_INCREMENT,
  `audittype` int(0) NULL DEFAULT NULL,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `committerid` int(0) NULL DEFAULT NULL,
  `auditerid` int(0) NULL DEFAULT NULL,
  `fileid` int(0) NULL DEFAULT NULL,
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '待审核',
  `orderid` int(0) NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`auditid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of audit
-- ----------------------------
INSERT INTO `audit` VALUES (1, 1, '2021-04-10 19:05:39', 2, 1, 15, '测试', '单位2', '通过', NULL, NULL);
INSERT INTO `audit` VALUES (2, 1, '2021-04-11 02:12:49', 4, 1, 17, '供应商1单位', '供应商1地址', '通过', NULL, NULL);
INSERT INTO `audit` VALUES (3, 2, '2021-04-11 04:59:07', 2, 1, 21, NULL, NULL, '通过', 1, NULL);
INSERT INTO `audit` VALUES (4, 1, '2021-04-13 02:07:35', 5, 1, 22, '供应商2的单位', '单位地址2', '通过', NULL, NULL);
INSERT INTO `audit` VALUES (5, 3, '2021-04-14 01:47:03', 2, 1, NULL, NULL, NULL, '通过', 1, 'disicicscs');

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files`  (
  `fileid` int(0) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `userid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`fileid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg`  (
  `msgid` int(0) NOT NULL AUTO_INCREMENT,
  `auditid` int(0) NULL DEFAULT NULL,
  `msgtype` int(0) NULL DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `processid` int(0) NULL DEFAULT 1,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `patient` int(0) NULL DEFAULT NULL,
  `doctor` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`msgid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for offer
-- ----------------------------
DROP TABLE IF EXISTS `offer`;
CREATE TABLE `offer`  (
  `offerid` int(0) NOT NULL AUTO_INCREMENT,
  `orderid` int(0) NULL DEFAULT NULL,
  `userid` int(0) NULL DEFAULT NULL,
  `money` decimal(18, 2) NULL DEFAULT 0.00,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`offerid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of offer
-- ----------------------------
INSERT INTO `offer` VALUES (1, 1, 4, 666.60, '2021-04-11 19:55:33');
INSERT INTO `offer` VALUES (2, 1, 4, 555.50, '2021-04-11 19:59:40');
INSERT INTO `offer` VALUES (3, 1, 5, 77.77, '2021-04-13 02:09:30');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderid` int(0) NOT NULL AUTO_INCREMENT,
  `committerid` int(0) NULL DEFAULT NULL,
  `accepterid` int(0) NULL DEFAULT NULL,
  `auditerid` int(0) NULL DEFAULT NULL,
  `audittype` int(0) NULL DEFAULT NULL,
  `ordername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `technology` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `minmoney` decimal(18, 2) NULL DEFAULT 0.00,
  `maxmoney` decimal(18, 2) NULL DEFAULT 0.00,
  `auditid` int(0) NULL DEFAULT NULL,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `enddate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 2, 4, 1, 5, '项目1', '修改的技术描述1', 5000.00, 77.77, 5, '2021-04-11 04:59:07', '2021-04-13 10:43:00');

-- ----------------------------
-- Table structure for sets
-- ----------------------------
DROP TABLE IF EXISTS `sets`;
CREATE TABLE `sets`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usertype` int(0) NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `px` int(0) NULL DEFAULT NULL,
  `fileid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usertype` int(0) NULL DEFAULT NULL,
  `audit` int(0) NULL DEFAULT 0,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `advantage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '123456', NULL, NULL, 0, 1, '2021-04-05 16:21:07', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, 'test', 'rename5', '123456', '15888888888', 'abc@qq.com', 1, 1, '2021-04-05 16:21:15', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, '供应商1', NULL, '123456', NULL, NULL, 2, 1, '2021-04-11 01:49:03', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, '供应商2', NULL, '123456', NULL, NULL, 2, 1, '2021-04-13 02:05:53', NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
