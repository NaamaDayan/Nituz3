package PL.PL_W.insertCommand;

import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import BL.BL_W.ShiftLogic;
import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;
import PL.PL_T.Utils;
import PL.PL_W.Command;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertWorkersAvailableShifts implements Command {

    static Scanner reader = new Scanner(System.in);
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void execute() {
        System.out.println("Enter Worker ID: ");
        String id = reader.next();
        Worker worker = WorkerLogic.getWorker(id);
        if (worker == null) {
            System.out.println("Worker with ID " + id + "Does not Exist in the system\n");
        } else {
            System.out.println("Enter a Date: ");
            java.sql.Date d = Utils.readDate(format);
            System.out.println("Enter a shift Morning/Evening");
            String shiftDayPart = reader.next();
            Shift.ShiftDayPart enumShiftDayPart;
            Shift.ShiftDayPart temp = Shift.getDayPartByName(shiftDayPart);
            if (temp != null)
                enumShiftDayPart = temp;
            else {
                System.out.println("Illegal input\n");
                return;
            }
            System.out.println("enter place id");
            String placeId = reader.next();
            try {
                if (!PlaceFunctions.isExist(placeId)) {
                    System.out.println("place does not exist");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Place place = PlaceFunctions.retrievePlace(placeId);
            Shift shift = ShiftLogic.getShift(d, temp, place);
            if (shift == null) {
                System.out.println("Shift doesn't exist , Please Create it before inserting workers\n");
                return;
            }
            if (!WorkerLogic.isWorkerNotAvailableForShift(worker, shift)) {
                System.out.println("Worker " + id + " is already available for this shift");
                return;
            }

            if (ShiftLogic.insertWorkerToAvailableShift(worker, shift))
                System.out.println("Worker " + id + " is now available for the " + enumShiftDayPart.name() + " shift at " + d + "\n");
        }
    }
}
