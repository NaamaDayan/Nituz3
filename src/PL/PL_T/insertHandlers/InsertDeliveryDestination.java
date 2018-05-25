package PL.PL_T.insertHandlers;

import BL.BL_T.Entities.DeliveryDestination;
import BL.BL_T.Entities.Order;
import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.DeliveryDestinationFunctions;
import BL.BL_T.EntitiyFunctions.DeliveryFunctions;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import PL.Functor;
import PL.PL_T.Utils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Naama on 23/04/2018.
 */
public class InsertDeliveryDestination extends Functor{
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() throws ParseException {
        System.out.println("insert delivery id:");
        String deliveryId = reader.next();
        try {
            if (!DeliveryFunctions.isExist(deliveryId)) {
                System.out.println("delivery id does not exist");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        insertDestination(deliveryId);

    }

    public static String insertDestination(String deliveryId){
        System.out.println("insert destination id:");
        String dest = reader.next();
        try {
            if (!PlaceFunctions.isExist(dest)) {
                System.out.println("place id does not exist");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Place destination = PlaceFunctions.retrievePlace(dest);
        Order order = getOrder();
        DeliveryDestination d = new DeliveryDestination(destination, order);
        DeliveryDestinationFunctions.insertDeliveryDestination(d, deliveryId);
        return dest;
    }

    private static Order getOrder(){
        System.out.println("insert order number for this destination:");
        String orderNumber = reader.next();
        Map<String, Integer> orderItems = new HashMap<>();
        boolean hasFinished = false;
        int i=1;
        while (!hasFinished){
            System.out.println("insert item number " +i +" name:");
            String item = reader.next();
            System.out.println("insert quantity:");
            int quantity = reader.nextInt();
            orderItems.put(item, quantity);
            i++;

            if (!Utils.boolQuery("do you want to add another item? y/n"))
                hasFinished = true;
        }
        return new Order(orderNumber, orderItems);
    }

}
