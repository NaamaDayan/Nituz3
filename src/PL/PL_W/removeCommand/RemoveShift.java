package PL.PL_W.removeCommand;
import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import BL.BL_W.ShiftLogic;
import BL.BL_W.Entities_W.Shift;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RemoveShift {

    static Scanner reader = new Scanner(System.in);
    protected Shift shiftToRemove;

    public void getShift() {
            System.out.println("Enter date available");
            String dateAvailable = reader.next();
            System.out.println("Enter day part: Morning/Evening");
            String dayPartStr = reader.next();
            Date d = new Date();
            try {
                d = new SimpleDateFormat("dd/MM/yyyy").parse(dateAvailable);
            } catch (ParseException e) {
            }
            Shift.ShiftDayPart dayPart = Shift.getDayPartByName(dayPartStr);
            System.out.println("enter place id");
            String placeId = reader.next();
        try {
            if (!PlaceFunctions.isExist(placeId)){
                System.out.println("place does not exist");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Place place = PlaceFunctions.retrievePlace(placeId);
            Shift temp = ShiftLogic.getShift(new java.sql.Date(d.getTime()), dayPart, place);
            if(temp != null)
                shiftToRemove = temp;
            else
                System.out.println("Shift doesn't exist\n");

    }
}
