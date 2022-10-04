package com.github.jkfaner.ui;

import com.github.jkfaner.domain.bo.autocomplete.StoreDataBo;
import com.github.jkfaner.domain.dto.InventoryDto;
import com.github.jkfaner.domain.dto.PreciseInventoryDto;
import com.github.jkfaner.sdk.ServiceSDK;
import com.github.jkfaner.service.MessageService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiamLee
 * @since: 2022-10-01
 **/
public class MainTest extends BaseJFrame {

    /**
     * 设置背景图
     */
    private void setBackGround(String imagePath) {
        //背景图片
        ImageIcon background = new ImageIcon(imagePath);
        // 把背景图片放在在一个标签里面 图片自适应
        JLabel label = new JLabel(background) {
            // 匿名类部类重写paint方法
            public void paint(Graphics g) {
                Image img = background.getImage();
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        //标签的大小位置设置
        label.setBounds(0, 0, this.getWidth(), this.getHeight());
        //把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明，要强制转化
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        //把背景图片添加到最底层作为背景
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
    }

    private void createMain() {
        // 设置窗口的标题
        this.setTitle("Hello");
        // 设置窗口位置及尺寸
        this.setBounds(500, 500, 440, 570);
        // 设置单击“关闭”按钮时退出程序
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createContent();
        // 禁止窗口改变
        this.setResizable(false);
        // 窗口屏幕中间显示
        this.setLocationRelativeTo(null);
        // 设置窗口是否可见，如果设置false或不设置那么窗口将不可见
        this.setVisible(true);
    }

    private void createContent() {
        // 设置背景图
        String path = this.getClass().getClassLoader().getResource("bg.jpg").getPath();
        setBackGround(path);

        JPanel paneMain = new JPanel(null);
        // 设置panel为透明的，否则会挡住后面的背景图片
        paneMain.setOpaque(false);
        JButton mainBtn = new JButton("进入监控主程序");
        mainBtn.setBounds(20, 240, 400, 50);
        // 设置字体
        Font font = new Font("宋体",Font.PLAIN,14);
        mainBtn.setFont(font);

        // 绘制边框
        mainBtn.setBorderPainted(true);
        // 添加按钮的点击事件监听器
        mainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 销毁当前页面
                closeThis();
                // 打开新页面
                System.out.println("按钮被点击");
            }
        });
        paneMain.add(mainBtn);
        this.add(paneMain);
    }

    private static void inventory(){
        MessageService storeService = new MessageService();
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setLocation("四川 成都 锦江区");
        inventoryDto.setModelList(new ArrayList<String>(){{
            add("MPVG3CH/A");
            add("MQ0D3CH/A");
            add("MQ863CH/A");
        }});
    }

    private static void preciseInventory(){
        MessageService storeService = new MessageService().getInstance();
        PreciseInventoryDto inventoryDto = new PreciseInventoryDto();
        inventoryDto.setStore("R448");
        inventoryDto.setModelList(new ArrayList<String>(){{
            add("MPVG3CH/A");
            add("MQ0D3CH/A");
            add("MQ863CH/A");
        }});
    }

    public static void main(String[] args) {
        ServiceSDK serviceSDK = new ServiceSDK().getInstance();
        List<StoreDataBo> r448 = serviceSDK.getStoreList("zh_CN");
        System.out.println(r448);
    }
}
