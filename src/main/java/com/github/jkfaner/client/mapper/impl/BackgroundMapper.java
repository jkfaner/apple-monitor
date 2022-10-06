package com.github.jkfaner.client.ui.mapper.impl;

import com.github.jkfaner.client.ui.mapper.IBackgroundMapper;
import org.springframework.stereotype.Component;

/**
 * @author: LiamLee
 * @since: 2022-10-05
 **/
@Component
public class BackgroundMapper extends BaseMapper implements IBackgroundMapper {

    private static final String groupName = "setting.normal";
    private static final String key = "unifiedBackground";

    /**
     * 是否统一背景
     */
    @Override
    public boolean isUnifiedBackground() {
        return this.getBool(key, groupName, true);
    }

    /**
     * 设置是否统一背景
     */
    @Override
    public void setUnifiedBackground(boolean unifiedBackground) {
        this.putByGroup(key, groupName, String.valueOf(unifiedBackground));
        this.save();
    }
}
