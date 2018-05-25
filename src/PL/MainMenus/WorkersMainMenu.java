package PL.MainMenus;

import PL.Functor;
import PL.PL_W.Command;
import PL.PL_W.insertCommand.InsertCommand;
import PL.PL_W.removeCommand.RemoveCommand;
import PL.PL_W.selectCommand.SelectCommand;
import PL.PL_W.updateCommand.UpdateCommand;
import PL.PL_W.utilCommands.ExitCommand;
import PL.PL_W.utilCommands.mainMenuCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public  class WorkersMainMenu extends Functor {
    static Scanner reader = new Scanner(System.in);
    static Map<String, Command> commands = new HashMap<>();

    @Override
    public void execute() {
        fillmap();
        System.out.println("Workers Menu");
        while (true) {
            userManual();
            Command toExec = commands.get(reader.next());
            if (toExec == null)
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
        commands.put("5", new mainMenuCommand());
    }

    public static void userManual() {
        System.out.println("Which command would you like to execute?");
        System.out.println("1 - Insert new information to the system");
        System.out.println("2 - Update existing information");
        System.out.println("3 - Receive information from the system");
        System.out.println("4 - Remove information from the system");
        System.out.println("5 - Return to Main Menu");
    }
}
