import java.sql.Connection;
import java.sql.*;
import Entities.Truck;
import Entities.TruckModel;
import EntitiyFunctions.ModelFunctions;

public class Trucks {


    public static void insertTruck(Truck t){
        try (Connection conn = Utils.openConnection()) {
            String query = "INSERT INTO Trucks VALUES (?, ?, ? ,?,?)  ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, t.getId());
            stmt.setString(2, t.getModel().getId());
            stmt.setString(3, t.getColor());
            stmt.setInt(4, t.getNetoWeight());
            stmt.setInt(5, t.getMaxWeight());
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    public static void removeTruck(String id){
        try (Connection conn = Utils.openConnection()) {
            String query = "DELETE FROM Trucks WHERE ID = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static Truck retrieveTruck(String id){
        try (Connection conn = Utils.openConnection()) {
            String query = "SELECT * FROM Trucks WHERE ID = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Truck truck = createTruck(rs);
            conn.close();
            return truck;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static void updateTruck(Truck t) throws SQLException {
        Connection conn = Utils.openConnection();
        String query = "UPDATE Trucks SET MODEL = ?, COLOR = ?, NETO_WEIGHT = ?, MAX_WEIGHT = ? WHERE ID = ?  ";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, t.getModel().getId());
        stmt.setString(2, t.getColor());
        stmt.setInt(3, t.getNetoWeight());
        stmt.setInt(4, t.getMaxWeight());
        stmt.setString(5, t.getId());
        stmt.executeUpdate();
        conn.close();
    }

    public static Truck createTruck(ResultSet rs) throws SQLException {
        if (!rs.isBeforeFirst()) //not exists
            return null;
        String id = rs.getString("ID");
        String modelId = rs.getString("MODEL");
        TruckModel model = ModelFunctions.retrieveModel(modelId);//exists because otherwise, the Truck itself
                                                                // wouldn't be existed
        String color = rs.getString("COLOR");
        int netoWeight = rs.getInt("NETO_WEIGHT");
        int maxWeight = rs.getInt("MAX_WEIGHT");
        return new Truck(id, model, color, netoWeight, maxWeight);
    }
}
