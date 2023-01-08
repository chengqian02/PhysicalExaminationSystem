package com.llu.cat.controller.admin;

import com.llu.cat.entity.SignAdmin;
import com.llu.cat.entity.SignUser;
import com.llu.cat.services.SignAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @create 2022-03-09 22:20
 */
@Controller
public class admin_manager {

    @Autowired
    SignAdminService signAdminService;

    @RequestMapping("/addAdminpage")
    public String addAdminpage(){
        return "/admin/topPage/admin_add";
    }

    @RequestMapping("/addAdmin")
    public String addAdmin(SignAdmin signAdmin, Map<String, Object> map){
        boolean save = signAdminService.save(signAdmin);
        if (save){
            return "redirect:/main_admin.html";
        }else {
            map.put("add_admin_msg","添加失败，请重新添加");
            return "/admin/topPage/admin_add";
        }
    }
}
