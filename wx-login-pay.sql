/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 8.0.16 : Database - wx-login-pay
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wx-login-pay` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wx-login-pay`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(256) NOT NULL COMMENT '用户名',
  `openid` varchar(256) NOT NULL,
  `head_img` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `video_title` varchar(256) NOT NULL,
  `video_cover` varchar(256) NOT NULL,
  `price` int(10) NOT NULL,
  `author` varchar(256) NOT NULL,
  `summary` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `video_order` */

DROP TABLE IF EXISTS `video_order`;

CREATE TABLE `video_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `out_trade_no` varchar(32) NOT NULL COMMENT '订单号',
  `openid` varchar(256) DEFAULT NULL,
  `user_id` int(10) NOT NULL,
  `user_name` varchar(256) NOT NULL,
  `ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `video_id` int(10) NOT NULL,
  `video_title` varchar(256) NOT NULL,
  `video_cover` varchar(256) NOT NULL,
  `video_price` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `notify_time` datetime DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
