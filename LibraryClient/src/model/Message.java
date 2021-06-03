package model;

import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class Message<T> {
    private String message;
    private String type;
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

    /**
     * A method to add an admin
     * @param admin admin
     */
    public void addAdmin(Admin admin)
    {
        admins.add(admin);
    }

    /**
     * A method to add a customer
     * @param customer customer
     */
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }

    /**
     * Returns  all the admins
     * @return all the admins
     */
    public ArrayList<Admin> getAdmins()
    {
        return  admins;
    }

    /**
     * Returns all the customers
     * @return all the customers
     */
    public ArrayList<Customer> getCustomers()
    {
        return customers;
    }
}
