package com.github.jkfaner.sdk;

import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.domain.bo.autocomplete.StoreDataBo;
import com.github.jkfaner.domain.dto.AddressDto;
import com.github.jkfaner.domain.vo.AddressVo;
import com.github.jkfaner.extract.AddressExtract;
import com.github.jkfaner.extract.StoreExtract;
import com.github.jkfaner.service.AddressService;
import com.github.jkfaner.service.StoreService;

import java.util.List;

/**
 * @author: LiamLee
 * @since: 2022-10-02
 **/
public class ServiceSDK implements IBaseObject<ServiceSDK> {

    private static ServiceSDK serviceSDK;

    @Override
    public ServiceSDK getInstance() {
        serviceSDK = serviceSDK == null ? new ServiceSDK() : serviceSDK;
        return serviceSDK;
    }

    /**
     * 获取地址列表
     */
    public List<AddressVo> getAddressList(AddressDto addressDto){
        AddressService addressService = new AddressService().getInstance();
        AddressExtract addressExtract = new AddressExtract().getInstance();
        String result = addressService.addressList(addressDto);
        List<AddressVo> addressVos = addressExtract.addressList(result);
        System.out.println(addressVos);
        return addressVos;
    }

    /**
     * 获取地址结果
     */
    public String getAddressResult(AddressDto addressDto){
        AddressService addressService = new AddressService().getInstance();
        AddressExtract addressExtract = new AddressExtract().getInstance();
        String result = addressService.addressList(addressDto);
        return addressExtract.addressResult(result);
    }

    /**
     * 获取零售店列表
     */
    public List<StoreDataBo> getStoreList(String locale){
        StoreService storeService = new StoreService().getInstance();
        StoreExtract storeExtract = new StoreExtract().getInstance();
        String result = storeService.storeList(locale);
        return storeExtract.storeList(result);
    }
}
