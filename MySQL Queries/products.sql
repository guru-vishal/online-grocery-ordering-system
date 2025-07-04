CREATE TABLE Products(
	product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    product_category VARCHAR(50) NOT NULL,
    price DECIMAL(10,2),
    stock_quantity INT,
    product_description TEXT
);

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Rice', 'Grains', 45.0, 100, 'High-quality basmati rice');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Wheat Flour', 'Grains', 35.0, 150, 'Whole wheat flour for healthy baking');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Milk', 'Dairy', 25.0, 200, 'Fresh cow milk');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Butter', 'Dairy', 150.0, 50, 'Creamy and smooth butter');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Eggs', 'Poultry', 6.0, 500, 'Organic farm eggs');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Apple', 'Fruits', 120.0, 80, 'Fresh and juicy apples');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Banana', 'Fruits', 40.0, 100, 'Sweet and ripe bananas');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Tomato', 'Vegetables', 30.0, 300, 'Fresh red tomatoes');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Potato', 'Vegetables', 20.0, 400, 'Good quality potatoes');

INSERT INTO Products (product_name, product_category, price, stock_quantity, product_description)
VALUES ('Chicken', 'Meat', 200.0, 60, 'Freshly cut chicken meat');


SELECT * FROM Products;

DROP TABLE Products;