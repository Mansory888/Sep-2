package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector implements Runnable
{
  private final int PORT = 6789;
  private Model model;
  private boolean running;
  private ServerSocket welcomeSocket;

  public Connector(Model model)
  {
    this.model = model;
  }

  @Override public void run()
  {
    try
    {
      model.addLog("Starting Server...");
      welcomeSocket = new ServerSocket(PORT);

      running = true;
      while (running)
      {
        model.addLog("Waiting for a client...");
        Socket socket = welcomeSocket.accept();
        model.countUser(1);

        ClientHandler clientHandler = new ClientHandler(socket,
            model);

        Thread clientThread = new Thread(clientHandler);
        clientThread.setDaemon(true);
        clientThread.start();
      }
    }
    catch (IOException e)
    {
      model.addLog("Error: " + e.getMessage());
    }
  }

  public void close()
  {
    running = false;
    try
    {
      welcomeSocket.close();
    }
    catch (Exception e)
    {
      //
    }
  }

}
