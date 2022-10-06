package com.github.jkfaner.client.ui.mapper.impl;

import com.github.jkfaner.client.ui.mapper.IFontMapper;
import com.github.jkfaner.client.util.SystemUtil;
import org.springframework.stereotype.Component;

/**
 * @author: LiamLee
 * @since: 2022-10-05
 **/
@Component
public class FontMapper extends BaseMapper implements IFontMapper {

    private static final String groupName = "setting.appearance";
    private static final String fontKey = "font";
    private static final String fontSize = "fontSize";

    /**
     * 获取字体
     */
    @Override
    public String getFont() {
        if (SystemUtil.isLinuxOs()) {
            return this.getStr(fontKey, groupName, "Noto Sans CJK HK");
        } else {
            return this.getStr(fontKey, groupName, "Microsoft YaHei");
        }
    }

    /**
     * 设置字体
     */
    @Override
    public void setFont(String font) {
        this.putByGroup(fontKey, groupName, font);
        this.save();
    }

    /**
     * 获取字体大小
     */
    @Override
    public int getFontSize() {
        return this.getInt(fontSize, groupName, 13);
    }

    /**
     * 设置字体大小
     */
    @Override
    public void setFontSize(int fontSize) {
        setFontSize(String.valueOf(fontSize));
    }

    /**
     * 设置字体大小
     */
    @Override
    public void setFontSize(String fontSize) {
        this.putByGroup(fontSize, groupName, fontSize);
        this.save();
    }
}
