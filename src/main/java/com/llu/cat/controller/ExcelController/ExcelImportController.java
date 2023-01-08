package com.llu.cat.controller.ExcelController;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.llu.cat.component.DepartListener;
import com.llu.cat.component.ItemListener;
import com.llu.cat.component.SetListener;
import com.llu.cat.component.UserListener;
import com.llu.cat.entity.CheckItem;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.entity.PhySet;
import com.llu.cat.entity.SignUser;
import com.llu.cat.entity.mapping.Set_Depart;
import com.llu.cat.services.system.PhyDepartService;
import com.llu.cat.services.system.PhySetService;
import com.llu.cat.services.system.SetDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiListUI;
import java.io.IOException;
import java.util.List;

/**
 * @create 2022-04-03 22:01
 */
@Controller
public class ExcelImportController {

    @Autowired
    UserListener userListener;

    @Autowired
    DepartListener departListener;

    @Autowired
    ItemListener itemListener;

    @Autowired
    PhySetService phySetService;

    @Autowired
    SetDepartService setDepartService;
    @Autowired
    SetListener setListener;
    @RequestMapping(value = "/importUserinfo", method = RequestMethod.POST)
    public String importUserinfo(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        ExcelReaderBuilder readWorkBook = EasyExcel.read(multipartFile.getInputStream(), SignUser.class, userListener);
        readWorkBook.sheet().doRead();
        return "redirect:/user_manage";
    }

    @RequestMapping(value = "/importDepartinfo", method = RequestMethod.POST)
    public String importDepartinfo(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        ExcelReaderBuilder readWorkBook = EasyExcel.read(multipartFile.getInputStream(), PhyDepart.class, departListener);
        readWorkBook.sheet().doRead();
        return "redirect:/phy_depart";
    }

    @RequestMapping(value = "/importIteminfo", method = RequestMethod.POST)
    public String importIteminfo(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        ExcelReaderBuilder readWorkBook = EasyExcel.read(multipartFile.getInputStream(), CheckItem.class, itemListener);
        readWorkBook.sheet().doRead();
        return "redirect:/checkitemList";
    }

    @RequestMapping(value = "/importSetinfo", method = RequestMethod.POST)
    public String importSetinfo(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        ExcelReaderBuilder   readWorkBook2 = EasyExcel.read(multipartFile.getInputStream(), CheckItem.class, itemListener);
        ExcelReader readWorkBook = EasyExcel.read(multipartFile.getInputStream(),setListener).build();
        ReadSheet sheet1 = EasyExcel.readSheet(0).head(PhySet.class).build();
        ReadSheet sheet2 = EasyExcel.readSheet(1).head(Set_Depart.class).build();
        readWorkBook.read(sheet1);
        List<Object> datas = setListener.getDatas();
        for (Object setInfo:
        datas) {
            phySetService.save((PhySet) setInfo);
        }
        setListener.getDatas().clear();
        // 开始读取第二个sheet
        readWorkBook.read(sheet2);
        List<Object> entry = setListener.getDatas();
        for (Object setInfo:
                entry) {
            setDepartService.save((Set_Depart) setInfo);
        }
        return "redirect:/checkitemList";
    }
}
