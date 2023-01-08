package com.llu.cat.controller.ExcelController;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.llu.cat.entity.CheckItem;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.entity.PhySet;
import com.llu.cat.entity.SignUser;
import com.llu.cat.entity.mapping.Set_Depart;
import lombok.SneakyThrows;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @create 2022-04-02 23:03
 */
@Controller
public class ExcelExportController {

    @RequestMapping("/exportUser")
    @ResponseBody
    public void exportUser(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String filename = URLEncoder.encode("用户信息模板","UTF-8");
        response.setHeader("Content-Disposition","attachment; filename*=UTF-8''"+filename+".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriterBuilder writerWorkBook = EasyExcel.write(outputStream, SignUser.class);
        ExcelWriterSheetBuilder sheet = writerWorkBook.sheet();
        ArrayList<SignUser> list = new ArrayList<>();
        sheet.doWrite(list);
    }

    @RequestMapping("/exportTeam")
    @ResponseBody
    public void exportTeam(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String filename = URLEncoder.encode("科室信息模板","UTF-8");
        response.setHeader("Content-Disposition","attachment; filename*=UTF-8''"+filename+".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriterBuilder writerWorkBook = EasyExcel.write(outputStream, PhyDepart.class);
        ExcelWriterSheetBuilder sheet = writerWorkBook.sheet();
        ArrayList<PhyDepart> list = new ArrayList<>();
        sheet.doWrite(list);
    }
    @RequestMapping("/exportSet")
    @ResponseBody
    public void exportSet(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String filename = URLEncoder.encode("体检套餐信息模板","UTF-8");
        response.setHeader("Content-Disposition","attachment; filename*=UTF-8''"+filename+".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
        ArrayList<PhySet> list = new ArrayList<>();
        ArrayList<Set_Depart> list2 = new ArrayList<>();
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "套餐基础信息").head(PhySet.class).build();
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "套餐映射表").head(Set_Depart.class).build();
        excelWriter.write(list, sheet1);
        excelWriter.write(list2, sheet2);
        excelWriter.finish();
    }
    @RequestMapping("/exportItem")
    @ResponseBody
    public void exportitem(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String filename = URLEncoder.encode("体检项目信息模板","UTF-8");
        response.setHeader("Content-Disposition","attachment; filename*=UTF-8''"+filename+".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriterBuilder writerWorkBook = EasyExcel.write(outputStream, CheckItem.class);
        ExcelWriterSheetBuilder sheet = writerWorkBook.sheet();
        ArrayList<CheckItem> list = new ArrayList<>();
        sheet.doWrite(list);
    }
}
