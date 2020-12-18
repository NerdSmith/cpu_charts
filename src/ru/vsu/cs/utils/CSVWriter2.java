package ru.vsu.cs.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter2 {
    private final FileWriter csvFile;
    private char separator = ',';

    public CSVWriter2(String filePath) throws IOException {
        this.csvFile = new FileWriter(filePath);
    }

    private void writeAll(List<List<String>> data) throws IOException {
        for (List<String> line : data) {
            for (int i = 0; i < line.size(); i++) {
                csvFile.append(line.get(i));
                if (i < (line.size() - 1))
                    csvFile.append(separator);
            }
            csvFile.append(System.lineSeparator());
        }
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }
}
