/*
Navicat MySQL Data Transfer

Source Server         : dream-city192.168.0.88
Source Server Version : 50562
Source Host           : 192.168.0.88:3306
Source Database       : dreamcity

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-09-19 15:55:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `account_dynamic`;
CREATE TABLE `account_dynamic` (
  `dyn_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dyn_acc_id` int(10) unsigned NOT NULL COMMENT '账户id',
  `dyn_amount` decimal(9,4) NOT NULL DEFAULT '0.0000' COMMENT '动账金额',
  `dyn_type` char(3) DEFAULT NULL COMMENT '动账类型(收入in,支出out)',
  `dyn_amount_type` char(4) DEFAULT NULL COMMENT '资金类型（usdt，mt）',
  `dyn_desc` varchar(100) DEFAULT NULL COMMENT '动账描述',
  `create_time` datetime DEFAULT NULL COMMENT '动账金额',
  PRIMARY KEY (`dyn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动账记录表（收入支出）';

-- ----------------------------
-- Records of account_dynamic
-- ----------------------------

-- ----------------------------
-- Table structure for city_auth_code
-- ----------------------------
DROP TABLE IF EXISTS `city_auth_code`;
CREATE TABLE `city_auth_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '认证码',
  `phone` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户id',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_code` (`code`),
  KEY `index_phone` (`phone`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='认证码';

-- ----------------------------
-- Records of city_auth_code
-- ----------------------------

-- ----------------------------
-- Table structure for city_file
-- ----------------------------
DROP TABLE IF EXISTS `city_file`;
CREATE TABLE `city_file` (
  `id` bigint(20) NOT NULL,
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件访问路径',
  `file_type` varchar(20) DEFAULT NULL COMMENT '文件类型',
  `is_valid` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用的',
  `from_id` varchar(64) DEFAULT NULL COMMENT '添加人id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_from_id` (`from_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件';

-- ----------------------------
-- Records of city_file
-- ----------------------------

-- ----------------------------
-- Table structure for city_invest
-- ----------------------------
DROP TABLE IF EXISTS `city_invest`;
CREATE TABLE `city_invest` (
  `in_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '标识',
  `in_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `in_limit` float unsigned DEFAULT NULL COMMENT '限额',
  `in_start` datetime DEFAULT NULL COMMENT '开始时间',
  `in_tax` double unsigned DEFAULT NULL COMMENT '税金',
  `in_earning` varchar(255) DEFAULT NULL COMMENT '收益倍数',
  `in_end` datetime DEFAULT NULL COMMENT '投资结束时间',
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='投资项目表';

-- ----------------------------
-- Records of city_invest
-- ----------------------------

-- ----------------------------
-- Table structure for city_message
-- ----------------------------
DROP TABLE IF EXISTS `city_message`;
CREATE TABLE `city_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `friend_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `content` varchar(600) DEFAULT NULL COMMENT '消息内容',
  `have_read` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0未读，1已读',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_friend_id` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of city_message
-- ----------------------------

-- ----------------------------
-- Table structure for city_player
-- ----------------------------
DROP TABLE IF EXISTS `city_player`;
CREATE TABLE `city_player` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '玩家id',
  `player_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `player_nick` varchar(50) NOT NULL COMMENT '昵称',
  `player_pass` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `player_trade_pass` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '交易密码',
  `player_invite` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邀请码',
  `is_valid` tinyint(1) unsigned DEFAULT '1',
  `create_time` datetime DEFAULT NULL COMMENT ' 创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_player_id` (`player_id`) USING BTREE,
  UNIQUE KEY `index_player_name` (`player_name`) USING BTREE,
  KEY `index_player_nick` (`player_nick`),
  KEY `index_player_invite` (`player_invite`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COMMENT='用户表（玩家）';

-- ----------------------------
-- Records of city_player
-- ----------------------------

-- ----------------------------
-- Table structure for city_setting
-- ----------------------------
DROP TABLE IF EXISTS `city_setting`;
CREATE TABLE `city_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) NOT NULL COMMENT '玩家id',
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT '设置类型',
  `val` varchar(50) NOT NULL DEFAULT '' COMMENT '设置值',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='游戏设置';

-- ----------------------------
-- Records of city_setting
-- ----------------------------

-- ----------------------------
-- Table structure for city_tree
-- ----------------------------
DROP TABLE IF EXISTS `city_tree`;
CREATE TABLE `city_tree` (
  `tree_id` int(11) NOT NULL,
  `tree_parent_id` int(11) DEFAULT NULL COMMENT '上级ID',
  `tree_player_id` int(255) DEFAULT NULL COMMENT '当前用户ID',
  `tree_relation` varchar(0) DEFAULT NULL COMMENT '关系网络',
  PRIMARY KEY (`tree_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of city_tree
-- ----------------------------

-- ----------------------------
-- Table structure for invest_order
-- ----------------------------
DROP TABLE IF EXISTS `invest_order`;
CREATE TABLE `invest_order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_invest_id` int(11) unsigned DEFAULT NULL COMMENT '投资项目ID',
  `order_payer_id` varchar(255) DEFAULT NULL COMMENT '玩家ID',
  `order_state` tinyint(4) DEFAULT NULL COMMENT '状态',
  `order_repeat` tinyint(4) DEFAULT NULL COMMENT '是否复投',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  KEY `index_order_invest_id` (`order_invest_id`),
  KEY `index_order_payer_id` (`order_payer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单（投资记录）';

-- ----------------------------
-- Records of invest_order
-- ----------------------------

-- ----------------------------
-- Table structure for invest_rule
-- ----------------------------
DROP TABLE IF EXISTS `invest_rule`;
CREATE TABLE `invest_rule` (
  `rule_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rule_name` varchar(50) DEFAULT NULL COMMENT '规则名称',
  `rule_desc` varchar(200) DEFAULT NULL COMMENT '规则描述',
  `rule_item` int(11) unsigned DEFAULT NULL COMMENT '规则项目',
  `rule_rate` double unsigned DEFAULT NULL,
  `rale_level` tinyint(4) unsigned DEFAULT NULL COMMENT '规则优先级别',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`rule_id`) USING BTREE,
  KEY `index_rule_item` (`rule_item`),
  KEY `index_rule_name` (`rule_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='项目规则表';

-- ----------------------------
-- Records of invest_rule
-- ----------------------------

-- ----------------------------
-- Table structure for player_account
-- ----------------------------
DROP TABLE IF EXISTS `player_account`;
CREATE TABLE `player_account` (
  `acc_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `acc_player_id` varchar(64) NOT NULL COMMENT '账户玩家',
  `acc_usdt` decimal(9,4) NOT NULL DEFAULT '0.0000' COMMENT '账户usdt额度',
  `acc_usdt_available` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'usdt可用金额',
  `acc_usdt_freeze` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'usdt冻结金额',
  `acc_mt` decimal(9,4) NOT NULL DEFAULT '0.0000' COMMENT '账户mt额度',
  `acc_mt_available` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'mt可用金额',
  `acc_mt_freeze` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'mt冻结金额',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`acc_id`),
  KEY `index_acc_player_id` (`acc_player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家账户表';

-- ----------------------------
-- Records of player_account
-- ----------------------------

-- ----------------------------
-- Table structure for player_earning
-- ----------------------------
DROP TABLE IF EXISTS `player_earning`;
CREATE TABLE `player_earning` (
  `earn_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `earn_player_id` varchar(64) NOT NULL COMMENT '用户id',
  `earn_max` double DEFAULT NULL COMMENT '最大提取额度',
  `earn_tax` double DEFAULT NULL COMMENT '税金',
  PRIMARY KEY (`earn_id`) USING BTREE,
  KEY `index_earn_player_id` (`earn_player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家收益';

-- ----------------------------
-- Records of player_earning
-- ----------------------------

-- ----------------------------
-- Table structure for player_ext
-- ----------------------------
DROP TABLE IF EXISTS `player_ext`;
CREATE TABLE `player_ext` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '用户id',
  `friendlink` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '主页入口',
  `imgurl` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像地址',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_player_id` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展表（玩家）';

-- ----------------------------
-- Records of player_ext
-- ----------------------------

-- ----------------------------
-- Table structure for player_friends
-- ----------------------------
DROP TABLE IF EXISTS `player_friends`;
CREATE TABLE `player_friends` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '用户id',
  `friend_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '好友id',
  `agree` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '同意添加',
  `invite` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邀请码（来自friend_id）',
  `is_valid` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用的',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_friend_id` (`friend_id`),
  KEY `index_invite` (`invite`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='好友';

-- ----------------------------
-- Records of player_friends
-- ----------------------------

-- ----------------------------
-- Table structure for player_grade
-- ----------------------------
DROP TABLE IF EXISTS `player_grade`;
CREATE TABLE `player_grade` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '玩家id',
  `grade` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '玩家等级',
  `integral` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '积分',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_grade` (`grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='玩家等级(会员/商会等级)';

-- ----------------------------
-- Records of player_grade
-- ----------------------------

-- ----------------------------
-- Table structure for player_likes
-- ----------------------------
DROP TABLE IF EXISTS `player_likes`;
CREATE TABLE `player_likes` (
  `liked_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `liked_player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '收获玩家',
  `liked_invest_id` int(10) unsigned DEFAULT NULL COMMENT '点赞项目ID',
  `liked_invest_total` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '项目点赞数量',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`liked_id`),
  KEY `index_liked_player_id` (`liked_player_id`),
  KEY `index_liked_invest_id` (`liked_invest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='玩家获赞表';

-- ----------------------------
-- Records of player_likes
-- ----------------------------

-- ----------------------------
-- Table structure for player_likes_log
-- ----------------------------
DROP TABLE IF EXISTS `player_likes_log`;
CREATE TABLE `player_likes_log` (
  `like_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `like_player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '点赞玩家ID',
  `like_liked_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '收获点赞玩家ID',
  `like_invest_id` int(11) unsigned DEFAULT NULL COMMENT '点赞投资ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='点赞记录表';

-- ----------------------------
-- Records of player_likes_log
-- ----------------------------

-- ----------------------------
-- Table structure for player_login_log
-- ----------------------------
DROP TABLE IF EXISTS `player_login_log`;
CREATE TABLE `player_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '玩家id',
  `imei` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '设备识别码',
  `ip` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'ip地址',
  `type` varchar(255) DEFAULT NULL,
  `descr` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_imei` (`imei`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of player_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for rule_item
-- ----------------------------
DROP TABLE IF EXISTS `rule_item`;
CREATE TABLE `rule_item` (
  `item_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) NOT NULL COMMENT '规则项目名称',
  `item_desc` varchar(0) DEFAULT NULL COMMENT '规则项目描述',
  `item_state` tinyint(4) DEFAULT NULL COMMENT '可用状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `index_item_name` (`item_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='规则表';

-- ----------------------------
-- Records of rule_item
-- ----------------------------
