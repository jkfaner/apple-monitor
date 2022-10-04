package com.github.jkfaner.server.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @doclink: <a>https://github.com/Finb/Bark</a>
 * @author: LiamLee
 * @since: 2022-10-03
 **/
@Data
public class Bark implements Serializable {

    private static final long serialVersionUID = 1086505293053383014L;

    /**
     * key
     */
    private String yourKey;

    /**
     * 是否自动复制到剪切板 1-是
     */
    private Integer automaticallyCopy;

    /**
     * 需要复制的内容
     */
    private String copy;

    /**
     * 点击推送将跳转到url的地址（发送时，URL参数需要编码）
     * https://api.day.app/yourkey/百度网址?url=https://www.baidu.com
     */
    private String url;

    /**
     * 指定是否需要保存推送信息到历史记录，1 为保存，其他值为不保存。
     * 如果不指定这个参数，推送信息将按照APP内设置来决定是否保存。
     */
    private Integer isArchive;

    /**
     * 指定推送消息分组，可在历史记录中按分组查看推送。
     * https://api.day.app/yourkey/需要分组的推送?group=groupName
     */
    private String group;

    /**
     * icon (仅 iOS15 或以上支持）
     * // 指定推送消息图标
     * https://api.day.app/yourkey/需要自定义图标的推送?icon=http://day.app/assets/images/avatar.jpg
     */
    private String icon;

    /**
     * 时效性通知
     *
     * 设置时效性通知
     * https://api.day.app/yourkey/时效性通知?level=timeSensitive
     *
     * 可选参数值
     * active：不设置时的默认值，系统会立即亮屏显示通知。
     * timeSensitive：时效性通知，可在专注状态下显示通知。
     * passive：仅将通知添加到通知列表，不会亮屏提醒
     */
    private String level;
}
