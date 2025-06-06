DROP DATABASE BD1_i202111582;
CREATE DATABASE BD1_i202111582;
USE BD1_i202111582;

CREATE TABLE Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100) NOT NULL,
    ruc CHAR(11) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL
);

CREATE TABLE TipoAuditoria (
    id_tipo_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE Especialista (
    id_especialista INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL
);

CREATE TABLE Certificacion (
    id_certificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_tipo_auditoria INT NOT NULL,
    id_especialista INT NOT NULL,
    fecha_emision DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    estado ENUM('VIGENTE', 'EXPIRADA', 'EN_REVISIÓN') NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_tipo_auditoria) REFERENCES TipoAuditoria(id_tipo_auditoria),
    FOREIGN KEY (id_especialista) REFERENCES Especialista(id_especialista)
);


INSERT INTO Cliente (nombre_empresa, ruc, correo) VALUES
('TechCorp S.A.C.', '20123456789', 'contacto@techcorp.com'),
('SecureNet Ltda.', '20456781234', 'info@securenet.pe'),
('CyberTrust S.A.', '20345678901', 'ventas@cybertrust.com'),
('Auditech S.R.L.', '20567890123', 'consultas@auditech.pe'),
('NetShield Perú', '20789012345', 'soporte@netshield.pe');

INSERT INTO TipoAuditoria (descripcion) VALUES
('ISO/IEC 27001'),
('ISO/IEC 27017'),
('ISO/IEC 27018'),
('ISO/IEC 22301'),
('SOC 2 Tipo II');

INSERT INTO Especialista (nombre_completo, dni, correo, especialidad) VALUES
('Carlos Ramírez Pérez', '72145678', 'carlos.ramirez@cibersecurity.com', 'Seguridad de la Información'),
('María Torres Delgado', '74890123', 'maria.torres@cibersecurity.com', 'Auditoría de Sistemas'),
('Luis Gutiérrez León', '73219876', 'luis.gutierrez@cibersecurity.com', 'Continuidad del Negocio'),
('Ana Morales Vega', '75643210', 'ana.morales@cibersecurity.com', 'Protección de Datos'),
('José Hidalgo Zegarra', '71234567', 'jose.hidalgo@cibersecurity.com', 'Cloud Security');

INSERT INTO Certificacion (id_cliente, id_tipo_auditoria, id_especialista, fecha_emision, fecha_vencimiento, estado) VALUES
(1, 1, 1, '2024-06-01', '2025-06-01', 'VIGENTE'),
(2, 3, 2, '2023-05-15', '2024-05-15', 'EXPIRADA'),
(3, 4, 3, '2024-01-10', '2025-01-10', 'VIGENTE'),
(4, 2, 4, '2024-03-20', '2025-03-20', 'EN_REVISIÓN'),
(5, 5, 5, '2024-02-28', '2025-02-28', 'VIGENTE');
