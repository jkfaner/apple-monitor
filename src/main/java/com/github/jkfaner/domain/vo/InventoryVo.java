package com.github.jkfaner.domain.vo;

import lombok.Data;

/**
 * @author: LiamLee
 * @since: 2022-09-26
 **/
@Data
public class InventoryVo{
    private String city;
    private String state;
    private String storeName;
    private String storeNumber;

    private String partNumber;
    private String pickupSearchQuote;
    private String storePickupProductTitle;
    private String buyLink;
    private Boolean enable;
}
