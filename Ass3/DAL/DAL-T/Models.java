import Entities.TruckModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Models {

    public static void insertModel(TruckModel m) throws SQLException {
        Connection conn = Utils.openConnection();
        String query = "INSERT INTO Models VALUES (?,?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, m.getId());
        stmt.setString(2, m.getModelName());
        stmt.executeUpdate();
        conn.close();
    }

    public static void removeModel(String id) throws SQLException {
        Connection conn = Utils.openConnection();
        String query = "DELETE FROM Models WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        stmt.executeUpdate();
        conn.close();
    }

    public static TruckModel retrieveModel(String id) throws SQLException {
        Connection conn = Utils.openConnection();
        String query = "SELECT * FROM Models WHERE ID= ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (!rs.isBeforeFirst())
            return null;
        TruckModel model = createModel(rs);
        conn.close();
        return model;
    }

    public static void updateModel(TruckModel m) throws SQLException {
        Connection conn = Utils.openConnection();
        String query = "UPDATE Models SET MODEL_NAME = ? WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, m.getModelName());
        stmt.setString(2, m.getId());
        stmt.executeUpdate();
        conn.close();
    }

    public static TruckModel createModel(ResultSet rs) throws SQLException {
        if (!rs.isBeforeFirst()) //not exists
            return null;
        String id = rs.getString("ID");
        String name = rs.getString("MODEL_NAME");
        return new TruckModel(id, name);
    }
}
