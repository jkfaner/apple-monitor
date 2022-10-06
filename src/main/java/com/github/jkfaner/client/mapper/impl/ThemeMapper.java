package com.github.jkfaner.client.ui.mapper.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.github.jkfaner.client.ui.constant.ThemeConstants;
import com.github.jkfaner.client.ui.mapper.IThemeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * @author: LiamLee
 * @since: 2022-10-05
 **/
@Component
public class ThemeMapper extends BaseMapper implements IThemeMapper {

    private static final Log logger = LogFactory.get();
    private static final String groupName = "setting.appearance";
    private static final String key = "theme";

    @Autowired
    BackgroundMapper backgroundMapper;

    public ThemeMapper(){
        setupTheme();
    }

    public void setupTheme() {
        try {
            switch (this.getTheme()) {
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

            if (backgroundMapper.isUnifiedBackground()) {
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

    @Override
    public void setTheme(String themeName){
        this.putByGroup(key, groupName, themeName);
        this.save();
        setupTheme();
    }

    @Override
    public String getTheme(){
        return this.getStr(key, groupName, "深紫色");
    }

    /**
     * 主题是否黑暗
     */
    @Override
    public boolean isDarkLaf() {
        String theme = getTheme();
        return ThemeConstants.FLAT_DARCULA_THEME.equals(theme)
                || ThemeConstants.DARK_PURPLE_THEME.equals(theme);
    }
}
