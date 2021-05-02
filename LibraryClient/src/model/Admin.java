package model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class Admin extends UserType{
    private boolean isAdmin;

    /**
     *  Creates and user type as admin
     * @param email email
     * @param username username
     * @param password password
     */
    public Admin(String email, String username, String password){
        super(email, username, password);
        isAdmin = true;
    }

    /**
     * returns is admin
     * @return is admin
     */
    @Override public boolean isAdmin(){
        return isAdmin;
    }
}
