package DAL;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Naama on 23/05/2018.
 */
public class Tables {
    private static final String databaseName = "WorkersAndTransports.db";

    public static void createDatabase() {
        try (Connection connection = openConnection()) {
            createModelsTable(connection);
            createTrucksTable(connection);
            createLicensesTable(connection);
            createDeliveriesTable(connection);
            createPlacesTable(connection);
            createDeliveryDestinationsTable(connection);
            createLicensesForDriversTable(connection);

            createWorkers(connection);
            createBankAccounts(connection);
            createRoles(connection);
            createShifts(connection);
            createWorkersRoles(connection);
            createAvailableShifts(connection);
            createWorkersShifts(connection);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static Connection openConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.createStatement().execute("PRAGMA foreign_keys = ON");
        } catch (Exception e) {
            System.exit(0);
        }
        return connection;
    }

    public static void openDatabase() {
        File f = new File(databaseName);
        if (!f.exists())
            createDatabase();
    }


    private static void createModelsTable(Connection conn) {
        try (Statement stmt = conn.createStatement();) {
            String sql = "CREATE TABLE Models " +
                    "(ID VARCHAR(9) PRIMARY KEY NOT NULL," +
                    "MODEL_NAME     TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTrucksTable(Connection conn) {
        try (Statement stmt = conn.createStatement();) {
            String sql = "CREATE TABLE Trucks " +
                    "(ID VARCHAR(9) PRIMARY KEY NOT NULL," +
                    "MODEL           VARCHAR(9)    NOT NULL, " +
                    " COLOR           TEXT    NOT NULL, " +
                    "NETO_WEIGHT         INT, " +
                    "MAX_WEIGHT         INT, " +
                    "FOREIGN KEY (MODEL) REFERENCES Models(ID) ON DELETE CASCADE)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void createLicensesTable(Connection conn) {
        try (Statement stmt = conn.createStatement();) {

            String sql = "CREATE TABLE Licenses " +
                    "(ID VARCHAR (9) NOT NULL," +
                    " TRUCK_MODEL  VARCHAR (9), " +
                    "PRIMARY KEY (ID)" +
                    "FOREIGN KEY (TRUCK_MODEL) REFERENCES Models(ID) ON DELETE CASCADE)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createLicensesForDriversTable(Connection conn) {
        try (Statement stmt = conn.createStatement();) {

            String sql = "CREATE TABLE LicensesForDrivers " +
                    "(DRIVER_ID TEXT," +
                    "LICENSE_TYPE VARCHAR (9), "+
                    "FOREIGN KEY (LICENSE_TYPE) REFERENCES Licenses(ID) ON DELETE CASCADE," +
                    "FOREIGN KEY(DRIVER_ID) REFERENCES Workers(ID) ON DELETE CASCADE,"+ // TODO: 23/05/2018 change to workers table (N)
                    "PRIMARY KEY (LICENSE_TYPE, DRIVER_ID))";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createPlacesTable(Connection conn) {
        try (Statement stmt = conn.createStatement();) {

            String sql = "CREATE TABLE Places " +
                    "(ID VARCHAR (9) PRIMARY KEY NOT NULL," +
                    "ADDRESS TEXT NOT NULL ," +
                    "PHONE_NUMBER TEXT, " +
                    "CONTACT_WORKER TEXT, "+
                    "FOREIGN KEY(CONTACT_WORKER) REFERENCES Workers(ID))"; // TODO: 23/05/2018 add contact worker (N)
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDeliveriesTable(Connection conn) {
        try (Statement stmt = conn.createStatement();) {

            String sql = "CREATE TABLE Deliveries " +
                    "(ID VARCHAR (9) PRIMARY KEY NOT NULL," +
                    "LEAVING_DATE DATETIME NOT NULL, " +
                    "LEAVING_TIME DATETIME NOT NULL, " +
                    "TRUCK_ID VARCAR (9), " +
                    "DRIVER_ID TEXT, " +
                    "SOURCE_ID VARCHAR(9) ,"+
                    "FOREIGN KEY(TRUCK_ID) REFERENCES Trucks(ID) ON DELETE CASCADE,"+
                    "FOREIGN KEY(DRIVER_ID) REFERENCES Workers(ID) ON DELETE CASCADE,"+ // TODO: 23/05/2018 change drivers to workers (N)
                    "FOREIGN KEY(SOURCE_ID) REFERENCES Places(ID) ON DELETE CASCADE)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void createDeliveryDestinationsTable(Connection conn) {
        try (Statement stmt = conn.createStatement();) {
            String sql = "CREATE TABLE DeliveryDestinations " +
                    "(DELIVERY_ID VARCHAR (9), " +
                    "PLACE_ID VARCHAR (9),"+
                    "ORDER_NUMBER VARCHAR (9),"+
                    "ITEM_ID TEXT,"+
                    "QUANTITY INTEGER ,"+
                    "PRIMARY KEY(DELIVERY_ID, PLACE_ID, ORDER_NUMBER, ITEM_ID)," +
                    "FOREIGN KEY(PLACE_ID) REFERENCES Places(ID) ON DELETE CASCADE," +
                    "FOREIGN KEY(DELIVERY_ID) REFERENCES Deliveries(ID) ON DELETE CASCADE)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void createWorkers(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE Workers " +
                    "(ID             TEXT    PRIMARY KEY     NOT NULL," +
                    " FName          TEXT    NOT NULL, " +
                    " LName          TEXT    NOT NULL, " +
                    " PhoneNumber          TEXT    NOT NULL, " +
                    " EmploymentDate    DATE); ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public static void createShifts(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE Shifts" +
                    "(ShiftDate     DATE    NOT NULL, " +
                    "ShiftDayPart   TEXT    NOT NULL, " +
                    "PlaceId   TEXT    NOT NULL, " + // TODO: 23/05/2018 add place id as foreign key(N)
                    "FOREIGN KEY (PlaceId) REFERENCES Places(ID) ON DELETE CASCADE )"+
                    "PRIMARY KEY (ShiftDate , ShiftDayPart, PlaceId));";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public static void createRoles(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE Roles" +
                    "(RoleID    INTEGER     PRIMARY KEY   AUTOINCREMENT," +
                    "RoleName   TEXT    NOT NULL  UNIQUE,"+
                    "RoleDescription   TEXT    NOT NULL)"; //todo: add role description (N)
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public static void createBankAccounts(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE BankAccounts" +
                    "(WorkerID  Text PRIMARY KEY    NOT NULL, " +
                    "BankCode   Text    NOT NULL, " +
                    "BranchNumber  Text    NOT NULL," +
                    "AccountNumber   Text    NOT NULL," +
                    "FOREIGN KEY (WorkerID) REFERENCES Workers(ID)); ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public static void createWorkersRoles(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE WorkersRoles" +
                    "(WorkerID Text     NOT NULL, " +
                    "Role   INTEGER        NOT NULL, " +
                    "PRIMARY KEY (WorkerID , Role)," +
                    "FOREIGN KEY (WorkerID) REFERENCES Workers(ID) ON DELETE CASCADE, " +
                    "FOREIGN KEY (Role) REFERENCES Roles(RoleID) ON DELETE CASCADE ); ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public static void createAvailableShifts(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE WorkersAvailableShifts" +
                    "(WorkerID  Text     NOT NULL, " +
                    "DateAvailable    DATE   NOT NULL, " +
                    "DayPart   Text    NOT NULL, " +
                    "PlaceId   TEXT    NOT NULL, " + // TODO: 23/05/2018 add place id as foreign key(N)
                    "FOREIGN KEY (PlaceId) REFERENCES Places(ID))"+
                    "PRIMARY KEY (WorkerID , DateAvailable , DayPart, PlaceId)," +
                    "FOREIGN KEY (WorkerID) REFERENCES Workers(ID) ON DELETE CASCADE," +
                    "FOREIGN KEY (DateAvailable,DayPart) REFERENCES Shifts(ShiftDate,ShiftDayPart) ON DELETE CASCADE ); ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public static void createWorkersShifts(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE WorkersShifts" +
                    "(WorkerID  Text    NOT NULL, " +
                    "ShiftDate  DATE    NOT NULL, " +
                    "ShiftDayPart   Text    NOT NULL, " +
                    "PlaceId   TEXT    NOT NULL, " + // TODO: 23/05/2018 add place id as foreign key(N)
                    "Role   INTEGER  NOT NULL," +
                    "PRIMARY KEY (WorkerID , ShiftDate , ShiftDayPart, PlaceId)," +
                    "FOREIGN KEY (PlaceId) REFERENCES Places(ID))"+
                    "FOREIGN KEY (Role) REFERENCES Roles(RoleID) ON DELETE CASCADE, " +
                    "FOREIGN KEY (WorkerID , ShiftDate , ShiftDayPart) REFERENCES WorkersAvailableShifts(WorkerID , DateAvailable, DayPart) ON DELETE CASCADE );";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }
}
