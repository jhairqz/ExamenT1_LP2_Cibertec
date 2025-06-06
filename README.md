# 🛡️ Sistema de Gestión de Certificaciones

Este proyecto consiste en una aplicación de escritorio desarrollada en Java (Swing) que permite registrar y visualizar certificaciones emitidas por una entidad. La solución se basa en una arquitectura por capas (DAO, Service, Entity, UI) e integra Hibernate como ORM para conectarse a una base de datos MySQL.

## 📋 Funcionalidades

- Registro de certificaciones con datos como:
    - Cliente
    - Tipo de auditoría
    - Especialista
    - Fechas de emisión y vencimiento
    - Estado de la certificación
- Visualización de todas las certificaciones en una tabla
- Inserción dinámica de nuevos clientes desde la interfaz
- Validación completa de campos antes de guardar
- Interfaz moderna, con columnas ajustadas, scroll horizontal y contenido centrado

## 🧱 Tecnologías Utilizadas

- **Java 8+**
- **Swing** (Interfaz gráfica)
- **Hibernate** (ORM)
- **MySQL** (Base de datos relacional)
- **Maven** (Gestor de dependencias)
- **JPA** (Java Persistence API)

## 📐 Arquitectura del Proyecto

📁 src/main/java
├── dao
├── dao.impl
├── entity
├── service
├── service.impl
└── swing (Interfaz de usuario - JFrame principal)


## 🗃️ Estructura de Base de Datos

La base de datos contiene las siguientes tablas:

- `Cliente`
- `TipoAuditoria`
- `Especialista`
- `Certificacion`

La tabla `Certificacion` cuenta con claves foráneas hacia las demás entidades. El estado es representado como un campo `ENUM` con valores `VIGENTE`, `EXPIRADA` y `EN_REVISIÓN`.

## 🚀 Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/sistema-certificaciones.git

Importa el proyecto en tu IDE (por ejemplo, Eclipse o IntelliJ) como proyecto Maven.

Configura tu base de datos MySQL:

Crea una base de datos llamada BD1_i202111582.

Actualiza tu archivo persistence.xml si cambias usuario/contraseña.

Ejecuta la clase RegistroCertificacion para abrir la interfaz.

Nota: Al iniciar, Hibernate usará create-drop, por lo que la base de datos se recreará cada vez. Para conservar los datos, cambia el valor a update.

📸 Capturas

## Registro

![Registro](https://prnt.sc/3d9F4dwDWn40)

## Listado

![Listado](https://prnt.sc/HQdjX6Cegunt)


Autor
Gustavo Jhair Salguerano Salazar
Estudiante de Computación e Informática – Cibertec
