/*
Navicat MySQL Data Transfer

Source Server         : spring5_jdbc
Source Server Version : 50737
Source Host           : 192.168.83.161:3306
Source Database       : llu_cat

Target Server Type    : MYSQL
Target Server Version : 50737
File Encoding         : 65001

Date: 2023-01-08 17:21:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `depart_item`
-- ----------------------------
DROP TABLE IF EXISTS `depart_item`;
CREATE TABLE `depart_item` (
  `item_id` int(10) NOT NULL AUTO_INCREMENT,
  `item_num` int(10) NOT NULL,
  `item_name` varchar(20) NOT NULL,
  `item_simp` varchar(20) NOT NULL,
  `depart_num` int(10) NOT NULL,
  `depart_name` varchar(30) DEFAULT NULL,
  `item_remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `num_unique` (`item_num`),
  KEY `depart_item` (`depart_num`),
  KEY `depart_item2` (`depart_name`),
  CONSTRAINT `depart_item` FOREIGN KEY (`depart_num`) REFERENCES `phy_depart` (`depart_num`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `depart_item2` FOREIGN KEY (`depart_name`) REFERENCES `phy_depart` (`depart_name`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depart_item
-- ----------------------------
INSERT INTO `depart_item` VALUES ('1', '309001', '身高', 'SG', '309', '一般检查', null);
INSERT INTO `depart_item` VALUES ('2', '309002', '体重', 'TZ', '309', '一般检查', null);
INSERT INTO `depart_item` VALUES ('3', '309003', '腰围', 'YW', '309', '一般检查', null);
INSERT INTO `depart_item` VALUES ('4', '309004', '臀围', 'TW', '309', '一般检查', null);
INSERT INTO `depart_item` VALUES ('5', '310001', '胸廓', 'XK', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('6', '310002', '肺与胸膜', 'FYXM', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('7', '310003', '脉搏', 'MB', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('8', '310004', '血压', 'MB', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('9', '310005', '心脏', 'XZ', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('10', '310006', '肝脏', 'GZ', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('11', '310007', '脾脏', 'PZ', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('12', '310008', '肾脏', 'SZ', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('13', '310009', '腹部', 'FB', '310', '内科', null);
INSERT INTO `depart_item` VALUES ('17', '311002', '右视力', 'YSL', '311', '眼科', null);
INSERT INTO `depart_item` VALUES ('18', '311003', '色觉', 'SJ', '311', '眼科', null);
INSERT INTO `depart_item` VALUES ('19', '311001', '左视力', 'zsl', '311', '眼科', '');
INSERT INTO `depart_item` VALUES ('20', '312001', 'dsfdsf', 'sdfsd', '312', '外科', '');

-- ----------------------------
-- Table structure for `inspect__table`
-- ----------------------------
DROP TABLE IF EXISTS `inspect__table`;
CREATE TABLE `inspect__table` (
  `inspect_id` int(10) NOT NULL AUTO_INCREMENT,
  `registration_id` int(20) NOT NULL,
  `set_id` int(10) NOT NULL,
  `depart_id` int(10) NOT NULL,
  `examine` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  `inspect_date` varchar(30) DEFAULT NULL,
  `data` varchar(1000) DEFAULT NULL,
  `num` int(20) DEFAULT NULL,
  PRIMARY KEY (`inspect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspect__table
-- ----------------------------
INSERT INTO `inspect__table` VALUES ('15', '2022032513', '1', '309', '1', '2022-05-08', '{\"体重\":\"130\",\"腰围\":\"60\",\"臀围\":\"80\",\"身高\":\"180\"}', '4');
INSERT INTO `inspect__table` VALUES ('16', '2022032513', '1', '310', '1', '2022-05-08', '{\"心脏\":\"正常\",\"肺与胸膜\":\"正常\",\"脾脏\":\"正常\",\"肾脏\":\"正常\",\"肝脏\":\"正常\",\"胸廓\":\"正常\",\"脉搏\":\"正常\",\"血压\":\"正常\",\"腹部\":\"正常\"}', '9');
INSERT INTO `inspect__table` VALUES ('17', '2022032513', '1', '311', '1', '2022-05-08', '{\"右视力\":\"正常\",\"色觉\":\"正常\",\"左视力\":\"正常\"}', '3');
INSERT INTO `inspect__table` VALUES ('18', '2022032513', '1', '312', '1', '2022-05-08', '{\"dsfdsf\":\"\"}', '1');
INSERT INTO `inspect__table` VALUES ('19', '2022032514', '1', '309', '1', '2022-05-08', '{\"体重\":\"\",\"腰围\":\"\",\"臀围\":\"\",\"身高\":\"\"}', '4');
INSERT INTO `inspect__table` VALUES ('20', '2022032514', '1', '310', '0', null, null, null);
INSERT INTO `inspect__table` VALUES ('21', '2022032514', '1', '311', '0', null, null, null);
INSERT INTO `inspect__table` VALUES ('22', '2022032514', '1', '312', '0', null, null, null);

-- ----------------------------
-- Table structure for `phy_depart`
-- ----------------------------
DROP TABLE IF EXISTS `phy_depart`;
CREATE TABLE `phy_depart` (
  `depart_id` int(10) NOT NULL AUTO_INCREMENT,
  `depart_num` int(10) NOT NULL,
  `depart_name` varchar(20) NOT NULL,
  `depart_simp` varchar(20) DEFAULT NULL,
  `depart_type` varchar(10) DEFAULT NULL,
  `depart_remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`depart_id`),
  KEY `depart_num` (`depart_num`),
  KEY `depart_name` (`depart_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phy_depart
-- ----------------------------
INSERT INTO `phy_depart` VALUES ('1', '309', '一般检查', 'YBJC', '普通', '一般检查');
INSERT INTO `phy_depart` VALUES ('2', '310', '内科', 'NK', '普通', null);
INSERT INTO `phy_depart` VALUES ('3', '311', '眼科', 'YK', '普通', null);
INSERT INTO `phy_depart` VALUES ('4', '312', '外科', 'WK', '普通', null);
INSERT INTO `phy_depart` VALUES ('5', '313', '耳鼻喉科', 'EBHK', '普通', null);
INSERT INTO `phy_depart` VALUES ('6', '314', '口腔科', 'KQK', '普通', null);
INSERT INTO `phy_depart` VALUES ('7', '315', '妇科', 'FK', '普通', null);
INSERT INTO `phy_depart` VALUES ('10', '316', '乳透室', 'RTS', 'PACS', null);
INSERT INTO `phy_depart` VALUES ('12', '317', '心电图室', 'XDTS', 'PACS', '');

-- ----------------------------
-- Table structure for `phy_ex_registration`
-- ----------------------------
DROP TABLE IF EXISTS `phy_ex_registration`;
CREATE TABLE `phy_ex_registration` (
  `registration_id` int(10) NOT NULL AUTO_INCREMENT,
  `phy_id` int(10) NOT NULL,
  `phy_name` varchar(30) NOT NULL,
  `registration_name` varchar(20) NOT NULL,
  `registration_phone` varchar(11) NOT NULL,
  `registration_gender` varchar(4) NOT NULL,
  `registration_birth` varchar(10) NOT NULL,
  `sub_phy_check_date` varchar(10) NOT NULL,
  `registration_date` varchar(30) NOT NULL,
  `inspect_date` varchar(30) DEFAULT NULL,
  `registration_cert` varchar(18) NOT NULL,
  `registration_email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`registration_id`),
  KEY `index_one` (`phy_id`),
  CONSTRAINT `phy_ex_registration_ibfk_1` FOREIGN KEY (`phy_id`) REFERENCES `phy_set` (`phy_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2022032515 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phy_ex_registration
-- ----------------------------
INSERT INTO `phy_ex_registration` VALUES ('2022032513', '1', '学生', 'sdjkkl', '18334594878', '男', '2022-03-04', '2022-03-28', '2022-03-25', null, '14019238912812', 'sdfj@153.com');
INSERT INTO `phy_ex_registration` VALUES ('2022032514', '1', '学生', '韩絾筱', '18334219080', '男', '2022-03-17', '2022-03-10', '2022-03-25', null, '140428199503083920', 'jklsfd@qq.com');

-- ----------------------------
-- Table structure for `phy_set`
-- ----------------------------
DROP TABLE IF EXISTS `phy_set`;
CREATE TABLE `phy_set` (
  `phy_id` int(10) NOT NULL AUTO_INCREMENT,
  `phy_name` varchar(20) NOT NULL,
  `phy_check_num` int(3) DEFAULT NULL,
  `phy_check_type_one` varchar(20) DEFAULT NULL,
  `phy_check_type_two` varchar(20) DEFAULT NULL,
  `phy_check_type_three` varchar(20) DEFAULT NULL,
  `phy_check_type_four` varchar(20) DEFAULT NULL,
  `phy_check_type_five` varchar(20) DEFAULT NULL,
  `phy_check_remark` varchar(100) DEFAULT NULL,
  `phy_price` int(10) DEFAULT NULL,
  PRIMARY KEY (`phy_id`),
  KEY `phy_check_num` (`phy_check_num`),
  KEY `phy_name` (`phy_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phy_set
-- ----------------------------
INSERT INTO `phy_set` VALUES ('1', '学生', '1', '基础检查', '', '', '', '', '', '137');
INSERT INTO `phy_set` VALUES ('2', '女性体检方案1', '3', '妇科', '乳腺', '基础检查', '', '', '', '753');

-- ----------------------------
-- Table structure for `set_depart_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `set_depart_mapping`;
CREATE TABLE `set_depart_mapping` (
  `set_depart_mapping_id` int(10) NOT NULL AUTO_INCREMENT,
  `phy_set_num` int(10) NOT NULL,
  `phy_set_name` varchar(30) NOT NULL,
  `depart_num` int(10) NOT NULL,
  `depart_name` varchar(30) NOT NULL,
  PRIMARY KEY (`set_depart_mapping_id`),
  KEY `mapping2` (`depart_num`),
  KEY `mapping1` (`phy_set_num`),
  KEY `mapping3` (`phy_set_name`),
  KEY `mapping4` (`depart_name`),
  CONSTRAINT `mapping1` FOREIGN KEY (`phy_set_num`) REFERENCES `phy_set` (`phy_id`) ON DELETE NO ACTION,
  CONSTRAINT `mapping2` FOREIGN KEY (`depart_num`) REFERENCES `phy_depart` (`depart_num`) ON DELETE NO ACTION,
  CONSTRAINT `mapping3` FOREIGN KEY (`phy_set_name`) REFERENCES `phy_set` (`phy_name`) ON DELETE NO ACTION,
  CONSTRAINT `mapping4` FOREIGN KEY (`depart_name`) REFERENCES `phy_depart` (`depart_name`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of set_depart_mapping
-- ----------------------------
INSERT INTO `set_depart_mapping` VALUES ('32', '2', '女性体检方案1', '309', '一般检查');
INSERT INTO `set_depart_mapping` VALUES ('33', '2', '女性体检方案1', '313', '耳鼻喉科');
INSERT INTO `set_depart_mapping` VALUES ('34', '2', '女性体检方案1', '314', '口腔科');
INSERT INTO `set_depart_mapping` VALUES ('35', '2', '女性体检方案1', '315', '妇科');
INSERT INTO `set_depart_mapping` VALUES ('36', '2', '女性体检方案1', '316', '乳透室');
INSERT INTO `set_depart_mapping` VALUES ('37', '2', '女性体检方案1', '317', '心电图室');
INSERT INTO `set_depart_mapping` VALUES ('38', '1', '学生', '309', '一般检查');
INSERT INTO `set_depart_mapping` VALUES ('39', '1', '学生', '310', '内科');
INSERT INTO `set_depart_mapping` VALUES ('40', '1', '学生', '311', '眼科');
INSERT INTO `set_depart_mapping` VALUES ('41', '1', '学生', '312', '外科');
INSERT INTO `set_depart_mapping` VALUES ('42', '1', '学生', '313', '耳鼻喉科');

-- ----------------------------
-- Table structure for `sign_admin`
-- ----------------------------
DROP TABLE IF EXISTS `sign_admin`;
CREATE TABLE `sign_admin` (
  `admin_id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_num` double(11,0) DEFAULT NULL,
  `admin_name` varchar(20) NOT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `phone` double NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(34) DEFAULT NULL,
  `depart` varchar(8) DEFAULT NULL,
  `major` varchar(16) DEFAULT NULL,
  `classes` varchar(22) DEFAULT NULL,
  `entrance_date` date DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_admin
-- ----------------------------
INSERT INTO `sign_admin` VALUES ('1', '10181106321', '悦', '女', '13193785066', '785066', ' yue@qq.com', '计算机系', '计算机科学与技术', '计科（专升本）2003', '2018-09-02');
INSERT INTO `sign_admin` VALUES ('53', '20201104151', '韩絾絾', '男', '18935177972', '123456', 'sjdl@163.com', 'sdjflsj', 'sdljflk', 'dslkjsd', '2022-03-11');

-- ----------------------------
-- Table structure for `sign_user`
-- ----------------------------
DROP TABLE IF EXISTS `sign_user`;
CREATE TABLE `sign_user` (
  `stu_id` int(10) NOT NULL AUTO_INCREMENT,
  `stu_num` double(11,0) DEFAULT NULL,
  `stu_name` varchar(20) NOT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `phone` double NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(34) DEFAULT NULL,
  `depart` varchar(8) DEFAULT NULL,
  `major` varchar(16) DEFAULT NULL,
  `classes` varchar(22) DEFAULT NULL,
  `entrance_date` date DEFAULT NULL,
  `stu_power` int(1) unsigned zerofill NOT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_user
-- ----------------------------
INSERT INTO `sign_user` VALUES ('8', '20201104107', '韩絾', '男', '18034913806', '654321', 'c_liberty@163.com', '计算机系', '计算机科学与技术', '计科（专升本）2003', '2020-10-12', '1');
INSERT INTO `sign_user` VALUES ('50', '20201104150', '韩絾筱', '男', '18034913807', '123456', 'cheng@163.com', '计算机系', '计算机科学与技术', '2003班', '2022-03-22', '0');
INSERT INTO `sign_user` VALUES ('52', '10181106321', '悦', '女', '13193785066', '785066', 'yue@qq.com', 'sdsdf', 'sdfd', 'sdf', '2022-06-07', '0');

-- ----------------------------
-- Table structure for `sub_phy`
-- ----------------------------
DROP TABLE IF EXISTS `sub_phy`;
CREATE TABLE `sub_phy` (
  `sub_phy_id` int(10) NOT NULL AUTO_INCREMENT,
  `phy_id` int(10) NOT NULL,
  `phy_name` varchar(30) NOT NULL,
  `sub_phy_name` varchar(20) NOT NULL,
  `sub_phy_phone` varchar(11) NOT NULL,
  `sub_phy_gender` varchar(4) NOT NULL,
  `sub_phy_birth` varchar(10) NOT NULL,
  `sub_phy_date` varchar(10) NOT NULL,
  `sub_phy_cert` varchar(18) NOT NULL,
  `sub_phy_email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sub_phy_id`),
  KEY `index_one` (`phy_id`),
  CONSTRAINT `index_one` FOREIGN KEY (`phy_id`) REFERENCES `phy_set` (`phy_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_phy
-- ----------------------------
