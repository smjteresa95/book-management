DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS loan_record;
DROP TABLE IF EXISTS major_category;
DROP TABLE IF EXISTS sub_category;
DROP TABLE IF EXISTS users;


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
                       availableCopies INT,
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE loan_record (
                             loanId BIGINT AUTO_INCREMENT PRIMARY KEY,
                             bookId BIGINT,
                             userId BIGINT,
                             loanDate TIMESTAMP,
                             dueDate TIMESTAMP,
                             returnDate TIMESTAMP
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

CREATE TABLE roles (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(20)
);

CREATE TABLE user_roles (
                            user_id BIGINT,
                            role_id INT,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);



