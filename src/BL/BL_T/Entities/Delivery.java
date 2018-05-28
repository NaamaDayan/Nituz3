package BL.BL_T.Entities;

import BL.BL_W.Entities_W.Worker;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Delivery {
    private String id;
    private Date leavingDate;
    private Time leavingTime;
    private Truck truck;
    private Driver driver;
    private Place sourcePlace;
    private List<DeliveryDestination> destinations;

    public Delivery(String id, Date date, Time hour, Truck truckId, Driver driverId, Place source, List<DeliveryDestination> destinations) {
        this.id = id;
        this.leavingDate = date;
        this.leavingTime = hour;
        this.truck= truckId;
        this.driver= driverId;
        this.sourcePlace = source;
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        String dests = "{";
        for (DeliveryDestination dest: destinations)
            dests= dests+ dest.toString()+ ", ";
        if (dests.length()>1)
            dests = dests.substring(0, dests.length()-2);
        dests += "}";
        return "Delivery{" +
                "id='" + id + '\'' +
                ", leavingDate=" + leavingDate +
                ", leavingTime=" + leavingTime +
                ", truckId='" + truck.toString()+ '\'' +
                ", driverId='" + driver.toString() + '\'' +
                ", sourceId='" + sourcePlace.toString() + '\'' +
                ", destinations ='" + dests+ '\''+
                '}';
    }

    public String getId() {
        return id;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public Time getLeavingTime() {
        return leavingTime;
    }

    public Truck getTruck() {
        return truck;
    }

    public Worker getDriver() {
        return driver;
    }

    public Place getSourcePlace() {
        return sourcePlace;
    }

    public List<DeliveryDestination> getDestinations() {
        return destinations;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public void setLeavingTime(Time leavingTime) {
        this.leavingTime = leavingTime;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setSourcePlace(Place sourcePlace) {
        this.sourcePlace = sourcePlace;
    }

    public void setDestinations(List<DeliveryDestination> destinations) {
        this.destinations = destinations;
    }
}
