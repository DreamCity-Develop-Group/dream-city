/*
Navicat MySQL Data Transfer

Source Server         : dream-city192.168.0.88
Source Server Version : 50562
Source Host           : 192.168.0.88:3306
Source Database       : dreamcity

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-09-26 18:42:30
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='认证码';

-- ----------------------------
-- Records of city_auth_code
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COMMENT='用户表（玩家）';

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_player_id`(`player_id`) USING BTREE
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
DROP TABLE IF EXISTS `game_notice`;
CREATE TABLE `game_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容',
  `notice_state` tinyint(255) DEFAULT NULL COMMENT '状态：1可用0不可用',
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `create_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
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
  `create_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
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

-- ----------------------------
-- Table structure for player_account
-- ----------------------------
DROP TABLE IF EXISTS `player_account`;
CREATE TABLE `player_account` (
  `acc_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `acc_player_id` varchar(64) NOT NULL COMMENT '账户玩家',
  `acc_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账户地址',
  `acc_pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账户密码',
  `acc_usdt` decimal(9,4) NOT NULL DEFAULT 0.0000 COMMENT '账户usdt额度',
  `acc_usdt_available` decimal(9,4) unsigned NOT NULL DEFAULT 0.0000 COMMENT 'usdt可用金额',
  `acc_usdt_freeze` decimal(9, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT 'usdt冻结金额',
  `acc_mt` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT '账户mt额度',
  `acc_mt_available` decimal(9, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT 'mt可用金额',
  `acc_mt_freeze` decimal(9, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT 'mt冻结金额',
  `acc_income` decimal(9,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '积累总收入',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`acc_id`) USING BTREE,
  INDEX `index_acc_player_id`(`acc_player_id`) USING BTREE
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家提现收益';

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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_player_id`(`player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展表（玩家）';

-- ----------------------------
-- Records of player_ext

-- ----------------------------
-- Table structure for player_friends
-- ----------------------------
DROP TABLE IF EXISTS `player_friends`;
CREATE TABLE `player_friends`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `friend_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '好友id',
  `agree` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '同意添加',
  `invite` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邀请码（来自friend_id）',
  `is_valid` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可用的',
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_player_id`(`player_id`) USING BTREE,
  INDEX `index_friend_id`(`friend_id`) USING BTREE,
  INDEX `index_invite`(`invite`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '好友' ROW_FORMAT = Dynamic;

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
  INDEX `index_player_id`(`player_id`) USING BTREE,
  INDEX `index_grade`(`grade`) USING BTREE
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
  `create_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0)
  PRIMARY KEY (`liked_id`),
  KEY `index_liked_player_id` (`liked_player_id`),
  KEY `index_liked_invest_id` (`liked_invest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='玩家获赞表';

-- ----------------------------
-- Records of player_likes
-- ----------------------------
DROP TABLE IF EXISTS `player_likes_log`;
CREATE TABLE `player_likes_log`  (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `like_player_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '点赞玩家ID',
  `like_liked_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收获点赞玩家ID',
  `like_invest_id` int(11) DEFAULT NULL COMMENT '点赞投资ID',
  `create_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`like_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  INDEX `index_player_id`(`player_id`) USING BTREE,
  INDEX `index_imei`(`imei`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of player_login_log
-- ----------------------------

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
-- Table structure for rule_item
-- ----------------------------
DROP TABLE IF EXISTS `rule_item`;
CREATE TABLE `rule_item` (
  `item_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) NOT NULL COMMENT '规则项目名称',
  `item_desc` varchar(0) DEFAULT NULL COMMENT '规则项目描述',
  `item_state` tinyint(4) DEFAULT NULL COMMENT '可用状态',
  `create_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0)
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
  `verify_trade_id` int(11) unsigned DEFAULT NULL COMMENT '交易id(交易记录表)',
  `verify_emp_id` int(11) unsigned DEFAULT NULL COMMENT '审核人id(员工表)',
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

-- Table structure for sales_order
-- ----------------------------
DROP TABLE IF EXISTS `sales_order`;
CREATE TABLE `sales_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单ID',
  `order_amount` double(255, 0) DEFAULT NULL COMMENT '订单额度',
  `order_buy_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单购买类型',
  `order_pay_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单支付类型',
  `order_pay_amount` double(255, 0) DEFAULT NULL COMMENT '支付额度',
  `order_player_buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单玩家',
  `order_player_seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单卖家',
  `order_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单状态：0创建1完成2拒绝3超时',
  `createtime` datetime(0) DEFAULT NULL,
  `updatetime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
