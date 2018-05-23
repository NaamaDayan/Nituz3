package EntitiyFunctions;

import Entities.Driver;
import Drivers;
import ErrorsHandler;

import java.sql.SQLException;

/**
 * Created by Naama on 21/04/2018.
 */
public class DriverFunctions {

    public static void insertDriver(Driver driver) {
        Drivers.insertDriver(driver.getId(), driver.getFirstName(), driver.getLastName(), driver.getPhoneNumber());
    }

    public static Driver retrieveDriver(String id){
        return Drivers.retrieveDriver(id);
    }

    public static void removeDriver(String id) throws SQLException, ClassNotFoundException {
     //   Driver d = Drivers.retrieveDriver(id);
        Drivers.removeDriver(id);
        /*List<LicenseTypeForTruck> licenseList = DriversLicenses.retrieveDriverLicenses(id); //list of id's of licenses of driver
        for (LicenseTypeForTruck license: licenseList) {
            DriversLicenses.removeDriverLicense(id, license.getLicenseType());
        }*/
    }

    public static void updateDriver(Driver d) throws SQLException, ClassNotFoundException {
        Drivers.updateDriver(d);
    }

    public static boolean isExist(String id) throws Exception {
        return ErrorsHandler.isDriverExist(id);
    }
}
