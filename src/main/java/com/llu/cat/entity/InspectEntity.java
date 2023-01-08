package com.llu.cat.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @create 2022-03-16 11:55
 */
@Data
@TableName("inspect__table")
public class InspectEntity {
    @TableId(type = IdType.INPUT)
    private long inspectId;
    private long registrationId;
    private long setId;
    private long departId;
    private long examine;
    private String inspectDate;
    private String data;
    private long num;
}
