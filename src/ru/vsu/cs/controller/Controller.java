package ru.vsu.cs.controller;

import ru.vsu.cs.models.CPUDataModel;
import ru.vsu.cs.models.CPUsTableModel;
import ru.vsu.cs.view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private CPUDataModel dataModel;
    private CPUsTableModel tableModel;
    private View view;


    // private final float[] columnWidthPercentage = {0.49f, 0.11f, 0.14f, 0.1f, 0.1f, 0.06f};

    public Controller(CPUDataModel model, View view) {
        this.dataModel = model;
        this.view = view;
        initTable();
        initEditButton();

    }

    private void initEditButton() {
        JButton editButton = this.view.getEditButton();
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModel.isEditable()) {
                    tableModel.setEditable(false);
                    view.getCPUsTable().setEnabled(false);
                    view.getEditTextField().setText("OFF");
                }
                else {
                    tableModel.setEditable(true);
                    view.getCPUsTable().setEnabled(true);
                    view.getEditTextField().setText("ON");
                }
            }
        });
    }

    private void initTable() {
        /*CPUsTable.setModel(new CPUsTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        */
        JTable cpusTable = view.getCPUsTable();
        cpusTable.setCellSelectionEnabled(true);
        cpusTable.getTableHeader().setReorderingAllowed(false);
        cpusTable.getTableHeader().setResizingAllowed(false);
        cpusTable.setShowGrid(true);
        cpusTable.setIntercellSpacing(new Dimension(1, 1));
        cpusTable.setFillsViewportHeight(false);
        cpusTable.setDragEnabled(false);

        tableModel = new CPUsTableModel(this.dataModel.getCPUsData(), this.dataModel.getHeader());
        tableModel.addTableModelListener(e -> uploadDataToFile());
        this.view.getCPUsTable().setModel(tableModel);
        this.view.getCPUsTable().setEnabled(false);

        // this.view.getCPUsTable().setModel(new DefaultTableModel(this.model.getCPUsData(), this.model.getHeader()));

    }

    public void initController() {
        JTable cpusTable = view.getCPUsTable();
        cpusTable.getTableHeader().setResizingAllowed(true);
        /*
        cpusTable.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeColumns(e);
            }
        });*/
    }

    private void uploadDataToFile() {
        JTable cpusTable = view.getCPUsTable();
        // TODO
    }

    private static String[][] readDataFromTableModel(DefaultTableModel tableModel) {
        int rowCounts = tableModel.getRowCount();
        int colCount = tableModel.getColumnCount();
        String[][] tableData = new String[rowCounts][colCount];

        for (int row = 0; row < rowCounts; row++) {
            for (int col = 0; col < colCount; col++) {
                Object value = tableModel.getValueAt(row, col);
                if (value != null) {
                    tableData[row][col] = value.toString();
                }
                else {
                    tableData[row][col] = "";
                }
            }
        }
        return tableData;
    }

    /*private void resizeColumns(ComponentEvent e) {
        TableColumnModel columnModel = ((JTable) e.getComponent()).getColumnModel();
        int tableWidth = columnModel.getTotalColumnWidth();
        TableColumn column;
        int cantCols = columnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = columnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tableWidth);
            column.setPreferredWidth(pWidth);
        }
    }*/
}
