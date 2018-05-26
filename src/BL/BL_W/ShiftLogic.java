package BL.BL_W;

import DAL.DAL_W.WorkersDatabase;
import BL.BL_W.Entities_W.Role;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;
import java.util.*;


public class ShiftLogic {
    public static boolean removeAvailableShifts(Worker worker, Shift shift) {
        return WorkersDatabase.removeAvailableShifts(worker, shift);
    }

    public static boolean insertWorkerToAvailableShift(Worker worker, Shift shift) {
        return WorkersDatabase.makeWorkerAvailableForShift(worker, shift);
    }

    public static boolean removeWorkerShift(Worker worker, Shift shift) {
        return WorkersDatabase.removeWorkerShifts(worker, shift);
    }

    public static boolean insertShift(Shift s) {
        return WorkersDatabase.insertShift(s);
    }


    public static Map<Worker, Role> selectAssignedWorkers(Shift shift) {
        return WorkersDatabase.selectAssignedWorkers(shift);
    }

    public static List<Worker> selectAvailableWorkers(Shift shift) {
        return WorkersDatabase.selectAvailableWorkers(shift);
    }

    public static boolean shiftExists(Shift newShift) throws SQLException {
        return WorkersDatabase.shiftExists(newShift);
    }

    public static boolean scheduleWorker(Shift newShift, Worker worker, Role role) {
        return WorkersDatabase.scheduleWorker(newShift, worker, role);
    }

    public static boolean isWorkerAssignedForShift(Worker worker, Shift shift){
        return WorkersDatabase.isWorkerAssignedForShift(worker,shift);
    }

    //todo: implement this
    public static boolean isStoreKeeperExistInShift(Shift shift){
        return WorkersDatabase.isStoreKeeperExistInShift(shift);
    }

    public static Worker getShiftManager(Shift newShift) {
        return WorkersDatabase.getShiftManager(newShift);
    }
}
