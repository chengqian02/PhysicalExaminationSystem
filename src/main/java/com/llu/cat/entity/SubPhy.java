package com.llu.cat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @create 2022-03-08 12:07
 */
@Data
@TableName("sub_phy")
public class SubPhy {

    @TableId(type = IdType.INPUT)
    private long subPhyId;
    private long phyId;
    private String phyName;
    private String subPhyName;
    private String subPhyPhone;
    private String subPhyGender;
    private String subPhyBirth;
    private String subPhyDate;
    private String subPhyCert;
    private String subPhyEmail;
}
