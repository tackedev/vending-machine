package controller;

import service.CanceledRequestException;
import service.FinishedStepException;

import java.io.IOException;

public interface Controller {

    void process() throws IOException, FinishedStepException, CanceledRequestException;

}
