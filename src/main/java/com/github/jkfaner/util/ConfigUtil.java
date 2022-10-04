package com.github.jkfaner.util;

/**
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class ConfigUtil extends ConfigBaseUtil {

    private static final ConfigUtil configUtil = new ConfigUtil();

    /**
     * 自动更新
     */
    private boolean autoCheckUpdate;

    /**
     * 默认最大窗口
     */
    private boolean defaultMaxWindow;

    /**
     * 统一背景
     */
    private boolean unifiedBackground;

    /**
     * 之前版本
     */
    private String beforeVersion;

    /**
     * 主题
     */
    private String theme;

    /**
     * 字体
     */
    private String font;

    /**
     * 字体大小
     */
    private int fontSize;

    public static ConfigUtil getInstance() {
        return configUtil;
    }

    public ConfigUtil() {
        super();
    }

    /**
     * 是否自动更新
     */
    public boolean isAutoCheckUpdate() {
        return setting.getBool("autoCheckUpdate", "setting.common", true);
    }

    /**
     * 设置是否自动更新
     */
    public void setAutoCheckUpdate(boolean autoCheckUpdate) {
        setting.putByGroup("autoCheckUpdate", "setting.common", String.valueOf(autoCheckUpdate));
    }

    /**
     * 是否默认最大窗口
     */
    public boolean isDefaultMaxWindow() {
        return setting.getBool("defaultMaxWindow", "setting.normal", false);
    }

    /**
     * 设置是否默认最大窗口
     */
    public void setDefaultMaxWindow(boolean defaultMaxWindow) {
        setting.putByGroup("defaultMaxWindow", "setting.normal", String.valueOf(defaultMaxWindow));
    }

    /**
     * 是否统一背景
     */
    public boolean isUnifiedBackground() {
        return setting.getBool("unifiedBackground", "setting.normal", true);
    }

    /**
     * 设置是否统一背景
     */
    public void setUnifiedBackground(boolean unifiedBackground) {
        setting.putByGroup("unifiedBackground", "setting.normal", String.valueOf(unifiedBackground));
    }

    /**
     * 获取上一个版本
     */
    public String getBeforeVersion() {
        return setting.getStr("beforeVersion", "setting.common", "1.0.0");
    }

    /**
     * 设置上一个版本
     */
    public void setBeforeVersion(String beforeVersion) {
        setting.putByGroup("beforeVersion", "setting.common", beforeVersion);
    }

    /**
     * 获取主题
     */
    public String getTheme() {
        return setting.getStr("theme", "setting.appearance", "深紫色");
    }

    /**
     * 设置主题
     */
    public void setTheme(String theme) {
        setting.putByGroup("theme", "setting.appearance", theme);
    }

    /**
     * 获取字体
     */
    public String getFont() {
        if (SystemUtil.isLinuxOs()) {
            return setting.getStr("font", "setting.appearance", "Noto Sans CJK HK");
        } else {
            return setting.getStr("font", "setting.appearance", "Microsoft YaHei");
        }
    }

    /**
     * 设置字体
     */
    public void setFont(String font) {
        setting.putByGroup("font", "setting.appearance", font);
    }

    /**
     * 获取字体大小
     */
    public int getFontSize() {
        return setting.getInt("fontSize", "setting.appearance", 13);
    }

    /**
     * 设置字体大小
     */
    public void setFontSize(int fontSize) {
        setFontSize(String.valueOf(fontSize));
    }

    /**
     * 设置字体大小
     */
    public void setFontSize(String fontSize) {
        setting.putByGroup("fontSize", "setting.appearance", fontSize);
    }
}
