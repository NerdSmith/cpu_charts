package ru.vsu.cs.models;



import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class CPUsTableModel extends DefaultTableModel {
    private boolean editable = false;

    public CPUsTableModel(Object[][] data, String[] header) {
        super(data, header);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }
}
