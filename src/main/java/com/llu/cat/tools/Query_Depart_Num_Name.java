package com.llu.cat.tools;

import com.llu.cat.entity.PhyDepart;
import com.llu.cat.services.system.PhyDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @create 2022-03-25 14:28
 */
@Service
public class Query_Depart_Num_Name {


    @Autowired
    PhyDepartService phyDepartService;

    public HashMap<Long, PhyDepart> query_num_name(){
        HashMap<Long, PhyDepart> hashMap = new HashMap<>();
        List<PhyDepart> list = phyDepartService.list();
        for (PhyDepart phyDepart: list) {
            hashMap.put(phyDepart.getDepartNum(), phyDepart);
        }
        return  hashMap;
    }
}
