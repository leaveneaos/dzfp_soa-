package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.dubbo.sksocket.command.ReturnGetKcCommandService;

/**
 *@ClassName ReturnGetKcCommandImpl
 *@Description 获取库存的实现类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version="1.0.0",group = "socket",timeout = 12000,retries = 0)
public class ReturnGetKcCommandImpl implements ReturnGetKcCommandService{
    @Override
    public boolean returnGetKc(String data)throws Exception {
        return false;
    }
}
