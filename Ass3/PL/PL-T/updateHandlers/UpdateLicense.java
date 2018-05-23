package updateHandlers;

import Entities.LicenseTypeForTruck;
import Entities.TruckModel;
import EntitiyFunctions.LicenseTypeForTruckFunctions;
import EntitiyFunctions.ModelFunctions;
import Functor;
import Utils;

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
