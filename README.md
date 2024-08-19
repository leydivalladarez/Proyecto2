# ✨Proyecto de Aplicaciones Distribuidas
## 🛠️Herramientas y tecnologías
- Oracle Database
- Netbeans 12 o superior
- IntelliJ IDEA (Opcional, en reemplazo de Netbeans)
- Java JDK 21 (~~Antes 17~~)
- Tomcat
- Spring Boot (v3.3.2)
- Maven

### API Rest Java
- Todos los datos a enviar y recibir deben estar en formato JSON.

### Frontend: React

## 🏁 Iniciar
Antes de iniciar se requiere tener instalado JDK 21 y Apache Tomcat/10.1.26.

### Clonar Repositorio
Vía HTTPS
```bash
git clone https://github.com/leydivalladarez/Proyecto2Back.git
cd Proyecto2Back
```
Vía SSH
```bash
git clone git@github.com:leydivalladarez/Proyecto2Back.git
cd Proyecto2Back
```
### Abrir en IDE
<details>
<summary>🚀 Netbeans </summary>
Para compilar tu proyecto en NetBeans, sigue estos pasos:

1. **Importar el Proyecto:**
 - Abre NetBeans.
 - Ve a `File > Open Project`.
 - Navega hasta la carpeta donde está tu proyecto Maven y selecciona la carpeta que contiene el archivo `pom.xml`.
 - Haz clic en `Open Project`.

2. **Configurar la JDK:**
 - Asegúrate de que NetBeans esté configurado para usar JDK 21.
 - Ve a `Tools > Java Platforms` y verifica que JDK 21 esté configurado. Si no es así, agrégalo.

3. **Compilar el Proyecto:**
 - Después de importar el proyecto, NetBeans debería reconocer automáticamente la configuración de Maven.
 - Puedes compilar el proyecto haciendo clic derecho sobre él en el panel de proyectos y seleccionando `Build`.

4. **Resolver Problemas de Dependencias:**
 - Si hay algún problema con las dependencias, asegúrate de que NetBeans esté conectado a internet para descargar las dependencias de Maven.
 - También puedes forzar la actualización de dependencias haciendo clic derecho en el proyecto y seleccionando `Clean and Build`.

5. **Ejecutar la Aplicación:**
 - Si todo está correctamente configurado, puedes ejecutar la aplicación desde NetBeans haciendo clic derecho en el proyecto y seleccionando `Run`.

Este proceso debería permitirte compilar y ejecutar tu proyecto en NetBeans sin mayores problemas.
</details>
<details>
<summary>🚀 InteliJ IDEA </summary>
Para abrir y ejecutar tu proyecto en IntelliJ IDEA, sigue estos pasos:

### 1. **Importar el Proyecto en IntelliJ IDEA:**
- Abre IntelliJ IDEA.
- Ve a `File > Open`.
- Navega hasta la carpeta donde se encuentra tu proyecto Maven y selecciona la carpeta que contiene el archivo `pom.xml`.
- Haz clic en `Open`. IntelliJ IDEA detectará automáticamente que es un proyecto Maven y comenzará a importar las dependencias.

### 2. **Configurar la JDK:**
- Asegúrate de que el proyecto esté utilizando JDK 21.
- Ve a `File > Project Structure` (`Ctrl + Alt + Shift + S` en Windows/Linux, `Command + ;` en macOS).
- En la sección `Project`, asegúrate de que `Project SDK` esté configurado en JDK 21.
- Si no tienes JDK 21 configurado, haz clic en `New > JDK` y selecciona la ruta donde tienes instalado JDK 21.

### 3. **Verificar la Configuración de Maven:**
- IntelliJ IDEA maneja Maven de manera integrada, pero es importante asegurarte de que esté configurado correctamente.
- Ve a `View > Tool Windows > Maven` para abrir la ventana de Maven.
- Aquí puedes ver los objetivos de Maven (`clean`, `install`, `spring-boot:run`, etc.).
- Puedes ejecutar `clean` y luego `install` o `spring-boot:run` directamente desde esta ventana para compilar y ejecutar tu proyecto.

### 4. **Configurar una Configuración de Ejecución (`Run Configuration`):**
- IntelliJ IDEA generalmente crea automáticamente una configuración de ejecución para Spring Boot.
- Para verificarla o crear una nueva:
 - Ve a `Run > Edit Configurations`.
 - Haz clic en el botón `+` y selecciona `Spring Boot`.
 - En la configuración, selecciona la clase principal de tu aplicación.
 - Asegúrate de que la configuración de `Before launch` esté establecida para ejecutar `Build` o `Maven Build`.

