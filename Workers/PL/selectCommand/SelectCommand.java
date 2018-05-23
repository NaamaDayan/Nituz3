package PL.selectCommand;
import PL.utilCommands.mainMenuCommand;
import utils.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SelectCommand implements Command {
    static Scanner reader = new Scanner(System.in);
    static Map<String , Command> selectCommands = new HashMap<>();

    public SelectCommand(){
        selectCommands.put("1", new SelectAvailableWorkers());
        selectCommands.put("2", new SelectAssignedWorkers());
        selectCommands.put("3", new SelectWorker());
        selectCommands.put("4", new mainMenuCommand());
    }


    @Override
    public void execute() {
        userManual();
        selectCommands.get(reader.next()).execute();
    }

    private void userManual(){
        System.out.println("1 - select available workers for a desired shift");
        System.out.println("2 - select workers assigned to a desired shift");
        System.out.println("3 - select worker by ID");
        System.out.println("4 - Return to main menu");
    }
}
