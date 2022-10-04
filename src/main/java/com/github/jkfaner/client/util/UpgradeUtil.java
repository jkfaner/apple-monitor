package com.github.jkfaner.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.github.jkfaner.Application;
import com.github.jkfaner.service.domain.VersionSummary;
import com.github.jkfaner.constant.AppConstants;
import com.github.jkfaner.ui.constant.UIConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 升级工具类
 * @author: LiamLee
 * @since: 2022-10-01
 **/
@Slf4j
public class UpgradeUtil {

    /**
     * 检查更新
     */
    public static void checkUpdate(boolean initCheck) {
        // 当前版本
        String currentVersion = AppConstants.APP_VERSION;

        // Get information about the latest version from github
        String versionSummaryJsonContent = HttpUtil.get(AppConstants.CHECK_VERSION_URL);
        if (org.apache.commons.lang3.StringUtils.isEmpty(versionSummaryJsonContent) && !initCheck) {
            JOptionPane.showMessageDialog(Application.mainFrame,
                    "Check for timeouts, follow GitHub Release!", "Network error",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (StringUtils.isEmpty(versionSummaryJsonContent) || versionSummaryJsonContent.contains("404: Not Found")) {
            return;
        }
        versionSummaryJsonContent = versionSummaryJsonContent.replace("\n", "");

        VersionSummary versionSummary = JSON.parseObject(versionSummaryJsonContent, VersionSummary.class);
        // 最新版本
        String newVersion = versionSummary.getCurrentVersion();
        String versionIndexJsonContent = versionSummary.getVersionIndex();
        // 版本索引
        Map<String, String> versionIndexMap = JSON.parseObject(versionIndexJsonContent, Map.class);
        // 版本详细信息列表
        List<VersionSummary.Version> versionDetailList = versionSummary.getVersionDetailList();

        if (newVersion.compareTo(currentVersion) > 0) {
            // 当前版本索引
            int currentVersionIndex = Integer.parseInt(versionIndexMap.get(currentVersion));
            // 版本更新日志信息
            StringBuilder versionLogBuilder = new StringBuilder("<h1>当前有个新本! Download it now?</h1>");
            VersionSummary.Version version;
            for (int i = currentVersionIndex + 1; i < versionDetailList.size(); i++) {
                version = versionDetailList.get(i);
                versionLogBuilder.append("<h2>").append(version.getVersion()).append("</h2>");
                versionLogBuilder.append("<b>").append(version.getTitle()).append("</b><br/>");
                versionLogBuilder.append("<p>").append(version.getLog().replaceAll("\\n", "</p><p>")).append("</p>");
            }
            String versionLog = versionLogBuilder.toString();

            /*
            UpdateInfoDialog updateInfoDialog = new UpdateInfoDialog();
            updateInfoDialog.setHtmlText(versionLog);
            updateInfoDialog.setNewVersion(newVersion);
            updateInfoDialog.pack();
            updateInfoDialog.setVisible(true);
             */
        } else {
            if (!initCheck) {
                JOptionPane.showMessageDialog(Application.mainFrame,
                        "已经是最新版本", "版本更新",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * 平滑升级
     * 所涉及的版本更新脚本和sql方法应尽可能幂等，以避免由于升级过程中的电源故障和死亡等异常中断而重复升级操作
     */
    public static void smoothUpgrade() {
        // 获取当前版本
        String currentVersion = AppConstants.APP_VERSION;
        // 获取升级前版本
        String beforeVersion = Application.config.getBeforeVersion();

        if (currentVersion.compareTo(beforeVersion) <= 0) {
            // 如果两者一致，则不执行升级操作
            return;
        } else {
            log.info("平滑升级开始");

            // 然后获取两个版本的索引
            String versionSummaryJsonContent = FileUtil.readString(Objects.requireNonNull(UIConstants.class.getResource("/version_summary.json")), CharsetUtil.UTF_8);
            versionSummaryJsonContent = versionSummaryJsonContent.replace("\n", "");
            VersionSummary versionSummary = JSON.parseObject(versionSummaryJsonContent, VersionSummary.class);
            String versionIndex = versionSummary.getVersionIndex();
            Map<String, String> versionIndexMap = JSON.parseObject(versionIndex, Map.class);
            int currentVersionIndex = Integer.parseInt(versionIndexMap.get(currentVersion));
            int beforeVersionIndex = Integer.parseInt(versionIndexMap.get(beforeVersion));
            log.info("旧版本{}", beforeVersion);
            log.info("当前版本{}", currentVersion);
            // 遍历索引范围
            beforeVersionIndex++;
            for (int i = beforeVersionIndex; i <= currentVersionIndex; i++) {
                log.info("从索引 {} 开始更新版本", i);
                // 执行每个版本索引的更新，从遥远的时间到最近的时间
                upgrade(i);
                log.info("从索引 {} 结束更新版本", i);
            }

            // 如果升级完成并成功，则将升级前的版本号分配给当前版本
            Application.config.setBeforeVersion(currentVersion);
            Application.config.save();
            log.info("平滑升级结束");
        }
    }

    /**
     * 执行升级脚本
     *
     * @param versionIndex 版本索引
     */
    private static void upgrade(int versionIndex) {
        log.info("开始执行升级脚本, 版本索引:{}", versionIndex);
        switch (versionIndex) {
            // TODO 设置更新方法
            case 21:
                break;
            default:
        }
        log.info("升级脚本执行结束, 版本索引:{}", versionIndex);
    }
}
