package insertCommand;

import WorkerLogic;
import Entities.BankAccount;
import Entities.Worker;
import utils.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InsertWorker implements Command {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        Worker worker = getInformation();
        if(worker!=null) {
            if (WorkerLogic.insertWorker(worker))
                System.out.println("Worker " + worker.getId() + " Inserted Successfully\n");
            else
                System.out.println("Worker Details Insertion Failed\n");
        }
    }

    private Worker getInformation(){
        System.out.println("Enter Worker ID:");
        String id = reader.next();
        if(WorkerLogic.getWorker(id)!=null) {
            System.out.println("Worker with id "+ id+ " already exists\n");
            return null;
        }
        System.out.println("Enter First Name:");
        String fname = reader.next();
        System.out.println("Enter Last Name:");
        String lname = reader.next();
        System.out.println("Enter Worker's Employment Date:");
        String employmentDate = reader.next();
        System.out.println("Bank Account information:");
        System.out.println("Enter Bank code:");
        String bankCode = reader.next();
        System.out.println("Enter Branch Number");
        String branchNum = reader.next();
        System.out.println("Enter Account Number");
        String accountNum = reader.next();
        BankAccount bankAccount = new BankAccount(bankCode , branchNum , accountNum);
        Date d = new Date();
        try {
            d = new SimpleDateFormat("dd/MM/yyyy").parse(employmentDate);
        } catch (ParseException e) {
        }
        return new Worker(id, fname, lname,new java.sql.Date(d.getTime()), bankAccount);
    }
}
