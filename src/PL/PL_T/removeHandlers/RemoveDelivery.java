package PL.PL_T.removeHandlers;
import BL.BL_T.EntitiyFunctions.DeliveryFunctions;
import PL.PL_T.Functor;

import java.util.Scanner;

/**
 * Created by Shahar on 22/04/2018.
 */
public class RemoveDelivery extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter delivery id");
        String id = reader.next();
        try {
            if (!DeliveryFunctions.isExist(id)) {
                System.out.println("error: delivery doesn't exist");
                return;
            }
            DeliveryFunctions.removeDelivery(id);
        } catch (Exception e) {
            System.out.println("error: remove failed");
            return;
        }
    }
}
