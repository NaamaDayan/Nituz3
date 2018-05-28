package BL.BL_W;

import DAL.DAL_W.WorkersDatabase;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;

import java.util.List;

public class WorkerLogic {
    public static boolean insertWorker(Worker worker){return WorkersDatabase.insertWorker(worker);}
    public static Worker getWorker(String id){return WorkersDatabase.getWorker(id);}
    public static boolean updateBankAccount(Worker worker){
        return WorkersDatabase.updateBankAccount(worker);
    }
    public static boolean updateWorker(Worker worker){
        return WorkersDatabase.updateWorker(worker);
    }
    public static boolean isWorkerNotAvailableForShift(Worker worker, Shift shift) {
        return WorkersDatabase.isWorkerNotAvailableForShift(worker, shift);
    }
    public static List<Worker> getShiftManagers(){
        return WorkersDatabase.getShiftManagers();
    }

    public static boolean isShiftManager(Worker shiftManager) {
        return WorkersDatabase.isShiftManager(shiftManager);
    }
}

