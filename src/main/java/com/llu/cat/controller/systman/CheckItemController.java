package com.llu.cat.controller.systman;

import com.llu.cat.entity.CheckItem;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.services.system.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @create 2022-03-10 23:02
 */
@Controller
public class CheckItemController {

    @Autowired
    CheckItemService checkItemService;

    @GetMapping("/checkitemList")
    public String checkitemList(Model model){
        List<CheckItem> checkitemlist = checkItemService.list();
        model.addAttribute("checkitemlist",checkitemlist);
        return "/admin/system_manage/check_item";
    }

    @GetMapping("/phyItem/modify/{id}")
    public String phyItemModify(@PathVariable("id") long id, Model model){
        CheckItem checkItem = checkItemService.getById(id);
        model.addAttribute("checkItem",checkItem);
        return "/admin/system_manage/phyItem_modify";
    }


    @RequestMapping("/phyItem_modify")
    public String phyItemmodify(CheckItem checkItem){
        checkItemService.updateById(checkItem);
        return "redirect:/checkitemList";
    }

    @GetMapping("/phyItem/delete/{id}")
    public String deleteUers(@PathVariable("id") long id){
        checkItemService.removeById(id);
        return "redirect:/checkitemList";
    }

    @PostMapping("/itemInfoAdd")
    public String itemInfoAdd(CheckItem checkItem){
        checkItemService.save(checkItem);

        return "redirect:/checkitemList";
    }

}
