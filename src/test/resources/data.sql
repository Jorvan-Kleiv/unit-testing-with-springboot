CREATE TABLE persons
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(50),
    last_name    VARCHAR(50),
    city         VARCHAR(50),
    phone_number VARCHAR(50)
);
INSERT INTO persons (first_name, last_name, city, phone_number)
VALUES ('Jorvan', 'Talom', 'Douala', '123-546-8923'),
       ('Michael', 'Voss', 'New york', '153-546-4623'),
       ('Bruno', 'Mesmer', 'Paris', '123-546-8073'),
       ('Kyle', 'Loveless', 'Chicago', '123-546-8954');