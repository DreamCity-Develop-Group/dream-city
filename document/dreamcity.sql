/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : dreamcity

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 16/09/2019 17:01:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city_invest
-- ----------------------------
DROP TABLE IF EXISTS `city_invest`;
CREATE TABLE `city_invest`  (
  `in_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `in_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `in_limit` float NULL DEFAULT NULL COMMENT '限额',
  `in_start` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `in_tax` double NULL DEFAULT NULL COMMENT '税金',
  `in_earning` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收益倍数',
  `in_end` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '投资结束时间',
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for city_player
-- ----------------------------
DROP TABLE IF EXISTS `city_player`;
CREATE TABLE `city_player`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NULL DEFAULT NULL COMMENT '玩家id',
  `player_name` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '玩家账号',
  `player_nick` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `player_pass` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '玩家密码',
  `player_trade_pass` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易密码',
  `player_invite` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for city_tree
-- ----------------------------
DROP TABLE IF EXISTS `city_tree`;
CREATE TABLE `city_tree`  (
  `tree_id` int(11) NOT NULL,
  `tree_parent_id` int(11) NULL DEFAULT NULL COMMENT '上级ID',
  `tree_player_id` int(255) NULL DEFAULT NULL COMMENT '当前用户ID',
  `tree_relation` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关系网络',
  PRIMARY KEY (`tree_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for invest_order
-- ----------------------------
DROP TABLE IF EXISTS `invest_order`;
CREATE TABLE `invest_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_invest_id` int(11) NULL DEFAULT NULL COMMENT '投资项目ID',
  `order_payer_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '玩家ID',
  `order_state` tinyint(4) NULL DEFAULT NULL COMMENT '状态',
  `order_repeat` tinyint(4) NULL DEFAULT NULL COMMENT '是否复投',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for invest_rule
-- ----------------------------
DROP TABLE IF EXISTS `invest_rule`;
CREATE TABLE `invest_rule`  (
  `rule_id` int(11) NOT NULL,
  `rule_name` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `rule_desc` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则描述',
  `rule_item` int(11) NULL DEFAULT NULL COMMENT '规则项目',
  `rule_rate` double NULL DEFAULT NULL,
  `rale_level` tinyint(4) NULL DEFAULT NULL COMMENT '规则优先级别',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`rule_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for player_account
-- ----------------------------
DROP TABLE IF EXISTS `player_account`;
CREATE TABLE `player_account`  (
  `acc_id` int(11) NULL DEFAULT NULL,
  `acc_player_id` int(11) NULL DEFAULT NULL COMMENT '账户玩家',
  `acc_usdt` double NULL DEFAULT NULL COMMENT '账户usdt额度',
  `acc_mt` double NULL DEFAULT NULL COMMENT '账户mt额度'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for player_earning
-- ----------------------------
DROP TABLE IF EXISTS `player_earning`;
CREATE TABLE `player_earning`  (
  `earn_id` int(11) NOT NULL AUTO_INCREMENT,
  `earn_player_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `earn_max` double NULL DEFAULT NULL COMMENT '最大提取额度',
  `earn_tax` double NULL DEFAULT NULL COMMENT '税金',
  PRIMARY KEY (`earn_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for player_likes
-- ----------------------------
DROP TABLE IF EXISTS `t_player_likes`;
CREATE TABLE `t_player_likes` (
  `liked_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `liked_player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '收获玩家',
  `liked_invest_id` int(10) unsigned DEFAULT NULL COMMENT '点赞项目ID',
  `liked_invest_total` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '项目点赞数量',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`liked_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for player_likes_log
-- ----------------------------
DROP TABLE IF EXISTS `t_player_likes_log`;
CREATE TABLE `t_player_likes_log`  (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `like_player_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点赞玩家ID',
  `like_liked_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收获点赞玩家ID',
  `like_invest_id` int(11) NULL DEFAULT NULL COMMENT '点赞投资ID',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`like_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rule_item
-- ----------------------------
DROP TABLE IF EXISTS `rule_item`;
CREATE TABLE `rule_item`  (
  `item_id` int(11) NULL DEFAULT NULL,
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则项目名称',
  `item_desc` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则项目描述',
  `item_state` tinyint(4) NULL DEFAULT NULL COMMENT '可用状态',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
