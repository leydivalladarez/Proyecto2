# ✨Proyecto de Aplicaciones Distribuidas
## 🛠️Herramientas y tecnologías
- Oracle Database
- Netbeans 12 o superior
- IntelliJ IDEA (Opcional, en reemplazo de Netbeans)
- Java JDK 17
- Tomcat
- Spring Boot (v3.3.2)
- Maven

### API Rest Java
- Todos los datos a enviar y recibir deben estar en formato JSON.

### Frontend: React

## 🏁 Iniciar
Antes de iniciar se requiere tener instalado JDK 17 y Apache Tomcat/10.1.26.

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
a
</details>
<details>
<summary>🚀 InteliJ IDEA </summary>
b
</details>



### Arrancar Base de Datos (Oracle)
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

## 👩🏽‍💻👩🏻‍💻👨🏻‍💻👨🏻‍💻Contribuidores
<a href="https://github.com/leydivalladarez/Proyecto2Back/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=leydivalladarez/Proyecto2Back" alt="Contributos" />
</a>

---