package DAL.DAL_W;

import BL.BL_W.Entities_W.BankAccount;
import BL.BL_W.Entities_W.Role;
import BL.BL_W.Entities_W.Shift;
import BL.BL_W.Entities_W.Worker;

import java.io.File;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class WorkersDatabase {
    private static final String databaseName = "WorkersModule.db";

    // TODO: 25/05/18 we shall not use all of the create-tables functions anymore, consider deleting them(S)
   /* public static void createDatabase() {
        try (Connection connection = openConnection()) {
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
    }*/

    public static void createWorkers(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE Workers " +
                    "(ID             TEXT    PRIMARY KEY     NOT NULL," +
                    " FName          TEXT    NOT NULL, " +
                    " LName          TEXT    NOT NULL, " +
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
                    "PRIMARY KEY (ShiftDate , ShiftDayPart));";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public static void createRoles(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE Roles" +
                    "(RoleID    INTEGER     PRIMARY KEY   AUTOINCREMENT," +
                    "RoleName   TEXT    NOT NULL  UNIQUE);";
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
                    "PRIMARY KEY (WorkerID , DateAvailable , DayPart)," +
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
                    "Role   INTEGER  NOT NULL," +
                    "PRIMARY KEY (WorkerID , ShiftDate , ShiftDayPart)," +
                    "FOREIGN KEY (Role) REFERENCES Roles(RoleID) ON DELETE CASCADE, " +
                    "FOREIGN KEY (WorkerID , ShiftDate , ShiftDayPart) REFERENCES WorkersAvailableShifts(WorkerID , DateAvailable, DayPart) ON DELETE CASCADE );";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    // TODO: 25/05/18 we shall not use this anymore, consider deleting (S)
    /*public static void openDatabase() {
        File f = new File(databaseName);
        if (!f.exists())
            WorkersDatabase.createDatabase();
    }*/

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

    public static boolean insertWorker(Worker worker) {
        String workersSql = "Insert INTO Workers (ID , FName, LName, PhoneNumber, EmploymentDate) " +
                "VALUES (? , ? , ? , ? , ?)";
        String bankSql = "Insert INTO BankAccounts (WorkerID , BankCode , BranchNumber , AccountNumber) " +
                "VALUES (? , ? , ? , ?)";

        try (Connection connection = openConnection();
             PreparedStatement pstmt1 = connection.prepareStatement(workersSql);
             PreparedStatement pstmt2 = connection.prepareStatement(bankSql)) {
            connection.setAutoCommit(false);
            pstmt1.setString(1, worker.getId());
            pstmt1.setString(2, worker.getFname());
            pstmt1.setString(3, worker.getLname());
            pstmt1.setString(4, worker.getPhoneNum());
            pstmt1.setDate(5, worker.getEmploymentDate());
            pstmt1.executeUpdate();
            pstmt2.setString(1, worker.getId());
            pstmt2.setString(2, worker.getBankAccount().getBankCode());
            pstmt2.setString(3, worker.getBankAccount().getBranchNumber());
            pstmt2.setString(4, worker.getBankAccount().getAccountNumber());
            pstmt2.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // TODO: 26/04/18 change
    public static Worker getWorker(String id) {
        String sql = "SELECT W.FName, W.LNAME, W.LNAME, W.PhoneNumber, W.EmploymentDate, BA.BankCode, BA.AccountNumber" +
                ", BA.BranchNumber FROM Workers AS W,BankAccounts AS BA " +
                " WHERE  W.ID=BA.WorkerID AND W.ID=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println();
            } else {
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                String phoneNum = rs.getString("PhoneNumber");
                Date employmentDate = rs.getDate("EmploymentDate");
                String bankCode = rs.getString("BankCode");
                String accountNumber = rs.getString("AccountNumber");
                String branchNumber = rs.getString("BranchNumber");
                List<Role> roles = getRoles(id);
                return new Worker(id, fName, lName, phoneNum, employmentDate, new BankAccount(bankCode, accountNumber, branchNumber), roles);
            }
        } catch (SQLException e) {
        }
        return null;
    }


    public static List<Role> getRoles(String id) {
        String sql = "SELECT R.RoleName FROM Roles AS R INNER JOIN WorkersRoles AS WR WHERE " +
                "R.RoleID=WR.Role AND WR.WorkerID=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            return getWorkerRoles(rs);
        } catch (SQLException e) {
            return null;
        }

    }

    private static List<Role> getWorkerRoles(ResultSet rs) {
        List<Role> workerRoles = new ArrayList<>();
        try {
            if (!rs.isBeforeFirst()) return workerRoles;
            while (rs.next()) {
                String roleName = rs.getString("RoleName");
                workerRoles.add(new Role(roleName));
            }
        } catch (SQLException e) {
        }
        return workerRoles;
    }


    public static boolean updateBankAccount(Worker worker) {
        String sql = "UPDATE BankAccounts SET BankCode=?, AccountNumber=?, BranchNumber=? " +
                "WHERE WorkerID=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getBankAccount().getBankCode());
            pstmt.setString(2, worker.getBankAccount().getAccountNumber());
            pstmt.setString(3, worker.getBankAccount().getBranchNumber());
            pstmt.setString(4, worker.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean makeWorkerAvailableForShift(Worker worker, Shift shift) {
        String sql = "Insert INTO WorkersAvailableShifts " +
                "(WorkerID , DateAvailable , DayPart) VALUES (? , ? , ?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, shift.getShiftDayPart().toString());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateWorker(Worker worker) {
        String sql = "Update Workers SET FName=?, LName=?, PhoneNumber=?, EmploymentDate=? WHERE ID=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getFname());
            pstmt.setString(2, worker.getLname());
            pstmt.setString(3, worker.getPhoneNum());
            pstmt.setDate(4, worker.getEmploymentDate());
            pstmt.setString(5, worker.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean removeAvailableShifts(Worker worker, Shift shift) {
        String sql = "DELETE FROM WorkersAvailableShifts " +
                "WHERE WorkerID=? AND DateAvailable=? AND DayPart=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, (shift.getShiftDayPart()).toString());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public static boolean removeWorkerShifts(Worker worker, Shift shift) {
        String sql = "DELETE FROM WorkersShifts WHERE WorkerID=? AND ShiftDate=? AND ShiftDayPart=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, (shift.getShiftDayPart()).toString());
            pstmt.execute();
            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    public static boolean removeWorkersRoles(Worker worker, Role role) {
        String sql = "DELETE FROM WorkersRoles WHERE WorkerID=? AND Role=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setInt(2, getRoleIDByName(role.getRole()));
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean insertRoleForWorker(Worker worker, Role role) {
        int roleID = getRoleIDByName(role.getRole());
        if (roleID < 0)
            return false;
        String sql = "Insert Into WorkersRoles (WorkerID , Role) Values (? , ?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setInt(2, roleID);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }


    private static int getRoleIDByName(String role) {
        String sql = "SELECT RoleID FROM Roles WHERE RoleName=?";
        try {
            try (Connection connection = openConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, role);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int ret = rs.getInt("RoleID");
                    return ret;
                }
            }
        } catch (SQLException e) {
        }
        return -1;
    }

    public static Map<Worker, Role> selectAssignedWorkers(Shift shift) {
        String sql = "SELECT W.ID, W.FName, W.LName, W.PhoneNumber, R.RoleName FROM Workers AS W " +
                "INNER JOIN WorkersShifts AS WS INNER JOIN WorkersRoles as WR INNER JOIN Roles AS R " +
                " WHERE W.ID = WS.WorkerID AND WS.WorkerID=WR.WorkerID AND WS.Role=WR.Role AND WR.Role=R.RoleID" +
                " AND ShiftDate=? AND ShiftDayPart=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, shift.getDate());
            pstmt.setString(2, (shift.getShiftDayPart()).toString());
            ResultSet rs = pstmt.executeQuery();
            return mappingRSForShifts(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    private static Map<Worker, Role> mappingRSForShifts(ResultSet resultSet) {
        ResultSet rs = resultSet;
        Map<Worker, Role> data = new HashMap<>();
        try {
            if (!rs.isBeforeFirst()) return data;
            while (rs.next()) {
                String ID = rs.getString("ID");
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                String phoneNum = rs.getString("PhoneNumber");
                String role = rs.getString("RoleName");
                data.put(new Worker(ID, fName, lName, phoneNum, null), new Role(role));
            }

        } catch (SQLException e) {
        }

        return data;
    }


    public static List<Worker> selectAvailableWorkers(Shift shift) {
        String sql = "SELECT W.ID, W.FName, W.LName, W.PhoneNumber FROM Workers AS W " +
                "INNER JOIN WorkersAvailableShifts AS WAS " +
                "WHERE W.ID = WAS.WorkerID " +
                " AND WAS.DateAvailable=? AND WAS.DayPart=? ";
        try {
            try (Connection connection = openConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setDate(1, shift.getDate());
                pstmt.setString(2, (shift.getShiftDayPart()).toString());
                ResultSet rs = pstmt.executeQuery();
                return listingRSForShifts(rs);
            }
        } catch (SQLException e) {
            return null;
        }
    }

    private static List<Worker> listingRSForShifts(ResultSet resultSet) {
        List<Worker> workers = new ArrayList<>();
        try {
            if (!resultSet.isBeforeFirst()) return workers;
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                String fName = resultSet.getString("FName");
                String lName = resultSet.getString("LName");
                String phoneNum = resultSet.getString("PhoneNumber");
                Worker toAdd = new Worker(ID, fName, lName, phoneNum, null);
                toAdd.setRoles(WorkersDatabase.getRoles(ID));
                workers.add(toAdd);
            }
            return workers;
        } catch (SQLException e) {
            return workers;
        }
    }


    public static boolean insertShift(Shift s) {
        String sql = "INSERT INTO Shifts (ShiftDate, ShiftDayPart) Values (? , ?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, s.getDate());
            pstmt.setString(2, s.getShiftDayPart().toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public static List<Role> selectAllRoles() {
        List<Role> allRoles = new LinkedList<>();
        String sql = "Select RoleID , RoleName From Roles";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Role r = new Role(resultSet.getString("RoleName"));
                allRoles.add(r);
            }
            return allRoles;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean insertRole(Role role) {
        String sql = "INSERT INTO Roles (RoleName) Values (?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, role.getRole());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public static boolean roleExists(Role role) throws SQLException {
        String sql = "Select * from Roles Where RoleName = ?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, role.getRole());
            ResultSet rs = pstmt.executeQuery();
            return (rs.isBeforeFirst());
        }
    }

    //returns the shifts without the workers scheduled in the shift
    public static List<Shift> getAllCreatedShifts() { //TODO: add the workers list for every shift
        List<Shift> allShifts = new LinkedList<>();
        String sql = "Select ShiftDate , ShiftDayPart From Shifts";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                allShifts.add(new Shift(rs.getDate("ShiftDate"), Shift.getDayPartByName(rs.getString("ShiftDayPart"))));
        } catch (SQLException e) {
            return null;
        }
        return allShifts;
    }

    public static boolean shiftExists(Shift newShift) throws SQLException {
        String sql = "Select * From Shifts Where ShiftDate=? AND ShiftDayPart=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, newShift.getDate());
            pstmt.setString(2, newShift.getShiftDayPart().toString());
            ResultSet rs = pstmt.executeQuery();
            return rs.isBeforeFirst();
        }
    }

    public static boolean scheduleWorker(Shift newShift, Worker worker, Role role) {
        int roleID = getRoleIDByName(role.getRole());
        String sql = "INSERT INTO WorkersShifts (WorkerID , ShiftDate, ShiftDayPart , Role) Values (? , ? , ? , ?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, newShift.getDate());
            pstmt.setString(3, newShift.getShiftDayPart().toString());
            pstmt.setInt(4, roleID);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean isWorkerAvailableForShift(Worker worker, Shift shift) {
        String sql = "SELECT * FROM WorkersAvailableShifts WHERE WorkerID=? AND DateAvailable=? AND DayPart=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, shift.getShiftDayPart().toString());
            ResultSet rs = pstmt.executeQuery();
            return !rs.isBeforeFirst();
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean isWorkerAssignedForShift(Worker worker, Shift shift) {
        String sql = "SELECT * FROM WorkersShifts WHERE WorkerID=? AND ShiftDate=? AND ShiftDayPart=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, shift.getShiftDayPart().toString());
            return pstmt.executeQuery().isBeforeFirst();
        } catch (SQLException e) {
            return false;
        }
    }

}