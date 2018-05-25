package PL.MainMenus;
import PL.Functor;

import java.text.ParseException;
import java.util.Scanner;

public class TransportsMainMenu extends Functor {
    static Scanner reader = new Scanner(System.in);

    //---enum with handlers names
    enum transportsMenuFunctions {
        InsertChoice,
        UpdateChoice,
        RetrieveChoice,
        RemoveChoice
    }

    @Override
    public void execute() throws ParseException {
        Functor choiceFuncs[] = fillChoiceFunctions();
        System.out.println("Transports Menu");
        while(true) {
            System.out.println("Enter:\n 1 to insert data\n 2 to update data\n 3 to retrieve data\n 4 to remove data\n 5 to Main Menu");
            int choice = reader.nextInt();
            choice = rangeCheck(1, 5, choice); //after this the choice is legal
            if (choice == 5)
                break;
            choiceFuncs[choice - 1].execute();
        }
    }

    //returns array of main menu Handlers
    private static Functor[] fillChoiceFunctions() {
        Functor[] funcsArr = new Functor[transportsMenuFunctions.values().length]; //number of items in the enum
        Class funcClass;
        try {
            for (int i = 0; i < funcsArr.length; i++) {
                funcClass = Class.forName("PL.PL_T.mainMenuHandlers."+ transportsMenuFunctions.values()[i].name()); //gets the name of the function
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

    public static int rangeCheck(int lowerBound, int upperBound, int input) {
        while (input < lowerBound || input > upperBound) {
            System.out.println("Enter a number in range " + lowerBound + "-" + upperBound +" please:");
            input = reader.nextInt();
        }
        return input;
    }

}
