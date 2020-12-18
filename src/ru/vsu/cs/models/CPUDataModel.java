package ru.vsu.cs.models;

import ru.vsu.cs.utils.ArrayListUtils;

import ru.vsu.cs.utils.CSVReader;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class CPUDataModel {
    private String[] header;
    private Object[][] CPUsData;
    private String filePath = "src/ru/vsu/cs/data/CPUsdata.csv";

    public CPUDataModel() throws FileNotFoundException {
        CSVReader csvReader = new CSVReader(filePath);
        String[][] allData = ArrayListUtils.toStringArr(csvReader.readAll());

        this.header = allData[0];
        this.CPUsData = toArrayOfObjects(Arrays.copyOfRange(allData, 1, allData.length));
    }

    public String[] getHeader() {
        return header;
    }

    public Object[][] getCPUsData() {
        return CPUsData;
    }

    private Object[][] toArrayOfObjects(String[][] rawData) {
        Object[][] data = new Object[rawData.length][rawData[0].length];
        for (int i = 0; i < rawData.length; i++) {
            for (int j = 0; j < rawData[0].length; j++) {
                if (j == 1) {
                    data[i][j] = Integer.valueOf(rawData[i][j]);
                }
                else if (j > 1) {
                    if (rawData[i][j].equals("")) {
                        data[i][j] = "";
                    }
                    else {
                        data[i][j] = Double.valueOf(rawData[i][j]);
                    }
                }
                else {
                    data[i][j] = rawData[i][j];
                }
            }
        }
        return data;
    }
}
