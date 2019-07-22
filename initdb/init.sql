-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: cinema_demo
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'ALIEN'),(2,'Avatar');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filmdate`
--

LOCK TABLES `filmdate` WRITE;
/*!40000 ALTER TABLE `filmdate` DISABLE KEYS */;
INSERT INTO `filmdate` VALUES (1,'2019-08-01 12:00:00',2),(2,'2019-08-05 12:00:00',1),(3,'2019-05-01 12:00:00',2),(4,'2019-12-05 12:00:00',2),(5,'2019-10-05 12:00:00',2);
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
  PRIMARY KEY (`id`),
  KEY `fk_visitor` (`visitor_id`),
  KEY `fk_filmdate` (`filmdate_id`),
  CONSTRAINT `fk_filmdate` FOREIGN KEY (`filmdate_id`) REFERENCES `filmdate` (`id`),
  CONSTRAINT `fk_visitor` FOREIGN KEY (`visitor_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filmticket`
--

LOCK TABLES `filmticket` WRITE;
/*!40000 ALTER TABLE `filmticket` DISABLE KEYS */;
INSERT INTO `filmticket` VALUES (9,1,76,'eObFknO',1),(10,1,77,'eObFknO',1),(11,1,78,'eObFknO',1),(12,2,31,'8GYznZ3',3),(13,2,32,'8GYznZ3',3),(14,2,33,'8GYznZ3',3),(18,3,98,'egIIdUx',2),(19,3,99,'egIIdUx',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mina','USER'),(2,'nina','USER'),(3,'mina1','USER'),(4,'GVATEMALA','USER'),(5,'admin','ADMIN'),(17,'USER_TESTUSER_TEST','USER');
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

-- Dump completed on 2019-07-22 12:54:54
