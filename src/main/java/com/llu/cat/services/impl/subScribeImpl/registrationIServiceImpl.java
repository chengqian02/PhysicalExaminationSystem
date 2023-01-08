package com.llu.cat.services.impl.subScribeImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llu.cat.entity.PhyExRegistration;
import com.llu.cat.mapper.subscribeMapper.RegistrationMapper;
import com.llu.cat.services.subscribe.RegistrationService;
import org.springframework.stereotype.Service;

/**
 * @create 2022-03-14 22:44
 */
@Service
public class registrationIServiceImpl extends ServiceImpl<RegistrationMapper, PhyExRegistration> implements RegistrationService {
}
