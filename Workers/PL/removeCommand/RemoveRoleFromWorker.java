package PL.removeCommand;

import BL.RolesLogic;
import BL.WorkerLogic;
import Entities.Role;
import Entities.Worker;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoveRoleFromWorker implements utils.Command {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Enter worker's id");
        String id = reader.next();
        Worker w = WorkerLogic.getWorker(id);
        if(w==null)
            System.out.println("there is no worker with id: "+id+"\n");
        Role role = getRole(w);
        if(role == null) {
            System.out.println("the role entered doesn't exist in the system\n");
            return;
        }
        if(RolesLogic.removeWorkersRoles(w,role))
            System.out.println("removed role of worker "+ w.getId()+" successfully\n");
        else
            System.out.println("the chosen worker doesn't have the desired role\n");
    }

    public static Role getRole(Worker w){
        if(w != null){
            System.out.println("Enter worker's role");
            String role = reader.next();
            Role workerRole = new Role(role);
            try {
                if (RolesLogic.roleExists(workerRole))
                    return workerRole;

            } catch (SQLException e) {
            }
        }
        return null;
    }

}
