package mediator;


import com.google.gson.Gson;
import model.Model;

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


            ClientReceiver clientReceiver = new ClientReceiver(this, in);
            Thread t1 = new Thread(clientReceiver, "");
            t1.start();

        } catch (Exception e){

        }
    }

    /**
     *  Method to receive strings from the server
     * @param s string
     * @throws IOException connection exception
     */
    public void receive(String s) throws IOException {

    }


    /**
     *  Method that logins the client
     * @param username the username
     * @param password the password
     */
    @Override public void Login(String username, String password){
        out.println("Login");
        out.println();
    }
}