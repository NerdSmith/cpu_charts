package ru.vsu.cs.models;

import ru.vsu.cs.utils.ArrayListUtils;

import ru.vsu.cs.utils.CSVReader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class CPUDataModel {
    private String[] header;
    private Object[][] CPUsData;
    private String filePath = "src/ru/vsu/cs/data/CPUsdata.csv";

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
        for (int i = 0; i < rawData.size(); i++) {
            for (int j = 0; j < rawData.get(0).size(); j++) {
                if (j == 1) {
                    data[i][j] = Integer.valueOf(rawData.get(i).get(j));
                }
                else if (j > 1) {
                    if (rawData.get(i).get(j).equals("")) {
                        data[i][j] = null;
                    }
                    else {
                        data[i][j] = Double.valueOf(rawData.get(i).get(j));
                    }
                }
                else {
                    data[i][j] = rawData.get(i).get(j);
                }
            }
        }
        return data;
    }
}
