import DAL.WorkersDatabase;
import Entities.BankAccount;
import Entities.Role;
import Entities.Shift;
import Entities.Worker;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class WorkersShiftsTests extends BasicTest {

    Worker worker1;
    Shift shift;
    Role role1;
    Role role2;

    @Test
    public void makeWorkerAvailableForShift() {
        CreateOutline();
    }

    @Test
    public void scheduleWorker() {
        CreateOutline();
        WorkersDatabase.scheduleWorker(shift , worker1 , role1);
        Map<Worker , Role> assignedWorkers = WorkersDatabase.selectAssignedWorkers(shift);
        assertEquals(1 , assignedWorkers.size());
        for (Worker w: assignedWorkers.keySet()){
            assertEquals(worker1.getId() , w.getId());
        }
    }

    public void CreateOutline() {
        try {
            String worker1ID = "1";
            String fName1 = "Lebron";
            String lName1 = "James";
            String eDate1 = "01/01/2000";
            BankAccount bankAccount1 = new BankAccount("100", "123456", "100");
            worker1 = new Worker(worker1ID, fName1, lName1, new Date((new SimpleDateFormat("dd/MM/yyyy").parse(eDate1)).getTime()), bankAccount1);
            WorkersDatabase.insertWorker(worker1);

            role1 = new Role("Cashier");
            role2 = new Role("Storekeeper");

            WorkersDatabase.insertWorker(worker1);
            WorkersDatabase.insertRole(role1);
            WorkersDatabase.insertRole(role2);

            WorkersDatabase.insertRoleForWorker(worker1, role1);
            WorkersDatabase.insertRoleForWorker(worker1, role2);

            shift = new Shift(new Date(new SimpleDateFormat("dd/MM/yyyy").parse(eDate1).getTime()), Shift.getDayPartByName("Morning"));

            WorkersDatabase.insertShift(shift);

            WorkersDatabase.makeWorkerAvailableForShift(worker1, shift);

            List<Worker> availableWorkers = WorkersDatabase.selectAvailableWorkers(shift);

            assertEquals(1, availableWorkers.size());

            assertEquals(worker1ID, availableWorkers.get(0).getId());

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
