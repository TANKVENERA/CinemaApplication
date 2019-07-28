-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema_demo
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'Avatar'),(2,'TerminatorIV'),(10,'Kin-dza-dza'),(13,'Ymka');
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filmdate`
--

DROP TABLE IF EXISTS `filmdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filmdate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filmdate` datetime NOT NULL,
  `film_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_film_date` (`film_id`),
  CONSTRAINT `fk_date` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`),
  CONSTRAINT `fk_film_date` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filmdate`
--

LOCK TABLES `filmdate` WRITE;
/*!40000 ALTER TABLE `filmdate` DISABLE KEYS */;
INSERT INTO `filmdate` VALUES (1,'2019-08-04 12:00:00',1),(2,'2019-08-03 12:00:00',1),(3,'2019-08-17 12:00:00',2),(10,'2019-08-14 12:00:00',10),(13,'2019-07-10 12:00:00',13),(14,'2019-07-11 12:00:00',13);
/*!40000 ALTER TABLE `filmdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filmticket`
--

DROP TABLE IF EXISTS `filmticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filmticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitor_id` int(11) NOT NULL,
  `seat` int(11) NOT NULL,
  `ticket` varchar(10) NOT NULL,
  `filmdate_id` int(11) DEFAULT NULL,
  `row` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_visitor` (`visitor_id`),
  KEY `fk_filmdate` (`filmdate_id`),
  CONSTRAINT `fk_filmdate` FOREIGN KEY (`filmdate_id`) REFERENCES `filmdate` (`id`),
  CONSTRAINT `fk_visitor` FOREIGN KEY (`visitor_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filmticket`
--

LOCK TABLES `filmticket` WRITE;
/*!40000 ALTER TABLE `filmticket` DISABLE KEYS */;
INSERT INTO `filmticket` VALUES (19,2,5,'oiwMnai',1,6),(20,2,6,'oiwMnai',1,6),(24,1,1,'SZeHiT1',3,2),(25,1,2,'SZeHiT1',3,2),(26,1,3,'SZeHiT1',3,2),(31,1,1,'H47kvla',1,1),(32,1,2,'H47kvla',1,1),(33,26,1,'NYbPMms',2,8),(34,26,3,'NYbPMms',2,8),(35,26,2,'NYbPMms',2,8),(36,1,12,'2928ukw',1,8),(37,1,11,'2928ukw',1,8),(38,1,10,'2928ukw',1,8),(41,23,12,'2AF1QhF',13,3),(43,1,1,'3q6ZYGU',13,4),(44,1,2,'3q6ZYGU',13,4),(45,1,3,'3q6ZYGU',13,4),(46,1,4,'7bUkeU9',3,3),(47,1,5,'7bUkeU9',3,3),(48,1,6,'7bUkeU9',3,3);
/*!40000 ALTER TABLE `filmticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `role` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mina','USER'),(2,'mina2','USER'),(23,'admin','ADMIN'),(26,'nina','USER'),(27,'vino','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-29  0:01:42
