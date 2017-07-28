/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : projecttemp

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2017-07-28 18:04:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `userName` varchar(30) NOT NULL COMMENT '用户姓名',
  `userAge` int(10) unsigned DEFAULT NULL COMMENT '用户年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('7', '23', '2');
INSERT INTO `t_user` VALUES ('8', 'user', '33');
INSERT INTO `t_user` VALUES ('9', '王瑞儿', '3');
INSERT INTO `t_user` VALUES ('10', 'afasdf', '1');
INSERT INTO `t_user` VALUES ('11', 'daee', '32');
