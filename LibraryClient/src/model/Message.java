package model;

public class Message<T> {
    private String message;
    private String type;
    private T user;

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
    public T  getUser(){
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }
}
