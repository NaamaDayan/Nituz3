import BL.WorkerLogic;
import DAL.WorkersDatabase;
import Entities.BankAccount;
import Entities.Role;
import Entities.Worker;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkersRolesTests extends BasicTest {

    @Test
    public void insertRolesToWorkers() {
        try {
            String worker1ID = "1";
            String fName1 = "a";
            String lName1 = "b";
            String eDate1 = "12/12/2012";
            BankAccount bankAccount1 = new BankAccount("1", "2", "3");
            Worker worker1 = new Worker(worker1ID, fName1, lName1, new Date((new SimpleDateFormat("dd/MM/yyyy").parse(eDate1)).getTime()), bankAccount1);

            String worker2ID = "2";
            String fName2 = "b";
            String lName2 = "c";
            String eDate2 = "28/07/2015";
            BankAccount bankAccount2 = new BankAccount("10", "20", "30");
            Worker worker2 = new Worker(worker2ID, fName2, lName2, new Date((new SimpleDateFormat("dd/MM/yyyy").parse(eDate2)).getTime()), bankAccount2);

            Role role1 = new Role("Wizard");
            Role role2 = new Role("Head Of Potatoes");
            Role role3 = new Role("m&m expert");

            WorkersDatabase.insertRole(role1);
            WorkersDatabase.insertRole(role2);
            WorkersDatabase.insertRole(role3);

            WorkersDatabase.insertWorker(worker1);
            WorkersDatabase.insertWorker(worker2);

            WorkersDatabase.insertRoleForWorker(worker1, role1);
            WorkersDatabase.insertRoleForWorker(worker1, role2);

            WorkersDatabase.insertRoleForWorker(worker2, role2);
            WorkersDatabase.insertRoleForWorker(worker2, role3);

            assertEquals(3, WorkersDatabase.selectAllRoles().size());
            Worker actualWorker1 = WorkersDatabase.getWorker(worker1ID);
            Worker actualWorker2 = WorkersDatabase.getWorker(worker2ID);

            assertEquals(2, actualWorker1.getRoles().size());
            assertEquals(2, actualWorker2.getRoles().size());

            assertTrue(actualWorker1.getRoles().contains(role1));
            assertTrue(actualWorker1.getRoles().contains(role2));
            assertTrue(actualWorker2.getRoles().contains(role2));
            assertTrue(actualWorker2.getRoles().contains(role3));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertDuplicateRolesForWorker() {
        try {
            String worker1ID = "1";
            String fName1 = "a";
            String lName1 = "b";
            String eDate1 = "12/12/2012";
            BankAccount bankAccount1 = new BankAccount("1", "2", "3");
            Worker worker1 = new Worker(worker1ID, fName1, lName1, new Date((new SimpleDateFormat("dd/MM/yyyy").parse(eDate1)).getTime()), bankAccount1);

            WorkersDatabase.insertWorker(worker1);

            Role role1 = new Role("FireMan");
            Role role2 = new Role("WaterMan");
            Role role3 = new Role("AirMan");
            Role role4 = new Role("EarthMan");
            List<Role> roles = new ArrayList<>();
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);
            roles.add(role4);
            Role role5 = new Role("FireMan");
            Role role6 = new Role("WaterMan");

            for (Role r:roles) {
                WorkersDatabase.insertRole(r);
                WorkersDatabase.insertRoleForWorker(worker1 , r);
            }
            WorkersDatabase.insertRoleForWorker(worker1 , role5);
            WorkersDatabase.insertRoleForWorker(worker1 , role6);

            Worker actualWorker1 = WorkersDatabase.getWorker(worker1ID);

            assertTrue(actualWorker1.getRoles().containsAll(roles));
            assertEquals(roles.size() , actualWorker1.getRoles().size());




        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
