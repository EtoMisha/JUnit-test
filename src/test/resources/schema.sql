drop table if exists products;

create table if not exists products (
    id          INT PRIMARY KEY IDENTITY ,
    name        VARCHAR(50) NOT NULL ,
    price       INT NOT NULL
);