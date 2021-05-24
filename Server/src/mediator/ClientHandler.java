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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

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

  /**
   * Creates a Client Handler
   * @param socket socket
   * @param model model
   * @throws IOException exception
   */
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

  /**
   * A method to implement the Runnable interface and start the communication protocol
   */
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
            String username1 = in.readLine();
          try{
            model.borrowBook(id, username1);
            BorrowedBooksDAOImpl.getInstance().create(id,username1);
            model.addLog("Book borrowed: " + id);
          }catch (SQLException e){
            model.addLog(e.getMessage());
          }
            break;
          case "Remove_book":
            String id1 = in.readLine();
          try {
            model.getLibraryInventory().removeBookById(id1);
            BookDAOImpl.getInstance().remove(id1);
            model.addLog("Remove book: " + id1);
          }catch (SQLException e){
            model.addLog(e.getMessage());
          }
            break;
          case "Edit_book":
            String id2 = in.readLine();
            String editedTitle = in.readLine();
            String editedAuthor = in.readLine();
            String editedYear = in.readLine();
            String editedDescription = in.readLine();
            String editedGenre = in.readLine();
          try {
            model.getLibraryInventory().getBookById(id2).setTitle(editedTitle);
            model.getLibraryInventory().getBookById(id2).setAuthor(editedAuthor);
            model.getLibraryInventory().getBookById(id2).setYear(Integer.parseInt(editedYear));
            model.getLibraryInventory().getBookById(id2).setDescription(editedDescription);
            model.getLibraryInventory().getBookById(id2).setGenre(editedGenre);
            BookDAOImpl.getInstance().updateBook(id2, editedTitle, editedAuthor, Integer.parseInt(editedYear), editedDescription, editedGenre);
            model.addLog("Edited book: " + id2);
          }catch (SQLException e){
            model.addLog(e.getMessage());
          }
            break;
          case "Return_book":
            String id3 = in.readLine();
            String username2 = in.readLine();
            model.returnBook(id3, username2);
            model.addLog("Book returned: " + id3);
            break;
          case "Rate_book":
            String id4 = in.readLine();
            String username3 = in.readLine();
            String rating = in.readLine();
            model.getLibraryInventory().getBookById(id4).setRating(Integer.parseInt(rating), username3);
            model.rateBookInUserInventory(Integer.parseInt(rating),username3,id4);
            model.addLog("Book rated: "+ model.getLibraryInventory().getBookById(id4).toString() + " rating: " + rating);
            break;
          case "Load_Library_inventory":
            for (int i = 0; i < model.getLibraryInventory().getSize(); i++){
              Message serveReply = new Message(gson.toJson(model.getLibraryInventory().getBook(i)), "Load_library");
              out.println(gson.toJson(serveReply));
            }
            break;
          case "Load_notifications":
            for(int i = 0; i<model.getNotifications().size(); i++){
              out.println(gson.toJson(new Message(model.getNotifications().get(i), "notification")));
            }
            break;
          case "Add_Book":
            String book = in.readLine();
          try{
            Book book1 = gson.fromJson(book, Book.class);
            model.addBookToLibrary(book1);
            BookDAOImpl.getInstance().create(book1);
            out.println(gson.toJson(new Message(book1.toString(), "Alert")));
            model.getNotifications().add("Book Added: " + book1.toString());
            model.addLog("Book added to library: " + book1.getTitle() + " " + book1.getAuthor());
          }catch (SQLException e){
            model.addLog(e.getMessage());
          }
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

          try {
            for (int i = 0; i < model.getAllUsers().size(); i++) {
              if (model.getAllUsers().get(i).getUsername().equals(username)) {
                usernameVerification = true;
                if (model.getAllUsers().get(i).getPassword().equals(password)) {
                  passwordVerification = true;
                  Message login = new Message("Login verified", "Message");

                  if (model.getAllUsers().get(i).isAdmin()) {

                    login.setAdmin((Admin) model.getAllUsers().get(i));
                  } else {
                    login.setCustomer((Customer) model.getAllUsers().get(i));
                  }
                  login.getUser().getUserInventory().getBooks().addAll(BorrowedBooksDAOImpl.getInstance().readAllBooks(username));
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
            }
          }catch (SQLException e){
            model.addLog(e.getMessage());
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

  /**
   * A method to close the server
   */
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

  /**
   * A method that implements property change listener
   * @param evt event
   */
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
