import DAL.WorkersDatabase;
import Entities.BankAccount;
import Entities.Worker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class WorkerTests extends BasicTest{
    private String databaseName = "WorkersModule.db";

    @Test
    public void simpleInsertWorker(){
        try {
            String workerID = "1234";
            String fName = "Leo";
            String lName = "Messi";
            String eDate = "16/10/2004";
            BankAccount bankAccount = new BankAccount("14" , "12345" , "100");
            Worker worker = new Worker(workerID , fName , lName , new Date((new SimpleDateFormat("dd/MM/yyyy").parse(eDate)).getTime()) , bankAccount);
            WorkersDatabase.insertWorker(worker);
            Worker retrieved = WorkersDatabase.getWorker(workerID);
            assertEquals(worker , retrieved);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertDuplicateWorkerID(){
        try {
            String worker1ID = "1";
            String fName1 = "Lebron";
            String lName1 = "James";
            String eDate1 = "01/01/2000";
            BankAccount bankAccount1 = new BankAccount("100" , "123456" , "100");
            Worker worker1 = new Worker(worker1ID , fName1 , lName1 , new Date((new SimpleDateFormat("dd/MM/yyyy").parse(eDate1)).getTime()) , bankAccount1);
            WorkersDatabase.insertWorker(worker1);

            String worker2ID = "1";
            String fName2 = "James";
            String lName2 = "Harden";
            String eDate2 = "07/08/2009";
            BankAccount bankAccount2 = new BankAccount("2" , "2" , "2");
            Worker worker2 = new Worker(worker2ID , fName2 , lName2 , new Date((new SimpleDateFormat("dd/MM/yyyy").parse(eDate2)).getTime()) , bankAccount2);
            WorkersDatabase.insertWorker(worker2);

            Worker retrieved = WorkersDatabase.getWorker("1");
            assertEquals(worker1 , retrieved);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
