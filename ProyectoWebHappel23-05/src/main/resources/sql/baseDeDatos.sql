

CREATE SEQUENCE public.comentarios_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.comentarios_id_seq
    OWNER TO postgres;

-- Table: public.comentarios

-- DROP TABLE public.comentarios;

CREATE TABLE public.comentarios
(
    id integer NOT NULL DEFAULT nextval('comentarios_id_seq'::regclass),
    id_producto_padre integer,
    id_comentario_padre integer,
    id_usuario_emisor integer,
    id_usuario_receptor integer,
    fecha text COLLATE pg_catalog."default",
    contenido text COLLATE pg_catalog."default",
    CONSTRAINT comentarios_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.comentarios
    OWNER to postgres;





CREATE SEQUENCE public.pcusuarios_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.pcusuarios_id_seq
    OWNER TO postgres;
    
-- Table: public.pcusuarios

-- DROP TABLE public.pcusuarios;

CREATE TABLE public.pcusuarios
(
    id integer NOT NULL DEFAULT nextval('pcusuarios_id_seq'::regclass),
    motherboard integer NOT NULL,
    microprocesador integer NOT NULL,
    placadevideo integer NOT NULL,
    ram1 integer NOT NULL,
    ram2 integer,
    discoduro integer NOT NULL,
    idpropietario integer NOT NULL,
    CONSTRAINT pcusuarios_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pcusuarios
    OWNER to postgres;
    


CREATE SEQUENCE public.usuarios_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.usuarios_id_seq
    OWNER TO postgres;
    
-- Table: public.usuarios

-- DROP TABLE public.usuarios;

CREATE TABLE public.usuarios
(
    id integer NOT NULL DEFAULT nextval('usuarios_id_seq'::regclass),
    nombre text COLLATE pg_catalog."default" NOT NULL,
    apellido text COLLATE pg_catalog."default" NOT NULL,
    nick text COLLATE pg_catalog."default",
    contrasenia text COLLATE pg_catalog."default" NOT NULL,
    mail text COLLATE pg_catalog."default" NOT NULL,
    imagen_de_perfil text COLLATE pg_catalog."default" DEFAULT 'https://cdn.filestackcontent.com/cQFdXPSuKwTYrDhpbbCQ'::text,
    idpc integer,
    administrador boolean NOT NULL DEFAULT false,
    codigo text COLLATE pg_catalog."default",
    CONSTRAINT usuarios_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuarios
    OWNER to postgres;
    
    


CREATE SEQUENCE public.productos_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.productos_id_seq
    OWNER TO postgres;

-- Table: public.productos

--DROP TABLE public.productos;

CREATE TABLE public.productos
(
    id integer NOT NULL DEFAULT nextval('productos_id_seq'::regclass),
    tipo text COLLATE pg_catalog."default" DEFAULT null,
    marca text COLLATE pg_catalog."default" DEFAULT null,
    modelo text COLLATE pg_catalog."default" DEFAULT null,
    socketcpu text COLLATE pg_catalog."default" DEFAULT null,
    tiporam text COLLATE pg_catalog."default" DEFAULT null,
    pci text COLLATE pg_catalog."default" DEFAULT null,
    sata text COLLATE pg_catalog."default" DEFAULT null,
    velocidad text COLLATE pg_catalog."default" DEFAULT null,
    tamanio text COLLATE pg_catalog."default" DEFAULT null,
    rendimiento text COLLATE pg_catalog."default" DEFAULT null,
    consumo text COLLATE pg_catalog."default" DEFAULT null,
    precio integer DEFAULT null,
    urlimagen text COLLATE pg_catalog."default" DEFAULT null,
    enoferta boolean DEFAULT false,
    CONSTRAINT producto_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.productos
    OWNER to postgres;

    

CREATE SEQUENCE public.juegos_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.juegos_id_seq
    OWNER TO postgres;
    
--Table: public.juegos

--DROP TABLE public.juegos;

CREATE TABLE public.juegos
(
    id integer NOT NULL DEFAULT nextval('juegos_id_seq'::regclass),
    nombre text COLLATE pg_catalog."default",
    desarrollador text COLLATE pg_catalog."default",
    fecha text COLLATE pg_catalog."default",
    plataforma text COLLATE pg_catalog."default",
    genero text COLLATE pg_catalog."default",
    rencpu1 integer,
    rencpu2 integer,
    rengpu1 integer,
    rengpu2 integer,
    vram1 integer,
    vram2 integer,
    ram1 integer,
    ram2 integer,
    precio integer,
    urlimagen text COLLATE pg_catalog."default",
    CONSTRAINT juegos_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.juegos
    OWNER to postgres;
    
  
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen, enoferta) VALUES( 'microprocesador', 'amd', 'ryzen 5 2400g', 'am4', null, null, null, '3.9', null, '9369', '65', '5300', '/imagenes/ryzen-5.jpg', true);
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen, enoferta) VALUES( 'placa de video', 'nvidia geforce', 'gtx 1050 ti', null, 'ddr5', 'pci express 3.0', null, null, '4gb', '5894', '75', '7400', '/imagenes/gtx-1050.jpg', true);
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'disco duro', 'wd', 'caviar blue', null, null, null, 'sata3', null, '1tb', null, '30', '1200', '/imagenes/wd-blue-1tb.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'motherboard', 'asrock', 'a320m-hdv', 'am4', 'ddr3', 'pci express 3.0', 'sata3', null, null, null, '50', '1700', '/imagenes/asrock-a320m-hdv.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'memoria', 'hyperx', 'fury', null, 'ddr3', null, null, '1866','4gb', null, '5', '1370', '/imagenes/hyperx-fury-blue.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen, enoferta) VALUES( 'placa de video', 'nvidia geforce', 'gtx 750 ti', null, 'ddr5', 'pci express 3.0', null, null,'2gb', '3726', '60', '3500', '/imagenes/gtx-750.jpg', true);
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'memoria', 'hyperx', 'fury', null, 'ddr3', null, null, '1866','8gb', null, '5', '2600', '/imagenes/hyperx-fury-blue-2.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'microprocesador', 'amd', 'a8-900', 'am4', null, null, null, '3.4', null, '4850', '65', '1850', '/imagenes/a8-9600.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen, enoferta) VALUES( 'microprocesador', 'amd', 'ryzen 3 2200', 'am4', null, null, null, '3.7', null, '7351', '65', '3200', '/imagenes/ryzen-3.jpg', true);

INSERT INTO juegos(nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen) VALUES('battletech', 'Harebrained Schemes', '24 ABR 2018', 'pc', 'accion, aventura, estrategia', '3721','4953', '3535','5373', '1', '2', '8', '16', '440', '/imagenes/battletech.jpg');
INSERT INTO juegos(nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen) VALUES('grand theft auto 5', 'rockstar games', '17 SEPT 2013', 'pc', 'accion, mundo abierto', '2958','6679', '720','5373', '1', '2', '4', '8', '1000', '/imagenes/gta5.jpg');
INSERT INTO juegos(nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen) VALUES('farcry 5', 'ubisoft', '27 MAR 2018', 'pc', 'accion, mundo abierto', '6383','9791', '5371','5373', '2', '4', '8', '16', '1500', '/imagenes/farcry5.jpg');


INSERT INTO usuarios(nombre, apellido, nick, contrasenia, mail, imagen_de_perfil, idpc, administrador) VALUES ('javier', 'happel', 'javi', 'admin', 'happeljavierleonardo@gmail.com', null , '1', true);
INSERT INTO pcusuarios(motherboard, microprocesador, placadevideo, ram1, ram2, discoduro, idpropietario) VALUES('4', '1', '2', '5', '5', '3', '1');



INSERT INTO comentarios(id_usuario_emisor, id_producto_padre, id_comentario_padre, id_usuario_receptor, fecha, contenido) VALUES('1', '2', '0', '0', '2018-05-01', 'comentario1.0');
INSERT INTO comentarios(id_usuario_emisor, id_producto_padre, id_comentario_padre, id_usuario_receptor, fecha, contenido) VALUES('1', '2', '0', '0', '2018-05-01', 'comentario2.0');

INSERT INTO comentarios(id_usuario_emisor, id_producto_padre, id_comentario_padre, id_usuario_receptor, fecha, contenido) VALUES('1', '2', '1', '0', '2018-05-01', 'comentario1.1');
INSERT INTO comentarios(id_usuario_emisor, id_producto_padre, id_comentario_padre, id_usuario_receptor, fecha, contenido) VALUES('1', '2', '1', '0', '2018-05-01', 'comentario1.2');
INSERT INTO comentarios(id_usuario_emisor, id_producto_padre, id_comentario_padre, id_usuario_receptor, fecha, contenido) VALUES('1', '2', '2', '0', '2018-05-01', 'comentario2.1');

 id_producto_padre integer,
    id_comentario_padre integer,
    id_usuario_emisor integer,
    id_usuario_receptor integer,

<div class="col-12" th:each="comentario:${listadoComentarios}">
				<h1>
				<label class="text-uppercase" th:text="${comentario.contenido}"></label>
				</h1>
					
				<h3 >
				<label class="text-uppercase" th:text="${comentario.subcomentario}" ></label>
				</h3>	
</div>

<a th:href="'/eliminar/' + ${producto.id}">X</a>

cursor :pointer
meteor.js
viu.js
react 
angular


