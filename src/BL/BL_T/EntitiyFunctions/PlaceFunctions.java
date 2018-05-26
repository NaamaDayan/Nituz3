package BL.BL_T.EntitiyFunctions;

import BL.BL_T.Entities.Place;
import BL.BL_W.Entities_W.Shift;
import DAL.DAL_T.ErrorsHandler;
import DAL.DAL_T.Places;
import DAL.DAL_W.WorkersDatabase;

import java.sql.SQLException;

/**
 * Created by Naama on 21/04/2018.
 */
public class PlaceFunctions {

    public static void insertPlace(Place place){

        Places.insertPlace(place.getId(), place.getAddress(), place.getPhoneNumber(), place.getContactName().getId());
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

    public static boolean isShiftExistInPlace(Place place, Shift shift) throws Exception {
        return Places.isShiftExistInPlace(shift, place);
    }

}
