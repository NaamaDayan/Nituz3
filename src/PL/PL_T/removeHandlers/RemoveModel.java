package PL.PL_T.removeHandlers;
import BL.BL_T.EntitiyFunctions.ModelFunctions;
import PL.PL_T.Functor;

import java.util.Scanner;

/**
 * Created by Shahar on 22/04/2018.
 */
public class RemoveModel extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter model id");
        String id = reader.next();
        try {
            if (!ModelFunctions.isExist(id)) {
                System.out.println("error: model doesn't exist");
                return;
            }
        ModelFunctions.removeModel(id);
        } catch (Exception e) {
            System.out.println("error: remove failed");
            return;
        }
    }
}
