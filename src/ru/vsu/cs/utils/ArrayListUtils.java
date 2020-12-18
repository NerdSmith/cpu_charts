package ru.vsu.cs.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListUtils {
    public static String[][] toStringArr(List<List<String>> data) {
        String[][] newData = new String[data.size()][data.get(0).size()];

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(0).size(); j++) {
                newData[i][j] = data.get(i).get(j);
            }
        }
        return newData;
    }

    public static List<List<String>> toList(Object[][] data) {
        List<List<String>> newData = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            String[] strData = new String[data[i].length];
            for (int j = 0; j < data[i].length; j++) {
                Object value = data[i][j];
                strData[j] = value == null ? "" : value.toString();
            }
            newData.add(Arrays.asList(strData));
        }
        return newData;
    }
}
