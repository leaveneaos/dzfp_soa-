package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizhandle.invoicehandling.DataOperate;
import com.rjxx.taxeasy.bizhandle.utils.KpUtils;
import com.rjxx.taxeasy.dal.KplsService;
import com.rjxx.taxeasy.dao.bo.Kpls;
import com.rjxx.taxeasy.dao.dto.InvoiceResponse;
import com.rjxx.taxeasy.dubbo.sksocket.command.ReturnReprintInvoiceCommandService;
import com.rjxx.utils.XmlJaxbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 *@ClassName ReturnReprintInvoiceCommandImpl
 *@Description 客户端返回重打发票数据处理的实现类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = 0)
public class ReturnReprintInvoiceCommandImpl implements ReturnReprintInvoiceCommandService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataOperate dataOperate;

    @Autowired
    private KplsService kplsService;

    @Override
    public boolean reprintInvoice(String params) {
        logger.debug("ReturnReprintInvoice:" + params);
        try {
            InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, params);
            String lsh = response.getLsh();
            int kplsh = KpUtils.getKplshByLsh(lsh);
            if (kplsh == 0) {
                logger.warn("Return Message Error,lsh is invalid:" + lsh);
                logger.warn(params);
                return false;
            }
            Kpls kpls = kplsService.findOne(kplsh);
            if (kpls == null) {
                logger.warn("kpls is not exists:" + lsh);
                logger.warn(params);
                return false;
            }
            if ("0000".equals(response.getReturnCode())) {
                kpls.setPrintflag("1");
                dataOperate.saveLog(kplsh, "00", "", "ReprintInvoice", "", 1, "", "" + kpls);
            } else {
                kpls.setPrintflag("2");
                dataOperate.saveLog(kplsh, "99", "", "ReprintInvoice", response.getReturnMessage(), 1, "", "" + kpls);
            }
            kpls.setXgsj(new Date());
            kplsService.save(kpls);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
