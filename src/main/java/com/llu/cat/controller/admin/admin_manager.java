package com.llu.cat.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llu.cat.entity.SignAdmin;
import com.llu.cat.entity.SignUser;
import com.llu.cat.services.SignAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            return "redirect:/admin_manage";
        }else {
            map.put("add_admin_msg","添加失败，请重新添加");
            return "redirect:/admin_manage";
        }
    }

    //管理员列表
    @RequestMapping("/admin_manage")
    public String admin_list(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam(value = "qu", defaultValue = "10") Integer qu ){
        List<SignAdmin> list = signAdminService.list();
//        model.addAttribute("user_list" ,list);
        Page<SignAdmin> page = new Page<>(pn, qu);
        Page<SignAdmin> userPage = signAdminService.page(page, null);
        Long current = userPage.getCurrent();
        Long total = userPage.getTotal();
        Long pages = userPage.getPages();
        List<SignAdmin> records = userPage.getRecords();
        model.addAttribute("signUserPage",userPage);
        return "/admin/system_manage/admin_manage";
    }

    //管理员删除
    @GetMapping("/admin/delete/{id}")
    public String deleteUers(@PathVariable("id") long id){
        signAdminService.removeById(id);
        return "redirect:/admin_manage";
    }

    //管理员修改
    @GetMapping("/admin/modify/{id}")
    public String ModifyUser(@PathVariable("id") long id,
                             Model model){
        SignAdmin user = signAdminService.getById(id);
        model.addAttribute("user",user);
        return "/admin/system_manage/admin_modify";
    }

    @PostMapping("/adminModify")
    public String usersModify(SignAdmin user){
        boolean update = signAdminService.updateById(user);
        return "redirect:/admin_manage";
    }

}
