CREATE TABLE usuarios(
    id bigint auto_increment,
    nombre varchar(200) not null,
    email varchar(200) not null,
    contrasena varchar(200) not null,
    primary key(id)
);