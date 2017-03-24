/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : crane1.0

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-03-20 22:28:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crane_sign_in
-- ----------------------------
DROP TABLE IF EXISTS `crane_sign_in`;
CREATE TABLE `crane_sign_in` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `sign_date` date NOT NULL DEFAULT '0000-00-00',
  `sign_time` datetime DEFAULT NULL COMMENT '签到时间',
  `source_from` varchar(1) DEFAULT 'P' COMMENT '来源 A:安卓 P:PC',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_icon` varchar(50) DEFAULT NULL COMMENT '用户小图像',
  PRIMARY KEY (`user_id`,`sign_date`),
  KEY `signin_index_userid` (`user_id`),
  KEY `signin_index_signdate` (`sign_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for crane_spit_slot
-- ----------------------------
DROP TABLE IF EXISTS `crane_spit_slot`;
CREATE TABLE `crane_spit_slot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `image_url` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `image_url_small` varchar(600) DEFAULT NULL COMMENT '用户小头像',
  `music_url` varchar(500) DEFAULT NULL COMMENT '音乐连接URL',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `source_from` varchar(1) DEFAULT 'P' COMMENT '来源 A:安卓 P:PC',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_icon` varchar(50) DEFAULT NULL COMMENT '用户小图像',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数',
  `like_count` int(11) DEFAULT '0' COMMENT '喜欢数',
  PRIMARY KEY (`id`),
  KEY `blast_index_id` (`id`),
  KEY `blast_index_userid` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2567 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for crane_spit_slot_comment
-- ----------------------------
DROP TABLE IF EXISTS `crane_spit_slot_comment`;
CREATE TABLE `crane_spit_slot_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '吐槽评论ID',
  `spit_slot_id` int(11) DEFAULT NULL COMMENT '吐槽id',
  `content` varchar(1000) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `source_from` char(1) DEFAULT 'P',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_icon` varchar(50) DEFAULT NULL COMMENT '用户小图像',
  PRIMARY KEY (`id`),
  KEY `blastcomment_index_blastid` (`spit_slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for crane_user
-- ----------------------------
DROP TABLE IF EXISTS `crane_user`;
CREATE TABLE `crane_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一ID',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `user_icon` varchar(50) DEFAULT NULL COMMENT '用户小图像',
  `user_bg` varchar(100) DEFAULT '0' COMMENT '背景图',
  `birthday` varchar(50) DEFAULT NULL COMMENT '生日',
  `sex` varchar(1) DEFAULT 'M' COMMENT '性别 M男 F女',
  `characters` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `mark` int(11) DEFAULT '0' COMMENT '积分',
  `address` varchar(50) DEFAULT NULL COMMENT '籍贯',
  `work` varchar(50) DEFAULT NULL COMMENT '职业',
  `register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `activation_code` varchar(25) DEFAULT NULL COMMENT '激活码',
  PRIMARY KEY (`user_id`),
  KEY `user_index_userid` (`user_id`),
  KEY `user_index_username` (`user_name`),
  KEY `user_index_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
