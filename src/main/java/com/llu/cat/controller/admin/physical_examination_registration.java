package com.llu.cat.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.InspectEntity;
import com.llu.cat.entity.PhyExRegistration;
import com.llu.cat.entity.SubPhy;
import com.llu.cat.entity.mapping.Set_Depart;
import com.llu.cat.mapper.InspectMapper;
import com.llu.cat.services.SubPhyService;
import com.llu.cat.services.admin.InspectService;
import com.llu.cat.services.subscribe.RegistrationService;
import com.llu.cat.services.system.SetDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @create 2022-03-14 16:44
 */

@Controller
public class physical_examination_registration {

    @Autowired
    SubPhyService subPhyService;

    @Autowired
    SetDepartService setDepartService;

    @Autowired
    InspectService inspectService;
    @Autowired
    RegistrationService registrationService;

    //预约登记功能
    @RequestMapping("/physical_examination_registration")
    public String physical_examination_registration(Model model){
        List<SubPhy> list = subPhyService.list();
        List<PhyExRegistration> list1 = registrationService.list();
        model.addAttribute("subPhyList",list);
        model.addAttribute("PhyRegistrationList",list1);
        return "/admin/physical_examination_registration";
    }

    //登记功能
    @RequestMapping("/registration/{id}")
    public String registration(Model model, @PathVariable("id") long id){
        SubPhy registrationUser = subPhyService.getById(id);

        //写入体检表的信息
        PhyExRegistration phyExRegistration = new PhyExRegistration();
        phyExRegistration.setPhyName((String)registrationUser.getPhyName());
        phyExRegistration.setRegistrationBirth(registrationUser.getSubPhyBirth());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = String.valueOf(df.format(new Date()));
        phyExRegistration.setRegistrationDate(date);
        //设置体检编号
        String[] split = date.split("-");
        String registrationId = "";
        for (String p :
                split) {
            registrationId = registrationId+p;
        }
        registrationId = registrationId+id;

        phyExRegistration.setRegistrationId(Integer.parseInt(registrationId));
        phyExRegistration.setRegistrationCert(registrationUser.getSubPhyCert());
        phyExRegistration.setRegistrationEmail(registrationUser.getSubPhyEmail());
        phyExRegistration.setRegistrationGender(registrationUser.getSubPhyGender());
        phyExRegistration.setPhyId(registrationUser.getPhyId());
        phyExRegistration.setRegistrationName(registrationUser.getSubPhyName());
        phyExRegistration.setRegistrationPhone(registrationUser.getSubPhyPhone());
        phyExRegistration.setSubPhyCheckDate(registrationUser.getSubPhyDate());
        boolean b = subPhyService.removeById(id);
        boolean save = registrationService.save(phyExRegistration);

        //查询体检,套餐用到的科室
        QueryWrapper<Set_Depart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phy_set_num", registrationUser.getPhyId());
        List<Set_Depart> list = setDepartService.list(queryWrapper);
        for (Set_Depart setDepart : list) {
            //各个科室需要体检的人员信息统计
            InspectEntity inspectEntity = new InspectEntity();
            inspectEntity.setDepartId(registrationUser.getPhyId());
            inspectEntity.setDepartId(setDepart.getDepartNum());
            inspectEntity.setExamine(0);
            inspectEntity.setSetId(registrationUser.getPhyId());
            inspectEntity.setRegistrationId(Integer.parseInt(registrationId));
            inspectService.save(inspectEntity);
        }
        return "redirect:/physical_examination_registration";
    }

    //取消登记功能
    @RequestMapping("/cancelRegistration/{id}")
    public String cancelRegistration(Model model, @PathVariable("id") long id){
        boolean b = registrationService.removeById(id);
        QueryWrapper<InspectEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("registration_id", id);
        boolean remove = inspectService.remove(queryWrapper);
        return "redirect:/physical_examination_registration";
    }
}
