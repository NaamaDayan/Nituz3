package BL.BL_T.Entities;

import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;

import java.util.List;

public class Place {

    private String id;
    private String address;
    private String phoneNumber;
    private Worker contactWorker;
    

    public Place(String id, String address, String phoneNumber, Worker contactName) {
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contactWorker = contactName;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactName=" + contactWorker +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Worker getContactWorker() {
        return contactWorker;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setContactWorker(Worker contactWorker) {
        this.contactWorker = contactWorker;
    }
}
