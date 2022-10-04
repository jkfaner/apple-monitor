package com.github.jkfaner.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: LiamLee
 * @since: 2022-09-27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String state;
    private String city;
    private String district;
}
