package com.llu.cat.component;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.llu.cat.entity.CheckItem;
import com.llu.cat.services.system.CheckItemService;
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
public class SetListener extends AnalysisEventListener {

    private List<Object> datas = new ArrayList<Object>();
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);
        doSomething(o);
    }
    private void doSomething(Object object) {
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
