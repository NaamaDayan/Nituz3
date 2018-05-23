import DAL.WorkersDatabase;
import Entities.Role;
import Entities.Worker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RoleTest extends BasicTest {

    @Test
    public void insertNewRoles(){
        List<Role> expectedRoles = new LinkedList<>();

        Role role1 = new Role("Cashier");
        Role role2 = new Role("Delivery Person");
        Role role3 = new Role("Storekeeper");

        expectedRoles.add(role1);
        expectedRoles.add(role2);
        expectedRoles.add(role3);

        for (Role r: expectedRoles) {
            WorkersDatabase.insertRole(r);
        }

        List<Role> actualRoles = WorkersDatabase.selectAllRoles();

        assertEquals(expectedRoles , actualRoles);
    }

    @Test
    public void insertDuplicateRoles(){
        List<Role> addedToDatabase = new LinkedList<>();

        addedToDatabase.add(new Role("GoalKeeper"));
        addedToDatabase.add(new Role("Defender"));
        addedToDatabase.add(new Role("Defender"));
        addedToDatabase.add(new Role("Defender"));
        addedToDatabase.add(new Role("Defender"));
        addedToDatabase.add(new Role("Midfielder"));
        addedToDatabase.add(new Role("Midfielder"));
        addedToDatabase.add(new Role("Midfielder"));
        addedToDatabase.add(new Role("Midfielder"));
        addedToDatabase.add(new Role("Striker"));
        addedToDatabase.add(new Role("Striker"));

        List<Role> expected = new LinkedList<>();

        for (Role r: addedToDatabase) {
            WorkersDatabase.insertRole(r);
            if(!expected.contains(r))
                expected.add(r);
        }

        List<Role> actualRoles = WorkersDatabase.selectAllRoles();

        assertEquals(expected , actualRoles);
    }
}
