package com.llu.cat.controller.subscribe;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.PhySet;
import com.llu.cat.entity.SubPhy;
import com.llu.cat.entity.mapping.Set_Depart;
import com.llu.cat.services.system.PhySetService;
import com.llu.cat.services.SubPhyService;
import com.llu.cat.services.system.SetDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @create 2022-03-07 8:27
 */
@Controller
public class physical_examination {

    @Autowired
    PhySetService phySetService;
    @Autowired
    SubPhyService subPhyService;

    @Autowired
    SetDepartService setDepartService;

    @GetMapping("/physical_examination_all")
    public String physical__examination(Model model){
        List<PhySet> list = phySetService.list();
        model.addAttribute("list",list);
        return "/user/physical _examination_all";
    }

    @GetMapping("/phy_profile/{id}")
    public String phy_profile(@PathVariable("id") long id, HttpSession session,
                              Model model){
        PhySet user = phySetService.getById(id);
        QueryWrapper<Set_Depart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phy_set_num", id);
        List<Set_Depart> list = setDepartService.list(queryWrapper);
        System.out.println(list);
        long count = setDepartService.count(queryWrapper);
        model.addAttribute("user",user);
        model.addAttribute("list",list);
        model.addAttribute("count",count);
        return "/user/phy_profile";
    }

    @GetMapping("/physical_examination/{id}")
    public String physical_examination(@PathVariable("id") long id, HttpSession session,
                              Model model){
        PhySet user = phySetService.getById(id);
        model.addAttribute("user",user);
        return "/user/physical_examination";
    }

    @PostMapping("/physical_examination_add")
    public String physical_examination_add(SubPhy subPhy, HttpSession session, Model model) {
        QueryWrapper<SubPhy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sub_phy_cert", subPhy.getSubPhyCert());
        long count = subPhyService.count(queryWrapper);
        if (count == 0) {
            boolean addphysave = subPhyService.save(subPhy);
            if (addphysave) {
                session.setAttribute("add_phy_info", "已经提交预约申请，等待管理员确认");
                PhySet user = phySetService.getById(subPhy.getPhyId());
                model.addAttribute("user",user);
                return "/user/physical_examination";
            } else {
                session.setAttribute("add_phy_info", "预约失败,请重新预约");
                PhySet user = phySetService.getById(subPhy.getPhyId());
                model.addAttribute("user",user);
                return "/user/physical_examination";

            }
        }else {
            session.setAttribute("add_phy_info", "已经提交预约申请，请勿重复预约");
            PhySet user = phySetService.getById(subPhy.getPhyId());
            model.addAttribute("user",user);
            return "/user/physical_examination";
        }
    }
}
