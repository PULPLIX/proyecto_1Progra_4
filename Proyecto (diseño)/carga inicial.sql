
USE banco ;
-- -----------------------------------------------------------------------------------------
-- Usuario
-- -----------------------------------------------------------------------------------------

select * from usuario;
INSERT INTO usuario (id_usuario, clave_acceso,rol) values
('222','222',1);
INSERT INTO usuario (id_usuario, clave_acceso,rol) values
('111','111',1);

-- -----------------------------------------------------------------------------------------
-- Cliente
-- -----------------------------------------------------------------------------------------
insert into cliente (id_cliente,usuario_id_usuario,apellidos,nombre,telefono) values (11738,'222','Alvarado Gutierrez','Oscar','2254-8891');
insert into cliente (id_cliente,usuario_id_usuario,apellidos,nombre,telefono) values (12318,'111','Aguilar Rojas','David','2224-8891');
select * from cliente; 

-- -----------------------------------------------------------------------------------------
-- Moneda
-- -----------------------------------------------------------------------------------------
INSERT INTO moneda   (descripcion,simbolo,tipo_cambio_compra,tipo_cambio_venta)
	VALUES	('Colón', '₡', '1.0', '1.0'),
		('Dólar EEUU', '$', '560.0', '570.0'),
		('Euro', '€', '700.0', '720.0');
SELECT * FROM  moneda;
-- -----------------------------------------------------------------------------------------
-- Tipo Cuenta
-- -----------------------------------------------------------------------------------------
INSERT INTO tipo_cuenta   (id_tipo_cuenta,descripción,tasa_interés) VALUES (1, 'Debito',0.3);
INSERT INTO tipo_cuenta   (id_tipo_cuenta,descripción,tasa_interés) VALUES (2, 'Crédito',0.3);
SELECT * FROM tipo_cuenta;
-- -----------------------------------------------------------------------------------------
-- Cuenta
-- -----------------------------------------------------------------------------------------
select * from cuenta;
insert into cuenta (fecha_creacion,limite_transferencia_diaria,activa,saldo_inicial,fecha_ultima_aplicacion,saldo_final,moneda_nombre,idTipoCuenta,cliente_id_cliente)
values ('03-12-22', 4000,1,300000,'03-05-21',240000,1,1,'111');
insert into cuenta (fecha_creacion,limite_transferencia_diaria,activa,saldo_inicial,fecha_ultima_aplicacion,saldo_final,moneda_nombre,idTipoCuenta,cliente_id_cliente)
values ('03-12-31', 4000,1,600000,'03-05-21',600000,1,1,'111');
insert into cuenta (fecha_creacion,limite_transferencia_diaria,activa,saldo_inicial,fecha_ultima_aplicacion,saldo_final,moneda_nombre,idTipoCuenta,cliente_id_cliente)
values ('03-12-22', 4000,1,300000,'03-05-21',240000,1,1,'222');
-- -----------------------------------------------------------------------------------------
-- Transferencias
-- -----------------------------------------------------------------------------------------
select * from transferencia;
insert into transferencia (monto,fecha,aplicado,cuenta_origen,cuenta_destino)
values ('50000','03-05-21',1,1,2);
DELETE FROM transferencia
WHERE id_transferencia = 8;
-- -----------------------------------------------------------------------------------------
-- Favorita
-- -----------------------------------------------------------------------------------------
select * from favorita;
insert into favorita (cuenta_id,cliente_id)values (1,'111');
insert into favorita values (2,'111');

delete from favorita where cuenta_id = 1;

select * from cuenta c 
inner join moneda m on c.moneda_nombre = m.nombre 
inner join cliente cli on c.cliente_id_cliente = cli.usuario_id_usuario 
inner join usuario u on cli.usuario_id_usuario = u.id_usuario 
inner join tipo_cuenta tp on c.idTipoCuenta = tp.id_tipo_cuenta 
where c.num_cuenta=1;
-- --------------------------------------------------------------------------------------------------------------------------

-- --------------------------------------------------------------------------------------------------------------------------
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema banco
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema banco
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `banco` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci ;
USE `banco` ;

