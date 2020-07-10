-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: store
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` varchar(10) NOT NULL,
  `amount` int NOT NULL,
  `price` float NOT NULL,
  `username` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `username` (`username`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_phone`
--

DROP TABLE IF EXISTS `cart_phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_phone` (
  `cart_id` varchar(10) NOT NULL,
  `phone_id` int NOT NULL,
  KEY `cart_id` (`cart_id`),
  KEY `phone_id` (`phone_id`),
  CONSTRAINT `cart_phone_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON UPDATE CASCADE,
  CONSTRAINT `cart_phone_ibfk_2` FOREIGN KEY (`phone_id`) REFERENCES `product` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_phone`
--

LOCK TABLES `cart_phone` WRITE;
/*!40000 ALTER TABLE `cart_phone` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discount_id` varchar(10) NOT NULL,
  `percentage` float DEFAULT NULL,
  `discount_desc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `manufacturer_id` int NOT NULL AUTO_INCREMENT,
  `manufacturer_name` varchar(100) NOT NULL,
  `image` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'Samsung','Samsung42.jpg'),(2,'IPhone','iPhone-(Apple).jpg'),(3,'Vivo','Vivo42.jpg'),(4,'Xiaomi','Xiaomi42.jpg'),(5,'Oppo','OPPO42.png'),(6,'VSmart','Vsmart42.png');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `manufacturer_id` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  `image` varchar(100) NOT NULL,
  `image_detail` varchar(100) DEFAULT NULL,
  `discount_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manufacturer` (`manufacturer_id`),
  KEY `discount_id` (`discount_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`manufacturer_id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`discount_id`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`discount_id`),
  CONSTRAINT `product_ibfk_4` FOREIGN KEY (`type_id`) REFERENCES `protype` (`type_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (13,'Xiaomi Redmi 9 (4GB/64GB)',10,3999000,'Màn hình 6.53, Camera sau Chính 13 MP & Phụ 8 MP, 5 MP, 2 MP, Pin 5020 mAh',4,4,'xiaomi-redmi-9-(10).jpg','',NULL),(14,'Samsung Galaxy A31',10,5840000,'Super AMOLED, 6.4, 	Android 10, Full HD+, Chính 48 MP & Phụ 8 MP, 5 MP, 5 MP, Camera sau 20MP',1,4,'samsung-galaxy-a21s-055620-045627-400x460.png','',NULL),(15,'Phone SE 256GB 2020',10,17490000,'	IPS LCD, 4.7\", Retina, iOS 13, 	12 MP, 	Apple A13 Bionic 6 nhân, 3GB/256GB',2,4,'iphone-7-gold-400x460.png','',NULL),(16,'Vivo V19',10,8590000,'Super AMOLED, 6.4, Android 10, Full HD+, Chính 48 MP & Phụ 8 MP, 5 MP, 5 MP, Camera sau 20MP, 8 GB/128GB',3,4,'vivo-y30-xanh-400x460-400x460.png','',NULL),(17,'OPPO A92',10,6490000,'	TFT LCD, 6.5\", Full HD+, Android 10, Chính 48 MP & Phụ 8 MP, 2 MP, 2 MP, Snapdragon 665 8 nhân, 8 GB/128GB',5,4,'oppo-a92-tim-400x460-400x460.png','',NULL),(18,'Vsmart Star 4',10,2190000,'IPS LCD, 6.09, Android 10, Chính 8 MP & Phụ 5 MP, MediaTek Helio P35 8 nhân, 2 GB/32GB',6,4,'vsmart-star-4-den-400x460-400x460.png','',NULL),(19,'iPhone SE 64GB (2020)',10,12490000,'IPS LCD, 4.7, Retina, iOS 13, 12/7MP, Apple A13 Bionic 6 nhân, 3/64GB',2,4,'iphone-se-2020-do-400x460-400x460.png','',NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protype`
--

DROP TABLE IF EXISTS `protype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `protype` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protype`
--

LOCK TABLES `protype` WRITE;
/*!40000 ALTER TABLE `protype` DISABLE KEYS */;
INSERT INTO `protype` VALUES (1,'Laptop'),(2,'Accessories'),(3,'Camera'),(4,'Smartphone');
/*!40000 ALTER TABLE `protype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL,
  `role` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `avata` varchar(100) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Do Duy Sang','thinh@gmail.com','',NULL,NULL,'admin','$2a$10$DJylXVNW5oIOBxuNg8FLoOre4dDTBy8s1mvAg8NXQRyrSXCI2s9ny',NULL,1),('Do Duy Thinh','admin@gmail.com','','Viet Nam',NULL,'user','$2a$10$CK1osM/kumphzS70EP1kY.GtMVcrOYwettFzsiK.mYlmM3xWziicK','white_cat.jpg',2);
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

-- Dump completed on 2020-07-10 12:35:01
