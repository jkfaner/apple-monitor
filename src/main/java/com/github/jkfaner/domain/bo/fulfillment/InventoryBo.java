package com.github.jkfaner.domain.bo.fulfillment;

import lombok.Data;

import java.util.Map;

/**
 * @author: LiamLee
 * @since: 2022-09-27
 **/
@Data
public class InventoryBo {
    private String storeEmail;
    private String storeName;
    private String reservationUrl;
    private String makeReservationUrl;
    private String state;
    private String storeImageUrl;
    private String country;
    private String city;
    private String storeNumber;
    private String phoneNumber;
    private String hoursUrl;
    private StoreBo retailStore;
    private Map<String,ProductBo> partsAvailability;
}
