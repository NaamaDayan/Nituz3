import WorkersDatabase;
import Entities.Role;
import Entities.Worker;

import java.sql.SQLException;
import java.util.List;

public class RolesLogic {

    public static boolean removeWorkersRoles(Worker worker, Role role){
        return WorkersDatabase.removeWorkersRoles(worker, role);
    }

    public static boolean insertRole(Role role){
        return WorkersDatabase.insertRole(role);
    }

    public static List<Role> getAllRoles(){
        return WorkersDatabase.selectAllRoles();

    }

    public static boolean insertRoleForWorker(Worker worker, Role role) {
        return WorkersDatabase.insertRoleForWorker(worker , role);
    }

    public static boolean workerHasRole(Worker worker, Role role){
        return (worker.getRoles().contains(role));
    }

    public static boolean roleExists(Role role) throws SQLException {
        return (WorkersDatabase.roleExists(role));
    }
}
