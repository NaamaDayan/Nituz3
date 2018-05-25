package PL.PL_T.insertHandlers;


import BL.BL_T.Entities.TruckModel;
import BL.BL_T.EntitiyFunctions.ModelFunctions;
import PL.PL_T.Functor;

import java.util.Scanner;

public class InsertModel extends Functor {

    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
    System.out.println("enter model id");
    String modelId = reader.next();
    try {
        if (ModelFunctions.isExist(modelId)) {
            System.out.println("model already exists");
            return;
        }
        System.out.println("enter model name");
        String name = reader.next();
        TruckModel model = new TruckModel(modelId, name);
        ModelFunctions.insertModel(model);
    }catch (Exception e) {
        System.out.println("error: insert failed");
        return;
    }
    }
}
