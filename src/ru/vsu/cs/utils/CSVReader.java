package ru.vsu.cs.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private final Scanner csvFile;
    private char quote = '"';
    private char separator = ',';

    public CSVReader(String filePath) throws FileNotFoundException {
        this.csvFile = new Scanner(new File(filePath));
    }

    public List<List<String>> readAll() {
        List<List<String>> data = new ArrayList<>();
        while (csvFile.hasNext()) {
            data.add(readLine(csvFile.nextLine()));
        }
        csvFile.close();
        return data;
    }

    private List<String> readLine(String cvsLine) {
        List<String> result = new ArrayList<>();

        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;

                if (ch == quote) {
                    curVal.append(quote);
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

                if (ch == quote) {
                    inQuotes = true;

                    if (chars[0] != '"' && quote == '\"') {
                        curVal.append('"');
                    }

                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separator) {
                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;
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

    public void setQuote(char quote) {
        this.quote = quote;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }
}
