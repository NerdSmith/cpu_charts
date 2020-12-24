package ru.vsu.cs;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.DecimalFormat;

public class PercentRenderer extends DefaultTableCellRenderer {
    private int currentDisplayMode = 0;
    private final int priceColumnIndex;

    public PercentRenderer(int currentDisplayMode, int priceColumnIndex) {
        this.currentDisplayMode = currentDisplayMode;
        this.priceColumnIndex = priceColumnIndex;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (currentDisplayMode == 0) {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
        else {
            if (table.getColumnClass(column) == Double.class) {
                JLabel tLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null) {
                    if (currentDisplayMode == 1){
                        double maxTestResult = getMaxTestResultInColumn(column, table.getModel());
                        DecimalFormat round = new DecimalFormat("#.##");
                        tLabel.setText(round.format(100. * ((double) value) / maxTestResult) + "%");
                    }
                    else {
                        Object price = table.getValueAt(row, priceColumnIndex);
                        if (price != null) {
                            DecimalFormat round = new DecimalFormat("#.##");
                            double maxPriceToPerformanceScore = getMaxPriceToPerformanceScore(column, table.getModel());
                            tLabel.setText(round.format(100. * maxPriceToPerformanceScore /
                                    (Double.parseDouble(price.toString()) / ((double) value))) + "%");
                        }
                    }
                }
                return tLabel;
            }
            else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
    }

    public double getMaxPriceToPerformanceScore(int columnIndex, TableModel tableModel) {
        double maxPriceToPerformanceScore = Double.MAX_VALUE;
        int rowCount = tableModel.getRowCount();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Object testResult = tableModel.getValueAt(rowIndex, columnIndex);
            Object price = tableModel.getValueAt(rowIndex, priceColumnIndex);
            if (testResult != null && price != null) {
                double currentPriceToPerformanceScore = Double.parseDouble(price.toString()) /
                        Double.parseDouble(testResult.toString());

                if (currentPriceToPerformanceScore < maxPriceToPerformanceScore) {
                    maxPriceToPerformanceScore = currentPriceToPerformanceScore;
                }
            }
        }
        return maxPriceToPerformanceScore;
    }

    public double getMaxTestResultInColumn(int columnIndex, TableModel tableModel) {
        double maxTestResult = 0;
        int rowCount = tableModel.getRowCount();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Object testResult = tableModel.getValueAt(rowIndex, columnIndex);
            if (testResult != null) {
                double v = Double.parseDouble(testResult.toString());
                if (maxTestResult < v) {
                    maxTestResult = v;
                }
            }
        }
        return maxTestResult;
    }

    public void setCurrentDisplayMode(int currentDisplayMode) {
        this.currentDisplayMode = currentDisplayMode;
    }
}
