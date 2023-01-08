package com.llu.cat.component;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.llu.cat.entity.SignUser;
import com.llu.cat.services.SignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2022-04-03 22:07
 */
@Component
@Scope("prototype")
public class UserListener extends AnalysisEventListener<SignUser> {

    @Autowired
    SignUserService signUserService;
    @Override
    public void invoke(SignUser signUser, AnalysisContext analysisContext) {
        signUserService.saveOrUpdate(signUser);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
