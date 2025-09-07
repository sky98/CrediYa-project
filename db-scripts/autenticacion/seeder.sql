INSERT INTO pragma_autenticacion_db.`roles` (`nombre`, `descripcion`) VALUES
('ADMINISTRADOR', 'Tiene acceso completo a la aplicación y a la gestión de usuarios.'),
('ASESOR', 'Puede gestionar y asesorar a los clientes.'),
('CLIENTE', 'Puede acceder a su información y servicios personales.');

INSERT INTO pragma_autenticacion_db.`usuarios`
(`usuario_id`, `documento_id`, `nombres`, `apellidos`, `fecha_nacimiento`, `direccion`, `telefono`, `correo_electronico`, `salario_base`, `estado`, `contrasena`, `rol_id`)VALUES
(1, 1102850500, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo0@test.com', 15000000.00, 'ACTIVO', '$2a$10$r45s84h4lZTrT3IXDtju9uY8KzV.HHXZFQfcw2kRvtxLWx6pw5Jce', 1),
(2, 1102850501, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo1@test.com', 15000000.00, 'ACTIVO', '$2a$10$sMWdaAm3ZWx9nog.SFKrPeoXvYn9fepV7ixMc1zMValdswx9KGCqe', 2),
(3, 1102850502, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo2@test.com', 7000000.00, 'ACTIVO', '$2a$10$Rv4l0mvIdGDhIBQd1mYnhOiV/ji0mQ9xyG4ACCOSXcZPNqsErDuGy', 3),
(4, 1102850503, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo3@test.com', 8000000.00, 'ACTIVO', '$2a$10$VdMS0x5n2s/QSpYIw0sbh.5WV9c7MuKrX4b8ne5Q5av4ae5Vv14XK', 3),
(5, 1102850504, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo4@test.com', 3500000.00, 'ACTIVO', '$2a$10$RS.fWIRHNF.K1y0FrYRApOcge7d0XhF4Xgnze2FiMuaarbm5PvzAS', 3),
(6, 1102850505, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo5@test.com', 6800000.00, 'ACTIVO', '$2a$10$08nFV5ux8byZdzwzAQopN.VZ9/eSn9dP74hkXtY1r25LY/TDcOkkm', 3),
(7, 1102850506, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo6@test.com', 4500000.00, 'ACTIVO', '$2a$10$0Wegw9GttvH6pGtF/WHoK.oUeZmTcZocJFidGJ2zcibxRiYSWuMEG', 3),
(8, 1102850507, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo7@test.com', 5600000.00, 'ACTIVO', '$2a$10$gMNWt3wagspOjPj5K1eOBu/N6xx7Z7i/mS/mFqRhJOfHILcGXcxfK', 3),
(9, 1102850508, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo8@test.com', 8654000.00, 'ACTIVO', '$2a$10$GTG693Sn27qU/KXl44R59.xgRrC6VX50FIFaYTjSOiRR5wzGO5RfO', 3),
(10, 1102850509, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo9@test.com', 2300000.00, 'ACTIVO', '$2a$10$D5Iz4DdbxlA5nGMP6A2AWuVvmoqIehFHe3pkB3H774S2xTOLFmPZ6', 3),
(11, 1102850510, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo10@test.com', 2300000.00, 'ACTIVO', '$2a$10$D5Iz4DdbxlA5nGMP6A2AWuVvmoqIehFHe3pkB3H774S2xTOLFmPZ6', 3),
(12, 1102850511, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo11@test.com', 2300000.00, 'ACTIVO', '$2a$10$D5Iz4DdbxlA5nGMP6A2AWuVvmoqIehFHe3pkB3H774S2xTOLFmPZ6', 3),
(13, 1102850512, 'Test nombres', 'asda', '1990-05-15 00:00:00', 'Calle Falsa 123', '555-1234', 'correo12@test.com', 2300000.00, 'ACTIVO', '$2a$10$D5Iz4DdbxlA5nGMP6A2AWuVvmoqIehFHe3pkB3H774S2xTOLFmPZ6', 3);