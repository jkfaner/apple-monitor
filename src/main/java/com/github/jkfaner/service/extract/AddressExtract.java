package com.github.jkfaner.extract;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.domain.vo.AddressVo;
import com.github.jkfaner.util.JsonPathFinder;

import java.util.List;


/**
 * 提取实现
 *
 * @author: LiamLee
 * @since: 2022-09-26
 **/
public class AddressExtract implements IBaseObject<AddressExtract> {

    private static AddressExtract addressExtract;

    @Override
    public AddressExtract getInstance() {
        addressExtract = addressExtract == null ? new AddressExtract() : addressExtract;
        return addressExtract;
    }


    /**
     * 提取地址
     *
     * @param jsonStr 数据
     * @return 结果
     */
    public List<AddressVo> addressList(String jsonStr) {
        JsonPathFinder jsonPathFinder = new JsonPathFinder(jsonStr);
        Object data = jsonPathFinder.getValue("data", 1);
        JSONArray array = (JSONArray) data;
        List<AddressVo> addressVos = array.toList(AddressVo.class);
        if (jsonPathFinder.checkKeyExist("city")) {
            String city = jsonPathFinder.getFirst("city").toString();
            addressVos.forEach(e -> e.setCity(city));
        }
        return addressVos;

    }

    /**
     * 提取最终结果
     *
     * @param jsonStr 数据
     * @return 数据
     */
    public String addressResult(String jsonStr) {
        JSONObject parseObj = JSONUtil.parseObj(jsonStr);
        Object byPath = parseObj.getByPath("body.provinceCityDistrict");
        return (String) byPath;
    }
}
