package com.rjxx.taxeasy.dubbo.business.ims.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizcomm.tcs.SocketService;
import com.rjxx.taxeasy.dal.SkpService;
import com.rjxx.taxeasy.dal.XfService;
import com.rjxx.taxeasy.dubbo.business.ims.service.DubboXfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName DubboXfServiceImpl
 * @Description TODO
 * @Author 许黎明
 * @Date 2018-04-18 15:15
 * @Version 1.0
 **/
@Service(version = "1.0.0",group = "ims",timeout = 12000,retries = '0')
@Component("dubboXfService")
public class DubboXfServiceImpl implements DubboXfService{

    @Autowired
    private XfService xfService;
    @Autowired
    private SkpService skpService;

    @Autowired
    private SocketService socketService;
    @Override
    public String registerXf(int skpid) throws Exception {
        return socketService.registerXf(skpid);
    }

    @Override
    public String refreshToken(int skpid) throws Exception {
        return socketService.refreshToken(skpid);
    }
}
