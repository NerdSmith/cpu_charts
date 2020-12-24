package ru.vsu.cs;

import ru.vsu.cs.models.CPUsDataModel;
import ru.vsu.cs.models.CPUsTableModel;
import ru.vsu.cs.utils.ArrayListUtils;
import ru.vsu.cs.utils.CSVWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private CPUsDataModel dataModel;
    private CPUsTableModel tableModel;
    private MainFrame view;

    private int currentDisplayMode = 0;

    public Controller(CPUsDataModel model, MainFrame view) {
        this.dataModel = model;
        this.view = view;
    }

    public void init() {
        initTable();
        initEditButton();
        initAddNewCPUButton();
        initDeleteCPUButton();
        initChangeDisplayModeButton();
        initTurnOffSortingButton();
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
        cpusTable.getTableHeader().setResizingAllowed(true);
        cpusTable.setShowGrid(true);
        cpusTable.setIntercellSpacing(new Dimension(1, 1));
        cpusTable.setFillsViewportHeight(false);
        cpusTable.setDragEnabled(false);

        tableModel = new CPUsTableModel(dataModel.getCPUsData(), dataModel.getHeader());
        tableModel.addTableModelListener(e -> uploadDataToFile());
        cpusTable.setModel(tableModel);
        cpusTable.setEnabled(false);
        cpusTable.setAutoCreateRowSorter(true);
        initCellRenderer();
    }

    private void initCellRenderer() {
        JTable cpusTable = view.getCPUsTable();
        int colsCount = cpusTable.getColumnCount();
        for (int colIndex = 2; colIndex < colsCount; colIndex++) {
            cpusTable.getColumnModel().getColumn(colIndex).setCellRenderer(new PercentRenderer(currentDisplayMode,
                                                                           dataModel.PRICE_COLUMN_NUMBER));
        }
    }

    private void initTurnOffSortingButton() {
        JButton turnOffSortingButton = view.getTurnOffSortingButton();
        turnOffSortingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCPUsTable().getRowSorter().setSortKeys(null);
            }
        });
    }

    private void initChangeDisplayModeButton() {
        JButton changeDisplayModeButton = view.getChangeDisplayModeButton();
        changeDisplayModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDisplayMode++;
                currentDisplayMode = currentDisplayMode % 3;
                setCellRendererForAllTestCols();
                view.getCPUsTable().repaint();
                if (currentDisplayMode == 0) {
                    view.getCurrentDisplayModeTextField().setText("");
                }
                else if (currentDisplayMode == 1) {
                    view.getCurrentDisplayModeTextField().setText("Performance percentages");
                }
                else if (currentDisplayMode == 2) {
                    view.getCurrentDisplayModeTextField().setText("Price/Performance percentages");
                }
            }
        });
    }

    private void setCellRendererForAllTestCols() {
        JTable cpusTable = view.getCPUsTable();
        int colsCount = cpusTable.getColumnCount();
        for (int colIndex = 2; colIndex < colsCount; colIndex++) {
            PercentRenderer cellRenderer = (PercentRenderer)
                    cpusTable.getColumnModel().getColumn(colIndex).getCellRenderer();
            cellRenderer.setCurrentDisplayMode(currentDisplayMode);
        }
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
