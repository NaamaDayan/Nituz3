package Entities;
import Drivers;

import java.util.List;

public class Driver{

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<LicenseTypeForTruck> licenses;

    public Driver(String id, String firstName, String lastName, String phoneNumber, List<LicenseTypeForTruck> licenses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.licenses = licenses;
    }

    @Override
    public String toString() {
        String licensesString;
        if (licenses!=null) {
            licensesString= "{";
            for (LicenseTypeForTruck license : licenses)
                licensesString = licensesString + license.toString() + ", ";
            if (licensesString.length() > 1)
                licensesString = licensesString.substring(0, licensesString.length() - 2);
            licensesString += "}";
        }
        else
            licensesString = "{}";
        return "Driver{" +
                "id ='" + id + '\'' +
                ", firstName ='" + firstName + '\'' +
                ", lastName ='" + lastName + '\'' +
                ", phoneNumber ='" + phoneNumber + '\'' +
                ", licenses ='" + licensesString + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
