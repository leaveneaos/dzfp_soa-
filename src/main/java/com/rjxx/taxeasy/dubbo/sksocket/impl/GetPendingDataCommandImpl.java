package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizcomm.tcs.SocketService;
import com.rjxx.taxeasy.dao.dto.InvoicePendingData;
import com.rjxx.taxeasy.dubbo.sksocket.command.GetPendingDataCommandService;
import com.rjxx.utils.XmlJaxbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



/**
 *@ClassName GetPendingDataCommandImpl
 *@Description 从mq获取待开票数据的类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = 0)
public class GetPendingDataCommandImpl implements GetPendingDataCommandService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SocketService socketService;

    /**
     *
     * @param kpdid
     * @return
     */
    @Override
    public String GetPendingData(String kpdid) {
        InvoicePendingData result = socketService.GetPendingData(kpdid);
        String xml = XmlJaxbUtils.toXml(result);
        return xml;
    }
}
