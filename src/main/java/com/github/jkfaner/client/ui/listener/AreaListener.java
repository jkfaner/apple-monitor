package com.github.jkfaner.ui.listener;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.github.jkfaner.domain.dto.AddressDto;
import com.github.jkfaner.sdk.ServiceSDK;
import com.github.jkfaner.ui.constant.UIConstants;
import com.github.jkfaner.ui.form.AreaFrom;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

/**
 * 地区监听器
 *
 * @author: LiamLee
 * @since: 2022-10-02
 **/
public class AreaListener {

    private static final Log logger = LogFactory.get();

    /**
     * 直辖市
     */
    private static final String[] MUNICIPALITY_DIRECTLY_UNDER = {"北京","上海","天津","重庆"};

    /**
     * 监控省份
     */
    private static void listeningProvince(){
        AreaFrom areaFrom = new AreaFrom().getInstance();
        ServiceSDK serviceSDK = new ServiceSDK().getInstance();

        // 多级地区联动下拉栏监听器:省份
        areaFrom.getProvinceBox().addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        // 选中
                        if (e.getStateChange() == ItemEvent.SELECTED
//                        &&!areaFrom.getProvinceBox().getSelectedItem().toString().equals(UIConstants.DEFAULT_SELECT)
                        ) {
                            long start = System.currentTimeMillis();
                            // 初始化
                            areaFrom.getCityBox().removeAllItems();
                            areaFrom.getCityBox().addItem(UIConstants.DEFAULT_SELECT);

                            String province = Objects.requireNonNull(areaFrom.getProvinceBox().getSelectedItem()).toString();
                            boolean isMunicipalityDirectlyUnder = false;
                            for (String s : MUNICIPALITY_DIRECTLY_UNDER) {
                                if (s.equals(province)){
                                    areaFrom.getCityBox().removeAllItems();
                                    areaFrom.getCityBox().addItem(province);
                                    isMunicipalityDirectlyUnder = true;
                                }
                            }
                            if (!isMunicipalityDirectlyUnder){
                                AddressDto addressDto = new AddressDto();
                                addressDto.setState(province);
                                serviceSDK.getAddressList(addressDto).forEach(vo -> areaFrom.getCityBox().addItem(vo.getValue()));
                            }
                            long end = System.currentTimeMillis();
                            logger.info("省份耗时:{"+(end-start)+"}");
                        }
                    }
                }

        );
    }

    /**
     * 监控城市
     */
    private static void listeningCity(){
        AreaFrom areaFrom = new AreaFrom().getInstance();
        ServiceSDK serviceSDK = new ServiceSDK().getInstance();

        // 多级地区联动下拉栏监听器:城市
        areaFrom.getCityBox().addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        // 选中
                        if (e.getStateChange() == ItemEvent.SELECTED
//                                &&!areaFrom.getCityBox().getSelectedItem().toString().equals(UIConstants.DEFAULT_SELECT)
                        ) {
                            long start = System.currentTimeMillis();
                            // 初始化
                            areaFrom.getDistrictBox().removeAllItems();
                            areaFrom.getDistrictBox().addItem(UIConstants.DEFAULT_SELECT);

                            String province = Objects.requireNonNull(areaFrom.getProvinceBox().getSelectedItem()).toString();
                            String city = Objects.requireNonNull(areaFrom.getCityBox().getSelectedItem()).toString();
                            AddressDto addressDto = new AddressDto();
                            addressDto.setState(province);
                            addressDto.setCity(city);
                            serviceSDK.getAddressList(addressDto).forEach(vo -> areaFrom.getDistrictBox().addItem(vo.getValue()));
                            long end = System.currentTimeMillis();
                            logger.info("城市耗时:{"+(end-start)+"}");
                        }
                    }
                }
        );
    }

    /**
     * 监控区县
     */
    private static void listeningDistrict(){
        AreaFrom areaFrom = new AreaFrom().getInstance();
        ServiceSDK serviceSDK = new ServiceSDK().getInstance();

        // 多级地区联动下拉栏监听器:区县
        areaFrom.getDistrictBox().addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        // 选中
                        if (e.getStateChange() == ItemEvent.SELECTED
//                                &&!areaFrom.getDistrictBox().getSelectedItem().toString().equals(UIConstants.DEFAULT_SELECT)
                        ) {
                            long start = System.currentTimeMillis();
                            String selectedItem = Objects.requireNonNull(areaFrom.getDistrictBox().getSelectedItem()).toString();
                            AddressDto addressDto = new AddressDto();
                            addressDto.setState(Objects.requireNonNull(areaFrom.getProvinceBox().getSelectedItem()).toString());
                            addressDto.setCity(Objects.requireNonNull(areaFrom.getCityBox().getSelectedItem()).toString());
                            addressDto.setDistrict(selectedItem);

                            String addressVos = serviceSDK.getAddressResult(addressDto);
                            long end = System.currentTimeMillis();
                            logger.info("区县耗时:{"+(end-start)+"}");
                        }
                    }
                }
        );
    }

    public static void addListeners() {
        listeningProvince();
        listeningCity();
        listeningDistrict();
    }
}
