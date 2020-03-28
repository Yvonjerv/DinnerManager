/*
Navicat MySQL Data Transfer

Source Server         : mysql_connect
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : dinnermanagedb

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2020-03-28 10:20:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` varchar(20) NOT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('yvon', '1234');
INSERT INTO `admin` VALUES ('zhangjs', '1234');

-- ----------------------------
-- Table structure for `dinnerdata`
-- ----------------------------
DROP TABLE IF EXISTS `dinnerdata`;
CREATE TABLE `dinnerdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dtime` datetime DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  `totalaccount` double DEFAULT NULL,
  `participant` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dinnerdata
-- ----------------------------

-- ----------------------------
-- Table structure for `rechargedata`
-- ----------------------------
DROP TABLE IF EXISTS `rechargedata`;
CREATE TABLE `rechargedata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `actime` datetime DEFAULT NULL,
  `account` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_username` (`username`) USING BTREE,
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rechargedata
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE,
  KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('yvon', '2000');
INSERT INTO `user` VALUES ('Zhang Jin Sheng', '2000');
