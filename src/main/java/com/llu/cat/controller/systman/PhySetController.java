package com.llu.cat.controller.systman;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.entity.PhySet;
import com.llu.cat.entity.mapping.Set_Depart;
import com.llu.cat.services.system.PhyDepartService;
import com.llu.cat.services.system.PhySetService;
import com.llu.cat.services.system.SetDepartService;
import com.llu.cat.tools.Query_Depart_Num_Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @create 2022-03-11 8:18
 */
@Controller
public class PhySetController {

    @Autowired
    PhySetService phySetService;

    @Autowired
    SetDepartService setDepartService;

    @Autowired
    PhyDepartService phyDepartService;

    @Autowired
    Query_Depart_Num_Name query_depart_num_name;
    @GetMapping("/phySetList")
    public String phySetList(Model model){
        List<PhySet> phySetList = phySetService.list();
        HashMap<Long, Long> checkNum = new HashMap<Long, Long>();
        HashMap<Long, List<Set_Depart>> checkName = new HashMap<Long, List<Set_Depart>>();
        for(PhySet List: phySetList) {
            QueryWrapper<Set_Depart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phy_set_num", List.getPhyId());
            Long count = setDepartService.count(queryWrapper);
            checkNum.put(List.getPhyId(),count);
            java.util.List<Set_Depart> list = setDepartService.list(queryWrapper);
            checkName.put(List.getPhyId(),list);
        }
        List<PhyDepart> DepartList = phyDepartService.list();
        model.addAttribute("DepartList",DepartList);
        model.addAttribute("phySetList",phySetList);
        model.addAttribute("checkNum",checkNum);
        model.addAttribute("checkName",checkName);

        return "/admin/system_manage/physetList";
    }

    @GetMapping("/set/modify/{id}")
    public String setModifyId(@PathVariable("id") long id,Model model, Map<String,Object> map){
        QueryWrapper<Set_Depart> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("phy_set_num", id);
        ArrayList<Long> departIdList = new ArrayList<>();
        List<Set_Depart> departList = setDepartService.list(updateWrapper);

        for (Set_Depart setdepart: departList
        ) {
            departIdList.add(setdepart.getDepartNum());
        }
        model.addAttribute("departIdList",departIdList);
        List<PhyDepart> DepartList = phyDepartService.list();
        model.addAttribute("DepartList",DepartList);
        PhySet updateSet = phySetService.getById(id);
        model.addAttribute("updateSet",updateSet);
        return "/admin/system_manage/SetInfo_modify";
    }
    @PostMapping("/setModify")
    public String setModifyId(PhySet phySet, HttpServletRequest request, HttpServletResponse response){
        boolean update = phySetService.saveOrUpdate(phySet);
        QueryWrapper<Set_Depart> updateWrapper = new QueryWrapper<>();
        QueryWrapper<Set_Depart> removeWrapper = new QueryWrapper<>();
        removeWrapper.eq("phy_set_num", phySet.getPhyId());
        long count = setDepartService.count(removeWrapper);
        if(count!=0){
            boolean remove = setDepartService.remove(removeWrapper);
        }
        String[] phyItem = request.getParameterValues("phyItem");
        HashMap<Long, PhyDepart> longPhyDepartHashMap = query_depart_num_name.query_num_name();

        for (String phyId :
                phyItem) {
            Set_Depart set_depart = new Set_Depart();
            set_depart.setPhySetNum(phySet.getPhyId());
            set_depart.setPhySetName(phySet.getPhyName());
            set_depart.setDepartNum(Long.parseLong(phyId));
            set_depart.setDepartName(longPhyDepartHashMap.get(Long.parseLong(phyId)).getDepartName());
            setDepartService.save(set_depart);
        }
        return "redirect:/phySetList";
    }


    @GetMapping("/set/delete/{id}")
    public String setDeleteIid(@PathVariable("id") long id){
        QueryWrapper<Set_Depart> removeWrapper = new QueryWrapper<>();
        removeWrapper.eq("phy_set_num", id);
        long count = setDepartService.count(removeWrapper);
        if(count!=0){
            boolean remove = setDepartService.remove(removeWrapper);
        }
        phySetService.removeById(id);
        return "redirect:/phySetList";
    }

    @PostMapping("/setInfoAdd")
    public String setInfoAdd(PhySet phySet, HttpServletRequest request, HttpServletResponse response){
        boolean update = phySetService.saveOrUpdate(phySet);
        String[] phyItem = request.getParameterValues("phyItem");
        HashMap<Long, PhyDepart> longPhyDepartHashMap = query_depart_num_name.query_num_name();
        for (String phyId :
                phyItem) {
            Set_Depart set_depart = new Set_Depart();
            set_depart.setPhySetNum(phySet.getPhyId());
            set_depart.setPhySetName(phySet.getPhyName());
            set_depart.setDepartNum(Long.parseLong(phyId));
            set_depart.setDepartName(longPhyDepartHashMap.get(Long.parseLong(phyId)).getDepartName());
            setDepartService.save(set_depart);
            System.out.println(phyId);
        }
        return "redirect:/phySetList";
    }
}
