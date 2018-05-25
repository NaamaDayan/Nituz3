package DAL.DAL_T;

import BL.BL_T.Entities.Driver;
import BL.BL_T.Entities.LicenseTypeForTruck;

import java.sql.*;
import java.util.List;

public class Drivers {

    /*public static void insertDriver(String id, String firstName, String lastName, String phoneNumber){
        try (Connection conn = Utils.openConnection()) {
            String query = "INSERT INTO Drivers VALUES (?, ?, ? ,?)  ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, phoneNumber);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void removeDriver(String id) throws SQLException {
        Connection conn = Utils.openConnection();
        String query = "DELETE FROM Drivers WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        stmt.executeUpdate();
        conn.close();
    }

    public static Driver retrieveDriver(String id){
        try (Connection conn = Utils.openConnection()) {
            String query = "SELECT * FROM Drivers WHERE ID = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Driver driver = createDriver(rs);
            conn.close();
            return driver;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static void updateDriver(Driver d) throws SQLException, ClassNotFoundException {
        Connection conn = Utils.openConnection();
        String query = "UPDATE Drivers SET FIRST_NAME = ?, LAST_NAME = ?, PHONE_NUMBER = ? WHERE ID = ?  ";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, d.getFirstName());
        stmt.setString(2, d.getLastName());
        stmt.setString(3, d.getPhoneNumber());
        stmt.setString(4, d.getId());
        stmt.executeUpdate();
        conn.close();
    }

    public static Driver isDriverExist(String id) throws SQLException, ClassNotFoundException {
        Connection conn = Utils.openConnection();
        String query = "SELECT * FROM Drivers WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        Driver driver = createDriver(rs);
        conn.close();
        return driver;
    }

    public static Driver createDriver(ResultSet rs) throws SQLException {
        if (!rs.isBeforeFirst()) //not exists)
            return null;
        String id = rs.getString("ID");
        String firstName = rs.getString("FIRST_NAME");
        String lastName = rs.getString("LAST_NAME");
        String phoneNumber = rs.getString("PHONE_NUMBER");
        List<LicenseTypeForTruck> licenses = DriversLicenses.retrieveDriverLicenses(id);
        return new Driver(id, firstName, lastName, phoneNumber, licenses);
    }*/


}
