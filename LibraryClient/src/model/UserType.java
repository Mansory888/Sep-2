package model;

import java.util.regex.Pattern;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public abstract class UserType {
    private String email;
    private String username;
    private String password;
    private UserInventory userInventory;


    /**
     * Creates a user type
     * @param email email
     * @param username username
     * @param password password
     */
    public UserType (String email, String username, String password){
        if(email !=null && username!=null && password!=null && !email.equals("") && !username.equals("") && !password.equals("")){
            Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
            if( !emailPattern.matcher(email).find()){
                throw new IllegalArgumentException("Entered email address is not valid");
            }
            if(username.length()<4){
                throw new IllegalArgumentException("Username is shorter than 4 characters.");
            }
            Pattern usernamePattern = Pattern.compile("^(?=.*[-+_!@#$%^&*., ?])");
            if(usernamePattern.matcher(username).find()){
                throw new IllegalArgumentException("Username contains special symbols");
            }
            if(password.length()<6){
                throw new IllegalArgumentException("Password is shorter than 6 characters.");
            }
            Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=."
                    + "*[A-Z])(?=.*\\d)"
                    + "(?=.*[-+_!@#$%^&*., ?]).+$");
            if(!passwordPattern.matcher(password).find()){
                throw new IllegalArgumentException("Password has to contain 1 Uppercase letter, 1 lowercase letter, 1 number and 1 symbol.");
            }
            this.email = email;
            this.username = username;
            this.password = password;
            this.userInventory = new UserInventory();
        }else{
            throw  new IllegalArgumentException("One or more fields are left empty.");
        }

    }

    /**
     * returns the email
     * @return email
     */
    public String getEmail(){return email;}

    /**
     * returns username
     * @return username
     */
    public String getUsername(){return username;}

    /**
     * returns password
     * @return password
     */
    public String getPassword(){return password;}

    /**
     * abstract method is admin
     * @return is admin
     */
    public abstract boolean isAdmin();

    /**
     * returning the user's library
     *
     * @return user library
     */
    public UserInventory getUserInventory() {
        return userInventory;
    }

}
