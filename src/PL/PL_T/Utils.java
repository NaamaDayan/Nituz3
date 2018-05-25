package PL.PL_T;

import java.sql.Time;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Utils {
    static Scanner reader = new Scanner(System.in);

    public static java.sql.Date readDate(SimpleDateFormat format){
        String dateString = reader.next();
        java.sql.Date sqlDate;
        java.util.Date date = format.parse(dateString, new ParsePosition(0));
        if (date!=null) {
            sqlDate = new java.sql.Date(date.getTime());
            while (sqlDate==null){
                System.out.println("insert legal date - the format is: dd.MM.yyyy");
                sqlDate = readDate(format);
            }
            return sqlDate;
        }
        else
            System.out.println("insert legal date - the format is: dd.MM.yyyy");
        return readDate(format);
    }

    public static Time readHour(SimpleDateFormat format) throws ParseException {
        String timeString = reader.next();
        Time sqlTime;
        long ms = format.parse(timeString).getTime();
        Time time = new Time(ms);
        if (time!=null) {
            sqlTime = new Time(time.getTime());
            while (sqlTime==null){
                System.out.println("insert legal time - the format is: h:mm");
                sqlTime = readHour(format);
            }
            return sqlTime;
        }
        else
            System.out.println("insert legal date - the format is: h:mm");
        return readHour(format);
    }

    public static boolean boolQuery(String input){
        System.out.println(input);
        String isLegal = reader.next();
        while (!isLegal.equals("y") && !isLegal.equals("n")){
            System.out.println("insert y or n only");
            isLegal = reader.next();
        }
        return isLegal.equals("y");
    }

}
