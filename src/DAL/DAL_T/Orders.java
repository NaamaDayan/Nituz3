package DAL.DAL_T;

import BL.BL_T.Entities.Order;
import DAL.Tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Naama on 25/04/2018.
 */
public class Orders {


    public static void removeOrder(String orderId){
        try (Connection conn = Tables.openConnection()) {
            String query = "DELETE FROM OrdersItems WHERE ORDER_NUMBER = ? ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, orderId);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static Order retrieveOrder(String deliveryId, String placeId, String orderId) {
        try (Connection conn = Tables.openConnection()) {
            String query = "SELECT * FROM DeliveryDestinations WHERE DELIVERY_ID = ?" +
                    "AND ORDER_NUMBER= (?) AND PLACE_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, deliveryId);
            stmt.setString(2, orderId);
            stmt.setString(3, placeId);
            ResultSet rs = stmt.executeQuery();
            Order order = createOrder(rs, orderId);
            conn.close();
            return order;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static Order createOrder(ResultSet rs, String orderId) throws SQLException {
        if (!rs.isBeforeFirst()) //not exists
            return null;
        Map<String, Integer> orderItems = new HashMap<>();
        while (rs.next()){
            String item = rs.getString("ITEM_ID");
            int quantity = rs.getInt("QUANTITY");
            orderItems.put(item, quantity);
        }
        return new Order(orderId, orderItems);
    }
}
