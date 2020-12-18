package ru.vsu.cs.models;



import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class CPUsTableModel extends DefaultTableModel {

    public CPUsTableModel(Object[][] data, String[] header) {
        super(data, header);
    }
}
