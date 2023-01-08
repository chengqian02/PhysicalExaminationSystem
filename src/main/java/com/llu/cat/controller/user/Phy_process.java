package com.llu.cat.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.InspectEntity;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.entity.PhyExRegistration;
import com.llu.cat.entity.SubPhy;
import com.llu.cat.services.SubPhyService;
import com.llu.cat.services.admin.InspectService;
import com.llu.cat.services.subscribe.RegistrationService;
import com.llu.cat.tools.Query_Depart_Num_Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @create 2022-03-25 11:54
 */
@Controller
public class
Phy_process {


    @Autowired
    SubPhyService subPhyService;

    @Autowired
    Query_Depart_Num_Name query_depart_num_name;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    InspectService inspectService;

    @GetMapping("/phy_process")
    public String phy_process(Map<String, String> map, HttpSession session, Model model){
        QueryWrapper<PhyExRegistration> phyExRegistrationQueryWrapper = new QueryWrapper<>();
        String  phy_process_id = String.valueOf(session.getAttribute("phy_process_id"));
        if(isPhy_processEmpty(session)){
            /**
             * 查询当前体检人员需要提交的部门
             */
            QueryWrapper<InspectEntity> inspectEntityQueryWrapper = new QueryWrapper<>();
            QueryWrapper<InspectEntity> inspectEntityQueryWrappertwo = new QueryWrapper<>();
            //查询该人员是否有误未提交项目
            inspectEntityQueryWrappertwo.eq("registration_id", phy_process_id).and(wrapper -> wrapper.eq("examine", 0));
            long count = inspectService.count(inspectEntityQueryWrappertwo);
            if(count == 0)
                model.addAttribute("phy_count",count);

            inspectEntityQueryWrapper.eq("registration_id", phy_process_id);
            List<InspectEntity> inspectList = inspectService.list(inspectEntityQueryWrapper);

            // 获取科室号对应的科室信息
            HashMap<Long, PhyDepart> departMap = query_depart_num_name.query_num_name();
            // 往浏览器传值
            model.addAttribute("departMap",departMap);
            model.addAttribute("inspectList", inspectList);
            return "/user/physical_examination_process";
        }else {
            return "/user/physical_examination_process";
        }

    }



    public boolean isPhy_processEmpty(HttpSession session){
        String  phy_process_id = String.valueOf(session.getAttribute("phy_process_id"));
        if(!phy_process_id.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    @PostMapping("/phy_process_query")
    public  String  reservation_queryinfo(@RequestParam("subPhyCert") String subPhyCert,
                                          @RequestParam("subPhyDate") String subPhyDate,
                                          Map<String, String> map, HttpSession session, Model model){
        QueryWrapper<SubPhy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sub_phy_cert", subPhyCert).and(wrapper -> wrapper.eq("sub_phy_date", subPhyDate));
        long count = subPhyService.count(queryWrapper);
        if(count == 0){
            QueryWrapper<PhyExRegistration> phyExRegistrationQueryWrapper = new QueryWrapper<>();
            phyExRegistrationQueryWrapper.eq("registration_cert", subPhyCert).and(wrapper -> wrapper.eq("sub_phy_check_date", subPhyDate));
            long count1 = registrationService.count(phyExRegistrationQueryWrapper);
            if(count1 != 0 ){
                map.put("msg_reservation","您已经成功预约");
                PhyExRegistration one = registrationService.getOne(phyExRegistrationQueryWrapper, true);
                session.setAttribute("phy_process_id", one.getRegistrationId());
                return "redirect:/phy_process";
            }else {
                map.put("msg_reservation","您还没有预约，请到预约体检页面进行预约");
                return "/user/physical_examination_process";
            }
        }else {
            map.put("msg_reservation","请已经预约等待管理员确认");
            return "/user/physical_examination_process";
        }

    }

}
