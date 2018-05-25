package PL.PL_T.mainMenuHandlers;

import PL.PL_T.Functor;
import PL.PL_T.Main;

import java.text.ParseException;
import java.util.Scanner;

public class RetrieveChoice extends Functor {
    static Scanner reader = new Scanner(System.in);

    enum retrieveFunctions {
        RetrieveTruck,
        RetrieveDriver,
        RetrieveLicense,
        RetrieveDelivery,
        RetrievePlace,
        RetrieveLicenseForDriver,
        RetrieveModel
    }

    @Override
    public void execute() throws ParseException {
        Functor retrieveFuncs[] = fillRetrieveFunctions();
        System.out.println("Enter:\n 1 to retrieve Truck\n 2 to retrieve driver\n 3 to retrieve license\n 4 to retrieve delivery \n 5 to retrieve place\n 6 to retrieve license of driver \n 7 to retrieve truck model\n 8 for main menu");
        int retrieveChoice = reader.nextInt();
        retrieveChoice = Main.rangeCheck(1, 8, retrieveChoice);
        if (retrieveChoice == 8)
            return;
        retrieveFuncs[retrieveChoice-1].execute();
    }

    private static Functor[] fillRetrieveFunctions() {
        Functor[] funcsArr = new Functor[retrieveFunctions.values().length]; //number of items in the enum
        Class funcClass;
        try {
            for (int i = 0; i < funcsArr.length; i++) {
                funcClass = Class.forName("PL.PL_T.retrieveHandlers."+retrieveFunctions.values()[i].name()); //gets the name of the function
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
