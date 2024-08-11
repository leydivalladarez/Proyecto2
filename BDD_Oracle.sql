create table ACTIVO
(
    ID                          NUMBER generated as identity
        constraint PK_ACTIVO
            primary key,
    NOMBRE                      VARCHAR2(45) not null,
    PERIODOS_DEPRECIACION_TOTAL NUMBER       not null,
    VALOR_COMPRA                NUMBER(8, 2) not null,
    TIPO_ACTIVO_CODIGO          NUMBER       not null
)
/

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
    NUMERO NUMBER generated as identity
        constraint PK_DEPRECIACION
            primary key,
    FECHA  DATE not null
)
/

create table EMPLEADO
(
    CEDULA    VARCHAR2(10)  not null
        constraint PK_EMPLEADO
            primary key,
    NOMBRE    VARCHAR2(100) not null,
    APELLIDO  VARCHAR2(100) not null,
    DIRECCION VARCHAR2(200) default NULL
)
/

create table FACTURA
(
    ID            NUMBER generated as identity
        constraint PK_FACTURA
            primary key,
    FECHA         DATE   not null,
    CLIENTE_ID    NUMBER not null,
    CIUDAD_CODIGO NUMBER not null
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
    CODIGO      NUMBER generated as identity
        constraint PK_MOTIVO
            primary key,
    DESCRIPCION VARCHAR2(100) not null
)
/

create table NOMINA
(
    NUMERO          NUMBER generated as identity
        constraint PK_NOMINA
            primary key,
    EMPLEADO_CEDULA VARCHAR2(10) not null
        constraint FK_NOMINA_EMPLEADO
            references EMPLEADO,
    FECHA           DATE         not null
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
    RUC       VARCHAR2(20),
    NOMBRE    VARCHAR2(100),
    DIRECCION VARCHAR2(100)
)
/

create table CIUDAD
(
    CODIGO NUMBER generated as identity
        constraint CIUDAD_PK
            primary key,
    NOMBRE VARCHAR2(100)
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
    ID NUMBER generated as identity
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

