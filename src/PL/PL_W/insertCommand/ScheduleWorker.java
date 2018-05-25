package PL.PL_W.insertCommand;

import BL.BL_W.RolesLogic;
import BL.BL_W.ShiftLogic;
import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Role;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;
import utils_W.Command;
import utils_W.Utils;

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

    @Override
    public void execute() {
        try {
            System.out.println("Enter shift date");
            String sDate = reader.next();
            Date d = new Date();
            try {
                d = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
            } catch (ParseException e) {
            }
            System.out.println("Enter shift day part - Morning/Evening");
            String sDayPart = reader.next();
            Shift.ShiftDayPart shiftDayPart = Shift.getDayPartByName(sDayPart);
            if (shiftDayPart == null) {
                System.out.println(sDayPart + "is not a valid day part\n");
                return;
            }
            Shift newShift = new Shift(new java.sql.Date(d.getTime()), shiftDayPart);
            if (!ShiftLogic.shiftExists(newShift)) {
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
        }
    }
}