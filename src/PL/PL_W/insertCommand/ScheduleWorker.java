package PL.PL_W.insertCommand;

import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import BL.BL_W.RolesLogic;
import BL.BL_W.ShiftLogic;
import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Role;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;
import PL.PL_W.Command;
import PL.PL_W.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScheduleWorker implements Command {
    static Scanner reader = new Scanner(System.in);
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void execute() {
        try {
            System.out.println("enter place id");
            String placeId = reader.next();
            if (!PlaceFunctions.isExist(placeId)){
                System.out.println("place does not exist");
                return;
            }
            Place place = PlaceFunctions.retrievePlace(placeId);
            System.out.println("Enter shift date");
            java.sql.Date d = PL.PL_T.Utils.readDate(format);
            System.out.println("Enter shift day part - Morning/Evening");
            String sDayPart = reader.next();
            Shift.ShiftDayPart shiftDayPart = Shift.getDayPartByName(sDayPart);
            if (shiftDayPart == null) {
                System.out.println(sDayPart + "is not a valid day part\n");
                return;
            }
            Shift newShift = ShiftLogic.getShift(d, shiftDayPart, place);
            if (newShift == null) {
                System.out.println("Shift does not exist, Please insert the shift before scheduling workers\n");
                return;
            }
            System.out.println("Available workers for this shift:");
            System.out.println(Utils.projectAvailableWorkers(ShiftLogic.selectAvailableWorkers(newShift)) + "\n");
            System.out.println("Enter Worker ID");
            String id = reader.next();
            Worker worker = WorkerLogic.getWorker(id);
            if (worker == null) {
                System.out.println("Worker does not exist\n");
                return;
            }

            if(ShiftLogic.getShiftManager(newShift).equals(worker))
                System.out.println("this worker is already assigned as the manager of the shift");

            if (WorkerLogic.isWorkerAvailableForShift(worker, newShift)) {
                System.out.println("Worker is not available for the specific shift\n");
                return;
            }
            System.out.println("Assign role to worker");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Role role = new Role(br.readLine());
            if (!RolesLogic.roleExists(role)) {
                System.out.println("Role does not exists\n");
                return;
            }
            if(ShiftLogic.isWorkerAssignedForShift(worker, newShift)) {
                System.out.println("Worker is already assigned for this shift");
                return;
            }
            if (!ShiftLogic.scheduleWorker(newShift, worker, role))
                System.out.println("Error while scheduling worker\n");
            else
                System.out.println("Worker "+worker.getId()+" successfully scheduled to the "+newShift.getShiftDayPart()+" shift at "+new SimpleDateFormat("dd/MM/yyyy").format(newShift.getDate()));
        } catch (SQLException e) {
            System.out.println("Error while scheduling worker\n");
        } catch (IOException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
