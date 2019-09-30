/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : dreamcity

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-09-30 19:22:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city_auth_code
-- ----------------------------
DROP TABLE IF EXISTS `city_auth_code`;
CREATE TABLE `city_auth_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '认证码',
  `phone` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户id',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_code` (`code`),
  KEY `index_phone` (`phone`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='认证码';

-- ----------------------------
-- Records of city_auth_code
-- ----------------------------
INSERT INTO `city_auth_code` VALUES ('1', '337219', '17895648524', null, '2019-09-24 03:50:48');
INSERT INTO `city_auth_code` VALUES ('2', '468360', '13956489561', null, '2019-09-24 03:57:07');
INSERT INTO `city_auth_code` VALUES ('3', '663288', '13956489561', null, '2019-09-24 04:07:33');
INSERT INTO `city_auth_code` VALUES ('4', '817277', '13956489561', null, '2019-09-24 04:07:33');
INSERT INTO `city_auth_code` VALUES ('5', '788875', '13956489561', null, '2019-09-24 04:07:34');
INSERT INTO `city_auth_code` VALUES ('6', '772209', '15478951564', null, '2019-09-24 04:12:13');
INSERT INTO `city_auth_code` VALUES ('7', '112666', '15478951564', null, '2019-09-24 04:13:45');
INSERT INTO `city_auth_code` VALUES ('8', '354758', '1', null, '2019-09-24 04:21:31');
INSERT INTO `city_auth_code` VALUES ('9', '376427', '12', null, '2019-09-24 04:24:22');
INSERT INTO `city_auth_code` VALUES ('10', '921368', '123', null, '2019-09-24 04:27:08');
INSERT INTO `city_auth_code` VALUES ('11', '322331', '1234', null, '2019-09-24 04:32:49');
INSERT INTO `city_auth_code` VALUES ('12', '728758', '1234', null, '2019-09-24 04:51:17');
INSERT INTO `city_auth_code` VALUES ('13', '29943', '1234', null, '2019-09-24 04:53:11');
INSERT INTO `city_auth_code` VALUES ('14', '175238', '17879502056', null, '2019-09-28 02:37:34');
INSERT INTO `city_auth_code` VALUES ('15', '101749', '17879502056', null, '2019-09-28 02:54:04');
INSERT INTO `city_auth_code` VALUES ('16', '372459', '17879502050', null, '2019-09-28 02:54:21');
INSERT INTO `city_auth_code` VALUES ('17', '130462', '17879502051', null, '2019-09-28 02:55:27');
INSERT INTO `city_auth_code` VALUES ('18', '640204', '17879502052', null, '2019-09-28 03:10:51');
INSERT INTO `city_auth_code` VALUES ('19', '826418', '17879502053', null, '2019-09-28 03:16:45');
INSERT INTO `city_auth_code` VALUES ('20', '356660', '17879502054', null, '2019-09-28 03:18:51');
INSERT INTO `city_auth_code` VALUES ('21', '569908', '17879502055', null, '2019-09-28 03:20:43');
INSERT INTO `city_auth_code` VALUES ('22', '229865', '17879502058', null, '2019-09-28 03:25:14');
INSERT INTO `city_auth_code` VALUES ('23', '599859', '17879502059', null, '2019-09-28 03:27:21');
INSERT INTO `city_auth_code` VALUES ('24', '852887', '17879502040', null, '2019-09-28 03:31:23');
INSERT INTO `city_auth_code` VALUES ('25', '793917', '17879502041', null, '2019-09-28 03:32:01');
INSERT INTO `city_auth_code` VALUES ('26', '839266', '17879502040', null, '2019-09-28 12:51:45');
INSERT INTO `city_auth_code` VALUES ('27', '796877', '17879502040', null, '2019-09-28 12:51:56');

-- ----------------------------
-- Table structure for city_file
-- ----------------------------
DROP TABLE IF EXISTS `city_file`;
CREATE TABLE `city_file` (
  `id` bigint(20) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件访问路径',
  `file_type` varchar(20) DEFAULT NULL COMMENT '文件类型（玩家头像:player_img，物业：property）',
  `is_valid` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用的',
  `from_id` varchar(64) DEFAULT NULL COMMENT '添加人id',
  `descr` varchar(50) DEFAULT NULL,
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
  `in_limit` decimal(9,4) unsigned DEFAULT NULL COMMENT '限额',
  `in_start` datetime DEFAULT NULL COMMENT '开始时间',
  `in_tax` float(6,4) unsigned zerofill DEFAULT NULL COMMENT '税金',
  `in_earning` tinyint(3) unsigned DEFAULT NULL COMMENT '收益倍数',
  `in_end` datetime DEFAULT NULL COMMENT '投资结束时间',
  `in_img` varchar(255) DEFAULT NULL COMMENT '项目图片地址(默认主图)',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否可投',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='投资项目表';

-- ----------------------------
-- Records of city_invest
-- ----------------------------
INSERT INTO `city_invest` VALUES ('1', '测试项目1', '5.0000', '2019-09-18 17:56:20', '0.0200', '2', '2019-09-30 17:56:50', null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COMMENT='用户表（玩家）';

-- ----------------------------
-- Records of city_player
-- ----------------------------
INSERT INTO `city_player` VALUES ('100', 'FD826FE2E378445594D23CA84C0C485D', '123', '123', '123', null, '', '1', '2019-09-12 02:37:37', null);
INSERT INTO `city_player` VALUES ('111', '99B1D351FD5242CD8F73E86A2BBC89A0', '17879502040', 'zp00', '11111111', null, 'fcf7dd', 'Y', '2019-09-28 11:31:28', null);
INSERT INTO `city_player` VALUES ('112', 'E13D6322D389411C959CD7AC7A2B230F', '17879502041', 'zp01', '11111111', null, 'eb47d7', 'Y', '2019-09-28 11:32:11', null);

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
-- Table structure for game_notice
-- ----------------------------
DROP TABLE IF EXISTS `game_notice`;
CREATE TABLE `game_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_content` varchar(255) DEFAULT NULL COMMENT '内容',
  `notice_state` tinyint(4) DEFAULT NULL COMMENT '状态：1可用0不可用',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of game_notice
-- ----------------------------

-- ----------------------------
-- Table structure for invest_order
-- ----------------------------
DROP TABLE IF EXISTS `invest_order`;
CREATE TABLE `invest_order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_invest_id` int(11) unsigned DEFAULT NULL COMMENT '投资项目ID',
  `order_payer_id` varchar(255) DEFAULT NULL COMMENT '玩家ID',
  `order_amount` decimal(9,4) unsigned DEFAULT NULL COMMENT '投资金额',
  `order_state` varchar(20) DEFAULT NULL COMMENT '状态(PAID待支付,PAY已支付,WAIT待审核，TOBESHIPPED待发货，SHIPPED已发货，CLOSE关闭，INVALID作废)',
  `order_repeat` tinyint(4) unsigned DEFAULT '0' COMMENT '是否复投（0否，1是）',
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
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型：0-菜单 1-子菜单 2-按钮',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0否，1是)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `menu_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, '系统管理', '10000', null, null, '0', 'system', null, '2017-09-20 15:52:54', 'admin', '2017-09-20 15:52:54', null, '0');
INSERT INTO `menu` VALUES ('2', '1', '用户管理', '10100', null, null, '1', 'system:user', null, '2017-09-20 15:55:34', 'admin', '2017-09-20 15:55:34', null, '0');
INSERT INTO `menu` VALUES ('3', '2', '编辑用户', '10101', null, null, '2', 'system:user:edit', null, '2018-05-14 22:45:54', null, '2018-05-14 22:45:54', null, '0');
INSERT INTO `menu` VALUES ('4', '2', '删除用户', '10102', null, null, '2', 'system:user:delete', null, '2018-05-14 22:47:25', null, '2018-05-14 22:47:25', null, '0');
INSERT INTO `menu` VALUES ('5', '2', '分配角色', '10103', null, null, '2', 'system:user:grant', null, '2018-05-14 22:48:04', null, '2018-05-14 22:48:04', null, '0');
INSERT INTO `menu` VALUES ('6', '2', '添加用户', '10104', null, null, '2', 'system:user:add', null, '2018-05-14 22:49:16', null, '2018-05-14 22:49:16', null, '0');
INSERT INTO `menu` VALUES ('7', '1', '角色管理', '10200', null, null, '1', 'system:role', null, '2018-05-19 22:03:19', null, '2018-05-19 22:03:19', null, '0');
INSERT INTO `menu` VALUES ('8', '7', '添加角色', '10201', null, null, '2', 'system:role:add', null, '2018-05-19 23:08:02', null, '2018-05-19 23:08:02', null, '0');
INSERT INTO `menu` VALUES ('9', '7', '编辑角色', '10202', null, null, '2', 'system:role:edit', null, '2018-05-19 23:08:02', null, '2018-05-19 23:08:02', null, '0');
INSERT INTO `menu` VALUES ('10', '7', '删除角色', '10203', null, null, '2', 'system:role:delete', null, '2018-05-19 23:08:02', null, '2018-05-19 23:08:02', null, '0');
INSERT INTO `menu` VALUES ('11', '7', '分配资源', '10204', null, null, '2', 'system:role:grant', null, '2018-05-19 23:08:02', null, '2018-05-19 23:08:02', null, '0');
INSERT INTO `menu` VALUES ('12', '1', '资源管理', '10300', null, null, '1', 'system:menu', null, '2018-05-20 22:16:39', null, '2018-05-20 22:16:39', null, '0');
INSERT INTO `menu` VALUES ('13', '12', '添加资源', '10301', null, null, '2', 'system:menu:add', null, '2018-05-20 22:17:11', null, '2018-05-20 22:17:11', null, '0');
INSERT INTO `menu` VALUES ('14', '12', '编辑资源', '10302', null, null, '2', 'system:menu:edit', null, '2018-05-20 22:17:47', null, '2018-05-20 22:17:47', null, '0');
INSERT INTO `menu` VALUES ('15', '12', '删除资源', '10303', null, null, '2', 'system:menu:delete', null, '2018-05-20 22:18:16', null, '2018-05-20 22:18:16', null, '0');
INSERT INTO `menu` VALUES ('48', null, 'test3', '30101', '', null, '1', 'system:test3', null, '2017-11-06 20:39:01', null, '2017-11-06 20:39:01', '', '0');

-- ----------------------------
-- Table structure for player_account
-- ----------------------------
DROP TABLE IF EXISTS `player_account`;
CREATE TABLE `player_account` (
  `acc_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `acc_player_id` varchar(64) NOT NULL COMMENT '账户玩家id',
  `acc_usdt` decimal(9,4) NOT NULL DEFAULT '0.0000' COMMENT '账户usdt额度',
  `acc_usdt_available` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'usdt可用金额',
  `acc_usdt_freeze` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'usdt冻结金额',
  `acc_mt` decimal(9,4) NOT NULL DEFAULT '0.0000' COMMENT '账户mt额度',
  `acc_mt_available` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'mt可用金额',
  `acc_mt_freeze` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'mt冻结金额',
  `acc_income` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '积累总收入',
  `acc_addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`acc_id`),
  KEY `index_acc_player_id` (`acc_player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000008 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家账户表';

-- ----------------------------
-- Records of player_account
-- ----------------------------
INSERT INTO `player_account` VALUES ('888888888', '88888888888888888888888888888888', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, '2018-08-08 08:08:08', null);
INSERT INTO `player_account` VALUES ('999999999', '99999999999999999999999999999999', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, '2019-09-09 09:09:09', null);
INSERT INTO `player_account` VALUES ('1000000006', '99B1D351FD5242CD8F73E86A2BBC89A0', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', 'F3936147126A459497BC6328E25E614C', null, null);
INSERT INTO `player_account` VALUES ('1000000007', 'E13D6322D389411C959CD7AC7A2B230F', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '78D3FAAD3A1944888774DD9E3DF341EB', null, null);

-- ----------------------------
-- Table structure for player_earning
-- ----------------------------
DROP TABLE IF EXISTS `player_earning`;
CREATE TABLE `player_earning` (
  `earn_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `earn_player_id` varchar(64) NOT NULL COMMENT '用户id',
  `earn_max` decimal(9,4) unsigned DEFAULT '0.0000' COMMENT '最大提取额度',
  `earn_tax` decimal(9,4) unsigned DEFAULT '0.0000' COMMENT '税金',
  `is_withdrew` char(1) NOT NULL DEFAULT 'N' COMMENT '是否已经提取(N否，Y是）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展表（玩家）';

-- ----------------------------
-- Records of player_ext
-- ----------------------------
INSERT INTO `player_ext` VALUES ('15', '99B1D351FD5242CD8F73E86A2BBC89A0', 'aaaa', 'aaaa', null, null);
INSERT INTO `player_ext` VALUES ('16', 'E13D6322D389411C959CD7AC7A2B230F', 'aaaa', 'aaaaa', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='好友';

-- ----------------------------
-- Records of player_friends
-- ----------------------------
INSERT INTO `player_friends` VALUES ('1', 'FD826FE2E378445594D23CA84C0C485D', '99B1D351FD5242CD8F73E86A2BBC89A0', '1', null, '0', '2019-09-11 17:04:40', '2019-09-12 09:44:19');
INSERT INTO `player_friends` VALUES ('2', 'FD826FE2E378445594D23CA84C0C485D', 'A1351FDFF0E344908A6EBBDDAC7D506D', '0', '123', '0', '2019-09-12 17:21:09', null);
INSERT INTO `player_friends` VALUES ('3', 'FD826FE2E378445594D23CA84C0C485D', 'E13D6322D389411C959CD7AC7A2B230F', '0', null, '0', '2019-09-12 09:57:41', null);
INSERT INTO `player_friends` VALUES ('6', '99B1D351FD5242CD8F73E86A2BBC89A0', 'E13D6322D389411C959CD7AC7A2B230F', '1', null, '1', '2019-09-28 17:36:01', '2019-09-28 19:39:33');
INSERT INTO `player_friends` VALUES ('7', 'E13D6322D389411C959CD7AC7A2B230F', '99B1D351FD5242CD8F73E86A2BBC89A0', '0', null, '1', '2019-09-28 18:01:24', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='玩家等级(会员/商会等级)';

-- ----------------------------
-- Records of player_grade
-- ----------------------------
INSERT INTO `player_grade` VALUES ('1', 'E13D6322D389411C959CD7AC7A2B230F', '1', '0', null, null);
INSERT INTO `player_grade` VALUES ('2', '99B1D351FD5242CD8F73E86A2BBC89A0', '1', '0', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of player_login_log
-- ----------------------------
INSERT INTO `player_login_log` VALUES ('58', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-20 15:29:30');
INSERT INTO `player_login_log` VALUES ('59', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-20 15:52:34');
INSERT INTO `player_login_log` VALUES ('60', null, null, null, 'login', null, '2019-09-28 10:36:19');
INSERT INTO `player_login_log` VALUES ('61', null, null, null, 'login', null, '2019-09-28 10:36:59');
INSERT INTO `player_login_log` VALUES ('62', 'FF27EE74B552460C96905EDE9EE58F61', null, null, 'login', null, '2019-09-28 11:33:10');
INSERT INTO `player_login_log` VALUES ('63', 'FF27EE74B552460C96905EDE9EE58F61', null, null, 'login', null, '2019-09-28 11:34:31');
INSERT INTO `player_login_log` VALUES ('64', 'FF27EE74B552460C96905EDE9EE58F61', null, null, 'login', null, '2019-09-28 11:36:01');
INSERT INTO `player_login_log` VALUES ('65', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 11:38:38');
INSERT INTO `player_login_log` VALUES ('66', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 11:45:33');
INSERT INTO `player_login_log` VALUES ('67', null, null, null, 'login', null, '2019-09-28 11:52:39');
INSERT INTO `player_login_log` VALUES ('68', null, null, null, 'login', null, '2019-09-28 11:52:48');
INSERT INTO `player_login_log` VALUES ('69', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 11:53:07');
INSERT INTO `player_login_log` VALUES ('70', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 11:56:22');
INSERT INTO `player_login_log` VALUES ('71', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 11:58:53');
INSERT INTO `player_login_log` VALUES ('72', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 11:59:03');
INSERT INTO `player_login_log` VALUES ('73', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 12:04:04');
INSERT INTO `player_login_log` VALUES ('74', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 12:14:39');
INSERT INTO `player_login_log` VALUES ('75', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 12:35:11');
INSERT INTO `player_login_log` VALUES ('76', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 12:35:18');
INSERT INTO `player_login_log` VALUES ('77', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 14:01:08');
INSERT INTO `player_login_log` VALUES ('78', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 14:26:28');
INSERT INTO `player_login_log` VALUES ('79', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 15:15:08');
INSERT INTO `player_login_log` VALUES ('80', null, null, null, 'login', null, '2019-09-28 15:38:45');
INSERT INTO `player_login_log` VALUES ('81', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 15:38:55');
INSERT INTO `player_login_log` VALUES ('82', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 15:45:50');
INSERT INTO `player_login_log` VALUES ('83', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 15:57:54');
INSERT INTO `player_login_log` VALUES ('84', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:02:15');
INSERT INTO `player_login_log` VALUES ('85', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:07:09');
INSERT INTO `player_login_log` VALUES ('86', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 16:17:44');
INSERT INTO `player_login_log` VALUES ('87', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 16:23:52');
INSERT INTO `player_login_log` VALUES ('88', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:27:32');
INSERT INTO `player_login_log` VALUES ('89', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:27:36');
INSERT INTO `player_login_log` VALUES ('90', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 16:32:35');
INSERT INTO `player_login_log` VALUES ('91', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:37:16');
INSERT INTO `player_login_log` VALUES ('92', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:44:42');
INSERT INTO `player_login_log` VALUES ('93', null, null, null, 'login', null, '2019-09-28 16:48:29');
INSERT INTO `player_login_log` VALUES ('94', null, null, null, 'login', null, '2019-09-28 16:48:34');
INSERT INTO `player_login_log` VALUES ('95', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:48:40');
INSERT INTO `player_login_log` VALUES ('96', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:56:29');
INSERT INTO `player_login_log` VALUES ('97', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 16:57:35');
INSERT INTO `player_login_log` VALUES ('98', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 17:05:44');
INSERT INTO `player_login_log` VALUES ('99', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 17:17:51');
INSERT INTO `player_login_log` VALUES ('100', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 17:29:52');
INSERT INTO `player_login_log` VALUES ('101', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 17:32:33');
INSERT INTO `player_login_log` VALUES ('102', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 17:35:48');
INSERT INTO `player_login_log` VALUES ('103', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 17:35:56');
INSERT INTO `player_login_log` VALUES ('104', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 18:01:04');
INSERT INTO `player_login_log` VALUES ('105', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 18:01:46');
INSERT INTO `player_login_log` VALUES ('106', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 18:27:03');
INSERT INTO `player_login_log` VALUES ('107', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 18:27:28');
INSERT INTO `player_login_log` VALUES ('108', 'E13D6322D389411C959CD7AC7A2B230F', null, null, 'login', null, '2019-09-28 18:30:11');
INSERT INTO `player_login_log` VALUES ('109', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 18:34:08');
INSERT INTO `player_login_log` VALUES ('110', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 19:07:40');
INSERT INTO `player_login_log` VALUES ('111', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 19:07:58');
INSERT INTO `player_login_log` VALUES ('112', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 19:20:44');
INSERT INTO `player_login_log` VALUES ('113', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 19:28:41');
INSERT INTO `player_login_log` VALUES ('114', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 19:36:35');
INSERT INTO `player_login_log` VALUES ('115', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 19:36:42');
INSERT INTO `player_login_log` VALUES ('116', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 19:39:17');
INSERT INTO `player_login_log` VALUES ('117', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 19:58:01');
INSERT INTO `player_login_log` VALUES ('118', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:18:42');
INSERT INTO `player_login_log` VALUES ('119', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:21:19');
INSERT INTO `player_login_log` VALUES ('120', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:27:56');
INSERT INTO `player_login_log` VALUES ('121', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:32:16');
INSERT INTO `player_login_log` VALUES ('122', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:33:37');
INSERT INTO `player_login_log` VALUES ('123', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:37:29');
INSERT INTO `player_login_log` VALUES ('124', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:40:40');
INSERT INTO `player_login_log` VALUES ('125', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:51:36');
INSERT INTO `player_login_log` VALUES ('126', '99B1D351FD5242CD8F73E86A2BBC89A0', null, null, 'login', null, '2019-09-28 20:51:39');

-- ----------------------------
-- Table structure for player_trade
-- ----------------------------
DROP TABLE IF EXISTS `player_trade`;
CREATE TABLE `player_trade` (
  `trade_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `trade_acc_id` int(10) unsigned NOT NULL COMMENT '账户id',
  `trade_player_id` varchar(64) DEFAULT NULL COMMENT '玩家id',
  `trade_order_id` int(11) unsigned DEFAULT NULL COMMENT '订单id',
  `trade_amount` decimal(9,4) NOT NULL DEFAULT '0.0000' COMMENT '交易金额',
  `trade_type` char(3) DEFAULT NULL COMMENT '动账类型(入账in,出账out)',
  `trade_amount_type` varchar(10) DEFAULT NULL COMMENT '资金类型（usdt投资:usdt_invest，mt投资:mt_invest，转账:transfer，提现:withdraw,usdt投资收益:usdt_earnings,mt投资收益:mt_earnings,usdt投资税金:usdt_invest_tax）',
  `trade_desc` varchar(100) DEFAULT NULL COMMENT '动账描述',
  `create_time` datetime DEFAULT NULL COMMENT '动账金额',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`trade_id`),
  KEY `index_trade_amount_type` (`trade_amount_type`) USING BTREE,
  KEY `index_trade_player_id` (`trade_player_id`),
  KEY `index_trade_order_id` (`trade_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易记录表';

-- ----------------------------
-- Records of player_trade
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `enname` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `useable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `role_del_flag` (`del_flag`) USING BTREE,
  KEY `role_en_name` (`enname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', 'admin', '1', null, '2017-08-25 11:26:36', null, '2017-08-25 11:26:36', null, '0');
INSERT INTO `role` VALUES ('2', '运营test', 'operator', '1', null, '2017-08-25 11:26:36', null, '2017-08-25 11:26:36', null, '0');
INSERT INTO `role` VALUES ('3', '人力', 'hr', '1', null, '2017-08-25 11:26:36', null, '2017-08-25 11:26:36', null, '0');
INSERT INTO `role` VALUES ('4', '产品', 'product', '1', null, '2017-08-25 11:26:37', null, '2017-08-25 11:26:37', null, '0');
INSERT INTO `role` VALUES ('5', '财务', 'finance', '1', null, '2017-08-25 11:26:37', null, '2017-08-25 11:26:37', null, '0');
INSERT INTO `role` VALUES ('6', '超级管理员', 'superadmin', '1', null, '2017-09-26 12:46:12', null, '2017-09-26 12:46:12', null, '0');
INSERT INTO `role` VALUES ('7', 'test3', null, '1', null, '2018-05-20 14:15:34', null, '2018-05-20 14:15:34', '吃了2', '0');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色-菜单';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('1', '6');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '12');
INSERT INTO `role_menu` VALUES ('1', '13');
INSERT INTO `role_menu` VALUES ('1', '14');
INSERT INTO `role_menu` VALUES ('1', '15');
INSERT INTO `role_menu` VALUES ('1', '48');

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

-- ----------------------------
-- Table structure for trade_verify
-- ----------------------------
DROP TABLE IF EXISTS `trade_verify`;
CREATE TABLE `trade_verify` (
  `verify_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `verify_emp_id` int(11) unsigned DEFAULT NULL COMMENT '审核人id(员工表)',
  `verify_trade_id` int(11) unsigned DEFAULT NULL COMMENT '交易id(交易记录表)',
  `verify_order_id` int(11) unsigned DEFAULT NULL COMMENT '订单id',
  `verify_amount` decimal(9,4) DEFAULT NULL,
  `verify_status` varchar(20) DEFAULT NULL COMMENT '审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)',
  `verify_desc` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`verify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易审核表';

-- ----------------------------
-- Records of trade_verify
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `photo` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0否，1是)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_login_name` (`login_name`) USING BTREE,
  KEY `idx_user_update_date` (`update_date`) USING BTREE,
  KEY `idx_user_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', null, '管理员', null, null, null, null, null, null, null, '2017-09-21 10:08:53', null, '2017-09-21 10:08:53', null, '0');
INSERT INTO `user` VALUES ('2', 'superadmin', 'e10adc3949ba59abbe56e057f20f883e', '1', null, '超级管理员', null, null, null, null, null, null, null, '2017-09-21 10:08:53', null, '2017-09-21 10:08:53', null, '0');
INSERT INTO `user` VALUES ('3', 'test2', 'e10adc3949ba59abbe56e057f20f883e', '1', null, '测试2', 'test@mail.cn', '13100131000', null, null, null, null, null, '2017-11-05 23:14:22', null, '2017-11-05 23:14:22', null, '0');
INSERT INTO `user` VALUES ('4', 'test1', 'f4cc399f0effd13c888e310ea2cf5399', '1', null, 'test1', 'tw@mail.com', '13800138000', null, null, null, null, null, '2018-05-26 00:00:45', null, '2018-05-16 23:27:53', '123eee', '0');
INSERT INTO `user` VALUES ('5', 'test1', 'e358efa489f58062f10dd7316b65649e', '1', null, 'test', '123@main.com', '123', null, null, null, null, null, '2018-05-19 20:13:03', null, '2018-05-19 20:13:19', '123', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户-角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '6');
INSERT INTO `user_role` VALUES ('3', '1');
INSERT INTO `user_role` VALUES ('3', '2');
INSERT INTO `user_role` VALUES ('4', '1');
INSERT INTO `user_role` VALUES ('4', '7');
