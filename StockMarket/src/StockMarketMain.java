import java.io.IOException;

import controller.Controller;
import controller.ControllerImpl;
import model.Model;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

public class StockMarketMain {

  public static void main(String[] args) {
    Model model = new ModelImpl(); //set up before if needed
    View view = new ViewImpl(System.out);
    //setup details of view if needed

      //create controller, give it the model and view
      Controller controller = new ControllerImpl(model, System.in, view);
      //give control to the controller. Controller relinquishes only when program ends
      controller.go();
  }

}
