package com.llu.cat.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @create 2022-03-10 22:51
 */
@Data
@TableName("depart_item")
public class CheckItem {
    @TableId(type = IdType.INPUT)
    @ExcelIgnore
    private long itemId;
    @ExcelProperty("项目编号")
    @ColumnWidth(20)
    private long itemNum;
    @ExcelProperty("项目名字")
    @ColumnWidth(20)
    private String itemName;
    @ExcelProperty("简称")
    @ColumnWidth(20)
    private String itemSimp;
    @ExcelProperty("所属科室编号")
    @ColumnWidth(20)
    private long departNum;
    @ExcelProperty("所属科室")
    @ColumnWidth(20)
    private String departName;
    @ExcelProperty("项目描述(可以为空)")
    @ColumnWidth(30)
    private String itemRemark;
}
