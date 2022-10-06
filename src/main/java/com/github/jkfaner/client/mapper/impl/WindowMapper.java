package com.github.jkfaner.client.ui.mapper.impl;

import com.github.jkfaner.client.ui.mapper.IWindowMapper;
import org.springframework.stereotype.Component;

/**
 * @author: LiamLee
 * @since: 2022-10-05
 **/
@Component
public class WindowMapper extends BaseMapper implements IWindowMapper {

    private static final String groupName = "setting.normal";
    private static final String key = "defaultMaxWindow";

    /**
     * 设置是否默认最大窗口
     */
    @Override
    public void setDefaultMaxWindow(Boolean defaultMaxWindow) {
        this.putByGroup(key, groupName, String.valueOf(defaultMaxWindow));
        this.save();
    }

    /**
     * 是否默认最大窗口
     */
    @Override
    public boolean isDefaultMaxWindow() {
        return this.getBool(key, groupName, false);
    }
}
