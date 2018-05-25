package PL.PL_T.retrieveHandlers;

import BL.BL_T.Entities.LicenseTypeForTruck;
import BL.BL_T.EntitiyFunctions.DriverLicenseFunctions;
import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Worker;
import PL.Functor;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Naama on 21/04/2018.
 */
public class RetrieveLicenseForDriver extends Functor{

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter driver id");
        String id = reader.next();
        Worker driver = WorkerLogic.getWorker(id);
        try {
            if (driver == null){
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
