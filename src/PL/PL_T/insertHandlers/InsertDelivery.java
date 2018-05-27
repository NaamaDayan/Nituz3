package PL.PL_T.insertHandlers;


import BL.BL_T.Entities.*;
import BL.BL_T.EntitiyFunctions.*;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;
import BL.BL_W.RolesLogic;
import BL.BL_W.ShiftLogic;
import BL.BL_W.WorkerLogic;
import PL.Functor;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

import PL.PL_T.Utils;

public class InsertDelivery extends Functor {

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
            if (DeliveryFunctions.isExist(deliveryId)) {
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
        try {
            Time morning = new Time(new Time(Hourformat.parse("04:00").getTime()).getTime());
            Time evening = new Time(new Time(Hourformat.parse("15:59").getTime()).getTime());
            String shiftDayPart;
            if (leavingHour.before(morning) && leavingHour.after(evening))
                shiftDayPart = "Evening";
            else
                shiftDayPart = "Morning";
            Shift.ShiftDayPart enumShiftDayPart = Shift.getDayPartByName(shiftDayPart);
            Shift.ShiftDayPart temp = Shift.getDayPartByName(shiftDayPart);
            System.out.println("shift day part: " + shiftDayPart);
            System.out.println("enter source id");
            String placeId = reader.next();
            if (!PlaceFunctions.isExist(placeId)) {
                System.out.println("place does not exist");
                return;
            }
            Place place = PlaceFunctions.retrievePlace(placeId);
            Shift shift = ShiftLogic.getShift(leavingDate, enumShiftDayPart, place);
        if (!PlaceFunctions.isPlaceHasShiftInSpecifiedTime(place, enumShiftDayPart, leavingDate)) {
            System.out.println("there is no such shift in the specified place");
            return;
        }
            System.out.println("enter truck id");
            String truckId = reader.next();
            try {
                if (!TruckFunctions.isExist(truckId)) {
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
            try {
                if (WorkerLogic.getWorker(driverId) == null) {
                    System.out.println("Worker " + driverId + " does not exist");
                    return;
                }
                Driver driver = DriverFunctions.getDriver(driverId);
                if (driver == null) {
                    System.out.println("Worker " + driverId + " is not a driver");
                    return;
                }
                if (!DeliveryFunctions.isDriverSuitableForTruck(driver, truck)) {
                    System.out.println("driver cannot drive this truck!");
                    return;
                }
                if (!ShiftLogic.isWorkerAssignedForShift(driver, shift)) {
                    System.out.println("driver is not register to a shift in the given date");
                    return;
                }
                if (!WorkerLogic.isWorkerAvailableForShift(driver, shift)) {
                    System.out.println("the driver is not available for the shift");
                    return;
                }
                //insert the delivery
                Delivery delivery = new Delivery(deliveryId, leavingDate, leavingHour, truck, driver, place, new LinkedList<>());
                DeliveryFunctions.insertDelivery(delivery);

                String firstDest = InsertDeliveryDestination.insertDestination(deliveryId); //insert first dest
                if (firstDest == null) { //not existing place
                    DeliveryFunctions.removeDelivery(deliveryId);
                    return;
                }
                Place destPlace = PlaceFunctions.retrievePlace(firstDest);
                if (!PlaceFunctions.isPlaceHasShiftInSpecifiedTime(destPlace, enumShiftDayPart, leavingDate)) {
                    System.out.println("there is no such shift in the specified place");
                    DeliveryFunctions.removeDelivery(deliveryId);
                    return;
                }
                if (!RolesLogic.containsStoreKeeper(ShiftLogic.getRolesOfShift(shift))) {
                    System.out.println("there is no storeKeeper registered in the shift");
                    DeliveryFunctions.removeDelivery(deliveryId);
                    return;
                }
                while (Utils.boolQuery("do you want to add another destination? y/n")) {
                    String dest = InsertDeliveryDestination.insertDestination(deliveryId);
                    if (dest == null) {
                        DeliveryFunctions.removeDelivery(deliveryId);
                        return;
                    }
                    destPlace = PlaceFunctions.retrievePlace(dest);
                    if (!PlaceFunctions.isPlaceHasShiftInSpecifiedTime(destPlace, enumShiftDayPart, leavingDate)) {
                        System.out.println("there is no such shift in the specified place");
                        DeliveryFunctions.removeDelivery(deliveryId);
                        return;
                    }
                    if (!RolesLogic.containsStoreKeeper(ShiftLogic.getRolesOfShift(shift))) {
                        System.out.println("there is no storeKeeper registered in the shift");
                        DeliveryFunctions.removeDelivery(deliveryId);
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("error: insertion failed");
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
