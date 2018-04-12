package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizcomm.tcs.SocketService;
import com.rjxx.taxeasy.dao.dto.InvoicePendingData;
import com.rjxx.taxeasy.dubbo.sksocket.command.PendingDataService;
import com.rjxx.utils.DesUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.rjxx.taxeasy.bizhandle.utils.ClientDesUtils.decryptClientQueryString;


/**
 *@ClassName PendingDataServiceImpl
 *@Description 客户端登陆获取销方开票点的实现类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = 0)
public class PendingDataServiceImpl implements PendingDataService{

    @Autowired
    private SocketService socketService;

    @Override
    public String getPendingData(String p) throws Exception{
        InvoicePendingData invoicePendingData = new InvoicePendingData();
        Map<String, String> queryMap = null;
        try {
            queryMap =decryptClientQueryString(p);
        } catch (Exception e) {
            invoicePendingData.setSuccess("false");
            invoicePendingData.setMessage(e.getMessage());
            return generateInvoicePendingDataResult(invoicePendingData);
        }
        String kpdidStr = queryMap.get("kpdid");
        String kpdid = kpdidStr;
        invoicePendingData  = socketService.GetPendingData(kpdid);
        String xml = XmlJaxbUtils.toXml(invoicePendingData);
        String result = DesUtils.DESEncrypt(xml, DesUtils.GLOBAL_DES_KEY);
        return result;
    }

    /**
     * 生成版本结果
     *
     * @param invoicePendingData
     * @return
     */
    private String generateInvoicePendingDataResult(InvoicePendingData invoicePendingData) throws Exception {
        String result = XmlJaxbUtils.toXml(invoicePendingData);
        result = DesUtils.DESEncrypt(result, DesUtils.GLOBAL_DES_KEY);
        return result;
    }
}
