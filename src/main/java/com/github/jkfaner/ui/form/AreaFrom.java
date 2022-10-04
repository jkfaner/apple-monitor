package com.github.jkfaner.ui.form;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.domain.dto.AddressDto;
import com.github.jkfaner.domain.vo.AddressVo;
import com.github.jkfaner.sdk.ServiceSDK;
import com.github.jkfaner.ui.constant.UIConstants;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 地区表单
 *
 * @author: LiamLee
 * @since: 2022-10-01
 **/
@Getter
public class AreaFrom implements IBaseObject<AreaFrom> {
    private static final Log logger = LogFactory.get();

    private static AreaFrom areaFrom;

    private JPanel mainJPanel;
    private JComboBox provinceBox;
    private JComboBox cityBox;
    private JComboBox districtBox;
    private JLabel provinceLabel;
    private JLabel cityLabel;
    private JLabel districtLabel;

    @Override
    public AreaFrom getInstance() {
        areaFrom = areaFrom == null ? new AreaFrom() : areaFrom;
        return areaFrom;
    }

    /**
     * 初始化
     */
    public static void init() {
        areaFrom = new AreaFrom().getInstance();
        initInfo();
    }

    /**
     * 初始化信息
     */
    private static void initInfo() {
        areaFrom = new AreaFrom().getInstance();
        // 获取地址
        ServiceSDK serviceSDK = new ServiceSDK().getInstance();
        List<AddressVo> addressList = serviceSDK.getAddressList(new AddressDto());

        // 下拉栏初始化
        areaFrom.getProvinceBox().addItem(UIConstants.DEFAULT_SELECT);
        areaFrom.getCityBox().addItem(UIConstants.DEFAULT_SELECT);
        areaFrom.getDistrictBox().addItem(UIConstants.DEFAULT_SELECT);
        addressList.forEach(addressVo -> areaFrom.getProvinceBox().addItem(addressVo.getValue()));
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainJPanel = new JPanel();
        mainJPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainJPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        provinceLabel = new JLabel();
        provinceLabel.setText("省份");
        panel1.add(provinceLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        provinceBox = new JComboBox();
        panel1.add(provinceBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityLabel = new JLabel();
        cityLabel.setText("城市");
        panel1.add(cityLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityBox = new JComboBox();
        panel1.add(cityBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        districtLabel = new JLabel();
        districtLabel.setText("区县");
        panel1.add(districtLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        districtBox = new JComboBox();
        panel1.add(districtBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainJPanel;
    }

}
