package ru.vsu.cs.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class csvUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "src/ru/vsu/cs/data/CPUsdata.csv";
        List<List<String>> data = readFromCSV(filePath);
        for (List<String> i : data) {
            for (String j : i) {
                if (j.equals("")) {
                    System.out.print("_" + " ");
                } else {
                    System.out.print(j + "|");
                }
            }
            System.out.println();
        }

    }

    public static List<List<String>> readFromCSV(String fileName) throws FileNotFoundException {
        Scanner csvFile = new Scanner(new File("src/ru/vsu/cs/data/CPUsdata.csv"));
        List<List<String>> data = new ArrayList<>();
        while (csvFile.hasNext()) {
            data.add(readLine(csvFile.nextLine()));
        }
        csvFile.close();
        return data;
    }

    public static List<String> readLine(String cvsLine) {
        return readLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> readLine(String cvsLine, char separators) {
        return readLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> readLine(String cvsLine, char separator, char customQuote) {
        List<String> result = new ArrayList<>();

        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separator == ' ') {
            separator = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;

                if (ch == customQuote) {
                    curVal.append(customQuote);
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    if (ch == '\"') {

                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {

                if (ch == customQuote) {
                    inQuotes = true;

                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separator) {
                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r') {
                    continue;
                } else if (ch == '\n') {
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }

    public static void writeToCSV(String fileName, List<String> header, List<List<String>> data) {
        writeToCSV(fileName, header, data, DEFAULT_SEPARATOR);
    }

    public static void writeToCSV(String fileName, List<String> header, List<List<String>> data, char separator) {
        try {
            FileWriter writer = new FileWriter(fileName);
            if (header != null) {
                for (int i = 0; i < header.size(); i++) {
                    writer.append(header.get(i));
                    if (i < (header.size() - 1))
                        writer.append(separator);
                }
            }
            for (List<String> line : data) {
                for (int i = 0; i < line.size(); i++) {
                    writer.append(line.get(i));
                    if (i < (line.size() - 1))
                        writer.append(separator);
                }
                writer.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}