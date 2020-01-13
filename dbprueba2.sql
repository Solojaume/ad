START TRANSACTION;
CREATE DATABASE dbprueva2;
use dbprueva2;
CREATE TABLE `clientes` (
  `id_cliente` bigint(20) UNSIGNED NOT NULL AUTO_Increment,
  `dni` varchar(100) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `telefono` decimal(10,2) DEFAULT NULL,
   PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `paquetes` (
  `id_paquete` bigint(20) UNSIGNED NOT NULL AUTO_Increment,
  `cantidad` bigint(250) DEFAULT NULL,
  `precio` bigint(200) DEFAULT NULL,
  `id_cliente` bigint(20) UNSIGNED DEFAULT NULL,
   PRIMARY KEY (`id_paquete`),
   FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
   KEY `id_cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
Commit;