-- -----------------------------------------------------
-- Table `banco`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banco`.`usuario` (
  `id_usuario` VARCHAR(12) NOT NULL,
  `clave_acceso` VARCHAR(45) NOT NULL,
  `rol` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banco`.`cliente` (
  `id_cliente` INT NOT NULL,
  `usuario_id_usuario` VARCHAR(16) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NULL,
  INDEX `fk_cliente_usuario1_idx` (`usuario_id_usuario` ASC) VISIBLE,
  PRIMARY KEY (`usuario_id_usuario`),
  CONSTRAINT `fk_cliente_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `banco`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`moneda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banco`.`moneda` (
  `nombre` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(20) NOT NULL,
  `simbolo` VARCHAR(4) NOT NULL,
  `tipo_cambio_compra` DOUBLE NOT NULL,
  `tipo_cambio_venta` DOUBLE NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`tipo_cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banco`.`tipo_cuenta` (
  `id_tipo_cuenta` INT NOT NULL AUTO_INCREMENT,
  `descripción` VARCHAR(45) NOT NULL,
  `tasa_interés` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_tipo_cuenta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banco`.`cuenta` (
  `num_cuenta` INT NOT NULL AUTO_INCREMENT,
  `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `limite_transferencia_diaria` DOUBLE NOT NULL DEFAULT 1000000,
  `activa` TINYINT NOT NULL DEFAULT 1,
  `saldo_inicial` DOUBLE NOT NULL DEFAULT 0,
  `fecha_ultima_aplicacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `saldo_final` DOUBLE NOT NULL DEFAULT 0,
  `moneda_nombre` INT NOT NULL,
  `idTipoCuenta` INT NOT NULL,
  `cliente_id_cliente` VARCHAR(16) NOT NULL,
  INDEX `fk_cuenta_moneda1_idx` (`moneda_nombre` ASC) VISIBLE,
  INDEX `fk_cuenta_tipo_cuenta1_idx` (`idTipoCuenta` ASC) VISIBLE,
  PRIMARY KEY (`num_cuenta`),
  INDEX `fk_cuenta_cliente1_idx` (`cliente_id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_cuenta_moneda1`
    FOREIGN KEY (`moneda_nombre`)
    REFERENCES `banco`.`moneda` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_tipo_cuenta1`
    FOREIGN KEY (`idTipoCuenta`)
    REFERENCES `banco`.`tipo_cuenta` (`id_tipo_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `banco`.`cliente` (`usuario_id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`movimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banco`.`movimiento` (
  `id_movimiento` INT NOT NULL AUTO_INCREMENT,
  `monto` DOUBLE NOT NULL DEFAULT 0,
  `fecha` DATETIME NOT NULL DEFAULT NOW(),
  `aplicado` TINYINT NOT NULL DEFAULT 0,
  `cuenta_num_cuenta` INT NOT NULL,
  PRIMARY KEY (`id_movimiento`, `cuenta_num_cuenta`),
  INDEX `fk_movimiento_cuenta1_idx` (`cuenta_num_cuenta` ASC) VISIBLE,
  CONSTRAINT `fk_movimiento_cuenta1`
    FOREIGN KEY (`cuenta_num_cuenta`)
    REFERENCES `banco`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`favorita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banco`.`favorita` (
  `cuenta_id` INT NOT NULL,
  `cliente_id` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`cuenta_id`, `cliente_id`),
  INDEX `fk_favorita_cuenta1_idx` (`cuenta_id` ASC) VISIBLE,
  INDEX `fk_favorita_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_favorita_cuenta1`
    FOREIGN KEY (`cuenta_id`)
    REFERENCES `banco`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorita_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `banco`.`cliente` (`usuario_id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`transferencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banco`.`transferencia` (
  `id_transferencia` INT NOT NULL AUTO_INCREMENT,
  `monto` VARCHAR(45) NOT NULL DEFAULT 0,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `aplicado` TINYINT NOT NULL,
  `cuenta_destino` INT NOT NULL,
  `cuenta_origen` INT NOT NULL,
  PRIMARY KEY (`id_transferencia`, `cuenta_destino`, `cuenta_origen`),
  INDEX `fk_transferencia_cuenta2_idx` (`cuenta_destino` ASC) VISIBLE,
  INDEX `fk_transferencia_cuenta1_idx` (`cuenta_origen` ASC) VISIBLE,
  CONSTRAINT `fk_transferencia_cuenta2`
    FOREIGN KEY (`cuenta_destino`)
    REFERENCES `banco`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencia_cuenta1`
    FOREIGN KEY (`cuenta_origen`)
    REFERENCES `banco`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;