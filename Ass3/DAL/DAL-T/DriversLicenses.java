import Entities.LicenseTypeForTruck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DriversLicenses {

    public static void insertDriverLicense(String driverId, String licenseId){
        try (Connection conn = Utils.openConnection()) {
            String query = "INSERT INTO LicensesForDrivers VALUES (?, ?)  ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, driverId);
            stmt.setString(2, licenseId);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void removeDriverLicense(String driverId ,String licenseId){
        try (Connection conn = Utils.openConnection()) {
            String query = "DELETE FROM LicensesForDrivers WHERE DRIVER_ID = ? AND LICENSE_TYPE = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, driverId);
            stmt.setString(2, licenseId);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //returns a list of all the licenses of a driver
    public static List<LicenseTypeForTruck> retrieveDriverLicenses(String driverId){
        try (Connection conn = Utils.openConnection()) {
            String query = "SELECT * FROM LicensesForDrivers WHERE DRIVER_ID = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, driverId);
            ResultSet rs = stmt.executeQuery();
            List<LicenseTypeForTruck> licenses = null;
            if (rs.isBeforeFirst()) {
                licenses = new LinkedList<>();
                while (rs.next()) {
                    String licenseType = rs.getString("LICENSE_TYPE");
                    LicenseTypeForTruck license = LicenseForTruck.retrieveLicense(licenseType);
                    if (license != null)
                        licenses.add(license);
                }
            }
            conn.close();
            return licenses;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }



}
