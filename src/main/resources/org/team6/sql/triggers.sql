-- Creating triggers for the database

-- Create a trigger to update the Utilities table when a new entry is added to ElectricityUtilities
CREATE ALIAS IF NOT EXISTS insert_or_update_utilities FOR "
    void insOrUpUt(Connection c, int id, Timestamp t) throws SQLException {
        String query = 
            'MERGE INTO Utilities (id, t) ' +
            'KEY (id) ' +
            'VALUES (?, ?)';
        
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setTimestamp(2, time);
            stmt.executeUpdate();
        }
    }
";

-- Trigger for AFTER INSERT
CREATE TRIGGER UpdateUtilitiesAfterInsert
AFTER INSERT ON ElectricityUtilities
FOR EACH ROW
CALL insert_or_update_utilities(NEW.id, NEW.time);

-- Trigger for AFTER UPDATE
CREATE TRIGGER UpdateUtilitiesAfterUpdate
AFTER UPDATE ON ElectricityUtilities
FOR EACH ROW
CALL insert_or_update_utilities(NEW.id, NEW.time);

-- Create a trigger to update the Usages table when a new entry is added to Owners
CREATE ALIAS IF NOT EXISTS insert_or_update_usages FOR "
    void insertOrUpdateUsages(Connection conn, int id, String product, int current_energy_output) throws SQLException {
        String query = 
            'MERGE INTO Usages (id, product, current_energy_output) ' +
            'KEY (id) ' +
            'VALUES (?, ?, ?)';
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, product);
            stmt.setInt(3, current_energy_output);
            stmt.executeUpdate();
        }
    }

";

-- Trigger for AFTER INSERT
CREATE TRIGGER OwnerConnectsProduct
AFTER INSERT ON Owners
FOR EACH ROW
CALL insert_or_update_usages(NEW.usage_id, NEW.product, 0);

