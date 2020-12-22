package ru.vsu.cs;

import ru.vsu.cs.models.CPUsDataModel;

import java.io.FileNotFoundException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Locale.setDefault(Locale.ROOT);
        CPUsDataModel model = new CPUsDataModel();
        MainFrame view = new MainFrame();
        Controller controller = new Controller(model, view);
        controller.init();
    }
}
