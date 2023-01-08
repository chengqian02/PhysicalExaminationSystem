package com.llu.cat.component;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.llu.cat.entity.PhyDepart;
import com.llu.cat.entity.SignUser;
import com.llu.cat.services.SignUserService;
import com.llu.cat.services.system.PhyDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @create 2022-04-03 22:07
 */
@Component
@Scope("prototype")
public class DepartListener extends AnalysisEventListener<PhyDepart> {

    
    @Autowired
    PhyDepartService phyDepartService;

    @Override
    public void invoke(PhyDepart phyDepart, AnalysisContext analysisContext) {
        phyDepartService.saveOrUpdate(phyDepart);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
