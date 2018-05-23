package Entities;

/**
 * Created by Naama on 23/04/2018.
 */
public class DeliveryDestination {
    private Place destination;
    private Order order;

    public DeliveryDestination(Place destination, Order order) {
        this.destination = destination;
        this.order = order;
    }


    public Place getDestination() {
        return destination;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Delivery Destination{" +
                "destination ='" + destination + '\'' +
                ", order ='" + order + '\'' +
                '}';
    }
}
