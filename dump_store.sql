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
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ship_id` int DEFAULT NULL,
  `cart_date` date DEFAULT NULL,
  `city` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `zipcode` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `delete_flag` int DEFAULT '1',
  PRIMARY KEY (`cart_id`),
  KEY `username` (`username`),
  KEY `ship_id` (`ship_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON UPDATE CASCADE,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`ship_id`) REFERENCES `shipment` (`ship_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (9,'user',5,'2020-07-22','HCM','VN','7000000',0),(10,'user',6,'2020-07-23','HCM','VN','700000',0),(15,'user',11,'2020-07-23','','','',1),(16,'user',12,'2020-07-24','Há» ChÃ­ Minh','Viá»t Nam','70000',1),(17,'user',13,'2020-07-24','Há» ChÃ­ Minh','Viá»t Nam','70000',1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_detail`
--

DROP TABLE IF EXISTS `cart_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_detail` (
  `cart_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `amount` int DEFAULT NULL,
  KEY `cart_id` (`cart_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `cart_detail_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON UPDATE CASCADE,
  CONSTRAINT `cart_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_detail`
--

LOCK TABLES `cart_detail` WRITE;
/*!40000 ALTER TABLE `cart_detail` DISABLE KEYS */;
INSERT INTO `cart_detail` VALUES (9,19,1),(10,19,1),(15,25,2),(15,14,3),(16,19,1),(17,20,2),(17,13,5);
/*!40000 ALTER TABLE `cart_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Smartphone'),(2,'Laptop'),(3,'Accessories'),(4,'Cameras');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discount_id` int NOT NULL AUTO_INCREMENT,
  `percentage` int DEFAULT NULL,
  `discount_desc` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,30,'Tặng tai nghe Sony'),(2,50,'Đổi trả trong tháng'),(3,60,'Trả góp 0%'),(4,0,NULL);
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
  `manufacturer_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `details` int DEFAULT NULL,
  `isNew` int DEFAULT '0',
  `manufacturer_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `discount_id` int DEFAULT NULL,
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_detail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manufacturer` (`manufacturer_id`),
  KEY `type_id` (`category_id`),
  KEY `discount_id` (`discount_id`),
  KEY `details` (`details`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`manufacturer_id`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_4` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`discount_id`) ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_5` FOREIGN KEY (`details`) REFERENCES `product_attribute` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (13,'Xiaomi Redmi 9 (4GB/64GB)',10,3999000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,0,4,1,1,'xiaomi-redmi-9-(10).jpg',''),(14,'Samsung Galaxy A31',10,5840000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,0,1,1,2,'samsung-galaxy-a21s-055620-045627-400x460.png',''),(15,'Phone SE 256GB 2020',10,17490000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',2,1,2,1,4,'iphone-7-gold-400x460.png',''),(16,'Vivo V19',10,8590000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,0,3,1,1,'vivo-y30-xanh-400x460-400x460.png',''),(17,'OPPO A92',10,6490000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,1,5,1,2,'oppo-a92-tim-400x460-400x460.png',''),(18,'Vsmart Star 4',10,2190000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,1,6,1,3,'vsmart-star-4-den-400x460-400x460.png',''),(19,'iPhone SE 64GB (2020)',10,12490000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',2,0,2,1,4,'iphone-se-2020-do-400x460-400x460.png',''),(20,'Xiaomi Redmi 9 (4GB/64GB)',10,3999000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,1,4,1,1,'xiaomi-mi-note-10-lite-400x460-trang-1-400x460.png',''),(21,'Samsung Galaxy A31',10,5840000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,0,1,1,3,'samsung-galaxy-fold-black-400x460.png',''),(22,'Phone SE 256GB 2020',10,17490000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',2,1,2,1,2,'iphone-11-256gb-black-400x460.png',''),(23,'Vivo V19',10,8590000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,0,3,1,1,'vivo-v19-neo-den-400x460-1-400x460.png',''),(24,'OPPO A92',10,6490000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,1,5,1,2,'oppo-a91-trang-400x460-1-400x460.png',''),(25,'Vsmart Star 4',10,2190000,'Samsung Galaxy A21s là chiếc điện thoại tầm trung mới của Samsung, mang trong mình có thiết kế màn hình nốt ruồi thời thượng, cùng cụm 4 camera sau độ phân giải lên đến 48 MP hỗ trợ nhiều tính năng chụp ảnh hấp dẫn.',1,1,6,1,1,'vsmart-joy-3-2gb-tim-400x460-400x460.png','');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_attribute`
--

DROP TABLE IF EXISTS `product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_attribute` (
  `id` int NOT NULL AUTO_INCREMENT,
  `screen` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `size` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `resolution` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `os` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `camera` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `chipset` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hardward` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_attribute`
--

LOCK TABLES `product_attribute` WRITE;
/*!40000 ALTER TABLE `product_attribute` DISABLE KEYS */;
INSERT INTO `product_attribute` VALUES (1,'LCD','6.5 inches','1080x1920','Android 10','12MP','Snapdragon 855','8BG/128GB'),(2,'Retina','6.5','1080x1920','IOS 12','12MP','Chipset A10','8GB/128GB');
/*!40000 ALTER TABLE `product_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL,
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `ship_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `notes` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ship_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (4,'',''),(5,'',''),(6,'123','123'),(7,'123 ThÃ nh ThÃ¡i','123 ThÃ nh ThÃ¡i'),(8,'asfdasdfasdf','asfdasdfasdf'),(9,'',''),(10,'',''),(11,'',''),(12,'123 ThÃ nh ThÃ¡i, VL','123 ThÃ nh ThÃ¡i, VL'),(13,'123 Huỳnh Tấn Phát, Nhà Bè','123 Huỳnh Tấn Phát, Nhà Bè');
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `fullname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `avata` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Do Duy Sang','thinh@gmail.com','0377725445','Nhà Bè, Việt Nam','1999-08-24','admin','$2a$10$DJylXVNW5oIOBxuNg8FLoOre4dDTBy8s1mvAg8NXQRyrSXCI2s9ny',NULL,2),('Đỗ Duy Thịnh','user@gmail.com','0977726444','Hồ Chí Minh, Việt Nam','1999-08-24','user','$2a$10$CK1osM/kumphzS70EP1kY.GtMVcrOYwettFzsiK.mYlmM3xWziicK','white_cat.jpg',1),('Tran Duy Thinh','tranthinh@gmail.com',NULL,NULL,NULL,'user2','$2a$10$tlMkQPEG.uzpbXF7WichA.CFRBVgzHyC3uZc30WszoVPG7Iwou8tC',NULL,1);
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

-- Dump completed on 2020-07-24 12:40:03
