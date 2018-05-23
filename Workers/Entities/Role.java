package Entities;

public class Role {

    private String role;

    public Role(String role) {
        this.role = role;
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
}
