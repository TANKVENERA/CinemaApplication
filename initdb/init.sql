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

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('01','belski','classpath:/db/liquibase-changelog.xml','2019-04-21 23:53:12',1,'EXECUTED','8:696ecac4c02fe9aa81bb317d5125a3ae','createTable tableName=visitor; createTable tableName=order; addForeignKeyConstraint baseTableName=visitor, constraintName=fk_visitor_order, referencedTableName=order','',NULL,'3.6.3',NULL,NULL,'5879990840'),('03','belski','classpath:/db/liquibase-changelog.xml','2019-04-22 22:24:32',2,'EXECUTED','8:fbe4e7c8c0304033456703c3349e8096','addColumn tableName=order; addForeignKeyConstraint baseTableName=order, constraintName=fk_visitor, referencedTableName=visitor','',NULL,'3.6.3',NULL,NULL,'5961071467'),('04','belski','classpath:/db/liquibase-changelog.xml','2019-04-24 21:40:07',3,'EXECUTED','8:effd495ac71258529fd768c7c0f5d300','renameTable newTableName=filmticket, oldTableName=order','',NULL,'3.6.3',NULL,NULL,'6131207000'),('05','belski','classpath:/db/liquibase-changelog.xml','2019-04-24 22:08:13',4,'EXECUTED','8:3f38367fe9498bb2d30799157579438c','addColumn tableName=filmticket','',NULL,'3.6.3',NULL,NULL,'6132892421'),('belski','06','classpath:/db/liquibase-changelog.xml','2019-04-24 22:51:58',5,'EXECUTED','8:5ae7c4922428e55bd0c453ae68904632','addAutoIncrement columnName=id, tableName=filmticket','',NULL,'3.6.3',NULL,NULL,'6135517716'),('07','belski','classpath:/db/liquibase-changelog.xml','2019-04-24 22:57:54',6,'EXECUTED','8:ac835dc136d3ff78c2001a04979babf4','addForeignKeyConstraint baseTableName=filmticket, constraintName=fk_visitor, referencedTableName=visitor','',NULL,'3.6.3',NULL,NULL,'6135873302'),('08','belski','classpath:/db/liquibase-changelog.xml','2019-04-24 23:05:37',7,'EXECUTED','8:624f37811c55f2839a8f039da51a6a49','renameTable newTableName=user, oldTableName=visitor','',NULL,'3.6.3',NULL,NULL,'6136337257'),('09','belski','classpath:/db/liquibase-changelog.xml','2019-04-24 23:14:07',8,'EXECUTED','8:bc52eafd2a453603d3ee266e858bcfa2','renameColumn newColumnName=seat, oldColumnName=seatNumber, tableName=filmticket','',NULL,'3.6.3',NULL,NULL,'6136846814'),('10','belski','classpath:/db/liquibase-changelog.xml','2019-04-29 21:50:01',9,'EXECUTED','8:b092982921399289e0f422fb86f3cc69','addForeignKeyConstraint baseTableName=filmticket, constraintName=fk_film, referencedTableName=film','',NULL,'3.6.3',NULL,NULL,'6563801110'),('belski','11','classpath:/db/liquibase-changelog.xml','2019-05-05 22:27:38',10,'EXECUTED','8:7837391ee821ae0b24af8407cc5c754d','renameColumn newColumnName=title, oldColumnName=filmtitle, tableName=film','',NULL,'3.6.3',NULL,NULL,'7084456948'),('12','belski','classpath:/db/liquibase-changelog.xml','2019-05-07 19:49:28',11,'EXECUTED','8:c81c266188aae9a0f0683c1474cdf2d7','addColumn tableName=user','',NULL,'3.6.3',NULL,NULL,'7247767827');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL,
  `filmdate` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'Avatar',1),(2,'Avatar',2),(3,'Terminator 2 Judgment day',1),(4,'Terminator 2 Judgment day',2),(5,'Terminator 2 Judgment day',3),(6,'Не грози южному централу',1);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filmticket`
--

DROP TABLE IF EXISTS `filmticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filmticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seat` int(11) DEFAULT NULL,
  `fkvisitor_id` int(11) NOT NULL,
  `film_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_visitor` (`fkvisitor_id`),
  KEY `fk_film` (`film_id`),
  CONSTRAINT `fk_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`),
  CONSTRAINT `fk_visitor` FOREIGN KEY (`fkvisitor_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filmticket`
--

LOCK TABLES `filmticket` WRITE;
/*!40000 ALTER TABLE `filmticket` DISABLE KEYS */;
INSERT INTO `filmticket` VALUES (7,15,2,2),(8,16,2,2),(102,1,2,6),(103,41,2,6),(104,42,2,6),(130,42,1,2),(146,10,9,6),(147,71,1,3),(149,73,1,3),(150,10,1,5),(151,91,1,5),(152,34,1,3),(154,24,1,3),(155,90,1,1),(157,88,1,1),(161,62,1,3),(162,72,1,3),(163,91,1,3),(164,10,1,4),(165,9,1,4),(166,91,1,4),(167,55,9,5),(168,56,9,5),(169,32,9,6),(171,23,9,3),(172,5,1,3),(173,6,1,3),(174,43,1,5),(175,44,1,5),(180,29,9,6),(182,6,63,1),(183,23,1,4),(184,24,1,4),(185,22,1,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nina','USER'),(2,'tiki-tak',''),(9,'mina','USER'),(10,'telepuzik','USER'),(11,'vasia','USER'),(12,'T1000','USER'),(13,'ttttt','USER'),(14,'olga','USER'),(57,'MINA4','USER'),(58,'ogogog','USER'),(59,'uuuuuuu','USER'),(60,'puiouhh','USER'),(61,'grisha','USER'),(62,'qwerty','USER'),(63,'lol','USER'),(65,'gggtyu','USER');
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

-- Dump completed on 2019-06-11 17:28:13
