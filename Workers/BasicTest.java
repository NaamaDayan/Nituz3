import DAL.WorkersDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public abstract class BasicTest {
    private String databaseName = "WorkersModule.db";

    @BeforeEach
    public void beforeEach(){
        File f = new File(databaseName);
        if (f.exists())
            f.delete();
        WorkersDatabase.createDatabase();
    }

    @AfterEach
    public void afterEach(){
        File f = new File(databaseName);
        if (f.exists())
            f.delete();
    }
}
