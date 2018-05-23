package EntitiyFunctions;

import Entities.LicenseTypeForTruck;
import ErrorsHandler;
import LicenseForTruck;

import java.sql.SQLException;

/**
 * Created by Naama on 21/04/2018.
 */
public class LicenseTypeForTruckFunctions {

    public static void insertLicense(LicenseTypeForTruck ltft){
        LicenseForTruck.insertLicense(ltft.getTruckModel(), ltft.getLicenseType());
    }

    public static LicenseTypeForTruck retrieveLicenses(String truckModel){
        return LicenseForTruck.retrieveLicense(truckModel);
    }

    public static void removeLicense(String id){
      //  LicenseTypeForTruck l = LicenseForTruck.retrieveLicense(id);
        LicenseForTruck.removeLicense(id);
        /*List<LicenseTypeForTruck> licenseList = DriversLicenses.retrieveDriverLicenses(id); //list of id's of licenses of driver
        for (LicenseTypeForTruck license: licenseList) {
            DriversLicenses.removeDriverLicense(id, license.getLicenseType());
        }*/
    }

    public static void updateLicense(LicenseTypeForTruck d) throws SQLException, ClassNotFoundException {
        LicenseForTruck.updateLicense(d);
    }

    public static boolean isExist(String id) throws Exception {
        return ErrorsHandler.isLicenseExist(id);
    }
}
