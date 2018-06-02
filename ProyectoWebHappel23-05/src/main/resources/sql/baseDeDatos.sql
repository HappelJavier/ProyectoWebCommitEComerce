

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
    
  
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'microprocesador', 'amd', 'ryzen 5 2400g', 'am4', null, null, null, '3.9', null, '14838', '95', '4400', '/imagenes/ryzen-5.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'placa de video', 'nvidia geforce', 'gtx 1050 ti', null, 'ddr5', 'pci express 3.0', null, null, '4gb', '5894', '75', '5700', '/imagenes/gtx-1050.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'disco duro', 'wd', 'caviar blue', null, null, null, 'sata3', null, '1tb', null, '30', '1000', '/imagenes/wd-blue-1tb.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'motherboard', 'asrock', 'a320m-hdv', 'am4', 'ddr3', 'pci express 3.0', 'sata3', null, null, null, '50', '1400', '/imagenes/asrock-a320m-hdv.jpg');
INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'memoria', 'hyperx', 'fury', null, 'ddr3', null, null, '1866','4gb', null, '5', '1125', '/imagenes/hyperx-fury-blue.jpg');
INSERT INTO juegos(nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen) VALUES('battletech', 'Harebrained Schemes', '24 ABR 2018', 'pc', 'accion, aventura, estrategia', '3721','4953', '3535','5373', '1', '2', '8', '16', '440', '/imagenes/battletech.jpg');
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

crea-tu-pccrea-tu-pccrea-tu-pccrea-tu-pccrea-tu-pccrea-tu-pc  MOTHER MEMORIA RAM DETALLES CSS