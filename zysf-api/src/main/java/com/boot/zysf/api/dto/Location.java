package com.boot.zysf.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Location{
    private Double lng;
    private Double lat;
}
