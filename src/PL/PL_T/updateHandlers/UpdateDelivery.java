package PL.PL_T.updateHandlers;

import BL.BL_T.Entities.Delivery;
import BL.BL_T.Entities.Truck;
import BL.BL_T.EntitiyFunctions.*;
import BL.BL_W.Entities_W.Worker;
import BL.BL_W.WorkerLogic;
import PL.PL_T.Functor;
import PL.PL_T.Utils;
import PL.PL_T.insertHandlers.InsertDeliveryDestination;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class UpdateDelivery extends Functor {
    static Scanner reader = new Scanner(System.in);
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static SimpleDateFormat Hourformat = new SimpleDateFormat("hh:mm");

    @Override
    public void execute() {
        String idToUpdate;
        System.out.println("enter delivery's ID");
        idToUpdate = reader.next();
        Delivery d;
        Truck truck = null;
        try {
            if (!DeliveryFunctions.isExist(idToUpdate)) {
                System.out.println("error: ID doesn't exist");
                return;
            }
            else {
                d = DeliveryFunctions.retrieveDelivery(idToUpdate);
            }
        } catch (Exception e) {
            System.out.println("error: update failed");
            return;
        }
        if (Utils.boolQuery("update leaving date? y/n")) {
            System.out.println("enter leaving date in format: 'dd.MM.yyyy'");
            java.sql.Date leavingDate = Utils.readDate(format);
            d.setLeavingDate(leavingDate);
        }
        if (Utils.boolQuery("update leaving time? y/n")) {
            System.out.println("enter leaving time");
            java.sql.Time leavingTime = null;
            try {
                leavingTime = Utils.readHour(Hourformat);
            } catch (ParseException e) {
                System.out.println("error: update failed");
                return;
            }
            d.setLeavingTime(leavingTime);
        }
        if (Utils.boolQuery("update truck? y/n")) {
            System.out.println("enter the new truck's id");
            String truckId = reader.next();
            try {
                if (!TruckFunctions.isExist(truckId)){
                    System.out.println("truck does not exists");
                    return;
                }
            } catch (Exception e) {
                System.out.println("error: insertion failed");
                return;
            }
            truck = TruckFunctions.retrieveTruck(truckId);
            d.setTruck(truck);
        }
        if (Utils.boolQuery("update driver? y/n")) {
            System.out.println("enter driver id");
            String driverId = reader.next();
            Worker driver = WorkerLogic.getWorker(driverId);
            try {
                if (driver == null){
                    System.out.println("driver does not exist");
                    return;
                }
            } catch (Exception e) {
                System.out.println("error: insertion failed");
                return;
            }
            if (!DeliveryFunctions.isDriverSuitableForTruck(driver, truck)){
                System.out.println("driver cannot drive this truck!");
                return;
            }
            d.setDriver(driver);
        }
        if (Utils.boolQuery("update source id? y/n")) {
            System.out.println("enter source id");
            String sourceId = reader.next();
            try {
                if (!PlaceFunctions.isExist(sourceId)){
                    System.out.println("source does not exist");
                    return;
                }
            } catch (Exception e) {
                System.out.println("error: insertion failed");
                return;
            }
            d.setSourcePlace(PlaceFunctions.retrievePlace(sourceId));
        }
        int i = 0;
        boolean cont = true;
        while (cont && Utils.boolQuery("do you want to update destination number: " + (i+1) +"? y/n")){
            String destId = InsertDeliveryDestination.insertDestination(d.getId());
            cont = ++i <= d.getDestinations().size();
        }
        try {
            DeliveryFunctions.updateDelivery(d);
        } catch (Exception e) {
            System.out.println("error: update failed");
        }
    }
}
