package com.github.jkfaner.domain.bo.autocomplete;

import lombok.Data;


/**
 * @author: LiamLee
 * @since: 2022-10-02
 **/
@Data
public class StoreItemBo {
    private String name;
    private String slug;
    private String storeNumber;
    private Geolocation geolocation;
}
