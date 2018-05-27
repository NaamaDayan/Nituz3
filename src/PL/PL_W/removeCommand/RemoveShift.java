package PL.PL_W.removeCommand;
import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import BL.BL_W.ShiftLogic;
import BL.BL_W.Entities_W.Shift;
import PL.PL_T.Utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RemoveShift {

    static Scanner reader = new Scanner(System.in);
    protected Shift shiftToRemove;
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


    public void getShift() {
            System.out.println("Enter available date");
            java.sql.Date d = Utils.readDate(format);
            System.out.println("Enter day part: Morning/Evening");
            String dayPartStr = reader.next();
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
            Shift temp = ShiftLogic.getShift(d, dayPart, place);
            if(temp != null)
                shiftToRemove = temp;
            else
                System.out.println("Shift doesn't exist\n");

    }
}
