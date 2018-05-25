package PL.PL_T;
import DAL.Tables;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    static Scanner reader = new Scanner(System.in);

    //---enum with handlers names
    enum mainMenuFunctions {
        InsertChoice,
        UpdateChoice,
        RetrieveChoice,
        RemoveChoice
    }

    public static void main(String[] args) throws ParseException {
        Tables.createDatabase();
        programFlow();
    }

    private static void programFlow() throws ParseException {
        System.out.println("Welcome!");
        System.out.println("Enter:\n 1 to insert data\n 2 to update data\n 3 to retrieve data\n 4 to remove data\n 5 to exit");
        int choice = reader.nextInt();
        boolean cont = true;
        Functor choiceFuncs[] = fillChoiceFunctions();
        while (cont) {
            choice = rangeCheck(1, 5, choice); //after this the choice is legal
            if (choice == 5) {
                System.out.println("Bye!");
                cont = false;
                break;
            }
            choiceFuncs[choice - 1].execute();
            if (cont) {
                System.out.println("Enter:\n 1 to insert data\n 2 to update data\n 3 to retrieve data\n 4 to remove data\n 5 to exit");
                choice = reader.nextInt();
            }
        }
    }

    //returns array of main menu Handlers
    private static Functor[] fillChoiceFunctions() {
        Functor[] funcsArr = new Functor[mainMenuFunctions.values().length]; //number of items in the enum
        Class funcClass;
        try {
            for (int i = 0; i < funcsArr.length; i++) {
                funcClass = Class.forName("PL.PL_T.mainMenuHandlers."+ mainMenuFunctions.values()[i].name()); //gets the name of the function
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
