package com.github.jkfaner.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 版本总结类
 * @author: LiamLee
 * @since: 2022-10-01
 **/
@Data
public class VersionSummary implements Serializable {

    private static final long serialVersionUID = -9186790082244663319L;

    /**
     * 当前版本
     */
    private String currentVersion;

    /**
     * 版本索引
     */
    private String versionIndex;

    /**
     * 版本详细信息列表
     */
    private List<Version> versionDetailList;

    /**
     * 版本类
     * @author: LiamLee
     * @since: 2022-10-01
     */
    @Data
    public static class Version implements Serializable {

        private static final long serialVersionUID = 4637273116136790268L;

        /**
         * 版本号
         */
        private String version;

        /**
         * 标题
         */
        private String title;

        /**
         * 日志
         */
        private String log;

    }
}
