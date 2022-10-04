package com.github.jkfaner.domain.bo.autocomplete;

import lombok.Data;

import java.util.List;

/**
 * @author: LiamLee
 * @since: 2022-10-02
 **/
@Data
public class StateBo {
    private String name;
    private List<StoreItemBo> store;
}
