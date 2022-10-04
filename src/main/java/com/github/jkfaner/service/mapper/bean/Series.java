package com.github.jkfaner.mapper.bean;

import lombok.Data;

import java.util.List;

/**
 * @author: LiamLee
 * @since: 2022-10-03
 **/
@Data
public class Series {
    private String label;
    private String value;
    private String name;
    private List<Model> models;
}
