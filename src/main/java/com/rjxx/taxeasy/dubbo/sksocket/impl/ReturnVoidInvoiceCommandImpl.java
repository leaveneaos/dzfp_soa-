package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.bizhandle.invoicehandling.GeneratePdfService;
import com.rjxx.taxeasy.bizhandle.utils.ClientDesUtils;
import com.rjxx.taxeasy.dal.GsxxService;
import com.rjxx.taxeasy.dal.KplsService;
import com.rjxx.taxeasy.dao.bo.Gsxx;
import com.rjxx.taxeasy.dao.bo.Kpls;
import com.rjxx.taxeasy.dao.dto.InvoiceResponse;
import com.rjxx.taxeasy.dubbo.sksocket.command.ReturnVoidInvoiceCommandService;
import com.rjxx.utils.XmlJaxbUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *@ClassName ReturnVoidInvoiceCommandImpl
 *@Description 客户端返回作废发票数据处理的实现类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries =0)
public class ReturnVoidInvoiceCommandImpl implements ReturnVoidInvoiceCommandService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KplsService kplsService;

    @Autowired
    private GeneratePdfService generatePdfService;

    @Autowired
    private ClientDesUtils clientDesUtils;

    @Autowired
    private GsxxService gsxxService;

    @Override
    public boolean voidInvoice(String data) {
        try {
            logger.info(data);
            InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, data);
            String returnCode = response.getReturnCode();
            if ("0000".equals(returnCode)) {
                String kplshStr = response.getLsh();
                int kplsh = Integer.valueOf(kplshStr);
                Kpls kpls = kplsService.findOne(kplsh);
                kpls.setFpztdm("08");
                kpls.setZfrq(DateUtils.parseDate(response.getCancelDate(), "yyyy-MM-dd"));
                kpls.setXgsj(new Date());
                kpls.setXgry(1);
                kplsService.save(kpls);
                Map parms = new HashMap(1);
                parms.put("gsdm", kpls.getGsdm());
                Gsxx gsxx = gsxxService.findOneByParams(parms);
                //String url="https://vrapi.fvt.tujia.com/Invoice/CallBack";
                String url = gsxx.getCallbackurl();
                if (!("").equals(url) && url != null) {
                    String returnmessage = generatePdfService.CreateReturnMessage(kpls.getKplsh());
                    //输出调用结果
                    logger.info("回写报文" + returnmessage);
                    if (returnmessage != null && !"".equals(returnmessage)) {
                        Map returnMap = generatePdfService.httpPost(returnmessage, kpls);
                        logger.info("返回报文" + JSON.toJSONString(returnMap));
                    }
                }
            } else {
                String kplshStr = response.getLsh();
                int kplsh = Integer.valueOf(kplshStr);
                Kpls kpls = kplsService.findOne(kplsh);
                //作废失败
                kpls.setFpztdm("05");
                kpls.setXgsj(new Date());
                kpls.setXgry(1);
                kpls.setErrorReason(response.getReturnMessage());
                kplsService.save(kpls);
                Map parms = new HashMap();
                parms.put("gsdm", kpls.getGsdm());
                Gsxx gsxx = gsxxService.findOneByParams(parms);
                //String url="https://vrapi.fvt.tujia.com/Invoice/CallBack";
                String url = gsxx.getCallbackurl();
                if (!("").equals(url) && url != null) {
                    String returnmessage = generatePdfService.CreateReturnMessage(kpls.getKplsh());
                    //输出调用结果
                    logger.info("回写报文" + returnmessage);
                    if (returnmessage != null && !"".equals(returnmessage)) {
                        Map returnMap = generatePdfService.httpPost(returnmessage, kpls);
                        logger.info("返回报文" + JSON.toJSONString(returnMap));
                    }
                }
                //作废失败
                kpls.setFpztdm("00");
                kplsService.save(kpls);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
