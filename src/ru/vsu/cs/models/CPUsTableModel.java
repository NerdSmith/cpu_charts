package ru.vsu.cs.models;

import javax.swing.table.DefaultTableModel;

public class CPUsTableModel extends DefaultTableModel {
    public final int NAME_COLUMN_NUMBER = 0;
    public final int PRICE_COLUMN_NUMBER = 1;

    public CPUsTableModel(Object[][] data, String[] header) {
        super(data, header);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == NAME_COLUMN_NUMBER) {
            return String.class;
        }
        else if (columnIndex == PRICE_COLUMN_NUMBER){
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
        int rowCount = this.getRowCount();
        int colCount = this.getColumnCount();
        Object[][] tableData = new Object[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                tableData[row][col] = this.getValueAt(row, col);
            }
        }
        return tableData;
    }
}
