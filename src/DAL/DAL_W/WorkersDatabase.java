package DAL.DAL_W;

import BL.BL_W.Entities_W.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static DAL.Tables.openConnection;

public class WorkersDatabase {
    private static final String databaseName = "WorkersModule.db";

    public static boolean insertWorker(Worker worker) {
        String workersSql = "Insert INTO Workers (ID , FName , LName , EmploymentDate , PhoneNumber) " +
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
            pstmt1.setDate(4, worker.getEmploymentDate());
            pstmt1.setString(5 , worker.getPhoneNum());
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
        String sql = "SELECT W.FName, W.LNAME, W.EmploymentDate, W.PhoneNumber ,BA.BankCode, BA.AccountNumber" +
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
                Date employmentDate = rs.getDate("EmploymentDate");
                String bankCode = rs.getString("BankCode");
                String accountNumber = rs.getString("AccountNumber");
                String branchNumber = rs.getString("BranchNumber");
                List<Role> roles = getRoles(id);
                String phoneNumber = rs.getString("PhoneNumber");
                return new Worker(id, fName, lName, phoneNumber, employmentDate ,new BankAccount(bankCode, accountNumber, branchNumber), roles);
            }
        } catch (SQLException e) {
        }
        return null;
    }


    public static List<Role> getRoles(String id) {
        String sql = "SELECT R.RoleName , R.RoleDescription FROM Roles AS R INNER JOIN WorkersRoles AS WR WHERE " +
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
                String roleDesc = rs.getString("RoleDescription");
                workerRoles.add(new Role(roleName , roleDesc));
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
                "(WorkerID , DateAvailable , DayPart , PlaceId) VALUES (? , ? , ? , ?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, shift.getShiftDayPart().toString());
            pstmt.setString(4 , shift.getPlace().getId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateWorker(Worker worker) {
        String sql = "Update Workers SET FName=?, LName=?, EmploymentDate=? , PhoneNumber = ? WHERE ID=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getFname());
            pstmt.setString(2, worker.getLname());
            pstmt.setDate(3, worker.getEmploymentDate());
            pstmt.setString(4, worker.getPhoneNum());
            pstmt.setString(5 , worker.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean removeAvailableShifts(Worker worker, Shift shift) {
        String sql = "DELETE FROM WorkersAvailableShifts " +
                "WHERE WorkerID=? AND DateAvailable=? AND DayPart=? AND PlaceId = ?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, (shift.getShiftDayPart()).toString());
            pstmt.setString(4 , shift.getPlace().getId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public static boolean removeWorkerShifts(Worker worker, Shift shift) {
        String sql = "DELETE FROM WorkersShifts WHERE WorkerID=? AND ShiftDate=? AND ShiftDayPart=? AND PlaceId=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, (shift.getShiftDayPart()).toString());
            pstmt.setString(4 , shift.getPlace().getId());
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
        String sql = "SELECT W.ID, W.FName, W.LName, W.PhoneNumber ,R.RoleName R.RoleDescription FROM Workers AS W " +
                "INNER JOIN WorkersShifts AS WS INNER JOIN WorkersRoles as WR INNER JOIN Roles AS R " +
                " WHERE W.ID = WS.WorkerID AND WS.WorkerID=WR.WorkerID AND WS.Role=WR.Role AND WR.Role=R.RoleID" +
                " AND ShiftDate=? AND ShiftDayPart=? AND PlaceId=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, shift.getDate());
            pstmt.setString(2, (shift.getShiftDayPart()).toString());
            pstmt.setString(3 , shift.getPlace().getId());
            ResultSet rs = pstmt.executeQuery();
            return mappingRSForShifts(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    // TODO: 25/05/2018 add the employment date
    private static Map<Worker, Role> mappingRSForShifts(ResultSet resultSet) {
        ResultSet rs = resultSet;
        Map<Worker, Role> data = new HashMap<>();
        try {
            if (!rs.isBeforeFirst()) return data;
            while (rs.next()) {
                String ID = rs.getString("ID");
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                String role = rs.getString("RoleName");
                String roleDesc = rs.getString("RoleDescription");
                String phoneNumber = rs.getString("PhoneNumber");
                data.put(new Worker(ID, fName, lName, phoneNumber , null), new Role(role , roleDesc));
            }

        } catch (SQLException e) {
        }

        return data;
    }


    public static List<Worker> selectAvailableWorkers(Shift shift) {
        String sql = "SELECT W.ID, W.FName, W.LName, W.PhoneNumber FROM Workers AS W " +
                "INNER JOIN WorkersAvailableShifts AS WAS " +
                "WHERE W.ID = WAS.WorkerID " +
                " AND WAS.DateAvailable=? AND WAS.DayPart=? AND WAS.PlaceId=? ";
        try {
            try (Connection connection = openConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setDate(1, shift.getDate());
                pstmt.setString(2, (shift.getShiftDayPart()).toString());
                pstmt.setString(3 , shift.getPlace().getId());
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
                String phoneNumber = resultSet.getString("PhoneNumber");
                Worker toAdd = new Worker(ID, fName, lName, phoneNumber ,null);
                toAdd.setRoles(WorkersDatabase.getRoles(ID));
                workers.add(toAdd);
            }
            return workers;
        } catch (SQLException e) {
            return workers;
        }
    }


    public static boolean insertShift(Shift s) {
        String sql = "INSERT INTO Shifts (ShiftDate, ShiftDayPart , PlaceId) Values (? , ? , ?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, s.getDate());
            pstmt.setString(2, s.getShiftDayPart().toString());
            pstmt.setString(3 , s.getPlace().getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public static List<Role> selectAllRoles() {
        List<Role> allRoles = new LinkedList<>();
        String sql = "Select RoleID , RoleName , RoleDescription From Roles";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Role r = new Role(resultSet.getString("RoleName") , resultSet.getString("RoleDescription"));
                allRoles.add(r);
            }
            return allRoles;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean insertRole(Role role) {
        String sql = "INSERT INTO Roles (RoleName , RoleDescription) Values (? , ?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, role.getRole());
            pstmt.setString(2 , role.getRoleDesc());
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
/*
    //returns the shifts without the workers scheduled in the shift
    public static List<Shift> getAllCreatedShifts() {
        List<Shift> allShifts = new LinkedList<>();
        String sql = "Select ShiftDate , ShiftDayPart , PlaceId From Shifts";
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
*/

    public static boolean shiftExists(Shift newShift) throws SQLException {
        String sql = "Select * From Shifts Where ShiftDate=? AND ShiftDayPart=? AND PlaceId = ?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, newShift.getDate());
            pstmt.setString(2, newShift.getShiftDayPart().toString());
            pstmt.setString(3 , newShift.getPlace().getId());
            ResultSet rs = pstmt.executeQuery();
            return rs.isBeforeFirst();
        }
    }

    public static boolean scheduleWorker(Shift newShift, Worker worker, Role role) {
        int roleID = getRoleIDByName(role.getRole());
        String sql = "INSERT INTO WorkersShifts (WorkerID , ShiftDate ,ShiftDayPart , Role, PlaceId) Values (? , ? , ? , ?, ?)";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, newShift.getDate());
            pstmt.setString(3, newShift.getShiftDayPart().toString());
            pstmt.setInt(4, roleID);
            pstmt.setString(5 , newShift.getPlace().getId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean isWorkerAvailableForShift(Worker worker, Shift shift) {
        String sql = "SELECT * FROM WorkersAvailableShifts WHERE WorkerID=? AND DateAvailable=? AND DayPart=? AND PlaceId = ?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, worker.getId());
            pstmt.setDate(2, shift.getDate());
            pstmt.setString(3, shift.getShiftDayPart().toString());
            pstmt.setString(4 , shift.getPlace().getId());
            ResultSet rs = pstmt.executeQuery();
            return !rs.isBeforeFirst();
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean isWorkerAssignedForShift(Worker worker, Shift shift) {
        String sql = "SELECT * FROM WorkersShifts WHERE PlaceId = ? AND WorkerID=? AND ShiftDate=? AND ShiftDayPart=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1 , shift.getPlace().getId());
            pstmt.setString(2, worker.getId());
            pstmt.setDate(3, shift.getDate());
            pstmt.setString(4, shift.getShiftDayPart().toString());
            return pstmt.executeQuery().isBeforeFirst();
        } catch (SQLException e) {
            return false;
        }
    }


    public static boolean isStoreKeeperExistInShift(Shift shift) {
        throw new NotImplementedException();
    }

    public static List<Worker> getShiftManagers() {
        String sql = "SELECT ID, FName, LName, PhoneNumber FROM Workers, WorkerRoles, Roles WHERE " +
                "Workers.ID=WorkerRoles.WorkerID AND WorkerRoles.Role = Roles.RoleID AND Roles.RoleName =?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "Shift Manager");
            return parseRStoListSM(pstmt.executeQuery());

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<Worker> parseRStoListSM(ResultSet rs) {
        List<Worker> parsed = new ArrayList<>();
        try {
            if (!rs.isBeforeFirst()) return parsed;
            while (rs.next()) {
                String ID = rs.getString("ID");
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                String phoneNum = rs.getString("PhoneNumber");
                Worker toAdd = new Worker(ID, fName, lName, phoneNum, null);
                parsed.add(toAdd);
            }
            return parsed;
        } catch (SQLException e) {
            e.printStackTrace();
            return parsed;
        }
    }

    public static boolean isShiftManager(Worker shiftManager) {
        String sql = "SELECT * FROM Workers, WorkerRoles, Roles WHERE " +
                "Workers.ID=WorkerRoles.WorkerID AND WorkerRoles.Role = Roles.RoleID AND Workers.ID = ?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, shiftManager.getId());
            return pstmt.executeQuery().isBeforeFirst();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Worker getShiftManager(Shift newShift) {
        String sql = "SELECT ManagerId FROM Shifts as s WHERE s.ShiftDate=? AND s.ShiftDayPart=? AND s.PlaceId=?";
        try (Connection connection = openConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, newShift.getDate());
            pstmt.setString(2, newShift.getShiftDayPart().toString());
            pstmt.setString(3, newShift.getPlaceId());
            return getWorker(pstmt.executeQuery().getString("ManagerId"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}