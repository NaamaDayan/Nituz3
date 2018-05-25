package PL.PL_W.selectCommand;

import BL.BL_W.Entities_W.Shift;

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
        desiredShift = new Shift(new java.sql.Date(d.getTime()), dayPart);
    }


}
