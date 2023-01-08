package com.llu.cat.component;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.llu.cat.entity.CheckItem;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.services.system.CheckItemService;
import com.llu.cat.services.system.PhyDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @create 2022-04-03 22:07
 */
@Component
@Scope("prototype")
public class ItemListener extends AnalysisEventListener<CheckItem> {

    @Autowired
    CheckItemService checkItemService;

    @Override
    public void invoke(CheckItem checkItem, AnalysisContext analysisContext) {
        checkItemService.saveOrUpdate(checkItem);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
