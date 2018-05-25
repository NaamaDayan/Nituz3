package PL.PL_W.updateCommand;

import PL.PL_W.utilCommands.mainMenuCommand;
import PL.PL_W.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UpdateCommand implements Command {
    static Scanner reader = new Scanner(System.in);
    static Map<String , Command> updateCommands = new HashMap<>();

    public UpdateCommand(){
        updateCommands.put("1", new UpdateWorker());
        updateCommands.put("2", new UpdateBankAccount());
        updateCommands.put("3", (Command) new mainMenuCommand());
    }

    @Override
    public void execute() {
        userManual();
        updateCommands.get(reader.next()).execute();
    }

    private void userManual(){
        System.out.println("1 - Update a worker's details");
        System.out.println("2 - Update a worker's bank account");
        System.out.println("3 - Return to previous menu");
    }
}
