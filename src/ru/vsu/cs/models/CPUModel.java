package ru.vsu.cs.models;

import java.util.List;

public class CPUModel {
    private String title;
    private double price;
    private List<Double> tests;

    public CPUModel(List<String> line) {
        this.title = line.get(0);
        this.price = Double.parseDouble(line.get(1));
        for (int i = 2; i < line.size(); i++) {
            String item = line.get(i);
            if (item == null) {
                tests.add(null);
            } else {
                tests.add(Double.parseDouble(item));
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public List<Double> getTests() {
        return tests;
    }
}
