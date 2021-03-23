/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.23 : Database - bookstoredb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookstoredb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `bookstoredb`;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `Full_Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2633679 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `author` */

/*Table structure for table `authorbook` */

DROP TABLE IF EXISTS `authorbook`;

CREATE TABLE `authorbook` (
  `Author_Id` bigint NOT NULL,
  `Book_Id` bigint NOT NULL,
  PRIMARY KEY (`Author_Id`,`Book_Id`),
  KEY `book_id` (`Book_Id`),
  CONSTRAINT `authorbook_ibfk_1` FOREIGN KEY (`Author_Id`) REFERENCES `author` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `authorbook_ibfk_2` FOREIGN KEY (`Book_Id`) REFERENCES `book` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `authorbook` */

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `Book_Title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Year_Of_Publication` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Publisher_Id` bigint DEFAULT NULL,
  `Pic_Url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `publisher_id` (`Publisher_Id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`Publisher_Id`) REFERENCES `publisher` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4309135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

/*Table structure for table `bookratings` */

DROP TABLE IF EXISTS `bookratings`;

CREATE TABLE `bookratings` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `Book_Id` bigint DEFAULT NULL,
  `User_Id` bigint DEFAULT NULL,
  `Rate` double DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `book_id` (`Book_Id`),
  KEY `user_id` (`User_Id`),
  CONSTRAINT `bookratings_ibfk_1` FOREIGN KEY (`Book_Id`) REFERENCES `book` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookratings_ibfk_2` FOREIGN KEY (`User_Id`) REFERENCES `user` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bookratings` */

/*Table structure for table `favoritelist` */

DROP TABLE IF EXISTS `favoritelist`;

CREATE TABLE `favoritelist` (
  `Book_Id` bigint NOT NULL,
  `User_Id` bigint NOT NULL,
  PRIMARY KEY (`Book_Id`,`User_Id`),
  KEY `user_id` (`User_Id`),
  CONSTRAINT `favoritelist_ibfk_1` FOREIGN KEY (`Book_Id`) REFERENCES `book` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favoritelist_ibfk_2` FOREIGN KEY (`User_Id`) REFERENCES `user` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `favoritelist` */

/*Table structure for table `genre` */

DROP TABLE IF EXISTS `genre`;

CREATE TABLE `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `genre` */

/*Table structure for table `genrebook` */

DROP TABLE IF EXISTS `genrebook`;

CREATE TABLE `genrebook` (
  `Book_Id` bigint NOT NULL,
  `Genre_Id` bigint NOT NULL,
  PRIMARY KEY (`Book_Id`,`Genre_Id`),
  KEY `genre_id` (`Genre_Id`),
  CONSTRAINT `genrebook_ibfk_1` FOREIGN KEY (`Book_Id`) REFERENCES `book` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `genrebook_ibfk_2` FOREIGN KEY (`Genre_Id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `genrebook` */

/*Table structure for table `location` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `City` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Postal_Code` bigint DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `location` */

insert  into `location`(`Id`,`City`,`Postal_Code`) values 
(3,'Yerevan',17617);

/*Table structure for table `publisher` */

DROP TABLE IF EXISTS `publisher`;

CREATE TABLE `publisher` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `Full_Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1591407 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `publisher` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Surname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Hash_Pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Role` enum('ADMIN','EDITOR','USER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'USER',
  `Age` int DEFAULT NULL,
  `Location_Id` bigint DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Location_Id` (`Location_Id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Location_Id`) REFERENCES `location` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
