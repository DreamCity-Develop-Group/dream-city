/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : dreamcity

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-09-21 19:36:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `account_dynamic`;
CREATE TABLE `account_dynamic` (
  `dyn_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dyn_acc_id` int(10) unsigned NOT NULL COMMENT '账户id',
  `dyn_player_id` varchar(64) DEFAULT NULL COMMENT '玩家id',
  `order_id` int(11) unsigned DEFAULT NULL COMMENT '订单id',
  `dyn_amount` decimal(9,4) NOT NULL DEFAULT '0.0000' COMMENT '动账金额',
  `dyn_type` char(3) DEFAULT NULL COMMENT '动账类型(入账in,出账out)',
  `dyn_amount_type` varchar(10) DEFAULT NULL COMMENT '资金类型（投资usdt，投资mt，转账transfer，提现withdraw）',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='认证码';

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
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否可用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='投资项目表';

-- ----------------------------
-- Records of city_invest
-- ----------------------------
INSERT INTO `city_invest` VALUES ('1', '测试项目1', '5', '2019-09-18 17:56:20', '0.1', '0.5', '2019-09-30 17:56:50', null, null, null);

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
  `is_valid` char(1) NOT NULL DEFAULT 'Y' COMMENT '是否可用',
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
INSERT INTO `city_player` VALUES ('99', '55ACB2722E33436F81040D0C8F257BF3', '17879502056', 'sd', '12345678', null, null, '1', '2019-09-11 12:23:10', null);
INSERT INTO `city_player` VALUES ('100', 'FD826FE2E378445594D23CA84C0C485D', '123', '123', '123', null, '', '1', '2019-09-12 02:37:37', null);
INSERT INTO `city_player` VALUES ('102', 'A1351FDFF0E344908A6EBBDDAC7D506D', '17856454567', 'qw', '12345678', null, '', '1', '2019-09-12 03:29:52', null);
INSERT INTO `city_player` VALUES ('103', '758DEB6C0A854D569FF2FC8AC9B422C9', '17856457895', 'qwe', '12345678', null, '', '1', '2019-09-12 03:33:02', null);
INSERT INTO `city_player` VALUES ('104', '727311B48CB4404EB9CACA6B9B235251', '17845685468', 'wqe', '12345678', null, '', '1', '2019-09-12 03:38:00', null);
INSERT INTO `city_player` VALUES ('105', '8ABBDDFE576647F88B055214259F1E1B', '17879502059', 'wqe', '12345678', null, '', '1', '2019-09-12 05:05:17', null);
INSERT INTO `city_player` VALUES ('106', '035510F21E1F45FF87A25AE9C466BEFC', '17879502055', 'joj', '12345678', null, '', '1', '2019-09-12 06:30:04', null);
INSERT INTO `city_player` VALUES ('107', 'D16D96E2A4584DCFBDBFBDD465C24A9D', '123456', '123', '123', null, '', '1', '2019-09-18 13:45:55', null);
INSERT INTO `city_player` VALUES ('108', 'CF3ACC42551F485884333875B91B98CE', '12345', '123', '123', null, '', '1', '2019-09-18 13:51:16', null);
INSERT INTO `city_player` VALUES ('109', '1C644121C866488B821098B1889C9443', '12333', '123', '123', null, '', '1', '2019-09-18 13:54:53', null);
INSERT INTO `city_player` VALUES ('110', '3A0D407A2D3941BEB7A3BEB558BF7115', '12334', '123', '123', null, '', '1', '2019-09-18 13:59:07', null);
INSERT INTO `city_player` VALUES ('111', 'A3574CBFB89C4EC19066EC93812895FF', '12335', '123', '123', null, '', '1', '2019-09-18 13:59:47', null);
INSERT INTO `city_player` VALUES ('112', '7AEB3D7B32C64B4494CCB7A95679DA82', '12336', '123', '123', null, '', '1', '2019-09-18 14:00:52', null);
INSERT INTO `city_player` VALUES ('113', 'C9B2117DBB9F48B7861691DFCA8DA230', '12337', '123', '123', null, '', '1', '2019-09-18 14:01:27', null);
INSERT INTO `city_player` VALUES ('114', 'A913D88C807D49B99E920F9D08955A74', '17845618956', 'sw', '12345678', null, '', '1', '2019-09-18 17:51:24', null);
INSERT INTO `city_player` VALUES ('115', '85935D38078B4AC489ADA0F0052C2DFD', '13645675678', 'wqe', '12345678', null, '', '1', '2019-09-18 17:55:22', null);
INSERT INTO `city_player` VALUES ('116', 'ABCACE3C53AE49D3838B901189484F04', '16567896545', 'werw', '12345678', null, '', '1', '2019-09-18 18:01:12', null);
INSERT INTO `city_player` VALUES ('117', '6892624972C44E5DB02970A01388AF9E', '14354642345', 'ewqe', '12345678', null, '', '1', '2019-09-18 18:09:43', null);
INSERT INTO `city_player` VALUES ('118', 'FBA48491B83C48E3B04306BF4EA1A56E', '15323456789', 'qwe', '12345678', null, '', '1', '2019-09-18 18:11:37', null);
INSERT INTO `city_player` VALUES ('119', '1CFC1FE9DAE2412194A861C812D50FE2', '16734324324', '231', '11111111', null, '', '1', '2019-09-18 18:15:04', null);
INSERT INTO `city_player` VALUES ('120', '98154ACBBF104388BF463F50D032A528', '15642342345', 'qwe', 'qwe213213', null, '', '1', '2019-09-18 18:23:44', null);
INSERT INTO `city_player` VALUES ('121', '1D50EFC8CAC2479A94E8A96A8E4D93CC', '14523455676', 'qweqwe', 'qweqweqwe', null, '', '1', '2019-09-18 18:35:49', null);
INSERT INTO `city_player` VALUES ('122', 'F60E0C63FE8C4BE383AD82972DF4A6D6', '15678465234', 'qwe', 'qweqwewq', null, '', '1', '2019-09-18 19:08:10', null);
INSERT INTO `city_player` VALUES ('123', 'BDA36993CD064A5DB7CD29EB96967C97', '19812346543', 'wqe', '11111111', null, '', '1', '2019-09-18 20:00:01', null);
INSERT INTO `city_player` VALUES ('124', '17C13935AC0E431BB2602F61E4C9C001', '12312321311', 'wqe', '11111111', null, '', '1', '2019-09-18 20:01:53', null);
INSERT INTO `city_player` VALUES ('125', '9C9536019EFF4844844868A3C1B3BBE8', '17823424324', 'awree', '11111111', null, '', '1', '2019-09-18 20:11:03', null);
INSERT INTO `city_player` VALUES ('126', 'D889560F137B404FBBF9B56B792463CD', 'wew', 'wev', '23adf2sdfwe', null, '', '1', '2019-09-19 11:10:45', null);
INSERT INTO `city_player` VALUES ('127', '3EF1AC04DE1C446E9197B03E3D4E7F0B', 'we2w', 'wev', '23adf2sdfwe', null, '', '1', '2019-09-19 11:13:26', null);
INSERT INTO `city_player` VALUES ('128', '154F611725D049748CCB16EB9DDB29C2', 'we22w', 'wev', '23adf2sdfwe', null, '', '1', '2019-09-19 11:17:45', null);
INSERT INTO `city_player` VALUES ('129', 'DA41A96A45E44138A16E4EDE36282F68', 'we222w', 'wev', '23adf2sdfwe', null, '', '1', '2019-09-19 11:27:16', null);
INSERT INTO `city_player` VALUES ('130', 'AD091F75AA4342819998F69FCC29F372', 'we22322w', 'wev', '23adf2sdfwe', null, '', '1', '2019-09-19 11:30:41', null);
INSERT INTO `city_player` VALUES ('131', 'D544958A976C4370B6A625FD3825CEDA', 'we2232w2w', 'wev', '23adf2sdfwe', null, '', '1', '2019-09-19 11:33:46', null);
INSERT INTO `city_player` VALUES ('132', '5CEE59F40B5B45ABBE0AC7450CB27F6D', 'we2232ww2w', 'wev', '23adf2sdfwe', null, '', '1', '2019-09-19 11:37:44', null);
INSERT INTO `city_player` VALUES ('133', '1CD633148E73434A979BCE71F8060087', 'w0ww2w', 'wev', '23adf2sdfwe', null, '', '1', '2019-09-19 11:55:16', null);

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
INSERT INTO `city_setting` VALUES ('1', '123', 'game', 'true', '1', null, '2019-09-16 08:25:39');
INSERT INTO `city_setting` VALUES ('2', '123', 'bg', 'false', '1', null, '2019-09-16 08:26:33');

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
-- Table structure for likes_log
-- ----------------------------
DROP TABLE IF EXISTS `likes_log`;
CREATE TABLE `likes_log` (
  `like_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `like_player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '点赞玩家ID',
  `like_liked_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '收获点赞玩家ID',
  `like_invest_id` int(11) unsigned DEFAULT NULL COMMENT '点赞投资ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='点赞记录表';

-- ----------------------------
-- Records of likes_log
-- ----------------------------
INSERT INTO `likes_log` VALUES ('1', 'FD826FE2E378445594D23CA84C0C485D', 'FD826FE2E378445594D23CA84C0C485D', '1', '2019-09-18 11:59:08', null);
INSERT INTO `likes_log` VALUES ('2', 'FD826FE2E378445594D23CA84C0C485D', 'FD826FE2E378445594D23CA84C0C485D', '1', '2019-09-18 11:59:20', null);
INSERT INTO `likes_log` VALUES ('3', 'FD826FE2E378445594D23CA84C0C485D', 'FD826FE2E378445594D23CA84C0C485D', '1', '2019-09-18 12:02:44', null);
INSERT INTO `likes_log` VALUES ('4', 'FD826FE2E378445594D23CA84C0C485D', 'FD826FE2E378445594D23CA84C0C485D', '1', '2019-09-19 12:02:54', null);

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
  `acc_income` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '积累总收入',
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
  `earn_max` decimal(9,4) unsigned DEFAULT '0.0000' COMMENT '最大提取额度',
  `earn_tax` decimal(9,4) unsigned DEFAULT '0.0000' COMMENT '税金',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`earn_id`) USING BTREE,
  KEY `index_earn_player_id` (`earn_player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家提现规则';

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
INSERT INTO `player_ext` VALUES ('1', 'FD826FE2E378445594D23CA84C0C485D', 'friendlink', 'imgurl', null, null);
INSERT INTO `player_ext` VALUES ('2', '55ACB2722E33436F81040D0C8F257BF3', 'friendlink', 'imgurl', null, null);
INSERT INTO `player_ext` VALUES ('3', 'A1351FDFF0E344908A6EBBDDAC7D506D', 'friendlink', 'imgurl', null, null);
INSERT INTO `player_ext` VALUES ('4', '758DEB6C0A854D569FF2FC8AC9B422C9', 'friendlink', 'imgurl', null, null);

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
INSERT INTO `player_friends` VALUES ('1', 'FD826FE2E378445594D23CA84C0C485D', '55ACB2722E33436F81040D0C8F257BF3', '1', null, '0', '2019-09-11 17:04:40', '2019-09-12 09:44:19');
INSERT INTO `player_friends` VALUES ('2', 'FD826FE2E378445594D23CA84C0C485D', 'A1351FDFF0E344908A6EBBDDAC7D506D', '0', '123', '0', '2019-09-12 17:21:09', null);
INSERT INTO `player_friends` VALUES ('3', 'FD826FE2E378445594D23CA84C0C485D', '758DEB6C0A854D569FF2FC8AC9B422C9', '0', null, '0', '2019-09-12 09:57:41', null);
INSERT INTO `player_friends` VALUES ('4', 'FD826FE2E378445594D23CA84C0C485D', '727311B48CB4404EB9CACA6B9B235251', '1', null, '1', '2019-09-16 16:29:23', '2019-09-16 16:32:11');

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
INSERT INTO `player_likes` VALUES ('1', 'FD826FE2E378445594D23CA84C0C485D', '1', '2', '2019-09-08 15:58:07', null);
INSERT INTO `player_likes` VALUES ('2', 'FD826FE2E378445594D23CA84C0C485D', '2', '1', '2019-09-09 15:58:23', null);
INSERT INTO `player_likes` VALUES ('3', 'FD826FE2E378445594D23CA84C0C485D', null, '1', '2019-09-18 11:59:08', null);
INSERT INTO `player_likes` VALUES ('4', 'FD826FE2E378445594D23CA84C0C485D', null, '1', '2019-09-18 11:59:13', null);
INSERT INTO `player_likes` VALUES ('5', 'FD826FE2E378445594D23CA84C0C485D', null, '1', '2019-09-18 12:02:44', null);
INSERT INTO `player_likes` VALUES ('6', 'FD826FE2E378445594D23CA84C0C485D', null, '1', '2019-09-18 12:02:44', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of player_login_log
-- ----------------------------
INSERT INTO `player_login_log` VALUES ('58', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-20 15:29:30');
INSERT INTO `player_login_log` VALUES ('59', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-20 15:52:34');

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
