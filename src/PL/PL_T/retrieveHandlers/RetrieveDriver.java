package PL.PL_T.retrieveHandlers;

import BL.BL_T.Entities.Driver;
import BL.BL_T.EntitiyFunctions.DriverFunctions;
import PL.Functor;

import java.util.Scanner;

public class RetrieveDriver extends Functor {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter driver id");
        String id = reader.next();
        try {
            if (!DriverFunctions.isExist(id)){
                System.out.println("driver does not exist");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Driver driver = DriverFunctions.retrieveDriver(id);
        System.out.println(driver.toString());
    }
}
