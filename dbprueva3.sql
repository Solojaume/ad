-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 13-02-2020 a las 23:19:52
-- Versión del servidor: 8.0.17
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbprueva3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `categoria` bigint(20) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`id`, `nombre`, `precio`, `categoria`) VALUES
(1, 'artículo 1', '1.00', 1),
(2, 'artículo 2', '2.00', 2),
(3, 'artículo 3', '3.00', 3),
(5, 'Art 2020-01-29T11:54:16.153471', '82.00', 4),
(6, 'art 6', '9.55', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(5, 'cat 2020-01-27T09:21:49.433018'),
(6, 'cat 2020-01-27T09:40:45.143172'),
(7, 'cat 2020-01-31T08:23:21.669025'),
(8, 'cat 2020-02-10T08:32:28.094127'),
(1, 'categoría 1'),
(2, 'categoría 2'),
(3, 'categoría 3'),
(4, 'categoria 4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`) VALUES
(1, 'Cli 1'),
(2, 'cliente 2'),
(3, 'cliente 3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `fecha` datetime NOT NULL,
  `cliente` bigint(20) UNSIGNED NOT NULL,
  `importe` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `fecha`, `cliente`, `importe`) VALUES
(2, '2020-02-12 11:39:54', 2, '364.00'),
(3, '2020-02-13 08:59:28', 2, '309.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidolinea`
--

CREATE TABLE `pedidolinea` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `pedido` bigint(20) UNSIGNED NOT NULL,
  `articulo` bigint(20) UNSIGNED NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `unidades` decimal(10,2) NOT NULL,
  `importe` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedidolinea`
--

INSERT INTO `pedidolinea` (`id`, `pedido`, `articulo`, `precio`, `unidades`, `importe`) VALUES
(4, 2, 2, '2.00', '100.00', '200.00'),
(5, 2, 1, '1.00', '89.00', '89.00'),
(6, 2, 3, '3.00', '25.00', '75.00'),
(7, 3, 2, '2.00', '150.00', '300.00'),
(8, 3, 1, '1.00', '9.00', '9.00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `categoria` (`categoria`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `fecha` (`fecha`),
  ADD KEY `cliente` (`cliente`);

--
-- Indices de la tabla `pedidolinea`
--
ALTER TABLE `pedidolinea`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `pedido` (`pedido`),
  ADD KEY `articulo` (`articulo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pedidolinea`
--
ALTER TABLE `pedidolinea`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `pedidolinea`
--
ALTER TABLE `pedidolinea`
  ADD CONSTRAINT `pedidolinea_ibfk_1` FOREIGN KEY (`pedido`) REFERENCES `pedido` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `pedidolinea_ibfk_2` FOREIGN KEY (`articulo`) REFERENCES `articulo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
