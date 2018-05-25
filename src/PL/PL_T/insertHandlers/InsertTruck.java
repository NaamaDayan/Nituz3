package PL.PL_T.insertHandlers;

import BL.BL_T.Entities.Truck;
import BL.BL_T.Entities.TruckModel;
import BL.BL_T.EntitiyFunctions.ModelFunctions;
import BL.BL_T.EntitiyFunctions.TruckFunctions;
import PL.PL_T.Functor;

import java.util.Scanner;
public class InsertTruck extends Functor {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter truck id");
        String truckId = reader.next();
        try {
            if (TruckFunctions.isExist(truckId)) {
                System.out.println("truck already exists");
                return;
            }
            System.out.println("enter truck model id");
            String truckModel = reader.next();
            TruckModel model = ModelFunctions.retrieveModel(truckModel);
            if (model == null) {
                System.out.println("model does't exist");
                return;
            }
            System.out.println("enter truck color");
            String truckColor = reader.next();
            System.out.println("enter truck max weight");
            int maxWeight = reader.nextInt();
            System.out.println("enter truck neto weight");
            int netoWeight = reader.nextInt();
            while (maxWeight < netoWeight) {
                System.out.println("neto weight must be smaller or equal to max weight: " + maxWeight);
                netoWeight = reader.nextInt();
            }
            Truck t = new Truck(truckId, model, truckColor, netoWeight, maxWeight);
            TruckFunctions.insertTruck(t);
        } catch (Exception e) {
            System.out.println("error: insert failed");
        }
    }

}