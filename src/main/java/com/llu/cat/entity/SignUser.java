package com.llu.cat.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.Key;

/**
 * @create 2022-03-06 15:06
 */
@Data
@TableName("sign_user")
public class SignUser
{
    @TableId(value = "stu_id", type = IdType.INPUT)
    @ExcelIgnore
    private long stuId;
    @ExcelProperty("学号")
    @ColumnWidth(20)
    private Long stuNum;
    @ExcelProperty("姓名")
    @ColumnWidth(20)
    private String stuName;
    @ExcelProperty("性别")
    @ColumnWidth(20)
    private String gender;
    @ExcelProperty("联系方式")
    @ColumnWidth(30)
    private Long phone;
    @ExcelProperty("密码")
    @ColumnWidth(20)
    private String password;
    @ExcelProperty("邮箱")
    @ColumnWidth(20)
    private String email;
    @ExcelProperty("系别")
    @ColumnWidth(20)
    private String depart;
    @ExcelProperty("专业")
    @ColumnWidth(20)
    private String major;
    @ExcelProperty("班级")
    @ColumnWidth(20)
    private String classes;
    @ExcelProperty("出生日期")
    @ColumnWidth(20)
    private String entranceDate;
}
