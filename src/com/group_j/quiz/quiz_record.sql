CREATE DATABASE  IF NOT EXISTS `quiz` ;
USE `quiz`;
CREATE TABLE IF NOT EXISTS `record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usrName` varchar(255) NOT NULL,
  `Score` int DEFAULT NULL,
  `grade` varchar(255) NOT NULL,
  `remark` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `record` WRITE;
UNLOCK TABLES;