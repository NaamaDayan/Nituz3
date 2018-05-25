package PL.PL_W.insertCommand;

import BL.BL_W.RolesLogic;
import BL.BL_W.Entities_W.Role;
import utils_W.Command;

import java.sql.SQLException;
import java.util.Scanner;

public class InsertRole implements Command{
    static Scanner reader = new Scanner(System.in);
    @Override
    public void execute() {
        System.out.println("Enter the new role you would like to add:");
        String roleName = reader.nextLine();
        Role role = new Role(roleName);
        try {
            if(RolesLogic.roleExists(role))
                System.out.println("The role "+role.getRole()+" already exists\n");
            else if(RolesLogic.insertRole(role))
                System.out.println("new role added successfully\n");
            else{
                System.out.println("Adding role" +role.getRole()+" failed\n");
            }
        }catch (SQLException e) {
            System.out.println("Error while adding the role\n");
        }
    }
}
