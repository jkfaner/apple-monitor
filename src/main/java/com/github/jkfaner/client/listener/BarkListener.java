package com.github.jkfaner.client.ui.listener;

import com.github.jkfaner.client.ui.form.MainWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * @author: LiamLee
 * @since: 2022-10-04
 **/
@Component
public class BarkListener {

    @Autowired
    MainWindow mainWindow;

    public void addListeners() {
        JTextField barkField = mainWindow.getBarkField();
        JButton barkBtn = mainWindow.getBarkBtn();
        barkBtn.addActionListener(e->{

        });
    }
}
