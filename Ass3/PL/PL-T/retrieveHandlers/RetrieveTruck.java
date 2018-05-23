package retrieveHandlers;

import Entities.Truck;
import EntitiyFunctions.TruckFunctions;
import Functor;

import java.util.Scanner;

public class RetrieveTruck extends Functor {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter truck id");
        String id = reader.next();
        try {
            if (!TruckFunctions.isExist(id)){
                System.out.println("truck does not exist");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Truck truck = TruckFunctions.retrieveTruck(id);
        System.out.println(truck.toString());
    }
}
