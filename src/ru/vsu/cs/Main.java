package ru.vsu.cs;

import ru.vsu.cs.controller.Controller;
import ru.vsu.cs.models.CPUDataModel;
import ru.vsu.cs.view.MainFrame;

import java.io.FileNotFoundException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Locale.setDefault(Locale.ROOT);
        CPUDataModel model = new CPUDataModel();
        MainFrame view = new MainFrame();
        Controller controller = new Controller(model, view);
        controller.init();
    }
}
