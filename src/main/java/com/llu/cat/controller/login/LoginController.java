package com.llu.cat.controller.login;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.SignAdmin;
import com.llu.cat.entity.SignUser;
import com.llu.cat.services.SignAdminService;
import com.llu.cat.services.SignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.PrintStream;
import java.util.Map;

/**
 * @create 2022-02-25 21:33
 */
@Controller
public class LoginController {

    @Autowired
    SignUserService signUserService;

    @Autowired
    SignAdminService signAdminService;
    @PostMapping(value = "/user_login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("btn") int btn,
                        Map<String, Object> map, HttpSession session){
        if(btn == 1) {
            QueryWrapper<SignUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone",username);
            long count = signUserService.count(queryWrapper);
            if (count != 0) {
                SignUser user = signUserService.getOne(queryWrapper);
                if (user.getPhone() == Long.parseLong(username) && user.getPassword().equals(password)) {
                    session.setAttribute("loginUser", user.getStuName());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("phone", user.getPhone());
                    session.setAttribute("password", user.getPassword());
                    session.setAttribute("loginUserId", user.getStuId());
                    session.setAttribute("authority", 1);
                    return "redirect:/main_user.html";
                } else {
                    map.put("msg", "用户密码错误,重新登录");
                    return "login";
                }
            } else {
                map.put("msg", "没有该用户,请先注册");
                return "login";
            }
        }else {
            QueryWrapper<SignAdmin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone",username);
            long count = signAdminService.count(queryWrapper);
            if (count != 0) {
                SignAdmin admin = signAdminService.getOne(queryWrapper);
                if (admin.getPhone() == Long.parseLong(username) && admin.getPassword().equals(password)) {
                    session.setAttribute("loginUser", admin.getAdminName());
                    session.setAttribute("email", admin.getEmail());
                    session.setAttribute("phone", admin.getPhone());
                    session.setAttribute("password", admin.getPassword());
                    session.setAttribute("loginUserId", admin.getAdminId());
                    session.setAttribute("authority", 0);
                    return "redirect:/main_admin.html";
                } else {
                    map.put("msg", "用户密码错误,重新登录");
                    return "login";
                }
            } else {
                map.put("msg", "没有该用户,请先注册");
                return "login";
            }
        }
    }

    //用户注册
    @PostMapping("/user_register")
    public String user_register(SignUser signUser, HttpSession session,Map<String, Object> map){
        System.out.println(signUser.getStuName());
        boolean save = signUserService.save(signUser);
        if(save){
            map.put("msg", "注册成功, 请登录！");
            return "login";
        }else {
            map.put("msg", "注 册 失 败！");
            return "login";
        }
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    //修改密码
    @PostMapping("/modifyPassword")
    public String modifyPassword(HttpSession session,Map<String, Object> map,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword){
        if((int)session.getAttribute("authority")==0){
            Long loginUserId = (Long)session.getAttribute("loginUserId");
            SignAdmin signuser = signAdminService.getById(loginUserId);
            if(signuser.getPassword().equals(oldPassword)){
                signuser.setPassword(newPassword);
                boolean update = signAdminService.updateById(signuser);
                map.put("msg", "密码修改成功，请重新登录");
                return "login";
            }else {
                return "redirect:/main_admin.html";
            }
        }else {
            Long loginUserId = (Long)session.getAttribute("loginUserId");
            SignUser signuser = signUserService.getById(loginUserId);
            if(signuser.getPassword().equals(oldPassword)){
                signuser.setPassword(newPassword);
                boolean update = signUserService.updateById(signuser);
                map.put("msg", "密码修改成功，请重新登录");
                return "login";
            }else {
                return "redirect:/main_user.html";
            }
        }
    }

    //忘记密码
    @PostMapping("/forgetPassword")
    public String forgetPassword(HttpSession session,Map<String, Object> map,
                                 @RequestParam("username") String username,
                                 @RequestParam("newPassword") String newPassword){
        System.out.println(username);
        QueryWrapper<SignUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",username);
        long count = signUserService.count(queryWrapper);
        if(count != 0){
            SignUser user = signUserService.getOne(queryWrapper);
            user.setPassword(newPassword);
            boolean b = signUserService.updateById(user);
            if(b){
                map.put("msg", "密码修改成功，请重新登录");
                return "login";
            }else {
                map.put("msg", "密码修改失败，");
                return "login";
            }
        }else {
            map.put("msg", "用户名不存在,重新检查用户名或重新注册");
            return "login";
        }

    }
}
