create table pokemones(
    id bigint not null auto_increment,
    habilidades varchar(100),
    nombre varchar(50) unique,
    altura int,
    numero_pokedex int,
    peso int,

    primary key(id)
);