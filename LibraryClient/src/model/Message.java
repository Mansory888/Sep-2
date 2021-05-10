package model;

public class Message<T> {
    private String message;
    private String type;
    private UserType user;
    private Admin admin;
    private Customer customer;

    public Message(String message, String type){
        this.message = message;
        this.type = type;
        user=null;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public String getType(){
        return type;
    }
    public UserType  getUser(){
        if(customer!=null){
            return customer;
        }else{
            return admin;
        }
    }

    public void setCustomer(Customer user) {
        this.customer = user;
    }
    public void setAdmin(Admin user){
        this.admin=user;
    }
}
