package PL.PL_T.insertHandlers;


import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import BL.BL_W.Entities_W.Worker;
import BL.BL_W.WorkerLogic;
import PL.PL_T.Functor;

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
        System.out.println("enter place contact worker id");
        String contactId = reader.next();
        Worker contact = WorkerLogic.getWorker(contactId);
        if (contact == null){
            System.out.println("worker does not exist");
            return;
        }
        Place place = new Place(placeId, address, phoneNumber, contact);
        PlaceFunctions.insertPlace(place);
    }

}
