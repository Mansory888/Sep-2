package model;

public class Message {
    private String message;
    private String type;

    public Message(String message, String type){
        this.message = message;
        this.type = type;
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
}
