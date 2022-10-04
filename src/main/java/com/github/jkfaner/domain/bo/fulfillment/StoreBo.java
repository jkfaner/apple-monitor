package com.github.jkfaner.domain.bo.fulfillment;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: LiamLee
 * @since: 2022-09-27
 **/
@Data
public class StoreBo {
    private String storeNumber;
    private String storeUniqueId;
    private String name;
    private String storeTypeKey;
    private String storeSubTypeKey;
    private String storeType;
    private String phoneNumber;
    private String email;
    private String carrierCode;
    private String locationType;
    private Double latitude;
    private Double longitude;
    private AddressBo address;
    private String urlKey;
    private String directionsUrl;
    private String storeImageUrl;
    private String makeReservationUrl;
    private String hoursAndInfoUrl;
    private List<Map<String,String>> storeHours;
    private List<Map<String,String>> storeHolidays;
    private String secureStoreImageUrl;
    private Float distance;
    private String distanceUnit;
    private String distanceWithUnit;
    private String timezone;
    private Boolean storeIsActive;
    private Integer lastUpdated;
    private Integer lastFetched;
    private String dateStamp;

}
