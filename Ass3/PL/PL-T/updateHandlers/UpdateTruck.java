package updateHandlers;

import Entities.Truck;
import Entities.TruckModel;
import EntitiyFunctions.ModelFunctions;
import EntitiyFunctions.TruckFunctions;
import Functor;
import Utils;

import java.util.Scanner;


public class UpdateTruck extends Functor {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        String idToUpdate;
        String newStringField;
        int newNumericField;
        System.out.println("enter Truck's ID");
        idToUpdate = reader.next();
        Truck t;
        try {
            if (!TruckFunctions.isExist(idToUpdate)) {
                System.out.println("error: ID doesn't exist");
                return;
            }
            else {
                t = TruckFunctions.retrieveTruck(idToUpdate);
            }
            if (Utils.boolQuery("update model? y/n")) {
                System.out.println("enter truck model id");
                newStringField = reader.next();
                TruckModel model = ModelFunctions.retrieveModel(newStringField);
                if (model == null) {
                    System.out.println("model does't exist");
                    return;
                }
                t.setModel(model);
            }
            if (Utils.boolQuery("update color? y/n")) {
                System.out.println("enter color");
                newStringField = reader.next();
                t.setColor(newStringField);
            }
            if (Utils.boolQuery("update max weight? y/n")) {
                System.out.println("enter max weight");
                newNumericField = reader.nextInt();
                t.setMaxWeight(newNumericField);
            }
            if (Utils.boolQuery("update neto weight? y/n")) {
                System.out.println("enter neto weight");
                newNumericField = reader.nextInt();
                while (t.getMaxWeight() < newNumericField) {
                    System.out.println("neto weight must be smaller or equal to max weight: " + t.getMaxWeight());
                    newNumericField = reader.nextInt();
                }
                t.setNetoWeight(newNumericField);
            }
            TruckFunctions.updateTruck(t);
        } catch (Exception e) {
            System.out.println("error: update failed");
        }
    }
}
