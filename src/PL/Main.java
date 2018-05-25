package PL;
import DAL.Tables;
import PL.Functor;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    static Scanner reader = new Scanner(System.in);

    //---enum with handlers names
    enum mainMenuFunctions {
        WorkersMainMenu,
        TransportsMainMenu
    }

    public static void main(String[] args) throws ParseException {
        Tables.createDatabase();
        programFlow();
    }

    private static void programFlow() throws ParseException {
        System.out.println("Welcome!");
        Functor choiceFuncs[] = fillChoiceFunctions();
        while (true) {
            System.out.println("Enter:\n 1 to Workers Main Menu\n 2 to Transports Main Menu\n 3 to exit");
            int choice = reader.nextInt();
            choice = rangeCheck(1, 3, choice); //after this the choice is legal
            if (choice == 3) {
                System.out.println("Bye!");
                break;
            }
            choiceFuncs[choice - 1].execute();
        }
    }

    //returns array of main menu Handlers
    private static Functor[] fillChoiceFunctions() {
        Functor[] funcsArr = new Functor[mainMenuFunctions.values().length]; //number of items in the enum
        Class funcClass;
        try {
            for (int i = 0; i < funcsArr.length; i++) {
                funcClass = Class.forName("PL.MainMenus."+ mainMenuFunctions.values()[i].name()); //gets the name of the function
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
