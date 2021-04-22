package model;

public abstract class UserType {
    private String email;
    private String username;
    private String password;




    public UserType (String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;

    }

    public String getEmail(){return email;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public abstract boolean isAdmin();

}
