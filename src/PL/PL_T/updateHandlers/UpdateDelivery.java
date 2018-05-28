package PL.PL_T.updateHandlers;

import BL.BL_T.Entities.Delivery;
import BL.BL_T.Entities.Driver;
import BL.BL_T.Entities.Place;
import BL.BL_T.Entities.Truck;
import BL.BL_T.EntitiyFunctions.*;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.ShiftLogic;
import PL.Functor;
import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Worker;
import PL.PL_T.Utils;
import PL.PL_T.insertHandlers.InsertDeliveryDestination;

import java.sql.Time;
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
            Driver driver = DriverFunctions.getDriver(driverId);
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
                Place place = PlaceFunctions.retrievePlace(sourceId);

                Time evening = null;
                try {
                    evening = new Time(new Time(Hourformat.parse("15:59").getTime()).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String shiftDayPart;
                if (d.getLeavingTime().after(evening))
                    shiftDayPart = "Evening";
                else
                    shiftDayPart = "Morning";

                Shift shift = ShiftLogic.getShift(d.getLeavingDate(), Shift.getDayPartByName(shiftDayPart), place);
                if (shift == null) {
                    System.out.println("there is no such shift in the specified place");
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
            e.printStackTrace();
            System.out.println("error: update failed");
        }
    }
}
