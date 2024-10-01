-- Setup for the database
-- Creating tables

-- Create tables for the database
-- Drop tables if they exist


CREATE TABLE Products (
    name TEXT,
    PRIMARY KEY (name)
);

CREATE TABLE LampProducts(
    name TEXT,
    on_mode_power INT CHECK (on_mode_power >= 0),
    standby_power INT CHECK (standby_power >= 0),
    PRIMARY KEY (name),
    FOREIGN KEY (name) REFERENCES Products(name)
);

CREATE TABLE Usages (
    id INT,
    product TEXT,
    current_energy_output INT CHECK (current_energy_output >= 0),
    PRIMARY KEY (id),
    FOREIGN KEY (product) REFERENCES Products(name)
);

CREATE TABLE Utilities (
    id INT,
    time DATE,
    PRIMARY KEY (id, time),
    FOREIGN KEY (id) REFERENCES Usages(id)
);

CREATE TABLE ElectricityUtilities (
    id INT,
    time DATE,
    power_consumption INT CHECK (power_consumption >= 0),
    PRIMARY KEY (id, time),
    FOREIGN KEY (id, time) REFERENCES Utilities(id, time)
);

CREATE TABLE Users (
    id INT,
    fname TEXT NOT NULL,
    lname TEXT NOT NULL,
    address TEXT,
    pcode TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE Owners (
    user_id INT,
    usage_id INT,
    product TEXT,
    PRIMARY KEY (user_id, usage_id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (usage_id) REFERENCES Usages(id),
    FOREIGN KEY (product) REFERENCES Products(name)
);

-- Inserting values
-- Inserting values into Products
INSERT INTO Products (name) VALUES ('LED Bulb');
INSERT INTO Products (name) VALUES ('Smart Thermostat');
INSERT INTO Products (name) VALUES ('Solar Panel');

-- Inserting values into LampProducts
INSERT INTO LampProducts (name, on_mode_power, standby_power) VALUES ('LED Bulb', 9, 0);
INSERT INTO LampProducts (name, on_mode_power, standby_power) VALUES ('Smart Thermostat', 5, 1);
INSERT INTO LampProducts (name, on_mode_power, standby_power) VALUES ('Solar Panel', 0, 0);

-- Inserting values into Usages
INSERT INTO Usages (id, product, current_energy_output) VALUES (1, 'LED Bulb', 900);
INSERT INTO Usages (id, product, current_energy_output) VALUES (2, 'Smart Thermostat', 600);
INSERT INTO Usages (id, product, current_energy_output) VALUES (3, 'Solar Panel', 3000);

-- Inserting values into Utilities
INSERT INTO Utilities (id, time) VALUES (1, '2023-10-01');
INSERT INTO Utilities (id, time) VALUES (2, '2023-10-02');
INSERT INTO Utilities (id, time) VALUES (2, '2023-10-03');
INSERT INTO Utilities (id, time) VALUES (3, '2023-10-03');
INSERT INTO Utilities (id, time) VALUES (3, '2023-10-04');
INSERT INTO Utilities (id, time) VALUES (3, '2024-10-03');

-- Inserting values into ElectricityUtilities
INSERT INTO ElectricityUtilities (id, time, power_consumption) VALUES (1, '2023-10-01', 500);
INSERT INTO ElectricityUtilities (id, time, power_consumption) VALUES (2, '2023-10-02', 400);
INSERT INTO ElectricityUtilities (id, time, power_consumption) VALUES (2, '2023-10-03', 700);
INSERT INTO ElectricityUtilities (id, time, power_consumption) VALUES (3, '2023-10-03', 1000);
INSERT INTO ElectricityUtilities (id, time, power_consumption) VALUES (3, '2023-10-04', 709);
INSERT INTO ElectricityUtilities (id, time, power_consumption) VALUES (3, '2024-10-03', 451);

-- Inserting values into Users
INSERT INTO Users (id, fname, lname, address, pcode) VALUES (1, 'John', 'Doe', '123 Main St', '12345');
INSERT INTO Users (id, fname, lname, address, pcode) VALUES (2, 'Jane', 'Smith', '456 Elm St', '67890');
INSERT INTO Users (id, fname, lname, address, pcode) VALUES (3, 'Alice', 'Johnson', '789 Oak St', '11223');

-- Inserting values into Owners
INSERT INTO Owners (user_id, usage_id) VALUES (1, 1);
INSERT INTO Owners (user_id, usage_id) VALUES (2, 2);
INSERT INTO Owners (user_id, usage_id) VALUES (3, 3);

-- Creating views 
-- Create a view that shows the total power consumption of all products in a given time period
CREATE OR REPLACE VIEW TotalPowerConsumption AS (
    -- Daily total power consumption
    SELECT 
        user_id,
        CAST(time AS DATE) AS period, 
        'Daily' AS period_type, 
        SUM(power_consumption) AS total_power_consumption
    FROM 
        ElectricityUtilities JOIN Owners ON ElectricityUtilities.id = Owners.usage_id
    GROUP BY 
        user_id,
        CAST(time AS DATE)
    
    UNION ALL
    
    -- Monthly total power consumption
    SELECT 
        user_id,
        DATE_TRUNC('MONTH', time) AS period, 
        'Monthly' AS period_type, 
        SUM(power_consumption) AS total_power_consumption
    FROM 
        ElectricityUtilities JOIN Owners ON ElectricityUtilities.id = Owners.usage_id
    GROUP BY 
        user_id,
        DATE_TRUNC('MONTH', time)
    
    UNION ALL
    
    -- Yearly total power consumption
    SELECT 
        user_id,
        DATE_TRUNC('YEAR', time) AS period, 
        'Yearly' AS period_type, 
        SUM(power_consumption) AS total_power_consumption
    FROM 
        ElectricityUtilities JOIN Owners ON ElectricityUtilities.id = Owners.usage_id
    GROUP BY 
        user_id,
        DATE_TRUNC('YEAR', time)
);

-- Create a view that shows the utilities of all products
-- Update to include when other utilities are added
CREATE OR REPLACE VIEW ProductUtilities AS (
    SELECT ut.id, ut.time, power_consumption 
    FROM Utilities ut 
    LEFT JOIN ElectricityUtilities eu ON ut.id = eu.id AND ut.time = eu.time
);

-- Create a view that allows the user to change the power consumption of a product
CREATE OR REPLACE VIEW ProductStatus AS (
    SELECT user_id, id, product, current_energy_output 
    FROM Usages 
    LEFT JOIN Owners ON Usages.id = Owners.usage_id
);

-- Create a view that shows owned products
CREATE OR REPLACE VIEW OwnedProducts AS (
    SELECT user_id, product 
    FROM Owners O 
    JOIN Usages U ON O.usage_id = U.id
);

-- Creating triggers