package com.github.jkfaner.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 指定门店库存查询对象
 * @author: LiamLee
 * @since: 2022-09-28
 **/
@Data
public class PreciseInventoryDto {
    /**
     * 零售店
     */
    private String store;
    /**
     * 型号列表
     */
    private List<String> modelList;
}
