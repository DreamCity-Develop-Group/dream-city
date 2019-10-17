
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city_auth_code
-- ----------------------------
DROP TABLE IF EXISTS `city_auth_code`;
CREATE TABLE `city_auth_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '认证码',
  `phone` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `valid` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_code` (`code`) USING BTREE,
  KEY `index_phone` (`phone`) USING BTREE,
  KEY `index_user_id` (`valid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='认证码';

-- ----------------------------
-- Records of city_auth_code
-- ----------------------------
INSERT INTO `city_auth_code` VALUES ('121', '73193', '17076613584', 'false', '2019-10-16 08:10:31');
INSERT INTO `city_auth_code` VALUES ('122', '314231', '17076613584', null, '2019-10-16 08:09:21');
INSERT INTO `city_auth_code` VALUES ('123', '877798', '13601234561', null, '2019-10-16 09:17:22');
INSERT INTO `city_auth_code` VALUES ('124', '630762', '13601234561', null, '2019-10-16 09:18:27');

-- ----------------------------
-- Table structure for city_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `city_dictionary`;
CREATE TABLE `city_dictionary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `val` varchar(600) DEFAULT NULL,
  `is_valid` tinyint(4) unsigned DEFAULT '1',
  `descr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='字典（配置项）';

-- ----------------------------
-- Records of city_dictionary
-- ----------------------------
INSERT INTO `city_dictionary` VALUES ('1', '平台账户id', 'platform.account.accIds', '4E2EE556055042AB80E3D164E51DDD1A', '1', '平台账户id，多个，都号隔开来自player_account表', null, null);
INSERT INTO `city_dictionary` VALUES ('2', '注册密码salt', 'register.password.salt', 'DREAM_CITY_890@#$%', '1', '注册密码salt', null, null);
INSERT INTO `city_dictionary` VALUES ('3', '提现税金', 'player.withdraw.mt.tax', '5', '1', '玩家提现税金', null, null);
INSERT INTO `city_dictionary` VALUES ('4', '转账税金', 'player.transfer.mt.tax', '5', '1', '玩家转账税金', null, null);
INSERT INTO `city_dictionary` VALUES ('5', '内部转账审核', 'player.inside.transfer.verify', 'true', '1', '玩家内部转账审核是否要审核，默认要审核', null, null);

-- ----------------------------
-- Table structure for city_file
-- ----------------------------
DROP TABLE IF EXISTS `city_file`;
CREATE TABLE `city_file` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件访问路径',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型（玩家头像:player_img，物业：property）',
  `is_valid` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用的',
  `from_id` varchar(64) DEFAULT NULL COMMENT '添加人id',
  `descr` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_from_id` (`from_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件';

-- ----------------------------
-- Records of city_file
-- ----------------------------

-- ----------------------------
-- Table structure for city_help
-- ----------------------------
DROP TABLE IF EXISTS `city_help`;
CREATE TABLE `city_help` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `content` text,
  `type` varchar(50) DEFAULT NULL,
  `is_valid` tinyint(4) unsigned DEFAULT '1',
  `descr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='帮助';

-- ----------------------------
-- Records of city_help
-- ----------------------------
INSERT INTO `city_help` VALUES ('2', '帮助2', '内容22', null, '1', null, null, null);
INSERT INTO `city_help` VALUES ('3', '帮助1', '鞍山市所所所所所所所所所付多所撒', 'help', '1', null, '2019-10-02 10:47:48', '2019-10-02 10:47:48');

-- ----------------------------
-- Table structure for city_invest
-- ----------------------------
DROP TABLE IF EXISTS `city_invest`;
CREATE TABLE `city_invest` (
  `in_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '标识',
  `in_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `in_type` int(4) unsigned DEFAULT NULL COMMENT '物业类型',
  `in_limit` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '限额',
  `in_start` datetime DEFAULT NULL COMMENT '开始时间',
  `in_personal_tax` decimal(20,4) unsigned zerofill DEFAULT '0000000000000000.0000' COMMENT '个人税金',
  `in_enterprise_tax` decimal(20,4) unsigned zerofill DEFAULT '0000000000000000.0000' COMMENT '企业税金',
  `in_quota_tax` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '定额税',
  `in_earning` tinyint(4) unsigned DEFAULT '0' COMMENT '收益倍数',
  `in_end` datetime DEFAULT NULL COMMENT '投资结束时间',
  `in_img` varchar(100) DEFAULT NULL COMMENT '项目图片地址(默认主图)',
  `is_valid` char(1) DEFAULT 'N' COMMENT '是否可投',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='投资项目表';

-- ----------------------------
-- Records of city_invest
-- ----------------------------
INSERT INTO `city_invest` VALUES ('1', '小吃摊', '11', '30.0000', '2019-09-18 17:56:20', '0000000000000000.0200', '0000000000000000.0000', '0.0000', '2', '2020-01-31 17:56:50', null, 'Y', null, null);
INSERT INTO `city_invest` VALUES ('2', '玩具摊', '21', '100.0000', '2019-10-01 21:04:25', '0000000000000000.0100', '0000000000000000.0000', '0.0000', '3', '2019-11-30 21:04:53', null, 'Y', null, null);
INSERT INTO `city_invest` VALUES ('3', '酒吧', '31', '300.0000', '2019-10-01 21:04:25', '0000000000000000.1000', '0000000000000000.1500', '0.0000', '3', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('4', '医药公司', '41', '500.0000', '2019-10-01 21:04:25', '0000000000000000.2000', '0000000000000000.1000', '0.0000', '4', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('5', '电影公司', '51', '1000.0000', '2019-10-01 21:04:25', '0000000000000000.2000', '0000000000000000.1500', '0.0000', '4', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('6', '汽车集团', '61', '3000.0000', '2019-10-01 21:04:25', '0000000000000000.1000', '0000000000000000.1000', '0.0000', '5', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('7', '投资集团', '71', '5000.0000', '2019-10-01 21:04:25', '0000000000000000.1000', '0000000000000000.0000', '0.0000', '5', '2019-11-30 21:04:53', null, 'N', null, null);

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
  `send_time` datetime DEFAULT NULL COMMENT '通知发送时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_friend_id` (`friend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of city_message
-- ----------------------------
INSERT INTO `city_message` VALUES ('1', 'FD826FE2E378445594D23CA84C0C485D', 'E13D6322D389411C959CD7AC7A2B230F', 'dgjnyrmtfsd', '0', '2019-10-02 18:03:30', '2019-10-02 18:03:30', null);
INSERT INTO `city_message` VALUES ('3', 'FD826FE2E378445594D23CA84C0C485D', '99B1D351FD5242CD8F73E86A2BBC89A0', 'asadvbsbz', '0', '2019-10-02 18:03:30', null, null);

-- ----------------------------
-- Table structure for city_notice
-- ----------------------------
DROP TABLE IF EXISTS `city_notice`;
CREATE TABLE `city_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_content` varchar(255) DEFAULT NULL COMMENT '内容',
  `notice_state` tinyint(4) DEFAULT NULL COMMENT '状态：1可用0不可用',
  `send_time` datetime DEFAULT NULL COMMENT '公告发送时间',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of city_notice
-- ----------------------------
INSERT INTO `city_notice` VALUES ('1', 'advababavs', '1', '2019-10-02 14:17:02', null);
INSERT INTO `city_notice` VALUES ('3', 'wwwwwwwwwwwwwwww', '1', '2019-10-02 14:17:02', '2019-10-02 14:17:02');

-- ----------------------------
-- Table structure for city_player
-- ----------------------------
DROP TABLE IF EXISTS `city_player`;
CREATE TABLE `city_player` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '玩家id',
  `player_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `player_nick` varchar(50) NOT NULL COMMENT '昵称',
  `player_pass` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `player_trade_pass` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '交易密码',
  `player_invite` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邀请码',
  `player_level` int(11) DEFAULT NULL COMMENT '玩家级别',
  `is_valid` tinyint(4) unsigned DEFAULT '1' COMMENT '是否可用',
  `create_time` datetime DEFAULT NULL COMMENT ' 创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index_player_id` (`player_id`) USING BTREE,
  UNIQUE KEY `index_player_name` (`player_name`) USING BTREE,
  KEY `index_player_nick` (`player_nick`) USING BTREE,
  KEY `index_player_invite` (`player_invite`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表（玩家）';

-- ----------------------------
-- Records of city_player
-- ----------------------------
INSERT INTO `city_player` VALUES ('1', '8A2922A66F474A0DA9B10FB4BCD59BA0', 'system', 'system', '8A2922A66F474A0DA9B10FB4BCD59BA0', null, 'system', null, '1', null, null);
INSERT INTO `city_player` VALUES ('152', 'FCCDC7A84EBD47BCB63F4B1281BE527D', '13601234568', '1234', 'e10adc3949ba59abbe56e057f20f883e', '123456', '4b057c', null, '1', '2019-10-11 11:05:31', '2019-10-11 20:42:24');
INSERT INTO `city_player` VALUES ('153', 'B22BD7C3B9374473AB7133C3A4271234', '13601234569', '12345', 'e10adc3949ba59abbe56e057f20f883e', null, '43ea52', null, '1', '2019-10-11 11:06:42', null);
INSERT INTO `city_player` VALUES ('154', '52ABA6CE89164C8484A7F7FFF16B3670', '13601234560', '111', 'e10adc3949ba59abbe56e057f20f883e', null, '7841fb', null, '1', '2019-10-11 11:07:12', null);
INSERT INTO `city_player` VALUES ('155', 'A07246C2924A415982ABE5E8C6DAD53D', '13601234561', '112', 'c33367701511b4f6020ec61ded352059', '123456', 'c73572', null, '1', '2019-10-11 11:07:57', '2019-10-15 16:52:44');
INSERT INTO `city_player` VALUES ('156', 'D6EDA06FDF654A46BC8299A05DDFF591', '13601234562', '113', 'e10adc3949ba59abbe56e057f20f883e', '111111', '6ec3ab', null, '1', '2019-10-11 11:08:13', '2019-10-15 18:44:50');
INSERT INTO `city_player` VALUES ('157', '1301094B88274AF78C26D532F0C9E6E3', '13601234563', 'qqq', 'e10adc3949ba59abbe56e057f20f883e', null, 'a5d24b', null, '1', '2019-10-11 18:35:37', null);
INSERT INTO `city_player` VALUES ('158', '68493901879941308DFF85CB8EA3A077', '13601234564', 'eee', 'e10adc3949ba59abbe56e057f20f883e', null, 'f57cc4', null, '1', '2019-10-11 18:51:23', null);
INSERT INTO `city_player` VALUES ('159', 'F3ECC5684F8C44339314ADF7768EB63B', '13601234565', 'ggg', 'e10adc3949ba59abbe56e057f20f883e', '123456', '1c2fea', null, '1', '2019-10-11 19:03:02', '2019-10-11 19:46:56');
INSERT INTO `city_player` VALUES ('160', '7C4E0329EC64423093D84281EB8A26C3', '13611234563', '去去去', 'e10adc3949ba59abbe56e057f20f883e', null, '5f0dac', null, '1', '2019-10-14 18:56:30', null);
INSERT INTO `city_player` VALUES ('161', 'DA744E2BD88343C8B2BF7750FC7E501B', '13611234564', '是是是', 'e10adc3949ba59abbe56e057f20f883e', null, '19d0e2', null, '1', '2019-10-14 18:58:25', null);
INSERT INTO `city_player` VALUES ('162', 'B62A7E3C3259429A92130B2196F6A39A', '13611234565', '是是看', 'e10adc3949ba59abbe56e057f20f883e', null, '833cad', null, '1', '2019-10-14 18:59:37', null);
INSERT INTO `city_player` VALUES ('163', '3A309D33667D4B45AD30FC318F255E61', '13022099406', '期Qi', '9ae0147d65724f72f74804af4aac6f13', null, '8bb722', null, '1', '2019-10-15 16:37:44', null);
INSERT INTO `city_player` VALUES ('164', '4937E1605DF84FB4A17C31DE22BF6782', '13866666154', 'gyy', 'e10adc3949ba59abbe56e057f20f883e', null, '6d53ae', null, '1', '2019-10-15 16:38:39', null);
INSERT INTO `city_player` VALUES ('165', 'BFA938AFDD784CFCA4850D7330A5407F', '13022099407', '期Qi1', '9ae0147d65724f72f74804af4aac6f13', null, 'f0f479', null, '1', '2019-10-15 16:39:38', null);
INSERT INTO `city_player` VALUES ('166', '7D09619AC1054CF1B8380F6ED098F797', '15918833191', '步入', 'e10adc3949ba59abbe56e057f20f883e', null, 'd0e9d8', null, '1', '2019-10-15 22:52:23', null);


-- ----------------------------
-- Table structure for city_player_grade
-- ----------------------------
DROP TABLE IF EXISTS `city_player_grade`;
CREATE TABLE `city_player_grade` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '玩家id',
  `grade` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '玩家等级',
  `integral` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '积分',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_player_id` (`player_id`) USING BTREE,
  KEY `index_grade` (`grade`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='玩家等级(会员/商会等级)';

-- ----------------------------
-- Records of city_player_grade
-- ----------------------------
INSERT INTO `city_player_grade` VALUES ('43', '956540BCA48549589B70DFF89B715F81', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('44', 'FCCDC7A84EBD47BCB63F4B1281BE527D', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('45', 'B22BD7C3B9374473AB7133C3A4271234', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('46', '52ABA6CE89164C8484A7F7FFF16B3670', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('47', 'A07246C2924A415982ABE5E8C6DAD53D', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('48', 'D6EDA06FDF654A46BC8299A05DDFF591', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('49', '1301094B88274AF78C26D532F0C9E6E3', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('50', '68493901879941308DFF85CB8EA3A077', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('51', 'F3ECC5684F8C44339314ADF7768EB63B', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('52', '7C4E0329EC64423093D84281EB8A26C3', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('53', 'DA744E2BD88343C8B2BF7750FC7E501B', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('54', 'B62A7E3C3259429A92130B2196F6A39A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('55', '3A309D33667D4B45AD30FC318F255E61', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('56', '4937E1605DF84FB4A17C31DE22BF6782', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('57', 'BFA938AFDD784CFCA4850D7330A5407F', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('58', '7D09619AC1054CF1B8380F6ED098F797', 'L1', '0', null, null);


-- ----------------------------
-- Table structure for city_tree
-- ----------------------------
DROP TABLE IF EXISTS `city_tree`;
CREATE TABLE `city_tree` (
  `tree_id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_parent_id` varchar(64) DEFAULT NULL COMMENT '上级ID',
  `tree_player_id` varchar(64) DEFAULT NULL COMMENT '当前用户ID',
  `tree_relation` varchar(1000) DEFAULT NULL COMMENT '关系网络',
  `send_auto` varchar(11) DEFAULT NULL COMMENT '是 否自动发货',
  `tree_level` int(11) DEFAULT NULL COMMENT '商会等级',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tree_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of city_tree
-- ----------------------------
INSERT INTO `city_tree` VALUES ('1', 'system', '8A2922A66F474A0DA9B10FB4BCD59BA0', 'system', '1', '100', '2019-10-11 11:26:38');
INSERT INTO `city_tree` VALUES ('65', '8A2922A66F474A0DA9B10FB4BCD59BA0', 'FCCDC7A84EBD47BCB63F4B1281BE527D', 'system/4b057c', '', '0', '2019-10-11 03:05:32');
INSERT INTO `city_tree` VALUES ('66', '8A2922A66F474A0DA9B10FB4BCD59BA0', 'B22BD7C3B9374473AB7133C3A4271234', 'system/43ea52', '', '0', '2019-10-11 03:06:42');
INSERT INTO `city_tree` VALUES ('67', 'FCCDC7A84EBD47BCB63F4B1281BE527D', '52ABA6CE89164C8484A7F7FFF16B3670', 'system/4b057c/7841fb', '', '0', '2019-10-11 03:07:13');
INSERT INTO `city_tree` VALUES ('68', '52ABA6CE89164C8484A7F7FFF16B3670', 'A07246C2924A415982ABE5E8C6DAD53D', 'system/4b057c/7841fb/c73572', '0', '0', '2019-10-13 12:10:51');
INSERT INTO `city_tree` VALUES ('69', 'A07246C2924A415982ABE5E8C6DAD53D', 'D6EDA06FDF654A46BC8299A05DDFF591', 'system/4b057c/7841fb/c73572/6ec3ab', '', '0', '2019-10-11 03:08:14');
INSERT INTO `city_tree` VALUES ('70', 'A07246C2924A415982ABE5E8C6DAD53D', 'F3ECC5684F8C44339314ADF7768EB63B', 'system/4b057c/7841fb/c73572/1c2fea', '', '0', '2019-10-11 11:44:17');

-- ----------------------------
-- Table structure for earn_falldown_log
-- ----------------------------
DROP TABLE IF EXISTS `earn_falldown_log`;
CREATE TABLE `earn_falldown_log` (
  `fall_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fall_invest_id` int(11) DEFAULT NULL COMMENT '掉落的项目',
  `fall_player_id` varchar(64) DEFAULT NULL COMMENT '掉落的玩家',
  `fall_amount` decimal(20,4) DEFAULT NULL COMMENT '掉落的额度',
  `create_time` datetime DEFAULT NULL COMMENT '掉落的时间',
  PRIMARY KEY (`fall_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of earn_falldown_log
-- ----------------------------

-- ----------------------------
-- Table structure for earn_income_log
-- ----------------------------
DROP TABLE IF EXISTS `earn_income_log`;
CREATE TABLE `earn_income_log` (
  `in_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `in_invest_id` int(11) DEFAULT NULL COMMENT '投资的ID',
  `in_player_id` varchar(64) DEFAULT NULL COMMENT '玩家的ID',
  `in_amount` decimal(20,4) DEFAULT NULL COMMENT '本次收益',
  `create_time` datetime DEFAULT NULL COMMENT '收益时间',
  PRIMARY KEY (`in_log_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of earn_income_log
-- ----------------------------

-- ----------------------------
-- Table structure for invest_allow
-- ----------------------------
DROP TABLE IF EXISTS `invest_allow`;
CREATE TABLE `invest_allow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` varchar(50) DEFAULT NULL,
  `allowed` varchar(255) DEFAULT NULL,
  `amount` decimal(20,4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of invest_allow
-- ----------------------------
INSERT INTO `invest_allow` VALUES ('10', 'D6EDA06FDF654A46BC8299A05DDFF591', '1', '10.0000', '2019-10-11 03:10:20');
INSERT INTO `invest_allow` VALUES ('11', 'wew32d', '1', '10.0000', '2019-10-11 06:20:49');
INSERT INTO `invest_allow` VALUES ('12', 'F3ECC5684F8C44339314ADF7768EB63B', '1', '10.0000', '2019-10-11 11:47:09');
INSERT INTO `invest_allow` VALUES ('14', 'FCCDC7A84EBD47BCB63F4B1281BE527D', '1', '10.0000', '2019-10-11 12:42:24');
INSERT INTO `invest_allow` VALUES ('15', 'A07246C2924A415982ABE5E8C6DAD53D', '1', '10.0000', '2019-10-11 13:18:13');

-- ----------------------------
-- Table structure for invest_order
-- ----------------------------
DROP TABLE IF EXISTS `invest_order`;
CREATE TABLE `invest_order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_invest_id` int(11) unsigned DEFAULT NULL COMMENT '投资项目ID',
  `order_payer_id` varchar(64) DEFAULT NULL COMMENT '玩家ID',
  `order_name` varchar(50) DEFAULT NULL,
  `order_num` varchar(64) DEFAULT NULL,
  `order_amount` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '投资金额',
  `order_state` varchar(60) DEFAULT NULL COMMENT '状态(SUBSCRIBE预约，SUBSCRIBED已预约,MANAGEMENT经营中,EXTRACT可提取,FINISHED已完成,CANCEL取消,SUBSCRIBE_VERIFY_FAIL预约审核不通过，INVALID作废)',
  `order_repeat` tinyint(4) unsigned DEFAULT '0' COMMENT '是否复投（0否，1是）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  KEY `index_order_invest_id` (`order_invest_id`),
  KEY `index_order_payer_id` (`order_payer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单（投资记录）';

-- ----------------------------
-- Records of invest_order
-- ----------------------------
INSERT INTO `invest_order` VALUES ('21', '3', 'D6EDA06FDF654A46BC8299A05DDFF591', null, null, '5.0000', 'SUBSCRIBE', '0', '2019-10-15 12:28:49', '2019-10-15 12:28:49');
INSERT INTO `invest_order` VALUES ('22', '1', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 15:16:45', '2019-10-16 15:16:45');
INSERT INTO `invest_order` VALUES ('23', '1', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 15:19:09', '2019-10-16 15:19:09');
INSERT INTO `invest_order` VALUES ('24', '1', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 15:23:19', '2019-10-16 15:23:19');
INSERT INTO `invest_order` VALUES ('25', '1', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 15:24:09', '2019-10-16 15:24:09');
INSERT INTO `invest_order` VALUES ('26', '1', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 15:28:51', '2019-10-16 15:28:51');
INSERT INTO `invest_order` VALUES ('27', '1', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 16:09:22', '2019-10-16 16:09:22');
INSERT INTO `invest_order` VALUES ('28', '2', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 16:28:47', '2019-10-16 16:28:47');
INSERT INTO `invest_order` VALUES ('29', '3', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 16:28:52', '2019-10-16 16:28:52');
INSERT INTO `invest_order` VALUES ('30', '1', '52ABA6CE89164C8484A7F7FFF16B3670', null, null, '0.0000', 'SUBSCRIBE', '0', '2019-10-16 17:06:51', '2019-10-16 17:06:51');

-- ----------------------------
-- Table structure for invest_rule
-- ----------------------------
DROP TABLE IF EXISTS `invest_rule`;
CREATE TABLE `invest_rule` (
  `rule_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rule_flag` varchar(255) DEFAULT NULL COMMENT '标识',
  `rule_opt_pre` varchar(255) DEFAULT NULL,
  `rule_opt` varchar(255) DEFAULT NULL COMMENT '操作',
  `rule_name` varchar(50) DEFAULT NULL COMMENT '规则名称',
  `rule_desc` varchar(200) DEFAULT NULL COMMENT '规则描述',
  `rule_item` int(11) unsigned DEFAULT NULL COMMENT '规则项目',
  `rule_rate_pre` tinyint(4) unsigned DEFAULT '0',
  `rule_rate` decimal(20,4) unsigned DEFAULT '0.0000',
  `rule_level` decimal(20,4) unsigned DEFAULT NULL COMMENT '规则优先级别',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`rule_id`) USING BTREE,
  KEY `index_rule_item` (`rule_item`) USING BTREE,
  KEY `index_rule_name` (`rule_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='项目规则表';

-- ----------------------------
-- Records of invest_rule
-- ----------------------------
INSERT INTO `invest_rule` VALUES ('1', 'DIRECT_DIS', null, 'OPT_RATE', '直推印记分成', null, '1', null, '0.3500', '1.0000', null, null);
INSERT INTO `invest_rule` VALUES ('2', 'INDIRECT_DIS', null, 'OPT_RATE', '间推印记分成', null, '1', null, '0.0500', '2.0000', null, null);
INSERT INTO `invest_rule` VALUES ('3', null, null, null, '小摊预约权重', null, '2', null, '0.2000', '1.0000', null, null);
INSERT INTO `invest_rule` VALUES ('4', null, null, null, '大厦投资', null, '3', null, '0.3000', '1.0000', null, null);
INSERT INTO `invest_rule` VALUES ('5', 'ALL_ORDERS', 'OPT_RATE', 'OPT_RATE', '所有玩家', '订单所有玩家', '4', '1', '0.2000', '1.0000', null, null);
INSERT INTO `invest_rule` VALUES ('6', 'TOP_MEMBERS', 'OPT_TOP', 'OPT_RATE', '会员最多', '取所有会员数量', '4', '20', '0.3000', '2.0000', null, null);
INSERT INTO `invest_rule` VALUES ('7', 'FIRST_TIME', 'OPT_RATE', 'OPT_RATE', '第一次投资', '投资时间与计算当天时间一样', '4', '0', '0.5000', '3.0000', null, null);
INSERT INTO `invest_rule` VALUES ('8', 'LIKES_GATHER', 'OPT_RATE', 'OPT_RATE', '获得点赞', '获得数量最多的', '4', '0', '0.0000', '4.0000', null, null);
INSERT INTO `invest_rule` VALUES ('9', 'INVEST_LONG', 'OPT_TOP', 'OPT_RATE', '投资时长', '第一次投资时间算起', '4', '0', '0.0000', '5.0000', null, null);
INSERT INTO `invest_rule` VALUES ('10', 'ORDER_OTHERS', 'OPT_RATE', 'OPT_RATE', '其他', '其他剩余的订单', '4', '0', '0.0000', '6.0000', null, null);
INSERT INTO `invest_rule` VALUES ('11', 'LEVEL_ONE_START', 'OPT_RATE', 'OPT_NUM', '一星', '一级商会', '5', '1', '3.0000', '1.0000', null, null);
INSERT INTO `invest_rule` VALUES ('12', 'LEVEL_TWO_START', 'OPT_RATE', 'OPT_NUM', '二星', '二级商会', '5', '1', '9.0000', '2.0000', null, null);
INSERT INTO `invest_rule` VALUES ('13', 'LEVEL_THREE_START', 'OPT_RATE', 'OPT_NUM', '三星', '三级商会', '5', '1', '27.0000', '3.0000', null, null);
INSERT INTO `invest_rule` VALUES ('14', 'LEVEL_FOUR_START', 'OPT_RATE', 'OPT_NUM', '四星', '四级商会', '5', '1', '81.0000', '4.0000', null, null);
INSERT INTO `invest_rule` VALUES ('15', 'LEVEL_FIVE_START', 'OPT_RATE', 'OPT_NUM', '五星', '五级商会', '5', '1', '405.0000', '5.0000', null, null);
INSERT INTO `invest_rule` VALUES ('16', 'LEVEL_SIX_START', 'OPT_RATE', 'OPT_NUM', '六星', '六级商会', '5', '1', '2025.0000', '6.0000', null, null);
INSERT INTO `invest_rule` VALUES ('17', 'LEVEL_SEVEN_START', 'OPT_RATE', 'OPT_NUM', '七星', '七级商会', '5', '1', '10125.0000', '7.0000', null, null);
INSERT INTO `invest_rule` VALUES ('18', 'LEVEL_EIGHT_START', 'OPT_RATE', 'OPT_NUM', '八星', '八商级会', '5', '1', '101250.0000', '8.0000', null, null);
INSERT INTO `invest_rule` VALUES ('19', 'LEVEL_NINE_START', 'OPT_RATE', 'OPT_NUM', '九星', '九级商会', '5', '1', '1012500.0000', '9.0000', null, null);
INSERT INTO `invest_rule` VALUES ('20', 'ALL_ORDERS', 'OPT_RATE', 'OPT_RATE', '所有玩家', '订单所有玩家', '6', '1', '0.4000', '1.0000', null, null);
INSERT INTO `invest_rule` VALUES ('21', 'TOP_MEMBERS', 'OPT_TOP', 'OPT_RATE', '会员最多', '取所有会员数量', '6', '20', '0.2000', '2.0000', null, null);
INSERT INTO `invest_rule` VALUES ('22', 'FIRST_TIME', 'OPT_RATE', 'OPT_RATE', '第一次投资', '投资时间与计算当天时间一样', '4', '0', '0.2000', '3.0000', null, null);
INSERT INTO `invest_rule` VALUES ('23', 'LIKES_GATHER', 'OPT_TOP', 'OPT_RATE', '获得点赞', '获得数量最多的', '6', '20', '0.1000', '4.0000', null, null);
INSERT INTO `invest_rule` VALUES ('24', 'INVEST_LONG', 'OPT_TOP', 'OPT_RATE', '投资时长', '第一次投资时间算起', '6', '10', '0.1000', '5.0000', null, null);
INSERT INTO `invest_rule` VALUES ('25', 'ORDER_OTHERS', 'OPT_RATE', 'OPT_RATE', '其他', '其他剩余的订单', '6', '0', '0.0000', '6.0000', null, null);
INSERT INTO `invest_rule` VALUES ('26', 'SALES_OVERTIME', 'OPT_NUM', 'OPT_NUM', '超时时间', '超时的时间和单位时间次数', '7', '1', '3.0000', '1.0000', null, null);

-- ----------------------------
-- Table structure for likes_log
-- ----------------------------
DROP TABLE IF EXISTS `likes_log`;
CREATE TABLE `likes_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `like_id` int(11) unsigned DEFAULT NULL,
  `like_player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '点赞玩家ID',
  `like_liked_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '收获点赞玩家ID',
  `like_invest_id` int(11) unsigned DEFAULT NULL COMMENT '点赞投资ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞记录表';

-- ----------------------------
-- Records of likes_log
-- ----------------------------

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
  `acc_player_id` varchar(64) DEFAULT NULL COMMENT '账户玩家',
  `acc_addr` varchar(255) DEFAULT NULL COMMENT '账户地址',
  `acc_pass` varchar(255) DEFAULT NULL COMMENT '账户密码',
  `acc_usdt` decimal(20,4) DEFAULT '0.0000' COMMENT '账户usdt额度',
  `acc_usdt_available` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT 'usdt可用金额',
  `acc_usdt_freeze` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT 'usdt冻结金额',
  `acc_mt` decimal(20,4) DEFAULT '0.0000' COMMENT '账户mt额度',
  `acc_mt_available` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT 'mt可用金额',
  `acc_mt_freeze` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT 'mt冻结金额',
  `acc_income` decimal(20,4) DEFAULT '0.0000',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`acc_id`) USING BTREE,
  KEY `index_acc_player_id` (`acc_player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=888888890 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家账户表';

-- ----------------------------
-- Records of player_account
-- ----------------------------
INSERT INTO `player_account` VALUES ('32', 'FCCDC7A84EBD47BCB63F4B1281BE527D', '8539D6814E364B1B834EFBC93DB17330', null, '93.0000', '83.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('33', 'B22BD7C3B9374473AB7133C3A4271234', '65AB2C93C5994934A7DA30A76F4D6B50', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('34', '52ABA6CE89164C8484A7F7FFF16B3670', 'B424A91EF8004558B34354CA440F75B7', null, '63.0000', '63.0000', '30.0000', '62.9800', '999999.9800', '100000.0200', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('35', 'A07246C2924A415982ABE5E8C6DAD53D', '3574E5E05A32430C8731D640C5FA2E4F', null, '64420.0000', '9420.0000', '14580.0000', '64419.7500', '170008.6200', '10000.3800', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('36', 'D6EDA06FDF654A46BC8299A05DDFF591', '128ECC1E2A22412C9757C19710625714', null, '9994.0000', '8994.0000', '550000.0000', '9944.0000', '9999.0000', '1000.0000', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('37', '1301094B88274AF78C26D532F0C9E6E3', 'FD3854472D1845E2B824245303403502', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('38', '68493901879941308DFF85CB8EA3A077', '9D11A42595FC48B6AC4F05983EAA663C', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('39', 'F3ECC5684F8C44339314ADF7768EB63B', 'D799B2D185DD4774996FBA82229077C0', null, '100.0000', '90.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', null, '4.0000', '3.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, null);
INSERT INTO `player_account` VALUES ('888888889', 'B62A7E3C3259429A92130B2196F6A39A', '2559340F3754455B87B36D82C0776601', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', null, null);

-- ----------------------------
-- Table structure for player_account_log
-- ----------------------------
DROP TABLE IF EXISTS `player_account_log`;
CREATE TABLE `player_account_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `acc_id` int(11) NOT NULL COMMENT '交易ID号',
  `player_id` varchar(64) DEFAULT NULL COMMENT '玩家',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `amount_mt` decimal(20,4) DEFAULT '0.0000',
  `account_usdt` decimal(20,4) DEFAULT '0.0000',
  `type` varchar(50) DEFAULT NULL COMMENT '1入账2出账',
  `desc` varchar(255) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of player_account_log
-- ----------------------------
INSERT INTO `player_account_log` VALUES ('1', '35', 'A07246C2924A415982ABE5E8C6DAD53D', '3574E5E05A32430C8731D640C5FA2E4F', '1', '0', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('2', '0', 'D6EDA06FDF654A46BC8299A05DDFF591', '128ECC1E2A22412C9757C19710625714', '0', '5', '1', '收入账户多余的额度', '2019-10-15 10:44:50');
INSERT INTO `player_account_log` VALUES ('3', '0', 'D6EDA06FDF654A46BC8299A05DDFF591', '128ECC1E2A22412C9757C19710625714', '0', '5', '1', '收入账户多余的额度', '2019-10-15 10:45:11');
INSERT INTO `player_account_log` VALUES ('4', '0', 'D6EDA06FDF654A46BC8299A05DDFF591', '128ECC1E2A22412C9757C19710625714', '0', '5', '1', '收入账户多余的额度', '2019-10-15 10:46:58');
INSERT INTO `player_account_log` VALUES ('5', '0', 'D6EDA06FDF654A46BC8299A05DDFF591', '128ECC1E2A22412C9757C19710625714', '0', '5', '1', '收入账户多余的额度', '2019-10-15 10:48:23');

-- ----------------------------
-- Table structure for player_earning
-- ----------------------------
DROP TABLE IF EXISTS `player_earning`;
CREATE TABLE `player_earning` (
  `earn_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `earn_invest_id` int(11) DEFAULT NULL COMMENT '投资项目ID',
  `earn_player_id` varchar(64) DEFAULT NULL COMMENT '玩家ID',
  `earn_max` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '最大提取额度（预计最大收益）',
  `earn_current` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '当前获得额度',
  `earn_personal_tax` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '个人税金',
  `earn_enterprise_tax` decimal(20,4) DEFAULT '0.0000' COMMENT '企业税金',
  `earn_quota_tax` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '定额税',
  `is_withdrew` tinyint(4) DEFAULT '0' COMMENT '是否可以提取(0新增,1，收益中，2可提取，3已提取)',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`earn_id`) USING BTREE,
  KEY `index_earn_player_id` (`earn_player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家收益';

-- ----------------------------
-- Records of player_earning
-- ----------------------------
INSERT INTO `player_earning` VALUES ('1', '3', 'D6EDA06FDF654A46BC8299A05DDFF591', '5.0000', '5.0000', '0.0100', '0.0200', '0.0000', '1', '2019-10-08 17:53:51', '2019-10-15 18:48:22');

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
  UNIQUE KEY `index_player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户扩展表（玩家）';

-- ----------------------------
-- Records of player_ext
-- ----------------------------
INSERT INTO `player_ext` VALUES ('55', '956540BCA48549589B70DFF89B715F81', null, null, null, null);
INSERT INTO `player_ext` VALUES ('56', 'FCCDC7A84EBD47BCB63F4B1281BE527D', null, null, null, null);
INSERT INTO `player_ext` VALUES ('57', 'B22BD7C3B9374473AB7133C3A4271234', null, null, null, null);
INSERT INTO `player_ext` VALUES ('58', '52ABA6CE89164C8484A7F7FFF16B3670', null, null, null, null);
INSERT INTO `player_ext` VALUES ('59', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, null, null);
INSERT INTO `player_ext` VALUES ('60', 'D6EDA06FDF654A46BC8299A05DDFF591', null, null, null, null);
INSERT INTO `player_ext` VALUES ('61', '1301094B88274AF78C26D532F0C9E6E3', null, null, null, null);
INSERT INTO `player_ext` VALUES ('62', '68493901879941308DFF85CB8EA3A077', null, null, null, null);
INSERT INTO `player_ext` VALUES ('63', 'F3ECC5684F8C44339314ADF7768EB63B', null, null, null, null);
INSERT INTO `player_ext` VALUES ('64', '7C4E0329EC64423093D84281EB8A26C3', null, null, null, null);
INSERT INTO `player_ext` VALUES ('65', 'DA744E2BD88343C8B2BF7750FC7E501B', null, null, null, null);
INSERT INTO `player_ext` VALUES ('66', 'B62A7E3C3259429A92130B2196F6A39A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('67', '3A309D33667D4B45AD30FC318F255E61', null, null, null, null);
INSERT INTO `player_ext` VALUES ('68', '4937E1605DF84FB4A17C31DE22BF6782', null, null, null, null);
INSERT INTO `player_ext` VALUES ('69', 'BFA938AFDD784CFCA4850D7330A5407F', null, null, null, null);
INSERT INTO `player_ext` VALUES ('70', '7D09619AC1054CF1B8380F6ED098F797', null, null, null, null);

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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_player_id` (`player_id`) USING BTREE,
  KEY `index_friend_id` (`friend_id`) USING BTREE,
  KEY `index_invite` (`invite`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='好友';

-- ----------------------------
-- Records of player_friends
-- ----------------------------
INSERT INTO `player_friends` VALUES ('10', 'FCCDC7A84EBD47BCB63F4B1281BE527D', '8A2922A66F474A0DA9B10FB4BCD59BA0', '0', 'system', '1', '2019-10-11 11:05:32', '2019-10-11 11:05:32');
INSERT INTO `player_friends` VALUES ('11', 'B22BD7C3B9374473AB7133C3A4271234', '8A2922A66F474A0DA9B10FB4BCD59BA0', '0', 'system', '1', '2019-10-11 11:06:42', '2019-10-11 11:06:42');
INSERT INTO `player_friends` VALUES ('12', '52ABA6CE89164C8484A7F7FFF16B3670', 'FCCDC7A84EBD47BCB63F4B1281BE527D', '0', '4b057c', '1', '2019-10-11 11:07:13', '2019-10-11 11:07:13');
INSERT INTO `player_friends` VALUES ('13', 'A07246C2924A415982ABE5E8C6DAD53D', '52ABA6CE89164C8484A7F7FFF16B3670', '0', '7841fb', '1', '2019-10-11 11:07:57', '2019-10-11 11:07:57');
INSERT INTO `player_friends` VALUES ('14', 'D6EDA06FDF654A46BC8299A05DDFF591', 'A07246C2924A415982ABE5E8C6DAD53D', '0', 'c73572', '1', '2019-10-11 11:08:14', '2019-10-11 11:08:14');

-- ----------------------------
-- Table structure for player_game_setting
-- ----------------------------
DROP TABLE IF EXISTS `player_game_setting`;
CREATE TABLE `player_game_setting` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游戏设置';

-- ----------------------------
-- Records of player_game_setting
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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_player_id` (`player_id`) USING BTREE,
  KEY `index_grade` (`grade`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='玩家等级(会员/商会等级)';

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
  `liked_get_total` int(11) unsigned NOT NULL DEFAULT '0',
  `liked_set_total` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '项目点赞数量',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`liked_id`) USING BTREE,
  KEY `index_liked_player_id` (`liked_player_id`) USING BTREE,
  KEY `index_liked_invest_id` (`liked_invest_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='玩家获赞表';

-- ----------------------------
-- Records of player_likes
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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_player_id` (`player_id`) USING BTREE,
  KEY `index_imei` (`imei`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=704 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登录日志';

-- ----------------------------
-- Records of player_login_log
-- ----------------------------
INSERT INTO `player_login_log` VALUES ('668', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 15:37:28');
INSERT INTO `player_login_log` VALUES ('669', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:06:06');
INSERT INTO `player_login_log` VALUES ('670', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:10:21');
INSERT INTO `player_login_log` VALUES ('671', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-16 16:44:52');
INSERT INTO `player_login_log` VALUES ('672', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:47:36');
INSERT INTO `player_login_log` VALUES ('673', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:47:56');
INSERT INTO `player_login_log` VALUES ('674', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:47:57');
INSERT INTO `player_login_log` VALUES ('675', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:48:19');
INSERT INTO `player_login_log` VALUES ('676', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:49:29');
INSERT INTO `player_login_log` VALUES ('677', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:49:31');
INSERT INTO `player_login_log` VALUES ('678', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:49:31');
INSERT INTO `player_login_log` VALUES ('679', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:49:31');
INSERT INTO `player_login_log` VALUES ('680', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:49:32');
INSERT INTO `player_login_log` VALUES ('681', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:49:41');
INSERT INTO `player_login_log` VALUES ('682', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:49:41');
INSERT INTO `player_login_log` VALUES ('683', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:49:42');
INSERT INTO `player_login_log` VALUES ('684', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:24');
INSERT INTO `player_login_log` VALUES ('685', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:27');
INSERT INTO `player_login_log` VALUES ('686', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:27');
INSERT INTO `player_login_log` VALUES ('687', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:28');
INSERT INTO `player_login_log` VALUES ('688', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:28');
INSERT INTO `player_login_log` VALUES ('689', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:29');
INSERT INTO `player_login_log` VALUES ('690', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:29');
INSERT INTO `player_login_log` VALUES ('691', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:29');
INSERT INTO `player_login_log` VALUES ('692', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:29');
INSERT INTO `player_login_log` VALUES ('693', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:29');
INSERT INTO `player_login_log` VALUES ('694', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 16:50:29');
INSERT INTO `player_login_log` VALUES ('695', '52ABA6CE89164C8484A7F7FFF16B3670', null, null, 'login', null, '2019-10-16 16:51:07');
INSERT INTO `player_login_log` VALUES ('696', '52ABA6CE89164C8484A7F7FFF16B3670', null, null, 'quit', null, '2019-10-16 16:56:33');
INSERT INTO `player_login_log` VALUES ('697', '52ABA6CE89164C8484A7F7FFF16B3670', null, null, 'login', null, '2019-10-16 16:57:22');
INSERT INTO `player_login_log` VALUES ('698', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 17:14:09');
INSERT INTO `player_login_log` VALUES ('699', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 17:14:17');
INSERT INTO `player_login_log` VALUES ('700', '1301094B88274AF78C26D532F0C9E6E3', null, null, 'login', null, '2019-10-16 17:15:23');
INSERT INTO `player_login_log` VALUES ('701', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 17:17:03');
INSERT INTO `player_login_log` VALUES ('702', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 17:17:08');
INSERT INTO `player_login_log` VALUES ('703', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-16 17:18:21');

-- ----------------------------
-- Table structure for player_trade
-- ----------------------------
DROP TABLE IF EXISTS `player_trade`;
CREATE TABLE `player_trade` (
  `trade_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `trade_acc_id` int(10) unsigned DEFAULT NULL COMMENT '账户id',
  `trade_player_id` varchar(64) DEFAULT NULL COMMENT '交易人id',
  `trade_order_id` int(11) unsigned DEFAULT NULL COMMENT '订单id',
  `trade_amount` decimal(20,4) DEFAULT '0.0000' COMMENT '交易金额',
  `personal_tax` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '个人所得税',
  `enterprise_tax` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '企业所得税',
  `quota_tax` decimal(20,4) unsigned DEFAULT '0.0000' COMMENT '定额税',
  `trade_status` varchar(30) DEFAULT NULL COMMENT '交易状态(FREEZE冻结,OUT已出账,IN已入账)',
  `in_out` varchar(10) DEFAULT NULL COMMENT '入账还是出账(入账in,出账out)',
  `trade_type` varchar(30) DEFAULT NULL COMMENT '交易类型（充值:recharge,转账:transfer,提现:withdraw,购买mt:buy_mt,投资invest,投资收益:invest_earnings)',
  `trade_desc` varchar(100) DEFAULT NULL COMMENT '交易描述',
  `create_time` datetime DEFAULT NULL COMMENT '交易时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`trade_id`),
  KEY `index_trade_player_id` (`trade_player_id`),
  KEY `index_trade_order_id` (`trade_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='交易记录表';

-- ----------------------------
-- Records of player_trade
-- ----------------------------
INSERT INTO `player_trade` VALUES ('1', null, 'D6EDA06FDF654A46BC8299A05DDFF591', '15', '5.0000', '0.0200', '0.0100', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-15 12:14:28', '2019-10-15 12:14:28');
INSERT INTO `player_trade` VALUES ('2', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '16', '5.0000', '0.0200', '0.0100', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-15 12:17:19', '2019-10-15 12:17:19');
INSERT INTO `player_trade` VALUES ('3', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '16', '5.0000', '0.0200', '0.0100', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-15 12:17:19', '2019-10-15 12:17:19');
INSERT INTO `player_trade` VALUES ('4', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '17', '5.0000', '0.0200', '0.0100', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-15 12:18:26', '2019-10-15 12:18:26');
INSERT INTO `player_trade` VALUES ('5', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '18', '5.0000', '0.0200', '0.0100', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-15 12:21:07', '2019-10-15 12:21:07');
INSERT INTO `player_trade` VALUES ('6', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '19', '5.0000', '0.0200', '0.0100', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-15 12:23:40', '2019-10-15 12:23:40');
INSERT INTO `player_trade` VALUES ('7', '35', 'A07246C2924A415982ABE5E8C6DAD53D', '20', '5.0000', '0.0200', '0.0100', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-15 12:25:22', '2019-10-15 12:25:22');
INSERT INTO `player_trade` VALUES ('8', '35', 'A07246C2924A415982ABE5E8C6DAD53D', '21', '5.0000', '0.0200', '0.0100', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-15 12:28:49', '2019-10-15 12:28:49');
INSERT INTO `player_trade` VALUES ('9', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '21', '4.9700', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-15 18:41:57', '2019-10-15 18:41:57');
INSERT INTO `player_trade` VALUES ('10', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '21', '4.9700', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-15 18:42:11', '2019-10-15 18:42:11');
INSERT INTO `player_trade` VALUES ('11', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '21', '4.9700', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-15 18:44:46', '2019-10-15 18:44:46');
INSERT INTO `player_trade` VALUES ('12', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '21', '4.9700', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-15 18:44:49', '2019-10-15 18:44:49');
INSERT INTO `player_trade` VALUES ('13', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '21', '4.9700', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-15 18:46:58', '2019-10-15 18:46:58');
INSERT INTO `player_trade` VALUES ('14', '36', 'D6EDA06FDF654A46BC8299A05DDFF591', '21', '4.9700', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-15 18:48:22', '2019-10-15 18:48:22');
INSERT INTO `player_trade` VALUES ('15', '35', 'A07246C2924A415982ABE5E8C6DAD53D', '26', '30.0000', '0.0200', '0.0000', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-16 15:29:18', '2019-10-16 15:29:18');
INSERT INTO `player_trade` VALUES ('16', '35', 'A07246C2924A415982ABE5E8C6DAD53D', '27', '30.0000', '0.0200', '0.0000', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-16 16:09:24', '2019-10-16 16:09:24');
INSERT INTO `player_trade` VALUES ('17', '35', 'A07246C2924A415982ABE5E8C6DAD53D', '28', '100.0000', '0.0100', '0.0000', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-16 16:28:47', '2019-10-16 16:28:47');
INSERT INTO `player_trade` VALUES ('18', '35', 'A07246C2924A415982ABE5E8C6DAD53D', '29', '300.0000', '0.1000', '0.1500', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-16 16:28:52', '2019-10-16 16:28:52');
INSERT INTO `player_trade` VALUES ('19', '34', '52ABA6CE89164C8484A7F7FFF16B3670', '30', '30.0000', '0.0200', '0.0000', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-16 17:06:51', '2019-10-16 17:06:51');

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
  `item_flag` varchar(50) DEFAULT NULL COMMENT '规则标识',
  `item_name` varchar(50) NOT NULL COMMENT '规则项目名称',
  `item_desc` varchar(100) DEFAULT NULL COMMENT '规则项目描述',
  `item_state` tinyint(4) DEFAULT NULL COMMENT '可用状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE,
  KEY `index_item_name` (`item_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='规则表';

-- ----------------------------
-- Records of rule_item
-- ----------------------------
INSERT INTO `rule_item` VALUES ('1', 'STAMP_SIGN', '社交印记', '直推间推印记分成', '1', null, null);
INSERT INTO `rule_item` VALUES ('2', 'PROFIT_CACL', '收益计算', '计算收益分成', '1', null, null);
INSERT INTO `rule_item` VALUES ('4', 'INVEST_ORDER', '投资订单', '计算投资预约成功', '1', null, null);
INSERT INTO `rule_item` VALUES ('5', 'PlAYER_LEVEL', '商会等级', '计算玩家商会等级', '1', null, null);
INSERT INTO `rule_item` VALUES ('6', 'PROFIT_GRANT', '利益分配', '计算收益分配', '1', null, null);
INSERT INTO `rule_item` VALUES ('7', 'SALES_OVERTIME', '订单超时', '订单超时未处理', '1', null, null);

-- ----------------------------
-- Table structure for sales_order
-- ----------------------------
DROP TABLE IF EXISTS `sales_order`;
CREATE TABLE `sales_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) DEFAULT NULL COMMENT '订单ID',
  `order_amount` decimal(20,0) DEFAULT NULL COMMENT '订单额度',
  `order_buy_type` varchar(50) DEFAULT NULL COMMENT '订单购买类型',
  `order_pay_type` varchar(50) DEFAULT NULL COMMENT '订单支付类型',
  `order_pay_amount` decimal(20,0) DEFAULT NULL COMMENT '支付额度',
  `order_player_buyer` varchar(64) DEFAULT NULL COMMENT '订单玩家',
  `order_player_seller` varchar(64) DEFAULT NULL COMMENT '订单卖家',
  `order_state` tinyint(10) DEFAULT NULL COMMENT '订单状态：0创建,1:待支付2完成支付，已通过3支付成功等待处理4超时5拒绝',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sales_order
-- ----------------------------
INSERT INTO `sales_order` VALUES ('77', '20191014181418', '1', 'MT', 'USDT', '1', 'A07246C2924A415982ABE5E8C6DAD53D', '52ABA6CE89164C8484A7F7FFF16B3670', '2', '2019-10-14 10:14:19', null);
INSERT INTO `sales_order` VALUES ('78', '20191014181418', '1', 'MT', 'USDT', '1', 'A07246C2924A415982ABE5E8C6DAD53D', '52ABA6CE89164C8484A7F7FFF16B3670', '2', '2019-10-14 10:14:19', null);

-- --
-- ----------------------------
-- Table structure for trade_detail
-- ----------------------------
DROP TABLE IF EXISTS `trade_detail`;
CREATE TABLE `trade_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `trade_id` int(11) unsigned DEFAULT NULL,
  `verify_id` int(11) unsigned DEFAULT NULL COMMENT '审核id',
  `order_id` int(11) unsigned DEFAULT NULL COMMENT '订单id',
  `player_id` varchar(64) DEFAULT NULL COMMENT '交易人',
  `trade_detail_type` varchar(50) DEFAULT NULL COMMENT '交易明细类型（充值:recharge,usdt投资收益:usdt_earnings,mt投资收益:mt_earnings,usdt投资税金:usdt_invest_tax,mt投资税金:mt_invest_tax,个人所得税:personal_tax,企业所得税:enterprise_tax,转账所得税:transfer_tax,转账冻结:transfer_freeze,提现冻结:withdraw_freeze,购买mt冻结:buy_mt_freeze,usdt投资冻结:usdt_invest_freeze,mt投资冻结:mt_inves_freeze,转账审核通过扣款:transfer_verify,提现审核通过扣款:withdraw_verify,usdt投资审核通过扣款:usdt_invest_verify',
  `trade_amount` decimal(20,4) DEFAULT '0.0000' COMMENT '交易金额',
  `usdt_surplus` decimal(20,4) DEFAULT '0.0000' COMMENT '余额',
  `mt_surplus` decimal(20,4) DEFAULT '0.0000' COMMENT '余额',
  `verify_time` datetime DEFAULT NULL COMMENT '交易审核时间',
  `descr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_trade_id` (`trade_id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_order_id` (`order_id`),
  KEY `index_verify_id` (`verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='交易流水表';

-- ----------------------------
-- Records of trade_detail
-- ----------------------------
INSERT INTO `trade_detail` VALUES ('1', '9', null, '21', 'D6EDA06FDF654A46BC8299A05DDFF591', 'USDT_EARNINGS', '4.9700', null, '投资提取,提取金额：5,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-15 18:42:04');
INSERT INTO `trade_detail` VALUES ('2', '10', null, '21', 'D6EDA06FDF654A46BC8299A05DDFF591', 'USDT_EARNINGS', '4.9700', null, '投资提取,提取金额：5,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-15 18:42:20');
INSERT INTO `trade_detail` VALUES ('3', '11', null, '21', 'D6EDA06FDF654A46BC8299A05DDFF591', 'USDT_EARNINGS', '4.9700', null, '投资提取,提取金额：5,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-15 18:44:49');
INSERT INTO `trade_detail` VALUES ('4', '12', null, '21', 'D6EDA06FDF654A46BC8299A05DDFF591', 'USDT_EARNINGS', '4.9700', null, '投资提取,提取金额：5,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-15 18:45:04');
INSERT INTO `trade_detail` VALUES ('5', '13', null, '21', 'D6EDA06FDF654A46BC8299A05DDFF591', 'USDT_EARNINGS', '4.9700', null, '投资提取,提取金额：5,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-15 18:46:58');
INSERT INTO `trade_detail` VALUES ('6', '14', null, '21', 'D6EDA06FDF654A46BC8299A05DDFF591', 'USDT_EARNINGS', '4.9700', null, '投资提取,提取金额：5,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-15 18:48:22');
INSERT INTO `trade_detail` VALUES ('7', '15', null, '26', 'A07246C2924A415982ABE5E8C6DAD53D', 'USDT_INVEST_FREEZE', '30.0000', null, null, '2019-10-16 15:29:18');
INSERT INTO `trade_detail` VALUES ('8', '15', null, '26', 'A07246C2924A415982ABE5E8C6DAD53D', 'MT_INVEST_PERSONAL_TAX', '0.0200', null, null, '2019-10-16 15:29:18');
INSERT INTO `trade_detail` VALUES ('9', '16', null, '27', 'A07246C2924A415982ABE5E8C6DAD53D', 'USDT_INVEST_FREEZE', '30.0000', null, null, '2019-10-16 16:09:24');
INSERT INTO `trade_detail` VALUES ('10', '16', null, '27', 'A07246C2924A415982ABE5E8C6DAD53D', 'MT_INVEST_PERSONAL_TAX', '0.0200', null, null, '2019-10-16 16:09:24');
INSERT INTO `trade_detail` VALUES ('11', '17', null, '28', 'A07246C2924A415982ABE5E8C6DAD53D', 'USDT_INVEST_FREEZE', '100.0000', null, null, '2019-10-16 16:28:48');
INSERT INTO `trade_detail` VALUES ('12', '17', null, '28', 'A07246C2924A415982ABE5E8C6DAD53D', 'MT_INVEST_PERSONAL_TAX', '0.0100', null, null, '2019-10-16 16:28:48');
INSERT INTO `trade_detail` VALUES ('13', '18', null, '29', 'A07246C2924A415982ABE5E8C6DAD53D', 'USDT_INVEST_FREEZE', '300.0000', null, null, '2019-10-16 16:28:52');
INSERT INTO `trade_detail` VALUES ('14', '18', null, '29', 'A07246C2924A415982ABE5E8C6DAD53D', 'MT_INVEST_PERSONAL_TAX', '0.1000', null, null, '2019-10-16 16:28:53');
INSERT INTO `trade_detail` VALUES ('15', '18', null, '29', 'A07246C2924A415982ABE5E8C6DAD53D', 'MT_INVEST_ENTERPRISE_TAX', '0.1500', null, null, '2019-10-16 16:28:53');
INSERT INTO `trade_detail` VALUES ('16', '19', null, '30', '52ABA6CE89164C8484A7F7FFF16B3670', 'USDT_INVEST_FREEZE', '30.0000', null, null, '2019-10-16 17:06:51');
INSERT INTO `trade_detail` VALUES ('17', '19', null, '30', '52ABA6CE89164C8484A7F7FFF16B3670', 'MT_INVEST_PERSONAL_TAX', '0.0200', null, null, '2019-10-16 17:06:51');

-- ----------------------------
-- Table structure for trade_verify
-- ----------------------------
DROP TABLE IF EXISTS `trade_verify`;
CREATE TABLE `trade_verify` (
  `verify_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `verify_user_id` int(11) unsigned DEFAULT NULL COMMENT '审核人id(员工表)',
  `verify_trade_id` int(11) unsigned DEFAULT NULL COMMENT '交易id(交易记录表)',
  `verify_order_id` int(11) unsigned DEFAULT NULL COMMENT '订单id',
  `verify_status` varchar(50) DEFAULT NULL COMMENT '审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)',
  `verify_desc` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='交易审核表';

-- ----------------------------
-- Records of trade_verify
-- ----------------------------
INSERT INTO `trade_verify` VALUES ('6', null, '1', '15', 'WAIT', '待审核', '2019-10-15 12:14:28', '2019-10-15 12:14:28');
INSERT INTO `trade_verify` VALUES ('7', null, '4', '17', 'WAIT', '待审核', '2019-10-15 12:18:53', '2019-10-15 12:18:53');
INSERT INTO `trade_verify` VALUES ('8', null, '5', '18', 'WAIT', '待审核', '2019-10-15 12:21:07', '2019-10-15 12:21:07');
INSERT INTO `trade_verify` VALUES ('9', null, '6', '19', 'WAIT', '待审核', '2019-10-15 12:23:40', '2019-10-15 12:23:40');
INSERT INTO `trade_verify` VALUES ('10', null, '7', '20', 'WAIT', '待审核', '2019-10-15 12:25:22', '2019-10-15 12:25:22');
INSERT INTO `trade_verify` VALUES ('11', null, '8', '21', 'WAIT', '待审核', '2019-10-15 12:28:49', '2019-10-15 12:28:49');
INSERT INTO `trade_verify` VALUES ('12', null, '15', '26', 'WAIT', '待审核', '2019-10-16 15:29:18', '2019-10-16 15:29:18');
INSERT INTO `trade_verify` VALUES ('13', null, '16', '27', 'WAIT', '待审核', '2019-10-16 16:09:25', '2019-10-16 16:09:25');
INSERT INTO `trade_verify` VALUES ('14', null, '17', '28', 'WAIT', '待审核', '2019-10-16 16:28:48', '2019-10-16 16:28:48');
INSERT INTO `trade_verify` VALUES ('15', null, '18', '29', 'WAIT', '待审核', '2019-10-16 16:28:53', '2019-10-16 16:28:53');
INSERT INTO `trade_verify` VALUES ('16', null, '19', '30', 'WAIT', '待审核', '2019-10-16 17:06:51', '2019-10-16 17:06:51');

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
