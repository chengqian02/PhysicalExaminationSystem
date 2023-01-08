package com.llu.cat.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * @create 2022-03-09 12:10
 */
@Data
@TableName("phy_depart")
public class PhyDepart {
    @TableId(type = IdType.INPUT)
    @ExcelIgnore
    private long departId;
    @ExcelProperty("科室编号")
    @ColumnWidth(20)
    private long departNum;
    @ExcelProperty("科室名字")
    @ColumnWidth(20)
    private String departName;
    @ExcelProperty("简码")
    @ColumnWidth(20)
    private String departSimp;
    @ExcelProperty("类型")
    @ColumnWidth(20)
    private String departType;
    @ExcelProperty("科室说明")
    @ColumnWidth(20)
    private String departRemark;
}
