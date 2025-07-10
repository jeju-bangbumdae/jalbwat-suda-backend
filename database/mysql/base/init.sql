-- spmple SQL script --
CREATE DATABASE IF NOT EXISTS jalbwat-suda;
USE jalbwat-suda;

-- USER TABLE
CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      actor VARCHAR(255) NOT NULL,
                      name VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      deleted_at TIMESTAMP DEFAULT NULL
);

-- QUESTION TABLE
CREATE TABLE question (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          question VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          deleted_at TIMESTAMP DEFAULT NULL
);

-- STORE TABLE
CREATE TABLE store (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       name VARCHAR(100) NOT NULL,
                       business_num VARCHAR(100) NOT NULL,
                       address VARCHAR(100) NOT NULL,
                       category VARCHAR(100) NOT NULL,
                       latitude DOUBLE NOT NULL,
                       longitude DOUBLE NOT NULL,
                       operation_time VARCHAR(255),
                       phone VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP DEFAULT NULL,
                       CONSTRAINT fk_store_user FOREIGN KEY (user_id) REFERENCES user(id)
);

-- GUEST_BOOK TABLE
CREATE TABLE guest_book (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            content VARCHAR(255) NOT NULL,
                            question_id INT,
                            answer VARCHAR(255),
                            user_id INT,
                            store_id INT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            deleted_at TIMESTAMP DEFAULT NULL,
                            CONSTRAINT fk_guestbook_question FOREIGN KEY (question_id) REFERENCES question(id),
                            CONSTRAINT fk_guestbook_user FOREIGN KEY (user_id) REFERENCES user(id),
                            CONSTRAINT fk_guestbook_store FOREIGN KEY (store_id) REFERENCES store(id)
);


GRANT ALL PRIVILEGES ON jalbwat-suda.* TO 'myuser'@'%' IDENTIFIED BY 'mypass123';
FLUSH PRIVILEGES;