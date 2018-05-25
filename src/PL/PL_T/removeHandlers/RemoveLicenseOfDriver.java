package PL.PL_T.removeHandlers;
import BL.BL_T.EntitiyFunctions.DriverLicenseFunctions;
import PL.PL_T.Functor;

import java.util.Scanner;

/**
 * Created by Shahar on 22/04/2018.
 */
public class RemoveLicenseOfDriver extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter driver id");
        String driverId = reader.next();
        System.out.println("enter license id");
        String licenseId = reader.next();
        try {
            if (!DriverLicenseFunctions.isExist(driverId, licenseId)) {
                System.out.println("error: driver's license doesn't exist");
                return;
            }
        } catch (Exception e) {
            System.out.println("error: remove failed");
            return;
        }
        DriverLicenseFunctions.removeLicenseOfDriver(driverId, licenseId);
    }
}
