package com.llu.cat.controller.systman;

import com.llu.cat.entity.SignAdmin;
import com.llu.cat.entity.SignUser;
import com.llu.cat.services.SignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @create 2022-03-10 8:18
 */
@Controller
public class User_person_coreController {

    @Autowired
    SignUserService signUserService;

    @RequestMapping("/user_person_core")
    public String person_core(HttpSession session, Model model){
        Long userId  = (Long)session.getAttribute("loginUserId");
        SignUser Login_user = signUserService.getById(userId);
        model.addAttribute("Login_user", Login_user);
        return "/user/topPage/user_profile";
    }
    @RequestMapping("/docker_person_core")
    public String docker_person_core(HttpSession session, Model model){
        Long userId  = (Long)session.getAttribute("loginUserId");
        SignUser Login_user = signUserService.getById(userId);
        model.addAttribute("Login_user", Login_user);
        return "/user/topPage/docker_profile";
    }

}
