CREATE DATABASE bsystem;

USE bsystem;

CREATE TABLE rates
( 
	rate_id INT PRIMARY KEY AUTO_INCREMENT,
	rate VARCHAR(30) NOT NULL
);

CREATE TABLE clients
( 
	client_id INT PRIMARY KEY AUTO_INCREMENT,
	client_login VARCHAR(20) NOT NULL,
	client_email VARCHAR(30) NOT NULL,
	client_surname VARCHAR(30) NOT NULL,
    client_balance DECIMAL,
    rate_id INT NOT NULL,
    FOREIGN KEY (rate_id) REFERENCES rates(rate_id) ON DELETE CASCADE
);