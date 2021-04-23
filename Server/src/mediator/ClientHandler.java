package mediator;

import com.google.gson.Gson;
import javafx.application.Platform;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Model model;
  private boolean running;
  private Gson gson;
  private String clientName = "";
  private String request;

  public ClientHandler(Socket socket, Model model)
      throws IOException
  {
    model.addListener(this);
    this.model = model;
    this.socket = socket;
    this.gson = new Gson();

    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));

    this.out = new PrintWriter(socket.getOutputStream(), true);
  }

  @Override
  public void run()
  {
    running = true;
    while (running)
    {
      try
      {

        request = in.readLine();
        model.addLog("Client> " + request);

        switch (request){
          case "!commands":
            Message m1 = new Message("Server> !Number = users connected, !Exit = exit", "Normal");
            String reply1 = gson.toJson(m1);
            out.println(reply1);
            model.addLog("Server> !Number = users connected, !Exit = exit");
            break;
          case "!Number":
            Message m2 = new Message("Server> "+ model.getNrOfUsers() + "","Normal");
            String reply2 = gson.toJson(m2);
            out.println(reply2);
            model.addLog("Server> " + model.getNrOfUsers() + "");
            break;
          case "!Exit":
            String reply3 = gson.toJson(new Message("Exit","Normal"));
            out.println(reply3);
            model.addLog(clientName + " disconnected ");
            model.countUser(-1);
            break;
          case "Login":
            clientName = in.readLine();
            model.addLog("ClientName: " + clientName);
            break;
          default:
            model.addMessage(clientName + ": " + request);
            break;
        }

      }
      catch (Exception e)
      {
        model.addLog("Client error");
        close();
      }
    }
    close();
  }
  
  public void close()
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      //
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if(evt.getPropertyName().equals("message")){
        String reply = gson.toJson(new Message(evt.getNewValue()+ "", "BROADCAST"));
        out.println(reply);
      }
    });
  }
}
