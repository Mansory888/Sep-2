package mediator;

import java.io.BufferedReader;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class ClientReceiver implements Runnable {
    private BufferedReader in;
    private LibraryClient client;

    /**
     *  Creates a client receiver
     * @param client
     * @param in
     */
    public ClientReceiver(LibraryClient client, BufferedReader in){
        this.in = in;
        this.client = client;
    }

    /**
     * reads from the server
     */
    @Override
    public void run() {
        while (true){
            try {
                String serverReply = in.readLine();
                client.receive(serverReply);

            } catch (Exception e){

            }
        }
    }
}

