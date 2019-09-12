/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : player

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-09-12 19:46:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_friends
-- ----------------------------
DROP TABLE IF EXISTS `t_friends`;
CREATE TABLE `t_friends` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '用户id',
  `friend_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '好友id',
  `agree` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '同意添加',
  `invite` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邀请码（来自friend_id）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='好友';

-- ----------------------------
-- Records of t_friends
-- ----------------------------
INSERT INTO `t_friends` VALUES ('1', '123', '17879502056', '1', null, '2019-09-11 17:04:40', '2019-09-12 09:44:19');
INSERT INTO `t_friends` VALUES ('2', '123', '17879502056', '0', '123', '2019-09-12 17:21:09', null);
INSERT INTO `t_friends` VALUES ('3', '123', '1231', '0', null, '2019-09-12 09:57:41', null);

-- ----------------------------
-- Table structure for t_likes
-- ----------------------------
DROP TABLE IF EXISTS `t_likes`;
CREATE TABLE `t_likes` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL,
  `likes` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '点赞次数',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞';

-- ----------------------------
-- Records of t_likes
-- ----------------------------

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '玩家id',
  `imei` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '设备识别码',
  `ip` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'ip地址',
  `type` varchar(255) DEFAULT NULL,
  `descr` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('1', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 16:05:26');
INSERT INTO `t_login_log` VALUES ('2', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 16:05:26');
INSERT INTO `t_login_log` VALUES ('3', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 16:06:39');
INSERT INTO `t_login_log` VALUES ('4', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 16:06:56');
INSERT INTO `t_login_log` VALUES ('5', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 16:07:04');
INSERT INTO `t_login_log` VALUES ('6', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:01:31');
INSERT INTO `t_login_log` VALUES ('7', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:01:31');
INSERT INTO `t_login_log` VALUES ('8', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:01:53');
INSERT INTO `t_login_log` VALUES ('9', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:01:58');
INSERT INTO `t_login_log` VALUES ('10', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:02:06');
INSERT INTO `t_login_log` VALUES ('11', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:18:27');
INSERT INTO `t_login_log` VALUES ('12', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:18:44');
INSERT INTO `t_login_log` VALUES ('13', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:18:51');
INSERT INTO `t_login_log` VALUES ('14', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:19:06');
INSERT INTO `t_login_log` VALUES ('15', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:19:17');
INSERT INTO `t_login_log` VALUES ('16', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:20:54');
INSERT INTO `t_login_log` VALUES ('17', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:20:54');
INSERT INTO `t_login_log` VALUES ('18', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:21:03');
INSERT INTO `t_login_log` VALUES ('19', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:21:05');
INSERT INTO `t_login_log` VALUES ('20', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:21:07');
INSERT INTO `t_login_log` VALUES ('21', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:21:23');
INSERT INTO `t_login_log` VALUES ('22', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:21:26');
INSERT INTO `t_login_log` VALUES ('23', 'FB10A0CD79024123800455E7685F89C1', null, null, 'login', null, '2019-09-11 17:21:35');
INSERT INTO `t_login_log` VALUES ('24', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 11:52:41');
INSERT INTO `t_login_log` VALUES ('25', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 11:52:41');
INSERT INTO `t_login_log` VALUES ('26', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 11:52:57');
INSERT INTO `t_login_log` VALUES ('27', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 11:55:55');
INSERT INTO `t_login_log` VALUES ('28', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:02:44');
INSERT INTO `t_login_log` VALUES ('29', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:02:46');
INSERT INTO `t_login_log` VALUES ('30', '727311B48CB4404EB9CACA6B9B235251', null, null, 'login', null, '2019-09-12 13:04:22');
INSERT INTO `t_login_log` VALUES ('31', '8ABBDDFE576647F88B055214259F1E1B', null, null, 'login', null, '2019-09-12 13:05:50');
INSERT INTO `t_login_log` VALUES ('32', '8ABBDDFE576647F88B055214259F1E1B', null, null, 'login', null, '2019-09-12 13:06:48');
INSERT INTO `t_login_log` VALUES ('33', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:08:09');
INSERT INTO `t_login_log` VALUES ('34', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:44:27');
INSERT INTO `t_login_log` VALUES ('35', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:45:03');
INSERT INTO `t_login_log` VALUES ('36', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:45:27');
INSERT INTO `t_login_log` VALUES ('37', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:46:15');
INSERT INTO `t_login_log` VALUES ('38', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:46:15');
INSERT INTO `t_login_log` VALUES ('39', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:46:26');
INSERT INTO `t_login_log` VALUES ('40', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:48:28');
INSERT INTO `t_login_log` VALUES ('41', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:50:02');
INSERT INTO `t_login_log` VALUES ('42', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 13:50:39');
INSERT INTO `t_login_log` VALUES ('43', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:13:27');
INSERT INTO `t_login_log` VALUES ('44', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:13:42');
INSERT INTO `t_login_log` VALUES ('45', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:14:24');
INSERT INTO `t_login_log` VALUES ('46', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:14:50');
INSERT INTO `t_login_log` VALUES ('47', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:19:37');
INSERT INTO `t_login_log` VALUES ('48', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:19:37');
INSERT INTO `t_login_log` VALUES ('49', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:19:37');
INSERT INTO `t_login_log` VALUES ('50', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:19:37');
INSERT INTO `t_login_log` VALUES ('51', 'FD826FE2E378445594D23CA84C0C485D', null, null, 'login', null, '2019-09-12 14:19:37');
INSERT INTO `t_login_log` VALUES ('52', null, null, null, 'login', null, '2019-09-12 14:29:01');
INSERT INTO `t_login_log` VALUES ('53', '035510F21E1F45FF87A25AE9C466BEFC', null, null, 'login', null, '2019-09-12 14:30:36');
INSERT INTO `t_login_log` VALUES ('54', '035510F21E1F45FF87A25AE9C466BEFC', null, null, 'login', null, '2019-09-12 14:30:47');

-- ----------------------------
-- Table structure for t_player
-- ----------------------------
DROP TABLE IF EXISTS `t_player`;
CREATE TABLE `t_player` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '玩家id',
  `player_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `player_nick` varchar(50) NOT NULL COMMENT '昵称',
  `player_pass` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `player_trade_pass` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '交易密码',
  `player_invite` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邀请码',
  `create_time` datetime DEFAULT NULL COMMENT ' 创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COMMENT='用户表（玩家）';

