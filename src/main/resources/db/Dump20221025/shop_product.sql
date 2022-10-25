-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shop
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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `p_description` varchar(255) DEFAULT NULL,
  `p_img` varchar(255) DEFAULT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `p_price` double DEFAULT NULL,
  `c_id` int DEFAULT NULL,
  `p_quantity` double DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `FKp999tsu8jkot8t7jqtsin7d7k` (`c_id`),
  CONSTRAINT `FKp999tsu8jkot8t7jqtsin7d7k` FOREIGN KEY (`c_id`) REFERENCES `category` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'Thịt sạch nhập từ nhật bản đạt tiêu chuẩn châu âu','1653870630608.jpg','Thịt lợn 500 gram',120000,1,100),(5,'Rau sạch','1653870859591.jpg','Rau ngót',25000,2,100),(6,'Rau sạch','1653870901078.jpg','Rau cải',15000,2,100),(7,'Rau sạch','1653871028724.jpg','Rau muống',10000,2,100),(8,'Rau sạch','1653871096716.jpg','Rau cần',10000,2,100),(9,'Thịt bò chuẩn nhật bản','1653871170450.png','Thịt bò 500 Gram',150000,1,100),(10,'Thịt cừu châu úc','1653871304075.jpg','Thịt cừu',250000,1,100),(11,'Mực tươi đại dương','1653871524942.jpg','Mực tươi 500 Gram',200000,3,100),(12,'Cá ngừ tươi','1653871733107.jpg','Cá ngừ đại dương 300 Gram',400000,3,100),(13,'Bạch tuộc tươi','1653871815047.jpg','Bạch tuộc tươi 500 Gram',200000,3,100),(14,'Tôm hùm tươi','1653871967728.jpg','Tôm hùm 1 Kilogram',1800000,3,100),(15,'Cua hoàng đế Alaska','1653872094266.jpg','Cua hoàng đế 1 Kilogram',1800000,3,100);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-25  9:41:15
