package retrieveHandlers;

import Entities.LicenseTypeForTruck;
import EntitiyFunctions.DriverFunctions;
import EntitiyFunctions.DriverLicenseFunctions;
import Functor;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Naama on 21/04/2018.
 */
public class RetrieveLicenseForDriver extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter driver id");
        String id = reader.next();
        try {
            if (!DriverFunctions.isExist(id)){
                System.out.println("driver does not exist");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<LicenseTypeForTruck> licensesTypes = DriverLicenseFunctions.retrieveLicenses(id);
        System.out.println("licenses' ids of driver "+id+":");
        if (licensesTypes == null)
            System.out.println("no licenses");
        else {
            for (LicenseTypeForTruck license : licensesTypes)
                System.out.println(license + ", ");
        }
    }
}
