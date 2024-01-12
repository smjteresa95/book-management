DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS loan_record;
DROP TABLE IF EXISTS major_category;
DROP TABLE IF EXISTS sub_category;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;



CREATE TABLE books (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       majorCategoryId INT,
                       subCategoryId INT,
                       title VARCHAR(255) NOT NULL,
                       isbn VARCHAR(255) NOT NULL,
                       author VARCHAR(255),
                       translator VARCHAR(255),
                       publisher VARCHAR(255),
                       publicationDate DATE,
                       status TINYINT DEFAULT 2,
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       version BIGINT DEFAULT 0
);

CREATE TABLE loan_record (
                             loanId BIGINT AUTO_INCREMENT PRIMARY KEY,
                             bookId BIGINT,
                             userId BIGINT,
                             loanDate TIMESTAMP NOT NULL,
                             dueDate TIMESTAMP NOT NULL,
                             returnDate TIMESTAMP,
                             version BIGINT DEFAULT 0
);

CREATE TABLE major_category (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                category VARCHAR(255)
);

CREATE TABLE sub_category (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                majorCategoryId INT,
                                category VARCHAR(255)
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       userName VARCHAR(255) UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255),
                       firstName VARCHAR(255),
                       lastName VARCHAR(255),
                       phoneNum VARCHAR(255),
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE user_roles (
                            user_id BIGINT,
                            role_name VARCHAR(50),
                            PRIMARY KEY (user_id, role_name),
                            FOREIGN KEY (user_id) REFERENCES users(id)
);




