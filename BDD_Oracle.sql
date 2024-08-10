--------------------------------------------------------
-- Archivo creado  - lunes-julio-29-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ACTIVO
--------------------------------------------------------

  CREATE TABLE "ACTIVO" 
   (	"ID" NUMBER, 
	"NOMBRE" VARCHAR2(45 BYTE), 
	"PERIODOS_DEPRECIACION_TOTAL" NUMBER, 
	"VALOR_COMPRA" NUMBER(8,2), 
	"TIPO_ACTIVO_CODIGO" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ARTICULO
--------------------------------------------------------

  CREATE TABLE "ARTICULO" 
   (	"CODIGO" NUMBER, 
	"NOMBRE" VARCHAR2(45 BYTE), 
	"PRECIO" NUMBER(8,2)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CIUDAD
--------------------------------------------------------

  CREATE TABLE "CIUDAD" 
   (	"CODIGO" NUMBER, 
	"NOMBRE" VARCHAR2(45 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CLIENTE
--------------------------------------------------------

  CREATE TABLE "CLIENTE" 
   (	"ID" NUMBER, 
	"RUC" VARCHAR2(13 BYTE), 
	"NOMBRE" VARCHAR2(100 BYTE), 
	"DIRECCION" VARCHAR2(200 BYTE) DEFAULT NULL
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table COMPROBANTE_CONTABLE
--------------------------------------------------------

  CREATE TABLE "COMPROBANTE_CONTABLE" 
   (	"NUMERO" NUMBER, 
	"FECHA" DATE, 
	"TIPO_COMPROBANTE_CODIGO" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CUENTA
--------------------------------------------------------

  CREATE TABLE "CUENTA" 
   (	"CODIGO" NUMBER, 
	"NOMBRE" VARCHAR2(45 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table DEPRECIACION
--------------------------------------------------------

  CREATE TABLE "DEPRECIACION" 
   (	"NUMERO" NUMBER, 
	"FECHA" DATE
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EMPLEADO
--------------------------------------------------------

  CREATE TABLE "EMPLEADO" 
   (	"CEDULA" VARCHAR2(10 BYTE), 
	"NOMBRE" VARCHAR2(100 BYTE), 
	"APELLIDO" VARCHAR2(100 BYTE), 
	"DIRECCION" VARCHAR2(200 BYTE) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FACTURA
--------------------------------------------------------

  CREATE TABLE "FACTURA" 
   (	"ID" NUMBER, 
	"FECHA" DATE, 
	"CLIENTE_ID" NUMBER, 
	"CIUDAD_CODIGO" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MODULO_ROL
--------------------------------------------------------

  CREATE TABLE "MODULO_ROL" 
   (	"MODULO_ID" NUMBER, 
	"ROL_ID" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MODULOS
--------------------------------------------------------

  CREATE TABLE "MODULOS" 
   (	"ID" NUMBER, 
	"NOMBRE" VARCHAR2(45 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MOTIVO
--------------------------------------------------------

  CREATE TABLE "MOTIVO" 
   (	"CODIGO" NUMBER, 
	"DESCRIPCION" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table NOMINA
--------------------------------------------------------

  CREATE TABLE "NOMINA" 
   (	"NUMERO" NUMBER, 
	"EMPLEADO_CEDULA" VARCHAR2(10 BYTE), 
	"FECHA" DATE
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ROLES
--------------------------------------------------------

  CREATE TABLE "ROLES" 
   (	"ID" NUMBER, 
	"NOMBRE" VARCHAR2(45 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USUARIOS
--------------------------------------------------------

  CREATE TABLE "USUARIOS" 
   (	"ID" NUMBER, 
	"NOMBRE" VARCHAR2(100 BYTE), 
	"ROL_ID" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into ACTIVO
SET DEFINE OFF;
REM INSERTING into ARTICULO
SET DEFINE OFF;
REM INSERTING into CIUDAD
SET DEFINE OFF;
Insert into CIUDAD (CODIGO,NOMBRE) values (1,'Quito');
Insert into CIUDAD (CODIGO,NOMBRE) values (2,'Guayaquil');
Insert into CIUDAD (CODIGO,NOMBRE) values (4,'Ambatoooo');
Insert into CIUDAD (CODIGO,NOMBRE) values (5,'Loja');
Insert into CIUDAD (CODIGO,NOMBRE) values (6,'Guayas');
REM INSERTING into CLIENTE
SET DEFINE OFF;
Insert into CLIENTE (ID,RUC,NOMBRE,DIRECCION) values (1,'1723456784001','Peter Parker','Av. Amazonas');
Insert into CLIENTE (ID,RUC,NOMBRE,DIRECCION) values (2,'1548576543002','Demi Lovato','Av. Colon y America');
Insert into CLIENTE (ID,RUC,NOMBRE,DIRECCION) values (3,'1745676543001','Juan Perez','Cotocollao');
Insert into CLIENTE (ID,RUC,NOMBRE,DIRECCION) values (4,'0583948576001','Viviana Perez','Rumu�ahui');
Insert into CLIENTE (ID,RUC,NOMBRE,DIRECCION) values (5,'1719803650001','Leydi Valladarez','La Roldos');
Insert into CLIENTE (ID,RUC,NOMBRE,DIRECCION) values (6,'1217601812001','Grupo5','Amazona y rio coca');
REM INSERTING into COMPROBANTE_CONTABLE
SET DEFINE OFF;
REM INSERTING into CUENTA
SET DEFINE OFF;
REM INSERTING into DEPRECIACION
SET DEFINE OFF;
REM INSERTING into EMPLEADO
SET DEFINE OFF;
REM INSERTING into FACTURA
SET DEFINE OFF;
REM INSERTING into MODULO_ROL
SET DEFINE OFF;
REM INSERTING into MODULOS
SET DEFINE OFF;
REM INSERTING into MOTIVO
SET DEFINE OFF;
REM INSERTING into NOMINA
SET DEFINE OFF;
REM INSERTING into ROLES
SET DEFINE OFF;
REM INSERTING into USUARIOS
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index PK_ACTIVO
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_ACTIVO" ON "ACTIVO" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_ARTICULO
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_ARTICULO" ON "ARTICULO" ("CODIGO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_CIUDAD
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_CIUDAD" ON "CIUDAD" ("CODIGO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_CLIENTE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_CLIENTE" ON "CLIENTE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_COMPROBANTE_CONTABLE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_COMPROBANTE_CONTABLE" ON "COMPROBANTE_CONTABLE" ("NUMERO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_CUENTA
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_CUENTA" ON "CUENTA" ("CODIGO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_DEPRECIACION
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_DEPRECIACION" ON "DEPRECIACION" ("NUMERO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_EMPLEADO
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_EMPLEADO" ON "EMPLEADO" ("CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_FACTURA
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_FACTURA" ON "FACTURA" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_MODULO_ROL
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_MODULO_ROL" ON "MODULO_ROL" ("MODULO_ID", "ROL_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_MODULOS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_MODULOS" ON "MODULOS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_MOTIVO
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_MOTIVO" ON "MOTIVO" ("CODIGO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_NOMINA
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_NOMINA" ON "NOMINA" ("NUMERO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_ROLES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_ROLES" ON "ROLES" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_USUARIOS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_USUARIOS" ON "USUARIOS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table ACTIVO
--------------------------------------------------------

  ALTER TABLE "ACTIVO" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "ACTIVO" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "ACTIVO" MODIFY ("PERIODOS_DEPRECIACION_TOTAL" NOT NULL ENABLE);
  ALTER TABLE "ACTIVO" MODIFY ("VALOR_COMPRA" NOT NULL ENABLE);
  ALTER TABLE "ACTIVO" MODIFY ("TIPO_ACTIVO_CODIGO" NOT NULL ENABLE);
  ALTER TABLE "ACTIVO" ADD CONSTRAINT "PK_ACTIVO" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ARTICULO
--------------------------------------------------------

  ALTER TABLE "ARTICULO" MODIFY ("CODIGO" NOT NULL ENABLE);
  ALTER TABLE "ARTICULO" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "ARTICULO" MODIFY ("PRECIO" NOT NULL ENABLE);
  ALTER TABLE "ARTICULO" ADD CONSTRAINT "PK_ARTICULO" PRIMARY KEY ("CODIGO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CIUDAD
--------------------------------------------------------

  ALTER TABLE "CIUDAD" MODIFY ("CODIGO" NOT NULL ENABLE);
  ALTER TABLE "CIUDAD" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "CIUDAD" ADD CONSTRAINT "PK_CIUDAD" PRIMARY KEY ("CODIGO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CLIENTE
--------------------------------------------------------

  ALTER TABLE "CLIENTE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "CLIENTE" MODIFY ("RUC" NOT NULL ENABLE);
  ALTER TABLE "CLIENTE" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "CLIENTE" ADD CONSTRAINT "PK_CLIENTE" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table COMPROBANTE_CONTABLE
--------------------------------------------------------

  ALTER TABLE "COMPROBANTE_CONTABLE" MODIFY ("NUMERO" NOT NULL ENABLE);
  ALTER TABLE "COMPROBANTE_CONTABLE" MODIFY ("FECHA" NOT NULL ENABLE);
  ALTER TABLE "COMPROBANTE_CONTABLE" MODIFY ("TIPO_COMPROBANTE_CODIGO" NOT NULL ENABLE);
  ALTER TABLE "COMPROBANTE_CONTABLE" ADD CONSTRAINT "PK_COMPROBANTE_CONTABLE" PRIMARY KEY ("NUMERO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CUENTA
--------------------------------------------------------

  ALTER TABLE "CUENTA" MODIFY ("CODIGO" NOT NULL ENABLE);
  ALTER TABLE "CUENTA" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "CUENTA" ADD CONSTRAINT "PK_CUENTA" PRIMARY KEY ("CODIGO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table DEPRECIACION
--------------------------------------------------------

  ALTER TABLE "DEPRECIACION" MODIFY ("NUMERO" NOT NULL ENABLE);
  ALTER TABLE "DEPRECIACION" MODIFY ("FECHA" NOT NULL ENABLE);
  ALTER TABLE "DEPRECIACION" ADD CONSTRAINT "PK_DEPRECIACION" PRIMARY KEY ("NUMERO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table EMPLEADO
--------------------------------------------------------

  ALTER TABLE "EMPLEADO" MODIFY ("CEDULA" NOT NULL ENABLE);
  ALTER TABLE "EMPLEADO" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "EMPLEADO" MODIFY ("APELLIDO" NOT NULL ENABLE);
  ALTER TABLE "EMPLEADO" ADD CONSTRAINT "PK_EMPLEADO" PRIMARY KEY ("CEDULA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table FACTURA
--------------------------------------------------------

  ALTER TABLE "FACTURA" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "FACTURA" MODIFY ("FECHA" NOT NULL ENABLE);
  ALTER TABLE "FACTURA" MODIFY ("CLIENTE_ID" NOT NULL ENABLE);
  ALTER TABLE "FACTURA" MODIFY ("CIUDAD_CODIGO" NOT NULL ENABLE);
  ALTER TABLE "FACTURA" ADD CONSTRAINT "PK_FACTURA" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MODULO_ROL
--------------------------------------------------------

  ALTER TABLE "MODULO_ROL" MODIFY ("MODULO_ID" NOT NULL ENABLE);
  ALTER TABLE "MODULO_ROL" MODIFY ("ROL_ID" NOT NULL ENABLE);
  ALTER TABLE "MODULO_ROL" ADD CONSTRAINT "PK_MODULO_ROL" PRIMARY KEY ("MODULO_ID", "ROL_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MODULOS
--------------------------------------------------------

  ALTER TABLE "MODULOS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "MODULOS" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "MODULOS" ADD CONSTRAINT "PK_MODULOS" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MOTIVO
--------------------------------------------------------

  ALTER TABLE "MOTIVO" MODIFY ("CODIGO" NOT NULL ENABLE);
  ALTER TABLE "MOTIVO" MODIFY ("DESCRIPCION" NOT NULL ENABLE);
  ALTER TABLE "MOTIVO" ADD CONSTRAINT "PK_MOTIVO" PRIMARY KEY ("CODIGO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table NOMINA
--------------------------------------------------------

  ALTER TABLE "NOMINA" MODIFY ("NUMERO" NOT NULL ENABLE);
  ALTER TABLE "NOMINA" MODIFY ("EMPLEADO_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "NOMINA" MODIFY ("FECHA" NOT NULL ENABLE);
  ALTER TABLE "NOMINA" ADD CONSTRAINT "PK_NOMINA" PRIMARY KEY ("NUMERO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ROLES
--------------------------------------------------------

  ALTER TABLE "ROLES" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "ROLES" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "ROLES" ADD CONSTRAINT "PK_ROLES" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USUARIOS
--------------------------------------------------------

  ALTER TABLE "USUARIOS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "USUARIOS" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "USUARIOS" MODIFY ("ROL_ID" NOT NULL ENABLE);
  ALTER TABLE "USUARIOS" ADD CONSTRAINT "PK_USUARIOS" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table COMPROBANTE_CONTABLE
--------------------------------------------------------

  ALTER TABLE "COMPROBANTE_CONTABLE" ADD CONSTRAINT "FK_COMPROBANTE_TIPO" FOREIGN KEY ("TIPO_COMPROBANTE_CODIGO")
	  REFERENCES "CUENTA" ("CODIGO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FACTURA
--------------------------------------------------------

  ALTER TABLE "FACTURA" ADD CONSTRAINT "FK_FACTURA_CIUDAD" FOREIGN KEY ("CIUDAD_CODIGO")
	  REFERENCES "CIUDAD" ("CODIGO") ENABLE;
  ALTER TABLE "FACTURA" ADD CONSTRAINT "FK_FACTURA_CLIENTE" FOREIGN KEY ("CLIENTE_ID")
	  REFERENCES "CLIENTE" ("ID") DISABLE;
--------------------------------------------------------
--  Ref Constraints for Table MODULO_ROL
--------------------------------------------------------

  ALTER TABLE "MODULO_ROL" ADD CONSTRAINT "FK_MODULO" FOREIGN KEY ("MODULO_ID")
	  REFERENCES "MODULOS" ("ID") ENABLE;
  ALTER TABLE "MODULO_ROL" ADD CONSTRAINT "FK_ROL" FOREIGN KEY ("ROL_ID")
	  REFERENCES "ROLES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table NOMINA
--------------------------------------------------------

  ALTER TABLE "NOMINA" ADD CONSTRAINT "FK_NOMINA_EMPLEADO" FOREIGN KEY ("EMPLEADO_CEDULA")
	  REFERENCES "EMPLEADO" ("CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USUARIOS
--------------------------------------------------------

  ALTER TABLE "USUARIOS" ADD CONSTRAINT "FK_USUARIOS_ROL" FOREIGN KEY ("ROL_ID")
	  REFERENCES "ROLES" ("ID") ENABLE;
