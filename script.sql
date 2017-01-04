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
-- Table structure for table `domain_entity`
--

DROP TABLE IF EXISTS `domain_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domain_entity` (
  `dtype` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `survey_id` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `census` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `username_creator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mjkjds1f2jnib0fgvfkg5a44h` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain_entity`
--

LOCK TABLES `domain_entity` WRITE;
/*!40000 ALTER TABLE `domain_entity` DISABLE KEYS */;
INSERT INTO `domain_entity` VALUES ('Survey',1,0,NULL,NULL,NULL,NULL,1,'Indaga sobre las preferencias de los ciudadanos en los equipos de la ciudad','2016-06-02 12:00:00','41005','2016-05-02 12:00:00','Cerrado','Encuesta futbolística en Sevilla','Verde1'),('Question',2,0,1,'¿Cual es su equipo de fútbol favorito?',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Question',3,0,1,'¿Prefiere a Sevilla o Betis?',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Question',4,0,1,'¿Tiene problemas mentales?',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `domain_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domain_entity_questions`
--

DROP TABLE IF EXISTS `domain_entity_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domain_entity_questions` (
  `domain_entity` int(11) NOT NULL,
  `questions` int(11) NOT NULL,
  UNIQUE KEY `UK_7wb3r06mfugp9b6wpfmuiw8bj` (`questions`),
  KEY `FK_3b5miul0r1933n3qt774j1dje` (`domain_entity`),
  CONSTRAINT `FK_3b5miul0r1933n3qt774j1dje` FOREIGN KEY (`domain_entity`) REFERENCES `domain_entity` (`id`),
  CONSTRAINT `FK_7wb3r06mfugp9b6wpfmuiw8bj` FOREIGN KEY (`questions`) REFERENCES `domain_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain_entity_questions`
--

LOCK TABLES `domain_entity_questions` WRITE;
/*!40000 ALTER TABLE `domain_entity_questions` DISABLE KEYS */;
INSERT INTO `domain_entity_questions` VALUES (1,2),(1,3),(1,4);
/*!40000 ALTER TABLE `domain_entity_questions` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',2);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `domain_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-04 20:58:36
