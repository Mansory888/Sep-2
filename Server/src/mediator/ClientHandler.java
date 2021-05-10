package mediator;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.sun.webkit.Timer;
import javafx.application.Platform;
import model.*;

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
          case "Borrow_book":
            String id = in.readLine();
            model.getUserInventory().addBook(model.getLibraryInventory().getBookById(id));
            model.addLog("Book borrowed: " + id);
            break;
          case "Remove_book":
            String id1 = in.readLine();
            model.getLibraryInventory().removeBookById(id1);
            model.addLog("Remove book: " + id1);
            break;
          case "Return_book":
            String id2 = in.readLine();
            model.getUserInventory().returnedBookById(id2);
            model.addLog("Book returned: " + id2);
            break;
          case "Load_Library_inventory":
            for (int i = 0; i < model.getLibraryInventory().getSize(); i++){
              Message serveReply = new Message(gson.toJson(model.getLibraryInventory().getBook(i)), "Load_library");
              out.println(gson.toJson(serveReply));
            }
            break;
          case "Add_Book":
            String book = in.readLine();
            Book book1 = gson.fromJson(book, Book.class);
            model.addBookToLibrary(book1);
            model.addLog("Book added to library: "+ book1.getTitle() + " "+ book1.getAuthor());
            break;
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
                  Message login = new Message("Login verified", "Message");
                  if(model.getAllUsers().get(i).isAdmin()){

                    login.setAdmin((Admin) model.getAllUsers().get(i));
                    System.out.println("Is admin true?: " + login.getUser().isAdmin());
                  }else{
                    login.setCustomer((Customer) model.getAllUsers().get(i));
                  }
                  String loginVerified = gson.toJson(login);
                  out.println(loginVerified);
                  break;
                }
              }
            }

            if (!usernameVerification){
              String wrongUsername = gson.toJson(new Message("Wrong Username", "Message"));
              out.println(wrongUsername);
            } else if (!passwordVerification){
              String wrongPassword = gson.toJson(new Message("Wrong Password", "Message"));
              out.println(wrongPassword);
            }else{
//          party
            }
            break;

        }

      }
      catch (Exception e)
      {
        model.addLog("Connection with the client ended.");
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
