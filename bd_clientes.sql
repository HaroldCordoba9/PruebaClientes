-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.37 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para bd_clientes
CREATE DATABASE IF NOT EXISTS `bd_clientes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_clientes`;

-- Volcando estructura para tabla bd_clientes.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `shared_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `business_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `data_added` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla bd_clientes.cliente: ~5 rows (aproximadamente)
INSERT INTO `cliente` (`id`, `shared_key`, `business_id`, `email`, `phone`, `data_added`, `start_date`, `end_date`) VALUES
	(1, 'jgutierrez', 'Juliana Gutierrez', 'jgutierrez@gmail.com', '3219876543', '2024-11-23', '2024-01-01', '2024-10-31'),
	(2, 'mmartinez', 'Manuel Martínez', 'mmartinez@gmail.com', '3219876544', '2024-11-23', '2023-09-15', '2024-09-30'),
	(3, 'aruiz', 'Ana Ruíz', 'aruiz@gmail.com', '3219876545', '2024-11-23', '2023-04-12', NULL),
	(4, 'ogarcia', 'Oscar García', 'ogarcia@gmail.com', '3219876546', '2024-11-23', '2024-11-01', NULL),
	(5, 'tramos', 'Tania Ramos', 'tramos@gmail.com', '3219876547', '2024-11-23', '2024-11-02', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
