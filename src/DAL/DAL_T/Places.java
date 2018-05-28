package DAL.DAL_T;


import BL.BL_T.Entities.Place;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;
import BL.BL_W.WorkerLogic;
import DAL.Tables;

import java.sql.*;

public class Places {

    public static void insertPlace(String placeId, String address, String phoneNumber, String contactName){
        try (Connection conn = Tables.openConnection()) {
            String query = "INSERT INTO Places VALUES (?, ?, ?, ?)  ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, placeId);
            stmt.setString(2, address);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, contactName);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void removePlace(String id){
        try (Connection conn = Tables.openConnection()) {
            String query = "DELETE FROM Places WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static Place retrievePlace(String id) {
        try (Connection conn = Tables.openConnection()) {
            String query = "SELECT * FROM Places WHERE ID= (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Place place = createPlace(rs);
            conn.close();
            return place;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static void updatePlace(Place p) throws SQLException, ClassNotFoundException {
        Connection conn = Tables.openConnection();
        String query = "UPDATE Places SET ADDRESS = ?, PHONE_NUMBER = ?, CONTACT_WORKER = ? WHERE ID = ?  ";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, p.getAddress());
        stmt.setString(2, p.getPhoneNumber());
        stmt.setString(3, p.getContactWorker().getId());
        stmt.setString(4, p.getId());
        stmt.executeUpdate();
        conn.close();
    }

    public static Place createPlace(ResultSet rs) throws SQLException {
        if (!rs.isBeforeFirst()) //not exists
            return null;
        String id = rs.getString("ID");
        String address = rs.getString("ADDRESS");
        String contactName = rs.getString("CONTACT_WORKER");
        Worker contact = WorkerLogic.getWorker(contactName);
        String phoneNumber = rs.getString("PHONE_NUMBER");
        return new Place(id, address, phoneNumber, contact);
    }

    // TODO: 27/05/18 instead of this use getShift(Dan wrote) (S)     
    public static boolean isPlaceHasShiftInSpecifiedTime(Place place, Shift.ShiftDayPart dp, java.sql.Date date) {
        throw new UnsupportedOperationException();
    }
}
