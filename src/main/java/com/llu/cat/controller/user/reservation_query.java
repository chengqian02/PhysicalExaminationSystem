package com.llu.cat.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.PhyExRegistration;
import com.llu.cat.entity.SubPhy;
import com.llu.cat.services.SubPhyService;
import com.llu.cat.services.admin.InspectService;
import com.llu.cat.services.subscribe.RegistrationService;
import com.llu.cat.tools.Query_Depart_Num_Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @create 2022-03-13 11:41
 */
@Controller
public class reservation_query {


    @Autowired
    SubPhyService subPhyService;

    @Autowired
    Query_Depart_Num_Name query_depart_num_name;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    InspectService inspectService;

    @RequestMapping("/reservation_query")
    public  String  reservation_query(){
        return "/user/reservation_query/reservation_query";
    }

    @RequestMapping("/reservation_queryinfo")
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
                PhyExRegistration one = registrationService.getOne(phyExRegistrationQueryWrapper, true);
                session.setAttribute("phy_process_id", one.getRegistrationId());
                map.put("msg_reservation","您已经成功预约");
                model.addAttribute("msg_process",1);
                return "/user/reservation_query/reservation_query";
            }else {
                session.removeAttribute("phy_process_id");
                map.put("msg_reservation","您还没有预约，请到预约体检页面进行预约");
                return "/user/reservation_query/reservation_query";
            }
        }else {
            session.removeAttribute("phy_process_id");
            map.put("msg_reservation","请已经预约等待管理员确认");
            return "/user/reservation_query/reservation_query";
        }
    }
}
