package view;

import service.CanceledRequestException;
import service.FinishedStepException;

import java.io.IOException;

/**
 * @author tackedev
 */
public interface View {

    void showMenu() throws IOException;

    void cancel(CanceledRequestException ex) throws IOException;

    void showResult(FinishedStepException ex) throws IOException;
}
