package mediator;


import com.google.gson.Gson;
import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LibraryClient implements ServerModel{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Model model;
    private Gson gson;


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

    public void receive(String s) throws IOException {

    }



    @Override public void Login(String s){
        out.println("Login");
        out.println(s);
    }
}
