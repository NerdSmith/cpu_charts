package ru.vsu.cs;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private JPanel panelMain;
    private JTable CPUsTable;
    private JButton editButton;
    private JTextField editTextField;
    private JButton addNewCPUButton;
    private JButton deleteCPUButton;
    private JButton changeDisplayModeButton;
    private JButton disableSortingButton;
    private JTextField currentDisplayModeTextField;

    public MainFrame() {
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

    public JButton getAddNewCPUButton() {
        return addNewCPUButton;
    }

    public JTextField getEditTextField() {
        return editTextField;
    }

    public JButton getDeleteCPUButton() {
        return deleteCPUButton;
    }

    public JButton getChangeDisplayModeButton() {
        return changeDisplayModeButton;
    }

    public JButton getDisableSortingButton() {
        return disableSortingButton;
    }

    public JTextField getCurrentDisplayModeTextField() {
        return currentDisplayModeTextField;
    }
}
