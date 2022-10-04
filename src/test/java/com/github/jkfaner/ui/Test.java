package com.github.jkfaner.ui;

import com.github.jkfaner.domain.dto.AddressDto;
import com.github.jkfaner.domain.vo.AddressVo;
import com.github.jkfaner.service.AddressService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;

/**
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class Test extends BaseJFrame{
    private JComboBox stateBox;
    private JComboBox cityBox;
    private JComboBox districtBox;

    private AddressDto addressDto;
    private AddressService addressService;
    private List<AddressVo> stateAddressVos;
    private List<AddressVo> cityAddressVos;
    private List<AddressVo> districtAddressVos;


    private void createMenuBar() {
        // 创建一个菜单栏
        JMenuBar menuBar = new JMenuBar();
        JMenu menuMenu = new JMenu("菜单");
        JMenu modelMenu = new JMenu("模式");
        // 一级菜单添加到菜单栏
        menuBar.add(menuMenu);
        menuBar.add(modelMenu);

        // 子菜单
        JMenu areaMenu = new JMenu("地区");
        JMenuItem zhMenu = new JRadioButtonMenuItem("大陆");
        JMenuItem hkMenu = new JRadioButtonMenuItem("香港");
        areaMenu.add(zhMenu);
        areaMenu.add(hkMenu);
        // 其中两个 单选按钮子菜单，要实现单选按钮的效果，需要将它们放到一个按钮组中
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(zhMenu);
        btnGroup.add(hkMenu);
        zhMenu.setSelected(true);

        JMenuItem cleanMenu = new JMenuItem("清空缓存");
        JMenuItem aboutMenu = new JMenuItem("关于");
        JMenuItem exitMenuItem = new JMenuItem("退出");
        // 子菜单添加到一级菜单
        menuMenu.add(areaMenu);
        menuMenu.add(cleanMenu);
        menuMenu.add(aboutMenu);
        menuMenu.addSeparator();// 添加一条分割线
        menuMenu.add(exitMenuItem);

        // 设置 "退出" 子菜单被点击的监听器
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeThis();
            }
        });

        // 设置 "大陆" 子菜单被点击的监听器
        zhMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zhMenu.setSelected(true);
                hkMenu.setSelected(false);
                System.out.println("地区-大陆 是否被选中: " + zhMenu.isSelected());
            }
        });

        // 设置 "香港" 子菜单被点击的监听器
        hkMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zhMenu.setSelected(false);
                hkMenu.setSelected(true);
                System.out.println("地区-香港 是否被选中: " + zhMenu.isSelected());
            }
        });

        /*
         * 最后 把菜单栏设置到窗口
         */
        this.setJMenuBar(menuBar);
    }


}
