package com.llu.cat.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.InspectEntity;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.entity.PhyExRegistration;
import com.llu.cat.entity.SubPhy;
import com.llu.cat.services.SubPhyService;
import com.llu.cat.services.admin.InspectService;
import com.llu.cat.services.subscribe.RegistrationService;
import com.llu.cat.tools.Query_Depart_Num_Name;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.*;

import javax.servlet.Registration;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @create 2022-03-25 20:40
 */
@Controller
public class PhyReportController {

    @Autowired
    SubPhyService subPhyService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    InspectService inspectService;

    @Autowired
    Query_Depart_Num_Name query_depart_num_name;

    @Autowired
    Phy_process phy_process;

    @GetMapping("/phy_report")
    public String phyReport(Map<String, String> map, HttpSession session, Model model){
        QueryWrapper<PhyExRegistration> phyExRegistrationQueryWrapper = new QueryWrapper<>();
        String phy_process_id = String.valueOf(session.getAttribute("phy_process_id"));
         /*
            查看是否已经输入要查询的信息，
                - 已经输入执行 if 里的语句， 否则 执行else里的语句
          */
        System.out.println(phy_process_id);
        if(phy_process.isPhy_processEmpty(session)){
            /**
             * 查询当前体检人员需要提交的部门
             */
            QueryWrapper<InspectEntity> inspectEntityQueryWrapper = new QueryWrapper<>();
            QueryWrapper<InspectEntity> inspectEntityQueryWrappertwo = new QueryWrapper<>();
            //查询该人员是否有误未提交项目
            inspectEntityQueryWrappertwo.eq("registration_id", phy_process_id).and(wrapper -> wrapper.eq("examine", 0));
            long count = inspectService.count(inspectEntityQueryWrappertwo);

            if(count != 0){
                session.removeAttribute("registration_id");
                return "/user/phy_report";

            }else {
/*
                    判断是否还有未检查的项目
                 */
                // 查询用户的体检数据
                inspectEntityQueryWrapper.eq("registration_id", phy_process_id);
                List<InspectEntity> inspectList = inspectService.list(inspectEntityQueryWrapper);
                // 获取科室号对应的科室信息
                HashMap<Long, PhyDepart> departMap = query_depart_num_name.query_num_name();
                // 往浏览器传值
                System.out.println("有数据");
                map.put("report_info2", "没有该用户,请先注册");

                // 获取要检查的用户数据
                QueryWrapper<PhyExRegistration> phyExRegistrationQueryWrapper1 = new QueryWrapper<>();
                phyExRegistrationQueryWrapper1.eq("registration_id", phy_process_id);
                PhyExRegistration one = registrationService.getOne(phyExRegistrationQueryWrapper1);
                HashMap<Long, JSONObject> integerJSONObjectHashMap = new HashMap<>();
                for(InspectEntity inspectEntity: inspectList){
                    JSONObject inspectJson = JSONObject.parseObject(inspectEntity.getData());
                    integerJSONObjectHashMap.put(inspectEntity.getDepartId(), inspectJson);
                }
                model.addAttribute("userinfo",one);
                model.addAttribute("departMap",departMap);
                model.addAttribute("inspectList", inspectList);
                model.addAttribute("integerJSONObjectHashMap", integerJSONObjectHashMap);
                return "/user/phy_report";

            }
        }else {
            session.removeAttribute("registration_id");
            return "/user/phy_report";
        }
    }


    @PostMapping("/phy_report_query")
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
                return "redirect:/phy_report";
            }else {
                map.put("msg_reservation","您还没有预约，请到预约体检页面进行预约");
                return "/user/phy_report";
            }
        }else {
            map.put("msg_reservation","请已经预约等待管理员确认");
            return "/user/phy_report";
        }

    }
}
