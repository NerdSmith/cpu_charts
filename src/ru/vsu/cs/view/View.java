package ru.vsu.cs.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class View extends JFrame{
    private JPanel panelMain;
    private JTable CPUsTable;
    private JButton editButton;
    private JButton showChartsButton;
    private JTextField editTextField;

    public View() {
        super("CPU charts");
        this.setContentPane(panelMain);
        this.setBounds(100, 100, 1000, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 130));
        this.setVisible(true);
    }

    public JTable getCPUsTable() {
        return CPUsTable;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JTextField getEditTextField() {
        return editTextField;
    }
}
