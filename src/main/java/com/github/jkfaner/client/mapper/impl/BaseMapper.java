package com.github.jkfaner.client.ui.mapper.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;
import com.github.jkfaner.client.ui.mapper.IBaseMapper;
import com.github.jkfaner.client.util.SystemUtil;

/**
 * 配置管理的基类
 * @author: LiamLee
 * @since: 2022-10-05
 **/
public class BaseMapper extends Setting implements IBaseMapper {

    BaseMapper() {
        super(FileUtil.touch(SystemUtil.SETTING_FILE_PATH), CharsetUtil.CHARSET_UTF_8, false);
    }

    @Override
    public void save() {
        this.store(SystemUtil.SETTING_FILE_PATH);
    }
}
