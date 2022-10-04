package com.github.jkfaner.ui.frame;

import cn.hutool.core.thread.ThreadUtil;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.constant.AppConstants;
import com.github.jkfaner.ui.component.TopMenuBar;
import com.github.jkfaner.ui.listener.AreaListener;
import com.github.jkfaner.ui.listener.FrameListener;
import com.github.jkfaner.util.ComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 主题框架
 *
 * @author: LiamLee
 * @since: 2022-09-30
 **/
public class MainFrame extends JFrame implements IBaseObject<MainFrame> {

    private static MainFrame mainFrame;

    @Override
    public MainFrame getInstance() {
        mainFrame = mainFrame == null ? new MainFrame() : mainFrame;
        return mainFrame;
    }

    public void init() {
        this.setName(AppConstants.APP_NAME);
        this.setTitle(AppConstants.APP_TITLE);
        // FrameUtil.setFrameIcon(this);
        List<Image> iconImages = FlatSVGUtils.createWindowIconImages("/icons/logo_black.svg");
        setIconImages(iconImages);
        // 获取顶部菜单栏实例
        TopMenuBar topMenuBar = new TopMenuBar().getInstance();
        // 初始化菜单
        topMenuBar.init();
        // 设置菜单
        System.out.println(topMenuBar.getMenu(0));
        setJMenuBar(topMenuBar);
        // 设置位置
        ComponentUtil.setPreferSizeAndLocateToCenter(this, 0.6, 0.8);
        // 表单监听
        FrameListener.addListeners();
    }

    public static void addListeners() {
        ThreadUtil.execute(AreaListener::addListeners);
    }

    public static void shutdown() {
        mainFrame.dispose();
        System.exit(0);
    }

}
