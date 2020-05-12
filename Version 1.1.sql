-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: videoclubz
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` varchar(16) NOT NULL,
  `nombres_cliente` varchar(30) NOT NULL,
  `apellido1_cliente` varchar(16) NOT NULL,
  `apellido2_cliente` varchar(16) NOT NULL,
  `direccion_cliente` varchar(50) NOT NULL,
  `telefono_cliente` varchar(10) NOT NULL,
  `saldo_pendiente` int DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('ABCD123456QWE','Moises','Guzman','Ramirez','CUCEI','1122334455',0),('BBBB123456BBB','Pedrito','Sola','Solin','Av. San Jacinto 456','1234567890',0),('QWER123456ASD','Jorge','Serrano','Pariente','Quien sabe, bueno si, pero no te diré','3366998877',0);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id_empleado` varchar(16) NOT NULL,
  `nombres_emp` varchar(30) NOT NULL,
  `apellido1_emp` varchar(16) NOT NULL,
  `apellido2_emp` varchar(16) NOT NULL,
  `puesto` varchar(20) NOT NULL,
  `telefono_emp` varchar(10) NOT NULL,
  `salario` int NOT NULL,
  `horario_emp` varchar(60) NOT NULL,
  PRIMARY KEY (`id_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES ('AAAA123456AAA','Juan','Perez','Lopez','Gerente','1122334455',2000,'L-V 10:00-15:00'),('GURR981207HJC','Ricardo','Guzman','Ramirez','Gerente','3322574805',3500,'L-V 9:00-15:00'),('MADJ860321JS9','Javier','Martinez','Dominguez','Empleado','3311223344',2000,'L-V 9:00 - 15:00'),('rfcccc','nombre','ape1','ape2','puestooo','6746467',1000,'NOSE');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados_prestamo`
--

DROP TABLE IF EXISTS `empleados_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados_prestamo` (
  `id_empleado` varchar(16) NOT NULL,
  `id_prestamo` bigint NOT NULL,
  PRIMARY KEY (`id_empleado`,`id_prestamo`),
  CONSTRAINT `empleados_prestamo_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados_prestamo`
--

LOCK TABLES `empleados_prestamo` WRITE;
/*!40000 ALTER TABLE `empleados_prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleados_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estanteria`
--

DROP TABLE IF EXISTS `estanteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estanteria` (
  `num_estant` int NOT NULL,
  `tipo` varchar(10) DEFAULT NULL,
  `rango_letras` varchar(5) NOT NULL,
  PRIMARY KEY (`num_estant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estanteria`
--

LOCK TABLES `estanteria` WRITE;
/*!40000 ALTER TABLE `estanteria` DISABLE KEYS */;
INSERT INTO `estanteria` VALUES (101,'Peliculas','A-C'),(102,'Peliculas','D-F'),(103,'Peliculas','G-I'),(104,'Peliculas','J-L'),(105,'Peliculas','M-O'),(106,'Peliculas','P-R'),(107,'Peliculas','S-U'),(108,'Peliculas','V-X'),(109,'Peliculas','Y-Z'),(110,'Peliculas','0-9'),(201,'Series','A-C'),(202,'Series','D-F'),(203,'Series','G-I'),(204,'Series','J-L'),(205,'Series','M-O'),(206,'Series','P-R'),(207,'Series','S-U'),(208,'Series','V-X'),(209,'Series','Y-Z'),(210,'Series','0-9');
/*!40000 ALTER TABLE `estanteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula_prestamo`
--

DROP TABLE IF EXISTS `pelicula_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula_prestamo` (
  `id_pelicula` bigint NOT NULL,
  `id_prestamo` bigint NOT NULL,
  PRIMARY KEY (`id_pelicula`,`id_prestamo`),
  KEY `id_prestamo` (`id_prestamo`),
  CONSTRAINT `pelicula_prestamo_ibfk_1` FOREIGN KEY (`id_pelicula`) REFERENCES `peliculas` (`id_pelicula`),
  CONSTRAINT `pelicula_prestamo_ibfk_2` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamo` (`id_prestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula_prestamo`
--

LOCK TABLES `pelicula_prestamo` WRITE;
/*!40000 ALTER TABLE `pelicula_prestamo` DISABLE KEYS */;
INSERT INTO `pelicula_prestamo` VALUES (1,5),(1,6),(3,6),(4,6),(1,7),(3,7),(4,7),(1,8),(1,9),(1,10),(4,10),(3,12),(4,16),(3,19);
/*!40000 ALTER TABLE `pelicula_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula_venta`
--

DROP TABLE IF EXISTS `pelicula_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula_venta` (
  `id_pelicula` bigint NOT NULL,
  `id_venta` bigint NOT NULL,
  PRIMARY KEY (`id_pelicula`,`id_venta`),
  KEY `id_venta` (`id_venta`),
  CONSTRAINT `pelicula_venta_ibfk_1` FOREIGN KEY (`id_pelicula`) REFERENCES `peliculas` (`id_pelicula`),
  CONSTRAINT `pelicula_venta_ibfk_2` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula_venta`
--

LOCK TABLES `pelicula_venta` WRITE;
/*!40000 ALTER TABLE `pelicula_venta` DISABLE KEYS */;
INSERT INTO `pelicula_venta` VALUES (3,23),(1,24),(1,25),(3,25),(4,25);
/*!40000 ALTER TABLE `pelicula_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculas` (
  `id_pelicula` bigint NOT NULL AUTO_INCREMENT,
  `anio_pel` int DEFAULT NULL,
  `titulo_pel` varchar(50) NOT NULL,
  `genero_pel` varchar(20) DEFAULT NULL,
  `costo_pel` int NOT NULL,
  `director_pel` varchar(50) DEFAULT NULL,
  `existencias_pres_pel` int NOT NULL,
  `existencias_ven_pel` int NOT NULL,
  `num_estant` int NOT NULL,
  PRIMARY KEY (`id_pelicula`),
  KEY `num_estant` (`num_estant`),
  CONSTRAINT `peliculas_ibfk_1` FOREIGN KEY (`num_estant`) REFERENCES `estanteria` (`num_estant`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (1,1985,'Alice in wonderland','Familiar',199,'Harry Harris',29,30,101),(3,1988,'Beetlejuice','Comedia',299,'Tim Burton',42,26,101),(4,1994,'Pulp Fiction','Drama',249,'Quentin Tarantino',19,31,106),(5,2020,'Gatos','Comedia',149,'Gato Jr',15,15,103);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `id_prestamo` bigint NOT NULL AUTO_INCREMENT,
  `fecha_entrega` date DEFAULT NULL,
  `fecha_entrega_est` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `metodo_pago_pres` varchar(16) NOT NULL,
  `id_cliente` varchar(16) NOT NULL,
  `costo_dia` int NOT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (5,'2020-04-14','2020-04-08','2020-04-05','Efectivo','ABCD123456QWE',0),(6,NULL,'2020-04-13','2020-04-13','Tarjeta','ABCD123456QWE',0),(7,NULL,'2020-04-15','2020-04-13','Tarjeta','ABCD123456QWE',0),(8,NULL,'2020-04-20','2020-04-13','Efectivo','ABCD123456QWE',0),(9,NULL,'2020-04-25','2020-04-13','Efectivo','ABCD123456QWE',0),(10,NULL,'2020-04-26','2020-04-13','Efectivo','ABCD123456QWE',0),(12,'2020-04-14','2020-04-30','2020-04-13','Tarjeta','ABCD123456QWE',30),(13,'2020-04-14','2020-04-30','2020-04-13','Tarjeta','ABCD123456QWE',30),(14,'2020-04-14','2020-04-30','2020-04-13','Tarjeta','ABCD123456QWE',50),(15,'2020-04-16','2020-04-01','2020-04-13','Tarjeta','ABCD123456QWE',50),(16,NULL,'2020-04-17','2020-04-16','Efectivo','ABCD123456QWE',15),(17,'2020-05-12','2020-05-10','2020-05-05','Tarjeta','QWER123456ASD',20),(18,'2020-05-12','2020-05-12','2020-05-05','Efectivo','QWER123456ASD',15),(19,NULL,'2020-05-15','2020-05-12','Tarjeta','BBBB123456BBB',10);
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serie_prestamo`
--

DROP TABLE IF EXISTS `serie_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serie_prestamo` (
  `id_serie` bigint NOT NULL,
  `id_prestamo` bigint NOT NULL,
  PRIMARY KEY (`id_serie`,`id_prestamo`),
  KEY `id_prestamo` (`id_prestamo`),
  CONSTRAINT `serie_prestamo_ibfk_1` FOREIGN KEY (`id_serie`) REFERENCES `series` (`id_serie`),
  CONSTRAINT `serie_prestamo_ibfk_2` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamo` (`id_prestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serie_prestamo`
--

LOCK TABLES `serie_prestamo` WRITE;
/*!40000 ALTER TABLE `serie_prestamo` DISABLE KEYS */;
INSERT INTO `serie_prestamo` VALUES (1,9),(1,12),(2,12),(1,14),(2,14),(1,15),(2,15),(1,19);
/*!40000 ALTER TABLE `serie_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serie_venta`
--

DROP TABLE IF EXISTS `serie_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serie_venta` (
  `id_serie` bigint NOT NULL,
  `id_venta` bigint NOT NULL,
  PRIMARY KEY (`id_serie`,`id_venta`),
  KEY `id_venta` (`id_venta`),
  CONSTRAINT `serie_venta_ibfk_1` FOREIGN KEY (`id_serie`) REFERENCES `series` (`id_serie`),
  CONSTRAINT `serie_venta_ibfk_2` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serie_venta`
--

LOCK TABLES `serie_venta` WRITE;
/*!40000 ALTER TABLE `serie_venta` DISABLE KEYS */;
INSERT INTO `serie_venta` VALUES (1,23),(2,23),(1,24),(1,25),(2,25),(2,26);
/*!40000 ALTER TABLE `serie_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `series`
--

DROP TABLE IF EXISTS `series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `series` (
  `id_serie` bigint NOT NULL AUTO_INCREMENT,
  `num_temp` int NOT NULL,
  `anio_temp` int DEFAULT NULL,
  `num_caps` int DEFAULT NULL,
  `costo_ser` int NOT NULL,
  `titulo_ser` varchar(50) NOT NULL,
  `genero_ser` varchar(20) DEFAULT NULL,
  `existencias_pres_ser` int NOT NULL,
  `existencias_ven_ser` int NOT NULL,
  `num_estant` int NOT NULL,
  PRIMARY KEY (`id_serie`),
  KEY `num_estant` (`num_estant`),
  CONSTRAINT `series_ibfk_1` FOREIGN KEY (`num_estant`) REFERENCES `estanteria` (`num_estant`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `series`
--

LOCK TABLES `series` WRITE;
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
INSERT INTO `series` VALUES (1,1,2019,6,149,'After Life','Comedia',35,21,201),(2,1,2008,7,249,'Breaking Bad','Drama',28,23,201),(3,1,2020,12,99,'Perros','Histórica',20,10,206);
/*!40000 ALTER TABLE `series` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id_venta` bigint NOT NULL AUTO_INCREMENT,
  `fecha_ven` date NOT NULL,
  `metodo_pago_ven` varchar(16) NOT NULL,
  `costo_total` int NOT NULL,
  `id_cliente` varchar(16) NOT NULL,
  `id_empleado` varchar(16) NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (23,'2020-04-05','Efectivo',577,'ABCD123456QWE','MADJ860321JS9'),(24,'2020-04-13','Efectivo',300,'ABCD123456QWE','MADJ860321JS9'),(25,'2020-04-13','Tarjeta',800,'ABCD123456QWE','MADJ860321JS9'),(26,'2020-05-12','Efectivo',249,'BBBB123456BBB','AAAA123456AAA');
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-12 12:38:31
