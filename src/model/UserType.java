package model;

import java.util.regex.Pattern;

public abstract class UserType {
    private String email;
    private String username;
    private String password;




    public UserType (String email, String username, String password){
        if(email !=null && username!=null && password!=null && !email.equals("") && !username.equals("") && !password.equals("")){
            Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
            if( !p.matcher(email).find()){
                throw new IllegalArgumentException("Entered email address is not valid");
            }
            this.email = email;
            this.username = username;
            this.password = password;
        }else{
            throw  new IllegalArgumentException("One or more fields are left empty.");
        }

    }

    public String getEmail(){return email;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public abstract boolean isAdmin();

}
