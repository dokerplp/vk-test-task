create table users (
    id integer unique not null,
    login varchar(40) unique not null,
    password bytea not null,
    salt bytea not null,
    name varchar(40) not null,
    surname varchar(40) not null,
    birthday Date not null,
    CONSTRAINT CHK_Users CHECK (length(login) > 0),
    CONSTRAINT CHK_Name CHECK (length(name) > 0),
    CONSTRAINT CHK_Surname CHECK (length(surname) > 0),
    PRIMARY KEY (id)
)