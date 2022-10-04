package com.github.jkfaner.ui.component;

import cn.hutool.core.thread.ThreadUtil;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.github.jkfaner.Application;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.ui.InitUtils;
import com.github.jkfaner.ui.constant.ThemeConstants;
import com.github.jkfaner.ui.dialog.AboutDialog;
import com.github.jkfaner.ui.form.MainWindow;
import com.github.jkfaner.util.UpgradeUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 顶部菜单栏组件
 *
 * @author: LiamLee
 * @since: 2022-09-30
 **/
@Slf4j
public class TopMenuBar extends JMenuBar implements IBaseObject<TopMenuBar> {

    private static TopMenuBar menuBar; // 必须为静态属性 内存中只允许有一份

    private static JMenu themeMenu;

    /**
     * 最初主题数量
     */
    private static int initialThemeItemCount = -1;

    /**
     * 主题名称
     */
    private static final String[] themeNames = {
            ThemeConstants.FLAT_LIGHT_THEME,
            ThemeConstants.FLAT_DARCULA_THEME,
            ThemeConstants.DARK_PURPLE_THEME,
    };

    @Override
    public TopMenuBar getInstance() {
        menuBar = menuBar == null ? new TopMenuBar() : menuBar;
        return menuBar;
    }


    public void init() {
        TopMenuBar topMenuBar = getInstance();

        // ---开始
        JMenu startMenu = new JMenu("开始");

        JMenuItem patternMenu = new JMenuItem("模式");
        JCheckBoxMenuItem addressPatternMenuItem = new JCheckBoxMenuItem("按地区");
        JCheckBoxMenuItem storePatternMenuItem = new JCheckBoxMenuItem("按店铺");
        patternMenu.add(addressPatternMenuItem);
        patternMenu.add(storePatternMenuItem);
        startMenu.add(patternMenu);

        JMenuItem stateMenu = new JMenuItem("国家");
        startMenu.add(stateMenu);

        topMenuBar.add(startMenu);

        // ---窗口
        JMenu windowMenu = new JMenu("窗口");
        themeMenu = new JMenu("主题");
        initThemesMenu();
        windowMenu.add(themeMenu);

        JCheckBoxMenuItem defaultMaxWindowItem = new JCheckBoxMenuItem("进入全屏幕");
        defaultMaxWindowItem.addActionListener(e -> {
            boolean selected = defaultMaxWindowItem.isSelected();
            if (selected) {
                Application.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else {
                Application.mainFrame.setExtendedState(JFrame.NORMAL);
            }
            Application.config.setDefaultMaxWindow(selected);
            Application.config.save();
        });
        defaultMaxWindowItem.setSelected(Application.config.isDefaultMaxWindow());
        windowMenu.add(defaultMaxWindowItem);

        topMenuBar.add(windowMenu);

        // ----帮助
        JMenu helpMenu = new JMenu("帮助");
        JMenuItem checkForUpdatesItem = new JMenuItem("检查更新");
        checkForUpdatesItem.addActionListener(e -> checkForUpdateListening());
        helpMenu.add(checkForUpdatesItem);

        JMenuItem aboutMenu = new JMenuItem("关于");
        aboutMenu.addActionListener(e -> aboutActionPerformed());
        helpMenu.add(aboutMenu);

        topMenuBar.add(helpMenu);
    }

    private void checkForUpdateListening() {
        ThreadUtil.execute(() -> UpgradeUtil.checkUpdate(false));
    }

    private void aboutActionPerformed() {
        try {
            AboutDialog dialog = new AboutDialog();
            dialog.pack();
            dialog.setVisible(true);
        } catch (Exception e2) {
            log.error(ExceptionUtils.getStackTrace(e2));
        }
    }

    private void initThemesMenu() {
        if (initialThemeItemCount < 0)
            initialThemeItemCount = themeMenu.getItemCount();
        else {
            // remove old items
            for (int i = themeMenu.getItemCount() - 1; i >= initialThemeItemCount; i--)
                themeMenu.remove(i);
        }
        for (String themeName : themeNames) {
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(themeName);
            item.setSelected(themeName.equals(Application.config.getTheme()));
            item.addActionListener(this::themeChanged);
            themeMenu.add(item);
        }
    }

    private void themeChanged(ActionEvent actionEvent) {
        try {
            String selectedThemeName = actionEvent.getActionCommand();

            FlatAnimatedLafChange.showSnapshot();

            Application.config.setTheme(selectedThemeName);
            Application.config.save();

            InitUtils.initTheme();
            // 更新UI
            SwingUtilities.updateComponentTreeUI(Application.mainFrame);
//            SwingUtilities.updateComponentTreeUI(new MainWindow().getInstance().getMainPanel());
//            FlatLaf.updateUI();

            FlatAnimatedLafChange.hideSnapshotWithAnimation();
            initThemesMenu();

        } catch (Exception e1) {
//            JOptionPane.showMessageDialog(MainWindow.getInstance().getMainPanel(), "Save failed!\n\n" + e1.getMessage(), "Failed",
//                    JOptionPane.ERROR_MESSAGE);
            log.error(ExceptionUtils.getStackTrace(e1));
        }
    }
}
