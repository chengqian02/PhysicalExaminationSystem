package com.llu.cat.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @create 2022-03-07 20:49
 */
@Data
@TableName("phy_set")
public class PhySet {
    @TableId(value = "phy_id" ,type = IdType.INPUT)
    @ExcelProperty("套餐编号")
    @ColumnWidth(20)
    private long phyId;
    @ExcelProperty("套餐名")
    @ColumnWidth(20)
    private String phyName;
    @ExcelProperty("检查类型数量")
    @ColumnWidth(20)
    private int phyCheckNum;
    @ExcelProperty("套餐类型1")
    @ColumnWidth(20)
    private String phyCheckTypeOne;
    @ExcelProperty("套餐类型2")
    @ColumnWidth(20)
    private String phyCheckTypeTwo;
    @ExcelProperty("套餐类型3")
    @ColumnWidth(20)
    private String phyCheckTypeThree;
    @ExcelProperty("套餐类型4")
    @ColumnWidth(20)
    private String phyCheckTypeFour;
    @ExcelProperty("套餐类型5")
    @ColumnWidth(20)
    private String phyCheckTypeFive;
    @ExcelProperty("描述")
    @ColumnWidth(20)
    private String phyCheckRemark;
    @ExcelProperty("价格")
    @ColumnWidth(20)
    private String phyPrice;
}
