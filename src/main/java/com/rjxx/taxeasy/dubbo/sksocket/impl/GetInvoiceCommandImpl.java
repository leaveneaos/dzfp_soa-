package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.bizhandle.invoicehandling.SkService;
import com.rjxx.taxeasy.bizcomm.tcs.SocketService;
import com.rjxx.taxeasy.bizhandle.maindata.command.SendCommand;
import com.rjxx.taxeasy.dal.KplsService;
import com.rjxx.taxeasy.dao.bo.Kpls;
import com.rjxx.taxeasy.dao.dto.InvoicePendingData;
import com.rjxx.taxeasy.dubbo.sksocket.command.GetInvoiceCommandService;
import com.rjxx.utils.XmlJaxbUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *@ClassName GetInvoiceCommandImpl
 *@Description 处理客户端定时任务开票请求的类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = 0)
public class GetInvoiceCommandImpl implements GetInvoiceCommandService{

    @Autowired
    private KplsService kplsService;

    @Autowired
    private SocketService socketService;

    @Autowired
    private SkService skService;

    /**
     * 开票方法
     * @param fpzldm
     * @param kpdid
     * @return
     */
    @Override
    public boolean getInvoice(String fpzldm, String kpdid) {

        try {
            doKp(fpzldm,kpdid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 执行开票
     *
     * @param fpzldm
     * @param kpdid
     */
    private void doKp(String fpzldm, String  kpdid) throws Exception {
        Kpls kpls = socketService.getDataFromMq(kpdid, fpzldm);
        if (kpls == null) {
            InvoicePendingData invoicePendingData = socketService.GetPendingData(kpdid);
            String xml = XmlJaxbUtils.toXml(invoicePendingData);
            Map map=new HashMap();
            map.put("kpdid",kpdid);
            map.put("SendCommand", SendCommand.SendPendingData);
            map.put("content",xml);
            map.put("lsh","");
            map.put("wait",false);
            map.put("timeout",1);
            skService.sendMessage(JSON.toJSONString(map));
            return;
        }
        if (kpls.getFpczlxdm().equals("14")) {
            kpls.setFpztdm("14");
            kpls.setXgsj(new Date());
            kplsService.save(kpls);
            socketService.voidInvoice(kpls.getKplsh(), false, 0);
        } else {
            kpls.setFpztdm("14");
            kpls.setXgsj(new Date());
            kplsService.save(kpls);
            socketService.doKp(kpls.getKplsh(), false, 0);
        }

    }
}
