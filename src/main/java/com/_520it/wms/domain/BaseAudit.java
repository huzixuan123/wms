package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 抽取单据审核相关信息
 */
@Setter
@Getter
public class BaseAudit extends BaseDomain{
    public static final int STATE_NOMAL = 0;
    public static final int STATE_AUDIT = 1;
    private int status=STATE_NOMAL; //审核状态
    private Date auditTime;//审核时间
    private Date inputTime;//录入时间
    private Employee inputUser;//制单人
    private Employee auditor;//审核人

    public String getDisplayStatus(){
        return this.status==STATE_NOMAL?"待审核":"已审核";
    }
}
