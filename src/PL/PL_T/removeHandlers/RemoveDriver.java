package PL.PL_T.removeHandlers;
import BL.BL_T.EntitiyFunctions.DriverFunctions;
import PL.Functor;

import java.util.Scanner;

/**
 * Created by Shahar on 22/04/2018.
 */
public class RemoveDriver extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter driver id");
        String id = reader.next();
        try {
            if (!DriverFunctions.isExist(id)) {
                System.out.println("error: driver doesn't exist");
                return;
            }
        DriverFunctions.removeDriver(id);
        } catch (Exception e) {
            System.out.println("error: remove failed");
            return;
        }
    }
}
