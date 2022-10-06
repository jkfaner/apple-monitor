package com.github.jkfaner.service.extract;

import cn.hutool.json.JSONArray;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.domain.bo.autocomplete.StoreDataBo;
import com.github.jkfaner.common.util.JsonPathFinder;

import java.util.List;

/**
 * @author: LiamLee
 * @since: 2022-09-28
 **/
public class StoreExtract implements IBaseObject<StoreExtract> {

    private static StoreExtract storeExtract;

    @Override
    public StoreExtract getInstance() {
        storeExtract = storeExtract == null ? new StoreExtract() : storeExtract;
        return storeExtract;
    }



    /**
     * 提取零售店信息
     * @param jsonStr 响应字符串
     * @return 结果
     */
    public List<StoreDataBo> storeList(String jsonStr){
        JsonPathFinder jsonPathFinder = new JsonPathFinder(jsonStr);
        Object storeListData = jsonPathFinder.getFirst("storeListData");
        JSONArray storeList = (JSONArray) storeListData;
        return storeList.toList(StoreDataBo.class);
    }
}
