package PL.insertCommand;

import BL.RolesLogic;
import BL.ShiftLogic;
import Entities.Role;
import Entities.Shift;
import Entities.Worker;
import utils.Command;

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
                Shift newShift = new Shift(new java.sql.Date(d.getTime()), shiftDayPart);
                if (ShiftLogic.shiftExists(newShift))
                    System.out.println("Specific shift already exists\n");
                else {
                    if(ShiftLogic.insertShift(newShift))
                        System.out.println("new shift added Successfully\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting new shift\n");
        }
    }
}
