package com.github.jkfaner.domain.bo.autocomplete;

import lombok.Data;

import java.util.List;

/**
 * @author: LiamLee
 * @since: 2022-09-28
 **/
@Data
public class StoreDataBo {
    private String locale;
    private Boolean hasStates;// 是否有州
    private List<StateBo> state; // hasStates=true
    private List<StoreItemBo> store; // hasStates=false
}
