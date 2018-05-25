package PL.PL_W.selectCommand;

import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Worker;
import PL.PL_W.Command;

import java.util.Scanner;

public class SelectWorker implements Command {
    static Scanner reader = new Scanner(System.in);
    @Override
    public void execute() {
        System.out.println("Enter worker's ID");
        String id = reader.next();
        Worker w = WorkerLogic.getWorker(id);
        if(w == null){
            System.out.println("worker's ID doesn't exist in the system\n");
            return;
        }
        else{
            System.out.println(w.toString()+"\n");
        }
    }
}
