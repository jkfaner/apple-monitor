package com.github.jkfaner.util;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;

import java.io.File;

/**
 * 配置管理的基类
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class ConfigBaseUtil {

    Setting setting;

    ConfigBaseUtil() {
        setting = new Setting(FileUtil.touch(SystemUtil.SETTING_FILE_PATH), CharsetUtil.CHARSET_UTF_8, false);
    }

    public void setProps(String key, String value) {
        setting.put(key, value);
    }

    public String getProps(String key) {
        return setting.get(key);
    }

    /**
     * 持久化保存
     */
    public void save() {
        setting.store(SystemUtil.SETTING_FILE_PATH);
    }
}
