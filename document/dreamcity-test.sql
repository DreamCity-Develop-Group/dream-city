
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
) ENGINE=InnoDB AUTO_INCREMENT=431 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='认证码';


-- ----------------------------
-- Table structure for city_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `city_dictionary`;
CREATE TABLE `city_dictionary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dic_name` varchar(100) DEFAULT NULL,
  `dic_key` varchar(255) DEFAULT NULL,
  `dic_val` varchar(600) DEFAULT NULL,
  `is_valid` tinyint(4) unsigned DEFAULT '1',
  `dic_descr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='字典（配置项）';

-- ----------------------------
-- Records of city_dictionary
-- ----------------------------
INSERT INTO `city_dictionary` VALUES ('1', '平台账户id', 'platform.account.accIds', '4E2EE556055042AB80E3D164E51DDD1A', '1', '平台账户id，多个，都号隔开来自player_account表', null, null);
INSERT INTO `city_dictionary` VALUES ('2', '注册密码salt', 'register.password.salt', 'DREAM_CITY_890@#$%', '1', '注册密码salt', null, null);
INSERT INTO `city_dictionary` VALUES ('3', '提现税金', 'player.withdraw.mt.tax', '5', '1', '玩家提现税金', null, null);
INSERT INTO `city_dictionary` VALUES ('4', '转账税金', 'player.transfer.mt.tax', '5', '1', '玩家转账税金', null, null);
INSERT INTO `city_dictionary` VALUES ('5', '内部转账审核', 'player.inside.transfer.verify', 'false', '1', '玩家内部转账审核是否要审核，默认true要审核,false时不用审核', null, null);
INSERT INTO `city_dictionary` VALUES ('6', '玩家获取消息天数', 'player.msg.getlist.day', '90', '1', '向玩家展示几天以内的消息', null, null);
INSERT INTO `city_dictionary` VALUES ('7', '玩家获取通知天数', 'player.note.getlist.day', '90', '1', '向玩家展示几天以内的通知', null, null);
INSERT INTO `city_dictionary` VALUES ('8', '修改交易密码', 'player.change.tran.pwd.tax', '1', '1', '修改交易密码扣除税金', null, null);

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
INSERT INTO `city_invest` VALUES ('1', '小地摊', '11', '30.0000', '2019-09-18 17:56:20', '0000000000000000.1000', '0000000000000000.1000', '0.0000', '2', '2020-01-31 17:56:50', null, 'Y', null, null);
INSERT INTO `city_invest` VALUES ('2', '玩具摊', '21', '100.0000', '2019-10-01 21:04:25', '0000000000000000.2000', '0000000000000000.1000', '0.0000', '3', '2019-11-30 21:04:53', null, 'Y', null, null);
INSERT INTO `city_invest` VALUES ('3', '酒吧', '31', '300.0000', '2019-10-01 21:04:25', '0000000000000000.2500', '0000000000000000.1000', '0.0000', '3', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('4', '医药公司', '41', '500.0000', '2019-10-01 21:04:25', '0000000000000000.3000', '0000000000000000.1000', '0.0000', '4', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('5', '电影公司', '51', '1000.0000', '2019-10-01 21:04:25', '0000000000000000.3500', '0000000000000000.1000', '0.0000', '4', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('6', '汽车集团', '61', '3000.0000', '2019-10-01 21:04:25', '0000000000000000.2000', '0000000000000000.1000', '0.0000', '5', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('7', '投资集团', '71', '5000.0000', '2019-10-01 21:04:25', '0000000000000000.1000', '0000000000000000.1000', '0.0000', '5', '2019-11-30 21:04:53', null, 'N', null, null);

-- ----------------------------
-- Table structure for city_message
-- ----------------------------
DROP TABLE IF EXISTS `city_message`;
CREATE TABLE `city_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `friend_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `content` varchar(600) DEFAULT NULL COMMENT '消息内容',
  `have_read` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '0未读，1已读',
  `send_time` datetime DEFAULT NULL COMMENT '通知发送时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_friend_id` (`friend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for city_notice
-- ----------------------------
DROP TABLE IF EXISTS `city_notice`;
CREATE TABLE `city_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `notice_content` varchar(255) DEFAULT NULL COMMENT '内容',
  `notice_state` tinyint(4) DEFAULT NULL COMMENT '状态：1可用0不可用',
  `send_time` datetime DEFAULT NULL COMMENT '公告发送时间',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of city_notice
-- ----------------------------
INSERT INTO `city_notice` VALUES ('1', 'fgnf', 'advababavs', '1', '2019-10-02 14:17:02', null);
INSERT INTO `city_notice` VALUES ('3', 'gnfg', 'wwwwwwwwwwwwwwww', '1', '2019-10-02 14:17:02', '2019-10-02 14:17:02');

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
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表（玩家）';

-- ----------------------------
-- Records of city_player
-- ----------------------------
INSERT INTO `city_player` VALUES ('1', '8A2922A66F474A0DA9B10FB4BCD59BA0', 'system', 'system', 'system', 'system', 'system', '1', '1', null, null);
INSERT INTO `city_player` VALUES ('230', '80F84F191FC94F32B4706C50072A35D1', '13601111111', 'test1', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', '670f07', null, '1', '2019-10-22 16:35:29', '2019-10-22 16:36:52');
INSERT INTO `city_player` VALUES ('232', '1B86C28CDE9F4F548E6839C7C971FB2A', '13601111112', 'test2', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', '925818', '1', '1', '2019-10-22 17:35:00', '2019-10-22 17:37:28');
INSERT INTO `city_player` VALUES ('233', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', '13601111113', 'test3', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', '98ebb9', null, '1', '2019-10-22 17:50:02', '2019-10-22 17:54:18');
INSERT INTO `city_player` VALUES ('234', 'C4F15930CF2F40D9988BB0ABDC326A8B', '13601111114', 'test4', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', 'd6a8a5', null, '1', '2019-10-22 18:07:27', '2019-10-22 18:09:47');
INSERT INTO `city_player` VALUES ('235', 'CACACA7E9B444CF78481C0BC926BA31D', '13601111115', 'test5', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', 'a0d305', null, '1', '2019-10-22 18:12:05', '2019-10-22 18:18:10');
INSERT INTO `city_player` VALUES ('236', '1EBF6363110844E5824F41E8F19398EC', '13601111116', 'test6', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', '367b2c', null, '1', '2019-10-22 18:24:52', '2019-10-22 18:26:22');
INSERT INTO `city_player` VALUES ('237', '8FCFFCD881CF48B4969929BB50E21D18', '13601111117', 'test7', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', '2d74dc', null, '1', '2019-10-22 18:32:40', '2019-10-22 18:43:51');
INSERT INTO `city_player` VALUES ('238', '5814CCFBA45B44B8B54E9FF8AB6C1868', '13601111118', 'test8', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', 'ff411d', '2', '1', '2019-10-22 18:54:27', '2019-10-22 18:56:31');
INSERT INTO `city_player` VALUES ('239', 'A569659CB82640328790A9D9BD6E2717', '13601111119', 'test9', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', '31d905', null, '1', '2019-10-22 19:06:19', '2019-10-22 19:07:39');
INSERT INTO `city_player` VALUES ('240', '7407B84521A944E7BF3E1442033AB9A3', '13802222222', 'yyy', '8ed4548c667bf26b19aa03895379305e', 'e10adc3949ba59abbe56e057f20f883e', 'eca2a4', null, '1', '2019-10-22 19:15:18', '2019-10-22 19:44:19');
INSERT INTO `city_player` VALUES ('241', '5AE83C28C6ED49B787634445EBF4C17A', '13800001111', 'uit', '29ce248d5e14f65c2f29f0f51ac53611', 'e10adc3949ba59abbe56e057f20f883e', 'dc90ba', null, '1', '2019-10-22 19:55:58', '2019-10-22 20:00:48');
INSERT INTO `city_player` VALUES ('242', 'C5318ACD5C5F40259728F6D8FD15F986', '13601111101', 'js1', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, '3f9907', null, '1', '2019-10-22 20:00:42', null);
INSERT INTO `city_player` VALUES ('243', '2C000246F4FA4176A1A76E2E005C6C41', '13601111102', 'js2', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, '64e367', null, '1', '2019-10-22 20:00:56', null);
INSERT INTO `city_player` VALUES ('244', 'CF76591AA4244FD0A98DE79ACBDAD624', '13601111103', 'js3', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, 'd307fb', null, '1', '2019-10-22 20:01:04', null);
INSERT INTO `city_player` VALUES ('245', '3D6BAE6E8B254EB1B8B863F74DBDBEF7', '13601111104', 'js4', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, '7a3929', null, '1', '2019-10-22 20:01:12', null);
INSERT INTO `city_player` VALUES ('246', '5360EDCB785645379E45751033FB73EF', '13601111105', 'js5', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, '7dd948', null, '1', '2019-10-22 20:01:19', null);
INSERT INTO `city_player` VALUES ('247', 'F9B106323AD9423680B9809CA79945A5', '13601111106', 'js6', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, '9a8a3e', null, '1', '2019-10-22 20:01:26', null);
INSERT INTO `city_player` VALUES ('248', '11A78B25DBD041BFAAF4AD3FFA3BB675', '13601111107', 'js7', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, 'f7b6e5', null, '1', '2019-10-22 20:01:32', null);
INSERT INTO `city_player` VALUES ('249', 'C7BF64E927574C75B1CA995D585F960B', '13601111108', 'js8', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, '0b7e0f', null, '1', '2019-10-22 20:01:44', null);
INSERT INTO `city_player` VALUES ('250', 'C03D606AF9AC46649244C13263C31A4A', '13601111109', 'js9', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, 'f06ffc', null, '1', '2019-10-22 20:01:51', null);
INSERT INTO `city_player` VALUES ('251', '8203E5421FF8447689EF82C86DC079C8', '13811113333', 'ttt', '29ce248d5e14f65c2f29f0f51ac53611', 'e10adc3949ba59abbe56e057f20f883e', '45fb3b', null, '1', '2019-10-22 20:11:57', '2019-10-22 20:16:31');
INSERT INTO `city_player` VALUES ('252', 'E0854DEAE58642B1A793E5CEE0F65400', '13611112222', 'www', '29ce248d5e14f65c2f29f0f51ac53611', null, 'cc9b88', null, '1', '2019-10-22 20:27:44', null);
INSERT INTO `city_player` VALUES ('253', '657CA88D55804299887B4E12C56F532B', '13711112222', 'eee', '29ce248d5e14f65c2f29f0f51ac53611', null, 'd94413', null, '1', '2019-10-22 20:31:52', null);
INSERT INTO `city_player` VALUES ('254', 'CE4D392671E64B8DAC27BF04C3A34078', '18826444888', '18826444888', '96e79218965eb72c92a549dd5a330112', null, '289af8', null, '1', '2019-10-22 20:34:11', null);
INSERT INTO `city_player` VALUES ('255', '62ECA12E056448398064BD7399C1FD4F', '18826444889', '18826444889', '96e79218965eb72c92a549dd5a330112', null, 'fb7aa0', null, '1', '2019-10-22 20:34:49', null);
INSERT INTO `city_player` VALUES ('256', '69CAD7C04A024793A701D3CBE7FEB743', '18826444887', '18826444887', '96e79218965eb72c92a549dd5a330112', null, '5fcafe', null, '1', '2019-10-22 20:38:28', null);
INSERT INTO `city_player` VALUES ('257', '5AE6F48FDD414B058A77EF8BF44BFDB1', '18826444886', '18826444886', '96e79218965eb72c92a549dd5a330112', null, '0bff46', null, '1', '2019-10-22 20:49:53', null);
INSERT INTO `city_player` VALUES ('258', 'B3FD06C30BE1406C810CFC20AAEADC74', '18826444885', '18826444885', '96e79218965eb72c92a549dd5a330112', null, '2c976e', null, '1', '2019-10-22 20:54:31', null);
INSERT INTO `city_player` VALUES ('259', 'CFAABA7E56BF4C6CB7615F6783D24140', '18826444884', '18826444884', '96e79218965eb72c92a549dd5a330112', null, 'db12ca', null, '1', '2019-10-22 20:56:17', null);
INSERT INTO `city_player` VALUES ('260', 'B964E2A2E63A484EBA31B5DE341D1930', '18826444870', '18826444870', '96e79218965eb72c92a549dd5a330112', null, 'aef627', null, '1', '2019-10-22 21:02:11', null);
INSERT INTO `city_player` VALUES ('261', '2D32014D0E0C4060A34CD8FFB386C477', '18826444871', '18826444871', '96e79218965eb72c92a549dd5a330112', null, '9fa14b', null, '1', '2019-10-22 21:03:46', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='玩家等级(会员/商会等级)';


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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of city_tree
-- ----------------------------
INSERT INTO `city_tree` VALUES ('1', 'system', '8A2922A66F474A0DA9B10FB4BCD59BA0', 'system', '1', '1', '2019-10-11 11:26:38');
INSERT INTO `city_tree` VALUES ('103', '8A2922A66F474A0DA9B10FB4BCD59BA0', '80F84F191FC94F32B4706C50072A35D1', 'system/670f07', '0', '0', '2019-10-22 08:37:05');
INSERT INTO `city_tree` VALUES ('104', '80F84F191FC94F32B4706C50072A35D1', '1B86C28CDE9F4F548E6839C7C971FB2A', 'system/670f07/925818', '0', '1', '2019-10-22 09:35:51');
INSERT INTO `city_tree` VALUES ('105', '1B86C28CDE9F4F548E6839C7C971FB2A', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'system/670f07/925818/98ebb9', '0', '0', '2019-10-22 09:52:01');
INSERT INTO `city_tree` VALUES ('106', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'C4F15930CF2F40D9988BB0ABDC326A8B', 'system/670f07/925818/98ebb9/d6a8a5', '', '0', '2019-10-22 10:09:17');
INSERT INTO `city_tree` VALUES ('107', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CACACA7E9B444CF78481C0BC926BA31D', 'system/670f07/925818/98ebb9/a0d305', '', '0', '2019-10-22 10:13:35');
INSERT INTO `city_tree` VALUES ('108', 'C4F15930CF2F40D9988BB0ABDC326A8B', '1EBF6363110844E5824F41E8F19398EC', 'system/670f07/925818/98ebb9/d6a8a5/367b2c', '', '0', '2019-10-22 10:26:02');
INSERT INTO `city_tree` VALUES ('109', '1EBF6363110844E5824F41E8F19398EC', '8FCFFCD881CF48B4969929BB50E21D18', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/2d74dc', '', '0', '2019-10-22 10:43:44');
INSERT INTO `city_tree` VALUES ('110', '1EBF6363110844E5824F41E8F19398EC', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d', '', '2', '2019-10-22 10:55:45');
INSERT INTO `city_tree` VALUES ('111', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'A569659CB82640328790A9D9BD6E2717', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/31d905', '', '0', '2019-10-22 11:07:28');
INSERT INTO `city_tree` VALUES ('112', '8A2922A66F474A0DA9B10FB4BCD59BA0', '7407B84521A944E7BF3E1442033AB9A3', 'system/eca2a4', '', '0', '2019-10-22 11:41:18');
INSERT INTO `city_tree` VALUES ('113', '8A2922A66F474A0DA9B10FB4BCD59BA0', '5AE83C28C6ED49B787634445EBF4C17A', 'system/dc90ba', '', '0', '2019-10-22 12:00:20');
INSERT INTO `city_tree` VALUES ('114', '8A2922A66F474A0DA9B10FB4BCD59BA0', '8203E5421FF8447689EF82C86DC079C8', 'system/45fb3b', '', '0', '2019-10-22 12:16:13');
INSERT INTO `city_tree` VALUES ('115', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'E0854DEAE58642B1A793E5CEE0F65400', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/cc9b88', '', '0', '2019-10-22 12:27:45');
INSERT INTO `city_tree` VALUES ('116', '5814CCFBA45B44B8B54E9FF8AB6C1868', '657CA88D55804299887B4E12C56F532B', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/d94413', '', '0', '2019-10-22 12:32:18');
INSERT INTO `city_tree` VALUES ('117', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'CE4D392671E64B8DAC27BF04C3A34078', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/289af8', '', '0', '2019-10-22 12:34:21');
INSERT INTO `city_tree` VALUES ('118', '5814CCFBA45B44B8B54E9FF8AB6C1868', '62ECA12E056448398064BD7399C1FD4F', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/fb7aa0', '', '0', '2019-10-22 12:34:53');
INSERT INTO `city_tree` VALUES ('119', '5814CCFBA45B44B8B54E9FF8AB6C1868', '69CAD7C04A024793A701D3CBE7FEB743', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/5fcafe', '', '0', '2019-10-22 12:39:03');
INSERT INTO `city_tree` VALUES ('120', '5814CCFBA45B44B8B54E9FF8AB6C1868', '5AE6F48FDD414B058A77EF8BF44BFDB1', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/0bff46', '', '0', '2019-10-22 12:50:07');
INSERT INTO `city_tree` VALUES ('121', '5814CCFBA45B44B8B54E9FF8AB6C1868', '5AE6F48FDD414B058A77EF8BF44BFDB1', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/0bff46', '', '0', '2019-10-22 12:50:15');
INSERT INTO `city_tree` VALUES ('122', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'B3FD06C30BE1406C810CFC20AAEADC74', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/2c976e', '', '0', '2019-10-22 12:54:37');
INSERT INTO `city_tree` VALUES ('123', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'CFAABA7E56BF4C6CB7615F6783D24140', 'system/670f07/925818/98ebb9/d6a8a5/367b2c/ff411d/db12ca', '', '0', '2019-10-22 12:56:25');
INSERT INTO `city_tree` VALUES ('124', '1B86C28CDE9F4F548E6839C7C971FB2A', 'B964E2A2E63A484EBA31B5DE341D1930', 'system/670f07/925818/aef627', '', '0', '2019-10-22 13:02:25');
INSERT INTO `city_tree` VALUES ('125', '1B86C28CDE9F4F548E6839C7C971FB2A', '2D32014D0E0C4060A34CD8FFB386C477', 'system/670f07/925818/9fa14b', '', '0', '2019-10-22 13:03:59');

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
  `allowed` varchar(255) DEFAULT NULL COMMENT '0b表示新建，1表示完成',
  `amount` decimal(20,4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of invest_allow
-- ----------------------------
INSERT INTO `invest_allow` VALUES ('26', '80F84F191FC94F32B4706C50072A35D1', '1', '10.0000', '2019-10-22 08:37:50');
INSERT INTO `invest_allow` VALUES ('27', '1B86C28CDE9F4F548E6839C7C971FB2A', '1', '10.0000', '2019-10-22 09:37:28');
INSERT INTO `invest_allow` VALUES ('28', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', '1', '10.0000', '2019-10-22 09:54:18');
INSERT INTO `invest_allow` VALUES ('29', 'C4F15930CF2F40D9988BB0ABDC326A8B', '1', '10.0000', '2019-10-22 10:09:47');
INSERT INTO `invest_allow` VALUES ('30', 'CACACA7E9B444CF78481C0BC926BA31D', '1', '10.0000', '2019-10-22 10:18:10');
INSERT INTO `invest_allow` VALUES ('31', '1EBF6363110844E5824F41E8F19398EC', '1', '10.0000', '2019-10-22 10:26:23');
INSERT INTO `invest_allow` VALUES ('32', '8FCFFCD881CF48B4969929BB50E21D18', '1', '10.0000', '2019-10-22 10:43:52');
INSERT INTO `invest_allow` VALUES ('33', '5814CCFBA45B44B8B54E9FF8AB6C1868', '1', '10.0000', '2019-10-22 10:56:31');
INSERT INTO `invest_allow` VALUES ('34', 'A569659CB82640328790A9D9BD6E2717', '1', '10.0000', '2019-10-22 11:07:39');
INSERT INTO `invest_allow` VALUES ('35', '7407B84521A944E7BF3E1442033AB9A3', '1', '10.0000', '2019-10-22 11:44:19');
INSERT INTO `invest_allow` VALUES ('36', '5AE83C28C6ED49B787634445EBF4C17A', '1', '10.0000', '2019-10-22 12:00:49');
INSERT INTO `invest_allow` VALUES ('37', '8203E5421FF8447689EF82C86DC079C8', '1', '10.0000', '2019-10-22 12:16:31');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单（投资记录）';

-- ----------------------------
-- Records of invest_order
-- ----------------------------
INSERT INTO `invest_order` VALUES ('1', '1', 'A07246C2924A415982ABE5E8C6DAD53D', '小吃摊', 'A07246C2924A415982ABE5E8C6DAD888', '30.0000', 'MANAGEMENT', '0', '2019-10-15 12:28:49', '2019-10-18 10:00:00');
INSERT INTO `invest_order` VALUES ('2', '2', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', '玩具摊', 'E38598357A4B46D4B530785E1435ED45', '100.0000', 'MANAGEMENT', '0', '2019-10-17 19:55:34', '2019-10-18 09:18:50');
INSERT INTO `invest_order` VALUES ('3', '2', 'A07246C2924A415982ABE5E8C6DAD53D', '玩具摊', 'D58A8A76A5AB41F993DF5AC87CA55576', '100.0000', 'SUBSCRIBED', '0', '2019-10-18 16:03:53', '2019-10-18 16:03:53');

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
  `version` int(10) unsigned DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`acc_id`) USING BTREE,
  KEY `index_acc_player_id` (`acc_player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=888888978 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家账户表';

-- ----------------------------
-- Records of player_account
-- ----------------------------
INSERT INTO `player_account` VALUES ('888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', 'system', '101.5000', '101.5000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', null, '96639.5000', '96639.5000', '0.0000', '9830.0000', '9830.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', null, '3567.0000', '3567.0000', '0.0000', '9940.0000', '9940.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888949', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'B24425489BB4421EA0C89EFBFCD01840', null, '999.0000', '999.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888950', 'C4F15930CF2F40D9988BB0ABDC326A8B', '3012DB4C2C174EA3B7D3498BF201A03D', null, '995.0000', '995.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888951', 'CACACA7E9B444CF78481C0BC926BA31D', 'EFF22A8E81BB4A33AF67A685F0B471DC', null, '990.0000', '990.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888952', '1EBF6363110844E5824F41E8F19398EC', '59280677EA934A249D1C2967F8DFE321', null, '997.5000', '997.5000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888953', '8FCFFCD881CF48B4969929BB50E21D18', '3D923B7799C44583ABB9A07E82F23036', null, '990.0000', '990.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888954', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'B8D591355ABF44E5A88E899F90E48284', null, '993.5000', '993.5000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888955', 'A569659CB82640328790A9D9BD6E2717', '1046B7BADE3D46A9B93424012C3729F8', null, '990.0000', '990.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888956', '7407B84521A944E7BF3E1442033AB9A3', '6AD59EA3FCDD437C944F014454B9BC8A', null, '990.0000', '990.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888957', '5AE83C28C6ED49B787634445EBF4C17A', 'A524CA6EC7C046908551598D17BFBB41', null, '990.0000', '990.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888958', 'C5318ACD5C5F40259728F6D8FD15F986', '10D058690CE34B6BAE51EBCE69F93C5E', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888959', '2C000246F4FA4176A1A76E2E005C6C41', '9F27BCB21DDE457C9EED167B7AE02F5A', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888960', 'CF76591AA4244FD0A98DE79ACBDAD624', '592580F61A7F405F8870CEF727565F5D', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888961', '3D6BAE6E8B254EB1B8B863F74DBDBEF7', '75FADB42C3BA412CAF2D8C60A0878357', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888962', '5360EDCB785645379E45751033FB73EF', 'AB522F12F3014F29B83370DA00839883', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888963', 'F9B106323AD9423680B9809CA79945A5', 'C110FD4190E841B2BD187D253921D605', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888964', '11A78B25DBD041BFAAF4AD3FFA3BB675', 'CDA303EDE0F642EE96B194CFAA72B2EB', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888965', 'C7BF64E927574C75B1CA995D585F960B', 'C00588409DCC4456AD7FC18A93EF59F2', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888966', 'C03D606AF9AC46649244C13263C31A4A', '35C1B5BEA8F442DE8E21395B167FC7B7', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888967', '8203E5421FF8447689EF82C86DC079C8', '5DAB809E6FB84E109034D674E635AC6F', null, '990.0000', '890.0000', '100.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888968', 'E0854DEAE58642B1A793E5CEE0F65400', '689BF1098E7E42289801EDF5A36B9ADF', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888969', '657CA88D55804299887B4E12C56F532B', '8E39C3E54DB74CDF9E31F07883F760ED', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888970', 'CE4D392671E64B8DAC27BF04C3A34078', '0A2FE30C820342CAA08CB65760A62E67', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888971', '62ECA12E056448398064BD7399C1FD4F', '7F687139ADF94716888E906A08AADECB', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888972', '69CAD7C04A024793A701D3CBE7FEB743', '0319FE8453AF47CEA307D45F9F1BF5AA', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888973', '5AE6F48FDD414B058A77EF8BF44BFDB1', 'C3E1ADE566334253BA95A100439934A2', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888974', 'B3FD06C30BE1406C810CFC20AAEADC74', 'EC750AC959CA4475BDB4B411DF172984', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888975', 'CFAABA7E56BF4C6CB7615F6783D24140', '50F1F2E0959F4FDD8462C8CA33493582', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888976', 'B964E2A2E63A484EBA31B5DE341D1930', '2DA02A6A9409419F880ADC8286015C5C', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888977', '2D32014D0E0C4060A34CD8FFB386C477', 'E84FFF8A003748F6B42142CC07B2F284', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
INSERT INTO `player_earning` VALUES ('1', '1', 'A07246C2924A415982ABE5E8C6DAD53D', '5.0000', '0.0000', '0.0100', '0.0200', '0.0000', '2', '2019-10-08 17:53:51', '2019-10-18 20:18:45');

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
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户扩展表（玩家）';

-- ----------------------------
-- Records of player_ext
-- ----------------------------
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
INSERT INTO `player_ext` VALUES ('71', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, null, null);
INSERT INTO `player_ext` VALUES ('72', '41BB098B5FF64960936CF4C9FDD5587E', null, null, null, null);
INSERT INTO `player_ext` VALUES ('73', 'F64C63B5286A4F0196502EE6F4D66E01', null, null, null, null);
INSERT INTO `player_ext` VALUES ('74', 'BE273C11BFA1413189BBFB773BBB3188', null, null, null, null);
INSERT INTO `player_ext` VALUES ('75', '498CB905328E4F11A81A201557CF36B9', null, null, null, null);
INSERT INTO `player_ext` VALUES ('76', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, null, null);
INSERT INTO `player_ext` VALUES ('77', '6563530126664673AD9F40A6A4A5D0EA', null, null, null, null);
INSERT INTO `player_ext` VALUES ('78', '4496D535FB9B468BA0F4DF19BCDB6769', null, null, null, null);
INSERT INTO `player_ext` VALUES ('79', '3704C4A299F94A1CA5855E3CBD0E2405', null, null, null, null);
INSERT INTO `player_ext` VALUES ('80', '3525917118CA4B2A944A5B4011A90FDE', null, null, null, null);
INSERT INTO `player_ext` VALUES ('81', '7F4A4539F26146A4B15AF5C056F33C2C', null, null, null, null);
INSERT INTO `player_ext` VALUES ('82', '91807D864D2146589EEAC447F5B83E01', null, null, null, null);
INSERT INTO `player_ext` VALUES ('83', 'F40FB10402B040C8BA60A3DAE6371CBF', null, null, null, null);
INSERT INTO `player_ext` VALUES ('84', '0FC0C33CE0E74310B1FAF9E0EFF3935A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('85', 'F25E38BA72524C4386A17F823E42A45E', null, null, null, null);
INSERT INTO `player_ext` VALUES ('86', 'F3DF65C5CCB34E829379DD0B8FECCA1D', null, null, null, null);
INSERT INTO `player_ext` VALUES ('87', '9D7B73FFE80240E78D283417BD90F6E5', null, null, null, null);
INSERT INTO `player_ext` VALUES ('88', 'D4B57A611CB14C0585935838E2C03F85', null, null, null, null);
INSERT INTO `player_ext` VALUES ('89', '67CA0103A1FF45769EE353F862660F54', null, null, null, null);
INSERT INTO `player_ext` VALUES ('90', 'EC074F6206594D639B860924F868F07A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('91', 'EA6420D6E88241A99CA253676CD05F27', null, null, null, null);
INSERT INTO `player_ext` VALUES ('92', '54793C2136784F348D1A14C7B2A56F3E', null, null, null, null);
INSERT INTO `player_ext` VALUES ('93', '3FE02519657C4DDBB70D605038AB6C40', null, null, null, null);
INSERT INTO `player_ext` VALUES ('94', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, null, null);
INSERT INTO `player_ext` VALUES ('95', 'F4FE3A31F0684891A0FABF3BEA82339F', null, null, null, null);
INSERT INTO `player_ext` VALUES ('96', '45FB476610184C43861705520B58C436', null, null, null, null);
INSERT INTO `player_ext` VALUES ('97', '5C388CE55130449399AAFF883BADCE93', null, null, null, null);
INSERT INTO `player_ext` VALUES ('98', 'D425E00EAED04780A4ED091339483972', null, null, null, null);
INSERT INTO `player_ext` VALUES ('99', '57131A43566C4563A559865C1C7F7EE8', null, null, null, null);
INSERT INTO `player_ext` VALUES ('100', 'C6BFE29AFBF84601BA9C5FAE941BEB42', null, null, null, null);
INSERT INTO `player_ext` VALUES ('101', '60F751762AD14E1E9D838295EA4EF7EF', null, null, null, null);
INSERT INTO `player_ext` VALUES ('102', '080C405C21594F9A9F972EE70244A476', null, null, null, null);
INSERT INTO `player_ext` VALUES ('103', 'C35793EC2300482E9B4EB547469178E1', null, null, null, null);
INSERT INTO `player_ext` VALUES ('104', '89BD70A8320B4DE2B5255F1845727BE9', null, null, null, null);
INSERT INTO `player_ext` VALUES ('105', 'C3D1B7EA3A7241F1AE8D09F19E4A62D5', null, null, null, null);
INSERT INTO `player_ext` VALUES ('106', 'B4362367C88C49F9957E8D2169119F86', null, null, null, null);
INSERT INTO `player_ext` VALUES ('107', '812E1928D65244809C2B4E8EA7D5DF4A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('108', '59A250B94DA44397A354E25F3E3FC9AA', null, null, null, null);
INSERT INTO `player_ext` VALUES ('109', '7A6581225F804011ABCB62504EC5B2DB', null, null, null, null);
INSERT INTO `player_ext` VALUES ('110', '8EF98E66EA074F28867851B8D5651956', null, null, null, null);
INSERT INTO `player_ext` VALUES ('111', '1569829A667041DDA8DD74FD493AC517', null, null, null, null);
INSERT INTO `player_ext` VALUES ('112', 'F053E2EF20594C709D818EBDC15B608A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('113', '521C00EAEE6D4BFABFB1F92CB8EE655B', null, null, null, null);
INSERT INTO `player_ext` VALUES ('114', '63424C4C9A064AC3B066D47F4EE31840', null, null, null, null);
INSERT INTO `player_ext` VALUES ('115', '8A4BFE6587084C148A2D6E7060103005', null, null, null, null);
INSERT INTO `player_ext` VALUES ('116', '6BD288B9EA4C486F9D1CDD05D46C5614', null, null, null, null);
INSERT INTO `player_ext` VALUES ('117', '2AB07B239AEA4F52AC99CC8646478E56', null, null, null, null);
INSERT INTO `player_ext` VALUES ('118', '9EA8FF96287C4834B6A39E4C8A01DC5D', null, null, null, null);
INSERT INTO `player_ext` VALUES ('119', 'A74D161A6F6C45E985EF4A90B557385D', null, null, null, null);
INSERT INTO `player_ext` VALUES ('120', 'D0C0BA6B2D044CBABFFEC3D73CE21F12', null, null, null, null);
INSERT INTO `player_ext` VALUES ('121', '795F30396EB54CC9A051C56E0E146189', null, null, null, null);
INSERT INTO `player_ext` VALUES ('122', 'AB92922C4E874E7B8ACC7030191757AA', null, null, null, null);
INSERT INTO `player_ext` VALUES ('123', 'B459081358E547928E09D886D13E36FD', null, null, null, null);
INSERT INTO `player_ext` VALUES ('124', 'ED4376EC0A2F47389E919F6CE3B1CC94', null, null, null, null);
INSERT INTO `player_ext` VALUES ('125', 'A24C369850B04C1082633B582552D1ED', null, null, null, null);
INSERT INTO `player_ext` VALUES ('126', 'DCBB45F63D5D4CE8AEC3785FE560548F', null, null, null, null);
INSERT INTO `player_ext` VALUES ('127', '76BB5F47E65E4D11BCE1DA94284AE545', null, null, null, null);
INSERT INTO `player_ext` VALUES ('128', 'EAFABDEF0D6440BEA7767596D9E46430', null, null, null, null);
INSERT INTO `player_ext` VALUES ('129', '9C604A15844B438BA61B2C0663D4D627', null, null, null, null);
INSERT INTO `player_ext` VALUES ('130', '9BDE829E950B4F878DCB55AF1070F36A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('131', 'BE67C6A636E54AE2AA9069F2CF62F76F', null, null, null, null);
INSERT INTO `player_ext` VALUES ('132', '9A187C229CDA460180C2521DA01F2A3A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('133', '80F84F191FC94F32B4706C50072A35D1', null, null, null, null);
INSERT INTO `player_ext` VALUES ('134', '091A4E0B8203446B99C62D18446C2C04', null, null, null, null);
INSERT INTO `player_ext` VALUES ('135', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('136', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, null, null, null);
INSERT INTO `player_ext` VALUES ('137', 'C4F15930CF2F40D9988BB0ABDC326A8B', null, null, null, null);
INSERT INTO `player_ext` VALUES ('138', 'CACACA7E9B444CF78481C0BC926BA31D', null, null, null, null);
INSERT INTO `player_ext` VALUES ('139', '1EBF6363110844E5824F41E8F19398EC', null, null, null, null);
INSERT INTO `player_ext` VALUES ('140', '8FCFFCD881CF48B4969929BB50E21D18', null, null, null, null);
INSERT INTO `player_ext` VALUES ('141', '5814CCFBA45B44B8B54E9FF8AB6C1868', null, null, null, null);
INSERT INTO `player_ext` VALUES ('142', 'A569659CB82640328790A9D9BD6E2717', null, null, null, null);
INSERT INTO `player_ext` VALUES ('143', '7407B84521A944E7BF3E1442033AB9A3', null, null, null, null);
INSERT INTO `player_ext` VALUES ('144', '5AE83C28C6ED49B787634445EBF4C17A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('145', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, null, null);
INSERT INTO `player_ext` VALUES ('146', '2C000246F4FA4176A1A76E2E005C6C41', null, null, null, null);
INSERT INTO `player_ext` VALUES ('147', 'CF76591AA4244FD0A98DE79ACBDAD624', null, null, null, null);
INSERT INTO `player_ext` VALUES ('148', '3D6BAE6E8B254EB1B8B863F74DBDBEF7', null, null, null, null);
INSERT INTO `player_ext` VALUES ('149', '5360EDCB785645379E45751033FB73EF', null, null, null, null);
INSERT INTO `player_ext` VALUES ('150', 'F9B106323AD9423680B9809CA79945A5', null, null, null, null);
INSERT INTO `player_ext` VALUES ('151', '11A78B25DBD041BFAAF4AD3FFA3BB675', null, null, null, null);
INSERT INTO `player_ext` VALUES ('152', 'C7BF64E927574C75B1CA995D585F960B', null, null, null, null);
INSERT INTO `player_ext` VALUES ('153', 'C03D606AF9AC46649244C13263C31A4A', null, null, null, null);
INSERT INTO `player_ext` VALUES ('154', '8203E5421FF8447689EF82C86DC079C8', 'gned', 'hehnmjt', null, null);
INSERT INTO `player_ext` VALUES ('155', 'E0854DEAE58642B1A793E5CEE0F65400', 'asdafafd', 'dfdfwfa', null, null);
INSERT INTO `player_ext` VALUES ('156', '657CA88D55804299887B4E12C56F532B', null, null, null, null);
INSERT INTO `player_ext` VALUES ('157', 'CE4D392671E64B8DAC27BF04C3A34078', null, null, null, null);
INSERT INTO `player_ext` VALUES ('158', '62ECA12E056448398064BD7399C1FD4F', null, null, null, null);
INSERT INTO `player_ext` VALUES ('159', '69CAD7C04A024793A701D3CBE7FEB743', null, null, null, null);
INSERT INTO `player_ext` VALUES ('160', '5AE6F48FDD414B058A77EF8BF44BFDB1', null, null, null, null);
INSERT INTO `player_ext` VALUES ('161', 'B3FD06C30BE1406C810CFC20AAEADC74', null, null, null, null);
INSERT INTO `player_ext` VALUES ('162', 'CFAABA7E56BF4C6CB7615F6783D24140', null, null, null, null);
INSERT INTO `player_ext` VALUES ('163', 'B964E2A2E63A484EBA31B5DE341D1930', null, null, null, null);
INSERT INTO `player_ext` VALUES ('164', '2D32014D0E0C4060A34CD8FFB386C477', null, null, null, null);

-- ----------------------------
-- Table structure for player_friends
-- ----------------------------
DROP TABLE IF EXISTS `player_friends`;
CREATE TABLE `player_friends` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '用户id',
  `friend_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '好友id',
  `agree` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '同意添加',
  `invite` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邀请码（来自friend_id）',
  `is_valid` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用的',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_player_id` (`player_id`) USING BTREE,
  KEY `index_friend_id` (`friend_id`) USING BTREE,
  KEY `index_invite` (`invite`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='好友';

-- ----------------------------
-- Records of player_friends
-- ----------------------------
INSERT INTO `player_friends` VALUES ('55', 'F9B106323AD9423680B9809CA79945A5', 'C5318ACD5C5F40259728F6D8FD15F986', '0', null, '0', '2019-10-23 18:01:08', '2019-10-23 18:15:19');
INSERT INTO `player_friends` VALUES ('56', 'F9B106323AD9423680B9809CA79945A5', '2C000246F4FA4176A1A76E2E005C6C41', '0', null, '1', '2019-10-23 18:01:09', '2019-10-23 18:01:09');
INSERT INTO `player_friends` VALUES ('57', 'F9B106323AD9423680B9809CA79945A5', 'CF76591AA4244FD0A98DE79ACBDAD624', '0', null, '1', '2019-10-23 18:01:10', '2019-10-23 18:01:10');
INSERT INTO `player_friends` VALUES ('58', 'F9B106323AD9423680B9809CA79945A5', '2D32014D0E0C4060A34CD8FFB386C477', '0', null, '1', '2019-10-23 18:08:18', '2019-10-23 18:08:18');
INSERT INTO `player_friends` VALUES ('59', 'F9B106323AD9423680B9809CA79945A5', '3D6BAE6E8B254EB1B8B863F74DBDBEF7', '0', null, '1', '2019-10-23 18:18:51', '2019-10-23 18:18:51');
INSERT INTO `player_friends` VALUES ('60', 'F9B106323AD9423680B9809CA79945A5', '3D6BAE6E8B254EB1B8B863F74DBDBEF7', '0', null, '1', '2019-10-23 18:18:51', '2019-10-23 18:18:51');
INSERT INTO `player_friends` VALUES ('61', 'F9B106323AD9423680B9809CA79945A5', '5360EDCB785645379E45751033FB73EF', '0', null, '1', '2019-10-23 18:19:32', '2019-10-23 18:19:32');
INSERT INTO `player_friends` VALUES ('62', 'F9B106323AD9423680B9809CA79945A5', '5360EDCB785645379E45751033FB73EF', '0', null, '1', '2019-10-23 18:19:32', '2019-10-23 18:19:32');
INSERT INTO `player_friends` VALUES ('63', 'F9B106323AD9423680B9809CA79945A5', '11A78B25DBD041BFAAF4AD3FFA3BB675', '0', null, '1', '2019-10-23 18:21:58', '2019-10-23 18:21:58');
INSERT INTO `player_friends` VALUES ('64', 'F9B106323AD9423680B9809CA79945A5', '11A78B25DBD041BFAAF4AD3FFA3BB675', '0', null, '1', '2019-10-23 18:21:58', '2019-10-23 18:21:58');
INSERT INTO `player_friends` VALUES ('65', 'F9B106323AD9423680B9809CA79945A5', 'B964E2A2E63A484EBA31B5DE341D1930', '0', null, '1', '2019-10-23 18:29:34', '2019-10-23 18:29:34');
INSERT INTO `player_friends` VALUES ('66', 'F9B106323AD9423680B9809CA79945A5', 'CFAABA7E56BF4C6CB7615F6783D24140', '0', null, '1', '2019-10-23 18:29:41', '2019-10-23 18:29:41');
INSERT INTO `player_friends` VALUES ('67', '1B86C28CDE9F4F548E6839C7C971FB2A', 'CE4D392671E64B8DAC27BF04C3A34078', '0', null, '1', '2019-10-23 20:22:13', '2019-10-23 20:22:13');
INSERT INTO `player_friends` VALUES ('68', '80F84F191FC94F32B4706C50072A35D1', '2D32014D0E0C4060A34CD8FFB386C477', '0', null, '1', '2019-10-23 20:49:16', '2019-10-23 20:49:16');
INSERT INTO `player_friends` VALUES ('69', '80F84F191FC94F32B4706C50072A35D1', '1B86C28CDE9F4F548E6839C7C971FB2A', '1', null, '1', '2019-10-23 20:50:43', '2019-10-23 20:50:47');

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
) ENGINE=InnoDB AUTO_INCREMENT=1369 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登录日志';


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
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8 COMMENT='交易记录表';

-- ----------------------------
-- Records of player_trade
-- ----------------------------
INSERT INTO `player_trade` VALUES ('262', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', null, '100.0000', '0.0000', '0.0000', '0.0000', null, null, 'TRANSFER', '玩家转账', '2019-10-23 21:49:49', '2019-10-23 21:49:49');
INSERT INTO `player_trade` VALUES ('263', '888888945', '80F84F191FC94F32B4706C50072A35D1', null, '100.0000', '0.0000', '0.0000', '0.0000', null, null, 'TRANSFER', '转账成功！', '2019-10-23 21:49:49', '2019-10-23 21:49:49');
INSERT INTO `player_trade` VALUES ('264', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', null, '100.0000', '0.0000', '0.0000', '0.0000', null, null, 'TRANSFER', '玩家转账', '2019-10-23 21:51:33', '2019-10-23 21:51:33');
INSERT INTO `player_trade` VALUES ('265', '888888945', '80F84F191FC94F32B4706C50072A35D1', null, '100.0000', '0.0000', '0.0000', '0.0000', null, null, 'TRANSFER', '转账成功！', '2019-10-23 21:51:34', '2019-10-23 21:51:34');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('schedulerFactoryBean', '预约自动审核', 'InverstVerifyJob', '0 0 9 * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', '预约自动审核', 'InverstVerifyJob', null, 'com.dream.city.job.InverstVerifyJob', '0', '0', '0', '0', 0x230D0A23546875204F63742031372032323A32373A31322043535420323031390D0A);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', '预约自动审核', 'InverstVerifyJob', '预约自动审核', 'InverstVerifyJob', null, '1571533200000', '1571455662452', '5', 'PAUSED', 'CRON', '1571322432000', '0', null, '0', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sales_order
-- ----------------------------
INSERT INTO `sales_order` VALUES ('81', '20191022181817', '30', 'MT', 'USDT', '30', '80F84F191FC94F32B4706C50072A35D1', '8A2922A66F474A0DA9B10FB4BCD59BA0', '2', '2019-10-22 10:18:18', null);
INSERT INTO `sales_order` VALUES ('82', '20191022201942', '100', 'MT', 'USDT', '100', '8203E5421FF8447689EF82C86DC079C8', '8A2922A66F474A0DA9B10FB4BCD59BA0', '2', '2019-10-22 12:19:42', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=467 DEFAULT CHARSET=utf8 COMMENT='交易流水表';

-- ----------------------------
-- Records of trade_detail
-- ----------------------------
INSERT INTO `trade_detail` VALUES ('464', '264', '176', null, '1B86C28CDE9F4F548E6839C7C971FB2A', 'TRANSFER_VERIFY', '100.0000', '3567.0000', '9945.0000', '2019-10-23 13:51:34', '转账扣除金额', '2019-10-23 21:51:33');
INSERT INTO `trade_detail` VALUES ('465', '264', '176', null, '1B86C28CDE9F4F548E6839C7C971FB2A', 'TRANSFER_VERIFY', '5.0000', '3567.0000', '9940.0000', '2019-10-23 13:51:34', '转账扣除税金', '2019-10-23 21:51:33');
INSERT INTO `trade_detail` VALUES ('466', '264', null, null, '80F84F191FC94F32B4706C50072A35D1', 'TRANSFER_TO', '100.0000', '96639.5000', '9830.0000', null, '转账进账', '2019-10-23 21:51:34');

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
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8 COMMENT='交易审核表';

-- ----------------------------
-- Records of trade_verify
-- ----------------------------
INSERT INTO `trade_verify` VALUES ('175', null, '262', null, 'PASS', '玩家转账', '2019-10-23 21:49:49', '2019-10-23 21:49:49');
INSERT INTO `trade_verify` VALUES ('176', null, '264', null, 'PASS', '玩家转账', '2019-10-23 21:51:33', '2019-10-23 21:51:33');

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
