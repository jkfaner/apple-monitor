package com.github.jkfaner.service;

import cn.hutool.http.HttpUtil;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.constant.ServerConstants;
import com.github.jkfaner.domain.dto.AddressDto;

import java.util.HashMap;

/**
 * @author: LiamLee
 * @since: 2022-09-28
 **/
public class AddressService implements IBaseObject<AddressService> {

    private static AddressService addressService;

    @Override
    public AddressService getInstance() {
        addressService = addressService == null ? new AddressService() : addressService;
        return addressService;
    }

    /**
     * 获取地址
     * @param address 地址
     * @return 结果
     */
    public String addressList(AddressDto address) {
        String result;
        if (address.getState() == null) {
            result = HttpUtil.get(ServerConstants.ADDRESS_LOOKUP_URL);
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("state", address.getState());
            map.put("city", address.getCity());
            map.put("district", address.getDistrict());
            result = HttpUtil.get(ServerConstants.ADDRESS_LOOKUP_URL, map);
        }
        return result;
    }

}
