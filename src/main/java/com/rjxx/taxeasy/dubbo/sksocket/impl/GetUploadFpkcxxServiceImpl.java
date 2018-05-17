package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizhandle.utils.InvoiceResponseUtils;
import com.rjxx.taxeasy.bizhandle.utils.ResponeseUtils;
import com.rjxx.taxeasy.dal.UploadFpckxxService;
import com.rjxx.taxeasy.dao.dto.InvoiceResponse;
import com.rjxx.taxeasy.dubbo.sksocket.command.GetUploadFpkcxxService;
import com.rjxx.utils.DesUtils;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: zsq
 * @date: 2018/5/17 10:51
 * @describe:获取客户端 发票库存信息、商品编码版本
 */
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = -2)
public class GetUploadFpkcxxServiceImpl implements GetUploadFpkcxxService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UploadFpckxxService uploadFpckxxService;


    @Override
    public String getuploadFpkcxx(String p) throws Exception {
        logger.info("进入-upload---------------------"+p);
        if (StringUtils.isBlank(p)) {
            throw new Exception("参数不能为空");
        }
        String key = DesUtils.GLOBAL_DES_KEY;
        try {
            String uploadData = DesUtils.DESDecrypt(p, key);
            logger.info("解密之后的数据--------------"+uploadData);
            if(StringUtils.isBlank(uploadData)){
                return ResponeseUtils.error("数据解密失败");
            }
            InvoiceResponse invoiceResponse = uploadFpckxxService.UploadFpckxx(uploadData);
            String result = XmlJaxbUtils.toXml(invoiceResponse);
            logger.debug(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            InvoiceResponse response = InvoiceResponseUtils.responseError(e.getMessage());
            return XmlJaxbUtils.toXml(response);
        }
    }
}
