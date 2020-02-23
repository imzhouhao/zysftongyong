package com.boot.zysf.api.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class YearData {
    private Integer year;
    private  Double income;
    private  Double profit;

    public YearData() {
    }
}
