package PL.PL_W.updateCommand;

import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Worker;
import PL.PL_W.Command;
import PL.PL_W.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UpdateWorker implements Command {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        Worker w = getInformation();
        if(w != null && WorkerLogic.updateWorker(w))
            System.out.println("info of worker: "+w.getId()+" updated Successfully\n");
        else
            System.out.println("Worker's update failed\n");
    }

    private Worker getInformation(){
        String toUpdate;
        System.out.println("Enter Worker ID:");
        String id = reader.next();
        Worker w = WorkerLogic.getWorker(id);
        if(w != null) {
            if(Utils.boolQuery("update first name? y/n")){
                System.out.println("enter first name");
                toUpdate = reader.next();
                w.setFname(toUpdate);
            }

            if(Utils.boolQuery("update last name? y/n")){
                System.out.println("enter last name");
                toUpdate = reader.next();
                w.setLname(toUpdate);
            }

            if(Utils.boolQuery("update employment date? y/n")){
                System.out.println("enter employment date");
                toUpdate = reader.next();
                Date d = new Date();
                try {
                    d = new SimpleDateFormat("dd/MM/yyyy").parse(toUpdate);
                } catch (ParseException e) {
                }
                w.setEmploymentDate(new java.sql.Date(d.getTime()));
            }

        }
        return w;
    }
}
