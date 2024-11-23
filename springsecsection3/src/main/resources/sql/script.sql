create database anshbank;
use anshbank;

CREATE TABLE users (
id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(45) NOT NULL,
password VARCHAR(45) NOT NULL,
enabled INT NOT NULL,
PRIMARY KEY (id)
);

create table authorities (
id int NOT NULL AUTO_INCREMENT,
username varchar(45) not null,
authority varchar(45) not null,
PRIMARY KEY(id));

CREATE TABLE customer (
  id int NOT NULL AUTO_INCREMENT,
  email varchar(45) NOT NULL,
  pwd varchar(200) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (id)
);

INSERT  INTO customer (email, pwd, role) VALUES ('johndoe@example.com', '54321', 'admin');
INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES ('admin@example.com', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'admin');

INSERT IGNORE INTO users VALUES(NULL, 'happy', '12345', 1);
INSERT IGNORE INTO authorities VALUES(NULL, 'happy', 'write');