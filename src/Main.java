import controller.CashInsertingController;
import controller.Controller;
import controller.ProductSelectingController;
import service.CashInsertingService;
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

        Service productSelectingService = new ProductSelectingService();
        View productSelectingView = new ProductSelectingView();
        Controller productSelectingController = new ProductSelectingController(productSelectingService, productSelectingView);



        Service cashInsertingService = new CashInsertingService();
        View cashInsertingView = new CashInsertingView();
        Controller cashInsertingController = new CashInsertingController(cashInsertingService, cashInsertingView, productSelectingController);

        while (true) {
            cashInsertingController.process();
        }
    }
}
