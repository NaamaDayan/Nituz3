package PL.removeCommand;

import PL.utilCommands.mainMenuCommand;
import utils.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RemoveCommand implements Command {
    static Scanner reader = new Scanner(System.in);
    static Map<String , Command> removeCommands = new HashMap<>();

    public RemoveCommand(){
        removeCommands.put("1", new RemoveRoleFromWorker());
        removeCommands.put("2", new RemoveAvailableShift());
        removeCommands.put("3", new RemoveActualShift());
        removeCommands.put("4", new mainMenuCommand());
    }


    @Override
    public void execute() {
        userManual();
        removeCommands.get(reader.next()).execute();
    }

    private void userManual(){
        System.out.println("1 - remove a role of a certain worker");
        System.out.println("2 - remove an available shift of a certain worker");
        System.out.println("3 - remove an actual shift of a certain worker");
        System.out.println("4 - Return to main menu");
    }
}
