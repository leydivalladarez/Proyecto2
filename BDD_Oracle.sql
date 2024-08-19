create table CUENTA
(
    CODIGO NUMBER generated as identity
        constraint PK_CUENTA
            primary key,
    NOMBRE VARCHAR2(45) not null
)
/

create table COMPROBANTE_CONTABLE
(
    NUMERO                  NUMBER generated as identity
        constraint PK_COMPROBANTE_CONTABLE
            primary key,
    FECHA                   DATE   not null,
    TIPO_COMPROBANTE_CODIGO NUMBER not null
        constraint FK_COMPROBANTE_TIPO
            references CUENTA
)
/

create table DEPRECIACION
(
    NUMERO        NUMBER generated as identity
        constraint PK_DEPRECIACION
            primary key,
    FECHA         DATE          not null,
    OBSERVACIONES VARCHAR2(250),
    RESPONSABLE   VARCHAR2(150) not null
)
/

create table EMPLEADO
(
    CEDULA        VARCHAR2(10),
    NOMBRE        VARCHAR2(100) not null,
    FECHA_INGRESO DATE          not null,
    SUELDO        NUMBER(8, 2)  not null,
    ID            NUMBER generated as identity
        constraint EMPLEADO_PK
            primary key
)
/

create table MODULOS
(
    ID     NUMBER generated as identity
        constraint PK_MODULOS
            primary key,
    NOMBRE VARCHAR2(45) not null
)
/

create table MOTIVO
(
    CODIGO NUMBER generated as identity
        constraint PK_MOTIVO
            primary key,
    NOMBRE VARCHAR2(100) not null,
    TIPO   VARCHAR2(20)
        check (tipo IN ('ingreso', 'egreso'))
)
/

create table NOMINA
(
    NUMERO      NUMBER generated as identity
        constraint PK_NOMINA
            primary key,
    FECHA       DATE not null,
    EMPLEADO_ID NUMBER
        constraint NOMINA_EMPLEADO_ID_FK
            references EMPLEADO
)
/

create table ROLES
(
    ID     NUMBER generated as identity
        constraint PK_ROLES
            primary key,
    NOMBRE VARCHAR2(45) not null
)
/

create table MODULO_ROL
(
    MODULO_ID NUMBER not null
        constraint FK_MODULO
            references MODULOS,
    ROL_ID    NUMBER not null
        constraint FK_ROL
            references ROLES,
    constraint PK_MODULO_ROL
        primary key (MODULO_ID, ROL_ID)
)
/

create table USUARIOS
(
    ID     NUMBER generated as identity
        constraint PK_USUARIOS
            primary key,
    NOMBRE VARCHAR2(100) not null,
    ROL_ID NUMBER        not null
        constraint FK_USUARIOS_ROL
            references ROLES
)
/

create table CLIENTE
(
    ID        NUMBER generated as identity
        constraint CLIENTE_PK
            primary key,
    RUC       VARCHAR2(255 char),
    NOMBRE    VARCHAR2(255 char),
    DIRECCION VARCHAR2(255 char)
)
/

create table FACTURA
(
    ID            NUMBER generated as identity
        constraint PK_FACTURA
            primary key,
    FECHA         DATE   not null,
    CLIENTE_ID    NUMBER not null
        constraint FACTURA_CLIENTE_ID_FK
            references CLIENTE,
    CIUDAD_CODIGO NUMBER not null
)
/

create table CIUDAD
(
    CODIGO NUMBER generated as identity
        constraint CIUDAD_PK
            primary key,
    NOMBRE VARCHAR2(45 char)
)
/

create table ARTICULO
(
    CODIGO NUMBER generated as identity
        constraint ARTICULO_PK
            primary key
                deferrable initially deferred,
    NOMBRE VARCHAR2(100) not null,
    PRECIO NUMBER(8, 2)  not null
)
/

create table FACTURA_DETALLE
(
    ID              NUMBER default "ISEQ$$_78868".nextval generated as identity
		constraint FACTURA_DETALLE_PK
			primary key,
    FACTURA_ID      NUMBER           not null
        constraint FACTURA_DETALLE_FACTURA_ID_FK
            references FACTURA,
    ARTICULO_CODIGO NUMBER           not null
        constraint FACTURA_DETALLE_ARTICULO_CODIGO_FK
            references ARTICULO,
    CANTIDAD        NUMBER default 1 not null,
    PRECIO          NUMBER(8, 2)     not null
)
/

create table NOMINA_DETALLE
(
    ID            NUMBER generated as identity
        constraint NOMINA_DETALLE_PK
            primary key,
    VALOR         NUMBER(8, 2) not null,
    NOMINA_NUMERO NUMBER       not null
        constraint NOMINA_DETALLE_NOMINA_NUMERO_FK
            references NOMINA,
    MOTIVO_CODIGO NUMBER       not null
        constraint NOMINA_DETALLE_MOTIVO_CODIGO_FK
            references MOTIVO
)
/

create table TIPO_ACTIVO
(
    CODIGO NUMBER generated as identity
        constraint TIPO_ACTIVO_PK
            primary key,
    NOMBRE VARCHAR2(100) not null
)
/

create table ACTIVO
(
    ID                          NUMBER generated as identity
        constraint PK_ACTIVO
            primary key,
    NOMBRE                      VARCHAR2(45) not null,
    PERIODOS_DEPRECIACION_TOTAL NUMBER       not null,
    VALOR_COMPRA                NUMBER(8, 2) not null,
    TIPO_ACTIVO_CODIGO          NUMBER       not null
        constraint ACTIVO_TIPO_ACTIVO_CODIGO_FK
            references TIPO_ACTIVO
)
/

create table DEPRECIACION_DETALLE
(
    ID                   NUMBER generated as identity
        constraint DEPRECIACION_DETALLE_PK
            primary key,
    ACTIVO_ID            NUMBER       not null
        constraint DEPRECIACION_DETALLE_ACTIVO_ID_FK
            references ACTIVO,
    PERIODO_DEPRECIACION NUMBER       not null,
    VALOR_DEPRECIACION   NUMBER(8, 2) not null,
    DEPRECIACION_NUMERO  NUMBER       not null
        constraint DEPRECIACION_DETALLE_DEPRECIACION_NUMERO_FK
            references DEPRECIACION
)
/

