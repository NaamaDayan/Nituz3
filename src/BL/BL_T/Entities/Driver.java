package BL.BL_T.Entities;

import BL.BL_W.Entities_W.BankAccount;
import BL.BL_W.Entities_W.Role;
import BL.BL_W.Entities_W.Worker;

import java.sql.Date;
import java.util.List;

public class Driver extends Worker{



    private List<LicenseTypeForTruck> licenses;

    public Driver(String id, String fname, String lname, String phoneNum, Date employmentDate) {
        super(id, fname, lname, phoneNum, employmentDate);
    }

    public Driver(String id, String fname, String lname, String phoneNum, Date employmentDate, BankAccount bankAccount) {
        super(id, fname, lname, phoneNum, employmentDate, bankAccount);
    }

    public Driver(String id, String fname, String lname, String phoneNum, Date employmentDate, BankAccount bankAccount, List<Role> roles, List<LicenseTypeForTruck> licenses) {
        super(id, fname, lname, phoneNum, employmentDate, bankAccount, roles);
        this.licenses = licenses;
    }

    public void setLicenses(List<LicenseTypeForTruck> licenses) {
        this.licenses = licenses;
    }
}
