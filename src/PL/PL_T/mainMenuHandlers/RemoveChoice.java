package PL.PL_T.mainMenuHandlers;

import PL.Functor;
import PL.MainMenus.TransportsMainMenu;
import java.text.ParseException;
import java.util.Scanner;

public class RemoveChoice extends Functor {
    static Scanner reader = new Scanner(System.in);

    enum removeFunctions {
        RemoveTruck,
        RemoveLicense,
        RemoveDelivery,
        RemoveDeliveryDestination,
        RemovePlace,
        RemoveLicenseOfDriver,
        RemoveModel
    }

    @Override
    public void execute() throws ParseException {
        Functor removeFuncs[] = fillRemoveFunctions();
        System.out.println("Enter:\n 1 to remove Truck\n 2 to remove license\n 3 to remove delivery \n 4 to remove delivery destination\n 5 to remove place\n 6 to remove license of driver \n 7 to remove truck model\n 8 for previous menu");
        int insertChoice = reader.nextInt();
        insertChoice = TransportsMainMenu.rangeCheck(1, 8, insertChoice);
        if (insertChoice == 8)
            return;
        removeFuncs[insertChoice-1].execute();
    }

    private static Functor[] fillRemoveFunctions() {
        Functor[] funcsArr = new Functor[removeFunctions.values().length]; //number of items in the enum
        Class funcClass;
        try {
            for (int i = 0; i < funcsArr.length; i++) {
                funcClass = Class.forName("PL.PL_T.removeHandlers."+ removeFunctions.values()[i].name()); //gets the name of the function
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
