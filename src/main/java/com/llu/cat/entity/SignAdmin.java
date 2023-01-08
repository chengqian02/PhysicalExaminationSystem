package com.llu.cat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @create 2022-03-08 19:29
 */
@Data
@TableName("sign_admin")
public class SignAdmin {

    @TableId(type = IdType.INPUT)
    private long adminId;
    private Long adminNum;
    private String adminName;
    private String gender;
    private Long phone;
    private String password;
    private String email;
    private String depart;
    private String major;
    private String classes;
    private String entranceDate;
}
