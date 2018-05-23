package PL.removeCommand;

import BL.ShiftLogic;
import BL.WorkerLogic;
import Entities.Worker;
import utils.Command;

public class RemoveAvailableShift extends RemoveShift implements Command {

    @Override
    public void execute() {
        System.out.println("Enter worker's ID");
        String id = reader.next();
        Worker worker = WorkerLogic.getWorker(id);
        if(worker == null){
            System.out.println("Worker with id: "+id+"doesn't exist\n");
            return;
        }
        getShift();
        if (ShiftLogic.removeAvailableShifts(worker,shiftToRemove))
            System.out.println("removed the availability of the shift of worker " + worker.getId() + " successfully\n");
        else
            System.out.println("remove of the shift has failed\n");
    }


}
