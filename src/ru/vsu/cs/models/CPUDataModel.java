package ru.vsu.cs.models;

import ru.vsu.cs.utils.CSVReader;

import java.io.FileNotFoundException;
import java.util.List;

public class CPUDataModel {
    private String[] header;
    private Object[][] CPUsData;
    private String filePath = "src/ru/vsu/cs/data/CPUsdata.csv";
    private final int NAME_COLUMN_NUMBER = 0;
    private final int PRICE_COLUMN_NUMBER = 1;

    public CPUDataModel() throws FileNotFoundException {
        CSVReader csvReader = new CSVReader(filePath);
        List<List<String>> allData = csvReader.readAll();

        this.header = allData.get(0).toArray(new String[0]);
        this.CPUsData = toArrayOfObjects(allData.subList(1, allData.size()));
    }

    public String[] getHeader() {
        return header;
    }

    public Object[][] getCPUsData() {
        return CPUsData;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public void setCPUsData(Object[][] CPUsData) {
        this.CPUsData = CPUsData;
    }

    public String getFilePath() {
        return filePath;
    }

    private Object[][] toArrayOfObjects(List<List<String>> rawData) {
        Object[][] data = new Object[rawData.size()][rawData.get(0).size()];
        for (int row = 0; row < rawData.size(); row++) {
            for (int col = 0; col < rawData.get(0).size(); col++) {
                String value = rawData.get(row).get(col);
                if (value == null || value.isEmpty()) {
                    data[row][col] = null;
                }
                else if (col == NAME_COLUMN_NUMBER){
                    data[row][col] = value;
                }
                else if (col == PRICE_COLUMN_NUMBER) {
                    data[row][col] = Integer.valueOf(value);
                }
                else {
                    data[row][col] = Double.valueOf(rawData.get(row).get(col));
                }
            }
        }
        return data;
    }
}
