package com.llu.cat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @create 2022-03-08 12:07
 */
@Data
@TableName("phy_ex_registration")
public class PhyExRegistration {

    @TableId(type = IdType.INPUT)
    private long registrationId;
    private long phyId;
    private String phyName;
    private String registrationName;
    private String registrationPhone;
    private String registrationGender;
    private String registrationBirth;
    private String subPhyCheckDate;
    private String registrationDate;
    private String inspectDate;
    private String registrationCert;
    private String registrationEmail;
}
