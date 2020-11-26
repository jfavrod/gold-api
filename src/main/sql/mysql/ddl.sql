CREATE DATABASE gold;
USE gold;

CREATE TABLE prices (
    id INT PRIMARY KEY AUTO_INCREMENT,
    price NUMERIC,
    sample_date TIMESTAMP,
    source VARCHAR(255)
);