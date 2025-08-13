create table usuario(
                       id bigint not null auto_increment,
                       nombre varchar(100) not null,
                       correo varchar(150) not null unique,
                       contrasena varchar(255) not null,
                       primary key (id)
);

CREATE TABLE curso (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       nombre VARCHAR(100) NOT NULL,
                       categoria VARCHAR(50) NOT NULL,
                       PRIMARY KEY (id)
);

create table topico(
                       id bigint not null auto_increment,
                       titulo varchar(200) not null,
                       mensaje varchar(500) not null,
                       fechaDeCreacion datetime not null,
                       status tinyint not null,
                       autor_id bigint not null,
                       curso_id bigint not null,
                       primary key (id),
                       foreign key (autor_id) references usuario(id),
                       foreign key (curso_id) references curso(id)
);

CREATE TABLE respuesta (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           mensaje VARCHAR(500) NOT NULL,
                           topico_id BIGINT NOT NULL,
                           fecha_creacion DATETIME NOT NULL,
                           autor_id BIGINT NOT NULL,
                           solucion TINYINT NOT NULL DEFAULT 0,
                           PRIMARY KEY (id),
                           FOREIGN KEY (topico_id) REFERENCES topico(id),
                           FOREIGN KEY (autor_id) REFERENCES usuario(id)
);