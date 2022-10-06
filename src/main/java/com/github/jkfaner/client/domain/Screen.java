package com.github.jkfaner.client.ui.domain;

import lombok.Getter;
import java.awt.*;

/**
 * 屏幕尺寸类
 * @author: LiamLee
 * @since: 2022-10-05
 **/
@Getter
public class Screen {
    /**
     * 屏幕尺寸（包含任务栏）
     */
    private Dimension screenSize;
    /**
     * 屏幕边界
     */
    private Insets screenInsets;
    /**
     * 屏幕宽度：屏幕尺寸-左边界-右边界
     */
    private Integer screenWidth;
    /**
     * 屏幕高度：屏幕尺寸-上边界-下边界
     */
    private Integer screenHeight;

    public void setScreenSize() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    @Deprecated
    public void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }

    public void setScreenInsets(Component component){
        this.screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(component.getGraphicsConfiguration());
    }

    @Deprecated
    public void setScreenInsets(Insets screenInsets) {
        this.screenInsets = screenInsets;
    }

    public void setScreenWidth() {
        this.screenWidth = screenSize.width - screenInsets.left - screenInsets.right;
    }

    @Deprecated
    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight() {
        this.screenHeight = screenSize.height - screenInsets.top - screenInsets.bottom;
    }

    @Deprecated
    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }

    /**
     * 初始化属性
     * @param component 组件
     */
    public void initAttribute(Component component){
        this.setScreenSize();
        this.setScreenInsets(component);
        this.setScreenWidth();
        this.setScreenHeight();
    }
}
