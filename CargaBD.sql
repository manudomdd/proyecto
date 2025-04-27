-- Insertar más usuarios (clientes, entrenadores y recepcionistas)
INSERT INTO usuarios (id, nombre, apellidos, correo, contraseña, tipoUsuario) VALUES
('C001', 'Manuel', 'García Pérez', 'manuel.gp@example.com', 'manuel123', 'Cliente'),
('C002', 'Lucía', 'Fernández Gómez', 'lucia.fg@example.com', 'lucia456', 'Cliente'),
('C003', 'David', 'Hernández Ruiz', 'david.hr@example.com', 'david789', 'Cliente'),
('C004', 'Elena', 'Martín Torres', 'elena.mt@example.com', 'elena321', 'Cliente'),
('E001', 'Carlos', 'Rodríguez Martín', 'carlos.rm@example.com', 'carlos789', 'Entrenador'),
('E002', 'Marta', 'López Sánchez', 'marta.ls@example.com', 'marta321', 'Entrenador'),
('E003', 'Alejandro', 'Gómez Díaz', 'alejandro.gd@example.com', 'alejandro111', 'Entrenador'),
('E004', 'Sara', 'Jiménez Fernández', 'sara.jf@example.com', 'sara222', 'Entrenador'),
('R001', 'Sergio', 'Díaz Romero', 'sergio.dr@example.com', 'sergio654', 'Recepcionista'),
('R002', 'Ana', 'Castillo Navarro', 'ana.cn@example.com', 'ana987', 'Recepcionista');

-- Insertar más clientes
INSERT INTO clientes (id, membresia, fechaAlta, cuota) VALUES
('C001', 'Premium', '2025-01-15', 49.99),
('C002', 'Básica', '2025-02-10', 29.99),
('C003', 'Estándar', '2025-03-05', 39.99),
('C004', 'Premium', '2025-04-01', 49.99);

-- Insertar más entrenadores
INSERT INTO entrenadores (id, especialidad, salario, turno) VALUES
('E001', 'Musculación', 1800.50, 'Mañana'),
('E002', 'Crossfit', 1750.75, 'Tarde'),
('E003', 'Cardio y resistencia', 1600.00, 'Mañana'),
('E004', 'Pilates', 1700.00, 'Tarde');

-- Insertar más recepcionistas
INSERT INTO recepcionistas (id, salario, turno) VALUES
('R001', 1500.00, 'Mañana'),
('R002', 1600.00, 'Tarde');

-- Insertar más dietas
INSERT INTO dietas (id, idCliente, idEntrenador, numCalorias, objetivo, grCarbohidratos, grProteinas, grGrasas) VALUES
('D001', 'C001', 'E001', 2500, 'Aumento de masa muscular', 300, 150, 80),
('D002', 'C002', 'E002', 1800, 'Definición muscular', 200, 120, 50),
('D003', 'C003', 'E003', 2200, 'Mejorar resistencia', 280, 140, 70),
('D004', 'C004', 'E004', 2000, 'Tonificación muscular', 250, 130, 60);

-- Insertar más rutinas
INSERT INTO rutinas (idRutina, idEntrenador, idCliente, tipoRutina) VALUES
('R001', 'E001', 'C001', 'FB'),
('R002', 'E002', 'C002', 'PPL'),
('R003', 'E003', 'C003', 'TP'),
('R004', 'E004', 'C004', 'PPL-TP');

-- Insertar más productos
INSERT INTO productos (idProducto, nombre, precio, stock) VALUES
('P001', 'Proteína en polvo', 35.99, 50),
('P002', 'Barrita energética', 2.99, 200),
('P003', 'Creatina Monohidratada', 25.99, 80),
('P004', 'Multivitamínico', 15.99, 100),
('P005', 'Botella de agua reutilizable', 12.99, 150);

-- Insertar más tickets de compra
INSERT INTO ticketCompra (idTicket, idCliente, totalCompra, fechaCompra) VALUES
('T001', 'C001', 38.98, '2025-04-27 16:30:00'),
('T002', 'C002', 35.99, '2025-04-27 17:45:00'),
('T003', 'C003', 27.99, '2025-04-27 18:10:00'),
('T004', 'C004', 48.99, '2025-04-27 18:30:00');

-- Insertar más detalles de compra
INSERT INTO detallesCompra (idTicket, idProducto, cantidad) VALUES
('T001', 'P001', 1),
('T001', 'P002', 1),
('T002', 'P001', 1),
('T003', 'P003', 1),
('T003', 'P004', 1),
('T004', 'P005', 2);

-- Insertar más pagos
INSERT INTO pagos (idPago, idCliente, monto, metodoPago, fechaPago, concepto) VALUES
('PA001', 'C001', 49.99, 'Tarjeta', '2025-04-01 10:00:00', 'Pago mensual gimnasio'),
('PA002', 'C002', 29.99, 'Efectivo', '2025-04-01 11:30:00', 'Pago mensual gimnasio'),
('PA003', 'C003', 39.99, 'Transferencia', '2025-04-01 12:00:00', 'Pago mensual gimnasio'),
('PA004', 'C004', 49.99, 'Tarjeta', '2025-04-01 13:00:00', 'Pago mensual gimnasio');

-- Insertar más ingresos
INSERT INTO ingresos (idIngreso, idPago, idTicket, fuente, montoTotal, fechaRegistro) VALUES
('I001', 'PA001', NULL, 'Membresia', 49.99, '2025-04-01 10:00:00'),
('I002', 'PA002', NULL, 'Membresia', 29.99, '2025-04-01 11:30:00'),
('I003', 'PA003', NULL, 'Membresia', 39.99, '2025-04-01 12:00:00'),
('I004', 'PA004', NULL, 'Membresia', 49.99, '2025-04-01 13:00:00'),
('I005', NULL, 'T001', 'Tienda', 38.98, '2025-04-27 16:30:00'),
('I006', NULL, 'T002', 'Tienda', 35.99, '2025-04-27 17:45:00'),
('I007', NULL, 'T003', 'Tienda', 27.99, '2025-04-27 18:10:00'),
('I008', NULL, 'T004', 'Tienda', 48.99, '2025-04-27 18:30:00');
