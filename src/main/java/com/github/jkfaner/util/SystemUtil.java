package com.github.jkfaner.util;

import java.io.File;

/**
 * 系统工具
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class SystemUtil {
    /**
     * 当前操作系统类型
     */
    private static final String OS_NAME = System.getProperty("os.name");
    /**
     * 当前系统架构
     */
    private static final String OS_ARCH = System.getProperty("os.arch");
    /**
     * 当前jvm供应商
     */
    private static final String VM_VENDOR = System.getProperty("java.vm.vendor");
    /**
     * 系统家目录
     */
    private static final String USER_HOME = System.getProperty("user.home");
    /**
     * 软件配置文件夹:家目录/.AppleMonitor
     */
    public static final String CONFIG_HOME = USER_HOME + File.separator + ".AppleMonitor";

    /**
     * 配置文件路径:家目录/.AppleMonitor/config/config.setting
     */
    public static final String SETTING_FILE_PATH = CONFIG_HOME + File.separator + "config" + File.separator + "config.setting";

    /**
     * 日志文件夹:家目录/.AppleMonitor/logs/
     */
    public final static String LOG_DIR = CONFIG_HOME + File.separator + "logs" + File.separator;

    /**
     * Mac系统
     */
    public static boolean isMacOs() {
        return OS_NAME.contains("Mac");
    }

    /**
     * Mac系统 M1系列
     */
    public static boolean isMacM1() {
        return OS_NAME.contains("Mac") && "aarch64".equals(OS_ARCH);
    }

    /**
     * Windows系统
     */
    public static boolean isWindowsOs() {
        return OS_NAME.contains("Windows");
    }

    /**
     * Linux系统
     */
    public static boolean isLinuxOs() {
        return OS_NAME.contains("Linux");
    }
}
