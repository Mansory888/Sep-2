package model;

import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class Message<T> {
    private String message;
    private String type;
    private UserType user;
    private Admin admin;
    private Customer customer;
    private ArrayList<Admin> admins;
    private ArrayList<Customer> customers;

    /**
     * Creates a message
     * @param message message string
     * @param type type of the message
     */
    public Message(String message, String type){
        this.message = message;
        this.type = type;
        user=null;
        this.admins= new ArrayList<>();
        this.customers= new ArrayList<>();
    }

    /**
     * Sets the  message
     * @param message message
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * Returns the message
     * @return message
     */
    public String getMessage(){
        return message;
    }

    /**
     * Returns the type of the message
     * @return type
     */
    public String getType(){
        return type;
    }

    /**
     * Returns the user
     * @return user
     */
    public UserType  getUser(){
        if(customer!=null){
            return customer;
        }else{
            return admin;
        }
    }

    /**
     * Sets the customer
     * @param user user
     */
    public void setCustomer(Customer user) {
        this.customer = user;
    }

    /**
     * Sets the admin
     * @param user user
     */
    public void setAdmin(Admin user){
        this.admin=user;
    }

    public void addAdmin(Admin admin)
    {
        admins.add(admin);
    }
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
    public ArrayList<Admin> getAdmins()
    {
        return  admins;
    }
    public ArrayList<Customer> getCustomers()
    {
        return customers;
    }
}
