package ru.vsu.cs.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListUtils {
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
