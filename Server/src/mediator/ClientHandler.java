package mediator;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import javafx.application.Platform;
import model.Customer;
import model.Message;
import model.Model;
import model.UserType;

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
          case "Register":
            String user = in.readLine();
            UserType userType = gson.fromJson(user, Customer.class);
            model.addUser(userType);
            model.addLog("User Registered: "+ userType.toString());
            break;

          case "Login":
            String username = in.readLine();
            String password = in.readLine();
            boolean usernameVerification = false;
            boolean passwordVerification = false;

            for (int i = 0; i<model.getAllUsers().size(); i++){
              if (model.getAllUsers().get(i).getUsername().equals(username)){
                usernameVerification = true;
                if (model.getAllUsers().get(i).getPassword().equals(password)){
                  passwordVerification = true;
                  String LoginVerified = gson.toJson(new Message("Login verified", "Message"));
                  out.println(LoginVerified);
                }
              }
            }

            if (usernameVerification = false){
              String wrongUsername = gson.toJson(new Message("Wrong Username", "Message"));
              out.println(wrongUsername);
            } else if (passwordVerification = false){
              String wrongPassword = gson.toJson(new Message("Wrong Password", "Message"));
              out.println(wrongPassword);
            }
            break;

        }

      }
      catch (Exception e)
      {
        model.addLog("Client error");
        e.printStackTrace();
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
        //String reply = gson.toJson(new Message(evt.getNewValue()+ "", "BROADCAST"));
        //out.println(reply);
      }
    });
  }
}
