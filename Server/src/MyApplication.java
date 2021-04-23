import javafx.application.Application;
import javafx.stage.Stage;
import mediator.Connector;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
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
