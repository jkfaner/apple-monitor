package com.github.jkfaner;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.formdev.flatlaf.util.SystemInfo;
import com.github.jkfaner.constant.AppConstants;
import com.github.jkfaner.ui.InitUtils;
import com.github.jkfaner.ui.form.LoadingForm;
import com.github.jkfaner.ui.form.MainWindow;
import com.github.jkfaner.ui.frame.MainFrame;
import com.github.jkfaner.util.ConfigUtil;
import com.github.jkfaner.util.UIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 *
 * @author: LiamLee
 * @since: 2022-09-25
 **/
public class Application {
    private static final Log logger = LogFactory.get();
    public static ConfigUtil config = ConfigUtil.getInstance();
    // 主框架
    public static MainFrame mainFrame = new MainFrame().getInstance();

    private static void macSetting() {
        if (SystemInfo.isMacOS) {
            // Mac系统需要将程序菜单栏放在屏幕菜单栏中
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            // 软件名称
            System.setProperty("apple.awt.application.name", AppConstants.APP_NAME);
            // 当前是暗色则跟随系统
            if (UIUtil.isDarkLaf()) {
                System.setProperty("apple.awt.application.appearance", "system");
            }
        }
    }

    public static void main(String[] args) {
        logger.info("程序启动...");
        macSetting();
        InitUtils.initTheme(); //加载主题
        mainFrame.init(); //加载框架信息


        // 加载等待窗口
        JPanel loadingPanel = new LoadingForm().getLoadingPanel();
        mainFrame.add(loadingPanel);
        mainFrame.pack();

        // 设置窗口大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (config.isDefaultMaxWindow() || screenSize.getWidth() <= 1366) {
            // 窗口在低分辨率时自动最大化
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        // 热更新
        // UpgradeUtil.smoothUpgrade();

        MainWindow mainWindow = new MainWindow().getInstance();
        // 将主窗口放在主框架中
        mainFrame.setContentPane(mainWindow.getMainPanel());

        // 主窗口初始化
        mainWindow.init();

        // 初始化所有窗口数据
        InitUtils.initAll();
//        InitUtils.initOthers();
        MainFrame.addListeners();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.remove(loadingPanel);

        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}
