import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class MyApplication extends Application {
    /**
     * A method to start the library
     * @param primaryStage primary stage
     */
    public void start(Stage primaryStage){
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);

    }
}
