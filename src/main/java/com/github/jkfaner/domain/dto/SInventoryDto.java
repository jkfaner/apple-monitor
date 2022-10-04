package com.github.jkfaner.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 含附近库存查询对象
 * @author: LiamLee
 * @since: 2022-09-28
 **/
@Data
public class InventoryDto {
    /**
     * 地区
     */
    private String location;
    /**
     * 型号列表
     */
    private List<String> modelList;
}
