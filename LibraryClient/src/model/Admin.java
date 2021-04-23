package model;

public class Admin extends UserType{
    private boolean isAdmin;

    public Admin(String email, String username, String password){
        super(email, username, password);
        isAdmin = true;
    }

    @Override public boolean isAdmin(){
        return isAdmin;
    }
}
