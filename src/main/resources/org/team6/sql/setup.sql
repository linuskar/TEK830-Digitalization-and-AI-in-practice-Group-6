-- Setup for the database

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
    product TEXT,
    current_energy_output INT CHECK (current_energy_output >= 0),
    PRIMARY KEY (product),
    FOREIGN KEY (product) REFERENCES Products(name)
);

CREATE TABLE Utilities (
    product TEXT,
    time DATE,
    PRIMARY KEY (product, time),
    FOREIGN KEY (product) REFERENCES Usages(product)
);

CREATE TABLE ElectricityUtilities (
    product TEXT,
    time DATE,
    power_consumption INT CHECK (power_consumption >= 0),
    PRIMARY KEY (product, time),
    FOREIGN KEY (product, time) REFERENCES Utilities(product, time)
);

SELECT * FROM products;

INSERT INTO products (name) VALUES ('Lamp');