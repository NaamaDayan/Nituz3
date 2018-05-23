import DAL.WorkersDatabase;
import Entities.Shift;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShiftTests extends BasicTest {

    @Test
    public void simpleInsertShift(){
        try {
            Date sDate1 = new Date((new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2018")).getTime());
            Shift.ShiftDayPart dayPart1 = Shift.getDayPartByName("Morning");

            Date sDate2 = new Date((new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2018")).getTime());
            Shift.ShiftDayPart dayPart2 = Shift.getDayPartByName("Evening");

            Shift expectedShift1 = new Shift(sDate1 , dayPart1);
            Shift expectedShift2 = new Shift(sDate2 , dayPart2);
            WorkersDatabase.insertShift(expectedShift1);
            WorkersDatabase.insertShift(expectedShift2);

            List<Shift> actualAllShifts = WorkersDatabase.getAllCreatedShifts();

            assertEquals(2 , actualAllShifts.size());
            assertTrue(actualAllShifts.contains(expectedShift1));
            assertTrue(actualAllShifts.contains(expectedShift2));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertDuplicateShifts(){
        try {
            Date sDate1 = new Date((new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2018")).getTime());
            Shift.ShiftDayPart dayPart1 = Shift.getDayPartByName("Morning");

            Date sDate2 = new Date((new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2018")).getTime());
            Shift.ShiftDayPart dayPart2 = Shift.getDayPartByName("Morning");

            Shift inserted1 = new Shift(sDate1 , dayPart1);
            Shift inserted2 = new Shift(sDate2 , dayPart2);

            WorkersDatabase.insertShift(inserted1);
            WorkersDatabase.insertShift(inserted2);

            List<Shift> actualAllShifts = WorkersDatabase.getAllCreatedShifts();

            assertEquals(1 , actualAllShifts.size());
            assertTrue(actualAllShifts.contains(inserted1));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
