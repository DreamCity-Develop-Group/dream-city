/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : setting

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-09-12 19:46:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_game_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_game_setting`;
CREATE TABLE `t_game_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '玩家id',
  `type` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设置类型',
  `val` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设置值',
  `status` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='游戏设置';

-- ----------------------------
-- Records of t_game_setting
-- ----------------------------
INSERT INTO `t_game_setting` VALUES ('1', '123', 'game', 'false', '1', null, '2019-09-10 09:23:06');
INSERT INTO `t_game_setting` VALUES ('2', '123', 'bg', 'true', '1', null, null);
