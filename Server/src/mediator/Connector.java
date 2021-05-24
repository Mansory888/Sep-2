package mediator;

import model.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class Connector implements Runnable
{
  private final int PORT = 6789;
  private Model model;
  private boolean running;
  private ServerSocket welcomeSocket;

  /**
   * Creates a connector
   * @param model model
   */
  public Connector(Model model)
  {
    this.model = model;
  }

  /**
   * A method to implement the Runnable interface
   */
  @Override public void run()
  {
    try
    {
      model.addLog("Starting Server...");
      BookDAOImpl.getInstance();
      model.getLibraryInventory().getBooks().addAll(BookDAOImpl.getInstance().readAllBooks());
      model.getAllUsers().addAll(UserDAOImpl.getInstance().readAllUsers());


      ArrayList<Rating> ratingArrayList = new ArrayList<>();
      ratingArrayList.addAll(RatingDaoImpl.getInstance().getAllRatings());
      for (int i = 0; i< ratingArrayList.size(); i++){
        model.getLibraryInventory().getBookById(ratingArrayList.get(i).getBook_id()).
                setRating(ratingArrayList.get(i).getRating(),
                ratingArrayList.get(i).getUsername());

        model.rateBookInUserInventory(ratingArrayList.get(i).getRating(),
                ratingArrayList.get(i).getUsername(),
                ratingArrayList.get(i).getBook_id() );
      }

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
    catch (SQLException sql){
      model.addLog(sql.getMessage());
    }
  }

  /**
   * A method to close the connection
   */
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
