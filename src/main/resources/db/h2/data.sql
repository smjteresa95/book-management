INSERT INTO books (majorCategoryId, subCategoryId, title, isbn, author, publisher, publicationDate, availableCopies, createdAt, updatedAt)
VALUES (9, 1, '역행자', '9788901260877', '자청', '웅진지식하우스', '2023-05-29', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO books (majorCategoryId, subCategoryId, title, isbn, author, translator, publisher, publicationDate, availableCopies, createdAt, updatedAt)
VALUES (1, 2, '오만과 편견', '9791160871210', '제인 오스틴', '송제훈', '연암서가', '2024-01-25', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO major_category (category) VALUES
                                          ('소설'),
                                          ('시/에세이'),
                                          ('인문'),
                                          ('가정/육아'),
                                          ('요리'),
                                          ('건강'),
                                          ('취미/실용/스포츠'),
                                          ('경제/경영'),
                                          ('자기계발'),
                                          ('정치/사회');

INSERT INTO sub_category (majorCategoryId, category) VALUES
                                                         (1, '한국소설'),
                                                         (1, '영미소설'),
                                                         (1, '일본소설');
INSERT INTO sub_category (majorCategoryId, category) VALUES
                                                         (9, '성공/처세'),
                                                         (9, '자기능력계발'),
                                                         (9, '비즈니스능력계발');
INSERT INTO sub_category (majorCategoryId, category) VALUES
                                                         (10, '정치/외교'),
                                                         (10, '행정/정책'),
                                                         (10, '국방/군사'),
                                                         (10, '법학'),
                                                         (10, '사회학');

-- 도서대출이 가능한 사용자
INSERT INTO users (userName, email, password, firstName, lastName, phoneNum, createdAt, updatedAt)
VALUES ('user1', 'user1@example.com', '1111', '민지', '송', '01011111111', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role_name)
VALUES ((SELECT id FROM users WHERE userName = 'user1'), 'ROLE_USER');

-- 연체로 도서대출이 불가한 사용자
INSERT INTO users (userName, email, password, firstName, lastName, phoneNum, createdAt, updatedAt)
VALUES ('user2', 'user2@example.com', '2222', '은주', '허', '01022222222', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role_name)
VALUES ((SELECT id FROM users WHERE userName = 'user2'), 'ROLE_USER');

-- 대여 가능 한 도서권수를 채운 사용자
INSERT INTO users (userName, email, password, firstName, lastName, phoneNum, createdAt, updatedAt)
VALUES ('user3', 'user3@example.com', '3333', '지나', '배', '01033333333', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role_name)
VALUES ((SELECT id FROM users WHERE userName = 'user3'), 'ROLE_USER');


-- 관리자, 사용자
INSERT INTO users (userName, email, password, firstName, lastName, phoneNum, createdAt, updatedAt)
VALUES ('adminname', 'admin@example.com', '1234', '민지', '송', '01009876543', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role_name)
VALUES ((SELECT id FROM users WHERE userName = 'adminname'), 'ROLE_USER');

INSERT INTO user_roles (user_id, role_name)
VALUES ((SELECT id FROM users WHERE userName = 'adminname'), 'ROLE_ADMIN');


-- 정상반납된 도서
INSERT INTO loan_record (bookId, userId, loanDate, dueDate, returnDate)
VALUES (2, 1, '2024-01-02 00:00:00', '2024-01-16 00:00:00', '2024-01-10 00:00:00');

-- 대출 중인 도서
INSERT INTO loan_record (bookId, userId, loanDate, dueDate)
VALUES (1, 1, '2024-01-10 00:00:00', '2024-01-24 00:00:00');

-- 연체된 도서가 있음
INSERT INTO loan_record (bookId, userId, loanDate, dueDate)
VALUES (1, 2, '2023-12-22 00:00:00', '2024-01-04 00:00:00');

-- 인당 대출 가능 한 도서권수가 넘어감
INSERT INTO loan_record (bookId, userId, loanDate, dueDate)
VALUES (1, 3, '2024-01-11 00:00:00', '2024-01-25 00:00:00');

INSERT INTO loan_record (bookId, userId, loanDate, dueDate)
VALUES (2, 3, '2024-01-11 00:00:00', '2024-01-25 00:00:00');

INSERT INTO loan_record (bookId, userId, loanDate, dueDate)
VALUES (3, 3, '2024-01-11 00:00:00', '2024-01-25 00:00:00');

INSERT INTO loan_record (bookId, userId, loanDate, dueDate)
VALUES (4, 3, '2024-01-11 00:00:00', '2024-01-25 00:00:00');

INSERT INTO loan_record (bookId, userId, loanDate, dueDate)
VALUES (5, 3, '2024-01-11 00:00:00', '2024-01-25 00:00:00');






