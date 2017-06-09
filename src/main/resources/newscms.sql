/*
Navicat MySQL Data Transfer

Source Server         : MySNS
Source Server Version : 50173
Source Host           : 192.168.207.128:3306
Source Database       : newscms

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-06-09 18:11:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `body` longtext,
  `create_date` datetime NOT NULL,
  `create_user` int(10) unsigned NOT NULL,
  `update_date` datetime NOT NULL,
  `update_user` int(10) unsigned NOT NULL,
  `remove` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '测试测试测试', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2017-06-08 16:40:14', '5', '2017-06-08 16:40:19', '5', '\0');
INSERT INTO `article` VALUES ('2', '222', '3333333', '2017-06-08 16:40:35', '6', '2017-06-08 16:40:39', '6', '\0');
INSERT INTO `article` VALUES ('3', '有内容的小新闻', '这是一篇有深度的内容', '2017-06-06 11:05:32', '9', '2017-06-12 11:05:46', '9', '\0');

-- ----------------------------
-- Table structure for user_basic
-- ----------------------------
DROP TABLE IF EXISTS `user_basic`;
CREATE TABLE `user_basic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `type` smallint(5) unsigned NOT NULL COMMENT '0---管理员 1---普通用户',
  `name` varchar(20) NOT NULL,
  `remove` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_basic
-- ----------------------------
INSERT INTO `user_basic` VALUES ('5', 'admin', '21232f297a57a5a74389', '0', '超级管理员', '\0');
INSERT INTO `user_basic` VALUES ('6', 'admin2', '21232f297a57a5a74389', '0', '管理员1', '\0');
INSERT INTO `user_basic` VALUES ('7', 'admin3', '21232f297a57a5a74389', '0', '管理员2', '\0');
INSERT INTO `user_basic` VALUES ('8', 'user1', 'e10adc3949ba59abbe56', '1', '李狗蛋', '\0');
INSERT INTO `user_basic` VALUES ('9', 'user2', 'e10adc3949ba59abbe56', '1', '赵日天', '\0');
INSERT INTO `user_basic` VALUES ('10', 'user3', 'e10adc3949ba59abbe56', '1', '香港记者', '\0');

-- ----------------------------
-- View structure for v_article
-- ----------------------------
DROP VIEW IF EXISTS `v_article`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`192.168.207.1` SQL SECURITY DEFINER VIEW `v_article` AS select `A`.`id` AS `id`,`A`.`title` AS `title`,`A`.`body` AS `body`,`A`.`create_date` AS `create_date`,`u1`.`name` AS `create_user_name`,`A`.`update_date` AS `update_date`,`u2`.`name` AS `update_user_name`,`A`.`create_user` AS `create_user_id`,`A`.`update_user` AS `update_user_id`,`A`.`remove` AS `remove`,`u2`.`type` AS `update_user_type` from ((`article` `A` left join `user_basic` `u1` on((`A`.`create_user` = `u1`.`id`))) left join `user_basic` `u2` on((`A`.`update_user` = `u2`.`id`))) order by `A`.`update_date` ;
