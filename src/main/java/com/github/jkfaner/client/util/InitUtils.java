package com.github.jkfaner.client.ui;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.github.jkfaner.Application;
import com.github.jkfaner.client.ui.form.ModelForm;
import com.github.jkfaner.client.ui.constant.ThemeConstants;
import com.github.jkfaner.client.ui.form.AreaFrom;

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
                case ThemeConstants.FLAT_LIGHT_THEME:
                    FlatLightLaf.setup();
                    break;
                case ThemeConstants.DARK_PURPLE_THEME:
                    FlatDarkPurpleIJTheme.setup();
                    break;
                default:
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
