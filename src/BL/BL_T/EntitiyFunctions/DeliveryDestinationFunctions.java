package BL.BL_T.EntitiyFunctions;

import BL.BL_T.Entities.DeliveryDestination;
import DAL.DAL_T.DeliveryDestinations;
import DAL.DAL_T.ErrorsHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Naama on 21/04/2018.
 */
public class DeliveryDestinationFunctions {

    public static void removeDeliveryDestination(String deliveryId, String destId) throws SQLException, ClassNotFoundException {
        DeliveryDestinations.removeDeliveryDestination(deliveryId, destId);
    }

    public static void insertDeliveryDestination(DeliveryDestination d, String deliveryId){
        DeliveryDestinations.insertDeliveryDestination(deliveryId, d.getDestination().getId(), d.getOrder());
    }

    public static List<DeliveryDestination> retrieveDeliveryDestination(String deliveryId){
        return DeliveryDestinations.retrieveDeliveryDestinations(deliveryId);
    }

    public static boolean isExist(String deliveryId, String destId) throws Exception {
        return ErrorsHandler.isDeliveryDestinationExist(deliveryId, destId);
    }
}
