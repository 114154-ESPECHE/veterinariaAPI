INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni, password, fecha_nacimiento, fecha_alta)
VALUES ('Juan', 'Perez', 'Calle A, Ciudad', 123456789, 'juan@example.com', 123456789, '123456', '2000-01-01', '2023-07-06');

INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni,password, fecha_nacimiento, fecha_alta)
VALUES ('Carla', 'Olocco', 'Calle B, Ciudad', 987654321, 'maria@example.com', 31736067,'123456', '1986-07-11', '2023-07-06');

INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni, password, fecha_nacimiento, fecha_alta)
VALUES ('Pedro', 'González', 'Calle C, Ciudad', 456789123, 'pedro@example.com', 456789123,'123456', '2000-03-03', '2023-07-06');

INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni,password, fecha_nacimiento, fecha_alta)
VALUES ('Ana', 'Martínez', 'Calle D, Ciudad', 789123456, 'ana@example.com', 789123456,'123456', '2000-04-04', '2023-07-06');

INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni,password, fecha_nacimiento, fecha_alta)
VALUES ('Luis', 'Rodríguez', 'Calle E, Ciudad', 654321987, 'luis@example.com', 654321987,'123456', '2000-05-05', '2023-07-06');

INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni,password, fecha_nacimiento, fecha_alta)
VALUES ('Sin mascota', 'Solo', 'Calle E, Ciudad', 654321987, 'luis@example.com', 1111111,'123456', '2000-05-05', '2023-07-06');


INSERT INTO mascotas (id_cliente, nombre, color, edad, especie)
VALUES (3, 'Pichu', 'Negro', 3, 0);

INSERT INTO mascotas (id_cliente, nombre, color, edad, especie)
VALUES (2, 'Pichu', 'blanco', 1, 2);

INSERT INTO mascotas (id_cliente, nombre, color, edad, especie)
VALUES (1, 'Mel', 'Blanco', 2, 1);

INSERT INTO mascotas (id_cliente, nombre, color, edad, especie)
VALUES (2, 'Leon', 'Marrón', 5, 1);

INSERT INTO mascotas (id_cliente, nombre, color, edad, especie)
VALUES (5, 'Negro', 'Gris', 1, 0);

INSERT INTO mascotas (id_cliente, nombre, color, edad, especie)
VALUES (4, 'Micho', 'Blanco y negro', 4, 0);


INSERT INTO HISTORIAMASCOTAS (id_mascota, fecha, evento, descripcion) VALUES (1, '2023-06-15', 'Cirugía', 'Esterilización');
INSERT INTO HISTORIAMASCOTAS (id_mascota, fecha, evento, descripcion) VALUES (2, '2023-07-02', 'Consulta', 'Examen de rutina');
INSERT INTO HISTORIAMASCOTAS (id_mascota, fecha, evento, descripcion) VALUES (3, '2023-06-30', 'Vacunación', 'Vacuna antirrábica');
INSERT INTO HISTORIAMASCOTAS (id_mascota, fecha, evento, descripcion) VALUES (4, '2023-07-03', 'Consulta', 'Control de peso');
INSERT INTO HISTORIAMASCOTAS (id_mascota, fecha, evento, descripcion) VALUES (5, '2023-06-29', 'Vacunación', 'Vacuna anual');
INSERT INTO HISTORIAMASCOTAS (id_mascota, fecha, evento, descripcion) VALUES (5, '2023-06-29', 'Consulta', 'Caida de pelo y picazon');


INSERT INTO usuarios (dni, password, fecha_nacimiento, telefono, direccion, email, fecha_alta)
VALUES (35576827, '123456', '1991-02-13', 3518109725, 'Dean Funes', 'admin@example.com', '2023-07-06');
INSERT INTO usuarios (dni, password, fecha_nacimiento, telefono, direccion, email, fecha_alta)
VALUES (31736067, 'abcdef', '1995-05-15', 5555555555, 'Calle B, Ciudad', 'usuario1@example.com', '2023-07-06');
INSERT INTO usuarios (dni, password, fecha_nacimiento, telefono, direccion, email, fecha_alta)
VALUES (456789123, 'qwerty', '1988-12-10', 9999999999, 'Calle C, Ciudad', 'usuario2@example.com', '2023-07-06');