-- ----------------------------
-- Records of t_player
-- ----------------------------
INSERT INTO `t_player` VALUES ('99', '55ACB2722E33436F81040D0C8F257BF3', '17879502056', 'sd', '12345678', null, null, '2019-09-11 12:23:10', null);
INSERT INTO `t_player` VALUES ('100', 'FD826FE2E378445594D23CA84C0C485D', '123', '123', '123', null, '', '2019-09-12 02:37:37', null);
INSERT INTO `t_player` VALUES ('102', 'A1351FDFF0E344908A6EBBDDAC7D506D', '17856454567', 'qw', '12345678', null, '', '2019-09-12 03:29:52', null);
INSERT INTO `t_player` VALUES ('103', '758DEB6C0A854D569FF2FC8AC9B422C9', '17856457895', 'qwe', '12345678', null, '', '2019-09-12 03:33:02', null);
INSERT INTO `t_player` VALUES ('104', '727311B48CB4404EB9CACA6B9B235251', '17845685468', 'wqe', '12345678', null, '', '2019-09-12 03:38:00', null);
INSERT INTO `t_player` VALUES ('105', '8ABBDDFE576647F88B055214259F1E1B', '17879502059', 'wqe', '12345678', null, '', '2019-09-12 05:05:17', null);
INSERT INTO `t_player` VALUES ('106', '035510F21E1F45FF87A25AE9C466BEFC', '17879502055', 'joj', '12345678', null, '', '2019-09-12 06:30:04', null);

-- ----------------------------
-- Table structure for t_player_ext
-- ----------------------------
DROP TABLE IF EXISTS `t_player_ext`;
CREATE TABLE `t_player_ext` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '用户id',
  `friendlink` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '主页入口',
  `imgurl` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像地址',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展表（玩家）';

-- ----------------------------
-- Records of t_player_ext
-- ----------------------------

-- ----------------------------
-- Table structure for t_player_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_player_grade`;
CREATE TABLE `t_player_grade` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '玩家id',
  `grade` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '玩家等级',
  `integral` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '积分',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='玩家等级(会员/商会等级)';

-- ----------------------------
-- Records of t_player_grade
-- ----------------------------
