package PL.PL_T.updateHandlers;

import BL.BL_T.Entities.LicenseTypeForTruck;
import BL.BL_T.Entities.TruckModel;
import BL.BL_T.EntitiyFunctions.LicenseTypeForTruckFunctions;
import BL.BL_T.EntitiyFunctions.ModelFunctions;
import PL.PL_T.Functor;
import PL.PL_T.Utils;

import java.util.Scanner;


public class UpdateLicense extends Functor {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        String idToUpdate;
        String newField;
        System.out.println("enter License's ID");
        idToUpdate = reader.next();
        LicenseTypeForTruck l;
        try {
            if (!LicenseTypeForTruckFunctions.isExist(idToUpdate)) {
                System.out.println("error: ID doesn't exist");
                return;
            }
            l = LicenseTypeForTruckFunctions.retrieveLicenses(idToUpdate);
            if (Utils.boolQuery("update truck model? y/n")) {
                System.out.println("enter truck model id");
                newField = reader.next();
                TruckModel model = ModelFunctions.retrieveModel(newField);
                if (model == null) {
                    System.out.println("model does't exist");
                    return;
                }
                l.setTruckModel(model);
            }
            LicenseTypeForTruckFunctions.updateLicense(l);
        } catch (Exception e) {
            System.out.println("error: update failed");
        }
    }
}
