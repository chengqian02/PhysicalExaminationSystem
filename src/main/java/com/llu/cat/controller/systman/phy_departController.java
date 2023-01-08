package com.llu.cat.controller.systman;

import com.llu.cat.entity.PhyDepart;
import com.llu.cat.services.system.PhyDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @create 2022-03-09 12:09
 */
@Controller
public class phy_departController {


    @Autowired
    PhyDepartService phyDpartService;

    @RequestMapping("/phy_depart")
    public String departList(Model model){
        List<PhyDepart> list = phyDpartService.list();
        model.addAttribute("depart_list", list);
        return "/admin/system_manage/phy_depart";
    }

    @RequestMapping("/depInfo/modify/{id}")
    public String depInfo_modify(@PathVariable("id") long id,Model model){
        PhyDepart depart = phyDpartService.getById(id);
        model.addAttribute("depart",depart);
        return "/admin/system_manage/depInfo_modify";
    }

    @RequestMapping("/depInfo_modify")
    public String depInfomodify(PhyDepart phyDepart){
        phyDpartService.updateById(phyDepart);
        return "redirect:/phy_depart";
    }
    @GetMapping("/depInfo/delete/{id}")
    public String deleteUers(@PathVariable("id") long id){
        phyDpartService.removeById(id);
        return "redirect:/phy_depart";
    }

    @PostMapping("/departInfoAdd")
    public  String DepartinfoAdd(PhyDepart phyDepart){
        phyDpartService.saveOrUpdate(phyDepart);
        return "redirect:/phy_depart";
    }

}
