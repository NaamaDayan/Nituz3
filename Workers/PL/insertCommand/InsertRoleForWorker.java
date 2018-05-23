package PL.insertCommand;

import BL.RolesLogic;
import BL.WorkerLogic;
import Entities.Role;
import Entities.Worker;
import utils.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class InsertRoleForWorker implements Command {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Enter Worker ID:");
        String id = reader.next();
        Worker worker = WorkerLogic.getWorker(id);
        if(worker==null) {
            System.out.println("Worker " + id + " does not exist\n");
        }
        else{
            List<Role>roles = RolesLogic.getAllRoles();
            if(roles.size()==0){
                System.out.println("there are no roles in the system");
                System.out.println("please enter roles before assigning one to a certain worker\n");
                return;
            }
            System.out.println("Enter the role you want to assign to the worker: ");
            System.out.println("Available roles: ");
            int i=1;
            for (Role r: roles) {
                System.out.println(i+"."+r.getRole());
                i++;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String roleName = null;
            try {
                roleName = br.readLine();
            } catch (IOException e) {
            }

            Role role = new Role(roleName);
            if(RolesLogic.workerHasRole(worker , role))
                System.out.println("Worker " + id+" is already qualified to work as "+role.getRole()+"\n");
            else if(RolesLogic.insertRoleForWorker(worker , role))
                System.out.println("added role "+role.getRole()+" to worker "+worker.getId()+"\n");
            else
                System.out.println("Adding role to worker failed\n");
        }
    }
}
