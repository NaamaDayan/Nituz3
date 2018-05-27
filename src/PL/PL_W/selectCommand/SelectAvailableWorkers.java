package PL.PL_W.selectCommand;

import BL.BL_W.ShiftLogic;
import BL.BL_W.Entities_W.Worker;
import PL.PL_W.Command;
import PL.PL_W.Utils;

import java.sql.SQLException;
import java.util.List;

public class SelectAvailableWorkers extends SelectShift implements Command {
    @Override
    public void execute() {
        getInformation();
        if (desiredShift == null) {
            System.out.println("The desired shift does not exist\n");
            return;
        }
        List<Worker> retrievedData = ShiftLogic.selectAvailableWorkers(desiredShift);
        if (retrievedData.isEmpty())
            System.out.println("The desired shift has no available workers\n");
        System.out.println(Utils.projectAvailableWorkers(retrievedData));
    }
}
