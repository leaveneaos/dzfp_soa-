package com.rjxx.taxeasy.dubbo.sksocket.command;

/**
 *@ClassName GetInvoiceCommandService
 *@Description TODO
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
public interface GetInvoiceCommandService {

    public boolean getInvoice(String fpzldm,String kpdid);

}
