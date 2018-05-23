package removeHandlers;
import EntitiyFunctions.DeliveryDestinationFunctions;
import Functor;

import java.util.Scanner;

/**
 * Created by Shahar on 22/04/2018.
 */
public class RemoveDeliveryDestination extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter delivery id");
        String deliveryId = reader.next();
        System.out.println("enter destination id");
        String destId = reader.next();
        try {
            if (!DeliveryDestinationFunctions.isExist(deliveryId, destId)) {
                System.out.println("error: delivery destination does not exist");
                return;
            }
            DeliveryDestinationFunctions.removeDeliveryDestination(deliveryId, destId);
        }catch (Exception e) {
            System.out.println("error: remove failed");
            return;
        }
    }
}
