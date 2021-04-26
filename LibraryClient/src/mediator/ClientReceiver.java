package mediator;

import java.io.BufferedReader;

public class ClientReceiver implements Runnable {
    private BufferedReader in;
    private LibraryClient client;

    public ClientReceiver(LibraryClient client, BufferedReader in){
        this.in = in;
        this.client = client;
    }

    @Override
    public void run() {
        while (true){
            try {
                String serverReply = in.readLine();

            } catch (Exception e){

            }
        }
    }
}

