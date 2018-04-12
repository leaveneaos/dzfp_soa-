package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.dal.KplsService;
import com.rjxx.taxeasy.dao.bo.Kpls;
import com.rjxx.taxeasy.dao.dto.InvoiceResponse;
import com.rjxx.taxeasy.dubbo.sksocket.command.CancelQueueCommandService;
import com.rjxx.utils.XmlJaxbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *@ClassName CancelQueueCommandImpl
 *@Description 客户端取消队列的类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = 0)
public class CancelQueueCommandImpl implements CancelQueueCommandService{


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private KplsService kplsService;

    /**
     * 取消队列的方法
     * @param data
     * @return
     */
    @Override
    public boolean CancelQueue(String data) {

        logger.info(data);
        try{
                InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, data);
                String returnCode = response.getReturnCode();
                if ("8888".equals(returnCode)) {
                    String lsh = response.getLsh();
                    int pos = lsh.indexOf("$");
                    int kplsh;
                    if (pos != -1) {
                        kplsh = Integer.valueOf(lsh.substring(0, pos));
                    } else {
                        kplsh = Integer.valueOf(lsh);
                    }
                    logger.info("------------KPLSH----------------"+kplsh);
                    int posCommand=lsh.indexOf("￥");
                    String  Command=lsh.substring(posCommand+1, lsh.length());
                    logger.info("------------命令----------------"+Command);
                    if(Command.equals("VoidInvoice")||Command.equals("ReprintInvoice")||Command.equals("Invoice")){
                        Kpls kpls = kplsService.findOne(kplsh);
                        kpls.setFpztdm("04");
                        kplsService.save(kpls);
                    }
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;

            }
    }
}
