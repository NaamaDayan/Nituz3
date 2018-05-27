package BL.BL_T.EntitiyFunctions;



import BL.BL_T.Entities.Driver;
import DAL.DAL_T.Drivers;

import java.sql.SQLException;


public class DriverFunctions {

    public static Driver getDriver(String driverId) {
        return Drivers.retrieveDriver(driverId);
    }
}
