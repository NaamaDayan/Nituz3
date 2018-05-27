package PL.PL_W.insertCommand;
import BL.BL_W.Entities_W.Worker;
import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import BL.BL_W.ShiftLogic;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.WorkerLogic;
import PL.PL_W.Command;
import PL.PL_W.Utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateShift implements Command {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        try {
            System.out.println("Enter new shift date");
            String sDate = reader.next();
            Date d = new Date();
            try {
                d = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
            } catch (ParseException e) {
            }
            System.out.println("Enter shift day part - Morning/Evening");
            String sDayPart = reader.next();
            Shift.ShiftDayPart shiftDayPart = Shift.getDayPartByName(sDayPart);
            if (shiftDayPart == null)
                System.out.println(sDayPart + "is not a valid day part\n");
            else {
                System.out.println("enter source id");
                String placeId = reader.next();
                if (!PlaceFunctions.isExist(placeId)){
                    System.out.println("place does not exist");
                    return;
                }
                Place place = PlaceFunctions.retrievePlace(placeId);
                Shift newShift = new Shift(new java.sql.Date(d.getTime()), shiftDayPart, place);
                if (ShiftLogic.shiftExists(newShift))
                    System.out.println("Specific shift already exists\n");
                else {
                    System.out.println("Enter shift manager");
                    System.out.println(Utils.projectShiftManagers(WorkerLogic.getShiftManagers())+"\n");
                    String shiftManagerId = reader.next();
                    Worker shiftManager = WorkerLogic.getWorker(shiftManagerId);
                    if(shiftManager == null) {
                        System.out.println("worker doesn't exist in the system");
                        return;
                    }
                    if(!WorkerLogic.isShiftManager(shiftManager)) {
                        System.out.println("worker " + shiftManagerId + " is not a shift manager");
                        return;
                    }

                    newShift = new Shift(new java.sql.Date(d.getTime()), shiftDayPart, shiftManager);
                    if(ShiftLogic.insertShift(newShift))
                        System.out.println("new shift added Successfully\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting new shift\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
