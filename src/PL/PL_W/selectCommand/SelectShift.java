package PL.PL_W.selectCommand;

import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.ShiftLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SelectShift {
    private static Scanner reader = new Scanner(System.in);
    protected Shift desiredShift;

    public void getInformation(){
        System.out.println("Enter a Date: ");
        String date = reader.next();
        Date d = new Date();
        try {
            d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
        }
        System.out.println("Enter a shift - Morning/Evening");
        String shiftDayPart = reader.next();
        Shift.ShiftDayPart dayPart = Shift.getDayPartByName(shiftDayPart);
        if(dayPart==null){
            System.out.println("Illegal input\n");
            return;
        }
        System.out.println("enter source id");
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
        desiredShift = ShiftLogic.getShift(new java.sql.Date(d.getTime()), dayPart, place);
    }


}
