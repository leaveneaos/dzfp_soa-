package com.rjxx.taxeasy.dubbo.business.ims.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizcomm.tcs.SocketService;
import com.rjxx.taxeasy.dubbo.business.ims.service.DubboSkpService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName DubboSkpServiceImpl
 * @Description TODO
 * @Author 许黎明
 * @Date 2018-04-25 15:24
 * @Version 1.0
 **/
@Service(version = "1.0.0",group = "ims",timeout = 12000,retries = '0')
public class DubboSkpServiceImpl implements DubboSkpService{

    @Autowired
    private SocketService socketService;
    @Override
    public String deviceAuth(int skpid) throws Exception {
        return socketService.deviceAuth(skpid);
    }

    @Override
    public String deviceState(int skpid) throws Exception {
        return socketService.deviceState(skpid);
    }

    @Override
    public String inputUDiskPassword(int skpid) throws Exception {
        return socketService.inputUDiskPassword(skpid);
    }
}
