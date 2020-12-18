package ru.vsu.cs.controller;

import ru.vsu.cs.models.CPUDataModel;
import ru.vsu.cs.models.CPUsTableModel;
import ru.vsu.cs.utils.ArrayListUtils;
import ru.vsu.cs.utils.CSVWriter;
import ru.vsu.cs.view.View;

import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                if (view.getCPUsTable().isEnabled()) {
                    view.getCPUsTable().setEnabled(false);
                    view.getEditTextField().setText("OFF");
                }
                else {
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

        tableModel = new CPUsTableModel(dataModel.getCPUsData(), dataModel.getHeader());
        tableModel.addTableModelListener(e -> uploadDataToFile());
        cpusTable.setModel(tableModel);
        cpusTable.setEnabled(false);

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
        System.out.println("uploading...");
        JTable cpusTable = view.getCPUsTable();
        tableModel = (CPUsTableModel) cpusTable.getModel();
        String[] header = getHeader(cpusTable);
        Object[][] data = getData(tableModel);

        dataModel.setCPUsData(data);
        dataModel.setHeader(header);

        List<List<String>> allData = new ArrayList<>();
        allData.add(Arrays.asList(header));
        allData.addAll(ArrayListUtils.toList(data));



        try {
            printData(allData);
            CSVWriter csvWriter = new CSVWriter(dataModel.getFilePath());
            csvWriter.writeAll(allData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableModel = (CPUsTableModel) cpusTable.getModel();
    }

    private void printData(List<List<String>> data) {
        System.out.println(data);
    }

    private String[] getHeader(JTable table) {
        int columnCount = table.getModel().getColumnCount();
        String[] header = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            header[i] = table.getColumnName(i);
        }
        return header;
    }

    private Object[][] getData(DefaultTableModel tableModel) {
        int rowCounts = tableModel.getRowCount();
        int colCount = tableModel.getColumnCount();
        Object[][] tableData = new Object[rowCounts][colCount];

        for (int row = 0; row < rowCounts; row++) {
            for (int col = 0; col < colCount; col++) {
                Object value = tableModel.getValueAt(row, col);
                if (value == null || value == "" || value == " ") {
                    tableData[row][col] = null;
                }
                else if (col == 0) {
                    tableData[row][col] = value.toString();
                }
                else if (col == 1) {
                    try {
                        tableData[row][col] = Integer.parseInt(value.toString());
                    }
                    catch (Exception e) {
                        view.getCPUsTable().setValueAt(null, row, col);
                        tableData[row][col] = null;
                        System.out.println("Error tracing...");
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        tableData[row][col] = Double.parseDouble(value.toString());
                    }
                    catch (Exception e) {
                        view.getCPUsTable().setValueAt(null, row, col);
                        tableData[row][col] = null;
                        System.out.println("Error tracing...");
                        e.printStackTrace();
                    }
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
