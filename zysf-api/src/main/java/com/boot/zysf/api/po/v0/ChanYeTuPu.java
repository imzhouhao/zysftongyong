package com.boot.zysf.api.po.v0;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class ChanYeTuPu {

    //行业id
    private String id;

    //行业名称
    private String hangName;

    //产业map  id--name
    private Map<String,String> chanYe;

    public ChanYeTuPu(String id, String hangName, Map<String, String> chanYe) {
        this.id = id;
        this.hangName = hangName;
        this.chanYe = chanYe;
    }
}
