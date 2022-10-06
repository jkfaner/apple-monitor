package com.github.jkfaner.client.util;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.util.SystemInfo;
import com.github.jkfaner.client.constant.ThemeConstants;
import com.github.jkfaner.client.mapper.impl.BackgroundMapper;
import com.github.jkfaner.client.mapper.impl.ThemeMapper;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * 主题工具类
 *
 * @author: LiamLee
 * @since: 2022-10-06
 **/
@Component
public class ThemeUtil {

    public final ThemeMapper themeMapper;

    public final BackgroundMapper backgroundMapper;

    public ThemeUtil(final ThemeMapper themeMapper, BackgroundMapper backgroundMapper) {
        this.themeMapper = themeMapper;
        this.backgroundMapper = backgroundMapper;
        macSetting();
        setupTheme();
        System.out.println("ThemeUtil Success");
    }

    public void macSetting() {
        if (SystemInfo.isMacOS) {
            // Mac系统需要将程序菜单栏放在屏幕菜单栏中
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            // 软件名称
            System.setProperty("apple.awt.application.name", "AppleMonitor");
            // 当前是暗色则跟随系统
            if (themeMapper.isDarkLaf()) {
                System.setProperty("apple.awt.application.appearance", "system");
            }
        }
    }

    /**
     * 装载主题
     * Swing项目启动前，须要先设置外观，再创建UI。
     * 为了保证创建UI时，已经实现设置好新外观，
     * 最好把设置外观的代码写在静态块中，
     * 并且把这个静态块写在类定义的最前面。
     */
    public void setupTheme() {
        switch (themeMapper.getTheme()) {
            case ThemeConstants.FLAT_LIGHT_THEME:
                FlatLightLaf.setup();
                break;
            case ThemeConstants.DARK_PURPLE_THEME:
                FlatDarkPurpleIJTheme.setup();
                break;
            default:
                FlatDarculaLaf.setup();
        }
        setDark();
        setUnifiedBackground();

        // top menubar background
        UIManager.put("PopupMenu.background", UIManager.getColor("Panel.background"));

        // arrow type
        UIManager.put("Component.arrowType", "chevron");
    }

    /**
     * 设置深色背景
     */
    public void setDark() {
        if (!themeMapper.isDarkLaf()) {
            FlatSVGIcon.ColorFilter.getInstance().setMapper(color -> color.brighter().brighter());
        } else {
            FlatSVGIcon.ColorFilter.getInstance().setMapper(color -> color.darker().darker());
//                SwingUtilities.windowForComponent(mainFrame).repaint();
        }
    }

    /**
     * 设置统一背景
     */
    public void setUnifiedBackground() {
        if (backgroundMapper.isUnifiedBackground()) {
            UIManager.put("TitlePane.unifiedBackground", true);
        }
    }

}
