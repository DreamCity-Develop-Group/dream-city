/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : setting

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-09-16 20:00:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` bigint(20) NOT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件访问路径',
  `file_type` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件类型',
  `is_valid` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用的',
  `from_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '添加人id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_file
-- ----------------------------

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
INSERT INTO `t_game_setting` VALUES ('1', '123', 'game', 'true', '1', null, '2019-09-16 08:25:39');
INSERT INTO `t_game_setting` VALUES ('2', '123', 'bg', 'false', '1', null, '2019-09-16 08:26:33');
