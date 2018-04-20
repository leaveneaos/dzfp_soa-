package com.rjxx.taxeasy.dubbo.sksocket.command;


/**
 *@ClassName ReturnInvoiceFileCommandService
 *@Description 税控服务返回电票开票数据处理接口
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
public interface ReturnInvoiceFileCommandService {

    public boolean einvoice(String data)throws Exception;

}
