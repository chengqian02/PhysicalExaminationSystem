package com.llu.cat.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llu.cat.entity.SignUser;
import com.llu.cat.services.SignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @create 2022-03-09 10:13
 */
@Controller
public class user_manage {

    @Autowired
    SignUserService signUserService;

    //用户列表
    @RequestMapping("/user_manage")
    public String user_list(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam(value = "qu", defaultValue = "10") Integer qu ){
        List<SignUser> list = signUserService.list();
//        model.addAttribute("user_list" ,list);
        Page<SignUser> page = new Page<>(pn, qu);
        Page<SignUser> userPage = signUserService.page(page, null);
        Long current = userPage.getCurrent();
        Long total = userPage.getTotal();
        Long pages = userPage.getPages();
        List<SignUser> records = userPage.getRecords();
        model.addAttribute("signUserPage",userPage);
        return "/admin/system_manage/user_manage";
    }

    //用户删除
    @GetMapping("/user/delete/{id}")
    public String deleteUers(@PathVariable("id") long id){
        signUserService.removeById(id);
        return "redirect:/user_manage";
    }

    //用户修改
    @GetMapping("/user/modify/{id}")
    public String ModifyUser(@PathVariable("id") long id,
                             Model model){
        SignUser user = signUserService.getById(id);
        model.addAttribute("user",user);
        return "/admin/system_manage/user_modify";
    }

    @PostMapping("/usersModify")
    public String usersModify(SignUser user){
        boolean update = signUserService.updateById(user);
        return "redirect:/user_manage";
    }

    //用户添加
    @PostMapping("/userAdd")
    public  String userAdd(SignUser signUser, Map<String, String> map ,HttpServletRequest request){
        String parameter = request.getParameter("stuPower");
        signUser.setStuPower(Integer.parseInt(parameter));
        boolean save = signUserService.save(signUser);
        if(save){
            map.put("addinfo", "添加成功");
        }else {
            map.put("addinfo", "添加失败");
        }
        return "redirect:/user_manage";
    }

}
