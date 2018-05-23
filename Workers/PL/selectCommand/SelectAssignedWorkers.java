package PL.selectCommand;

import BL.ShiftLogic;
import Entities.Role;
import Entities.Worker;
import utils.Command;
import utils.Utils;

import java.sql.SQLException;
import java.util.Map;

public class SelectAssignedWorkers extends SelectShift implements Command {
    @Override
    public void execute() {
        getInformation();
        try {
            if(!ShiftLogic.shiftExists(desiredShift)) {
                System.out.println("The desired shift does not exist\n");
                return;
            }
        } catch (SQLException e) {
        }
        Map<Worker, Role> retrievedData = ShiftLogic.selectAssignedWorkers(desiredShift);
        if(retrievedData.isEmpty())
            System.out.println("The desired shift has no workers\n");
        else {
            System.out.println(Utils.projectAssignedWorkers(ShiftLogic.selectAssignedWorkers(desiredShift)));
        }
    }
}
