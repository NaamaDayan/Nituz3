package EntitiyFunctions;

import BL.Entities.*;
import DAL.*;

import java.sql.SQLException;

/**
 * Created by Naama on 21/04/2018.
 */
public class DeliveryFunctions {

    public static void insertDelivery(Delivery delivery) {
        Deliveries.insertDeivery(delivery.getId(), delivery.getLeavingDate(), delivery.getLeavingTime(),
               delivery.getTruck().getId(), delivery.getDriver().getId(), delivery.getSourcePlace().getId());
    }

    public static Delivery retrieveDelivery(String id){
        return Deliveries.retrieveDelivery(id);
    }

    public static void removeDelivery(String id) throws SQLException, ClassNotFoundException {
        Delivery d = Deliveries.retrieveDelivery(id);
        Deliveries.removeDelivery(id);
        for (DeliveryDestination ds: d.getDestinations()) {
            DeliveryDestinations.removeDeliveryDestination(id, ds.getDestination().getId());
        }
    }

    public static boolean isExist(String id) throws Exception {
        return ErrorsHandler.isDeliveryExist(id);
    }

    public static boolean isDriverSuitableForTruck(Driver driver, Truck truck){
        return Deliveries.DoesDriverHaveLicense(driver.getId(), truck.getModel());
    }

    public static void updateDelivery(Delivery d) throws SQLException, ClassNotFoundException {
        Deliveries.updateDelivery(d);
    }
}
