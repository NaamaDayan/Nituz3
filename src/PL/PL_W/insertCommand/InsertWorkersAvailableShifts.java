package PL.PL_W.insertCommand;

import BL.BL_W.ShiftLogic;
import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;
import utils_W.Command;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InsertWorkersAvailableShifts implements Command {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Enter Worker ID: ");
        String id = reader.next();
        Worker worker = WorkerLogic.getWorker(id);
        if(worker==null){
            System.out.println("Worker with ID "+id+ "Does not Exist in the system\n");
        }
        else{
            System.out.println("Enter a Date: ");
            String date = reader.next();
            Date d = new Date();
            try {
                d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
            }
            System.out.println("Enter a shift Morning/Evening");
            String shiftDayPart = reader.next();
            Shift.ShiftDayPart enumShiftDayPart;
            Shift.ShiftDayPart temp = Shift.getDayPartByName(shiftDayPart);
            if(temp!=null)
                enumShiftDayPart = temp;
            else{
                System.out.println("Illegal input\n");
                return;
            }
            Shift shift = new Shift(new java.sql.Date(d.getTime()), enumShiftDayPart);
            try {
                if(!ShiftLogic.shiftExists(shift)){
                    System.out.println("Shift doesn't exist , Please Create it before inserting workers\n");
                    return;
                }
            } catch (SQLException e) {
                return;
            }
            if(!WorkerLogic.isWorkerAvailableForShift(worker , shift)){
                System.out.println("Worker "+id+" is not available for this shift");
                return;
            }

            if(ShiftLogic.insertWorkerToAvailableShift(worker, shift))
                System.out.println("Worker "+id+" is now available for the "+enumShiftDayPart.name()+ " shift at "+date+"\n");
        }
    }
}
