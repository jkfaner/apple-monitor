package com.github.jkfaner.domain.bo.fulfillment;

import lombok.Data;

/**
 * @author: LiamLee
 * @since: 2022-09-28
 **/
@Data
public class RegularBo {
    private Boolean storeSearchEnabled;
    private Boolean storeSelectionEnabled;
    private String storePickupLabel;
    private String storePickupQuote;
    private String storePickupQuote2_0;
    private String storePickupLinkText;
    private String storePickupProductTitle;
}
