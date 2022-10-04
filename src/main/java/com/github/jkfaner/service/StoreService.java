package com.github.jkfaner.service;

import cn.hutool.http.HttpUtil;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.constant.ServerConstants;

import java.util.HashMap;

/**
 * 零售店服务
 *
 * @author: LiamLee
 * @since: 2022-10-02
 **/
public class StoreService implements IBaseObject<StoreService> {

    private static StoreService storeService;

    @Override
    public StoreService getInstance() {
        storeService = storeService == null ? new StoreService() : storeService;
        return storeService;
    }

    /**
     * 获取零售店信息
     *
     * @param locale 地址
     * @return 结果
     */
    public String storeList(String locale) {
        HashMap<String, Object> map = new HashMap();
        map.put("locale", locale);
        return HttpUtil.get(ServerConstants.AUTOCOMPLETE_URL, map);
    }
}
