package BL.BL_T.Entities;

import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;

import java.util.List;

public class Place {

    private String id;
    private String address;
    private String phoneNumber;
    private Worker contactName;

    public Place(String id, String address, String phoneNumber, Worker contactName) {
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactName=" + contactName +
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

    public Worker getContactName() {
        return contactName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setContactName(Worker contactName) {
        this.contactName = contactName;
    }
}
