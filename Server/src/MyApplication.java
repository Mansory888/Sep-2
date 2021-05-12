import javafx.application.Application;
import javafx.stage.Stage;
import mediator.Connector;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class MyApplication extends Application
{
  /**
   * A method to start the server
   * @param primaryStage primary stage
   */
  public void start(Stage primaryStage)
  {

    try {
      Model model = new ModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);
      Connector C = new Connector(model);
      Thread t1 = new Thread(C,"");
      t1.start();
      view.start(primaryStage);

    } catch (Exception e){

    }

  }
}
