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