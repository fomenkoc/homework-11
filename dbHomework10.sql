DROP DATABASE IF EXISTS store;
CREATE DATABASE store CHAR SET UTF8;
USE store;

CREATE TABLE prod (
	prod_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    prod_name VARCHAR(255) NOT NULL UNIQUE,
    description varchar(255),
    price DECIMAL(9,2)
);

INSERT INTO prod (prod_name, description, price)
VALUES
	('Тестове видання 1', 'Опис', 50.5),
    ('Тестове видання 2', 'Опис', 99.99),
    ('Тестове видання 3', 'Опис', 99.99),
    ('Тестове видання 4', 'Опис', 99.99),
    ('Тестове видання 5', 'Опис', 99.99),
    ('Тестове видання 6', 'Опис', 99.99),
    ('Тестове видання 7', 'Опис', 99.99),
    ('Тестове видання 8', 'Опис', 99.99),
    ('Тестове видання 9', 'Опис', 99.99),
    ('Тестове видання 10', 'Опис', 99.99),
    ('Тестове видання 11', 'Опис', 99.99),
    ('Тестове видання 12', 'Опис', 99.99),
    ('Тестове видання 13', 'Опис', 99.99),
    ('Тестове видання 14', 'Опис', 99.99),
    ('Тестове видання 15', 'Опис', 99.99),
    ('Тестове видання 16', 'Опис', 99.99),
    ('Тестове видання 17', 'Опис', 99.99),
    ('Тестове видання 18', 'Опис', 99.99),
    ('Тестове видання 19', 'Опис', 99.99),
    ('Тестове видання 20', 'Опис', 99.99),
    ('Тестове видання 21', 'Опис', 99.99),
    ('Тестове видання 22', 'Опис', 99.99),
    ('Тестове видання 23', 'Опис', 99.99),
    ('Тестове видання 24', 'Опис', 99.99),
    ('Тестове видання 25', 'Опис', 99.99),
    ('Тестове видання 26', 'Опис', 99.99),
    ('Тестове видання 27', 'Опис', 99.99),
    ('Тестове видання 28', 'Опис', 99.99),
    ('Тестове видання 29', 'Опис', 99.99),
    ('Тестове видання 30', 'Опис', 150.0);

CREATE TABLE role(
	role_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL UNIQUE,
    is_staff BIT NOT NULL DEFAULT (FALSE)
);
INSERT INTO role (role_name, is_staff)
VALUES
	('Administrator', true),
    ('Coustomer', false);

CREATE TABLE user(
	user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role_id INT NOT NULL DEFAULT (2),
    password VARCHAR(255) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

INSERT INTO user(email, first_name, last_name, role_id, password)
VALUES
	('admin@gmail.com', 'Admin', 'Administrator', 1, 'Qwe123456'),
    ('test_coustomer@gmail.com', 'Test', 'Coustomer', 2, 'Qwe123456');

CREATE TABLE cart(
	cart_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    prod_id INT NOT NULL,
    price DECIMAL(9,2) NOT NULL,
    quantity DECIMAL(9,3) NOT NULL,
    sum DECIMAL(9,2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (prod_id) REFERENCES prod(prod_id)
);



CREATE TABLE order_head(
	order_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_date TIMESTAMP NOT NULL,
    order_status VARCHAR(255),
    order_note VARCHAR(255),
    user_id INT NOT NULL,
    total_quantity DECIMAL(9,3) NOT NULL,
    total_sum DECIMAL(9,2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE order_details(
	order_det_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    prod_id INT NOT NULL,
    quantity DECIMAL(9,3) NOT NULL,
    price DECIMAL(9,2) NOT NULL,
    sum DECIMAL(9,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES order_head(order_id),
    FOREIGN KEY (prod_id) REFERENCES prod(prod_id)
);