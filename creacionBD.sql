-- Eliminar y crear la base de datos
DROP DATABASE IF EXISTS Gimnasio;
CREATE DATABASE Gimnasio;
USE Gimnasio;

-- Crear tabla usuarios (abstracta)
CREATE TABLE usuarios (
    id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(50) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    tipoUsuario ENUM('Cliente', 'Entrenador', 'Recepcionista') NOT NULL
);

-- Crear tabla clientes
CREATE TABLE clientes (
    id VARCHAR(50) PRIMARY KEY,
    membresia VARCHAR(50),
    fechaAlta DATE NOT NULL,
    cuota DECIMAL(5,2),
    FOREIGN KEY (id) REFERENCES usuarios(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla entrenadores con salario y turno
CREATE TABLE entrenadores (
    id VARCHAR(50) PRIMARY KEY,
    especialidad VARCHAR(50),
    salario DECIMAL(10,2) NOT NULL,
    turno ENUM('Mañana', 'Tarde') NOT NULL,
    FOREIGN KEY (id) REFERENCES usuarios(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla recepcionistas con salario y turno
CREATE TABLE recepcionistas (
    id VARCHAR(50) PRIMARY KEY,
    salario DECIMAL(10,2) NOT NULL,
    turno ENUM('Mañana', 'Tarde') NOT NULL,
    FOREIGN KEY (id) REFERENCES usuarios(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla dietas
CREATE TABLE dietas (
    id VARCHAR(50) PRIMARY KEY,
    idCliente VARCHAR(50) NOT NULL,
    idEntrenador VARCHAR(50) NOT NULL,
    numCalorias INT NOT NULL,
    objetivo VARCHAR(100),
    grCarbohidratos INT NOT NULL,
    grProteinas INT NOT NULL,
    grGrasas INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES clientes(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (idEntrenador) REFERENCES entrenadores(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla rutinas
CREATE TABLE rutinas (
    idRutina VARCHAR(50) PRIMARY KEY,
    idEntrenador VARCHAR(50) NOT NULL,
    idCliente VARCHAR(50) NOT NULL,
    tipoRutina ENUM ('FB', 'TP', 'PPL-TP', 'PPL'),
    FOREIGN KEY (idEntrenador) REFERENCES entrenadores(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (idCliente) REFERENCES clientes(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla valoraciones
CREATE TABLE valoraciones (
    id VARCHAR(50) PRIMARY KEY,
    idCliente VARCHAR(50) NOT NULL,
    idEntrenador VARCHAR(50) NOT NULL,
    puntuacion INT CHECK (puntuacion BETWEEN 0 AND 10),
    comentario TEXT,
    FOREIGN KEY (idCliente) REFERENCES clientes(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (idEntrenador) REFERENCES entrenadores(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla clases dirigidas
CREATE TABLE clasesDirigidas (
    idClase VARCHAR(50) PRIMARY KEY,
    nombreClase VARCHAR(50),
    idEntrenador VARCHAR(50) NOT NULL,
    aforo INT CHECK (aforo > 0),
    FOREIGN KEY (idEntrenador) REFERENCES entrenadores(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla inscripciones a clases
CREATE TABLE inscripcionesClases (
    idCliente VARCHAR(50),
    idClase VARCHAR(50),
    PRIMARY KEY (idCliente, idClase),
    FOREIGN KEY (idCliente) REFERENCES clientes(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (idClase) REFERENCES clasesDirigidas(idClase) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla productos
CREATE TABLE productos (
    idProducto VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT CHECK (stock >= 0)
);

-- Crear tabla ticket de compra
CREATE TABLE ticketCompra (
    idTicket VARCHAR(50) PRIMARY KEY,
    idCliente VARCHAR(50) NOT NULL,
    totalCompra DECIMAL(10,2) NOT NULL,
    fechaCompra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idCliente) REFERENCES clientes(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla detalles de compra
CREATE TABLE detallesCompra (
    idTicket VARCHAR(50),
    idProducto VARCHAR(50),
    cantidad INT CHECK (cantidad > 0),
    PRIMARY KEY (idTicket, idProducto),
    FOREIGN KEY (idTicket) REFERENCES ticketCompra(idTicket) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla pagos
CREATE TABLE pagos (
    idPago VARCHAR(50) PRIMARY KEY,
    idCliente VARCHAR(50) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    metodoPago ENUM('Efectivo', 'Tarjeta', 'Transferencia') NOT NULL,
    fechaPago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    concepto VARCHAR(100) NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES clientes(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Crear tabla ingresos
CREATE TABLE ingresos (
    idIngreso VARCHAR(50) PRIMARY KEY,
    idPago VARCHAR(50) NULL, 
    idTicket VARCHAR(50) NULL, 
    fuente ENUM('Membresia', 'Tienda') NOT NULL,
    montoTotal DECIMAL(10,2) NOT NULL,
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idPago) REFERENCES pagos(idPago) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (idTicket) REFERENCES ticketCompra(idTicket) ON UPDATE CASCADE ON DELETE SET NULL
);