package PL.PL_W.insertCommand;

import BL.BL_W.RolesLogic;
import BL.BL_W.Entities_W.Role;
import PL.PL_W.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertRole implements Command{
    //static Scanner reader = new Scanner(System.in);
    @Override
    public void execute() {
        System.out.println("Enter the new role you would like to add:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String roleName = null;
        try {
            roleName = br.readLine();
        } catch (IOException e) {
        }

        String roleDesc = null;
        System.out.println("Enter the description of the role you just entered:");
        try {
            roleDesc= br.readLine();
        } catch (IOException e) {
        }

        Role role = new Role(roleName, roleDesc);
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
