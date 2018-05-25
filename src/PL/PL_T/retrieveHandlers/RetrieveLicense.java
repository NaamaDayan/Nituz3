package PL.PL_T.retrieveHandlers;

import BL.BL_T.Entities.LicenseTypeForTruck;
import BL.BL_T.EntitiyFunctions.LicenseTypeForTruckFunctions;
import PL.Functor;

import java.util.Scanner;

/**
 * Created by Naama on 21/04/2018.
 */
public class RetrieveLicense extends Functor{
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter license id");
        String id = reader.next();
        try {
            if (!LicenseTypeForTruckFunctions.isExist(id)){
                System.out.println("license does not exist");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LicenseTypeForTruck license = LicenseTypeForTruckFunctions.retrieveLicenses(id);
        System.out.println(license);
    }


}
