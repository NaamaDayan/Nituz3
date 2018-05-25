package PL.PL_W.utilCommands;
import utils_W.Command;
public class ExitCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Bye!");
        System.exit(0);
    }
}
