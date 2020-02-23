package com.boot.zysf.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result{
    private Location location;
    private Integer precise;
    private Integer confidence;
    private Integer comprehension;
    private String level;
}
