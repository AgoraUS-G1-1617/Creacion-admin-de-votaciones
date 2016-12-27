drop database if exists `CreacionAdminVotaciones`;
create database `CreacionAdminVotaciones`;



create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';


grant select, insert, update, delete 
	on `CreacionAdminVotaciones`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `CreacionAdminVotaciones`.* to 'acme-manager'@'%';

USE `CreacionAdminVotaciones`;

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: CreacionAdminVotaciones
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `domainentity`
--

DROP TABLE IF EXISTS `domainentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domainentity` (
  `DTYPE` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `surveyId` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `census` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `usernameCreator` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7c4bo9w7vkdy6tnl6i4lsxcwc` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domainentity`
--

LOCK TABLES `domainentity` WRITE;
/*!40000 ALTER TABLE `domainentity` DISABLE KEYS */;
INSERT INTO `domainentity` VALUES ('Survey',1,0,NULL,NULL,1,'Indaga sobre las preferencias de los ciudadanos en los equipos de la ciudad','2016-06-02 12:00:00','41005','2016-05-02 12:00:00','Cerrado','Encuesta futbolística en Sevilla','Verde1',NULL,NULL),('Question',2,0,1,'¿Cual es su equipo de fútbol favorito?',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Question',3,0,1,'¿Prefiere a Sevilla o Betis?',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Question',4,0,1,'¿Tiene problemas mentales?',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Survey',9,0,NULL,NULL,1,'Pretendemos conocer si la gente cree que ya hemos superado la crisis','2017-04-11 12:00:00','11393','2017-03-01 12:00:00','Cerrado','Crisis','Verde1',NULL,NULL),('Question',10,0,3,'No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Question',11,0,3,'Si',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `domainentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domainentity_domainentity`
--

DROP TABLE IF EXISTS `domainentity_domainentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domainentity_domainentity` (
  `DomainEntity_id` int(11) NOT NULL,
  `questions_id` int(11) NOT NULL,
  UNIQUE KEY `UK_d0uimbuu5c0k0did565pmg90c` (`questions_id`),
  KEY `FK_lwtddf7ju3ihywlna8nvxu2q6` (`DomainEntity_id`),
  CONSTRAINT `FK_lwtddf7ju3ihywlna8nvxu2q6` FOREIGN KEY (`DomainEntity_id`) REFERENCES `domainentity` (`id`),
  CONSTRAINT `FK_d0uimbuu5c0k0did565pmg90c` FOREIGN KEY (`questions_id`) REFERENCES `domainentity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domainentity_domainentity`
--

LOCK TABLES `domainentity_domainentity` WRITE;
/*!40000 ALTER TABLE `domainentity_domainentity` DISABLE KEYS */;
INSERT INTO `domainentity_domainentity` VALUES (1,2),(1,3),(1,4),(9,10),(9,11);
/*!40000 ALTER TABLE `domainentity_domainentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `domainentity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-27 10:41:24
