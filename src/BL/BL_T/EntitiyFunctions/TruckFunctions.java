package BL.BL_T.EntitiyFunctions;

import BL.BL_T.Entities.Truck;
import DAL.DAL_T.ErrorsHandler;
import DAL.DAL_T.Trucks;

import java.sql.SQLException;

/**
 * Created by Naama on 21/04/2018.
 */
public class TruckFunctions
{
    public static void insertTruck(Truck truck) {
        Trucks.insertTruck(truck);
    }

    public static Truck retrieveTruck(String id){
        return Trucks.retrieveTruck(id);
    }

    public static void removeTruck(String id){
        Trucks.removeTruck(id);
    }

    public static void updateTruck(Truck t) throws SQLException, ClassNotFoundException {
        Trucks.updateTruck(t);
    }
    public static boolean isExist(String id) throws Exception {
        return ErrorsHandler.isTruckExist(id);
    }
}
