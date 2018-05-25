package PL.PL_T.mainMenuHandlers;

import PL.Functor;
import PL.MainMenus.TransportsMainMenu;

import java.text.ParseException;
import java.util.Scanner;

public class UpdateChoice extends Functor {
    static Scanner reader = new Scanner(System.in);

    enum updateFunctions {
        UpdateTruck,
        UpdateLicense,
        UpdateDelivery,
        UpdatePlace,
        UpdateModel
    }

    @Override
    public void execute() throws ParseException {
        Functor updateFuncs[] = fillUpdateFunctions();
        System.out.println("Enter:\n 1 to update Truck\n 2 to update license\n 3 to update delivery \n 4 to update place \n 5 to update truck model\n 6 for previous menu");
        int updateChoice = reader.nextInt();
        updateChoice = TransportsMainMenu.rangeCheck(1, 6, updateChoice);
        if (updateChoice == 6)
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
