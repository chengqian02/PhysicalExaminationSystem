package com.llu.cat.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.CheckItem;
import com.llu.cat.entity.InspectEntity;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.entity.PhyExRegistration;
import com.llu.cat.services.admin.InspectService;
import com.llu.cat.services.subscribe.RegistrationService;
import com.llu.cat.services.system.CheckItemService;
import com.llu.cat.services.system.PhyDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @create 2022-03-16 11:54
 */
@Controller
public class inspectController {

    @Autowired
    PhyDepartService phyDepartService;

    @Autowired
    InspectService inspectService;

    @Autowired
    CheckItemService checkItemService;

    @Autowired
    RegistrationService registrationService;

    @RequestMapping("/division_inspect")
    public String division_inspect(Model model, @RequestParam(value = "departNum", defaultValue = "0") Long departNum){
        //获取所有科室列表
        String departName = null;
        List<PhyDepart> departlist = phyDepartService.list();
        if(0 == departNum){
            departNum = departlist.get(0).getDepartNum();
        }
        QueryWrapper<PhyDepart> departQueryWrapper = new QueryWrapper<>();
        departQueryWrapper.eq("depart_num",departNum);
        departName = phyDepartService.getOne(departQueryWrapper).getDepartName();

        //获取当前科室未检查的用户
        QueryWrapper<InspectEntity> inspectWrapperone = new QueryWrapper<>();
        inspectWrapperone.eq("depart_id", departNum).and(wapper -> wapper.eq("examine",0));
        List<InspectEntity> currentUseridListOne = inspectService.list(inspectWrapperone);
        ArrayList<PhyExRegistration> currentUserListOne = new ArrayList<>();
        for (InspectEntity inspect :
                currentUseridListOne) {
            PhyExRegistration byId = registrationService.getById(inspect.getRegistrationId());
            currentUserListOne.add(byId);
        }

        //获取当前科室已经检查的用户
        QueryWrapper<InspectEntity> inspectWrapperTwo = new QueryWrapper<>();
        inspectWrapperTwo.eq("depart_id", departNum).and(wapper -> wapper.eq("examine",1));
        List<InspectEntity> currentUseridListTwo = inspectService.list(inspectWrapperTwo);
        ArrayList<PhyExRegistration> currentUserListTwo = new ArrayList<>();
        for (InspectEntity inspect :
                currentUseridListTwo) {
            PhyExRegistration byId = registrationService.getById(inspect.getRegistrationId());
            currentUserListTwo.add(byId);
        }

        //获取当前科室需要检查的项目列表
        QueryWrapper<CheckItem> checkItemQueryWrapper = new QueryWrapper<>();
        checkItemQueryWrapper.eq("depart_num", departNum);
        List<CheckItem> itemList = checkItemService.list(checkItemQueryWrapper);

        //往页面传值

        model.addAttribute("currentUserListOne",currentUserListOne);
        model.addAttribute("currentUserListTwo",currentUserListTwo);
        model.addAttribute("departList",departlist);
        model.addAttribute("itemList",itemList);
        model.addAttribute("departName",departName);
        model.addAttribute("departNum",departNum);

        return "/admin/division_inspect";
    }

    @RequestMapping(value = "/inputInfo")
    public  String inputInfo(@RequestParam("id") String registrationdate, HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        //获取体检编号和部门id
        long registrationId = Integer.parseInt(registrationdate.split(",")[0]);
        long departId = Integer.parseInt(registrationdate.split(",")[1]);

        //form表单保存为json对象
        QueryWrapper<CheckItem> checkItemQueryWrapper = new QueryWrapper<>();
        checkItemQueryWrapper.eq("depart_num", departId);
        List<CheckItem> checkItems = checkItemService.list(checkItemQueryWrapper);
        int i = 0;
        for (CheckItem item: checkItems) {
            object.put(item.getItemName(), request.getParameter(item.getItemName()));
            i += 1;
        }
        QueryWrapper<InspectEntity> inspectquery = new QueryWrapper<>();
        inspectquery.eq("depart_id", departId).and(wapper -> wapper.eq("registration_id",registrationId));

        //保存提交数据，并修改转态为已体检
        InspectEntity inspect = inspectService.getOne(inspectquery);
        inspect.setExamine(1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = String.valueOf(df.format(new Date()));
        inspect.setInspectDate(date);
        inspect.setData(String.valueOf(object));
        inspect.setNum(i);
        boolean update = inspectService.updateById(inspect);


        return "redirect:/division_inspect";
    }

    @GetMapping(value = "/detailInfo")
    public  String detailInfo(@RequestParam("departNum") long departNum, @RequestParam("registrationId") long registrationId,
                            Model model) {
        QueryWrapper<InspectEntity> inspectquery = new QueryWrapper<>();
        inspectquery.eq("depart_id", departNum).and(wapper -> wapper.eq("registration_id",registrationId));
        InspectEntity inspect = inspectService.getOne(inspectquery);
        JSONObject inspectJson = JSONObject.parseObject(inspect.getData());
        model.addAttribute("inspect", inspect);
        model.addAttribute("inspectJson", inspectJson);
        return "/admin/inspectInfo";
    }
}
