/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : laishishui_e_commerce

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 10/01/2022 22:09:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_ecommerce_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_ecommerce_goods`;
CREATE TABLE `t_ecommerce_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_category` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品类别',
  `goods_category` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '品牌分类',
  `goods_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `goods_pic` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '商品图片',
  `goods_description` varchar(512) COLLATE utf8_bin NOT NULL COMMENT '商品描述信息',
  `goods_status` int(11) NOT NULL COMMENT '商品状态',
  `price` int(10) NOT NULL COMMENT '商品价格',
  `supply` bigint(20) NOT NULL COMMENT '总供应量',
  `inventory` bigint(20) NOT NULL COMMENT '库存',
  `goods_property` varchar(1024) COLLATE utf8_bin NOT NULL COMMENT '商品属性',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
