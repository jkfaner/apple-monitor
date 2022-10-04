package com.github.jkfaner.domain.bo.fulfillment;

import lombok.Data;


/**
 * @author: LiamLee
 * @since: 2022-09-27
 **/
@Data
public class ProductBo {
    private Boolean storePickEligible;
    private String pickupSearchQuote;
    private String partNumber;
    private String purchaseOption;
    private String ctoOptions;
    private String pickupDisplay;
    private String pickupType;
    private MessageTypeBo messageTypes;
}
