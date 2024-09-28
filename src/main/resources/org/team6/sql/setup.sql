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
    FOREIGN KEY (id, time) REFERENCES Utilities(product, time)
);