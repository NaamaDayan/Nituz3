package PL.PL_T.insertHandlers;


import BL.BL_T.Entities.TruckModel;
import BL.BL_T.EntitiyFunctions.LicenseTypeForTruckFunctions;
import BL.BL_T.EntitiyFunctions.ModelFunctions;
import PL.Functor;
import BL.BL_T.Entities.LicenseTypeForTruck;

import java.util.Scanner;

public class InsertLicense extends Functor
{
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter license id");
        String licenseType = reader.next();
        try {
            if (LicenseTypeForTruckFunctions.isExist(licenseType)){
                System.out.println("license already exists");
                return;
            }
        System.out.println("enter truck model id");
        String truckModel = reader.next();
        TruckModel model = ModelFunctions.retrieveModel(truckModel);
        if (model == null) {
            System.out.println("model does't exist");
            return;
        }
        LicenseTypeForTruck license = new LicenseTypeForTruck(licenseType, model);
        LicenseTypeForTruckFunctions.insertLicense(license);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
