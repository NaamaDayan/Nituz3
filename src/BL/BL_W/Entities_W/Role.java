package BL.BL_W.Entities_W;

public class Role {

    private String role;
    private String roleDesc;


    /**
     * this constructor is used when the admin wants to register a new role that doesn't exist
     * in the database. in this scenario he has to enter the role description as well
     * @param role
     * @param roleDesc
     */
    public Role(String role, String roleDesc) {
        this.role = role;
        this.roleDesc = roleDesc;
    }

    /**
     * this constructor is used to initialize a role that is already in the database
     * meaning this constructor creates a container for the roleName without the use
     * of role description
      * @param role
     */
    public Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Role))
            return false;
        Role other = (Role) obj;
        return this.role.toLowerCase().equals(other.role.toLowerCase()) &&
                this.roleDesc.toLowerCase().equals(other.roleDesc.toLowerCase());
    }
}
