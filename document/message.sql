/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : message

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-09-12 19:46:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_auth_code
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_code`;
CREATE TABLE `t_auth_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '认证码',
  `phone` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户id',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='认证码';

-- ----------------------------
-- Records of t_auth_code
-- ----------------------------
INSERT INTO `t_auth_code` VALUES ('1', '1234', '12345678989', '1213', '2019-09-10 11:55:42');
INSERT INTO `t_auth_code` VALUES ('2', '1213', '12345678989', '1213', '2019-09-11 11:56:07');
INSERT INTO `t_auth_code` VALUES ('3', '125807', '123', null, '2019-09-11 04:28:46');
INSERT INTO `t_auth_code` VALUES ('4', '813534', '123', null, '2019-09-11 04:28:47');
INSERT INTO `t_auth_code` VALUES ('5', '678816', '123', null, '2019-09-11 04:29:42');
INSERT INTO `t_auth_code` VALUES ('6', '699692', '123', null, '2019-09-11 04:30:08');
INSERT INTO `t_auth_code` VALUES ('7', '991977', '123', null, '2019-09-11 04:41:47');
INSERT INTO `t_auth_code` VALUES ('8', '950884', '123', null, '2019-09-11 04:42:14');
INSERT INTO `t_auth_code` VALUES ('9', '553626', '1231', null, '2019-09-12 07:13:25');
INSERT INTO `t_auth_code` VALUES ('10', '757543', '1231', null, '2019-09-12 07:13:25');


CREATE TABLE `t_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `friend_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `content` varchar(600) DEFAULT NULL COMMENT '消息内容',
  `have_read` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0未读，1已读',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;













