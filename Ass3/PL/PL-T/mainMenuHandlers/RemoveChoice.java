package mainMenuHandlers;

import Functor;
import Main;
import java.text.ParseException;
import java.util.Scanner;

public class RemoveChoice extends Functor {
    static Scanner reader = new Scanner(System.in);

    enum removeFunctions {
        RemoveTruck,
        RemoveDriver,
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
        System.out.println("Enter:\n 1 to remove Truck\n 2 to remove driver\n 3 to remove license\n 4 to remove delivery \n 5 to remove delivery destination\n 6 to remove place\n 7 to remove license of driver \n 8 to remove truck model\n 9 for main menu");
        int insertChoice = reader.nextInt();
        insertChoice = Main.rangeCheck(1, 9, insertChoice);
        if (insertChoice == 9)
            return;
        removeFuncs[insertChoice-1].execute();
    }

    private static Functor[] fillRemoveFunctions() {
        Functor[] funcsArr = new Functor[removeFunctions.values().length]; //number of items in the enum
        Class funcClass;
        try {
            for (int i = 0; i < funcsArr.length; i++) {
                funcClass = Class.forName("PL.removeHandlers."+ removeFunctions.values()[i].name()); //gets the name of the function
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
