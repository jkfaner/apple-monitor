package com.github.jkfaner.ui.listener;

import com.github.jkfaner.Application;
import com.github.jkfaner.ui.frame.MainFrame;
import com.github.jkfaner.util.SystemUtil;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 表单事件监控
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class FrameListener {
    public static void addListeners() {
        Application.mainFrame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (SystemUtil.isWindowsOs()) {
                    Application.mainFrame.setVisible(false);
                } else {
                    Application.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }

                MainFrame.shutdown();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }
        });

    }
}