### 5. **Ejecutar la Aplicación:**
- Puedes ejecutar la aplicación directamente desde la configuración de ejecución creada.
- Haz clic en el botón de `Run` (ícono verde de "play") en la esquina superior derecha, o ve a `Run > Run 'tu_configuración'`.

### 6. **Verificar Dependencias y Compilación:**
- Si hay problemas con las dependencias o la compilación, IntelliJ IDEA te notificará.
- Puedes hacer clic en `Maven` en la barra lateral derecha para forzar la actualización de dependencias o limpiar y construir el proyecto nuevamente.

Siguiendo estos pasos, deberías poder abrir y ejecutar tu proyecto Spring Boot en IntelliJ IDEA sin problemas.
</details>



### Arrancar Base de Datos (Oracle)
Actualizado (19/08/2024). (Pendiente actualizacion de usuario y roles)
Limpiar la anterior base de datos y volver a crear.

<details>

<summary>Abrir SQL PLUS</summary>

```bash
Introduzca el nombre de usuario: /as sysdba
```
Debería conectarse a la base de datos
```bash
Conectado a:
Oracle Database 21c Express Edition Release 21.0.0.0.0 - Production
Version 21.3.0.0.0
```
```bash
SQL>
```
```sql
ALTER SESSION SET "_ORACLE_SCRIPT"= TRUE;
```
En la siguiente línea debe reemplazar "MI_USUARIO" y "password"
 por su nombre de usuario y contraseña respectivamente.
```sql
CREATE USER MI_USUARIO IDENTIFIED BY "password"
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";
```
```sql
ALTER USER MI_USUARIO QUOTA UNLIMITED ON USERS;
```
```sql
GRANT CREATE SESSION TO MI_USUARIO;
```

```sql
GRANT "RESOURCE" TO MI_USUARIO;
```
```sql
ALTER USER MI_USUARIO DEFAULT ROLE "RESOURCE";
```

Con todo lo anterior la pantalla completa deberia verse así:
```bash
SQL*Plus: Release 21.0.0.0.0 - Production on Vie Ago 9 15:26:58 2024
Version 21.3.0.0.0

Copyright (c) 1982, 2021, Oracle.  All rights reserved.

Introduzca el nombre de usuario: /as sysdba

Conectado a:
Oracle Database 21c Express Edition Release 21.0.0.0.0 - Production
Version 21.3.0.0.0

SQL> ALTER SESSION SET "_ORACLE_SCRIPT"= TRUE;

Sesi¾n modificada.

SQL> CREATE USER MI_USUARIO IDENTIFIED BY "123456"
  2  DEFAULT TABLESPACE "USERS"
  3  TEMPORARY TABLESPACE "TEMP";

Usuario creado.

SQL> ALTER USER MI_USUARIO QUOTA UNLIMITED ON USERS;

Usuario modificado.

SQL> GRANT CREATE SESSION TO MI_USUARIO;

Concesi¾n terminada correctamente.

SQL> GRANT "RESOURCE" TO MI_USUARIO;

Concesi¾n terminada correctamente.

SQL> ALTER USER MI_USUARIO DEFAULT ROLE "RESOURCE";

Usuario modificado.

SQL>
```

Si tiene un gestor de base de datos como Oracle SQL Developer, proceder a conectar usando el usuario y contraseña anteriormente usadas.

Fuente: [Curso Completo 2023: Base de Datos Oracle Database (Youtube)](https://youtu.be/zJqYB-xbLWU?si=mA4K5eamI0Ec5LNl)
</details>
El respaldo de la base de datos del proyecto se encuentra en BDD_Oracle.sql

### Configurar application.properties
- Dentro de `src/main/resources`, copiar el archivo `application-examples.properties` a `application-local.properties`.
```
.
└── src/
    └── main/
        └── resources/
            ├── application.properties
            ├── application-examples.properties
            └── application-local.properties
```
- Luego llenar los datos necesarios para la conexión a la base de datos en `application-local.properties`.
```properties
spring.application.name=contable

spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XE
spring.datasource.username=miusuario
spring.datasource.password=micontraseña
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 👩🏽‍💻👩🏻‍💻👨🏻‍💻👨🏻‍💻Contribuidores
<a href="https://github.com/leydivalladarez/Proyecto2Back/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=leydivalladarez/Proyecto2Back" alt="Contributos" />
</a>

---