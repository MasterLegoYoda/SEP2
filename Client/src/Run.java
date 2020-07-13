import core.ClientFactory;
import core.ModelFactory;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Run extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ClientFactory clientFactory = new ClientFactory();
    ModelFactory modelFactory = new ModelFactory(clientFactory);
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start();
  }

}
