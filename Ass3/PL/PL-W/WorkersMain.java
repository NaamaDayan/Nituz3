import java.util.HashMap;
import java.util.Map;

import utilCommands.ExitCommand;
import insertCommand.InsertCommand;
import removeCommand.RemoveCommand;
import selectCommand.SelectCommand;
import updateCommand.UpdateCommand;
import utils.Command;

import java.util.Scanner;

public  class WorkersMain {
    static Scanner reader = new Scanner(System.in);
    static Map<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        WorkersDatabase.openDatabase();
        fillmap();
        boolean close = false;
        System.out.println("Hello, ");
        while (!close) {
            userManual();
            Command toExec = commands.get(reader.next());
            if(toExec==null)
                System.out.println("Illegal input!\n");
            else
                toExec.execute();
        }
    }

    private static void fillmap() {
        commands.put("1", new InsertCommand());
        commands.put("2", new UpdateCommand());
        commands.put("3", new SelectCommand());
        commands.put("4", new RemoveCommand());
        commands.put("5", new ExitCommand());
    }


    public static void userManual() {
        System.out.println("Which command would you like to execute?");
        System.out.println("1 - Insert new information to the system");
        System.out.println("2 - Update existing information");
        System.out.println("3 - Receive information from the system");
        System.out.println("4 - Remove information from the system");
        System.out.println("5 - Exit the system");
    }
}
