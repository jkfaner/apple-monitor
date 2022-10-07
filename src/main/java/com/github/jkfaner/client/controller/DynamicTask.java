package com.github.jkfaner.client.common;

import com.github.jkfaner.client.controller.MonitorController;
import com.github.jkfaner.client.ui.form.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.util.Date;

/**
 * 动态任务
 * @author: jkfaner
 * @since: 2022-10-07
 **/
@Component
@EnableScheduling
public class DynamicTask implements SchedulingConfigurer {

    private static Logger log = LoggerFactory.getLogger(DynamicTask.class);

    @Autowired
    MonitorController monitorController;

    @Autowired
    MainWindow mainWindow;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(doTask(), getTrigger());
    }

    private Runnable doTask() {
        return new Runnable() {
            @Override
            public void run() {
                // 业务逻辑
                log.info("执行了MyDynamicTask,时间为:" + new Date(System.currentTimeMillis()));
//                monitorController.startMonitor();
            }
        };
    }

    private Trigger getTrigger() {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 触发器
                CronTrigger trigger = new CronTrigger(getCron());
                return trigger.nextExecutionTime(triggerContext);
            }
        };
    }

    public String getCron() {
        JComboBox timeBox = mainWindow.getTimeBox();
        String timeStr = (String)timeBox.getSelectedItem();
        System.out.println(timeStr);
        return "0/1 * * * * ?";
    }
}
