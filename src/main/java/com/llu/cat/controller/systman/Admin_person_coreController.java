package com.llu.cat.controller.systman;

import com.llu.cat.entity.SignAdmin;
import com.llu.cat.services.SignAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @create 2022-03-10 8:18
 */
@Controller
public class Admin_person_coreController {

    @Autowired
    SignAdminService signAdminService;

    @RequestMapping("admin_person_core")
    private String person_core(HttpSession session, Model model){
        Long adminId  = (Long)session.getAttribute("loginUserId");
        SignAdmin Login_admin = signAdminService.getById(adminId);
        model.addAttribute("Login_admin", Login_admin);
        return "/admin/topPage/admin_profile";
    }
}
