-- Disable foreign key checks (if supported by your database)
-- For H2, use the following command:
SET REFERENTIAL_INTEGRITY FALSE;

-- Drop tables if they exist
DROP TABLE IF EXISTS ElectricityUtilities;
DROP TABLE IF EXISTS Utilities;
DROP TABLE IF EXISTS Owners;
DROP TABLE IF EXISTS Usages;
DROP TABLE IF EXISTS LampProducts;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Products;

-- Re-enable foreign key checks (if supported by your database)
-- For H2, use the following command:
SET REFERENTIAL_INTEGRITY TRUE;