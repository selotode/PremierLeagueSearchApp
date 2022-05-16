-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: teams
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `passwords`
--

DROP TABLE IF EXISTS `passwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passwords` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `userPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwords`
--

LOCK TABLES `passwords` WRITE;
/*!40000 ALTER TABLE `passwords` DISABLE KEYS */;
INSERT INTO `passwords` VALUES (1,'david','david123'),(2,'daviddd123','d123'),(3,'teddy','123');
/*!40000 ALTER TABLE `passwords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `TeamID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `MatchesPlayed` int DEFAULT NULL,
  `Wins` int DEFAULT NULL,
  `Draws` int DEFAULT NULL,
  `Loses` int DEFAULT NULL,
  `GF` int DEFAULT NULL,
  `GA` int DEFAULT NULL,
  `GD` int DEFAULT NULL,
  `Points` int DEFAULT NULL,
  `LastFive` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`TeamID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,'Liverpool',35,25,8,2,87,23,64,83,'DWWWD'),(2,'Manchester-City',35,27,5,3,89,21,68,86,'WWWWD'),(3,'Chelsea',35,19,10,6,70,31,39,67,'DLDWL'),(4,'Arsenal',35,21,3,11,56,42,14,66,'WWWWD'),(5,'Tottenham',35,19,5,11,60,40,20,62,'DWDLW'),(6,'Manchester-United',36,16,10,11,57,56,1,58,'LWDLL'),(7,'WestHam',36,16,7,13,57,46,11,55,'WLLDL'),(8,'Wolves',35,15,5,15,35,34,1,50,'DLLLW'),(9,'Brighton',36,11,14,11,38,42,-4,47,'WWDLW'),(10,'Crystal-Palace',35,10,14,11,89,21,68,86,'WWDLL'),(11,'Aston-Villa',34,13,4,17,47,47,0,43,'WWDLL'),(12,'Brentford',36,12,7,17,44,52,-8,43,'WLDWW'),(13,'Newcastle',36,11,10,15,40,61,-21,43,'LLWWW'),(14,'LeicesterCity',34,11,9,14,49,56,-7,42,'LLDDL'),(15,'Southampton',36,9,13,14,41,61,-20,40,'LLDLW'),(16,'Everton',34,9,13,14,41,61,-20,35,'WWLDW'),(17,'Burnley',35,7,13,15,32,49,-17,34,'LWWWD'),(18,'Leeds-United',35,8,10,17,39,74,-35,34,'LLDWD'),(19,'Watford',35,6,4,25,32,70,-38,22,'LLLLL'),(20,'Norwich-City',35,5,6,24,22,75,-53,21,'LLLLW');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16  8:39:43
