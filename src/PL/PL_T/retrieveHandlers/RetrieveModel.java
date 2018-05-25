package PL.PL_T.retrieveHandlers;

import BL.BL_T.Entities.TruckModel;
import BL.BL_T.EntitiyFunctions.ModelFunctions;
import PL.PL_T.Functor;

import java.util.Scanner;

public class RetrieveModel extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter model id");
        String id = reader.next();
        try {
            if (!ModelFunctions.isExist(id)) {
                System.out.println("id does not exist");
                return;
            }
            TruckModel model = ModelFunctions.retrieveModel(id);
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
