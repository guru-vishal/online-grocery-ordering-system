CREATE TABLE Deliveries(
	delivery_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    delivery_date DATETIME,
    delivery_status VARCHAR(50),
    tracking_info TEXT,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id) 
);

SELECT * FROM Deliveries;
DELETE FROM Deliveries;
DROP TABLE Deliveries;