package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by apple on 2017/10/21.
 */
@Setter
@Getter
public class Client extends BaseDomain{
    private String name;
    private String sn;
    private String phone;
}
