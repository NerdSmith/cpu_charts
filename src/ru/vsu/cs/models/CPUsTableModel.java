package ru.vsu.cs.models;

import javax.swing.table.DefaultTableModel;

public class CPUsTableModel extends DefaultTableModel {
    private final int NAME_COLUMN_NUMBER = 0;
    private final int PRICE_COLUMN_NUMBER = 1;

    public CPUsTableModel(Object[][] data, String[] header) {
        super(data, header);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        }
        else if (columnIndex == 1){
            return Integer.class;
        }
        else {
            return Double.class;
        }
    }

    public String[] getHeader() {
        int columnCount = this.getColumnCount();
        String[] header = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            header[i] = this.getColumnName(i);
        }
        return header;
    }

    public Object[][] getData() {
        int rowCounts = this.getRowCount();
        int colCount = this.getColumnCount();
        Object[][] tableData = new Object[rowCounts][colCount];

        for (int row = 0; row < rowCounts; row++) {
            for (int col = 0; col < colCount; col++) {
                Object value = this.getValueAt(row, col);
                if (value == null) {
                    tableData[row][col] = null;
                }
                else if (col == NAME_COLUMN_NUMBER) {
                    tableData[row][col] = value.toString();
                }
                else if (col == PRICE_COLUMN_NUMBER) {
                    try {
                        tableData[row][col] = Integer.parseInt(value.toString());
                    }
                    catch (Exception e) {
                        this.setValueAt(null, row, col);
                        tableData[row][col] = null;
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        tableData[row][col] = Double.parseDouble(value.toString());
                    }
                    catch (Exception e) {
                        this.setValueAt(null, row, col);
                        tableData[row][col] = null;
                        e.printStackTrace();
                    }
                }
            }
        }
        return tableData;
    }
}
