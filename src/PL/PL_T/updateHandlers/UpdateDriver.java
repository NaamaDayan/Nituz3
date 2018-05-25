package PL.PL_T.updateHandlers;

/*import BL.BL_T.Entities.Driver;
import BL.BL_T.EntitiyFunctions.DriverFunctions;
import PL.Functor;
import PL.PL_T.Utils;

import java.util.Scanner;


public class UpdateDriver extends Functor {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        String idToUpdate;
        String newField;
        System.out.println("enter driver's ID");
        idToUpdate = reader.next();
        Driver d;
        try {
            if (!DriverFunctions.isExist(idToUpdate)) {
                System.out.println("error: ID doesn't exist");
                return;
            }
            else {
                d = DriverFunctions.retrieveDriver(idToUpdate);
            }
        } catch (Exception e) {
            System.out.println("error: update failed");
            return;
        }
        if (Utils.boolQuery("update first name? y/n")) {
            System.out.println("enter first name");
            newField = reader.next();
            d.setFirstName(newField);
        }
        if (Utils.boolQuery("update last name? y/n")) {
            System.out.println("enter last name");
            newField = reader.next();
            d.setLastName(newField);
        }
        if (Utils.boolQuery("update phone number? y/n")) {
            System.out.println("enter phone number");
            newField = reader.next();
            d.setPhoneNumber(newField);
        }
        try {
            DriverFunctions.updateDriver(d);
        } catch (Exception e) {
            System.out.println("error: update failed");
        }
    }
}
*/