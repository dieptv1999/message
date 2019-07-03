-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: trandiep
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
-- Table structure for table `friend_trandiep010`
--

DROP TABLE IF EXISTS `friend_trandiep010`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_trandiep010` (
  `name` varchar(100) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_trandiep010`
--

LOCK TABLES `friend_trandiep010` WRITE;
/*!40000 ALTER TABLE `friend_trandiep010` DISABLE KEYS */;
INSERT INTO `friend_trandiep010` VALUES ('tranlam010',NULL);
/*!40000 ALTER TABLE `friend_trandiep010` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_tranlam010`
--

DROP TABLE IF EXISTS `friend_tranlam010`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_tranlam010` (
  `name` varchar(100) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_tranlam010`
--

LOCK TABLES `friend_tranlam010` WRITE;
/*!40000 ALTER TABLE `friend_tranlam010` DISABLE KEYS */;
INSERT INTO `friend_tranlam010` VALUES ('trandiep010',NULL);
/*!40000 ALTER TABLE `friend_tranlam010` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tranlam010__trandiep010`
--

DROP TABLE IF EXISTS `tranlam010__trandiep010`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tranlam010__trandiep010` (
  `id` varchar(100) NOT NULL,
  `message` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tranlam010__trandiep010`
--

LOCK TABLES `tranlam010__trandiep010` WRITE;
/*!40000 ALTER TABLE `tranlam010__trandiep010` DISABLE KEYS */;
INSERT INTO `tranlam010__trandiep010` VALUES ('tranlam010','123'),('trandiep010','456'),('tranlam010','123'),('trandiep010','789'),('tranlam010','78'),('tranlam010','12');
/*!40000 ALTER TABLE `tranlam010__trandiep010` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(300) NOT NULL,
  `pass` varchar(300) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1d29796d5ee3f1cdae2d91b301bd61a4195194c3a3c1856812197745769a53b3','1d29796d5ee3f1cdae2d91b301bd61a4195194c3a3c1856812197745769a53b3','dsdsddsdsdss'),('a8de938f9c2f158fa7d4c99d1ffc17eff19a6ede883a20d7b0f26f231f02547f','a8de938f9c2f158fa7d4c99d1ffc17eff19a6ede883a20d7b0f26f231f02547f','rerererererer');
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

-- Dump completed on 2019-07-03 19:15:17
