package com.github.jkfaner.util;


import java.awt.*;

import com.github.jkfaner.Application;
import com.github.jkfaner.ui.constant.ThemeConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * UI工具类
 * @author: LiamLee
 * @since: 2022-10-01
 **/
@Slf4j
public class UIUtil {

    /**
     * 获取屏幕规格
     * author by darcula@com.bulenkov
     * see https://github.com/bulenkov/Darcula
     */
    public static float getScreenScale() {
        int dpi = 96;

        try {
            dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        } catch (HeadlessException var2) {
        }

        float scale = 1.0F;
        if (dpi < 120) {
            scale = 1.0F;
        } else if (dpi < 144) {
            scale = 1.25F;
        } else if (dpi < 168) {
            scale = 1.5F;
        } else if (dpi < 192) {
            scale = 1.75F;
        } else {
            scale = 2.0F;
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        log.info("screen dpi:{},width:{},height:{}", dpi, screenSize.getWidth(), screenSize.getHeight());

        return scale;
    }

    /**
     * 主题是否黑暗
     */
    public static boolean isDarkLaf() {
        return ThemeConstants.FLAT_DARK_THEME.equals(Application.config.getTheme())
                || ThemeConstants.FLAT_DARCULA_THEME.equals(Application.config.getTheme())
                || ThemeConstants.DARK_PURPLE_THEME.equals(Application.config.getTheme());
    }
}
