package com.github.jkfaner.client.ui.listener;

import cn.hutool.core.thread.ThreadUtil;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.github.jkfaner.client.ui.component.TopMenuBar;
import com.github.jkfaner.client.ui.constant.ThemeConstants;
import com.github.jkfaner.client.ui.dialog.AboutDialog;
import com.github.jkfaner.client.ui.form.MainWindow;
import com.github.jkfaner.client.ui.frame.MainFrame;
import com.github.jkfaner.client.ui.mapper.impl.ThemeMapper;
import com.github.jkfaner.client.ui.mapper.impl.WindowMapper;
import com.github.jkfaner.client.util.UpgradeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author: LiamLee
 * @since: 2022-10-05
 **/
@Component
@Slf4j
public class TopMenuBarListener {

    /**
     * 最初主题数量
     */
    private static int initialThemeItemCount = -1;

    @Autowired
    TopMenuBar topMenuBar;

    @Autowired
    MainFrame mainFrame;
    @Autowired
    MainWindow mainWindow;

    @Autowired
    ThemeMapper themeMapper;
    @Autowired
    WindowMapper windowMapper;



    /**
     * 加载默认数据
     */
    public void loadDefault(){
        loadTheme();
        loadWindowDefault();
    }


    /**
     * 加载主题
     */
    public void loadTheme(){
        JMenu themeMenu = topMenuBar.getThemeMenu();
        if (initialThemeItemCount < 0)
            initialThemeItemCount = themeMenu.getItemCount();
        else {
            // remove old items
            for (int i = themeMenu.getItemCount() - 1; i >= initialThemeItemCount; i--)
                themeMenu.remove(i);
        }
        for (String themeName : ThemeConstants.themeNames) {
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(themeName);
            item.setSelected(themeName.equals(themeMapper.getTheme()));
            item.addActionListener(this::themeListener);
            themeMenu.add(item);
        }
    }

    /**
     * 默认窗口
     */
    public void loadWindowDefault(){
        JCheckBoxMenuItem windowItem = topMenuBar.getDefaultMaxWindowItem();
        // 默认窗口
        windowItem.setSelected(windowMapper.isDefaultMaxWindow());
    }

    /**
     * 窗口项监控器
     * @param windowItem 窗口 带复选框的子菜单
     */
    public void windowListener(JCheckBoxMenuItem windowItem){
        windowItem.addActionListener(e -> {
            boolean selected = windowItem.isSelected();
            if (selected) {
                mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else {
                mainFrame.setExtendedState(JFrame.NORMAL);
            }
            windowMapper.setDefaultMaxWindow(selected);
            windowMapper.save();
        });
    }

    /**
     * 检查更新监听器
     */
    public void checkForUpdateListener(JMenuItem checkForUpdatesItem) {
        checkForUpdatesItem.addActionListener(e -> {
            ThreadUtil.execute(() -> UpgradeUtil.checkUpdate(false,mainFrame));
        });
    }

    /**
     * 关于对话框监听器
     */
    public void aboutDialogListener(JMenuItem aboutMenu){
        aboutMenu.addActionListener(e ->{
            try {
                AboutDialog dialog = new AboutDialog();
                dialog.pack();
                dialog.setVisible(true);
            } catch (Exception e2) {
                log.error(ExceptionUtils.getStackTrace(e2));
            }
        });
    }

    public void themeListener(ActionEvent actionEvent) {
        try {
            String selectedThemeName = actionEvent.getActionCommand();
            FlatAnimatedLafChange.showSnapshot();

            themeMapper.setTheme(selectedThemeName);
            // 更新UI
            SwingUtilities.updateComponentTreeUI(mainFrame);
            SwingUtilities.updateComponentTreeUI(mainWindow.getMainPanel());

//            FlatLaf.updateUI();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
            loadTheme();

        } catch (Exception e1) {
//            JOptionPane.showMessageDialog(MainWindow.getInstance().getMainPanel(), "Save failed!\n\n" + e1.getMessage(), "Failed",
//                    JOptionPane.ERROR_MESSAGE);
            log.error(ExceptionUtils.getStackTrace(e1));
        }
    }

}
