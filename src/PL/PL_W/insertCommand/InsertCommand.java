package PL.PL_W.insertCommand;

import PL.PL_W.utilCommands.mainMenuCommand;
import PL.PL_W.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InsertCommand implements Command {
    static Scanner reader = new Scanner(System.in);
    static Map<String , Command> insertCommands = new HashMap<>();

    public InsertCommand(){
        insertCommands.put("1" , new InsertWorker());
        insertCommands.put("2" , new CreateShift());
        insertCommands.put("3", new InsertWorkersAvailableShifts());
        insertCommands.put("4" , new ScheduleWorker());
        insertCommands.put("5", (Command) new InsertRole());
        insertCommands.put("6" , new InsertRoleForWorker());
        insertCommands.put("7", (Command) new mainMenuCommand());
    }

    @Override
    public void execute() {
        userManual();
        insertCommands.get(reader.next()).execute();
    }

    private void userManual(){
        System.out.println("1 - Insert a new worker to the system");
        System.out.println("2 - Create a new shift");
        System.out.println("3 - Insert a worker's available shift");
        System.out.println("4 - Schedule a worker to a shift");
        System.out.println("5 - Insert a new role to the system");
        System.out.println("6 - Insert a new Role for a worker");
        System.out.println("7 - Return to previous menu");
    }


}
