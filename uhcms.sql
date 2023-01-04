-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: uhcmsdb
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES UTF8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `uhcmsdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `uhcmsdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `uhcmsdb`;

--
-- Table structure for table `uhcms_college`
--

DROP TABLE IF EXISTS `uhcms_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uhcms_college` (
  `cno` varchar(5) NOT NULL,
  `cname` varchar(45) DEFAULT NULL,
  `cdean_no` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cno`),
  KEY `fk_college_dean_idx` (`cdean_no`),
  CONSTRAINT `fk_college_dean` FOREIGN KEY (`cdean_no`) REFERENCES `uhcms_teacher` (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uhcms_college`
--

LOCK TABLES `uhcms_college` WRITE;
/*!40000 ALTER TABLE `uhcms_college` DISABLE KEYS */;
INSERT INTO `uhcms_college` VALUES ('C01','人文学院',NULL),('C02','计算机学院',NULL),('C03','管理学院',NULL),('C04','信息学院',NULL),('C05','理学院',NULL),('C06','健行学院',NULL),('C07','机械学院',NULL);
/*!40000 ALTER TABLE `uhcms_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uhcms_major`
--

DROP TABLE IF EXISTS `uhcms_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uhcms_major` (
  `mno` varchar(5) NOT NULL,
  `mname` varchar(45) DEFAULT NULL,
  `mcollege_no` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`mno`),
  KEY `college_major_idx` (`mcollege_no`),
  CONSTRAINT `college_major` FOREIGN KEY (`mcollege_no`) REFERENCES `uhcms_college` (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uhcms_major`
--

LOCK TABLES `uhcms_major` WRITE;
/*!40000 ALTER TABLE `uhcms_major` DISABLE KEYS */;
INSERT INTO `uhcms_major` VALUES ('M032','软件工程','C06'),('M064','大数据','C05'),('M123','电气工程','C07'),('M128','通信工程','C04'),('M233','网络工程','C02'),('M256','软件工程','C02'),('M512','公共关系','C03');
/*!40000 ALTER TABLE `uhcms_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uhcms_student`
--

DROP TABLE IF EXISTS `uhcms_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uhcms_student` (
  `sno` varchar(12) NOT NULL,
  `sname` varchar(45) DEFAULT NULL,
  `ssex` enum('男','女') DEFAULT NULL,
  `sid` varchar(20) DEFAULT NULL,
  `snumber` varchar(11) DEFAULT NULL,
  `scollege` varchar(45) DEFAULT NULL,
  `smajor` varchar(45) DEFAULT NULL,
  `sstate` enum('绿','黄','红','蓝') DEFAULT NULL,
  PRIMARY KEY (`sno`),
  KEY `fk_student_major_idx` (`smajor`),
  KEY `fk_student_college_idx` (`scollege`),
  CONSTRAINT `fk_student_college` FOREIGN KEY (`scollege`) REFERENCES `uhcms_college` (`cno`),
  CONSTRAINT `fk_student_major` FOREIGN KEY (`smajor`) REFERENCES `uhcms_major` (`mno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uhcms_student`
--

LOCK TABLES `uhcms_student` WRITE;
/*!40000 ALTER TABLE `uhcms_student` DISABLE KEYS */;
INSERT INTO `uhcms_student` VALUES ('201906062702','byteeplus','男','333012300013403212','15397012418','C02','M233','绿'),('201906062703','wlt','男','233333333333333333','100862333','C02','M233','绿'),('201906062704','陈礼暾','男','142321233342233421','23123445764','C03','M512','绿'),('201906062705','王熠恒','男','932388123332929348','12312231323','C06','M032','绿'),('201906062706','陆祥云','女','665552399923748923','18722943732','C04','M128','绿'),('201906062707','赵传聪','女','333201022203402304','15323332411','C05','M064','绿');
/*!40000 ALTER TABLE `uhcms_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uhcms_student_record`
--

DROP TABLE IF EXISTS `uhcms_student_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uhcms_student_record` (
  `rno` int NOT NULL AUTO_INCREMENT,
  `sno` varchar(12) DEFAULT NULL,
  `snumber` varchar(11) DEFAULT NULL,
  `judgement` enum('红','黄','绿') DEFAULT NULL,
  `current_state` enum('红','黄','绿') DEFAULT NULL,
  `rtime` time DEFAULT NULL,
  `rdate` date DEFAULT NULL,
  PRIMARY KEY (`rno`),
  KEY `fk_student_idx` (`sno`),
  CONSTRAINT `fk_student` FOREIGN KEY (`sno`) REFERENCES `uhcms_student` (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uhcms_student_record`
--

LOCK TABLES `uhcms_student_record` WRITE;
/*!40000 ALTER TABLE `uhcms_student_record` DISABLE KEYS */;
INSERT INTO `uhcms_student_record` VALUES (1,'201906062702','12345678901','绿','绿','18:07:00','2022-01-01'),(2,'201906062703','12345678902','绿','绿','18:08:00','2022-01-01'),(3,'201906062704','12345678903','绿','绿','18:08:00','2022-01-01'),(4,'201906062705','12345678904','绿','绿','18:09:00','2022-01-01'),(5,'201906062706','12345678905','绿','绿','18:09:00','2022-01-01'),(6,'201906062707','12345678906','绿','绿','18:09:00','2022-01-01'),(7,'201906062702','12345678901','绿','绿','18:20:57','2022-01-02'),(8,'201906062707','15323332411','绿','绿','19:47:05','2022-01-02');
/*!40000 ALTER TABLE `uhcms_student_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uhcms_teacher`
--

DROP TABLE IF EXISTS `uhcms_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uhcms_teacher` (
  `tno` varchar(10) NOT NULL,
  `tname` varchar(45) DEFAULT NULL,
  `tsex` enum('男','女') DEFAULT NULL,
  `tid` varchar(20) DEFAULT NULL,
  `tnumber` varchar(11) DEFAULT NULL,
  `tcollege` varchar(45) DEFAULT NULL,
  `tstate` enum('绿','黄','红','蓝') DEFAULT NULL,
  PRIMARY KEY (`tno`),
  KEY `fk_teacher_college_idx` (`tcollege`),
  CONSTRAINT `fk_teacher_college` FOREIGN KEY (`tcollege`) REFERENCES `uhcms_college` (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uhcms_teacher`
--

LOCK TABLES `uhcms_teacher` WRITE;
/*!40000 ALTER TABLE `uhcms_teacher` DISABLE KEYS */;
INSERT INTO `uhcms_teacher` VALUES ('100001','李小年','男','33023 195423292332','13946582019',NULL,'绿'),('106545','梁荣华','男','333322143654454334','10123223123','C02','绿'),('112332','李燕','女','552321223342231234','13822321192','C01','绿'),('330362','陈波','男','333201234412331232','12355434312','C02','绿'),('332033','李波','男','433212948777899432','18766623222','C02','红');
/*!40000 ALTER TABLE `uhcms_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uhcms_teacher_record`
--

DROP TABLE IF EXISTS `uhcms_teacher_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uhcms_teacher_record` (
  `rno` int NOT NULL AUTO_INCREMENT,
  `tno` varchar(12) DEFAULT NULL,
  `tnumber` varchar(11) DEFAULT NULL,
  `judgement` enum('红','黄','绿') DEFAULT NULL,
  `current_state` enum('红','黄','绿') DEFAULT NULL,
  `rdate` date DEFAULT NULL,
  `rtime` time DEFAULT NULL,
  PRIMARY KEY (`rno`),
  KEY `fk_teacher_idx` (`tno`),
  CONSTRAINT `fk_teacher` FOREIGN KEY (`tno`) REFERENCES `uhcms_teacher` (`tno`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uhcms_teacher_record`
--

LOCK TABLES `uhcms_teacher_record` WRITE;
/*!40000 ALTER TABLE `uhcms_teacher_record` DISABLE KEYS */;
INSERT INTO `uhcms_teacher_record` VALUES (1,'112332','13822321192','绿','绿','2022-01-01','17:39:00'),(2,'332033','18766623222','绿','红','2022-01-01','22:59:00'),(3,'112332','13822321192','绿','绿','2022-01-02','16:46:08'),(4,'332033','18766623222','绿','绿','2022-01-02','17:00:10'),(5,'106545','10123223123','绿','绿','2022-01-02','19:54:14'),(6,'332033',NULL,'绿','红','2021-12-31',NULL),(7,'332033',NULL,'绿','红','2021-12-30',NULL),(8,'332033',NULL,'绿','红','2021-12-29',NULL),(9,'332033',NULL,'绿','红','2021-12-28',NULL),(11,'332033',NULL,'绿','红','2021-12-27',NULL);
/*!40000 ALTER TABLE `uhcms_teacher_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uhcms_user`
--

DROP TABLE IF EXISTS `uhcms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uhcms_user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` enum('admin','school','college','personal') DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uhcms_user`
--

LOCK TABLES `uhcms_user` WRITE;
/*!40000 ALTER TABLE `uhcms_user` DISABLE KEYS */;
INSERT INTO `uhcms_user` VALUES ('100001','zjut','school'),('106545','jisuanji','college'),('201906062703','test','personal'),('330362',NULL,'personal'),('byteeplus','201906062702','admin');
/*!40000 ALTER TABLE `uhcms_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-03  7:22:42
