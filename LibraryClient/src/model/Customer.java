package model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class Customer extends UserType{
    private boolean isAdmin;



    /**
     *  Creates and user type as Customer
     * @param email email
     * @param username username
     * @param password password
     */
    public Customer(String email, String username, String password){
        super(email, username, password);
        this.isAdmin = false;
    }

    /**
     * returns is admin
     * @return is admin
     */
    @Override public boolean isAdmin(){
        return isAdmin;
    }

}
