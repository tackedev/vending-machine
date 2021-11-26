package controller;

import service.CanceledRequestException;
import service.FinishedStepException;
import service.Service;
import util.IOUtil;
import view.View;

import java.io.IOException;

/**
 * @author tackedev
 */
public class CashInsertingController implements Controller {

    private final Service service;
    private final View view;
    private final Controller nextController;

    public CashInsertingController(Service service, View view, Controller nextController) {
        this.service = service;
        this.view = view;
        this.nextController = nextController;
    }

    @Override
    public void process() throws IOException {

        view.showMenu();

        char input = IOUtil.getChar("Input the number (C=Cancel | N=Next): ",
                '1', '2', '3', '4', '5', 'C', 'c', 'N', 'n');

        try {
            service.execute(input);
        } catch (CanceledRequestException ex) {
            view.cancel();
        } catch (FinishedStepException ex) {
            nextController.process();
        }
    }

}
