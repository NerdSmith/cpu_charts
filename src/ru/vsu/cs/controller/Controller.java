package ru.vsu.cs.controller;

import ru.vsu.cs.models.CPUDataModel;
import ru.vsu.cs.models.CPUsTableModel;
import ru.vsu.cs.utils.ArrayListUtils;
import ru.vsu.cs.utils.CSVWriter;
import ru.vsu.cs.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private CPUDataModel dataModel;
    private CPUsTableModel tableModel;
    private MainFrame view;

    public Controller(CPUDataModel model, MainFrame view) {
        this.dataModel = model;
        this.view = view;
    }

    public void init() {
        initTable();
        initEditButton();
        initAddNewCPUButton();
        initDeleteCPUButton();
    }

    private void initDeleteCPUButton() {
        JButton deleteCPUButton = view.getDeleteCPUButton();
        deleteCPUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getCPUsTable().isEnabled()) {
                    int selectedRow = view.getCPUsTable().getSelectedRow();
                    if (selectedRow != -1) {
                        tableModel.removeRow(selectedRow);
                    }
                }
            }
        });
    }

    private void initEditButton() {
        JButton editButton = view.getEditButton();
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

    private void initAddNewCPUButton() {
        JButton addNewCPUButton = view.getAddNewCPUButton();
        addNewCPUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getCPUsTable().isEnabled()) {
                    Object[] newCPU = new Object[dataModel.getHeader().length];
                    tableModel.addRow(newCPU);
                }
            }
        });
    }

    private void initTable() {
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
        cpusTable.setAutoCreateRowSorter(true);
    }


    private void uploadDataToFile() {
        JTable cpusTable = view.getCPUsTable();
        tableModel = (CPUsTableModel) cpusTable.getModel();
        String[] header = tableModel.getHeader();
        Object[][] data = tableModel.getData();

        dataModel.setCPUsData(data);
        dataModel.setHeader(header);

        List<List<String>> allData = new ArrayList<>();
        allData.add(Arrays.asList(header));
        allData.addAll(ArrayListUtils.toList(data));

        try {
            CSVWriter csvWriter = new CSVWriter(dataModel.getFilePath());
            csvWriter.writeAll(allData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableModel = (CPUsTableModel) cpusTable.getModel();
    }

}
