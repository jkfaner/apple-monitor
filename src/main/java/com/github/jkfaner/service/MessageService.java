package com.github.jkfaner.service;

import cn.hutool.http.HttpUtil;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.constant.ServerConstants;
import com.github.jkfaner.domain.dto.InventoryDto;
import com.github.jkfaner.domain.dto.PreciseInventoryDto;

import java.util.HashMap;
import java.util.List;

/**
 * 信息服务
 * @author: LiamLee
 * @since: 2022-09-26
 **/
public class MessageService implements IBaseObject<MessageService> {

    private static MessageService storeService;

    @Override
    public MessageService getInstance() {
        storeService = storeService == null ? new MessageService() : storeService;
        return storeService;
    }

    private HashMap<String, Object> initParams(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pl", true);
        hashMap.put("mts.0", ServerConstants.REGULAR_PARAM); // deliveryMessage中regular参数
        hashMap.put("mts.1", ServerConstants.COMPACT_PARAM); // deliveryMessage中compact参数
        return hashMap;
    }

    /**
     * 供货查询：此地附近的Apple Store零售店
     */
    public String checkLocalInventory(InventoryDto inventory) {
        HashMap<String, Object> hashMap = initParams();
        List<String> modelList = inventory.getModelList();
        for (int i = 0; i < modelList.size(); i++) {
            hashMap.put("parts."+i, modelList.get(i));
        }
        hashMap.put("location", inventory.getLocation());
        return HttpUtil.get(ServerConstants.FULFILLMENT_MESSAGES_URL, hashMap);
    }

    /**
     * 供货查询：精确的Apple Store零售店
     */
    public String checkLocalPreciseInventory(PreciseInventoryDto preciseInventory) {
        HashMap<String, Object> hashMap = initParams();
        List<String> modelList = preciseInventory.getModelList();
        for (int i = 0; i < modelList.size(); i++) {
            hashMap.put("parts."+i, modelList.get(i));
        }
        hashMap.put("store", preciseInventory.getStore());
        return HttpUtil.get(ServerConstants.FULFILLMENT_MESSAGES_URL, hashMap);
    }

}
