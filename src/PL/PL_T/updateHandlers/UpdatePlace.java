package PL.PL_T.updateHandlers;

import BL.BL_T.Entities.Place;
import BL.BL_T.EntitiyFunctions.PlaceFunctions;
import PL.Functor;
import BL.BL_W.WorkerLogic;
import BL.BL_W.Entities_W.Worker;
import PL.PL_T.Utils;

import java.util.Scanner;

public class UpdatePlace extends Functor {
    static Scanner reader = new Scanner(System.in);

    @Override
    public void execute() {
        String idToUpdate;
        String newField;
        System.out.println("enter place's ID");
        idToUpdate = reader.next();
        Place p;
        try {
            if (!PlaceFunctions.isExist(idToUpdate)) {
                System.out.println("error: ID doesn't exist");
                return;
            }
            else {
                p = PlaceFunctions.retrievePlace(idToUpdate);
            }
        } catch (Exception e) {
            System.out.println("error: update failed");
            return;
        }
        if (Utils.boolQuery("update address? y/n")) {
            System.out.println("enter address (one word)");
            newField = reader.next();
            p.setAddress(newField);
        }
        if (Utils.boolQuery("update phone number? y/n")) {
            System.out.println("enter phone number");
            newField = reader.next();
            p.setPhoneNumber(newField);
        }
        if (Utils.boolQuery("update contact name? y/n")) {
            System.out.println("enter contact worker id");
            newField = reader.next();
            Worker contact = WorkerLogic.getWorker(newField);
            if (contact == null){
                System.out.println("contact worker does not exist!");
                return;
            }
            p.setContactName(contact);
        }
        try {
            PlaceFunctions.updatePlace(p);
        } catch (Exception e) {
            System.out.println("error: update failed");
        }
    }
}
