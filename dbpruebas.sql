Create table categoria(
    id serial primary key,
    nombre varchar(50) unique not null
);

/*Ejercicio crear una tabla llamada articulos con los campos:
    -id
    -nombre
    -precio
    -categoria: bigint
Menu :
    -Editar
    -Crear
    -Consultar
    -Eliminar 
    -Listar*/
CREATE TABLE `dbprueba`.`articulos` ( 
    `id` SERIAL NOT NULL AUTO_INCREMENT , 
    `nombre` VARCHAR(50) NOT NULL , 
    `precio` DECIMAL(10,2) NULL , 
    `categoria` BIGINT UNSIGNED NOT NULL ,
     PRIMARY KEY (`id`),
     FOREIGN KEY (`categoria`) REFERENCES categoria(`id`)
 ) ENGINE = InnoDB;

INSERT INTO `articulo` (`id`, `nombre`, `precio`, `categoria`) VALUES (NULL, 'ARTICULO1', '9.99', '1')
INSERT INTO `articulo` ( `nombre`, `precio`, `categoria`) VALUES ('ARTICULO1', null, '1')
