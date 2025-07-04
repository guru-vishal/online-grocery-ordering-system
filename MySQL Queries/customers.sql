CREATE TABLE Customers(
	customer_id INT PRIMARY KEY AUTO_INCREMENT,
	customer_name VARCHAR(100) NOT NULL,
	customer_email VARCHAR(100),
	customer_phone VARCHAR(15),
	customer_address VARCHAR(255)
);

USE online_grocery_ordering_system;

SET SQL_SAFE_UPDATES=0;

INSERT INTO Customers (customer_id, customer_name, customer_email, customer_phone, customer_address)
VALUES (-1, "admin", "admin", "admin", "admin");

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ("Guru", "vishal3012006@gmail.com", "9597293169", "Virudhunagar");
INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('John Doe', 'john.doe@example.com', '9876543210', '123 Elm St, Springfield');

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('Jane Smith', 'jane.smith@example.com', '9123456789', '456 Maple Ave, Greenfield');

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('Alice Brown', 'alice.brown@example.com', '9234567890', '789 Oak Dr, Rivertown');

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('Bob Johnson', 'bob.johnson@example.com', '9345678901', '101 Pine Ln, Hilltop');

INSERT INTO customers (customer_name, customer_email, customer_phone, customer_address) 
VALUES ('Charlie Davis', 'charlie.davis@example.com', '9456789012', '202 Birch Rd, Lakeview');

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('David Evans', 'david.evans@example.com', '9567890123', '303 Cedar St, Pinehill');

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('Emily Wilson', 'emily.wilson@example.com', '9678901234', '404 Willow Way, Oakdale');

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('Frank Miller', 'frank.miller@example.com', '9789012345', '505 Redwood Blvd, Seaview');

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('Grace Moore', 'grace.moore@example.com', '9890123456', '606 Maple St, Brookhaven');

INSERT INTO Customers (customer_name, customer_email, customer_phone, customer_address)
VALUES ('Henry Lee', 'henry.lee@example.com', '9901234567', '707 Aspen Rd, Highland');


SELECT * FROM Customers;

DELETE FROM Customers;

DROP TABLE Customers;