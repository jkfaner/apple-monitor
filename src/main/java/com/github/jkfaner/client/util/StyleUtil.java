package com.github.jkfaner.client.ui;

import com.github.jkfaner.client.ui.form.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * 定制Swing组件样式
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class Style {
    /**
     * 强调标题字体
     *
     * @param component
     */
    public static void emphaticTitleFont(JComponent component) {
        Font font = new MainWindow().getInstance().getMainPanel().getFont();
        component.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 2));
    }

    /**
     * 强调标签字体
     *
     * @param component
     */
    public static void emphaticLabelFont(JComponent component) {
        Font font = new MainWindow().getInstance().getMainPanel().getFont();
        component.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
    }

    /**
     * 强调标志字体
     *
     * @param component
     */
    public static void emphaticIndicatorFont(JComponent component) {
        Font font = new MainWindow().getInstance().getMainPanel().getFont();
        component.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 12));
    }
}
