package controller;

import dto.ProductStorage;
import service.CanceledRequestException;
import service.FinishedStepException;
import service.Service;
import util.ConsoleUtil;
import view.View;

import java.io.IOException;

/**
 * @author tackedev
 */
public class ProductSelectingController implements Controller {

    private final Service service;
    private final View view;

    ProductStorage productStorage;

    public ProductSelectingController(Service service, View view) throws IOException {
        this.service = service;
        this.view = view;

        productStorage = ProductStorage.getInstance();
    }

    @Override
    public void process() throws IOException, FinishedStepException, CanceledRequestException {
        view.showMenu();

        char[] acceptedValues = new char[productStorage.size() + 4];
        acceptedValues[0] = 'P'; acceptedValues[1] = 'p';
        acceptedValues[2] = 'C'; acceptedValues[3] = 'c';
        for (int i = 0; i < productStorage.size(); i++) {
            acceptedValues[4 + i] = Character.forDigit(i+1, 10);
        }
        char input = ConsoleUtil.getChar("Input the number (P=Pay | C=Cancel): ", acceptedValues);

        try {
            service.execute(input);
        } catch (FinishedStepException ex) {
            view.showResult(ex);
            throw ex;
        }
    }
}
