/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : bs

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 09/03/2022 12:17:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '地址表主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户表主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货电话号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `is_default` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否默认收货地址',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 1, 'admin', '13900000000', '广东省,广州市,天河区', '亡灵街道1号巷5单元606', '是', '2022-03-09 03:04:02', '2022-03-09 03:29:57');
INSERT INTO `address` VALUES (2, 1, '好友1', '13900000001', '北京市,朝阳区,安贞街道', '安贞街道1巷404', '否', '2022-03-09 03:14:49', '2022-03-09 03:29:52');

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `router` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES (4, '查看订单', 'order', '1');
INSERT INTO `app` VALUES (6, '书城', 'book', '');
INSERT INTO `app` VALUES (9, '首页', 'home', '');
INSERT INTO `app` VALUES (12, '用户信息管理', 'userInfoManagement', '9');
INSERT INTO `app` VALUES (13, '书籍信息管理', 'bInfoManagement', '9');
INSERT INTO `app` VALUES (14, '书籍分类设置', 'categoryConfig', '9');
INSERT INTO `app` VALUES (15, '购物车', 'shoppingCar', '1');
INSERT INTO `app` VALUES (16, '收货地址管理', 'addressManagement', '1');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '书表主键',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `press` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `book_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书的图片',
  `is_sale` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否上架',
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '救世主', '豆豆', '作家出版社', '481', '02,0201', '2022-03-08 02:41:23', '2022-03-08 03:41:54', 'http://localhost:9091/1_1646678459083_救世主.png', '是', '根据本书改编的电视剧《天道》正在全国掀起极大反响。\n这是一部可以傲然独尊的长篇小说。也是一部可遇不可求的完美佳作。豆豆以她的才华，探问一个**的、无定形的、陌生的、暧昧的和未曾臻达的世界。男女主人公那浓墨重彩的经历以及令人欷放的爱情故事创造出了一种超然背叛的意志、而这意志是那样的静谧、清明。一个作家的品质，在豆豆身上达到了极致，作品主题的睿智和简约，出色地表现出佛学的光耀和不蓄意的使人震惊！');
INSERT INTO `book` VALUES (3, '文城', '余华', '北京十月文艺出版社', '59', '02,0201', '2022-03-08 03:44:24', '2022-03-08 03:49:30', 'http://localhost:9091/1_1646682259842_文城.jpg', '是', '★时隔8年，余华全新长篇重磅归来！\n\n◆人生就是自己的往事和他人的序章\n\n◆时代的洪流推着每个人做出各自的选择。\n\n  这是一个荒蛮的年代，结束的尚未结束，开始的尚未开始。\n\n◆我们总是在不同时代、不同国家、不同语言的作家那里，读到自己的感受，甚至是自己的生活。假如文学中真的存在某些神秘的力量，我想可能就是这些。\n\n——余华');
INSERT INTO `book` VALUES (4, '第一人称单数', '村上春树', '花城出版社', '56', '02,0202', '2022-03-08 03:46:56', '2022-03-08 03:46:56', 'http://localhost:9091/1_1646682408421_第一人称单数.jpg', '是', '○暌违六年！村上春树2021全新短篇小说集，村上春树回归之作！\n继小说集《没有女人的男人们》6年之后！继长篇小说《刺杀骑士团长》3年之后村上春树正式小说作品。一经上市引发读者热评：“在疫情时代，还得是村上的书。读村上的小说能让人获得治愈。”\n\n○新书一经出版引发媒体轰动，中日各大媒体争相报道！\n澎湃新闻、人民网、腾讯新闻、中华读书报、环球网、读卖新闻、朝日新闻、日本经济新闻等中日多家媒体时间报道新书新闻，备受关注！\n\n○村上回归人称叙述，代入感强。尽显故事的魔力！');
INSERT INTO `book` VALUES (5, '追风筝的人', '卡勒德·胡赛尼', '上海人民出版社', '45', '02,0202', '2022-03-08 03:49:01', '2022-03-08 03:49:01', 'http://localhost:9091/1_1646682538568_追风筝的人.jpg', '是', '感动全球亿万读者，值得与友人分享的终身五星小说 畅销全球的奇迹之书 高圆圆动情推荐，窦靖童创作灵感的来源，朱一龙、张一山深情诵读 为你千千万万遍！');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '分类表主键',
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  `category_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类代码',
  `belong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属',
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '文学', '01', NULL, '文学', '2022-03-07 20:54:27', '2022-03-07 21:15:13');
INSERT INTO `category` VALUES (2, '中国文学', '0101', '01', '中国文学', '2022-03-07 21:14:11', '2022-03-07 21:15:44');
INSERT INTO `category` VALUES (3, '小说', '02', NULL, '小说', '2022-03-07 21:15:01', '2022-03-07 21:15:01');
INSERT INTO `category` VALUES (4, '外国文学', '0102', '01', '外国文学', '2022-03-07 21:16:07', '2022-03-07 21:16:07');
INSERT INTO `category` VALUES (6, '中国小说', '0201', '02', '中国小说', '2022-03-07 21:16:48', '2022-03-07 21:16:48');
INSERT INTO `category` VALUES (7, '外国小说', '0202', '02', '外国小说', '2022-03-07 21:17:13', '2022-03-07 21:17:13');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `file_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '文件表主键',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '新建时间',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户表主键',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (1, '1_1646676259794_Snipaste_2022-03-03_13-20-57.png', '2022-03-08 02:04:20', '1');
INSERT INTO `file` VALUES (2, '1_1646676394668_HashMap.md', '2022-03-08 02:06:35', '1');
INSERT INTO `file` VALUES (3, '1_1646677928946_救世主.png', '2022-03-08 02:32:09', '1');
INSERT INTO `file` VALUES (4, '1_1646678342593_救世主.png', '2022-03-08 02:39:03', '1');
INSERT INTO `file` VALUES (5, '1_1646678459083_救世主.png', '2022-03-08 02:40:59', '1');
INSERT INTO `file` VALUES (6, '1_1646682259842_文城.jpg', '2022-03-08 03:44:20', '1');
INSERT INTO `file` VALUES (7, '1_1646682408421_第一人称单数.jpg', '2022-03-08 03:46:48', '1');
INSERT INTO `file` VALUES (8, '1_1646682538568_追风筝的人.jpg', '2022-03-08 03:48:59', '1');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单表主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户表主键',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `address_id` int(0) NULL DEFAULT NULL COMMENT '地址表主键',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款金额',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 1, '已付款', 1, '390', '2022-03-09 04:34:41', '2022-03-09 04:34:41');

