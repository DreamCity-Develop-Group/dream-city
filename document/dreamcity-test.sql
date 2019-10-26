
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
) ENGINE=InnoDB AUTO_INCREMENT=446 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='认证码';

-- ----------------------------
-- Records of city_auth_code
-- ----------------------------
INSERT INTO `city_auth_code` VALUES ('121', '73193', '17076613584', 'false', '2019-10-16 08:10:31');
INSERT INTO `city_auth_code` VALUES ('122', '314231', '17076613584', null, '2019-10-16 08:09:21');
INSERT INTO `city_auth_code` VALUES ('123', '877798', '13601234561', null, '2019-10-16 09:17:22');
INSERT INTO `city_auth_code` VALUES ('124', '630762', '13601234561', null, '2019-10-16 09:18:27');
INSERT INTO `city_auth_code` VALUES ('125', '758384', '13611111111', 'false', '2019-10-17 05:10:49');
INSERT INTO `city_auth_code` VALUES ('126', '617431', '13622222222', 'false', '2019-10-17 05:10:21');
INSERT INTO `city_auth_code` VALUES ('127', '740262', '18925655262', null, '2019-10-17 07:59:16');
INSERT INTO `city_auth_code` VALUES ('128', '740799', '13022099406', 'false', '2019-10-17 08:10:38');
INSERT INTO `city_auth_code` VALUES ('129', '99853', '13022099406', null, '2019-10-17 08:03:01');
INSERT INTO `city_auth_code` VALUES ('130', '428136', '13022099406', null, '2019-10-17 08:04:51');
INSERT INTO `city_auth_code` VALUES ('131', '665666', '13022099406', null, '2019-10-17 08:44:31');
INSERT INTO `city_auth_code` VALUES ('132', '217001', '13022099406', null, '2019-10-17 08:44:36');
INSERT INTO `city_auth_code` VALUES ('133', '581731', '13022099406', null, '2019-10-17 08:44:37');
INSERT INTO `city_auth_code` VALUES ('134', '134730', '13022099406', null, '2019-10-17 08:44:38');
INSERT INTO `city_auth_code` VALUES ('135', '393260', '13022099406', null, '2019-10-17 08:44:38');
INSERT INTO `city_auth_code` VALUES ('136', '198743', '13022099406', null, '2019-10-17 08:45:08');
INSERT INTO `city_auth_code` VALUES ('137', '256886', '13022099406', null, '2019-10-17 08:45:09');
INSERT INTO `city_auth_code` VALUES ('138', '403348', '13022099406', null, '2019-10-17 08:45:09');
INSERT INTO `city_auth_code` VALUES ('139', '630039', '13022099406', null, '2019-10-17 08:45:38');
INSERT INTO `city_auth_code` VALUES ('140', '997184', '13022099406', null, '2019-10-17 08:46:07');
INSERT INTO `city_auth_code` VALUES ('141', '296363', '13611111111', null, '2019-10-17 08:52:40');
INSERT INTO `city_auth_code` VALUES ('142', '74819', '13022099406', null, '2019-10-17 08:55:34');
INSERT INTO `city_auth_code` VALUES ('143', '130630', '13022099406', null, '2019-10-17 08:55:47');
INSERT INTO `city_auth_code` VALUES ('144', '525678', '13611111111', null, '2019-10-17 08:55:57');
INSERT INTO `city_auth_code` VALUES ('145', '580532', '13022099406', null, '2019-10-17 08:56:05');
INSERT INTO `city_auth_code` VALUES ('146', '141437', '13022099406', null, '2019-10-17 08:56:39');
INSERT INTO `city_auth_code` VALUES ('147', '5606', '13022099406', null, '2019-10-17 08:56:47');
INSERT INTO `city_auth_code` VALUES ('148', '415116', '13022099406', null, '2019-10-17 08:58:04');
INSERT INTO `city_auth_code` VALUES ('149', '683379', '13022099406', null, '2019-10-17 08:59:10');
INSERT INTO `city_auth_code` VALUES ('150', '789524', '13022099406', null, '2019-10-17 09:01:46');
INSERT INTO `city_auth_code` VALUES ('151', '534185', '13601234569', null, '2019-10-17 09:02:29');
INSERT INTO `city_auth_code` VALUES ('152', '207021', '13022099406', null, '2019-10-17 09:02:48');
INSERT INTO `city_auth_code` VALUES ('153', '157372', '13022099406', null, '2019-10-17 09:03:37');
INSERT INTO `city_auth_code` VALUES ('154', '838400', '13022099406', null, '2019-10-17 09:07:03');
INSERT INTO `city_auth_code` VALUES ('155', '985607', '13022099406', null, '2019-10-17 09:07:27');
INSERT INTO `city_auth_code` VALUES ('156', '79844', '13022099406', null, '2019-10-17 09:07:54');
INSERT INTO `city_auth_code` VALUES ('157', '546442', '13601324568', null, '2019-10-17 09:09:25');
INSERT INTO `city_auth_code` VALUES ('158', '192734', '13601324568', null, '2019-10-17 09:09:25');
INSERT INTO `city_auth_code` VALUES ('159', '487733', '13601324568', null, '2019-10-17 09:09:26');
INSERT INTO `city_auth_code` VALUES ('160', '850272', '13601324568', null, '2019-10-17 09:09:26');
INSERT INTO `city_auth_code` VALUES ('161', '505768', '13022099406', null, '2019-10-17 09:09:37');
INSERT INTO `city_auth_code` VALUES ('162', '478373', '13022099406', null, '2019-10-17 09:10:30');
INSERT INTO `city_auth_code` VALUES ('163', '443671', '13022099406', null, '2019-10-17 09:10:31');
INSERT INTO `city_auth_code` VALUES ('164', '140525', '13022099406', null, '2019-10-17 09:11:31');
INSERT INTO `city_auth_code` VALUES ('165', '690092', '13022099406', null, '2019-10-17 09:11:31');
INSERT INTO `city_auth_code` VALUES ('166', '221264', '13022099406', null, '2019-10-17 09:11:31');
INSERT INTO `city_auth_code` VALUES ('167', '172297', '13022099406', null, '2019-10-17 09:11:31');
INSERT INTO `city_auth_code` VALUES ('168', '480870', '13022099406', null, '2019-10-17 09:11:45');
INSERT INTO `city_auth_code` VALUES ('169', '605586', '13022099406', null, '2019-10-17 09:12:17');
INSERT INTO `city_auth_code` VALUES ('170', '577529', '13022099406', null, '2019-10-17 09:12:26');
INSERT INTO `city_auth_code` VALUES ('171', '55870', '13022099406', null, '2019-10-17 09:13:52');
INSERT INTO `city_auth_code` VALUES ('172', '734062', '13022099406', null, '2019-10-17 09:14:03');
INSERT INTO `city_auth_code` VALUES ('173', '662166', '13022099406', null, '2019-10-17 09:25:43');
INSERT INTO `city_auth_code` VALUES ('174', '875108', '13601345611', null, '2019-10-17 09:35:04');
INSERT INTO `city_auth_code` VALUES ('175', '699671', '13601324611', null, '2019-10-17 09:37:36');
INSERT INTO `city_auth_code` VALUES ('176', '803553', '13561234561', 'false', '2019-10-17 09:10:41');
INSERT INTO `city_auth_code` VALUES ('177', '994413', '13561234561', null, '2019-10-17 09:41:20');
INSERT INTO `city_auth_code` VALUES ('178', '490518', '13561234567', 'false', '2019-10-17 09:10:36');
INSERT INTO `city_auth_code` VALUES ('179', '888812', '13561234567', null, '2019-10-17 09:42:14');
INSERT INTO `city_auth_code` VALUES ('180', '656263', '13561234579', 'false', '2019-10-17 09:10:27');
INSERT INTO `city_auth_code` VALUES ('181', '538029', '13561234570', 'false', '2019-10-17 09:10:40');
INSERT INTO `city_auth_code` VALUES ('182', '839755', '13022099406', null, '2019-10-17 09:56:20');
INSERT INTO `city_auth_code` VALUES ('183', '894584', '13022099406', null, '2019-10-17 09:56:20');
INSERT INTO `city_auth_code` VALUES ('184', '593341', '13022099406', null, '2019-10-17 09:56:23');
INSERT INTO `city_auth_code` VALUES ('185', '844556', '13022099406', null, '2019-10-17 09:57:25');
INSERT INTO `city_auth_code` VALUES ('186', '267366', '13022099406', null, '2019-10-17 09:57:50');
INSERT INTO `city_auth_code` VALUES ('187', '343659', '13022099406', null, '2019-10-17 09:59:01');
INSERT INTO `city_auth_code` VALUES ('188', '784', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('189', '86858', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('190', '758963', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('191', '188036', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('192', '194086', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('193', '623107', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('194', '742727', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('195', '922500', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('196', '166505', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('197', '359189', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('198', '116751', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('199', '728430', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('200', '832768', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('201', '953452', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('202', '530815', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('203', '907188', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('204', '155282', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('205', '182340', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('206', '1772', '13022099406', null, '2019-10-17 10:02:32');
INSERT INTO `city_auth_code` VALUES ('207', '938759', '13022099406', null, '2019-10-17 10:03:46');
INSERT INTO `city_auth_code` VALUES ('208', '825526', '13601234567', null, '2019-10-17 10:26:42');
INSERT INTO `city_auth_code` VALUES ('209', '713253', '13601234567', null, '2019-10-17 10:26:42');
INSERT INTO `city_auth_code` VALUES ('210', '455610', '13601234567', null, '2019-10-17 10:26:42');
INSERT INTO `city_auth_code` VALUES ('211', '121384', '13601234567', null, '2019-10-17 10:26:42');
INSERT INTO `city_auth_code` VALUES ('212', '820021', '13601234567', null, '2019-10-17 10:26:42');
INSERT INTO `city_auth_code` VALUES ('213', '914263', '13601234567', null, '2019-10-17 10:26:42');
INSERT INTO `city_auth_code` VALUES ('214', '756193', '13601234567', null, '2019-10-17 10:26:42');
INSERT INTO `city_auth_code` VALUES ('215', '975408', '13601234618', null, '2019-10-17 10:27:16');
INSERT INTO `city_auth_code` VALUES ('216', '733868', '13601234567', null, '2019-10-17 10:32:32');
INSERT INTO `city_auth_code` VALUES ('217', '214300', '13601234561', null, '2019-10-17 11:20:18');
INSERT INTO `city_auth_code` VALUES ('218', '384019', '13022099406', 'false', '2019-10-17 11:10:16');
INSERT INTO `city_auth_code` VALUES ('219', '408059', '13022099406', null, '2019-10-17 11:48:03');
INSERT INTO `city_auth_code` VALUES ('220', '989860', '13022099406', null, '2019-10-17 11:50:12');
INSERT INTO `city_auth_code` VALUES ('221', '350915', '13022099406', 'false', '2019-10-17 11:10:16');
INSERT INTO `city_auth_code` VALUES ('222', '905083', '13022099406', 'false', '2019-10-17 11:10:55');
INSERT INTO `city_auth_code` VALUES ('223', '403647', '13611111111', 'false', '2019-10-17 11:10:11');
INSERT INTO `city_auth_code` VALUES ('224', '592876', '13611111111', null, '2019-10-17 12:21:22');
INSERT INTO `city_auth_code` VALUES ('225', '734734', '13601234561', null, '2019-10-17 12:25:37');
INSERT INTO `city_auth_code` VALUES ('226', '113303', '13601234561', null, '2019-10-17 12:39:09');
INSERT INTO `city_auth_code` VALUES ('227', '656520', '13801234561', 'false', '2019-10-17 12:10:59');
INSERT INTO `city_auth_code` VALUES ('228', '447935', '13601234561', null, '2019-10-17 15:09:32');
INSERT INTO `city_auth_code` VALUES ('229', '113435', '13601234561', null, '2019-10-17 15:09:33');
INSERT INTO `city_auth_code` VALUES ('230', '269103', '13601234561', null, '2019-10-17 15:09:38');
INSERT INTO `city_auth_code` VALUES ('231', '966919', '13601234561', null, '2019-10-17 15:11:21');
INSERT INTO `city_auth_code` VALUES ('232', '882467', '13601234561', null, '2019-10-17 15:13:29');
INSERT INTO `city_auth_code` VALUES ('233', '422104', '13601234561', null, '2019-10-17 15:20:07');
INSERT INTO `city_auth_code` VALUES ('234', '741665', '13601234561', null, '2019-10-17 15:29:43');
INSERT INTO `city_auth_code` VALUES ('235', '598602', '13601234561', null, '2019-10-17 15:37:04');
INSERT INTO `city_auth_code` VALUES ('236', '752558', '15918833191', 'false', '2019-10-18 02:10:46');
INSERT INTO `city_auth_code` VALUES ('237', '967418', '15801234561', 'false', '2019-10-18 05:10:50');
INSERT INTO `city_auth_code` VALUES ('238', '130777', '13601234561', 'false', '2019-10-18 06:10:10');
INSERT INTO `city_auth_code` VALUES ('239', '330211', '18826444888', 'false', '2019-10-18 06:10:35');
INSERT INTO `city_auth_code` VALUES ('240', '71000', '18826444888', null, '2019-10-18 06:51:31');
INSERT INTO `city_auth_code` VALUES ('241', '494205', '18826444888', null, '2019-10-18 06:54:28');
INSERT INTO `city_auth_code` VALUES ('242', '83960', '18826444888', null, '2019-10-18 06:56:00');
INSERT INTO `city_auth_code` VALUES ('243', '59515', '18826444888', null, '2019-10-18 06:57:11');
INSERT INTO `city_auth_code` VALUES ('244', '958930', '18826444888', 'false', '2019-10-18 06:10:58');
INSERT INTO `city_auth_code` VALUES ('245', '929463', '18826444888', null, '2019-10-18 06:58:48');
INSERT INTO `city_auth_code` VALUES ('246', '897253', '18826444888', 'false', '2019-10-18 07:10:23');
INSERT INTO `city_auth_code` VALUES ('247', '701395', '18826444888', null, '2019-10-18 07:17:41');
INSERT INTO `city_auth_code` VALUES ('248', '809677', '18826444885', 'false', '2019-10-18 07:10:04');
INSERT INTO `city_auth_code` VALUES ('249', '429703', '18826444777', 'false', '2019-10-18 07:10:28');
INSERT INTO `city_auth_code` VALUES ('250', '516247', '18826444777', null, '2019-10-18 07:19:23');
INSERT INTO `city_auth_code` VALUES ('251', '923463', '18826444777', null, '2019-10-18 07:20:02');
INSERT INTO `city_auth_code` VALUES ('252', '646714', '18826444779', 'false', '2019-10-18 07:10:12');
INSERT INTO `city_auth_code` VALUES ('253', '566416', '15601234561', null, '2019-10-18 07:20:51');
INSERT INTO `city_auth_code` VALUES ('254', '330172', '18826444884', 'false', '2019-10-18 07:10:32');
INSERT INTO `city_auth_code` VALUES ('255', '14806', '18826444884', null, '2019-10-18 07:23:16');
INSERT INTO `city_auth_code` VALUES ('256', '930881', '18826444884', null, '2019-10-18 07:23:21');
INSERT INTO `city_auth_code` VALUES ('257', '703176', '18826444122', 'false', '2019-10-18 07:10:38');
INSERT INTO `city_auth_code` VALUES ('258', '750384', '18826444125', 'false', '2019-10-18 07:10:57');
INSERT INTO `city_auth_code` VALUES ('259', '417220', '18826444123', 'false', '2019-10-18 07:10:13');
INSERT INTO `city_auth_code` VALUES ('260', '478668', '1882644582', 'false', '2019-10-18 07:10:36');
INSERT INTO `city_auth_code` VALUES ('261', '524590', '18826444888', 'false', '2019-10-18 07:10:49');
INSERT INTO `city_auth_code` VALUES ('262', '447685', '18826444635', null, '2019-10-18 07:53:49');
INSERT INTO `city_auth_code` VALUES ('263', '132606', '18826445214', null, '2019-10-18 07:53:49');
INSERT INTO `city_auth_code` VALUES ('264', '875871', '18826445214', null, '2019-10-18 07:53:48');
INSERT INTO `city_auth_code` VALUES ('265', '758874', '18826444635', null, '2019-10-18 07:53:48');
INSERT INTO `city_auth_code` VALUES ('266', '577515', '18826444888', null, '2019-10-18 07:53:48');
INSERT INTO `city_auth_code` VALUES ('267', '858910', '18826444787', 'false', '2019-10-18 08:10:05');
INSERT INTO `city_auth_code` VALUES ('268', '635033', '18826444789', 'false', '2019-10-18 08:10:33');
INSERT INTO `city_auth_code` VALUES ('269', '591493', '18826444780', 'false', '2019-10-18 08:10:49');
INSERT INTO `city_auth_code` VALUES ('270', '252038', '18826444775', 'false', '2019-10-18 08:10:46');
INSERT INTO `city_auth_code` VALUES ('271', '688048', '18826444774', null, '2019-10-18 08:05:08');
INSERT INTO `city_auth_code` VALUES ('272', '677935', '18826214888', 'false', '2019-10-18 08:10:21');
INSERT INTO `city_auth_code` VALUES ('273', '565259', '13633333333', 'false', '2019-10-18 08:10:50');
INSERT INTO `city_auth_code` VALUES ('274', '275034', '13633333333', null, '2019-10-18 08:30:21');
INSERT INTO `city_auth_code` VALUES ('275', '146669', '18826444534', 'false', '2019-10-18 08:10:14');
INSERT INTO `city_auth_code` VALUES ('276', '148628', '18826444534', null, '2019-10-18 08:41:24');
INSERT INTO `city_auth_code` VALUES ('277', '265034', '18826444531', 'false', '2019-10-18 08:10:34');
INSERT INTO `city_auth_code` VALUES ('278', '881645', '18826440531', 'false', '2019-10-18 08:10:57');
INSERT INTO `city_auth_code` VALUES ('279', '716533', '18826440531', null, '2019-10-18 08:44:07');
INSERT INTO `city_auth_code` VALUES ('280', '778258', '18826440531', null, '2019-10-18 08:44:14');
INSERT INTO `city_auth_code` VALUES ('281', '511541', '18826440522', 'false', '2019-10-18 08:10:25');
INSERT INTO `city_auth_code` VALUES ('282', '802503', '18826440521', 'false', '2019-10-18 08:10:45');
INSERT INTO `city_auth_code` VALUES ('283', '452294', '18826440421', 'false', '2019-10-18 08:10:29');
INSERT INTO `city_auth_code` VALUES ('284', '304077', '18823440421', null, '2019-10-18 08:46:47');
INSERT INTO `city_auth_code` VALUES ('285', '464638', '18823440421', null, '2019-10-18 08:46:47');
INSERT INTO `city_auth_code` VALUES ('286', '656852', '18823440421', 'false', '2019-10-18 08:10:53');
INSERT INTO `city_auth_code` VALUES ('287', '90872', '13600000000', null, '2019-10-18 11:31:48');
INSERT INTO `city_auth_code` VALUES ('288', '137206', '13601234561', 'false', '2019-10-18 11:10:10');
INSERT INTO `city_auth_code` VALUES ('289', '645174', '18826444047', null, '2019-10-18 13:15:37');
INSERT INTO `city_auth_code` VALUES ('290', '46887', '18826444047', 'false', '2019-10-18 01:10:38');
INSERT INTO `city_auth_code` VALUES ('291', '170626', '18826444888', null, '2019-10-18 13:20:26');
INSERT INTO `city_auth_code` VALUES ('292', '757753', '18826444888', null, '2019-10-18 13:20:41');
INSERT INTO `city_auth_code` VALUES ('293', '531209', '18826444888', null, '2019-10-18 13:20:50');
INSERT INTO `city_auth_code` VALUES ('294', '788703', '18826444888', null, '2019-10-18 13:20:51');
INSERT INTO `city_auth_code` VALUES ('295', '977238', '18826444889', null, '2019-10-18 13:24:25');
INSERT INTO `city_auth_code` VALUES ('296', '565264', '18826444889', null, '2019-10-18 13:24:26');
INSERT INTO `city_auth_code` VALUES ('297', '564065', '18826444889', 'false', '2019-10-18 01:10:30');
INSERT INTO `city_auth_code` VALUES ('298', '564234', '18826444888', null, '2019-10-18 13:29:49');
INSERT INTO `city_auth_code` VALUES ('299', '409150', '18826444888', null, '2019-10-18 13:30:16');
INSERT INTO `city_auth_code` VALUES ('300', '138', '18826444888', null, '2019-10-18 13:30:15');
INSERT INTO `city_auth_code` VALUES ('301', '339515', '18826444888', null, '2019-10-18 13:30:21');
INSERT INTO `city_auth_code` VALUES ('302', '44925', '18826444888', null, '2019-10-18 13:30:21');
INSERT INTO `city_auth_code` VALUES ('303', '944367', '18826444888', null, '2019-10-18 13:30:20');
INSERT INTO `city_auth_code` VALUES ('304', '942010', '18826444888', null, '2019-10-18 13:31:08');
INSERT INTO `city_auth_code` VALUES ('305', '711709', '18826444888', null, '2019-10-18 13:31:27');
INSERT INTO `city_auth_code` VALUES ('306', '637727', '18826444888', null, '2019-10-18 13:32:48');
INSERT INTO `city_auth_code` VALUES ('307', '11430', '18826444888', null, '2019-10-18 13:33:03');
INSERT INTO `city_auth_code` VALUES ('308', '639595', '18826444888', null, '2019-10-18 13:34:37');
INSERT INTO `city_auth_code` VALUES ('309', '603723', '13602222222', null, '2019-10-19 02:09:30');
INSERT INTO `city_auth_code` VALUES ('310', '889067', '13602222222', 'false', '2019-10-19 02:10:32');
INSERT INTO `city_auth_code` VALUES ('311', '179004', '13699999999', 'false', '2019-10-19 02:10:50');
INSERT INTO `city_auth_code` VALUES ('312', '128663', '16801234561', 'false', '2019-10-19 02:10:46');
INSERT INTO `city_auth_code` VALUES ('313', '174294', '13688888888', 'false', '2019-10-19 02:10:27');
INSERT INTO `city_auth_code` VALUES ('314', '885511', '13688888888', null, '2019-10-19 03:31:09');
INSERT INTO `city_auth_code` VALUES ('315', '984405', '13688888888', null, '2019-10-19 03:37:03');
INSERT INTO `city_auth_code` VALUES ('316', '621286', '13688888888', null, '2019-10-19 03:56:16');
INSERT INTO `city_auth_code` VALUES ('317', '24870', '13688888888', null, '2019-10-19 03:59:10');
INSERT INTO `city_auth_code` VALUES ('318', '690450', '15801234561', 'false', '2019-10-19 05:10:06');
INSERT INTO `city_auth_code` VALUES ('319', '101061', '15801234561', 'false', '2019-10-19 05:10:24');
INSERT INTO `city_auth_code` VALUES ('320', '70143', '15801234563', 'false', '2019-10-19 05:10:39');
INSERT INTO `city_auth_code` VALUES ('321', '334224', '5855', null, '2019-10-19 05:14:20');
INSERT INTO `city_auth_code` VALUES ('322', '398689', '18826441472', 'false', '2019-10-19 07:10:52');
INSERT INTO `city_auth_code` VALUES ('323', '893585', '18826441472', null, '2019-10-19 07:34:01');
INSERT INTO `city_auth_code` VALUES ('324', '557431', '18826441472', null, '2019-10-19 07:35:52');
INSERT INTO `city_auth_code` VALUES ('325', '425104', '18826441472', null, '2019-10-19 07:37:02');
INSERT INTO `city_auth_code` VALUES ('326', '838452', '18826441472', null, '2019-10-19 07:38:00');
INSERT INTO `city_auth_code` VALUES ('327', '685051', '18826441471', 'false', '2019-10-19 07:10:46');
INSERT INTO `city_auth_code` VALUES ('328', '256970', '18826441497', null, '2019-10-19 07:43:03');
INSERT INTO `city_auth_code` VALUES ('329', '208815', '18826441497', null, '2019-10-19 07:43:03');
INSERT INTO `city_auth_code` VALUES ('330', '318603', '18826441497', null, '2019-10-19 07:43:03');
INSERT INTO `city_auth_code` VALUES ('331', '381818', '18826441497', null, '2019-10-19 07:43:03');
INSERT INTO `city_auth_code` VALUES ('332', '701103', '18826441497', 'false', '2019-10-19 07:10:09');
INSERT INTO `city_auth_code` VALUES ('333', '36095', '18826441490', 'false', '2019-10-19 07:10:03');
INSERT INTO `city_auth_code` VALUES ('334', '217425', '18826441498', 'false', '2019-10-19 07:10:42');
INSERT INTO `city_auth_code` VALUES ('335', '54461', '18826441475', 'false', '2019-10-19 07:10:35');
INSERT INTO `city_auth_code` VALUES ('336', '315178', '18826441476', null, '2019-10-19 07:49:14');
INSERT INTO `city_auth_code` VALUES ('337', '530426', '18826441476', null, '2019-10-19 07:49:15');
INSERT INTO `city_auth_code` VALUES ('338', '224772', '18826441471', null, '2019-10-19 07:49:45');
INSERT INTO `city_auth_code` VALUES ('339', '883333', '18826441471', 'false', '2019-10-19 07:10:59');
INSERT INTO `city_auth_code` VALUES ('340', '476558', '18826441471', null, '2019-10-19 07:50:00');
INSERT INTO `city_auth_code` VALUES ('341', '185687', '18826441471', null, '2019-10-19 07:50:00');
INSERT INTO `city_auth_code` VALUES ('342', '720382', '18826441471', null, '2019-10-19 07:50:09');
INSERT INTO `city_auth_code` VALUES ('343', '796641', '18826441471', null, '2019-10-19 07:50:19');
INSERT INTO `city_auth_code` VALUES ('344', '580825', '18826441471', null, '2019-10-19 07:50:25');
INSERT INTO `city_auth_code` VALUES ('345', '301538', '188264414724', 'false', '2019-10-19 07:10:38');
INSERT INTO `city_auth_code` VALUES ('346', '345847', '18821645416', 'false', '2019-10-19 07:10:00');
INSERT INTO `city_auth_code` VALUES ('347', '990208', '18821645416', null, '2019-10-19 08:10:48');
INSERT INTO `city_auth_code` VALUES ('348', '697398', '13601234561', null, '2019-10-19 08:15:23');
INSERT INTO `city_auth_code` VALUES ('349', '561077', '18821645411', 'false', '2019-10-19 09:10:51');
INSERT INTO `city_auth_code` VALUES ('350', '128478', '18821645411', null, '2019-10-19 09:03:16');
INSERT INTO `city_auth_code` VALUES ('351', '533496', '18821645411', null, '2019-10-19 09:03:21');
INSERT INTO `city_auth_code` VALUES ('352', '612195', '13601234561', null, '2019-10-19 09:39:44');
INSERT INTO `city_auth_code` VALUES ('353', '423607', '18801234569', 'false', '2019-10-19 09:10:03');
INSERT INTO `city_auth_code` VALUES ('354', '793501', '18801234568', 'false', '2019-10-19 09:10:26');
INSERT INTO `city_auth_code` VALUES ('355', '7626', '13601234561', null, '2019-10-19 09:57:46');
INSERT INTO `city_auth_code` VALUES ('356', '324906', '15901234561', 'false', '2019-10-19 12:10:24');
INSERT INTO `city_auth_code` VALUES ('357', '198765', '15918811111', 'false', '2019-10-19 01:10:10');
INSERT INTO `city_auth_code` VALUES ('358', '259669', '15918822222', 'false', '2019-10-19 01:10:46');
INSERT INTO `city_auth_code` VALUES ('359', '518021', '13807894563', 'false', '2019-10-21 11:10:08');
INSERT INTO `city_auth_code` VALUES ('360', '397528', '13709876543', 'false', '2019-10-21 12:10:36');
INSERT INTO `city_auth_code` VALUES ('361', '821919', '13677777777', 'false', '2019-10-22 02:10:11');
INSERT INTO `city_auth_code` VALUES ('362', '897246', '13677777777', 'false', '2019-10-22 02:10:25');
INSERT INTO `city_auth_code` VALUES ('363', '262301', '13600000000', 'false', '2019-10-22 02:10:25');
INSERT INTO `city_auth_code` VALUES ('364', '961215', '13633333333', 'false', '2019-10-22 03:10:01');
INSERT INTO `city_auth_code` VALUES ('365', '636755', '13711111111', 'false', '2019-10-22 03:10:37');
INSERT INTO `city_auth_code` VALUES ('366', '983159', '13722222222', 'false', '2019-10-22 03:10:04');
INSERT INTO `city_auth_code` VALUES ('367', '329232', '13722222222', null, '2019-10-22 03:38:40');
INSERT INTO `city_auth_code` VALUES ('368', '641344', '13722222222', null, '2019-10-22 03:39:24');
INSERT INTO `city_auth_code` VALUES ('369', '948895', '13722222222', null, '2019-10-22 03:39:44');
INSERT INTO `city_auth_code` VALUES ('370', '62349', '13722222222', null, '2019-10-22 03:41:42');
INSERT INTO `city_auth_code` VALUES ('371', '776703', '13722222222', null, '2019-10-22 03:44:24');
INSERT INTO `city_auth_code` VALUES ('372', '727070', '13744444444', 'false', '2019-10-22 03:10:08');
INSERT INTO `city_auth_code` VALUES ('373', '87716', '13755555555', 'false', '2019-10-22 04:10:56');
INSERT INTO `city_auth_code` VALUES ('374', '11307', '137666666', null, '2019-10-22 04:02:55');
INSERT INTO `city_auth_code` VALUES ('375', '829313', '13766666666', null, '2019-10-22 04:03:07');
INSERT INTO `city_auth_code` VALUES ('376', '274775', '13766666666', 'false', '2019-10-22 04:10:26');
INSERT INTO `city_auth_code` VALUES ('377', '262979', '13766666666', 'false', '2019-10-22 04:10:53');
INSERT INTO `city_auth_code` VALUES ('378', '575985', '13777777777', 'false', '2019-10-22 04:10:07');
INSERT INTO `city_auth_code` VALUES ('379', '486782', '13569874567', null, '2019-10-22 05:22:20');
INSERT INTO `city_auth_code` VALUES ('380', '960174', '13698745610', 'false', '2019-10-22 05:10:32');
INSERT INTO `city_auth_code` VALUES ('381', '345242', '13801234561', null, '2019-10-22 07:07:07');
INSERT INTO `city_auth_code` VALUES ('382', '111882', '13801234561', 'false', '2019-10-22 07:10:58');
INSERT INTO `city_auth_code` VALUES ('383', '407533', '13901234561', null, '2019-10-22 07:09:14');
INSERT INTO `city_auth_code` VALUES ('384', '545058', '13688888888', 'false', '2019-10-22 07:10:30');
INSERT INTO `city_auth_code` VALUES ('385', '455802', '13688888881', 'false', '2019-10-22 07:10:14');
INSERT INTO `city_auth_code` VALUES ('386', '214389', '13688888882', 'false', '2019-10-22 07:10:49');
INSERT INTO `city_auth_code` VALUES ('387', '717314', '13688888883', 'false', '2019-10-22 07:10:24');
INSERT INTO `city_auth_code` VALUES ('388', '543184', '13888888884', 'false', '2019-10-22 08:10:17');
INSERT INTO `city_auth_code` VALUES ('389', '916146', '13688888885', 'false', '2019-10-22 08:10:36');
INSERT INTO `city_auth_code` VALUES ('390', '324340', '13601111111', 'false', '2019-10-22 08:10:15');
INSERT INTO `city_auth_code` VALUES ('391', '308173', '13801111111', 'false', '2019-10-22 08:10:48');
INSERT INTO `city_auth_code` VALUES ('392', '124167', '13601111112', 'false', '2019-10-22 09:10:41');
INSERT INTO `city_auth_code` VALUES ('393', '995875', '13601111113', 'false', '2019-10-22 09:10:53');
INSERT INTO `city_auth_code` VALUES ('394', '686746', '13601111114', 'false', '2019-10-22 10:10:16');
INSERT INTO `city_auth_code` VALUES ('395', '800817', '13601111115', 'false', '2019-10-22 10:10:56');
INSERT INTO `city_auth_code` VALUES ('396', '848585', '13601111116', 'false', '2019-10-22 10:10:37');
INSERT INTO `city_auth_code` VALUES ('397', '662263', '13601111117', 'false', '2019-10-22 10:10:31');
INSERT INTO `city_auth_code` VALUES ('398', '692760', '13601111118', 'false', '2019-10-22 10:10:18');
INSERT INTO `city_auth_code` VALUES ('399', '766444', '13601111119', 'false', '2019-10-22 11:10:11');
INSERT INTO `city_auth_code` VALUES ('400', '466707', '13802222222', 'false', '2019-10-22 11:10:53');
INSERT INTO `city_auth_code` VALUES ('401', '959154', '13800001111', 'false', '2019-10-22 11:10:43');
INSERT INTO `city_auth_code` VALUES ('402', '762308', '136011111101', null, '2019-10-22 11:59:36');
INSERT INTO `city_auth_code` VALUES ('403', '776607', '13601111101', 'false', '2019-10-22 12:10:41');
INSERT INTO `city_auth_code` VALUES ('404', '262223', '13601111102', 'false', '2019-10-22 12:10:53');
INSERT INTO `city_auth_code` VALUES ('405', '562343', '13601111103', 'false', '2019-10-22 12:10:01');
INSERT INTO `city_auth_code` VALUES ('406', '670563', '13601111104', 'false', '2019-10-22 12:10:09');
INSERT INTO `city_auth_code` VALUES ('407', '420425', '13601111105', 'false', '2019-10-22 12:10:17');
INSERT INTO `city_auth_code` VALUES ('408', '280882', '13601111106', 'false', '2019-10-22 12:10:24');
INSERT INTO `city_auth_code` VALUES ('409', '214086', '13601111107', 'false', '2019-10-22 12:10:30');
INSERT INTO `city_auth_code` VALUES ('410', '764073', '13601111107', null, '2019-10-22 12:01:35');
INSERT INTO `city_auth_code` VALUES ('411', '509082', '13601111108', 'false', '2019-10-22 12:10:38');
INSERT INTO `city_auth_code` VALUES ('412', '767058', '13601111109', 'false', '2019-10-22 12:10:48');
INSERT INTO `city_auth_code` VALUES ('413', '906257', '13811113333', 'false', '2019-10-22 12:10:42');
INSERT INTO `city_auth_code` VALUES ('414', '181873', '13611112222', 'false', '2019-10-22 12:10:35');
INSERT INTO `city_auth_code` VALUES ('415', '230057', '13711112222', 'false', '2019-10-22 12:10:40');
INSERT INTO `city_auth_code` VALUES ('416', '681119', '18826444888', 'false', '2019-10-22 12:10:50');
INSERT INTO `city_auth_code` VALUES ('417', '830525', '18826444889', 'false', '2019-10-22 12:10:48');
INSERT INTO `city_auth_code` VALUES ('418', '794961', '18826444887', 'false', '2019-10-22 12:10:57');
INSERT INTO `city_auth_code` VALUES ('419', '341866', '18826444886', null, '2019-10-22 12:49:39');
INSERT INTO `city_auth_code` VALUES ('420', '193182', '18826444886', 'false', '2019-10-22 12:10:43');
INSERT INTO `city_auth_code` VALUES ('421', '408350', '18826444885', 'false', '2019-10-22 12:10:11');
INSERT INTO `city_auth_code` VALUES ('422', '893004', '18826444885', 'false', '2019-10-22 12:10:11');
INSERT INTO `city_auth_code` VALUES ('423', '444667', '18826444884', 'false', '2019-10-22 12:10:10');
INSERT INTO `city_auth_code` VALUES ('424', '604684', '18826444885', null, '2019-10-22 13:00:34');
INSERT INTO `city_auth_code` VALUES ('425', '726979', '18826444870', 'false', '2019-10-22 01:10:51');
INSERT INTO `city_auth_code` VALUES ('426', '524900', '18826444871', null, '2019-10-22 13:03:26');
INSERT INTO `city_auth_code` VALUES ('427', '3100', '18826444871', 'false', '2019-10-22 01:10:35');
INSERT INTO `city_auth_code` VALUES ('428', '771509', '13609999999', null, '2019-10-23 06:50:46');
INSERT INTO `city_auth_code` VALUES ('429', '287977', '13822222111', 'false', '2019-10-23 12:10:57');
INSERT INTO `city_auth_code` VALUES ('430', '193313', '13622221111', 'false', '2019-10-23 12:10:56');
INSERT INTO `city_auth_code` VALUES ('431', '738597', '13901119999', 'false', '2019-10-24 08:10:26');
INSERT INTO `city_auth_code` VALUES ('432', '317107', '15702228888', 'false', '2019-10-24 08:10:22');
INSERT INTO `city_auth_code` VALUES ('433', '161554', '13502225555', 'false', '2019-10-24 10:10:38');
INSERT INTO `city_auth_code` VALUES ('434', '441513', '13601111121', 'false', '2019-10-24 12:10:11');
INSERT INTO `city_auth_code` VALUES ('435', '755632', '13601111126', null, '2019-10-24 12:05:46');
INSERT INTO `city_auth_code` VALUES ('436', '542808', '13601111126', 'false', '2019-10-24 12:10:47');
INSERT INTO `city_auth_code` VALUES ('437', '931750', '13502225555', null, '2019-10-24 13:37:21');
INSERT INTO `city_auth_code` VALUES ('438', '263564', '13502225555', null, '2019-10-24 13:44:26');
INSERT INTO `city_auth_code` VALUES ('439', '447322', '13601111111', null, '2019-10-25 02:33:33');
INSERT INTO `city_auth_code` VALUES ('440', '286387', '13601111126', null, '2019-10-25 02:35:53');
INSERT INTO `city_auth_code` VALUES ('441', '623815', '13601111126', null, '2019-10-25 02:37:48');
INSERT INTO `city_auth_code` VALUES ('442', '117177', '13601111126', null, '2019-10-25 02:44:32');
INSERT INTO `city_auth_code` VALUES ('443', '869014', '13601111112', null, '2019-10-25 02:52:26');
INSERT INTO `city_auth_code` VALUES ('444', '335469', '13601111111', null, '2019-10-25 02:53:57');
INSERT INTO `city_auth_code` VALUES ('445', '42506', '13601111111', null, '2019-10-25 09:05:19');

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
INSERT INTO `city_dictionary` VALUES ('9', '转账税金', 'player.inside.transfer.mt.tax', '5', '1', '内部转账税金', null, null);

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
  `in_limit` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '限额',
  `in_start` datetime DEFAULT NULL COMMENT '开始时间',
  `in_personal_tax` decimal(50,9) unsigned zerofill DEFAULT '0.0000' COMMENT '个人税金',
  `in_enterprise_tax` decimal(50,9) unsigned zerofill DEFAULT '0.0000' COMMENT '企业税金',
  `in_quota_tax` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '定额税',
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
INSERT INTO `city_invest` VALUES ('1', '小吃摊', '11', '30.0000', '2019-09-18 17:56:20', '0.0000', '0.0000', '0.1000', '2', '2020-01-31 17:56:50', null, 'Y', null, null);
INSERT INTO `city_invest` VALUES ('2', '玩具摊', '21', '100.0000', '2019-10-01 21:04:25', '0.0000', '0.2000', '0.2000', '3', '2019-11-30 21:04:53', null, 'Y', null, null);
INSERT INTO `city_invest` VALUES ('3', '酒吧', '31', '300.0000', '2019-10-01 21:04:25', '0.2000', '0.0500' '0.0000', '3', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('4', '医药公司', '41', '500.0000', '2019-10-01 21:04:25', '0.2000', '0.1000', '0.0000', '4', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('5', '电影公司', '51', '1000.0000', '2019-10-01 21:04:25', '0.2000', '0.1500', '0.0000', '4', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('6', '汽车集团', '61', '3000.0000', '2019-10-01 21:04:25', '0.0000', '0.2000', '0.0000', '5', '2019-11-30 21:04:53', null, 'N', null, null);
INSERT INTO `city_invest` VALUES ('7', '投资集团', '71', '5000.0000', '2019-10-01 21:04:25', '0.0000', '0.1000', '0.0000', '5', '2019-11-30 21:04:53', null, 'N', null, null);

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
-- Records of city_message
-- ----------------------------
INSERT INTO `city_message` VALUES ('1', '80F84F191FC94F32B4706C50072A35D1', 'E13D6322D389411C959CD7AC7A2B230F', 'svz', 'dgjnyrmtfsd', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-24 19:39:52');
INSERT INTO `city_message` VALUES ('2', '80F84F191FC94F32B4706C50072A35D1', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvfshssvsv', 'asadvbsbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-24 16:25:01');
INSERT INTO `city_message` VALUES ('3', '80F84F191FC94F32B4706C50072A35D1', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvssvsv', 'asadvbsbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-24 16:25:34');
INSERT INTO `city_message` VALUES ('4', '80F84F191FC94F32B4706C50072A35D1', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvssvsv', 'cncncncn', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-24 19:39:42');
INSERT INTO `city_message` VALUES ('5', '80F84F191FC94F32B4706C50072A35D1', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvssvsv', 'asadvbsbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-19 19:31:13');
INSERT INTO `city_message` VALUES ('6', '80F84F191FC94F32B4706C50072A35D1', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvssvsv', 'asadvbsbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-19 19:31:15');
INSERT INTO `city_message` VALUES ('7', '80F84F191FC94F32B4706C50072A35D1', '99B1D351FD5242CD8F73E86A2BBC89A0', 'cnc', 'asadvbsbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-19 19:43:38');
INSERT INTO `city_message` VALUES ('8', '80F84F191FC94F32B4706C50072A35D1', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvssvsv', 'asadvbsbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-19 19:43:40');
INSERT INTO `city_message` VALUES ('9', '80F84F191FC94F32B4706C50072A35D1', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvssvncnsv', 'asadcncvbsbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-19 19:43:42');
INSERT INTO `city_message` VALUES ('10', '1B86C28CDE9F4F548E6839C7C971FB2A', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvssvsv', 'asadvbscncnbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-25 10:56:11');
INSERT INTO `city_message` VALUES ('41', '1B86C28CDE9F4F548E6839C7C971FB2A', '99B1D351FD5242CD8F73E86A2BBC89A0', 'vvvssvsv', 'asadvbsbz', '1', '2019-10-02 18:03:30', '2019-10-02 18:03:30', '2019-10-19 19:43:51');

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
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表（玩家）';

-- ----------------------------
-- Records of city_player
-- ----------------------------
INSERT INTO `city_player` VALUES ('1', '8A2922A66F474A0DA9B10FB4BCD59BA0', 'system', 'system', 'system', 'system', 'system', '1', '1', null, null);
INSERT INTO `city_player` VALUES ('230', '80F84F191FC94F32B4706C50072A35D1', '13601111111', 'test1', '49dec5fb8af4eeef7c95e7f5c66c8ae6', 'e10adc3949ba59abbe56e057f20f883e', '670f07', '1', '1', '2019-10-22 16:35:29', '2019-10-25 17:05:23');
INSERT INTO `city_player` VALUES ('232', '1B86C28CDE9F4F548E6839C7C971FB2A', '13601111112', 'test2', '96e79218965eb72c92a549dd5a330112', 'e10adc3949ba59abbe56e057f20f883e', '925818', '1', '1', '2019-10-22 17:35:00', '2019-10-25 10:53:00');
INSERT INTO `city_player` VALUES ('233', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', '13601111113', 'test3', '96e79218965eb72c92a549dd5a330112', 'e10adc3949ba59abbe56e057f20f883e', '98ebb9', null, '1', '2019-10-22 17:50:02', '2019-10-22 17:54:18');
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
INSERT INTO `city_player` VALUES ('261', '2D32014D0E0C4060A34CD8FFB386C477', '13601111121', '13601111121', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, '9fa14b', null, '1', '2019-10-22 21:03:46', null);
INSERT INTO `city_player` VALUES ('262', '7835E18A37E4416488E2F1E7D122BC2D', '13901119999', 'ggg', '8ed4548c667bf26b19aa03895379305e', null, 'e44584', null, '1', '2019-10-24 16:19:43', null);
INSERT INTO `city_player` VALUES ('263', '5363F435F71140A2B8CEA98504DF26EF', '15702228888', 'hhh', 'b3261745e8b439775e5a9c1a838e91fb', null, '132f6f', null, '1', '2019-10-24 16:59:33', null);
INSERT INTO `city_player` VALUES ('264', '0D4A5F9A0AE2411BB310D298CAAFC86D', '13502225555', 'yuyu', '8ed4548c667bf26b19aa03895379305e', 'e10adc3949ba59abbe56e057f20f883e', '50a7ec', null, '1', '2019-10-24 18:06:49', '2019-10-24 21:44:32');
INSERT INTO `city_player` VALUES ('265', 'E500720E7E5048C18EB8120C32107D8C', '13601111126', 'test26', '49dec5fb8af4eeef7c95e7f5c66c8ae6', null, '5c628d', null, '1', '2019-10-24 20:05:47', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='玩家等级(会员/商会等级)';

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
INSERT INTO `city_player_grade` VALUES ('59', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('60', '41BB098B5FF64960936CF4C9FDD5587E', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('61', 'F64C63B5286A4F0196502EE6F4D66E01', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('62', 'BE273C11BFA1413189BBFB773BBB3188', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('63', '498CB905328E4F11A81A201557CF36B9', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('64', '916A1597AB5D48EFA2ECCD75A2E255D9', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('65', '6563530126664673AD9F40A6A4A5D0EA', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('66', '4496D535FB9B468BA0F4DF19BCDB6769', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('67', '3704C4A299F94A1CA5855E3CBD0E2405', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('68', '3525917118CA4B2A944A5B4011A90FDE', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('69', '7F4A4539F26146A4B15AF5C056F33C2C', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('70', '91807D864D2146589EEAC447F5B83E01', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('71', 'F40FB10402B040C8BA60A3DAE6371CBF', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('72', '0FC0C33CE0E74310B1FAF9E0EFF3935A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('73', 'F25E38BA72524C4386A17F823E42A45E', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('74', 'F3DF65C5CCB34E829379DD0B8FECCA1D', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('75', '9D7B73FFE80240E78D283417BD90F6E5', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('76', 'D4B57A611CB14C0585935838E2C03F85', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('77', '67CA0103A1FF45769EE353F862660F54', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('78', 'EC074F6206594D639B860924F868F07A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('79', 'EA6420D6E88241A99CA253676CD05F27', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('80', '54793C2136784F348D1A14C7B2A56F3E', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('81', '3FE02519657C4DDBB70D605038AB6C40', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('82', '9D6E9096AEA648DAA3825AD50A4388A7', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('83', 'F4FE3A31F0684891A0FABF3BEA82339F', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('84', '45FB476610184C43861705520B58C436', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('85', '5C388CE55130449399AAFF883BADCE93', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('86', 'D425E00EAED04780A4ED091339483972', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('87', '57131A43566C4563A559865C1C7F7EE8', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('88', 'C6BFE29AFBF84601BA9C5FAE941BEB42', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('89', '60F751762AD14E1E9D838295EA4EF7EF', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('90', '080C405C21594F9A9F972EE70244A476', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('91', 'C35793EC2300482E9B4EB547469178E1', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('92', '89BD70A8320B4DE2B5255F1845727BE9', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('93', 'C3D1B7EA3A7241F1AE8D09F19E4A62D5', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('94', 'B4362367C88C49F9957E8D2169119F86', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('95', '812E1928D65244809C2B4E8EA7D5DF4A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('96', '59A250B94DA44397A354E25F3E3FC9AA', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('97', '7A6581225F804011ABCB62504EC5B2DB', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('98', '8EF98E66EA074F28867851B8D5651956', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('99', '1569829A667041DDA8DD74FD493AC517', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('100', 'F053E2EF20594C709D818EBDC15B608A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('101', '521C00EAEE6D4BFABFB1F92CB8EE655B', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('102', '63424C4C9A064AC3B066D47F4EE31840', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('103', '8A4BFE6587084C148A2D6E7060103005', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('104', '6BD288B9EA4C486F9D1CDD05D46C5614', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('105', '2AB07B239AEA4F52AC99CC8646478E56', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('106', '9EA8FF96287C4834B6A39E4C8A01DC5D', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('107', 'A74D161A6F6C45E985EF4A90B557385D', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('108', 'D0C0BA6B2D044CBABFFEC3D73CE21F12', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('109', '795F30396EB54CC9A051C56E0E146189', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('110', 'AB92922C4E874E7B8ACC7030191757AA', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('111', 'B459081358E547928E09D886D13E36FD', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('112', 'ED4376EC0A2F47389E919F6CE3B1CC94', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('113', 'A24C369850B04C1082633B582552D1ED', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('114', 'DCBB45F63D5D4CE8AEC3785FE560548F', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('115', '76BB5F47E65E4D11BCE1DA94284AE545', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('116', 'EAFABDEF0D6440BEA7767596D9E46430', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('117', '9C604A15844B438BA61B2C0663D4D627', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('118', '9BDE829E950B4F878DCB55AF1070F36A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('119', 'BE67C6A636E54AE2AA9069F2CF62F76F', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('120', '9A187C229CDA460180C2521DA01F2A3A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('121', '80F84F191FC94F32B4706C50072A35D1', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('122', '091A4E0B8203446B99C62D18446C2C04', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('123', '1B86C28CDE9F4F548E6839C7C971FB2A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('124', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('125', 'C4F15930CF2F40D9988BB0ABDC326A8B', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('126', 'CACACA7E9B444CF78481C0BC926BA31D', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('127', '1EBF6363110844E5824F41E8F19398EC', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('128', '8FCFFCD881CF48B4969929BB50E21D18', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('129', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('130', 'A569659CB82640328790A9D9BD6E2717', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('131', '7407B84521A944E7BF3E1442033AB9A3', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('132', '5AE83C28C6ED49B787634445EBF4C17A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('133', 'C5318ACD5C5F40259728F6D8FD15F986', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('134', '2C000246F4FA4176A1A76E2E005C6C41', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('135', 'CF76591AA4244FD0A98DE79ACBDAD624', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('136', '3D6BAE6E8B254EB1B8B863F74DBDBEF7', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('137', '5360EDCB785645379E45751033FB73EF', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('138', 'F9B106323AD9423680B9809CA79945A5', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('139', '11A78B25DBD041BFAAF4AD3FFA3BB675', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('140', 'C7BF64E927574C75B1CA995D585F960B', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('141', 'C03D606AF9AC46649244C13263C31A4A', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('142', '8203E5421FF8447689EF82C86DC079C8', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('143', 'E0854DEAE58642B1A793E5CEE0F65400', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('144', '657CA88D55804299887B4E12C56F532B', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('145', 'CE4D392671E64B8DAC27BF04C3A34078', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('146', '62ECA12E056448398064BD7399C1FD4F', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('147', '69CAD7C04A024793A701D3CBE7FEB743', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('148', '5AE6F48FDD414B058A77EF8BF44BFDB1', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('149', 'B3FD06C30BE1406C810CFC20AAEADC74', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('150', 'CFAABA7E56BF4C6CB7615F6783D24140', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('151', 'B964E2A2E63A484EBA31B5DE341D1930', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('152', '2D32014D0E0C4060A34CD8FFB386C477', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('153', '7835E18A37E4416488E2F1E7D122BC2D', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('154', '5363F435F71140A2B8CEA98504DF26EF', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('155', '0D4A5F9A0AE2411BB310D298CAAFC86D', 'L1', '0', null, null);
INSERT INTO `city_player_grade` VALUES ('156', 'E500720E7E5048C18EB8120C32107D8C', 'L1', '0', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of city_tree
-- ----------------------------
INSERT INTO `city_tree` VALUES ('1', 'system', '8A2922A66F474A0DA9B10FB4BCD59BA0', 'system', '1', '1', '2019-10-11 11:26:38');
INSERT INTO `city_tree` VALUES ('103', '8A2922A66F474A0DA9B10FB4BCD59BA0', '80F84F191FC94F32B4706C50072A35D1', 'system/670f07', '0', '1', '2019-10-22 08:37:05');
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
INSERT INTO `city_tree` VALUES ('126', '80F84F191FC94F32B4706C50072A35D1', '0D4A5F9A0AE2411BB310D298CAAFC86D', 'system/670f07/50a7ec', '', '0', '2019-10-24 12:52:39');
INSERT INTO `city_tree` VALUES ('127', '80F84F191FC94F32B4706C50072A35D1', '0D4A5F9A0AE2411BB310D298CAAFC86D', 'system/670f07/50a7ec', '', '0', '2019-10-24 12:52:39');
INSERT INTO `city_tree` VALUES ('128', '80F84F191FC94F32B4706C50072A35D1', '0D4A5F9A0AE2411BB310D298CAAFC86D', 'system/670f07/50a7ec', '', '0', '2019-10-24 12:52:39');

-- ----------------------------
-- Table structure for earn_falldown_log
-- ----------------------------
DROP TABLE IF EXISTS `earn_falldown_log`;
CREATE TABLE `earn_falldown_log` (
  `fall_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fall_invest_id` int(11) DEFAULT NULL COMMENT '掉落的项目',
  `fall_player_id` varchar(64) DEFAULT NULL COMMENT '掉落的玩家',
  `fall_amount` decimal(50,9) DEFAULT NULL COMMENT '掉落的额度',
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
  `in_amount` decimal(50,9) DEFAULT NULL COMMENT '本次收益',
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
  `amount` decimal(50,9) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
INSERT INTO `invest_allow` VALUES ('38', '0D4A5F9A0AE2411BB310D298CAAFC86D', '1', '10.0000', '2019-10-24 13:08:26');

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
  `order_amount` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '投资金额',
  `order_state` varchar(60) DEFAULT NULL COMMENT '状态(SUBSCRIBE预约，SUBSCRIBED已预约,MANAGEMENT经营中,EXTRACT可提取,FINISHED已完成,CANCEL取消,SUBSCRIBE_VERIFY_FAIL预约审核不通过，INVALID作废)',
  `order_repeat` tinyint(4) unsigned DEFAULT '0' COMMENT '是否复投（0否，1是）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  KEY `index_order_invest_id` (`order_invest_id`),
  KEY `index_order_payer_id` (`order_payer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单（投资记录）';

-- ----------------------------
-- Records of invest_order
-- ----------------------------
INSERT INTO `invest_order` VALUES ('1', '1', '80F84F191FC94F32B4706C50072A35D1', '小地摊', 'A07246C2924A415982ABE5E8C6DAD888', '30.0000', 'MANAGEMENT', '0', '2019-10-15 12:28:49', '2019-10-18 10:00:00');
INSERT INTO `invest_order` VALUES ('2', '2', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', '玩具摊', 'E38598357A4B46D4B530785E1435ED45', '100.0000', 'MANAGEMENT', '0', '2019-10-17 19:55:34', '2019-10-18 09:18:50');
INSERT INTO `invest_order` VALUES ('3', '2', 'A07246C2924A415982ABE5E8C6DAD53D', '玩具摊', 'D58A8A76A5AB41F993DF5AC87CA55576', '100.0000', 'SUBSCRIBED', '0', '2019-10-18 16:03:53', '2019-10-18 16:03:53');
INSERT INTO `invest_order` VALUES ('9', '1', '80F84F191FC94F32B4706C50072A35D1', '小地摊', 'A78C1AEA31BB44FC9DFF655D85D37953', '30.0000', 'SUBSCRIBED', '0', '2019-10-24 19:38:54', '2019-10-24 19:38:54');
INSERT INTO `invest_order` VALUES ('10', '1', '80F84F191FC94F32B4706C50072A35D1', '小地摊', 'EF88823535CE46CF9A0C2AEEB483E984', '30.0000', 'MANAGEMENT', '0', '2019-10-24 19:48:13', '2019-10-24 19:48:13');
INSERT INTO `invest_order` VALUES ('11', '2', '80F84F191FC94F32B4706C50072A35D1', '玩具摊', 'DBA7771D6379463CBA0C54E9ED1D5F7A', '100.0000', 'SUBSCRIBED', '0', '2019-10-24 19:51:22', '2019-10-24 19:51:22');
INSERT INTO `invest_order` VALUES ('12', '1', '1B86C28CDE9F4F548E6839C7C971FB2A', '小地摊', '41D614C62DD841A1A77BB7A5B6377301', '30.0000', 'MANAGEMENT', '0', '2019-10-25 10:59:15', '2019-10-25 10:59:15');

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
  `rule_rate` decimal(50,9) unsigned DEFAULT '0.0000',
  `rule_level` decimal(50,9) unsigned DEFAULT NULL COMMENT '规则优先级别',
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
  `acc_usdt` decimal(50,9) DEFAULT '0.0000' COMMENT '账户usdt额度',
  `acc_usdt_available` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT 'usdt可用金额',
  `acc_usdt_freeze` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT 'usdt冻结金额',
  `acc_mt` decimal(50,9) DEFAULT '0.0000' COMMENT '账户mt额度',
  `acc_mt_available` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT 'mt可用金额',
  `acc_mt_freeze` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT 'mt冻结金额',
  `acc_income` decimal(50,9) DEFAULT '0.0000',
  `version` int(10) unsigned DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`acc_id`) USING BTREE,
  KEY `index_acc_player_id` (`acc_player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=888888981 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家账户表';

-- ----------------------------
-- Records of player_account
-- ----------------------------
INSERT INTO `player_account` VALUES ('888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', 'system', '1080000.5000', '1070000.5000', '0.0000', '20000.0000', '20000.0000', '0.0000', '0.0000', '0', null, '2019-10-24 20:27:20');
INSERT INTO `player_account` VALUES ('888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', null, '11046.6000', '9746.6000', '400.0000', '1000.0000', '900.0000', '0.0000', '0.0000', '0', null, '2019-10-25 09:46:13');
INSERT INTO `player_account` VALUES ('888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', null, '3560.8200', '3560.8200', '30.0000', '3536.8000', '9939.8000', '0.2000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888949', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'B24425489BB4421EA0C89EFBFCD01840', null, '10.0000', '10.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, '2019-10-25 13:42:28');
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
INSERT INTO `player_account` VALUES ('888888978', '0D4A5F9A0AE2411BB310D298CAAFC86D', '9FFF5B21C61F471A85EBC559C947B6CD', null, '900.0000', '800.0000', '100.0000', '993.0000', '993.0000', '100.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888979', '7835E18A37E4416488E2F1E7D122BC2D', '38359733D5F84715B96292BD445534CF', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);
INSERT INTO `player_account` VALUES ('888888980', 'E500720E7E5048C18EB8120C32107D8C', '42B3632478284F6F9189C75CDF535AC8', null, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0', null, null);

-- ----------------------------
-- Table structure for player_account_log
-- ----------------------------
DROP TABLE IF EXISTS `player_account_log`;
CREATE TABLE `player_account_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `acc_id` int(11) NOT NULL COMMENT '交易ID号',
  `player_id` varchar(64) DEFAULT NULL COMMENT '玩家',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `amount_mt` decimal(50,9) DEFAULT '0.0000',
  `account_usdt` decimal(50,9) DEFAULT '0.0000',
  `type` varchar(50) DEFAULT NULL COMMENT '1入账2出账',
  `desc` varchar(255) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of player_account_log
-- ----------------------------
INSERT INTO `player_account_log` VALUES ('11', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '10.0000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('13', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('14', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '3.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('15', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '6.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('16', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('17', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('18', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '3.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('19', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '5.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('20', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('21', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('22', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('23', '888888949', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'B24425489BB4421EA0C89EFBFCD01840', '3.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('24', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '5.0000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('25', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('26', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('27', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('28', '888888949', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'B24425489BB4421EA0C89EFBFCD01840', '3.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('29', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '5.0000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('30', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('31', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('32', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('33', '888888949', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'B24425489BB4421EA0C89EFBFCD01840', '0.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('34', '888888950', 'C4F15930CF2F40D9988BB0ABDC326A8B', '3012DB4C2C174EA3B7D3498BF201A03D', '3.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('35', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '4.5000', '0.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('36', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('37', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('38', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('39', '888888949', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'B24425489BB4421EA0C89EFBFCD01840', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('40', '888888950', 'C4F15930CF2F40D9988BB0ABDC326A8B', '3012DB4C2C174EA3B7D3498BF201A03D', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('41', '888888952', '1EBF6363110844E5824F41E8F19398EC', '59280677EA934A249D1C2967F8DFE321', '0.0000', '3.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('42', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '4.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('43', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('44', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('45', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('46', '888888949', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'B24425489BB4421EA0C89EFBFCD01840', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('47', '888888950', 'C4F15930CF2F40D9988BB0ABDC326A8B', '3012DB4C2C174EA3B7D3498BF201A03D', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('48', '888888952', '1EBF6363110844E5824F41E8F19398EC', '59280677EA934A249D1C2967F8DFE321', '0.0000', '3.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('49', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '4.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('50', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('51', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('52', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('53', '888888949', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'B24425489BB4421EA0C89EFBFCD01840', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('54', '888888950', 'C4F15930CF2F40D9988BB0ABDC326A8B', '3012DB4C2C174EA3B7D3498BF201A03D', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('55', '888888952', '1EBF6363110844E5824F41E8F19398EC', '59280677EA934A249D1C2967F8DFE321', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('56', '888888954', '5814CCFBA45B44B8B54E9FF8AB6C1868', 'B8D591355ABF44E5A88E899F90E48284', '0.0000', '3.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('57', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '3.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('58', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '10.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('59', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '10.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('60', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '10.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('61', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '0.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('62', '888888945', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.5000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('63', '888888888', '8A2922A66F474A0DA9B10FB4BCD59BA0', '4E2EE556055042AB80E3D164E51DDD1A', '0.0000', '6.0000', '1', '获取印记收益', null);
INSERT INTO `player_account_log` VALUES ('64', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:41:15');
INSERT INTO `player_account_log` VALUES ('65', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:41:34');
INSERT INTO `player_account_log` VALUES ('66', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:47:06');
INSERT INTO `player_account_log` VALUES ('67', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:49:17');
INSERT INTO `player_account_log` VALUES ('68', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:49:17');
INSERT INTO `player_account_log` VALUES ('69', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:49:17');
INSERT INTO `player_account_log` VALUES ('70', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:50:48');
INSERT INTO `player_account_log` VALUES ('71', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:50:48');
INSERT INTO `player_account_log` VALUES ('72', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:50:48');
INSERT INTO `player_account_log` VALUES ('73', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:50:48');
INSERT INTO `player_account_log` VALUES ('74', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:50:48');
INSERT INTO `player_account_log` VALUES ('75', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:50:48');
INSERT INTO `player_account_log` VALUES ('76', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:50:48');
INSERT INTO `player_account_log` VALUES ('77', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:52:28');
INSERT INTO `player_account_log` VALUES ('78', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:52:28');
INSERT INTO `player_account_log` VALUES ('79', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:52:28');
INSERT INTO `player_account_log` VALUES ('80', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:52:28');
INSERT INTO `player_account_log` VALUES ('81', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:52:28');
INSERT INTO `player_account_log` VALUES ('82', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:52:28');
INSERT INTO `player_account_log` VALUES ('83', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:52:28');
INSERT INTO `player_account_log` VALUES ('84', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:52:28');
INSERT INTO `player_account_log` VALUES ('85', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:55:14');
INSERT INTO `player_account_log` VALUES ('86', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 09:55:28');
INSERT INTO `player_account_log` VALUES ('87', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 10:00:38');
INSERT INTO `player_account_log` VALUES ('88', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 10:00:50');
INSERT INTO `player_account_log` VALUES ('89', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 10:02:03');
INSERT INTO `player_account_log` VALUES ('90', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 10:02:03');
INSERT INTO `player_account_log` VALUES ('91', '0', '1B86C28CDE9F4F548E6839C7C971FB2A', '406A9C23E4E84EA9A45A77DC4EEF0B6F', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 10:03:05');
INSERT INTO `player_account_log` VALUES ('92', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '3.9700', '1', '收入账户多余的额度', '2019-10-25 10:42:13');
INSERT INTO `player_account_log` VALUES ('93', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '4.9700', '1', '收入账户多余的额度', '2019-10-25 11:05:09');
INSERT INTO `player_account_log` VALUES ('94', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '4.9700', '1', '收入账户多余的额度', '2019-10-25 12:32:18');
INSERT INTO `player_account_log` VALUES ('95', '0', '80F84F191FC94F32B4706C50072A35D1', 'A14A5008995242B28A8A50DA2804EA39', '0.0000', '4.9000', '1', '收入账户多余的额度', '2019-10-25 12:41:39');

-- ----------------------------
-- Table structure for player_earning
-- ----------------------------
DROP TABLE IF EXISTS `player_earning`;
CREATE TABLE `player_earning` (
  `earn_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned DEFAULT NULL COMMENT '订单id(表invest_order)',
  `earn_invest_id` int(11) DEFAULT NULL COMMENT '投资项目ID',
  `earn_player_id` varchar(64) DEFAULT NULL COMMENT '玩家ID',
  `earn_max` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '最大提取额度（预计最大收益）',
  `earn_current` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '当前获得额度',
  `earn_personal_tax` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '个人税金',
  `earn_enterprise_tax` decimal(50,9) DEFAULT '0.0000' COMMENT '企业税金',
  `earn_quota_tax` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '定额税',
  `withdrew_total` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '已提取总额',
  `withdrew_times` smallint(6) unsigned DEFAULT '0' COMMENT '已提取次数',
  `is_withdrew` tinyint(4) unsigned DEFAULT '0' COMMENT '是否可以提取(0新增,1，收益中，2可提取，3已提取)',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`earn_id`) USING BTREE,
  KEY `index_earn_player_id` (`earn_player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家收益';


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
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户扩展表（玩家）';

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
INSERT INTO `player_ext` VALUES ('165', '7835E18A37E4416488E2F1E7D122BC2D', null, null, null, null);
INSERT INTO `player_ext` VALUES ('166', '5363F435F71140A2B8CEA98504DF26EF', null, null, null, null);
INSERT INTO `player_ext` VALUES ('167', '0D4A5F9A0AE2411BB310D298CAAFC86D', null, null, null, null);
INSERT INTO `player_ext` VALUES ('168', 'E500720E7E5048C18EB8120C32107D8C', null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='好友';

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
INSERT INTO `player_friends` VALUES ('70', '1B86C28CDE9F4F548E6839C7C971FB2A', '7835E18A37E4416488E2F1E7D122BC2D', '0', null, '1', '2019-10-24 19:04:09', '2019-10-24 19:04:09');
INSERT INTO `player_friends` VALUES ('71', '0D4A5F9A0AE2411BB310D298CAAFC86D', 'E500720E7E5048C18EB8120C32107D8C', '0', null, '1', '2019-10-24 20:37:59', '2019-10-24 20:37:59');
INSERT INTO `player_friends` VALUES ('72', '0D4A5F9A0AE2411BB310D298CAAFC86D', '80F84F191FC94F32B4706C50072A35D1', '1', null, '1', '2019-10-24 20:45:24', '2019-10-24 20:45:29');

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
) ENGINE=InnoDB AUTO_INCREMENT=1452 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登录日志';

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
INSERT INTO `player_login_log` VALUES ('704', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 13:02:08');
INSERT INTO `player_login_log` VALUES ('705', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 13:02:36');
INSERT INTO `player_login_log` VALUES ('706', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 13:02:40');
INSERT INTO `player_login_log` VALUES ('707', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 13:14:18');
INSERT INTO `player_login_log` VALUES ('708', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 13:15:12');
INSERT INTO `player_login_log` VALUES ('709', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 13:15:18');
INSERT INTO `player_login_log` VALUES ('710', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 13:23:51');
INSERT INTO `player_login_log` VALUES ('711', 'BFA938AFDD784CFCA4850D7330A5407F', null, null, 'login', null, '2019-10-17 16:02:05');
INSERT INTO `player_login_log` VALUES ('712', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 16:02:10');
INSERT INTO `player_login_log` VALUES ('713', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 16:02:15');
INSERT INTO `player_login_log` VALUES ('714', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 16:03:04');
INSERT INTO `player_login_log` VALUES ('715', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 16:04:20');
INSERT INTO `player_login_log` VALUES ('716', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 16:05:19');
INSERT INTO `player_login_log` VALUES ('717', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 16:25:42');
INSERT INTO `player_login_log` VALUES ('718', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 16:25:49');
INSERT INTO `player_login_log` VALUES ('719', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'quit', null, '2019-10-17 16:40:20');
INSERT INTO `player_login_log` VALUES ('720', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 16:40:30');
INSERT INTO `player_login_log` VALUES ('721', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 16:40:36');
INSERT INTO `player_login_log` VALUES ('722', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 16:40:40');
INSERT INTO `player_login_log` VALUES ('723', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 16:40:56');
INSERT INTO `player_login_log` VALUES ('724', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 16:47:08');
INSERT INTO `player_login_log` VALUES ('725', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 16:47:22');
INSERT INTO `player_login_log` VALUES ('726', '3A309D33667D4B45AD30FC318F255E61', null, null, 'quit', null, '2019-10-17 16:52:36');
INSERT INTO `player_login_log` VALUES ('727', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 16:52:57');
INSERT INTO `player_login_log` VALUES ('728', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 16:54:15');
INSERT INTO `player_login_log` VALUES ('729', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 16:54:18');
INSERT INTO `player_login_log` VALUES ('730', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 16:54:24');
INSERT INTO `player_login_log` VALUES ('731', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 16:54:33');
INSERT INTO `player_login_log` VALUES ('732', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 16:54:52');
INSERT INTO `player_login_log` VALUES ('733', '3A309D33667D4B45AD30FC318F255E61', null, null, 'quit', null, '2019-10-17 16:54:55');
INSERT INTO `player_login_log` VALUES ('734', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 17:12:32');
INSERT INTO `player_login_log` VALUES ('735', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:14:43');
INSERT INTO `player_login_log` VALUES ('736', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'quit', null, '2019-10-17 17:15:10');
INSERT INTO `player_login_log` VALUES ('737', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 17:15:19');
INSERT INTO `player_login_log` VALUES ('738', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 17:15:23');
INSERT INTO `player_login_log` VALUES ('739', '3A309D33667D4B45AD30FC318F255E61', null, null, 'quit', null, '2019-10-17 17:15:26');
INSERT INTO `player_login_log` VALUES ('740', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:15:53');
INSERT INTO `player_login_log` VALUES ('741', '3A309D33667D4B45AD30FC318F255E61', null, null, 'quit', null, '2019-10-17 17:23:32');
INSERT INTO `player_login_log` VALUES ('742', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:23:33');
INSERT INTO `player_login_log` VALUES ('743', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:23:38');
INSERT INTO `player_login_log` VALUES ('744', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:23:38');
INSERT INTO `player_login_log` VALUES ('745', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:23:39');
INSERT INTO `player_login_log` VALUES ('746', null, null, null, 'login', null, '2019-10-17 17:24:53');
INSERT INTO `player_login_log` VALUES ('747', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:25:04');
INSERT INTO `player_login_log` VALUES ('748', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 17:25:25');
INSERT INTO `player_login_log` VALUES ('749', '3A309D33667D4B45AD30FC318F255E61', null, null, 'quit', null, '2019-10-17 17:25:33');
INSERT INTO `player_login_log` VALUES ('750', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:25:52');
INSERT INTO `player_login_log` VALUES ('751', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 17:44:06');
INSERT INTO `player_login_log` VALUES ('752', '3A309D33667D4B45AD30FC318F255E61', null, null, 'quit', null, '2019-10-17 17:48:50');
INSERT INTO `player_login_log` VALUES ('753', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 17:49:08');
INSERT INTO `player_login_log` VALUES ('754', '3A309D33667D4B45AD30FC318F255E61', null, null, 'quit', null, '2019-10-17 17:51:31');
INSERT INTO `player_login_log` VALUES ('755', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 18:06:32');
INSERT INTO `player_login_log` VALUES ('756', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 18:47:20');
INSERT INTO `player_login_log` VALUES ('757', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 19:19:26');
INSERT INTO `player_login_log` VALUES ('758', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:36');
INSERT INTO `player_login_log` VALUES ('759', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:36');
INSERT INTO `player_login_log` VALUES ('760', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:36');
INSERT INTO `player_login_log` VALUES ('761', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:37');
INSERT INTO `player_login_log` VALUES ('762', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:37');
INSERT INTO `player_login_log` VALUES ('763', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:37');
INSERT INTO `player_login_log` VALUES ('764', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:37');
INSERT INTO `player_login_log` VALUES ('765', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:37');
INSERT INTO `player_login_log` VALUES ('766', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:37');
INSERT INTO `player_login_log` VALUES ('767', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:28:38');
INSERT INTO `player_login_log` VALUES ('768', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:29:18');
INSERT INTO `player_login_log` VALUES ('769', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 19:47:44');
INSERT INTO `player_login_log` VALUES ('770', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:48:09');
INSERT INTO `player_login_log` VALUES ('771', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:48:28');
INSERT INTO `player_login_log` VALUES ('772', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:48:43');
INSERT INTO `player_login_log` VALUES ('773', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:48:47');
INSERT INTO `player_login_log` VALUES ('774', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:51:48');
INSERT INTO `player_login_log` VALUES ('775', '3A309D33667D4B45AD30FC318F255E61', null, null, 'login', null, '2019-10-17 19:52:56');
INSERT INTO `player_login_log` VALUES ('776', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 19:53:12');
INSERT INTO `player_login_log` VALUES ('777', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 19:53:18');
INSERT INTO `player_login_log` VALUES ('778', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 19:53:19');
INSERT INTO `player_login_log` VALUES ('779', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 19:53:45');
INSERT INTO `player_login_log` VALUES ('780', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 20:24:30');
INSERT INTO `player_login_log` VALUES ('781', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 20:45:12');
INSERT INTO `player_login_log` VALUES ('782', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 20:47:32');
INSERT INTO `player_login_log` VALUES ('783', null, null, null, 'login', null, '2019-10-17 20:49:28');
INSERT INTO `player_login_log` VALUES ('784', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 20:49:36');
INSERT INTO `player_login_log` VALUES ('785', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 20:50:39');
INSERT INTO `player_login_log` VALUES ('786', 'BE273C11BFA1413189BBFB773BBB3188', null, null, 'login', null, '2019-10-17 20:51:02');
INSERT INTO `player_login_log` VALUES ('787', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 20:53:07');
INSERT INTO `player_login_log` VALUES ('788', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:00:35');
INSERT INTO `player_login_log` VALUES ('789', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:00:35');
INSERT INTO `player_login_log` VALUES ('790', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:02:11');
INSERT INTO `player_login_log` VALUES ('791', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:02:11');
INSERT INTO `player_login_log` VALUES ('792', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:02:38');
INSERT INTO `player_login_log` VALUES ('793', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:04:26');
INSERT INTO `player_login_log` VALUES ('794', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:08:34');
INSERT INTO `player_login_log` VALUES ('795', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:08:42');
INSERT INTO `player_login_log` VALUES ('796', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:13:16');
INSERT INTO `player_login_log` VALUES ('797', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:13:17');
INSERT INTO `player_login_log` VALUES ('798', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:14:03');
INSERT INTO `player_login_log` VALUES ('799', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:15:31');
INSERT INTO `player_login_log` VALUES ('800', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:15:31');
INSERT INTO `player_login_log` VALUES ('801', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:16:19');
INSERT INTO `player_login_log` VALUES ('802', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 21:18:01');
INSERT INTO `player_login_log` VALUES ('803', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:19:20');
INSERT INTO `player_login_log` VALUES ('804', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:21:50');
INSERT INTO `player_login_log` VALUES ('805', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:28:24');
INSERT INTO `player_login_log` VALUES ('806', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:34:50');
INSERT INTO `player_login_log` VALUES ('807', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:38:20');
INSERT INTO `player_login_log` VALUES ('808', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 21:46:12');
INSERT INTO `player_login_log` VALUES ('809', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 21:46:58');
INSERT INTO `player_login_log` VALUES ('810', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 21:56:39');
INSERT INTO `player_login_log` VALUES ('811', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-17 21:57:34');
INSERT INTO `player_login_log` VALUES ('812', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'quit', null, '2019-10-17 22:00:22');
INSERT INTO `player_login_log` VALUES ('813', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:01:43');
INSERT INTO `player_login_log` VALUES ('814', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:24:34');
INSERT INTO `player_login_log` VALUES ('815', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 22:27:02');
INSERT INTO `player_login_log` VALUES ('816', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:27:52');
INSERT INTO `player_login_log` VALUES ('817', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:28:04');
INSERT INTO `player_login_log` VALUES ('818', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:28:18');
INSERT INTO `player_login_log` VALUES ('819', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 22:32:12');
INSERT INTO `player_login_log` VALUES ('820', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:32:26');
INSERT INTO `player_login_log` VALUES ('821', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 22:33:49');
INSERT INTO `player_login_log` VALUES ('822', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:34:08');
INSERT INTO `player_login_log` VALUES ('823', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 22:35:20');
INSERT INTO `player_login_log` VALUES ('824', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:35:39');
INSERT INTO `player_login_log` VALUES ('825', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:46:11');
INSERT INTO `player_login_log` VALUES ('826', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 22:53:30');
INSERT INTO `player_login_log` VALUES ('827', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:53:30');
INSERT INTO `player_login_log` VALUES ('828', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:57:10');
INSERT INTO `player_login_log` VALUES ('829', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-17 22:59:01');
INSERT INTO `player_login_log` VALUES ('830', null, null, null, 'login', null, '2019-10-17 22:59:29');
INSERT INTO `player_login_log` VALUES ('831', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 22:59:38');
INSERT INTO `player_login_log` VALUES ('832', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 23:10:09');
INSERT INTO `player_login_log` VALUES ('833', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 23:12:29');
INSERT INTO `player_login_log` VALUES ('834', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-17 23:14:19');
INSERT INTO `player_login_log` VALUES ('835', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 09:49:12');
INSERT INTO `player_login_log` VALUES ('836', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 09:49:16');
INSERT INTO `player_login_log` VALUES ('837', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 09:49:36');
INSERT INTO `player_login_log` VALUES ('838', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 09:50:06');
INSERT INTO `player_login_log` VALUES ('839', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 09:50:43');
INSERT INTO `player_login_log` VALUES ('840', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 09:51:46');
INSERT INTO `player_login_log` VALUES ('841', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 09:52:44');
INSERT INTO `player_login_log` VALUES ('842', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 09:56:31');
INSERT INTO `player_login_log` VALUES ('843', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 10:05:07');
INSERT INTO `player_login_log` VALUES ('844', '7D09619AC1054CF1B8380F6ED098F797', null, null, 'login', null, '2019-10-18 10:05:31');
INSERT INTO `player_login_log` VALUES ('845', '7D09619AC1054CF1B8380F6ED098F797', null, null, 'login', null, '2019-10-18 10:05:35');
INSERT INTO `player_login_log` VALUES ('846', '7D09619AC1054CF1B8380F6ED098F797', null, null, 'login', null, '2019-10-18 10:05:37');
INSERT INTO `player_login_log` VALUES ('847', '7D09619AC1054CF1B8380F6ED098F797', null, null, 'login', null, '2019-10-18 10:05:39');
INSERT INTO `player_login_log` VALUES ('848', '7D09619AC1054CF1B8380F6ED098F797', null, null, 'login', null, '2019-10-18 10:06:18');
INSERT INTO `player_login_log` VALUES ('849', '7D09619AC1054CF1B8380F6ED098F797', null, null, 'login', null, '2019-10-18 10:06:34');
INSERT INTO `player_login_log` VALUES ('850', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 10:11:04');
INSERT INTO `player_login_log` VALUES ('851', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 10:24:02');
INSERT INTO `player_login_log` VALUES ('852', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 11:24:46');
INSERT INTO `player_login_log` VALUES ('853', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 11:25:29');
INSERT INTO `player_login_log` VALUES ('854', null, null, null, 'login', null, '2019-10-18 11:46:59');
INSERT INTO `player_login_log` VALUES ('855', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 11:51:00');
INSERT INTO `player_login_log` VALUES ('856', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 11:52:28');
INSERT INTO `player_login_log` VALUES ('857', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 11:54:29');
INSERT INTO `player_login_log` VALUES ('858', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 11:55:18');
INSERT INTO `player_login_log` VALUES ('859', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 11:56:00');
INSERT INTO `player_login_log` VALUES ('860', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 11:57:01');
INSERT INTO `player_login_log` VALUES ('861', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 11:59:53');
INSERT INTO `player_login_log` VALUES ('862', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 12:00:50');
INSERT INTO `player_login_log` VALUES ('863', null, null, null, 'login', null, '2019-10-18 12:13:47');
INSERT INTO `player_login_log` VALUES ('864', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:03:18');
INSERT INTO `player_login_log` VALUES ('865', null, null, null, 'login', null, '2019-10-18 13:03:37');
INSERT INTO `player_login_log` VALUES ('866', null, null, null, 'login', null, '2019-10-18 13:04:12');
INSERT INTO `player_login_log` VALUES ('867', null, null, null, 'login', null, '2019-10-18 13:04:31');
INSERT INTO `player_login_log` VALUES ('868', null, null, null, 'login', null, '2019-10-18 13:04:45');
INSERT INTO `player_login_log` VALUES ('869', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:05:41');
INSERT INTO `player_login_log` VALUES ('870', null, null, null, 'login', null, '2019-10-18 13:05:54');
INSERT INTO `player_login_log` VALUES ('871', null, null, null, 'login', null, '2019-10-18 13:06:12');
INSERT INTO `player_login_log` VALUES ('872', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:07:01');
INSERT INTO `player_login_log` VALUES ('873', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:08:10');
INSERT INTO `player_login_log` VALUES ('874', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:10:24');
INSERT INTO `player_login_log` VALUES ('875', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:11:07');
INSERT INTO `player_login_log` VALUES ('876', '498CB905328E4F11A81A201557CF36B9', null, null, 'login', null, '2019-10-18 13:11:27');
INSERT INTO `player_login_log` VALUES ('877', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:13:26');
INSERT INTO `player_login_log` VALUES ('878', null, null, null, 'login', null, '2019-10-18 13:14:54');
INSERT INTO `player_login_log` VALUES ('879', null, null, null, 'login', null, '2019-10-18 13:15:26');
INSERT INTO `player_login_log` VALUES ('880', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:15:49');
INSERT INTO `player_login_log` VALUES ('881', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:16:39');
INSERT INTO `player_login_log` VALUES ('882', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:17:26');
INSERT INTO `player_login_log` VALUES ('883', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:18:54');
INSERT INTO `player_login_log` VALUES ('884', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:19:40');
INSERT INTO `player_login_log` VALUES ('885', null, null, null, 'login', null, '2019-10-18 13:27:04');
INSERT INTO `player_login_log` VALUES ('886', null, null, null, 'login', null, '2019-10-18 13:27:12');
INSERT INTO `player_login_log` VALUES ('887', null, null, null, 'login', null, '2019-10-18 13:27:28');
INSERT INTO `player_login_log` VALUES ('888', null, null, null, 'login', null, '2019-10-18 13:27:35');
INSERT INTO `player_login_log` VALUES ('889', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:27:46');
INSERT INTO `player_login_log` VALUES ('890', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:28:07');
INSERT INTO `player_login_log` VALUES ('891', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:28:40');
INSERT INTO `player_login_log` VALUES ('892', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:28:43');
INSERT INTO `player_login_log` VALUES ('893', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:29:31');
INSERT INTO `player_login_log` VALUES ('894', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:30:01');
INSERT INTO `player_login_log` VALUES ('895', null, null, null, 'login', null, '2019-10-18 13:32:31');
INSERT INTO `player_login_log` VALUES ('896', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:33:01');
INSERT INTO `player_login_log` VALUES ('897', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:33:38');
INSERT INTO `player_login_log` VALUES ('898', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:33:43');
INSERT INTO `player_login_log` VALUES ('899', null, null, null, 'login', null, '2019-10-18 13:41:03');
INSERT INTO `player_login_log` VALUES ('900', null, null, null, 'login', null, '2019-10-18 13:41:12');
INSERT INTO `player_login_log` VALUES ('901', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:41:25');
INSERT INTO `player_login_log` VALUES ('902', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:41:32');
INSERT INTO `player_login_log` VALUES ('903', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:41:55');
INSERT INTO `player_login_log` VALUES ('904', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:43:59');
INSERT INTO `player_login_log` VALUES ('905', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:44:20');
INSERT INTO `player_login_log` VALUES ('906', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:44:54');
INSERT INTO `player_login_log` VALUES ('907', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:44:57');
INSERT INTO `player_login_log` VALUES ('908', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:45:48');
INSERT INTO `player_login_log` VALUES ('909', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:45:51');
INSERT INTO `player_login_log` VALUES ('910', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:46:04');
INSERT INTO `player_login_log` VALUES ('911', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:47:33');
INSERT INTO `player_login_log` VALUES ('912', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:47:37');
INSERT INTO `player_login_log` VALUES ('913', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:50:27');
INSERT INTO `player_login_log` VALUES ('914', '498CB905328E4F11A81A201557CF36B9', null, null, 'login', null, '2019-10-18 13:51:05');
INSERT INTO `player_login_log` VALUES ('915', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 13:54:20');
INSERT INTO `player_login_log` VALUES ('916', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 13:54:53');
INSERT INTO `player_login_log` VALUES ('917', null, null, null, 'login', null, '2019-10-18 14:08:17');
INSERT INTO `player_login_log` VALUES ('918', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:08:38');
INSERT INTO `player_login_log` VALUES ('919', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 14:08:59');
INSERT INTO `player_login_log` VALUES ('920', 'BE273C11BFA1413189BBFB773BBB3188', null, null, 'login', null, '2019-10-18 14:09:56');
INSERT INTO `player_login_log` VALUES ('921', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:10:10');
INSERT INTO `player_login_log` VALUES ('922', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 14:10:32');
INSERT INTO `player_login_log` VALUES ('923', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:10:52');
INSERT INTO `player_login_log` VALUES ('924', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:11:35');
INSERT INTO `player_login_log` VALUES ('925', 'BE273C11BFA1413189BBFB773BBB3188', null, null, 'login', null, '2019-10-18 14:17:32');
INSERT INTO `player_login_log` VALUES ('926', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:30:03');
INSERT INTO `player_login_log` VALUES ('927', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:30:03');
INSERT INTO `player_login_log` VALUES ('928', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:30:45');
INSERT INTO `player_login_log` VALUES ('929', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:32:00');
INSERT INTO `player_login_log` VALUES ('930', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:32:18');
INSERT INTO `player_login_log` VALUES ('931', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:33:19');
INSERT INTO `player_login_log` VALUES ('932', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:33:33');
INSERT INTO `player_login_log` VALUES ('933', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:34:25');
INSERT INTO `player_login_log` VALUES ('934', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:38:22');
INSERT INTO `player_login_log` VALUES ('935', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:38:30');
INSERT INTO `player_login_log` VALUES ('936', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:38:34');
INSERT INTO `player_login_log` VALUES ('937', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:38:44');
INSERT INTO `player_login_log` VALUES ('938', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:38:55');
INSERT INTO `player_login_log` VALUES ('939', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:38:57');
INSERT INTO `player_login_log` VALUES ('940', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 14:39:05');
INSERT INTO `player_login_log` VALUES ('941', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:39:11');
INSERT INTO `player_login_log` VALUES ('942', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:39:21');
INSERT INTO `player_login_log` VALUES ('943', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:39:34');
INSERT INTO `player_login_log` VALUES ('944', null, null, null, 'login', null, '2019-10-18 14:40:26');
INSERT INTO `player_login_log` VALUES ('945', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:40:37');
INSERT INTO `player_login_log` VALUES ('946', null, null, null, 'login', null, '2019-10-18 14:40:58');
INSERT INTO `player_login_log` VALUES ('947', null, null, null, 'login', null, '2019-10-18 14:41:01');
INSERT INTO `player_login_log` VALUES ('948', null, null, null, 'login', null, '2019-10-18 14:41:22');
INSERT INTO `player_login_log` VALUES ('949', null, null, null, 'login', null, '2019-10-18 14:41:55');
INSERT INTO `player_login_log` VALUES ('950', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:42:05');
INSERT INTO `player_login_log` VALUES ('951', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:42:22');
INSERT INTO `player_login_log` VALUES ('952', null, null, null, 'login', null, '2019-10-18 14:44:02');
INSERT INTO `player_login_log` VALUES ('953', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:44:08');
INSERT INTO `player_login_log` VALUES ('954', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:44:20');
INSERT INTO `player_login_log` VALUES ('955', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 14:45:29');
INSERT INTO `player_login_log` VALUES ('956', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:17:07');
INSERT INTO `player_login_log` VALUES ('957', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:18:35');
INSERT INTO `player_login_log` VALUES ('958', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:18:37');
INSERT INTO `player_login_log` VALUES ('959', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:18:38');
INSERT INTO `player_login_log` VALUES ('960', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:18:42');
INSERT INTO `player_login_log` VALUES ('961', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:18:49');
INSERT INTO `player_login_log` VALUES ('962', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:18:49');
INSERT INTO `player_login_log` VALUES ('963', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:46:00');
INSERT INTO `player_login_log` VALUES ('964', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:46:59');
INSERT INTO `player_login_log` VALUES ('965', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:47:26');
INSERT INTO `player_login_log` VALUES ('966', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-18 15:48:00');
INSERT INTO `player_login_log` VALUES ('967', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:50:46');
INSERT INTO `player_login_log` VALUES ('968', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:55:59');
INSERT INTO `player_login_log` VALUES ('969', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 15:56:26');
INSERT INTO `player_login_log` VALUES ('970', '6563530126664673AD9F40A6A4A5D0EA', null, null, 'login', null, '2019-10-18 15:58:05');
INSERT INTO `player_login_log` VALUES ('971', '6563530126664673AD9F40A6A4A5D0EA', null, null, 'login', null, '2019-10-18 15:58:06');
INSERT INTO `player_login_log` VALUES ('972', '6563530126664673AD9F40A6A4A5D0EA', null, null, 'login', null, '2019-10-18 15:58:08');
INSERT INTO `player_login_log` VALUES ('973', '6563530126664673AD9F40A6A4A5D0EA', null, null, 'login', null, '2019-10-18 15:58:15');
INSERT INTO `player_login_log` VALUES ('974', '6563530126664673AD9F40A6A4A5D0EA', null, null, 'login', null, '2019-10-18 15:58:16');
INSERT INTO `player_login_log` VALUES ('975', '7D09619AC1054CF1B8380F6ED098F797', null, null, 'login', null, '2019-10-18 15:58:58');
INSERT INTO `player_login_log` VALUES ('976', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('977', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('978', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('979', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('980', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('981', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('982', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('983', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('984', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('985', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('986', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('987', null, null, null, 'login', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('988', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-18 16:19:05');
INSERT INTO `player_login_log` VALUES ('989', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 16:31:08');
INSERT INTO `player_login_log` VALUES ('990', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 16:39:24');
INSERT INTO `player_login_log` VALUES ('991', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 16:39:34');
INSERT INTO `player_login_log` VALUES ('992', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'quit', null, '2019-10-18 16:39:38');
INSERT INTO `player_login_log` VALUES ('993', '54793C2136784F348D1A14C7B2A56F3E', null, null, 'login', null, '2019-10-18 16:41:57');
INSERT INTO `player_login_log` VALUES ('994', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 16:53:50');
INSERT INTO `player_login_log` VALUES ('995', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 16:54:08');
INSERT INTO `player_login_log` VALUES ('996', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 16:54:28');
INSERT INTO `player_login_log` VALUES ('997', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 16:54:33');
INSERT INTO `player_login_log` VALUES ('998', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 16:57:46');
INSERT INTO `player_login_log` VALUES ('999', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 16:59:38');
INSERT INTO `player_login_log` VALUES ('1000', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 17:12:17');
INSERT INTO `player_login_log` VALUES ('1001', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 17:14:01');
INSERT INTO `player_login_log` VALUES ('1002', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 17:21:15');
INSERT INTO `player_login_log` VALUES ('1003', '45FB476610184C43861705520B58C436', null, null, 'login', null, '2019-10-18 17:39:29');
INSERT INTO `player_login_log` VALUES ('1004', '45FB476610184C43861705520B58C436', null, null, 'login', null, '2019-10-18 17:39:50');
INSERT INTO `player_login_log` VALUES ('1005', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 17:40:17');
INSERT INTO `player_login_log` VALUES ('1006', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 17:40:57');
INSERT INTO `player_login_log` VALUES ('1007', '45FB476610184C43861705520B58C436', null, null, 'login', null, '2019-10-18 17:41:15');
INSERT INTO `player_login_log` VALUES ('1008', '45FB476610184C43861705520B58C436', null, null, 'login', null, '2019-10-18 17:41:22');
INSERT INTO `player_login_log` VALUES ('1009', '45FB476610184C43861705520B58C436', null, null, 'login', null, '2019-10-18 17:50:35');
INSERT INTO `player_login_log` VALUES ('1010', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 17:50:57');
INSERT INTO `player_login_log` VALUES ('1011', '45FB476610184C43861705520B58C436', null, null, 'login', null, '2019-10-18 17:51:05');
INSERT INTO `player_login_log` VALUES ('1012', '45FB476610184C43861705520B58C436', null, null, 'login', null, '2019-10-18 17:51:19');
INSERT INTO `player_login_log` VALUES ('1013', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 17:54:17');
INSERT INTO `player_login_log` VALUES ('1014', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 17:56:22');
INSERT INTO `player_login_log` VALUES ('1015', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 18:02:07');
INSERT INTO `player_login_log` VALUES ('1016', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 18:11:38');
INSERT INTO `player_login_log` VALUES ('1017', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 18:19:21');
INSERT INTO `player_login_log` VALUES ('1018', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 18:19:32');
INSERT INTO `player_login_log` VALUES ('1019', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 18:21:20');
INSERT INTO `player_login_log` VALUES ('1020', null, null, null, 'login', null, '2019-10-18 18:21:39');
INSERT INTO `player_login_log` VALUES ('1021', '9D7B73FFE80240E78D283417BD90F6E5', null, null, 'login', null, '2019-10-18 18:22:16');
INSERT INTO `player_login_log` VALUES ('1022', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 18:32:45');
INSERT INTO `player_login_log` VALUES ('1023', 'EC074F6206594D639B860924F868F07A', null, null, 'login', null, '2019-10-18 18:33:14');
INSERT INTO `player_login_log` VALUES ('1024', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:33:56');
INSERT INTO `player_login_log` VALUES ('1025', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:34:00');
INSERT INTO `player_login_log` VALUES ('1026', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:34:02');
INSERT INTO `player_login_log` VALUES ('1027', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:34:03');
INSERT INTO `player_login_log` VALUES ('1028', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:34:06');
INSERT INTO `player_login_log` VALUES ('1029', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:34:06');
INSERT INTO `player_login_log` VALUES ('1030', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:34:08');
INSERT INTO `player_login_log` VALUES ('1031', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:34:15');
INSERT INTO `player_login_log` VALUES ('1032', '9D6E9096AEA648DAA3825AD50A4388A7', null, null, 'login', null, '2019-10-18 18:34:19');
INSERT INTO `player_login_log` VALUES ('1033', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 18:42:31');
INSERT INTO `player_login_log` VALUES ('1034', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 18:42:36');
INSERT INTO `player_login_log` VALUES ('1035', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 18:42:43');
INSERT INTO `player_login_log` VALUES ('1036', '3FE02519657C4DDBB70D605038AB6C40', null, null, 'login', null, '2019-10-18 18:43:29');
INSERT INTO `player_login_log` VALUES ('1037', 'D4B57A611CB14C0585935838E2C03F85', null, null, 'login', null, '2019-10-18 18:45:41');
INSERT INTO `player_login_log` VALUES ('1038', 'D4B57A611CB14C0585935838E2C03F85', null, null, 'login', null, '2019-10-18 18:45:46');
INSERT INTO `player_login_log` VALUES ('1039', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:13:08');
INSERT INTO `player_login_log` VALUES ('1040', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:20:31');
INSERT INTO `player_login_log` VALUES ('1041', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 19:35:20');
INSERT INTO `player_login_log` VALUES ('1042', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 19:36:10');
INSERT INTO `player_login_log` VALUES ('1043', null, null, null, 'login', null, '2019-10-18 19:48:17');
INSERT INTO `player_login_log` VALUES ('1044', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 19:48:23');
INSERT INTO `player_login_log` VALUES ('1045', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:49:10');
INSERT INTO `player_login_log` VALUES ('1046', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:49:15');
INSERT INTO `player_login_log` VALUES ('1047', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:49:34');
INSERT INTO `player_login_log` VALUES ('1048', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:49:41');
INSERT INTO `player_login_log` VALUES ('1049', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:49:44');
INSERT INTO `player_login_log` VALUES ('1050', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:51:02');
INSERT INTO `player_login_log` VALUES ('1051', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 19:51:04');
INSERT INTO `player_login_log` VALUES ('1052', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:51:48');
INSERT INTO `player_login_log` VALUES ('1053', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 19:52:49');
INSERT INTO `player_login_log` VALUES ('1054', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 19:53:58');
INSERT INTO `player_login_log` VALUES ('1055', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 20:08:01');
INSERT INTO `player_login_log` VALUES ('1056', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 20:17:42');
INSERT INTO `player_login_log` VALUES ('1057', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 20:18:24');
INSERT INTO `player_login_log` VALUES ('1058', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 20:23:06');
INSERT INTO `player_login_log` VALUES ('1059', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 20:24:02');
INSERT INTO `player_login_log` VALUES ('1060', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 20:27:24');
INSERT INTO `player_login_log` VALUES ('1061', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 20:28:59');
INSERT INTO `player_login_log` VALUES ('1062', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 20:32:42');
INSERT INTO `player_login_log` VALUES ('1063', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 21:20:47');
INSERT INTO `player_login_log` VALUES ('1064', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 21:20:54');
INSERT INTO `player_login_log` VALUES ('1065', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 21:21:22');
INSERT INTO `player_login_log` VALUES ('1066', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 21:23:16');
INSERT INTO `player_login_log` VALUES ('1067', 'D425E00EAED04780A4ED091339483972', null, null, 'login', null, '2019-10-18 21:24:19');
INSERT INTO `player_login_log` VALUES ('1068', 'D425E00EAED04780A4ED091339483972', null, null, 'login', null, '2019-10-18 21:24:49');
INSERT INTO `player_login_log` VALUES ('1069', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 21:26:57');
INSERT INTO `player_login_log` VALUES ('1070', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 21:27:04');
INSERT INTO `player_login_log` VALUES ('1071', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 21:27:20');
INSERT INTO `player_login_log` VALUES ('1072', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 21:27:21');
INSERT INTO `player_login_log` VALUES ('1073', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 21:27:23');
INSERT INTO `player_login_log` VALUES ('1074', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 21:27:23');
INSERT INTO `player_login_log` VALUES ('1075', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-18 21:27:41');
INSERT INTO `player_login_log` VALUES ('1076', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 21:28:02');
INSERT INTO `player_login_log` VALUES ('1077', 'D6EDA06FDF654A46BC8299A05DDFF591', null, null, 'login', null, '2019-10-18 21:31:46');
INSERT INTO `player_login_log` VALUES ('1078', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-18 22:31:21');
INSERT INTO `player_login_log` VALUES ('1079', null, null, null, 'login', null, '2019-10-19 10:09:58');
INSERT INTO `player_login_log` VALUES ('1080', null, null, null, 'login', null, '2019-10-19 10:10:10');
INSERT INTO `player_login_log` VALUES ('1081', 'C6BFE29AFBF84601BA9C5FAE941BEB42', null, null, 'login', null, '2019-10-19 10:12:12');
INSERT INTO `player_login_log` VALUES ('1082', 'C6BFE29AFBF84601BA9C5FAE941BEB42', null, null, 'login', null, '2019-10-19 10:12:20');
INSERT INTO `player_login_log` VALUES ('1083', 'C6BFE29AFBF84601BA9C5FAE941BEB42', null, null, 'quit', null, '2019-10-19 10:14:26');
INSERT INTO `player_login_log` VALUES ('1084', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 10:14:44');
INSERT INTO `player_login_log` VALUES ('1085', '60F751762AD14E1E9D838295EA4EF7EF', null, null, 'login', null, '2019-10-19 10:15:11');
INSERT INTO `player_login_log` VALUES ('1086', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 10:22:52');
INSERT INTO `player_login_log` VALUES ('1087', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 10:36:27');
INSERT INTO `player_login_log` VALUES ('1088', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-19 10:49:02');
INSERT INTO `player_login_log` VALUES ('1089', '080C405C21594F9A9F972EE70244A476', null, null, 'login', null, '2019-10-19 10:50:04');
INSERT INTO `player_login_log` VALUES ('1090', null, null, null, 'login', null, '2019-10-19 13:04:57');
INSERT INTO `player_login_log` VALUES ('1091', null, null, null, 'login', null, '2019-10-19 13:04:57');
INSERT INTO `player_login_log` VALUES ('1092', null, null, null, 'login', null, '2019-10-19 13:04:59');
INSERT INTO `player_login_log` VALUES ('1093', null, null, null, 'login', null, '2019-10-19 13:05:45');
INSERT INTO `player_login_log` VALUES ('1094', 'C35793EC2300482E9B4EB547469178E1', null, null, 'login', null, '2019-10-19 13:06:55');
INSERT INTO `player_login_log` VALUES ('1095', 'C35793EC2300482E9B4EB547469178E1', null, null, 'quit', null, '2019-10-19 13:14:02');
INSERT INTO `player_login_log` VALUES ('1096', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 13:53:49');
INSERT INTO `player_login_log` VALUES ('1097', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 14:02:07');
INSERT INTO `player_login_log` VALUES ('1098', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 14:41:01');
INSERT INTO `player_login_log` VALUES ('1099', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 14:47:12');
INSERT INTO `player_login_log` VALUES ('1100', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 15:38:47');
INSERT INTO `player_login_log` VALUES ('1101', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 15:50:21');
INSERT INTO `player_login_log` VALUES ('1102', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 16:00:49');
INSERT INTO `player_login_log` VALUES ('1103', null, null, null, 'login', null, '2019-10-19 17:03:06');
INSERT INTO `player_login_log` VALUES ('1104', null, null, null, 'login', null, '2019-10-19 17:03:20');
INSERT INTO `player_login_log` VALUES ('1105', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 17:03:40');
INSERT INTO `player_login_log` VALUES ('1106', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 17:16:52');
INSERT INTO `player_login_log` VALUES ('1107', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 17:16:59');
INSERT INTO `player_login_log` VALUES ('1108', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 17:26:45');
INSERT INTO `player_login_log` VALUES ('1109', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 17:26:45');
INSERT INTO `player_login_log` VALUES ('1110', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 17:26:45');
INSERT INTO `player_login_log` VALUES ('1111', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 17:26:45');
INSERT INTO `player_login_log` VALUES ('1112', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 17:26:45');
INSERT INTO `player_login_log` VALUES ('1113', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 17:26:45');
INSERT INTO `player_login_log` VALUES ('1114', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 17:26:45');
INSERT INTO `player_login_log` VALUES ('1115', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'quit', null, '2019-10-19 17:33:03');
INSERT INTO `player_login_log` VALUES ('1116', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 17:33:14');
INSERT INTO `player_login_log` VALUES ('1117', '63424C4C9A064AC3B066D47F4EE31840', null, null, 'login', null, '2019-10-19 17:53:46');
INSERT INTO `player_login_log` VALUES ('1118', '63424C4C9A064AC3B066D47F4EE31840', null, null, 'login', null, '2019-10-19 18:15:27');
INSERT INTO `player_login_log` VALUES ('1119', '63424C4C9A064AC3B066D47F4EE31840', null, null, 'login', null, '2019-10-19 18:16:21');
INSERT INTO `player_login_log` VALUES ('1120', '63424C4C9A064AC3B066D47F4EE31840', null, null, 'login', null, '2019-10-19 18:17:19');
INSERT INTO `player_login_log` VALUES ('1121', '63424C4C9A064AC3B066D47F4EE31840', null, null, 'login', null, '2019-10-19 18:20:10');
INSERT INTO `player_login_log` VALUES ('1122', '63424C4C9A064AC3B066D47F4EE31840', null, null, 'login', null, '2019-10-19 18:20:55');
INSERT INTO `player_login_log` VALUES ('1123', '63424C4C9A064AC3B066D47F4EE31840', null, null, 'login', null, '2019-10-19 18:22:59');
INSERT INTO `player_login_log` VALUES ('1124', '63424C4C9A064AC3B066D47F4EE31840', null, null, 'login', null, '2019-10-19 18:23:48');
INSERT INTO `player_login_log` VALUES ('1125', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 18:25:45');
INSERT INTO `player_login_log` VALUES ('1126', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'quit', null, '2019-10-19 18:27:36');
INSERT INTO `player_login_log` VALUES ('1127', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 18:27:43');
INSERT INTO `player_login_log` VALUES ('1128', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'quit', null, '2019-10-19 18:30:45');
INSERT INTO `player_login_log` VALUES ('1129', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 18:31:37');
INSERT INTO `player_login_log` VALUES ('1130', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 18:33:43');
INSERT INTO `player_login_log` VALUES ('1131', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 18:34:32');
INSERT INTO `player_login_log` VALUES ('1132', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 18:36:06');
INSERT INTO `player_login_log` VALUES ('1133', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:02:21');
INSERT INTO `player_login_log` VALUES ('1134', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:24:54');
INSERT INTO `player_login_log` VALUES ('1135', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:25:48');
INSERT INTO `player_login_log` VALUES ('1136', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:26:28');
INSERT INTO `player_login_log` VALUES ('1137', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:26:37');
INSERT INTO `player_login_log` VALUES ('1138', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:27:01');
INSERT INTO `player_login_log` VALUES ('1139', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:38:38');
INSERT INTO `player_login_log` VALUES ('1140', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:42:05');
INSERT INTO `player_login_log` VALUES ('1141', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:45:15');
INSERT INTO `player_login_log` VALUES ('1142', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:45:37');
INSERT INTO `player_login_log` VALUES ('1143', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:46:18');
INSERT INTO `player_login_log` VALUES ('1144', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:54:19');
INSERT INTO `player_login_log` VALUES ('1145', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:54:19');
INSERT INTO `player_login_log` VALUES ('1146', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:54:19');
INSERT INTO `player_login_log` VALUES ('1147', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:54:19');
INSERT INTO `player_login_log` VALUES ('1148', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:54:19');
INSERT INTO `player_login_log` VALUES ('1149', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:54:19');
INSERT INTO `player_login_log` VALUES ('1150', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:54:28');
INSERT INTO `player_login_log` VALUES ('1151', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:54:35');
INSERT INTO `player_login_log` VALUES ('1152', '916A1597AB5D48EFA2ECCD75A2E255D9', null, null, 'login', null, '2019-10-19 19:56:45');
INSERT INTO `player_login_log` VALUES ('1153', '8A4BFE6587084C148A2D6E7060103005', null, null, 'login', null, '2019-10-19 20:54:46');
INSERT INTO `player_login_log` VALUES ('1154', null, null, null, 'login', null, '2019-10-19 21:08:33');
INSERT INTO `player_login_log` VALUES ('1155', null, null, null, 'login', null, '2019-10-19 21:08:52');
INSERT INTO `player_login_log` VALUES ('1156', '6BD288B9EA4C486F9D1CDD05D46C5614', null, null, 'login', null, '2019-10-19 21:09:43');
INSERT INTO `player_login_log` VALUES ('1157', '6BD288B9EA4C486F9D1CDD05D46C5614', null, null, 'quit', null, '2019-10-19 21:10:33');
INSERT INTO `player_login_log` VALUES ('1158', '2AB07B239AEA4F52AC99CC8646478E56', null, null, 'login', null, '2019-10-19 21:11:14');
INSERT INTO `player_login_log` VALUES ('1159', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 23:11:17');
INSERT INTO `player_login_log` VALUES ('1160', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 23:11:17');
INSERT INTO `player_login_log` VALUES ('1161', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 23:11:17');
INSERT INTO `player_login_log` VALUES ('1162', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 23:11:17');
INSERT INTO `player_login_log` VALUES ('1163', 'A07246C2924A415982ABE5E8C6DAD53D', null, null, 'login', null, '2019-10-19 23:13:22');
INSERT INTO `player_login_log` VALUES ('1164', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-21 17:51:33');
INSERT INTO `player_login_log` VALUES ('1165', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'quit', null, '2019-10-21 18:00:35');
INSERT INTO `player_login_log` VALUES ('1166', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-21 18:00:44');
INSERT INTO `player_login_log` VALUES ('1167', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-21 18:51:25');
INSERT INTO `player_login_log` VALUES ('1168', null, null, null, 'login', null, '2019-10-21 19:03:19');
INSERT INTO `player_login_log` VALUES ('1169', null, null, null, 'login', null, '2019-10-21 19:03:20');
INSERT INTO `player_login_log` VALUES ('1170', null, null, null, 'login', null, '2019-10-21 19:03:24');
INSERT INTO `player_login_log` VALUES ('1171', null, null, null, 'login', null, '2019-10-21 19:03:24');
INSERT INTO `player_login_log` VALUES ('1172', null, null, null, 'login', null, '2019-10-21 19:03:24');
INSERT INTO `player_login_log` VALUES ('1173', null, null, null, 'login', null, '2019-10-21 19:03:24');
INSERT INTO `player_login_log` VALUES ('1174', null, null, null, 'login', null, '2019-10-21 19:03:24');
INSERT INTO `player_login_log` VALUES ('1175', null, null, null, 'login', null, '2019-10-21 19:03:24');
INSERT INTO `player_login_log` VALUES ('1176', null, null, null, 'login', null, '2019-10-21 19:03:24');
INSERT INTO `player_login_log` VALUES ('1177', null, null, null, 'login', null, '2019-10-21 19:03:27');
INSERT INTO `player_login_log` VALUES ('1178', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1179', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1180', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1181', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1182', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1183', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1184', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1185', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1186', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1187', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1188', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1189', null, null, null, 'login', null, '2019-10-21 19:03:31');
INSERT INTO `player_login_log` VALUES ('1190', null, null, null, 'login', null, '2019-10-21 19:03:50');
INSERT INTO `player_login_log` VALUES ('1191', null, null, null, 'login', null, '2019-10-21 19:03:51');
INSERT INTO `player_login_log` VALUES ('1192', null, null, null, 'login', null, '2019-10-21 20:08:15');
INSERT INTO `player_login_log` VALUES ('1193', 'A74D161A6F6C45E985EF4A90B557385D', null, null, 'login', null, '2019-10-21 20:09:29');
INSERT INTO `player_login_log` VALUES ('1194', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-21 20:28:05');
INSERT INTO `player_login_log` VALUES ('1195', '795F30396EB54CC9A051C56E0E146189', null, null, 'login', null, '2019-10-22 10:20:58');
INSERT INTO `player_login_log` VALUES ('1196', '795F30396EB54CC9A051C56E0E146189', null, null, 'quit', null, '2019-10-22 11:11:45');
INSERT INTO `player_login_log` VALUES ('1197', 'AB92922C4E874E7B8ACC7030191757AA', null, null, 'login', null, '2019-10-22 11:12:48');
INSERT INTO `player_login_log` VALUES ('1198', 'AB92922C4E874E7B8ACC7030191757AA', null, null, 'quit', null, '2019-10-22 11:22:46');
INSERT INTO `player_login_log` VALUES ('1199', 'B459081358E547928E09D886D13E36FD', null, null, 'login', null, '2019-10-22 11:23:19');
INSERT INTO `player_login_log` VALUES ('1200', 'B459081358E547928E09D886D13E36FD', null, null, 'quit', null, '2019-10-22 11:49:24');
INSERT INTO `player_login_log` VALUES ('1201', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-22 11:49:39');
INSERT INTO `player_login_log` VALUES ('1202', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-22 11:49:46');
INSERT INTO `player_login_log` VALUES ('1203', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-22 11:49:49');
INSERT INTO `player_login_log` VALUES ('1204', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'quit', null, '2019-10-22 11:54:59');
INSERT INTO `player_login_log` VALUES ('1205', 'ED4376EC0A2F47389E919F6CE3B1CC94', null, null, 'login', null, '2019-10-22 11:55:22');
INSERT INTO `player_login_log` VALUES ('1206', 'ED4376EC0A2F47389E919F6CE3B1CC94', null, null, 'quit', null, '2019-10-22 12:00:50');
INSERT INTO `player_login_log` VALUES ('1207', 'A24C369850B04C1082633B582552D1ED', null, null, 'login', null, '2019-10-22 12:01:41');
INSERT INTO `player_login_log` VALUES ('1208', 'A24C369850B04C1082633B582552D1ED', null, null, 'quit', null, '2019-10-22 12:02:47');
INSERT INTO `player_login_log` VALUES ('1209', 'DCBB45F63D5D4CE8AEC3785FE560548F', null, null, 'login', null, '2019-10-22 12:07:20');
INSERT INTO `player_login_log` VALUES ('1210', 'DCBB45F63D5D4CE8AEC3785FE560548F', null, null, 'quit', null, '2019-10-22 12:08:41');
INSERT INTO `player_login_log` VALUES ('1211', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'login', null, '2019-10-22 12:08:53');
INSERT INTO `player_login_log` VALUES ('1212', 'AD5C6D2FE8B2453D98C6DA4BC6F61189', null, null, 'quit', null, '2019-10-22 12:11:05');
INSERT INTO `player_login_log` VALUES ('1213', 'DCBB45F63D5D4CE8AEC3785FE560548F', null, null, 'login', null, '2019-10-22 12:11:18');
INSERT INTO `player_login_log` VALUES ('1214', '76BB5F47E65E4D11BCE1DA94284AE545', null, null, 'login', null, '2019-10-22 13:32:41');
INSERT INTO `player_login_log` VALUES ('1215', '76BB5F47E65E4D11BCE1DA94284AE545', null, null, 'quit', null, '2019-10-22 13:34:13');
INSERT INTO `player_login_log` VALUES ('1216', '76BB5F47E65E4D11BCE1DA94284AE545', null, null, 'login', null, '2019-10-22 13:34:30');
INSERT INTO `player_login_log` VALUES ('1217', 'EAFABDEF0D6440BEA7767596D9E46430', null, null, 'login', null, '2019-10-22 15:10:26');
INSERT INTO `player_login_log` VALUES ('1218', 'EAFABDEF0D6440BEA7767596D9E46430', null, null, 'quit', null, '2019-10-22 15:23:05');
INSERT INTO `player_login_log` VALUES ('1219', 'EAFABDEF0D6440BEA7767596D9E46430', null, null, 'login', null, '2019-10-22 15:23:23');
INSERT INTO `player_login_log` VALUES ('1220', 'EAFABDEF0D6440BEA7767596D9E46430', null, null, 'quit', null, '2019-10-22 15:34:36');
INSERT INTO `player_login_log` VALUES ('1221', '9C604A15844B438BA61B2C0663D4D627', null, null, 'login', null, '2019-10-22 15:35:21');
INSERT INTO `player_login_log` VALUES ('1222', '9C604A15844B438BA61B2C0663D4D627', null, null, 'quit', null, '2019-10-22 15:52:05');
INSERT INTO `player_login_log` VALUES ('1223', '9BDE829E950B4F878DCB55AF1070F36A', null, null, 'login', null, '2019-10-22 15:52:49');
INSERT INTO `player_login_log` VALUES ('1224', '9BDE829E950B4F878DCB55AF1070F36A', null, null, 'login', null, '2019-10-22 15:52:53');
INSERT INTO `player_login_log` VALUES ('1225', '9BDE829E950B4F878DCB55AF1070F36A', null, null, 'login', null, '2019-10-22 15:52:58');
INSERT INTO `player_login_log` VALUES ('1226', '9BDE829E950B4F878DCB55AF1070F36A', null, null, 'login', null, '2019-10-22 15:53:11');
INSERT INTO `player_login_log` VALUES ('1227', '9BDE829E950B4F878DCB55AF1070F36A', null, null, 'login', null, '2019-10-22 15:55:09');
INSERT INTO `player_login_log` VALUES ('1228', '9BDE829E950B4F878DCB55AF1070F36A', null, null, 'quit', null, '2019-10-22 16:01:08');
INSERT INTO `player_login_log` VALUES ('1229', null, null, null, 'login', null, '2019-10-22 16:01:43');
INSERT INTO `player_login_log` VALUES ('1230', null, null, null, 'login', null, '2019-10-22 16:01:52');
INSERT INTO `player_login_log` VALUES ('1231', null, null, null, 'login', null, '2019-10-22 16:02:36');
INSERT INTO `player_login_log` VALUES ('1232', 'BE67C6A636E54AE2AA9069F2CF62F76F', null, null, 'login', null, '2019-10-22 16:02:50');
INSERT INTO `player_login_log` VALUES ('1233', 'BE67C6A636E54AE2AA9069F2CF62F76F', null, null, 'quit', null, '2019-10-22 16:04:27');
INSERT INTO `player_login_log` VALUES ('1234', '9A187C229CDA460180C2521DA01F2A3A', null, null, 'login', null, '2019-10-22 16:04:58');
INSERT INTO `player_login_log` VALUES ('1235', '9A187C229CDA460180C2521DA01F2A3A', null, null, 'quit', null, '2019-10-22 16:27:08');
INSERT INTO `player_login_log` VALUES ('1236', '9C604A15844B438BA61B2C0663D4D627', null, null, 'login', null, '2019-10-22 16:27:31');
INSERT INTO `player_login_log` VALUES ('1237', '9C604A15844B438BA61B2C0663D4D627', null, null, 'quit', null, '2019-10-22 16:27:56');
INSERT INTO `player_login_log` VALUES ('1238', '9C604A15844B438BA61B2C0663D4D627', null, null, 'login', null, '2019-10-22 16:28:20');
INSERT INTO `player_login_log` VALUES ('1239', '9C604A15844B438BA61B2C0663D4D627', null, null, 'quit', null, '2019-10-22 16:29:34');
INSERT INTO `player_login_log` VALUES ('1240', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 16:35:44');
INSERT INTO `player_login_log` VALUES ('1241', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:38:23');
INSERT INTO `player_login_log` VALUES ('1242', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:38:28');
INSERT INTO `player_login_log` VALUES ('1243', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:39:56');
INSERT INTO `player_login_log` VALUES ('1244', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:06');
INSERT INTO `player_login_log` VALUES ('1245', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:30');
INSERT INTO `player_login_log` VALUES ('1246', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:42');
INSERT INTO `player_login_log` VALUES ('1247', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:50');
INSERT INTO `player_login_log` VALUES ('1248', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:52');
INSERT INTO `player_login_log` VALUES ('1249', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:53');
INSERT INTO `player_login_log` VALUES ('1250', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:53');
INSERT INTO `player_login_log` VALUES ('1251', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:53');
INSERT INTO `player_login_log` VALUES ('1252', '091A4E0B8203446B99C62D18446C2C04', null, null, 'login', null, '2019-10-22 16:40:53');
INSERT INTO `player_login_log` VALUES ('1253', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-22 16:43:06');
INSERT INTO `player_login_log` VALUES ('1254', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 16:57:57');
INSERT INTO `player_login_log` VALUES ('1255', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-22 17:34:13');
INSERT INTO `player_login_log` VALUES ('1256', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-22 17:35:24');
INSERT INTO `player_login_log` VALUES ('1257', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-22 17:49:27');
INSERT INTO `player_login_log` VALUES ('1258', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, null, 'login', null, '2019-10-22 17:50:26');
INSERT INTO `player_login_log` VALUES ('1259', 'C4F15930CF2F40D9988BB0ABDC326A8B', null, null, 'login', null, '2019-10-22 18:07:40');
INSERT INTO `player_login_log` VALUES ('1260', 'C4F15930CF2F40D9988BB0ABDC326A8B', null, null, 'quit', null, '2019-10-22 18:11:43');
INSERT INTO `player_login_log` VALUES ('1261', 'CACACA7E9B444CF78481C0BC926BA31D', null, null, 'login', null, '2019-10-22 18:12:23');
INSERT INTO `player_login_log` VALUES ('1262', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 18:16:50');
INSERT INTO `player_login_log` VALUES ('1263', 'CACACA7E9B444CF78481C0BC926BA31D', null, null, 'quit', null, '2019-10-22 18:24:24');
INSERT INTO `player_login_log` VALUES ('1264', '1EBF6363110844E5824F41E8F19398EC', null, null, 'login', null, '2019-10-22 18:25:33');
INSERT INTO `player_login_log` VALUES ('1265', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 18:29:52');
INSERT INTO `player_login_log` VALUES ('1266', '1EBF6363110844E5824F41E8F19398EC', null, null, 'quit', null, '2019-10-22 18:32:23');
INSERT INTO `player_login_log` VALUES ('1267', '8FCFFCD881CF48B4969929BB50E21D18', null, null, 'login', null, '2019-10-22 18:32:52');
INSERT INTO `player_login_log` VALUES ('1268', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-22 18:33:16');
INSERT INTO `player_login_log` VALUES ('1269', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 18:33:34');
INSERT INTO `player_login_log` VALUES ('1270', '8FCFFCD881CF48B4969929BB50E21D18', null, null, 'quit', null, '2019-10-22 18:54:08');
INSERT INTO `player_login_log` VALUES ('1271', '5814CCFBA45B44B8B54E9FF8AB6C1868', null, null, 'login', null, '2019-10-22 18:54:36');
INSERT INTO `player_login_log` VALUES ('1272', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 19:00:10');
INSERT INTO `player_login_log` VALUES ('1273', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 19:01:35');
INSERT INTO `player_login_log` VALUES ('1274', '5814CCFBA45B44B8B54E9FF8AB6C1868', null, null, 'quit', null, '2019-10-22 19:05:58');
INSERT INTO `player_login_log` VALUES ('1275', 'A569659CB82640328790A9D9BD6E2717', null, null, 'login', null, '2019-10-22 19:06:29');
INSERT INTO `player_login_log` VALUES ('1276', 'A569659CB82640328790A9D9BD6E2717', null, null, 'quit', null, '2019-10-22 19:13:54');
INSERT INTO `player_login_log` VALUES ('1277', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 19:14:02');
INSERT INTO `player_login_log` VALUES ('1278', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-22 19:15:27');
INSERT INTO `player_login_log` VALUES ('1279', '7407B84521A944E7BF3E1442033AB9A3', null, null, 'login', null, '2019-10-22 19:15:32');
INSERT INTO `player_login_log` VALUES ('1280', 'C4F15930CF2F40D9988BB0ABDC326A8B', null, null, 'login', null, '2019-10-22 19:15:37');
INSERT INTO `player_login_log` VALUES ('1281', 'C4F15930CF2F40D9988BB0ABDC326A8B', null, null, 'quit', null, '2019-10-22 19:32:36');
INSERT INTO `player_login_log` VALUES ('1282', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 19:32:47');
INSERT INTO `player_login_log` VALUES ('1283', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-22 19:33:08');
INSERT INTO `player_login_log` VALUES ('1284', null, null, null, 'login', null, '2019-10-22 19:50:53');
INSERT INTO `player_login_log` VALUES ('1285', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 19:51:02');
INSERT INTO `player_login_log` VALUES ('1286', '7407B84521A944E7BF3E1442033AB9A3', null, null, 'quit', null, '2019-10-22 19:55:29');
INSERT INTO `player_login_log` VALUES ('1287', '5AE83C28C6ED49B787634445EBF4C17A', null, null, 'login', null, '2019-10-22 19:56:30');
INSERT INTO `player_login_log` VALUES ('1288', '5AE83C28C6ED49B787634445EBF4C17A', null, null, 'quit', null, '2019-10-22 19:57:16');
INSERT INTO `player_login_log` VALUES ('1289', '5AE83C28C6ED49B787634445EBF4C17A', null, null, 'login', null, '2019-10-22 19:57:31');
INSERT INTO `player_login_log` VALUES ('1290', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-22 19:59:10');
INSERT INTO `player_login_log` VALUES ('1291', '5AE83C28C6ED49B787634445EBF4C17A', null, null, 'quit', null, '2019-10-22 19:59:12');
INSERT INTO `player_login_log` VALUES ('1292', '5AE83C28C6ED49B787634445EBF4C17A', null, null, 'login', null, '2019-10-22 19:59:55');
INSERT INTO `player_login_log` VALUES ('1293', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:02:04');
INSERT INTO `player_login_log` VALUES ('1294', '5AE83C28C6ED49B787634445EBF4C17A', null, null, 'quit', null, '2019-10-22 20:11:24');
INSERT INTO `player_login_log` VALUES ('1295', null, null, null, 'login', null, '2019-10-22 20:14:17');
INSERT INTO `player_login_log` VALUES ('1296', null, null, null, 'login', null, '2019-10-22 20:14:35');
INSERT INTO `player_login_log` VALUES ('1297', null, null, null, 'login', null, '2019-10-22 20:14:52');
INSERT INTO `player_login_log` VALUES ('1298', null, null, null, 'login', null, '2019-10-22 20:15:10');
INSERT INTO `player_login_log` VALUES ('1299', null, null, null, 'login', null, '2019-10-22 20:15:24');
INSERT INTO `player_login_log` VALUES ('1300', '8203E5421FF8447689EF82C86DC079C8', null, null, 'login', null, '2019-10-22 20:15:42');
INSERT INTO `player_login_log` VALUES ('1301', '8203E5421FF8447689EF82C86DC079C8', null, null, 'quit', null, '2019-10-22 20:26:05');
INSERT INTO `player_login_log` VALUES ('1302', null, null, null, 'login', null, '2019-10-22 20:26:30');
INSERT INTO `player_login_log` VALUES ('1303', '8203E5421FF8447689EF82C86DC079C8', null, null, 'login', null, '2019-10-22 20:26:36');
INSERT INTO `player_login_log` VALUES ('1304', '8203E5421FF8447689EF82C86DC079C8', null, null, 'quit', null, '2019-10-22 20:27:10');
INSERT INTO `player_login_log` VALUES ('1305', null, null, null, 'login', null, '2019-10-22 20:28:05');
INSERT INTO `player_login_log` VALUES ('1306', null, null, null, 'login', null, '2019-10-22 20:29:11');
INSERT INTO `player_login_log` VALUES ('1307', '8203E5421FF8447689EF82C86DC079C8', null, null, 'login', null, '2019-10-22 20:29:17');
INSERT INTO `player_login_log` VALUES ('1308', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1309', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1310', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1311', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1312', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1313', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1314', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1315', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1316', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1317', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1318', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:47');
INSERT INTO `player_login_log` VALUES ('1319', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 20:48:48');
INSERT INTO `player_login_log` VALUES ('1320', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 21:00:56');
INSERT INTO `player_login_log` VALUES ('1321', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-22 21:27:49');
INSERT INTO `player_login_log` VALUES ('1322', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-23 11:10:44');
INSERT INTO `player_login_log` VALUES ('1323', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-23 11:25:07');
INSERT INTO `player_login_log` VALUES ('1324', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'login', null, '2019-10-23 11:25:21');
INSERT INTO `player_login_log` VALUES ('1325', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'quit', null, '2019-10-23 14:05:46');
INSERT INTO `player_login_log` VALUES ('1326', '2C000246F4FA4176A1A76E2E005C6C41', null, null, 'login', null, '2019-10-23 14:06:01');
INSERT INTO `player_login_log` VALUES ('1327', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'login', null, '2019-10-23 14:06:43');
INSERT INTO `player_login_log` VALUES ('1328', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'quit', null, '2019-10-23 14:26:06');
INSERT INTO `player_login_log` VALUES ('1329', '2C000246F4FA4176A1A76E2E005C6C41', null, null, 'login', null, '2019-10-23 14:26:18');
INSERT INTO `player_login_log` VALUES ('1330', '2C000246F4FA4176A1A76E2E005C6C41', null, null, 'quit', null, '2019-10-23 14:33:33');
INSERT INTO `player_login_log` VALUES ('1331', 'CF76591AA4244FD0A98DE79ACBDAD624', null, null, 'login', null, '2019-10-23 14:33:59');
INSERT INTO `player_login_log` VALUES ('1332', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'login', null, '2019-10-23 15:04:06');
INSERT INTO `player_login_log` VALUES ('1333', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'quit', null, '2019-10-23 15:34:54');
INSERT INTO `player_login_log` VALUES ('1334', '2C000246F4FA4176A1A76E2E005C6C41', null, null, 'login', null, '2019-10-23 15:35:06');
INSERT INTO `player_login_log` VALUES ('1335', 'CF76591AA4244FD0A98DE79ACBDAD624', null, null, 'login', null, '2019-10-23 16:43:59');
INSERT INTO `player_login_log` VALUES ('1336', 'CF76591AA4244FD0A98DE79ACBDAD624', null, null, 'quit', null, '2019-10-23 16:59:36');
INSERT INTO `player_login_log` VALUES ('1337', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'login', null, '2019-10-23 16:59:47');
INSERT INTO `player_login_log` VALUES ('1338', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'quit', null, '2019-10-23 17:01:37');
INSERT INTO `player_login_log` VALUES ('1339', 'CF76591AA4244FD0A98DE79ACBDAD624', null, null, 'login', null, '2019-10-23 17:02:02');
INSERT INTO `player_login_log` VALUES ('1340', 'CF76591AA4244FD0A98DE79ACBDAD624', null, null, 'quit', null, '2019-10-23 17:02:21');
INSERT INTO `player_login_log` VALUES ('1341', '3D6BAE6E8B254EB1B8B863F74DBDBEF7', null, null, 'login', null, '2019-10-23 17:02:34');
INSERT INTO `player_login_log` VALUES ('1342', 'CF76591AA4244FD0A98DE79ACBDAD624', null, null, 'login', null, '2019-10-23 17:05:27');
INSERT INTO `player_login_log` VALUES ('1343', '5360EDCB785645379E45751033FB73EF', null, null, 'login', null, '2019-10-23 17:31:15');
INSERT INTO `player_login_log` VALUES ('1344', '3D6BAE6E8B254EB1B8B863F74DBDBEF7', null, null, 'quit', null, '2019-10-23 17:32:40');
INSERT INTO `player_login_log` VALUES ('1345', 'F9B106323AD9423680B9809CA79945A5', null, null, 'login', null, '2019-10-23 17:32:59');
INSERT INTO `player_login_log` VALUES ('1346', 'F9B106323AD9423680B9809CA79945A5', null, null, 'login', null, '2019-10-23 17:33:01');
INSERT INTO `player_login_log` VALUES ('1347', 'F9B106323AD9423680B9809CA79945A5', null, null, 'quit', null, '2019-10-23 18:13:37');
INSERT INTO `player_login_log` VALUES ('1348', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'login', null, '2019-10-23 18:13:50');
INSERT INTO `player_login_log` VALUES ('1349', 'C5318ACD5C5F40259728F6D8FD15F986', null, null, 'quit', null, '2019-10-23 18:16:07');
INSERT INTO `player_login_log` VALUES ('1350', 'F9B106323AD9423680B9809CA79945A5', null, null, 'login', null, '2019-10-23 18:16:20');
INSERT INTO `player_login_log` VALUES ('1351', 'F9B106323AD9423680B9809CA79945A5', null, null, 'quit', null, '2019-10-23 18:30:23');
INSERT INTO `player_login_log` VALUES ('1352', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-23 18:30:33');
INSERT INTO `player_login_log` VALUES ('1353', '5360EDCB785645379E45751033FB73EF', null, null, 'quit', null, '2019-10-23 18:44:00');
INSERT INTO `player_login_log` VALUES ('1354', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 18:44:16');
INSERT INTO `player_login_log` VALUES ('1355', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 19:54:28');
INSERT INTO `player_login_log` VALUES ('1356', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 19:54:28');
INSERT INTO `player_login_log` VALUES ('1357', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 19:54:28');
INSERT INTO `player_login_log` VALUES ('1358', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 19:54:28');
INSERT INTO `player_login_log` VALUES ('1359', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 19:54:28');
INSERT INTO `player_login_log` VALUES ('1360', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 19:54:28');
INSERT INTO `player_login_log` VALUES ('1361', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 19:54:28');
INSERT INTO `player_login_log` VALUES ('1362', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 19:55:13');
INSERT INTO `player_login_log` VALUES ('1363', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 20:25:23');
INSERT INTO `player_login_log` VALUES ('1364', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-23 20:48:42');
INSERT INTO `player_login_log` VALUES ('1365', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-23 20:50:06');
INSERT INTO `player_login_log` VALUES ('1366', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-23 20:50:18');
INSERT INTO `player_login_log` VALUES ('1367', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-23 20:50:27');
INSERT INTO `player_login_log` VALUES ('1368', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-23 21:48:57');
INSERT INTO `player_login_log` VALUES ('1369', '7835E18A37E4416488E2F1E7D122BC2D', null, null, 'login', null, '2019-10-24 16:20:16');
INSERT INTO `player_login_log` VALUES ('1370', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 16:20:59');
INSERT INTO `player_login_log` VALUES ('1371', '7835E18A37E4416488E2F1E7D122BC2D', null, null, 'quit', null, '2019-10-24 16:21:42');
INSERT INTO `player_login_log` VALUES ('1372', '7835E18A37E4416488E2F1E7D122BC2D', null, null, 'login', null, '2019-10-24 16:22:31');
INSERT INTO `player_login_log` VALUES ('1373', '7835E18A37E4416488E2F1E7D122BC2D', null, null, 'quit', null, '2019-10-24 16:26:10');
INSERT INTO `player_login_log` VALUES ('1374', '7835E18A37E4416488E2F1E7D122BC2D', null, null, 'login', null, '2019-10-24 16:26:25');
INSERT INTO `player_login_log` VALUES ('1375', '0D4A5F9A0AE2411BB310D298CAAFC86D', null, null, 'login', null, '2019-10-24 18:07:14');
INSERT INTO `player_login_log` VALUES ('1376', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 18:47:39');
INSERT INTO `player_login_log` VALUES ('1377', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 18:48:56');
INSERT INTO `player_login_log` VALUES ('1378', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 18:49:22');
INSERT INTO `player_login_log` VALUES ('1379', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:00:23');
INSERT INTO `player_login_log` VALUES ('1380', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:00:23');
INSERT INTO `player_login_log` VALUES ('1381', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-24 19:01:45');
INSERT INTO `player_login_log` VALUES ('1382', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-24 19:04:25');
INSERT INTO `player_login_log` VALUES ('1383', '7835E18A37E4416488E2F1E7D122BC2D', null, null, 'login', null, '2019-10-24 19:05:22');
INSERT INTO `player_login_log` VALUES ('1384', '7835E18A37E4416488E2F1E7D122BC2D', null, null, 'quit', null, '2019-10-24 19:16:48');
INSERT INTO `player_login_log` VALUES ('1385', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:17:47');
INSERT INTO `player_login_log` VALUES ('1386', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:26:42');
INSERT INTO `player_login_log` VALUES ('1387', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:26:56');
INSERT INTO `player_login_log` VALUES ('1388', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:28:41');
INSERT INTO `player_login_log` VALUES ('1389', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:28:56');
INSERT INTO `player_login_log` VALUES ('1390', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:31:08');
INSERT INTO `player_login_log` VALUES ('1391', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:31:50');
INSERT INTO `player_login_log` VALUES ('1392', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:34:43');
INSERT INTO `player_login_log` VALUES ('1393', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:34:59');
INSERT INTO `player_login_log` VALUES ('1394', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:37:47');
INSERT INTO `player_login_log` VALUES ('1395', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:42:40');
INSERT INTO `player_login_log` VALUES ('1396', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:42:54');
INSERT INTO `player_login_log` VALUES ('1397', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:43:56');
INSERT INTO `player_login_log` VALUES ('1398', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:45:09');
INSERT INTO `player_login_log` VALUES ('1399', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:45:18');
INSERT INTO `player_login_log` VALUES ('1400', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:47:37');
INSERT INTO `player_login_log` VALUES ('1401', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:47:51');
INSERT INTO `player_login_log` VALUES ('1402', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 19:48:33');
INSERT INTO `player_login_log` VALUES ('1403', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:49:29');
INSERT INTO `player_login_log` VALUES ('1404', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 19:51:08');
INSERT INTO `player_login_log` VALUES ('1405', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 20:03:49');
INSERT INTO `player_login_log` VALUES ('1406', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-24 20:04:57');
INSERT INTO `player_login_log` VALUES ('1407', 'E500720E7E5048C18EB8120C32107D8C', null, null, 'login', null, '2019-10-24 20:06:05');
INSERT INTO `player_login_log` VALUES ('1408', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-24 20:44:42');
INSERT INTO `player_login_log` VALUES ('1409', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 10:33:51');
INSERT INTO `player_login_log` VALUES ('1410', 'E500720E7E5048C18EB8120C32107D8C', null, null, 'quit', null, '2019-10-25 10:34:31');
INSERT INTO `player_login_log` VALUES ('1411', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 10:53:15');
INSERT INTO `player_login_log` VALUES ('1412', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-25 10:53:45');
INSERT INTO `player_login_log` VALUES ('1413', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 10:55:31');
INSERT INTO `player_login_log` VALUES ('1414', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 10:55:50');
INSERT INTO `player_login_log` VALUES ('1415', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 11:01:33');
INSERT INTO `player_login_log` VALUES ('1416', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 11:01:49');
INSERT INTO `player_login_log` VALUES ('1417', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 11:02:37');
INSERT INTO `player_login_log` VALUES ('1418', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 11:11:04');
INSERT INTO `player_login_log` VALUES ('1419', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, null, 'login', null, '2019-10-25 11:21:36');
INSERT INTO `player_login_log` VALUES ('1420', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, null, 'login', null, '2019-10-25 11:21:57');
INSERT INTO `player_login_log` VALUES ('1421', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, null, 'quit', null, '2019-10-25 11:22:59');
INSERT INTO `player_login_log` VALUES ('1422', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, null, 'login', null, '2019-10-25 11:23:11');
INSERT INTO `player_login_log` VALUES ('1423', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 17:05:04');
INSERT INTO `player_login_log` VALUES ('1424', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 17:05:10');
INSERT INTO `player_login_log` VALUES ('1425', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 17:05:27');
INSERT INTO `player_login_log` VALUES ('1426', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, null, 'quit', null, '2019-10-25 17:37:14');
INSERT INTO `player_login_log` VALUES ('1427', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 17:37:30');
INSERT INTO `player_login_log` VALUES ('1428', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 17:40:21');
INSERT INTO `player_login_log` VALUES ('1429', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 17:40:35');
INSERT INTO `player_login_log` VALUES ('1430', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 17:54:13');
INSERT INTO `player_login_log` VALUES ('1431', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 17:57:14');
INSERT INTO `player_login_log` VALUES ('1432', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 17:57:54');
INSERT INTO `player_login_log` VALUES ('1433', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 17:57:57');
INSERT INTO `player_login_log` VALUES ('1434', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 17:59:57');
INSERT INTO `player_login_log` VALUES ('1435', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 18:00:12');
INSERT INTO `player_login_log` VALUES ('1436', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 18:01:26');
INSERT INTO `player_login_log` VALUES ('1437', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 18:01:47');
INSERT INTO `player_login_log` VALUES ('1438', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 18:02:37');
INSERT INTO `player_login_log` VALUES ('1439', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'login', null, '2019-10-25 18:02:50');
INSERT INTO `player_login_log` VALUES ('1440', '1B86C28CDE9F4F548E6839C7C971FB2A', null, null, 'quit', null, '2019-10-25 18:08:04');
INSERT INTO `player_login_log` VALUES ('1441', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 19:31:28');
INSERT INTO `player_login_log` VALUES ('1442', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 19:31:36');
INSERT INTO `player_login_log` VALUES ('1443', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 19:33:26');
INSERT INTO `player_login_log` VALUES ('1444', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 19:39:59');
INSERT INTO `player_login_log` VALUES ('1445', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-25 19:40:53');
INSERT INTO `player_login_log` VALUES ('1446', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 19:41:08');
INSERT INTO `player_login_log` VALUES ('1447', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 19:47:09');
INSERT INTO `player_login_log` VALUES ('1448', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 19:48:07');
INSERT INTO `player_login_log` VALUES ('1449', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 20:10:46');
INSERT INTO `player_login_log` VALUES ('1450', '80F84F191FC94F32B4706C50072A35D1', null, null, 'quit', null, '2019-10-25 20:11:33');
INSERT INTO `player_login_log` VALUES ('1451', '80F84F191FC94F32B4706C50072A35D1', null, null, 'login', null, '2019-10-25 20:21:03');

-- ----------------------------
-- Table structure for player_trade
-- ----------------------------
DROP TABLE IF EXISTS `player_trade`;
CREATE TABLE `player_trade` (
  `trade_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `trade_acc_id` int(10) unsigned DEFAULT NULL COMMENT '账户id',
  `trade_player_id` varchar(64) DEFAULT NULL COMMENT '交易人id',
  `trade_order_id` int(11) unsigned DEFAULT NULL COMMENT '订单id',
  `trade_amount` decimal(50,9) DEFAULT '0.0000' COMMENT '交易金额',
  `personal_tax` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '个人所得税',
  `enterprise_tax` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '企业所得税',
  `quota_tax` decimal(50,9) unsigned DEFAULT '0.0000' COMMENT '定额税',
  `trade_status` varchar(30) DEFAULT NULL COMMENT '交易状态(FREEZE冻结,OUT已出账,IN已入账)',
  `in_out` varchar(10) DEFAULT NULL COMMENT '入账还是出账(入账in,出账out)',
  `trade_type` varchar(30) DEFAULT NULL COMMENT '交易类型（充值:recharge,转账:transfer,提现:withdraw,购买mt:buy_mt,投资invest,投资收益:invest_earnings)',
  `trade_desc` varchar(100) DEFAULT NULL COMMENT '交易描述',
  `create_time` datetime DEFAULT NULL COMMENT '交易时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`trade_id`),
  KEY `index_trade_player_id` (`trade_player_id`),
  KEY `index_trade_order_id` (`trade_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=352 DEFAULT CHARSET=utf8 COMMENT='交易记录表';

-- ----------------------------
-- Records of player_trade
-- ----------------------------
INSERT INTO `player_trade` VALUES ('1', '888888948', 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', '1', '30.0000', '0.1000', '0.1000', '0.0000', 'FREEZE', 'OUT', 'INVEST', '预约投资', '2019-10-25 10:59:16', '2019-10-25 10:59:16');
INSERT INTO `player_trade` VALUES ('282', '888888978', '0D4A5F9A0AE2411BB310D298CAAFC86D', null, '100.0000', '0.0000', '0.0000', '0.0000', null, null, 'TRANSFER', '玩家转账', '2019-10-24 21:31:52', '2019-10-24 21:31:52');
INSERT INTO `player_trade` VALUES ('283', '888888945', '80F84F191FC94F32B4706C50072A35D1', null, '100.0000', '0.0000', '0.0000', '0.0000', null, null, 'TRANSFER', '转账成功！', '2019-10-24 21:31:52', '2019-10-24 21:31:52');
INSERT INTO `player_trade` VALUES ('284', null, '0D4A5F9A0AE2411BB310D298CAAFC86D', null, '1.0000', '0.0000', '0.0000', '0.0000', 'OUT', 'OUT', 'CHANGE_TRAN_PWD', '玩家修改交易密码，扣除[1.0]MT', '2019-10-24 21:37:28', '2019-10-24 21:37:28');
INSERT INTO `player_trade` VALUES ('285', null, '8A2922A66F474A0DA9B10FB4BCD59BA0', null, '1.0000', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'CHANGE_TRAN_PWD', '玩家修改交易密码，收取[1.0]MT', '2019-10-24 21:37:28', '2019-10-24 21:37:28');
INSERT INTO `player_trade` VALUES ('286', null, '0D4A5F9A0AE2411BB310D298CAAFC86D', null, '1.0000', '0.0000', '0.0000', '0.0000', 'OUT', 'OUT', 'CHANGE_TRAN_PWD', '玩家修改交易密码，扣除[1.0]MT', '2019-10-24 21:44:32', '2019-10-24 21:44:32');
INSERT INTO `player_trade` VALUES ('287', null, '8A2922A66F474A0DA9B10FB4BCD59BA0', null, '1.0000', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'CHANGE_TRAN_PWD', '玩家修改交易密码，收取[1.0]MT', '2019-10-24 21:44:32', '2019-10-24 21:44:32');
INSERT INTO `player_trade` VALUES ('288', null, '80F84F191FC94F32B4706C50072A35D1', null, '0.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin减掉[-9000.0000]USDT', '2019-10-25 09:28:32', '2019-10-25 09:28:32');
INSERT INTO `player_trade` VALUES ('289', null, '80F84F191FC94F32B4706C50072A35D1', null, '0.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin减掉[9000.0000]USDT', '2019-10-25 09:38:35', '2019-10-25 09:38:35');
INSERT INTO `player_trade` VALUES ('290', null, '80F84F191FC94F32B4706C50072A35D1', null, '0.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin增加[9000.0000]USDT', '2019-10-25 09:41:00', '2019-10-25 09:41:00');
INSERT INTO `player_trade` VALUES ('291', null, '80F84F191FC94F32B4706C50072A35D1', null, '0.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin减掉[9000.0000]USDT', '2019-10-25 09:44:11', '2019-10-25 09:44:11');
INSERT INTO `player_trade` VALUES ('292', null, '80F84F191FC94F32B4706C50072A35D1', null, '0.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin增加[9000.0000]USDT', '2019-10-25 09:46:13', '2019-10-25 09:46:13');
INSERT INTO `player_trade` VALUES ('293', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '0.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin增加[1.0000]USDT', '2019-10-25 09:47:50', '2019-10-25 09:47:50');
INSERT INTO `player_trade` VALUES ('294', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '0.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin减掉[900.0000]USDT', '2019-10-25 09:48:16', '2019-10-25 09:48:16');
INSERT INTO `player_trade` VALUES ('295', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin增加[1.0000]MT', '2019-10-25 10:00:38', '2019-10-25 10:00:38');
INSERT INTO `player_trade` VALUES ('296', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, null, '管理后台修改账户额度,修改人：admin增加[900.0000]USDT', '2019-10-25 10:01:12', '2019-10-25 10:01:12');
INSERT INTO `player_trade` VALUES ('297', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin增加[1.0000]MT', '2019-10-25 10:05:04', '2019-10-25 10:05:04');
INSERT INTO `player_trade` VALUES ('298', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT,减掉[900.0000]USDT', '2019-10-25 10:06:33', '2019-10-25 10:06:33');
INSERT INTO `player_trade` VALUES ('299', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,增加[900.0000]USDT', '2019-10-25 10:08:35', '2019-10-25 10:08:35');
INSERT INTO `player_trade` VALUES ('300', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,增加[1.0000]MT', '2019-10-25 10:08:49', '2019-10-25 10:08:49');
INSERT INTO `player_trade` VALUES ('301', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT', '2019-10-25 10:11:50', '2019-10-25 10:11:50');
INSERT INTO `player_trade` VALUES ('302', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,减掉[900.0000]USDT', '2019-10-25 10:12:13', '2019-10-25 10:12:13');
INSERT INTO `player_trade` VALUES ('303', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,增加[1.0000]MT,增加[900.0000]USDT', '2019-10-25 10:12:52', '2019-10-25 10:12:52');
INSERT INTO `player_trade` VALUES ('304', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT,减掉[900.0000]USDT', '2019-10-25 10:13:16', '2019-10-25 10:13:16');
INSERT INTO `player_trade` VALUES ('305', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,增加[1.0000]MT,增加[900.0000]USDT', '2019-10-25 10:14:07', '2019-10-25 10:14:07');
INSERT INTO `player_trade` VALUES ('306', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT,减掉[900.0000]USDT', '2019-10-25 10:16:41', '2019-10-25 10:16:41');
INSERT INTO `player_trade` VALUES ('307', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,增加[1.0000]MT,增加[900.0000]USDT', '2019-10-25 10:24:52', '2019-10-25 10:24:52');
INSERT INTO `player_trade` VALUES ('308', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT,减掉[900.0000]USDT', '2019-10-25 10:26:26', '2019-10-25 10:26:26');
INSERT INTO `player_trade` VALUES ('310', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,增加[1.0000]MT', '2019-10-25 11:10:32', '2019-10-25 11:10:32');
INSERT INTO `player_trade` VALUES ('311', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin,增加[900.0000]USDT', '2019-10-25 11:11:12', '2019-10-25 11:11:12');
INSERT INTO `player_trade` VALUES ('312', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin', '2019-10-25 11:12:04', '2019-10-25 11:12:04');
INSERT INTO `player_trade` VALUES ('313', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin', '2019-10-25 11:12:04', '2019-10-25 11:12:04');
INSERT INTO `player_trade` VALUES ('314', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度', '2019-10-25 13:39:35', '2019-10-25 13:39:35');
INSERT INTO `player_trade` VALUES ('315', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度', '2019-10-25 13:39:35', '2019-10-25 13:39:35');
INSERT INTO `player_trade` VALUES ('316', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '900.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度', '2019-10-25 13:41:02', '2019-10-25 13:41:02');
INSERT INTO `player_trade` VALUES ('317', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度', '2019-10-25 13:41:02', '2019-10-25 13:41:02');
INSERT INTO `player_trade` VALUES ('318', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '90.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin', '2019-10-25 13:42:28', '2019-10-25 13:42:28');
INSERT INTO `player_trade` VALUES ('319', null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', null, '1.0000', '0.0000', '0.0000', '0.0000', null, null, 'CHANGE_TRAN_PWD', '管理后台修改账户额度,修改人：admin', '2019-10-25 13:42:29', '2019-10-25 13:42:29');
INSERT INTO `player_trade` VALUES ('320', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:41:05', '2019-10-25 17:41:05');
INSERT INTO `player_trade` VALUES ('321', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:41:19', '2019-10-25 17:41:19');
INSERT INTO `player_trade` VALUES ('322', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:47:06', '2019-10-25 17:47:06');
INSERT INTO `player_trade` VALUES ('323', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:49:16', '2019-10-25 17:49:16');
INSERT INTO `player_trade` VALUES ('324', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:49:16', '2019-10-25 17:49:16');
INSERT INTO `player_trade` VALUES ('325', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:49:17', '2019-10-25 17:49:17');
INSERT INTO `player_trade` VALUES ('326', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:50:47', '2019-10-25 17:50:47');
INSERT INTO `player_trade` VALUES ('327', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:50:47', '2019-10-25 17:50:47');
INSERT INTO `player_trade` VALUES ('328', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:50:47', '2019-10-25 17:50:47');
INSERT INTO `player_trade` VALUES ('329', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:50:47', '2019-10-25 17:50:47');
INSERT INTO `player_trade` VALUES ('330', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:50:47', '2019-10-25 17:50:47');
INSERT INTO `player_trade` VALUES ('331', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:50:47', '2019-10-25 17:50:47');
INSERT INTO `player_trade` VALUES ('332', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:50:47', '2019-10-25 17:50:47');
INSERT INTO `player_trade` VALUES ('333', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:52:02', '2019-10-25 17:52:02');
INSERT INTO `player_trade` VALUES ('334', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:52:26', '2019-10-25 17:52:26');
INSERT INTO `player_trade` VALUES ('335', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:52:27', '2019-10-25 17:52:27');
INSERT INTO `player_trade` VALUES ('336', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:52:27', '2019-10-25 17:52:27');
INSERT INTO `player_trade` VALUES ('337', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:52:27', '2019-10-25 17:52:27');
INSERT INTO `player_trade` VALUES ('338', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:52:27', '2019-10-25 17:52:27');
INSERT INTO `player_trade` VALUES ('339', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:52:27', '2019-10-25 17:52:27');
INSERT INTO `player_trade` VALUES ('340', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:52:28', '2019-10-25 17:52:28');
INSERT INTO `player_trade` VALUES ('341', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:55:12', '2019-10-25 17:55:12');
INSERT INTO `player_trade` VALUES ('342', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 17:55:17', '2019-10-25 17:55:17');
INSERT INTO `player_trade` VALUES ('343', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 18:00:38', '2019-10-25 18:00:38');
INSERT INTO `player_trade` VALUES ('344', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 18:00:38', '2019-10-25 18:00:38');
INSERT INTO `player_trade` VALUES ('345', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 18:02:02', '2019-10-25 18:02:02');
INSERT INTO `player_trade` VALUES ('346', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 18:02:02', '2019-10-25 18:02:02');
INSERT INTO `player_trade` VALUES ('347', '888888948', '1B86C28CDE9F4F548E6839C7C971FB2A', '12', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 18:03:04', '2019-10-25 18:03:04');
INSERT INTO `player_trade` VALUES ('348', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '3.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 18:42:12', '2019-10-25 18:42:12');
INSERT INTO `player_trade` VALUES ('349', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '4.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 19:05:08', '2019-10-25 19:05:08');
INSERT INTO `player_trade` VALUES ('350', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '4.9700', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 20:32:18', '2019-10-25 20:32:18');
INSERT INTO `player_trade` VALUES ('351', '888888945', '80F84F191FC94F32B4706C50072A35D1', '9', '4.9000', '0.0000', '0.0000', '0.0000', 'IN', 'IN', 'INVEST_EARNINGS', '提取已入账', '2019-10-25 20:41:39', '2019-10-25 20:41:39');

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
INSERT INTO `qrtz_cron_triggers` VALUES ('schedulerFactoryBean', '提现自动审核', 'withdrawVerifyJob', '0 0/3 * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('schedulerFactoryBean', '转账自动审核', 'transferVerifyJob', '0 0/5 * * * ?', 'Asia/Shanghai');
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
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', '提现自动审核', 'withdrawVerifyJob', '没3分钟执行一次', 'com.dream.city.job.WithdrawVerifyJob', '0', '0', '0', '0', 0x230D0A23467269204F63742032352031343A33363A30322043535420323031390D0A);
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', '转账自动审核', 'transferVerifyJob', '每5分钟执行一次', 'com.dream.city.job.TransferVerifyJob', '0', '0', '0', '0', 0x230D0A23467269204F63742032352031343A33343A33312043535420323031390D0A);
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', '预约自动审核', 'InverstVerifyJob', '每天9点执行一次', 'com.dream.city.job.InverstVerifyJob', '0', '0', '0', '0', 0x230D0A23546875204F63742031372032323A32373A31322043535420323031390D0A);

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
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', '提现自动审核', 'withdrawVerifyJob', '提现自动审核', 'withdrawVerifyJob', '没3分钟执行一次', '1571994360000', '1571994180000', '5', 'WAITING', 'CRON', '1571985363000', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', '转账自动审核', 'transferVerifyJob', '转账自动审核', 'transferVerifyJob', '没5分钟执行一次', '1571994300000', '1571994000000', '5', 'WAITING', 'CRON', '1571985272000', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', '预约自动审核', 'InverstVerifyJob', '预约自动审核', 'InverstVerifyJob', '每天9点执行一次', '1572051600000', '1571985079776', '5', 'WAITING', 'CRON', '1571322432000', '0', null, '0', '');

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
  `order_amount` decimal(50,9) DEFAULT NULL COMMENT '订单额度',
  `order_buy_type` varchar(255) DEFAULT NULL COMMENT '订单购买类型',
  `order_pay_type` varchar(255) DEFAULT NULL COMMENT '订单支付类型',
  `order_pay_amount` decimal(50,9) DEFAULT NULL COMMENT '支付额度',
  `order_player_buyer` varchar(64) DEFAULT NULL COMMENT '订单玩家',
  `order_player_seller` varchar(64) DEFAULT NULL COMMENT '订单卖家',
  `order_state` tinyint(10) DEFAULT NULL COMMENT '订单状态：0创建,1:待支付2完成支付，已通过3支付成功等待处理4超时5拒绝',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sales_order
-- ----------------------------
INSERT INTO `sales_order` VALUES ('81', '20191022181817', '30', 'MT', 'USDT', '30', '80F84F191FC94F32B4706C50072A35D1', '8A2922A66F474A0DA9B10FB4BCD59BA0', '3', '2019-10-22 10:18:18', null);
INSERT INTO `sales_order` VALUES ('82', '20191022201942', '100', 'MT', 'USDT', '100', '8203E5421FF8447689EF82C86DC079C8', '8A2922A66F474A0DA9B10FB4BCD59BA0', '2', '2019-10-22 12:19:42', null);
INSERT INTO `sales_order` VALUES ('83', '20191024210844', '100', 'MT', 'USDT', '100', '0D4A5F9A0AE2411BB310D298CAAFC86D', '80F84F191FC94F32B4706C50072A35D1', '3', '2019-10-24 13:08:44', null);
INSERT INTO `sales_order` VALUES ('84', '20191024210930', '100', 'MT', 'USDT', '100', '0D4A5F9A0AE2411BB310D298CAAFC86D', '80F84F191FC94F32B4706C50072A35D1', '3', '2019-10-24 13:09:31', null);
INSERT INTO `sales_order` VALUES ('85', '20191024210946', '1000', 'MT', 'USDT', '1000', '0D4A5F9A0AE2411BB310D298CAAFC86D', '80F84F191FC94F32B4706C50072A35D1', '3', '2019-10-24 13:09:47', null);
INSERT INTO `sales_order` VALUES ('86', '20191024211015', '200', 'MT', 'USDT', '200', '0D4A5F9A0AE2411BB310D298CAAFC86D', '80F84F191FC94F32B4706C50072A35D1', '3', '2019-10-24 13:10:15', null);
INSERT INTO `sales_order` VALUES ('87', '20191024211046', '200', 'MT', 'USDT', '200', '0D4A5F9A0AE2411BB310D298CAAFC86D', '80F84F191FC94F32B4706C50072A35D1', '3', '2019-10-24 13:10:46', null);
INSERT INTO `sales_order` VALUES ('88', '20191024211113', '100', 'MT', 'USDT', '100', '0D4A5F9A0AE2411BB310D298CAAFC86D', '80F84F191FC94F32B4706C50072A35D1', '3', '2019-10-24 13:11:14', null);
INSERT INTO `sales_order` VALUES ('89', '20191025202615', '100', 'MT', 'USDT', '100', '80F84F191FC94F32B4706C50072A35D1', '8A2922A66F474A0DA9B10FB4BCD59BA0', '3', '2019-10-25 12:26:16', null);
INSERT INTO `sales_order` VALUES ('90', '20191025202925', '100', 'MT', 'USDT', '100', '80F84F191FC94F32B4706C50072A35D1', '8A2922A66F474A0DA9B10FB4BCD59BA0', '3', '2019-10-25 12:29:25', null);
INSERT INTO `sales_order` VALUES ('91', '20191025203129', '100', 'MT', 'USDT', '100', '80F84F191FC94F32B4706C50072A35D1', '8A2922A66F474A0DA9B10FB4BCD59BA0', '3', '2019-10-25 12:31:29', null);

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
  `trade_amount` decimal(50,9) DEFAULT '0.0000' COMMENT '交易金额',
  `usdt_surplus` decimal(50,9) DEFAULT '0.0000' COMMENT '余额',
  `mt_surplus` decimal(50,9) DEFAULT '0.0000' COMMENT '余额',
  `verify_time` datetime DEFAULT NULL COMMENT '交易审核时间',
  `descr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_trade_id` (`trade_id`),
  KEY `index_player_id` (`player_id`),
  KEY `index_order_id` (`order_id`),
  KEY `index_verify_id` (`verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=574 DEFAULT CHARSET=utf8 COMMENT='交易流水表';

-- ----------------------------
-- Records of trade_detail
-- ----------------------------
INSERT INTO `trade_detail` VALUES ('501', '282', '188', null, '0D4A5F9A0AE2411BB310D298CAAFC86D', 'TRANSFER_VERIFY', '100.0000', '900.0000', '1000.0000', '2019-10-24 13:31:53', '转账扣除金额', '2019-10-24 21:31:52');
INSERT INTO `trade_detail` VALUES ('502', '282', '188', null, '0D4A5F9A0AE2411BB310D298CAAFC86D', 'TRANSFER_VERIFY', '5.0000', '900.0000', '995.0000', '2019-10-24 13:31:53', '转账扣除税金', '2019-10-24 21:31:52');
INSERT INTO `trade_detail` VALUES ('503', '282', null, null, '80F84F191FC94F32B4706C50072A35D1', 'TRANSFER_TO', '100.0000', '1100.0000', '1000.0000', null, '转账进账', '2019-10-24 21:31:52');
INSERT INTO `trade_detail` VALUES ('504', '284', null, null, '0D4A5F9A0AE2411BB310D298CAAFC86D', 'CHANGE_TRAN_PWD', '1.0000', '900.0000', '994.0000', null, '玩家修改交易密码，扣除[1.0]MT', '2019-10-24 21:37:28');
INSERT INTO `trade_detail` VALUES ('505', '285', null, null, '8A2922A66F474A0DA9B10FB4BCD59BA0', 'CHANGE_TRAN_PWD', '1.0000', '108.5000', '1.0000', null, '玩家修改交易密码，收取[1.0]MT', '2019-10-24 21:37:28');
INSERT INTO `trade_detail` VALUES ('506', '286', null, null, '0D4A5F9A0AE2411BB310D298CAAFC86D', 'CHANGE_TRAN_PWD', '1.0000', '900.0000', '993.0000', null, '玩家修改交易密码，扣除[1.0]MT', '2019-10-24 21:44:32');
INSERT INTO `trade_detail` VALUES ('507', '287', null, null, '8A2922A66F474A0DA9B10FB4BCD59BA0', 'CHANGE_TRAN_PWD', '1.0000', '108.5000', '2.0000', null, '玩家修改交易密码，收取[1.0]MT', '2019-10-24 21:44:32');
INSERT INTO `trade_detail` VALUES ('508', '288', null, null, '80F84F191FC94F32B4706C50072A35D1', 'CHANGE_TRAN_PWD', '0.0000', '-9000.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin减掉[-9000.0000]USDT', '2019-10-25 09:28:32');
INSERT INTO `trade_detail` VALUES ('509', '289', null, null, '80F84F191FC94F32B4706C50072A35D1', 'CHANGE_TRAN_PWD', '0.0000', '9000.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin减掉[9000.0000]USDT', '2019-10-25 09:38:35');
INSERT INTO `trade_detail` VALUES ('510', '290', null, null, '80F84F191FC94F32B4706C50072A35D1', 'CHANGE_TRAN_PWD', '0.0000', '9000.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin增加[9000.0000]USDT', '2019-10-25 09:41:00');
INSERT INTO `trade_detail` VALUES ('511', '291', null, null, '80F84F191FC94F32B4706C50072A35D1', 'CHANGE_TRAN_PWD', '0.0000', '9000.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin减掉[9000.0000]USDT', '2019-10-25 09:44:11');
INSERT INTO `trade_detail` VALUES ('512', '292', null, null, '80F84F191FC94F32B4706C50072A35D1', 'CHANGE_TRAN_PWD', '0.0000', '-9000.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin增加[9000.0000]USDT', '2019-10-25 09:46:13');
INSERT INTO `trade_detail` VALUES ('513', '293', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '0.0000', '-1.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin增加[1.0000]USDT', '2019-10-25 09:47:50');
INSERT INTO `trade_detail` VALUES ('514', '294', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '0.0000', '900.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin减掉[900.0000]USDT', '2019-10-25 09:48:16');
INSERT INTO `trade_detail` VALUES ('515', '295', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '0.0000', '-1.0000', null, '管理后台修改账户额度,修改人：admin增加[1.0000]MT', '2019-10-25 10:00:38');
INSERT INTO `trade_detail` VALUES ('516', '296', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '-900.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin增加[900.0000]USDT', '2019-10-25 10:01:12');
INSERT INTO `trade_detail` VALUES ('517', '297', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '0.0000', '-1.0000', null, '管理后台修改账户额度,修改人：admin增加[1.0000]MT', '2019-10-25 10:05:04');
INSERT INTO `trade_detail` VALUES ('518', '298', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '900.0000', '1.0000', null, '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT,减掉[900.0000]USDT', '2019-10-25 10:06:33');
INSERT INTO `trade_detail` VALUES ('519', '299', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '-900.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin,增加[900.0000]USDT', '2019-10-25 10:08:35');
INSERT INTO `trade_detail` VALUES ('520', '300', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '0.0000', '-1.0000', null, '管理后台修改账户额度,修改人：admin,增加[1.0000]MT', '2019-10-25 10:08:49');
INSERT INTO `trade_detail` VALUES ('521', '301', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '1000.0000', '1.0000', null, '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT', '2019-10-25 10:11:50');
INSERT INTO `trade_detail` VALUES ('522', '302', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '100.0000', '1.0000', null, '管理后台修改账户额度,修改人：admin,减掉[900.0000]USDT', '2019-10-25 10:12:13');
INSERT INTO `trade_detail` VALUES ('523', '303', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '1000.0000', '2.0000', null, '管理后台修改账户额度,修改人：admin,增加[1.0000]MT,增加[900.0000]USDT', '2019-10-25 10:12:52');
INSERT INTO `trade_detail` VALUES ('524', '304', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '100.0000', '1.0000', null, '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT,减掉[900.0000]USDT', '2019-10-25 10:13:16');
INSERT INTO `trade_detail` VALUES ('525', '305', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '1000.0000', '2.0000', null, '管理后台修改账户额度,修改人：admin,增加[1.0000]MT,增加[900.0000]USDT', '2019-10-25 10:14:11');
INSERT INTO `trade_detail` VALUES ('526', '306', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '100.0000', '1.0000', null, '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT,减掉[900.0000]USDT', '2019-10-25 10:16:45');
INSERT INTO `trade_detail` VALUES ('527', '307', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '1000.0000', '2.0000', null, '管理后台修改账户额度,修改人：admin,增加[1.0000]MT,增加[900.0000]USDT', '2019-10-25 10:24:56');
INSERT INTO `trade_detail` VALUES ('528', '308', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '100.0000', '1.0000', null, '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT,减掉[900.0000]USDT', '2019-10-25 10:26:58');
INSERT INTO `trade_detail` VALUES ('529', '309', '189', '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_INVEST_FREEZE', '30.0000', '3507.0000', '3536.8000', '2019-10-25 02:59:16', 'usdt投资冻结', '2019-10-25 10:59:16');
INSERT INTO `trade_detail` VALUES ('530', '309', '189', '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'MT_INVEST_PERSONAL_TAX', '0.1000', '3507.0000', '3536.7000', '2019-10-25 02:59:16', 'mt投资个人税金', '2019-10-25 10:59:16');
INSERT INTO `trade_detail` VALUES ('531', '309', '189', '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'MT_INVEST_ENTERPRISE_TAX', '0.1000', '3507.0000', '3536.6000', '2019-10-25 02:59:16', 'mt投资企业税金', '2019-10-25 10:59:16');
INSERT INTO `trade_detail` VALUES ('532', '310', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '100.0000', '2.0000', null, '管理后台修改账户额度,修改人：admin,增加[1.0000]MT', '2019-10-25 11:10:32');
INSERT INTO `trade_detail` VALUES ('533', '311', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '1000.0000', '2.0000', null, '管理后台修改账户额度,修改人：admin,增加[900.0000]USDT', '2019-10-25 11:11:12');
INSERT INTO `trade_detail` VALUES ('534', '312', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '100.0000', '1.0000', null, '管理后台修改账户额度,修改人：admin', '2019-10-25 11:12:04');
INSERT INTO `trade_detail` VALUES ('535', '313', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '100.0000', '1.0000', null, '管理后台修改账户额度,修改人：admin', '2019-10-25 11:12:04');
INSERT INTO `trade_detail` VALUES ('536', '314', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '1000.0000', '2.0000', null, '管理后台修改账户额度,增加[900.0000]USDT', '2019-10-25 13:39:35');
INSERT INTO `trade_detail` VALUES ('537', '315', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '1000.0000', '2.0000', null, '管理后台修改账户额度,增加[1.0000]MT', '2019-10-25 13:39:35');
INSERT INTO `trade_detail` VALUES ('538', '316', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '900.0000', '100.0000', '1.0000', null, '管理后台修改账户额度,减掉[900.0000]USDT', '2019-10-25 13:41:02');
INSERT INTO `trade_detail` VALUES ('539', '317', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '100.0000', '1.0000', null, '管理后台修改账户额度,减掉[1.0000]MT', '2019-10-25 13:41:02');
INSERT INTO `trade_detail` VALUES ('540', '318', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '90.0000', '10.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin,减掉[90.0000]USDT', '2019-10-25 13:42:28');
INSERT INTO `trade_detail` VALUES ('541', '319', null, null, 'DCCC09F63BCC4FA9BA6B16EBBE97E45C', 'CHANGE_TRAN_PWD', '1.0000', '10.0000', '0.0000', null, '管理后台修改账户额度,修改人：admin,减掉[1.0000]MT', '2019-10-25 13:42:29');
INSERT INTO `trade_detail` VALUES ('542', '320', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:41:11');
INSERT INTO `trade_detail` VALUES ('543', '321', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:41:28');
INSERT INTO `trade_detail` VALUES ('544', '322', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:47:06');
INSERT INTO `trade_detail` VALUES ('545', '323', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:49:17');
INSERT INTO `trade_detail` VALUES ('546', '325', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:49:17');
INSERT INTO `trade_detail` VALUES ('547', '324', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:49:17');
INSERT INTO `trade_detail` VALUES ('548', '327', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:50:47');
INSERT INTO `trade_detail` VALUES ('549', '326', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:50:47');
INSERT INTO `trade_detail` VALUES ('550', '330', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:50:47');
INSERT INTO `trade_detail` VALUES ('551', '329', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:50:47');
INSERT INTO `trade_detail` VALUES ('552', '328', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:50:47');
INSERT INTO `trade_detail` VALUES ('553', '332', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:50:47');
INSERT INTO `trade_detail` VALUES ('554', '331', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:50:47');
INSERT INTO `trade_detail` VALUES ('555', '333', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:52:27');
INSERT INTO `trade_detail` VALUES ('556', '334', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:52:27');
INSERT INTO `trade_detail` VALUES ('557', '335', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:52:27');
INSERT INTO `trade_detail` VALUES ('558', '336', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:52:27');
INSERT INTO `trade_detail` VALUES ('559', '337', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:52:27');
INSERT INTO `trade_detail` VALUES ('560', '338', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:52:28');
INSERT INTO `trade_detail` VALUES ('561', '339', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:52:28');
INSERT INTO `trade_detail` VALUES ('562', '340', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:52:28');
INSERT INTO `trade_detail` VALUES ('563', '341', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:55:14');
INSERT INTO `trade_detail` VALUES ('564', '342', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 17:55:24');
INSERT INTO `trade_detail` VALUES ('565', '343', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 18:00:38');
INSERT INTO `trade_detail` VALUES ('566', '344', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 18:00:38');
INSERT INTO `trade_detail` VALUES ('567', '345', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 18:02:02');
INSERT INTO `trade_detail` VALUES ('568', '346', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 18:02:02');
INSERT INTO `trade_detail` VALUES ('569', '347', null, '12', '1B86C28CDE9F4F548E6839C7C971FB2A', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 18:03:04');
INSERT INTO `trade_detail` VALUES ('570', '348', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '3.9700', '0.0000', '0.0000', null, '投资提取,提取金额：4,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 18:42:13');
INSERT INTO `trade_detail` VALUES ('571', '349', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '4.9700', '0.0000', '0.0000', null, '投资提取,提取金额：5,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 19:05:08');
INSERT INTO `trade_detail` VALUES ('572', '350', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '4.9700', '0.0000', '0.0000', null, '投资提取,提取金额：5,个人所得税:0.0100,企业所得税：0.0200,定额税：0.0000', '2019-10-25 20:32:18');
INSERT INTO `trade_detail` VALUES ('573', '351', null, '9', '80F84F191FC94F32B4706C50072A35D1', 'USDT_EARNINGS', '4.9000', '0.0000', '0.0000', null, '投资提取,提取金额：5,个人所得税:0.0000,企业所得税：0.0000,定额税：0.1000', '2019-10-25 20:41:39');

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
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COMMENT='交易审核表';

-- ----------------------------
-- Records of trade_verify
-- ----------------------------
INSERT INTO `trade_verify` VALUES ('188', null, '282', null, 'PASS', '玩家转账', '2019-10-24 21:31:52', '2019-10-24 21:31:52');
INSERT INTO `trade_verify` VALUES ('189', null, '309', '1', 'WAIT', '待审核', '2019-10-25 10:59:16', '2019-10-25 10:59:16');

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
