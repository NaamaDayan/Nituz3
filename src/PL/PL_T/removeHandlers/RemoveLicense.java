package PL.PL_T.removeHandlers;
import BL.BL_T.EntitiyFunctions.LicenseTypeForTruckFunctions;
import PL.Functor;

import java.util.Scanner;

/**
 * Created by Shahar on 22/04/2018.
 */
public class RemoveLicense extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
       System.out.println("enter license id");
        String id = reader.next();
        try {
            if (!LicenseTypeForTruckFunctions.isExist(id)) {
                System.out.println("error: License doesn't exist");
                return;
            }
        } catch (Exception e) {
            System.out.println("error: remove failed");
            return;
        }

        LicenseTypeForTruckFunctions.removeLicense(id);
    }
}
