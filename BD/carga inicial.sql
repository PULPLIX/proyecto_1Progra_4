
USE banco ;
-- -----------------------------------------------------------------------------------------
-- Usuario
-- -----------------------------------------------------------------------------------------

INSERT INTO usuario (id_usuario, clave_acceso,rol) values
('222','222',1);
INSERT INTO usuario (id_usuario, clave_acceso,rol) values
('111','111',1);
INSERT INTO usuario (id_usuario, clave_acceso,rol) values
('admin','admin',2);
select * from usuario;

-- -----------------------------------------------------------------------------------------
-- Cliente
-- -----------------------------------------------------------------------------------------
insert into cliente (id_cliente,usuario_id_usuario,apellidos,nombre,telefono) values (11738,'222','Alvarado Gutierrez','Oscar','2254-8891');
insert into cliente (id_cliente,usuario_id_usuario,apellidos,nombre,telefono) values (12318,'111','Aguilar Rojas','David','2224-8891');
insert into cliente (id_cliente,usuario_id_usuario,apellidos,nombre,telefono) values (123,'admin','Sanchez','Jose','2224-8891');

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

