package PL.PL_W;

import BL.BL_W.Entities_W.Role;
import BL.BL_W.Entities_W.Worker;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Utils {
    static Scanner reader = new Scanner(System.in);
    public static boolean boolQuery(String entry){
        System.out.println(entry);
        String input = reader.next();
        while (!(input.equals("y")||input.equals("n"))) {
            System.out.println("Enter y/n");
            input = reader.next();
        }
        if(input.equals("y"))
            return true;
        return false;
    }

    public static String projectAssignedWorkers(Map<Worker, Role> assignedWorkers) {
        String ret = "";
        for (Map.Entry<Worker,Role> rdt :
                assignedWorkers.entrySet()) {
            ret += rdt.getKey().getId() + "\t" + rdt.getKey().getFname() + "\t" + rdt.getKey().getLname() + "\t" + rdt.getValue().getRole() + ".\n";
        }
        return ret;
    }

    public static String projectAvailableWorkers(List<Worker> availableWorkers){
        String ret = "";
        for(Worker worker: availableWorkers)
            ret+= worker.getId()+ "\t" +worker.getFname()+ "\t" +worker.getLname()+"\t"+worker.projectRoles()+"\n";
        return ret;
    }

    public static String projectShiftManagers(List<Worker>shiftManagers){
        String ret = "";
        for (Worker worker :
                shiftManagers) {
            ret+= worker.getId()+ "\t" +worker.getFname()+ "\t" +worker.getLname();
        }
        return ret;
    }


}
