package com.github.jkfaner.client.ui.mapper;

/**
 * 主题Mapper
 * @author: LiamLee
 * @since: 2022-10-05
 **/
public interface IThemeMapper {

    void setTheme(String themeName);

    String getTheme();

    boolean isDarkLaf();
}
