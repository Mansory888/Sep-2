package model;

public class Customer extends UserType{
    private boolean isAdmin;


    public Customer(String email, String username, String password){
        super(email, username, password);
        this.isAdmin = false;
    }

    @Override public boolean isAdmin(){
        return isAdmin;
    }

}
