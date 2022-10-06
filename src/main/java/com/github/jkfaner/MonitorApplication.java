package com.github.jkfaner;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.formdev.flatlaf.util.SystemInfo;
import com.github.jkfaner.client.util.InitUtils;
import com.github.jkfaner.client.ui.form.LoadingForm;
import com.github.jkfaner.client.ui.form.MainWindow;
import com.github.jkfaner.client.ui.frame.MainFrame;
import com.github.jkfaner.client.util.ConfigUtil;
import com.github.jkfaner.client.util.UiUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 *
 * @author: LiamLee
 * @since: 2022-09-25
 **/
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(YxkjPhotoGraphyApplication.class, args);

    }
}
