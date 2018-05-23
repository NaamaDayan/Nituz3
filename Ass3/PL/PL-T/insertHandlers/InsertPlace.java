package insertHandlers;


import Entities.Place;
import EntitiyFunctions.PlaceFunctions;
import Functor;

import java.util.Scanner;

public class InsertPlace extends Functor {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("enter place id");
        String placeId = reader.next();
        try {
            if (PlaceFunctions.isExist(placeId)){
                System.out.println("place already exists");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("enter place address (one word)");
        String address = reader.next();
        System.out.println("enter place phone number");
        String phoneNumber = reader.next();
        System.out.println("enter place contact name");
        String contactName = reader.next();
        Place place = new Place(placeId, address, phoneNumber, contactName);
        PlaceFunctions.insertPlace(place);
    }

}
