import controller.CashInsertingController;
import controller.Controller;
import controller.ProductSelectingController;
import service.CanceledRequestException;
import service.CashInsertingService;
import service.FinishedStepException;
import service.ProductSelectingService;
import service.Service;
import view.CashInsertingView;
import view.ProductSelectingView;
import view.View;

import java.io.IOException;

/**
 * @author tackedev
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // Init Select products function
        Service productSelectingService = new ProductSelectingService();
        View productSelectingView = new ProductSelectingView();
        Controller productSelectingController = new ProductSelectingController(
                productSelectingService,
                productSelectingView
        );

        // Init Insert cash function
        Service cashInsertingService = new CashInsertingService();
        View cashInsertingView = new CashInsertingView();
        Controller cashInsertingController = new CashInsertingController(
                cashInsertingService,
                cashInsertingView,
                productSelectingController
        );

        // Run application
        while (true) {
            try {
                cashInsertingController.process();
            } catch (FinishedStepException | CanceledRequestException ex) {
                ex.printStackTrace();
            }
        }

    }

}
