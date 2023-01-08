package com.llu.cat.entity.mapping;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @create 2022-03-11 8:31
 */
@Data
@TableName("set_depart_mapping")
public class Set_Depart {
    @TableId(type = IdType.INPUT)
    @ExcelIgnore
    private long setDepartMappingId;
    @ExcelProperty("套餐编号")
    @ColumnWidth(20)
    private long phySetNum;
    @ExcelProperty("套餐名")
    @ColumnWidth(20)
    private String phySetName;
    @ExcelProperty("科室编号")
    @ColumnWidth(20)
    private long departNum;
    @ExcelProperty("科室名")
    @ColumnWidth(20)
    private String departName;
}
