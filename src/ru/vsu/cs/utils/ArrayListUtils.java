package ru.vsu.cs.utils;

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
}
