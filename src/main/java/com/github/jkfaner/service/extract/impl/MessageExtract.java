package com.github.jkfaner.service.extract;

import cn.hutool.json.JSONArray;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.constant.ServerConstants;
import com.github.jkfaner.domain.bo.fulfillment.InventoryBo;
import com.github.jkfaner.domain.bo.fulfillment.MessageTypeBo;
import com.github.jkfaner.domain.bo.fulfillment.ProductBo;
import com.github.jkfaner.domain.vo.SInventoryVo;
import com.github.jkfaner.common.util.JsonPathFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 库存查询信息提取
 *
 * @author: LiamLee
 * @since: 2022-10-02
 **/
public class MessageExtract implements IBaseObject<MessageExtract> {

    private static MessageExtract messageExtract;

    @Override
    public MessageExtract getInstance() {
        messageExtract = messageExtract == null ? new MessageExtract() : messageExtract;
        return messageExtract;
    }

    /**
     * 检查库存
     *
     * @return 结果
     */
    public ArrayList<SInventoryVo> checkInventory(List<InventoryBo> inventoryBoList) {
        ArrayList<SInventoryVo> inventoryVos = new ArrayList<>();
        for (InventoryBo inventoryBo : inventoryBoList) {
            Map<String, ProductBo> productBoMap = inventoryBo.getPartsAvailability();
            for (String key : productBoMap.keySet()) {
                SInventoryVo inventoryVo = new SInventoryVo();
                inventoryVo.setCity(inventoryBo.getCity());
                inventoryVo.setState(inventoryBo.getState());
                inventoryVo.setStoreName(inventoryBo.getStoreName());
                inventoryVo.setStoreNumber(inventoryBo.getStoreNumber());
                ProductBo productBo = productBoMap.get(key);
                inventoryVo.setPartNumber(productBo.getPartNumber());
                inventoryVo.setPickupSearchQuote(productBo.getPickupSearchQuote());
                MessageTypeBo messageTypes = productBo.getMessageTypes();
                String productTitle = messageTypes.getRegular().getStorePickupProductTitle();
                inventoryVo.setStorePickupProductTitle(productTitle);
                // iPhone 14 Pro系列购买链接
                inventoryVo.setBuyLink(ServerConstants.BUY_IPHONE_14_PRO + productBo.getPartNumber());
                inventoryVo.setEnable(!productBo.getPickupSearchQuote().equals("暂无供应"));
                inventoryVos.add(inventoryVo);
            }
        }
        return inventoryVos;
    }


    /**
     * 提取库存信息
     *
     * @return 结果
     */
    public List<InventoryBo> stockInfo(String jsonStr) {
        // 获取所有零售店
        JsonPathFinder jsonPathFinder = new JsonPathFinder(jsonStr);
        Object stores = jsonPathFinder.getFirst("stores");
        JSONArray storeList = (JSONArray) stores;
        return storeList.toList(InventoryBo.class);
    }
}
