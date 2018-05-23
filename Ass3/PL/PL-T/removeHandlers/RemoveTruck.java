package removeHandlers;
import EntitiyFunctions.TruckFunctions;
import Functor;

import java.util.Scanner;

/**
 * Created by Naama on 21/04/2018.
 */
public class RemoveTruck extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter truck id");
        String truckId = reader.next();
        try {
            if (!TruckFunctions.isExist(truckId)) {
                System.out.println("error: truck doesn't exist");
                return;
            }
        } catch (Exception e) {
            System.out.println("error: remove failed");
            return;
        }
        TruckFunctions.removeTruck(truckId);
    }
}
