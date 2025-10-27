/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - db_spring
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_spring` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `db_spring`;

/*Table structure for table `customers` */

DROP TABLE IF EXISTS `customers`;

CREATE TABLE `customers` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `saldo` varchar(255) DEFAULT NULL,
  `is_active` enum('1','0') DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `created_by` int(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `updated_by` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `customers` */

insert  into `customers`(`id`,`name`,`address`,`phone`,`email`,`saldo`,`is_active`,`created`,`created_by`,`updated`,`updated_by`) values 
(1,'Adit','Course-net','08765665456','adit@gmail.com','195000','1','2025-10-27 21:28:39',1,'2025-10-27 21:28:51',1);

/*Table structure for table `transaction_customer` */

DROP TABLE IF EXISTS `transaction_customer`;

CREATE TABLE `transaction_customer` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(1) DEFAULT NULL,
  `transaction_id` int(1) DEFAULT NULL,
  `nominal` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `transaction_customer` */

insert  into `transaction_customer`(`id`,`customer_id`,`transaction_id`,`nominal`) values 
(1,1,1,'5');

/*Table structure for table `transaction_history` */

DROP TABLE IF EXISTS `transaction_history`;

CREATE TABLE `transaction_history` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(1) DEFAULT NULL,
  `transaction_id` int(1) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `created_by` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `transaction_history` */

/*Table structure for table `transactions` */

DROP TABLE IF EXISTS `transactions`;

CREATE TABLE `transactions` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `category` enum('pulsa','token') DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `created_by` int(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `updated_by` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `transactions` */

insert  into `transactions`(`id`,`name`,`price`,`category`,`created`,`created_by`,`updated`,`updated_by`) values 
(1,'iM3 Pulsa 20 GB','50000','pulsa','2025-10-27 21:27:20',1,'2025-10-27 21:27:26',1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `_token` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `users` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
