-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gimnasio
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `clasesdirigidas`
--

DROP TABLE IF EXISTS `clasesdirigidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clasesdirigidas` (
  `idClase` varchar(50) NOT NULL,
  `nombreClase` varchar(50) DEFAULT NULL,
  `idEntrenador` varchar(50) NOT NULL,
  `aforo` int DEFAULT NULL,
  `fecha` date NOT NULL DEFAULT '2025-01-01',
  PRIMARY KEY (`idClase`),
  KEY `idEntrenador` (`idEntrenador`),
  CONSTRAINT `clasesdirigidas_ibfk_1` FOREIGN KEY (`idEntrenador`) REFERENCES `entrenadores` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clasesdirigidas_chk_1` CHECK ((`aforo` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasesdirigidas`
--

LOCK TABLES `clasesdirigidas` WRITE;
/*!40000 ALTER TABLE `clasesdirigidas` DISABLE KEYS */;
INSERT INTO `clasesdirigidas` VALUES ('CLASE001','Pilates','11223344C',11,'2025-06-16'),('CLASE002','HIIT','12345678A',10,'2025-06-16'),('CLASE003','Zumba','11223344C',20,'2025-06-17'),('CLASE004','Boxeo','12345678A',8,'2025-06-18'),('CLASE005','Crossfit','11223344C',15,'2025-06-19');
/*!40000 ALTER TABLE `clasesdirigidas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `nif` varchar(50) NOT NULL,
  `nifEntrenador` varchar(50) DEFAULT NULL,
  `fechaAlta` date NOT NULL,
  `fechaBaja` date DEFAULT NULL,
  `cuota` decimal(5,2) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`nif`),
  KEY `nifEntrenador` (`nifEntrenador`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`nif`) REFERENCES `usuarios` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`nifEntrenador`) REFERENCES `entrenadores` (`nif`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('44332211D','12345678A','2023-01-15',NULL,30.00,1),('55667788E','11223344C','2023-02-20',NULL,35.00,1);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `clientes_en_riesgo_de_baja`
--

DROP TABLE IF EXISTS `clientes_en_riesgo_de_baja`;
/*!50001 DROP VIEW IF EXISTS `clientes_en_riesgo_de_baja`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `clientes_en_riesgo_de_baja` AS SELECT 
 1 AS `nif_cliente`,
 1 AS `nombre`,
 1 AS `apellidos`,
 1 AS `ultima_fecha_pago`,
 1 AS `dias_sin_pagar`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `clientesconcuotasvencidas`
--

DROP TABLE IF EXISTS `clientesconcuotasvencidas`;
/*!50001 DROP VIEW IF EXISTS `clientesconcuotasvencidas`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `clientesconcuotasvencidas` AS SELECT 
 1 AS `nif_cliente`,
 1 AS `nombre`,
 1 AS `apellidos`,
 1 AS `ultima_fecha_pago`,
 1 AS `dias_desde_ultimo_pago`,
 1 AS `cuota`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `idCompra` int NOT NULL AUTO_INCREMENT,
  `idCliente` varchar(50) NOT NULL,
  `idProducto` varchar(50) NOT NULL,
  `fechaCompra` date DEFAULT NULL,
  `cantidad` int DEFAULT '1',
  PRIMARY KEY (`idCompra`),
  KEY `idCliente` (`idCliente`),
  KEY `idProducto` (`idProducto`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`nif`),
  CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dietas`
--

DROP TABLE IF EXISTS `dietas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dietas` (
  `id` varchar(36) NOT NULL,
  `nifCliente` varchar(20) DEFAULT NULL,
  `idEntrenador` varchar(36) DEFAULT NULL,
  `numCalorias` int DEFAULT NULL,
  `objetivo` varchar(50) DEFAULT NULL,
  `grCarbs` int DEFAULT NULL,
  `grProt` int DEFAULT NULL,
  `grGrasas` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nifCliente` (`nifCliente`),
  CONSTRAINT `dietas_ibfk_1` FOREIGN KEY (`nifCliente`) REFERENCES `clientes` (`nif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dietas`
--

LOCK TABLES `dietas` WRITE;
/*!40000 ALTER TABLE `dietas` DISABLE KEYS */;
INSERT INTO `dietas` VALUES ('abffb48e-4934-11f0-8a18-4cebbd2576d0','44332211D',NULL,2046,'mantener peso',205,153,68),('bc2c1509-4938-11f0-8a18-4cebbd2576d0','55667788E','11223344C',2137,'mantener peso',214,160,71),('c22f8cb6-4770-11f0-8a18-4cebbd2576d0','44332211D',NULL,2046,'mantener peso',205,153,68),('e678002c-4933-11f0-8a18-4cebbd2576d0','44332211D',NULL,2046,'mantener peso',205,153,68);
/*!40000 ALTER TABLE `dietas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrenadores`
--

DROP TABLE IF EXISTS `entrenadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrenadores` (
  `nif` varchar(50) NOT NULL,
  `tipo` enum('Musculacion','Monitor') NOT NULL,
  `fechaAlta` date NOT NULL,
  `salario` decimal(10,2) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contraseña` varchar(50) NOT NULL,
  PRIMARY KEY (`nif`),
  CONSTRAINT `entrenadores_ibfk_1` FOREIGN KEY (`nif`) REFERENCES `usuarios` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenadores`
--

LOCK TABLES `entrenadores` WRITE;
/*!40000 ALTER TABLE `entrenadores` DISABLE KEYS */;
INSERT INTO `entrenadores` VALUES ('11223344C','Monitor','2019-05-15',1800.00,1,'carlos_monitor','clave456'),('12345678A','Musculacion','2020-01-10',2000.00,1,'juan_trainer','clave123');
/*!40000 ALTER TABLE `entrenadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripcionesclases`
--

DROP TABLE IF EXISTS `inscripcionesclases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscripcionesclases` (
  `idCliente` varchar(50) NOT NULL,
  `idClase` varchar(50) NOT NULL,
  `fecha` date NOT NULL DEFAULT '2025-01-01',
  PRIMARY KEY (`idCliente`,`idClase`),
  KEY `idClase` (`idClase`),
  CONSTRAINT `inscripcionesclases_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inscripcionesclases_ibfk_2` FOREIGN KEY (`idClase`) REFERENCES `clasesdirigidas` (`idClase`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcionesclases`
--

LOCK TABLES `inscripcionesclases` WRITE;
/*!40000 ALTER TABLE `inscripcionesclases` DISABLE KEYS */;
INSERT INTO `inscripcionesclases` VALUES ('44332211D','CLASE001','2025-06-16'),('55667788E','CLASE001','2025-06-16');
/*!40000 ALTER TABLE `inscripcionesclases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos` (
  `idPago` varchar(50) NOT NULL,
  `idCliente` varchar(50) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `metodoPago` enum('Efectivo','Tarjeta','Transferencia') NOT NULL,
  `fechaPago` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `concepto` varchar(100) NOT NULL,
  PRIMARY KEY (`idPago`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` VALUES ('ce308d53-6a7e-4442-94fd-c84fb8576d09','44332211D',800.00,'Tarjeta','2025-06-14 15:48:33','Compra de 20 x Proteina en polvo'),('e99748bc-0f20-4bc3-b427-2a4f65641711','44332211D',30.00,'Efectivo','2025-06-13 22:00:00','Pago mensual'),('PAGO001','44332211D',30.00,'Tarjeta','2023-03-01 09:00:00','Pago de cuota mensual'),('PAGO002','55667788E',35.00,'Transferencia','2023-03-02 10:30:00','Pago de cuota mensual');
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idProducto` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  CONSTRAINT `productos_chk_1` CHECK ((`stock` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES ('PROD001','Proteína en polvo',40.00,30),('PROD002','Barra energética',2.50,100);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recepcionistas`
--

DROP TABLE IF EXISTS `recepcionistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recepcionistas` (
  `nif` varchar(50) NOT NULL,
  `turno` enum('Mañana','Tarde') NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contraseña` varchar(50) NOT NULL,
  `salario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`nif`),
  CONSTRAINT `recepcionistas_ibfk_1` FOREIGN KEY (`nif`) REFERENCES `usuarios` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recepcionistas`
--

LOCK TABLES `recepcionistas` WRITE;
/*!40000 ALTER TABLE `recepcionistas` DISABLE KEYS */;
INSERT INTO `recepcionistas` VALUES ('87654321B','Mañana','maria_recep','clave789',1500.00);
/*!40000 ALTER TABLE `recepcionistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rutinas`
--

DROP TABLE IF EXISTS `rutinas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rutinas` (
  `idRutina` varchar(50) NOT NULL,
  `idEntrenador` varchar(50) NOT NULL,
  `idCliente` varchar(50) NOT NULL,
  `tipoRutina` enum('FB','TP','PPL-TP','PPL') DEFAULT NULL,
  PRIMARY KEY (`idRutina`),
  KEY `idEntrenador` (`idEntrenador`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `rutinas_ibfk_1` FOREIGN KEY (`idEntrenador`) REFERENCES `entrenadores` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rutinas_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rutinas`
--

LOCK TABLES `rutinas` WRITE;
/*!40000 ALTER TABLE `rutinas` DISABLE KEYS */;
INSERT INTO `rutinas` VALUES ('d27403ec-4773-11f0-8a18-4cebbd2576d0','12345678A','44332211D','TP'),('fa61abf2-4933-11f0-8a18-4cebbd2576d0','11223344C','44332211D','PPL'),('RUTINA001','12345678A','44332211D','FB'),('RUTINA002','11223344C','55667788E','PPL');
/*!40000 ALTER TABLE `rutinas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `nif` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `fechaNac` date NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `altura` int NOT NULL,
  `peso` decimal(5,2) DEFAULT NULL,
  `actividad` int DEFAULT NULL,
  PRIMARY KEY (`nif`),
  CONSTRAINT `usuarios_chk_1` CHECK ((`actividad` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('11223344C','Carlos','Gómez Ruiz','1975-11-30','Masculino',180,80.00,4),('12345678A','Juan','Pérez López','1985-02-15','Masculino',175,70.50,3),('44332211D','Laura','Fernández Sánchez','1995-04-12','Femenino',165,60.00,1),('55667788E','Ana','López Díaz','1989-09-25','Femenino',170,65.20,5),('87654321B','María','García Martín','1990-06-20','Femenino',160,55.30,2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoraciones`
--

DROP TABLE IF EXISTS `valoraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valoraciones` (
  `id` varchar(50) NOT NULL,
  `idCliente` varchar(50) NOT NULL,
  `idEntrenador` varchar(50) NOT NULL,
  `puntuacion` int DEFAULT NULL,
  `comentario` text,
  PRIMARY KEY (`id`),
  KEY `idCliente` (`idCliente`),
  KEY `idEntrenador` (`idEntrenador`),
  CONSTRAINT `valoraciones_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `valoraciones_ibfk_2` FOREIGN KEY (`idEntrenador`) REFERENCES `entrenadores` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `valoraciones_chk_1` CHECK ((`puntuacion` between 0 and 10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoraciones`
--

LOCK TABLES `valoraciones` WRITE;
/*!40000 ALTER TABLE `valoraciones` DISABLE KEYS */;
INSERT INTO `valoraciones` VALUES ('78f6ad77-6f1e-4c46-912d-a1751a95d876','44332211D','12345678A',2,'Es muy mal entrenador. No presta atencion cuando hago los ejercicios.'),('f2ebdcbc-083b-4907-b69f-36b3c38dbc9c','44332211D','11223344C',3,'Mal entrenador'),('VAL001','44332211D','12345678A',8,'Muy buena atención y entrenamiento personalizado.'),('VAL002','55667788E','11223344C',9,'Excelente motivación y seguimiento.');
/*!40000 ALTER TABLE `valoraciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `clientes_en_riesgo_de_baja`
--

/*!50001 DROP VIEW IF EXISTS `clientes_en_riesgo_de_baja`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `clientes_en_riesgo_de_baja` AS select `c`.`nif` AS `nif_cliente`,`u`.`nombre` AS `nombre`,`u`.`apellidos` AS `apellidos`,`c`.`fechaAlta` AS `ultima_fecha_pago`,(to_days(curdate()) - to_days(`c`.`fechaAlta`)) AS `dias_sin_pagar` from (`clientes` `c` join `usuarios` `u` on((`c`.`nif` = `u`.`nif`))) where ((`c`.`activo` = 1) and ((to_days(curdate()) - to_days(`c`.`fechaAlta`)) > 7)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `clientesconcuotasvencidas`
--

/*!50001 DROP VIEW IF EXISTS `clientesconcuotasvencidas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `clientesconcuotasvencidas` AS select `c`.`nif` AS `nif_cliente`,`u`.`nombre` AS `nombre`,`u`.`apellidos` AS `apellidos`,`c`.`fechaAlta` AS `ultima_fecha_pago`,(to_days(curdate()) - to_days(`c`.`fechaAlta`)) AS `dias_desde_ultimo_pago`,`c`.`cuota` AS `cuota` from (`clientes` `c` join `usuarios` `u` on((`c`.`nif` = `u`.`nif`))) where ((`c`.`activo` = 1) and ((to_days(curdate()) - to_days(`c`.`fechaAlta`)) > 30)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-15 17:18:05
