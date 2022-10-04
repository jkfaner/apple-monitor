package com.github.jkfaner.util;

import com.github.jkfaner.Application;

import java.awt.*;

/**
 * 组件工具类
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class ComponentUtil {
    /**
     * 屏幕尺寸（包含任务栏）
     */
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * 屏幕边界
     */
    private static Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(Application.mainFrame.getGraphicsConfiguration());

    /**
     * 屏幕宽度：屏幕尺寸-左边界-右边界
     */
    private static int screenWidth = screenSize.width - screenInsets.left - screenInsets.right;

    /**
     * 屏幕高度：屏幕尺寸-上边界-下边界
     */
    private static int screenHeight = screenSize.height - screenInsets.top - screenInsets.bottom;

    /**
     * 设置组件preferSize并将其放置在屏幕中央
     */
    public static void setPreferSizeAndLocateToCenter(Component component, int preferWidth, int preferHeight) {
        component.setBounds((screenWidth - preferWidth) / 2, (screenHeight - preferHeight) / 2,
                preferWidth, preferHeight);
        Dimension preferSize = new Dimension(preferWidth, preferHeight);
        component.setPreferredSize(preferSize);
    }

    /**
     * 设置组件preferSize并将其放置在屏幕中央(基于屏幕宽度的百分比)
     */
    public static void setPreferSizeAndLocateToCenter(Component component, double preferWidthPercent, double preferHeightPercent) {
        int preferWidth = (int) (screenWidth * preferWidthPercent);
        int preferHeight = (int) (screenHeight * preferHeightPercent);
        setPreferSizeAndLocateToCenter(component, preferWidth, preferHeight);
    }
}
