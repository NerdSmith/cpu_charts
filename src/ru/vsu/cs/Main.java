package ru.vsu.cs;

import ru.vsu.cs.controller.Controller;
import ru.vsu.cs.models.CPUDataModel;
import ru.vsu.cs.view.View;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        CPUDataModel model = new CPUDataModel();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.initController();
    }
}
