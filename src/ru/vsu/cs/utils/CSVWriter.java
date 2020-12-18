package ru.vsu.cs.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private static char separator = ',';

    public CSVWriter(String fileName, List<List<String>> data) throws IOException {
        writeDataToCSV(fileName, data, separator);
    }

    private static void writeDataToCSV(String fileName, List<List<String>> data, char separator) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (List<String> line : data) {
            for (int i = 0; i < line.size(); i++) {
                writer.append(line.get(i));
                if (i < (line.size() - 1))
                    writer.append(separator);
            }
            writer.append(System.lineSeparator());
        }
    }

}
