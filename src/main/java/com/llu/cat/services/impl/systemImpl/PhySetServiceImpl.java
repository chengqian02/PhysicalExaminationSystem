package com.llu.cat.services.impl.systemImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llu.cat.entity.PhySet;
import com.llu.cat.mapper.subscribeMapper.PhySetMapper;
import com.llu.cat.services.system.PhySetService;
import org.springframework.stereotype.Service;

/**
 * @create 2022-03-07 20:58
 */
@Service
public class PhySetServiceImpl extends ServiceImpl<PhySetMapper, PhySet> implements PhySetService {
}
