package mediator;


import com.google.gson.Gson;
import model.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class LibraryClient implements ServerModel{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Model model;
    private Gson gson;
    private ClientReceiver clientReceiver;


    /**
     * Creates a library client that starts the connection and creates a client receiver
     * @param host host
     * @param port port
     * @param model model
     */
    public LibraryClient(String host, int port, Model model){
        try {
            socket = new Socket(host, port);
            this.model = model;
            this.gson = new Gson();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            clientReceiver = new ClientReceiver(this, in);

        } catch (Exception e){

        }
    }

    /**
     *  Method to receive strings from the server
     * @param s string
     * @throws IOException connection exception
     */
    public void receive(String s) throws IOException {
        Message m = gson.fromJson(s, Message.class);
        switch (m.getType()){
            case "Load_library":
                Book book = gson.fromJson(m.getMessage(), Book.class);
                model.addBookToLibrary(book);
                break;

        }



    }


    /**
     *  Method that logins the client
     * @param username the username
     * @param password the password
     */
    @Override public boolean Login(String username, String password){
        out.println("Login");
        out.println(username);
        out.println(password);

        try {
            String readLine = in.readLine();
             Message m = gson.fromJson(readLine,Message.class);
            if(m.getType().equals("Message")){
                if(m.getMessage().equals("Login verified")){
                    model.setUser(m.getUser());
                    Thread t1 = new Thread(clientReceiver, "");
                    t1.start();
                    out.println("Load_Library_inventory");
                    return true;
                } else if(m.getMessage().equals("Wrong Username")){
                    model.setErrorLabel("Wrong Username");
                } else if(m.getMessage().equals("Wrong Password")){
                    model.setErrorLabel("Wrong Password");
                }
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    @Override public void Register (UserType User){
        out.println("Register");
        String RegisterUser = gson.toJson(User);
        out.println(RegisterUser);
    }

    @Override public void addBookToServerLibrary(Book book){
        out.println("Add_Book");
        String reply = gson.toJson(book);
        out.println(reply);
    }
}
