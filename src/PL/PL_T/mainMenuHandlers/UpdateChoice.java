package PL.PL_T.mainMenuHandlers;

import PL.PL_T.Functor;
import PL.PL_T.Main;

import java.text.ParseException;
import java.util.Scanner;

public class UpdateChoice extends Functor {
    static Scanner reader = new Scanner(System.in);

    enum updateFunctions {
        UpdateTruck,
        UpdateDriver,
        UpdateLicense,
        UpdateDelivery,
        UpdatePlace,
        UpdateModel
    }

    @Override
    public void execute() throws ParseException {
        Functor updateFuncs[] = fillUpdateFunctions();
        System.out.println("Enter:\n 1 to update Truck\n 2 to update driver\n 3 to update license\n 4 to update delivery \n 5 to update place \n 6 to update truck model\n 7 for main menu");
        int updateChoice = reader.nextInt();
        updateChoice = Main.rangeCheck(1, 7, updateChoice);
        if (updateChoice == 7)
            return;
        updateFuncs[updateChoice-1].execute();
    }

    private static Functor[] fillUpdateFunctions() {
        Functor[] funcsArr = new Functor[updateFunctions.values().length]; //number of items in the enum
        Class funcClass;
        try {
            for (int i = 0; i < funcsArr.length; i++) {
                funcClass = Class.forName("PL.PL_T.updateHandlers."+updateFunctions.values()[i].name()); //gets the name of the function
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
