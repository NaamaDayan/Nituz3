package DAL.DAL_T;

import BL.BL_T.Entities.Driver;
import BL.BL_T.Entities.LicenseTypeForTruck;
import BL.BL_W.Entities_W.BankAccount;
import BL.BL_W.Entities_W.Role;
import DAL.Tables;

import java.sql.*;
import java.util.List;

import static DAL.DAL_W.WorkersDatabase.getRoles;

public class Drivers {



    public static Driver retrieveDriver(String id){
        try (Connection conn = Tables.openConnection()) {
            String query = "SELECT * FROM Workers, WorkersRoles, Roles, BankAccounts WHERE WorkersRoles.WorkerID = ID "+
            "AND ID=BankAccounts.WorkerID "+
                    "AND RoleID = Role AND RoleName = (?) AND Workers.ID = (?) ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "Driver");
            stmt.setString(2, id);
            ResultSet rs = stmt.executeQuery();
            Driver driver = createDriver(rs);
            conn.close();
            return driver;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }




    public static Driver createDriver(ResultSet rs) throws SQLException {
        if (!rs.isBeforeFirst()) //not exists)
            return null;
        String id = rs.getString("ID");
        String fName = rs.getString("FName");
        String lName = rs.getString("LName");
        Date employmentDate = rs.getDate("EmploymentDate");
        String bankCode = rs.getString("BankCode");
        String accountNumber = rs.getString("AccountNumber");
        String branchNumber = rs.getString("BranchNumber");
        List<Role> roles = getRoles(id);
        String phoneNumber = rs.getString("PhoneNumber");
        List<LicenseTypeForTruck> licenses = DriversLicenses.retrieveDriverLicenses(id);
        return new Driver(id, fName, lName, phoneNumber, employmentDate, new BankAccount(bankCode, accountNumber, branchNumber), roles, licenses);
    }
}
