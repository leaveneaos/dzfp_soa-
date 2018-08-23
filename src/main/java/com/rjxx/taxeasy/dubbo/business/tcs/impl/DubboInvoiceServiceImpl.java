package com.rjxx.taxeasy.dubbo.business.tcs.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizcomm.tcs.SocketService;
import com.rjxx.taxeasy.bizhandle.invoicehandling.SkService;
import com.rjxx.taxeasy.bizhandle.utils.InvoiceResponseUtils;
import com.rjxx.taxeasy.dao.dto.InvoiceResponse;
import com.rjxx.taxeasy.dubbo.business.tcs.service.DubboInvoiceService;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName DubboInvoiceServiceImpl
 * @Description 处理开票业务的接口实现类
 * @Author 许黎明
 * @Date 2018/4/11 15:31
 * @Version 1.0
 **/
@Service(version = "1.0.0",group = "tcs",timeout = 12000,retries = -2)
@Component("dubboInvoiceService")
public class DubboInvoiceServiceImpl implements DubboInvoiceService{


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SocketService socketService;

    @Autowired
    private SkService skService;


    @Override
    public String getCodeAndNo(String p) throws Exception {
        return socketService.getCodeAndNo(p);
    }

    @Override
    public String voidInvoice(String p) throws Exception {
        if (StringUtils.isBlank(p)) {
            throw new Exception("参数不能为空");
        }
        String kplshStr = skService.decryptSkServerParameter(p);
        int kplsh = Integer.valueOf(kplshStr);
        logger.debug("receive void invoice request:" + kplsh);
        return socketService.voidInvoice(kplsh,false,0);
    }

    @Override
    public String reprintInvoice(String p) throws Exception {
        return socketService.reprintInvoice(p);
    }

    @Override
    public String invoice(String p) throws Exception {
            String result=null;
        try {
            if (StringUtils.isBlank(p)) {
                throw new Exception("参数不能为空");
            }
            String kplshStr = skService.decryptSkServerParameter(p);
            int kplsh = Integer.valueOf(kplshStr);
            logger.debug("receive doKp invoice request:" + kplsh);
            InvoiceResponse invoiceResponse =socketService.doKp(kplsh,false,0);
            result = XmlJaxbUtils.toXml(invoiceResponse);
            logger.debug(result);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String ReCreatePdf(String p) throws Exception {
        try {
            if (StringUtils.isBlank(p)) {
                throw new Exception("参数不能为空");
            }
            String kplshStr = skService.decryptSkServerParameter(p);
            int kplsh = Integer.valueOf(kplshStr);
            logger.debug("receive invoice request:" + kplsh);
            InvoiceResponse invoiceResponse  = socketService.generatePdf(kplsh);
            String result = XmlJaxbUtils.toXml(invoiceResponse);
            logger.debug(result);
            return result;
        }catch (Exception e){
            logger.error("", e);
            InvoiceResponse response = InvoiceResponseUtils.responseError(e.getMessage());
            return XmlJaxbUtils.toXml(response);
        }
    }

    @Override
    public String skServerKP(String p) throws Exception {
        try {
            if (StringUtils.isBlank(p)) {
                throw new Exception("参数不能为空");
            }
            String kplshStr = skService.decryptSkServerParameter(p);
            int kplsh = Integer.valueOf(kplshStr);
            logger.debug("receive invoice request:" + kplsh);
            InvoiceResponse invoiceResponse  = socketService.skServerKP(kplsh);
            String result = XmlJaxbUtils.toXml(invoiceResponse);
            logger.debug(result);
            return result;
        }catch (Exception e){
            logger.error("", e);
            InvoiceResponse response = InvoiceResponseUtils.responseError(e.getMessage());
            return XmlJaxbUtils.toXml(response);
        }
    }


    @Override
    public String skServerQuery(String p) throws Exception {
        try {
            if (StringUtils.isBlank(p)) {
                throw new Exception("参数不能为空");
            }
            String kplshStr = skService.decryptSkServerParameter(p);
            int kplsh = Integer.valueOf(kplshStr);
            logger.debug("服务器电子发票查询receive invoice request:" + kplsh);
            InvoiceResponse invoiceResponse  = socketService.skServerQuery(kplsh);
            String result = XmlJaxbUtils.toXml(invoiceResponse);
            logger.debug(result);
            return result;
        }catch (Exception e){
            logger.error("", e);
            InvoiceResponse response = InvoiceResponseUtils.responseError(e.getMessage());
            return XmlJaxbUtils.toXml(response);
        }
    }

    @Override
    public String skBoxKP(String p) throws Exception {
        return socketService.skBoxKP(p);
    }

    @Override
    public String skEkyunKP(String p) throws Exception {
        return socketService.skEkyunKP(p);
    }

    @Override
    public String skEkyunGetFpData(String p) throws Exception {
        return socketService.skEkyunGetFpData(p);
    }

    @Override
    public String skInvoiceQuery(String p) throws Exception {
        return socketService.skInvoiceQuery(p);
    }

    @Override
    public String InvalidateInvoice(String p) throws Exception {
        return socketService.InvalidateInvoice(p);
    }

    @Override
    public String GetCurrentInvoiceInfo(String p) throws Exception {
        return socketService.GetCurrentInvoiceInfo(p);
    }

    @Override
    public String GetInvoicesToPrint(String p) throws Exception {
        return socketService.GetInvoicesToPrint(p);
    }

    @Override
    public String PrintInvoice(String p) throws  Exception{
        return socketService.PrintInvoice(p);
    }
}
