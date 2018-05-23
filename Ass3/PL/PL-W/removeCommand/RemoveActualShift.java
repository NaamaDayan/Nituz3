package removeCommand;

import ShiftLogic;
import WorkerLogic;
import Entities.Shift;
import Entities.Worker;
import utils.Command;

public class RemoveActualShift extends RemoveShift implements Command{

    @Override
    public void execute() {
        System.out.println("Enter worker's ID");
        String id = reader.next();
        Worker worker = WorkerLogic.getWorker(id);
        if(worker == null){
            System.out.println("Worker with id: "+id+" doesn't exist\n");
            return;
        }
        getShift();
        if(ShiftLogic.removeWorkerShift(worker, shiftToRemove))
            System.out.println("removed the assigning of the shift of worker "+worker.getId()+" successfully\n");
        else
            System.out.println("Worker with id: "+id+" isn't available in this shift\n");
    }




}
