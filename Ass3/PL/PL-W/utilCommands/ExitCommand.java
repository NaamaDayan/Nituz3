package utilCommands;
import utils.Command;
public class ExitCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Bye!");
        System.exit(0);
    }
}
