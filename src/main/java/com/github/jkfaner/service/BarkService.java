package com.github.jkfaner.service;

import cn.hutool.http.HttpUtil;
import com.github.jkfaner.bean.Bark;
import com.github.jkfaner.common.IBaseObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Bark推送服务
 *
 * @author: LiamLee
 * @since: 2022-10-03
 **/

public class BarkService implements IBaseObject<BarkService> {

    private static BarkService barkService;

    private static final String BARK_SERVER_URL = "https://api.day.app/";

    @Override
    public BarkService getInstance() {
        barkService = barkService == null ? new BarkService() : barkService;
        return barkService;
    }

    private String doGet(Bark bark, String content, Map<String, Object> paramMap){
        String url = BARK_SERVER_URL + "/" +content+"/"+ bark.getYourKey();
        return HttpUtil.get(url, paramMap);
    }

    /**
     * 推送
     * @param bark
     * @param content
     * @return
     */
    public String push(Bark bark, String content){
        Map<String, Object> map = new HashMap<>();
        map.put("isArchive",1);
        return doGet(bark, content, map);
    }

}
