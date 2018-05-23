package removeHandlers;
import EntitiyFunctions.PlaceFunctions;
import Functor;

import java.util.Scanner;

/**
 * Created by Shahar on 22/04/2018.
 */
public class RemovePlace extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter place id");
        String id = reader.next();
        try {
            if (!PlaceFunctions.isExist(id)) {
                System.out.println("error: place doesn't exist");
                return;
            }
        } catch (Exception e) {
            System.out.println("error: remove failed");
            return;
        }
        PlaceFunctions.removePlace(id);
    }
}
