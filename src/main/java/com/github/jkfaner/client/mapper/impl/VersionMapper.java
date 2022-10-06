package com.github.jkfaner.client.ui.mapper.impl;

import com.github.jkfaner.client.domain.Config;
import com.github.jkfaner.client.ui.mapper.IVersionMapper;
import org.springframework.stereotype.Component;

/**
 * @author: LiamLee
 * @since: 2022-10-05
 **/
@Component
public class VersionMapper extends BaseMapper implements IVersionMapper {

    private static final String groupName = "setting.common";
    private static final String updateKey = "autoCheckUpdate";
    private static final String versionKey = "beforeVersion";

    /**
     * 是否自动更新
     */
    public Boolean isAutoCheckUpdate(Config config) {
        return this.getBool(updateKey, groupName, true);
    }

    /**
     * 设置是否自动更新
     */
    public void setAutoCheckUpdate(Boolean autoCheckUpdate) {
        this.putByGroup(updateKey, groupName, String.valueOf(autoCheckUpdate));
        this.save();
    }

    /**
     * 获取上一个版本
     */
    public String getBeforeVersion() {
        return this.getStr(versionKey, groupName, "1.0.0");
    }

    /**
     * 设置上一个版本
     */
    public void setBeforeVersion(String beforeVersion) {
        this.putByGroup(versionKey, groupName, beforeVersion);
        this.save();
    }
}
