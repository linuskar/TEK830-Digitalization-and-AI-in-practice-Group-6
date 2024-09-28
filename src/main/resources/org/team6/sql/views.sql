-- Create views for the database

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
        FORMATDATETIME(time, 'yyyy-MM') AS period, 
        'Monthly' AS period_type, 
        SUM(power_consumption) AS total_power_consumption
    FROM 
        ElectricityUtilities JOIN Owners ON ElectricityUtilities.id = Owners.usage_id
    GROUP BY 
        user_id,
        FORMATDATETIME(time, 'yyyy-MM')
    
    UNION ALL
    
    -- Yearly total power consumption
    SELECT 
        user_id,
        FORMATDATETIME(time, 'yyyy') AS period, 
        'Yearly' AS period_type, 
        SUM(power_consumption) AS total_power_consumption
    FROM 
        ElectricityUtilities JOIN Owners ON ElectricityUtilities.id = Owners.usage_id
    GROUP BY 
        user_id,
        FORMATDATETIME(time, 'yyyy')
);

-- Create a view that shows the utlitities of all products
-- Update to include when other utilities are added
CREATE OR REPLACE VIEW ProductUtilities AS (
    SELECT user_id, product, ut.time, power_consumption 
    FROM Utilities ut 
    LEFT JOIN ElectricityUtilities eu ON ut.id = eu.id
    LEFT JOIN Owners O ON eu.id = O.usage_id
    LEFT JOIN Usages U ON O.usage_id = U.id
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