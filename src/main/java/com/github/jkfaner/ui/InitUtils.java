package com.github.jkfaner.ui;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.github.jkfaner.Application;
import com.github.jkfaner.ui.constant.ThemeConstants;
import com.github.jkfaner.ui.form.AreaFrom;
import com.github.jkfaner.ui.form.ModelForm;

import javax.swing.*;

/**
 * 初始化UI类
 *
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class InitUtils {

    private static final Log logger = LogFactory.get();

    public static void initTheme() {
        try {
            switch (Application.config.getTheme()) {
                case ThemeConstants.DEFAULT_THEME:
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                case ThemeConstants.FLAT_LIGHT_THEME:
                    FlatLightLaf.setup();
                    break;
                case ThemeConstants.Flat_IntelliJ_THEME:
                    FlatIntelliJLaf.setup();
                    break;
                case ThemeConstants.FLAT_DARK_THEME:
                    FlatDarkLaf.setup();
                    break;
                case ThemeConstants.DARK_PURPLE_THEME:
                    FlatDarkPurpleIJTheme.setup();
                    break;
                default:
                    // ThemeConstants.FLAT_DARCULA_THEME
                    // Flat Darcula
                    FlatDarculaLaf.setup();
            }

            /*
            if (UIUtil.isDarkLaf()) {
                FlatSVGIcon.ColorFilter.getInstance().setMapper(color -> color.brighter().brighter());
            } else {
                FlatSVGIcon.ColorFilter.getInstance().setMapper(color -> color.darker().darker());
                SwingUtilities.windowForComponent(Application.mainFrame).repaint();
            }
             */

            if (Application.config.isUnifiedBackground()) {
                UIManager.put("TitlePane.unifiedBackground", true);
            }

            // top menubar background
            UIManager.put("PopupMenu.background", UIManager.getColor("Panel.background"));
            // arrow type
            UIManager.put("Component.arrowType", "chevron");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public static void initAll() {
        ThreadUtil.execute(AreaFrom::init);
        ThreadUtil.execute(ModelForm::init);
    }
}
