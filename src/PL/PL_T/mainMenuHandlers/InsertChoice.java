package PL.PL_T.mainMenuHandlers;

import PL.Functor;
import PL.MainMenus.TransportsMainMenu;

import java.text.ParseException;
import java.util.Scanner;

public class InsertChoice extends Functor {
    static Scanner reader = new Scanner(System.in);

    enum insertFunctions {
        InsertTruck,
        InsertLicense,
        InsertDelivery,
        InsertPlace,
        InsertLicenseForDriver,
        InsertDeliveryDestination,
        InsertModel
    }

    @Override
    public void execute() throws ParseException {
        Functor insertFuncs[] = fillInsertFunctions();
        System.out.println("Enter:\n 1 to insert Truck\n 2 to insert license\n 3 to insert delivery \n 4 to insert place\n 5 to insert license for driver\n 6 to insert delivery destination\n 7 to insert truck model\n 8 for previous menu");
        int insertChoice = reader.nextInt();
        insertChoice = TransportsMainMenu.rangeCheck(1, 8, insertChoice);
        if (insertChoice == 8)
            return;
        insertFuncs[insertChoice-1].execute();
    }

    private static Functor[] fillInsertFunctions() {
        Functor[] funcsArr = new Functor[insertFunctions.values().length]; //number of items in the enum
        Class funcClass;
        try {
            for (int i = 0; i < funcsArr.length; i++) {
                funcClass = Class.forName("PL.PL_T.insertHandlers."+ insertFunctions.values()[i].name()); //gets the name of the function
                funcsArr[i] = (Functor)funcClass.newInstance(); //creates instance
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return funcsArr;
    }


}
