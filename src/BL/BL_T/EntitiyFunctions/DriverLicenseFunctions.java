package BL.BL_T.EntitiyFunctions;

import BL.BL_T.Entities.LicenseTypeForTruck;
import DAL.DAL_T.DriversLicenses;
import DAL.DAL_T.ErrorsHandler;

import java.util.List;

/**
 * Created by Naama on 21/04/2018.
 */
public class DriverLicenseFunctions {

    public static void insertDriverLicense(LicenseTypeForTruck license, String driverId){
        DriversLicenses.insertDriverLicense(driverId, license.getLicenseType());
    }
    public static List<LicenseTypeForTruck> retrieveLicenses(String driverId){
        return DriversLicenses.retrieveDriverLicenses(driverId);
    }

    public static void removeLicenseOfDriver(String driverId ,String licenseId){
        DriversLicenses.removeDriverLicense(driverId, licenseId);
    }

    public static boolean isExist(String driverId, String licenseId) throws Exception {
        return ErrorsHandler.isDriverLicenseExist(driverId, licenseId);
    }
}
