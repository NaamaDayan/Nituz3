package BL.BL_W.Entities_W;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Worker {

    private String id;
    private String fname;
    private String lname;
    private String phoneNum;
    private Date employmentDate;
    private BankAccount bankAccount;
    private List<Role> roles;

    public Worker(String id, String fname, String lname, String phoneNum, Date employmentDate) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phoneNum = phoneNum;
        this.employmentDate = employmentDate;
        this.bankAccount = null;
        this.roles = new ArrayList<>();
    }

    public Worker(String id, String fname, String lname, String phoneNum, Date employmentDate, BankAccount bankAccount) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phoneNum = phoneNum;
        this.employmentDate = employmentDate;
        this.bankAccount = bankAccount;
        this.roles = new ArrayList<>();
    }

    public Worker(String id, String fname, String lname, String phoneNum, Date employmentDate, BankAccount bankAccount, List<Role> roles) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phoneNum = phoneNum;
        this.employmentDate = employmentDate;
        this.bankAccount = bankAccount;
        this.roles = roles;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Worker))
            return false;
        else {
            Worker worker = (Worker) obj;
            return (this.id.equals(worker.id) && this.fname.equals(worker.fname) && this.lname.equals(worker.lname) && this.phoneNum.equals(worker.phoneNum) &&
                    this.bankAccount.equals(worker.bankAccount) && this.employmentDate.toString().equals(worker.employmentDate.toString()) &&
                    this.roles.equals(worker.roles));
        }
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        String rolesSTR = projectRoles();
        if(roles.isEmpty())
            return "ID="+id + "\t" +"FName="+ fname + "\t" + "LName="+lname + "\t" + "Phone number="+phoneNum + "\t" + "EmploymentDate="+ new SimpleDateFormat("dd/MM/yyyy").format(employmentDate) + "\t" + bankAccount.toString();
        return "ID="+id + "\t" +"FName="+ fname + "\t" + "LName="+lname + "\t" + "EmploymentDate="+ new SimpleDateFormat("dd/MM/yyyy").format(employmentDate) + "\t" + bankAccount.toString() + "\t" +"Roles="+ rolesSTR;
    }

    public String projectRoles() {
        String ret = "";
        int size = roles.size();
        for (int i = 0; i < size; i++) {
            String role = roles.get(i).getRole();
            if (i == size - 1)
                ret += role + ".";
            else
                ret += role + ", ";
        }
        return ret;
    }

    public boolean isDriver(){
        for (Role r: roles) {
            if (r.getRole().equals("Driver"))
                return true;
        }
        return false;
    }
}
