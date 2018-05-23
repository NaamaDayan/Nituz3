package updateCommand;

import WorkerLogic;
import Entities.Worker;
import utils.Utils;


import java.util.Scanner;

public class UpdateBankAccount implements utils.Command {
    static Scanner reader = new Scanner(System.in);


    @Override
    public void execute() {
        Worker w = getInformation();
        if(w != null && WorkerLogic.updateBankAccount(w))
            System.out.println("Bank account of worker: "+w.getId()+" updated Successfully\n");
        else
            System.out.println("Worker's update failed\n");

    }

    private Worker getInformation(){
        String toUpdate;
        System.out.println("Enter Worker ID:");
        String id = reader.next();
        Worker w = WorkerLogic.getWorker(id);
        if(w != null) {
            if(Utils.boolQuery("update bank code? y/n")){
                System.out.println("Enter bank code");
                toUpdate = reader.next();
                w.getBankAccount().setBankCode(toUpdate);
            }

            if(Utils.boolQuery("update account number? y/n")){
                System.out.println("Enter account number");
                toUpdate = reader.next();
                w.getBankAccount().setAccountNumber(toUpdate);
            }

            if(Utils.boolQuery("update branch number? y/n")){
                System.out.println("Enter branch number");
                toUpdate = reader.next();
                w.getBankAccount().setBranchNumber(toUpdate);
            }
        }
        return w;

    }
}
