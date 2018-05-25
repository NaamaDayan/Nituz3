package BL.BL_W.Entities_W;

public class Role {

    private String role;
    private String description;

    public Role(String role , String description) {
        this.role = role;
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Role))
            return false;
        Role other = (Role)obj;
        return this.role.toLowerCase().equals(other.role.toLowerCase());
    }

    public String getDescription() {
        return description;
    }
}
