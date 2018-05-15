package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizhandle.utils.ParseInvoiceFileUtils;
import com.rjxx.taxeasy.dao.dto.InvoiceResponse;
import com.rjxx.taxeasy.dubbo.sksocket.command.ReturnInvoiceCommandService;
import com.rjxx.utils.XmlJaxbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *@ClassName ReturnInvoiceCommandImpl
 *@Description 客户端返回纸票开票数据处理的实现类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = -2)
public class ReturnInvoiceCommandImpl implements ReturnInvoiceCommandService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ParseInvoiceFileUtils parseInvoiceFileUtils;

    /**
     *方法
     * @param data
     * @return
     */
    @Override
    public boolean returnInvoice(String data)throws Exception {
        logger.info(data);
        try {
            InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, data);
            parseInvoiceFileUtils.updateInvoiceResult(response);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
