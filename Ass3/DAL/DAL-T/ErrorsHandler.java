import java.sql.*;

/**
 * Created by Naama on 22/04/2018.
 */
public class ErrorsHandler {

    public static boolean isTruckExist(String id) throws SQLException, ClassNotFoundException {
        return isEntityExist(id,  "Trucks");
    }

    public static boolean isModelExist(String id) throws SQLException, ClassNotFoundException {
        return isEntityExist(id, "Models");
    }

    public static boolean isDriverExist(String id) throws SQLException, ClassNotFoundException {
        return isEntityExist(id,  "Drivers");
    }

    public static boolean isDeliveryExist(String id) throws SQLException, ClassNotFoundException {
        return isEntityExist(id,  "Deliveries");
    }
    public static boolean isPlaceExist(String id) throws SQLException, ClassNotFoundException {
        return isEntityExist(id,  "Places");
    }
    public static boolean isLicenseExist(String id) throws SQLException, ClassNotFoundException {
        return isEntityExist(id,  "Licenses");
    }

    public static boolean isDeliveryDestinationExist(String deliveryId, String destId) throws SQLException, ClassNotFoundException {
        Connection conn = Utils.openConnection();
        String query  = "SELECT * FROM DeliveryDestinations WHERE DELIVERY_ID = ? AND PLACE_ID = ? ";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, deliveryId);
        stmt.setString(2, destId);
        ResultSet rs = stmt.executeQuery();
        conn.close();
        return rs.isBeforeFirst();
    }

    public static boolean isDriverLicenseExist(String driverId, String licenseId) throws SQLException, ClassNotFoundException {
        Connection conn = Utils.openConnection();
        String query  = "SELECT * FROM LicensesForDrivers WHERE DRIVER_ID = ? AND LICENSE_TYPE = ? ";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, driverId);
        stmt.setString(2, licenseId);
        ResultSet rs = stmt.executeQuery();
        conn.close();
        return rs.isBeforeFirst();
    }

    public static boolean isEntityExist(String id,  String tableName) throws SQLException, ClassNotFoundException {
        Connection conn = Utils.openConnection();
        String query  = "SELECT * FROM " + tableName +" WHERE ID = ? ";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        //stmt.setString(2, id);
        ResultSet rs = stmt.executeQuery();
        conn.close();
        return rs.isBeforeFirst();
    }

}
