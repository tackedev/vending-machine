package view;

import java.io.IOException;

/**
 * @author tackedev
 */
public interface View {

    void showMenu() throws IOException;

    void cancel();

    void showResult();
}
