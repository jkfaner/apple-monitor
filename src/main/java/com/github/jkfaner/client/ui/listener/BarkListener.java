package com.github.jkfaner.ui.listener;

import com.github.jkfaner.ui.form.MainWindow;

import javax.swing.*;

/**
 * @author: LiamLee
 * @since: 2022-10-04
 **/
public class BarkListener {

    public static void addListeners() {
        MainWindow mainWindow = new MainWindow().getInstance();
        JTextField barkField = mainWindow.getBarkField();
        JButton barkBtn = mainWindow.getBarkBtn();
        barkBtn.addActionListener(e->{

        });
    }
}
