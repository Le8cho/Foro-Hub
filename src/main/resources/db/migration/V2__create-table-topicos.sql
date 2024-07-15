CREATE TABLE topicos(
    id bigint auto_increment,
    titulo varchar(200) not null,
    mensaje varchar(200) not null,
    fecha_creacion datetime not null,
    status varchar(200) not null,
    autor_id bigint not null,
    nombre_curso varchar(200) not null,
    primary key(id),
    constraint fk_usuarios_topicos foreign key(autor_id) references usuarios(id)
);