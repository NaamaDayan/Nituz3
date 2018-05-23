package BL;

import DAL.WorkersDatabase;
import Entities.Shift;
import Entities.Worker;

public class WorkerLogic {
    public static boolean insertWorker(Worker worker){return WorkersDatabase.insertWorker(worker);}
    public static Worker getWorker(String id){return WorkersDatabase.getWorker(id);}
    public static boolean updateBankAccount(Worker worker){
        return WorkersDatabase.updateBankAccount(worker);
    }
    public static boolean updateWorker(Worker worker){
        return WorkersDatabase.updateWorker(worker);
    }
    public static boolean isWorkerAvailableForShift(Worker worker, Shift shift){
        return WorkersDatabase.isWorkerAvailableForShift(worker,shift);}
}