-- ----------------------------
-- Table structure for shopping
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping`  (
  `shopping_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '购物车表主键',
  `book_id` int(0) NULL DEFAULT NULL COMMENT '书籍表主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户表主键',
  `num` int(0) NULL DEFAULT NULL COMMENT '数量',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `order_id` int(0) NULL DEFAULT NULL COMMENT '订单表主键',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`shopping_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping
-- ----------------------------
INSERT INTO `shopping` VALUES (2, 3, 1, 3, '2022-03-08 19:40:08', '2022-03-09 04:34:42', 1, '177');
INSERT INTO `shopping` VALUES (3, 4, 1, 3, '2022-03-09 01:02:40', '2022-03-09 04:34:42', 1, '168');
INSERT INTO `shopping` VALUES (4, 5, 1, 1, '2022-03-09 01:02:42', '2022-03-09 04:34:42', 1, '45');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型',
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '余额',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '13900000000', '000', '9', '0', '2022-03-07 14:46:45', '2022-03-07 15:57:55', '12', '男');
INSERT INTO `user` VALUES (2, 'dangdang', '13900000001', '000', '1', '0', '2022-03-07 14:57:37', '2022-03-07 16:27:14', '10', '男');
INSERT INTO `user` VALUES (4, 'test', '13900000002', '000', '1', '0', '2022-03-07 16:37:33', '2022-03-07 16:37:33', '1', '男');

SET FOREIGN_KEY_CHECKS = 1;
