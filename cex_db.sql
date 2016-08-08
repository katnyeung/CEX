/*
SQLyog Community v12.16 (64 bit)
MySQL - 5.7.9-log : Database - cex
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cex` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cex`;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `remark` longtext,
  `type` varchar(50) DEFAULT NULL,
  `bidPrice` decimal(10,0) DEFAULT NULL,
  `buyoutPrice` decimal(10,0) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `rating` int(11) DEFAULT 0,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;




/*Table structure for table `item_tag` */

DROP TABLE IF EXISTS `item_tag`;

CREATE TABLE `item_tag` (
  `itemId` int(11) DEFAULT NULL,
  `tagId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `item_tag` */

/*Table structure for table `item_tag` */

DROP TABLE IF EXISTS `rating_history`;

CREATE TABLE `rating_history` (
  `itemId` int(11) DEFAULT NULL,
  `tagId` int(11) DEFAULT NULL,
  `type`  varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `tagId` int(11) NOT NULL AUTO_INCREMENT,
  `value` text,
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `itemCount` int(11) DEFAULT NULL,
  `goodRatingCount` int(11) DEFAULT NULL,
  `notGoodRatingCount` int(11) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
