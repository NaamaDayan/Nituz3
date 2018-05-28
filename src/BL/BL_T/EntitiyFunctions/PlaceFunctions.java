package BL.BL_T.EntitiyFunctions;

import BL.BL_T.Entities.Place;
import BL.BL_W.Entities_W.Shift;
import DAL.DAL_T.ErrorsHandler;
import DAL.DAL_T.Places;

import java.sql.SQLException;

/**
 * Created by Naama on 21/04/2018.
 */
public class PlaceFunctions {

    public static void insertPlace(Place place){

        Places.insertPlace(place.getId(), place.getAddress(), place.getPhoneNumber(), place.getContactWorker().getId());
    }

    public static Place retrievePlace(String id){
        return Places.retrievePlace(id);
    }

    public static void removePlace(String id){
        Places.removePlace(id);
    }

    public static void updatePlace(Place p) throws SQLException, ClassNotFoundException {
        Places.updatePlace(p);
    }

    public static boolean isExist(String id) throws Exception {
        return ErrorsHandler.isPlaceExist(id);
    }

    public static boolean isPlaceHasShiftInSpecifiedTime(Place place, Shift.ShiftDayPart dp, java.sql.Date date) throws Exception {
        return Places.isPlaceHasShiftInSpecifiedTime(place, dp, date);
    }

}
