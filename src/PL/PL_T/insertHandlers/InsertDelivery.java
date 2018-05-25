package PL.PL_T.insertHandlers;


import BL.BL_T.Entities.*;
import BL.BL_T.EntitiyFunctions.*;
import BL.BL_W.Entities_W.Worker;
import BL.BL_W.WorkerLogic;
import PL.PL_T.Functor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

import PL.PL_T.Utils;

public class InsertDelivery extends Functor{

    static Scanner reader = new Scanner(System.in);
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static SimpleDateFormat Hourformat = new SimpleDateFormat("h:mm");


    @Override
    public void execute() {
        java.sql.Date leavingDate;
        java.sql.Time leavingHour;
        System.out.println("enter delivery id");
        String deliveryId = reader.next();
        try {
            if (DeliveryFunctions.isExist(deliveryId)){
                System.out.println("delivery already exists");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("enter date in format: 'dd.MM.yyyy' ");
        leavingDate = Utils.readDate(format);
        System.out.println("enter hour in format: 'hh:mm' ");
        try {
            leavingHour = Utils.readHour(Hourformat);
        } catch (ParseException e) {
            System.out.println("error: insertion failed");
            return;
        }
        System.out.println("enter truck id");
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
        Truck truck = TruckFunctions.retrieveTruck(truckId);
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
        System.out.println("enter source id");
        String placeId = reader.next();
        try {
            if (!PlaceFunctions.isExist(placeId)){
                System.out.println("place does not exist");
                return;
            }
        Place place = PlaceFunctions.retrievePlace(placeId);
        //insert the delivery
        Delivery delivery = new Delivery(deliveryId, leavingDate, leavingHour, truck, driver, place, new LinkedList<>());
        DeliveryFunctions.insertDelivery(delivery);

        String firstDest = InsertDeliveryDestination.insertDestination(deliveryId); //insert first dest
        if (firstDest==null) { //not existing place
            DeliveryFunctions.removeDelivery(deliveryId);
            return;
        }
        while (Utils.boolQuery("do you want to add another destination? y/n")) {
            String dest = InsertDeliveryDestination.insertDestination(deliveryId);
            if (dest == null){
                DeliveryFunctions.removeDelivery(deliveryId);
                return;
            }
        }
        } catch (Exception e) {
            System.out.println("error: insertion failed");
            return;
        }
    }
}
