-- spmple SQL script --
CREATE DATABASE IF NOT EXISTS jalbwat-suda;
USE jalbwat-suda;

CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100)
);

GRANT ALL PRIVILEGES ON jalbwat-suda.* TO 'myuser'@'%' IDENTIFIED BY 'mypass123';
FLUSH PRIVILEGES;