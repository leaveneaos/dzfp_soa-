package com.rjxx.taxeasy.dubbo.business.tcs.service;

/**
 * @ClassName DubboInvoiceService
 * @Description 处理开票业务的接口
 * @Author 许黎明
 * @Date 2018/4/11 15:17
 * @Version 1.0
 **/
public interface DubboInvoiceService {

    /**
     * 获取发票代码，发票号码dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String getCodeAndNo(String p)throws Exception;

    /**
     * 作废发票dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String voidInvoice(String p)throws Exception;

    /**
     * 重新打印dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String reprintInvoice(String p) throws Exception;

    /**
     * 客户端录屏和组件开票dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String invoice(String p) throws Exception;

    /**
     * 重新生成pdf方法dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String ReCreatePdf(String p) throws Exception;

    /**
     * 税控服务器开票dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String skServerKP(String p) throws Exception;


    /**
     * 税控服务器电子发票查询dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String skServerQuery(String p) throws Exception;

    /**
     * 税控盒子开票dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String skBoxKP(String p) throws Exception;

    /**
     * 盟度E开云智能开票 dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String skEkyunKP(String p) throws Exception;

    /**
     * 盟度E开云智能获取发票数据方法 dubbo接口方法
     * @param p
     * @return
     * @throws Exception
     */
    public String skEkyunGetFpData(String p) throws Exception;

    /**
     * 凯盈开票查询接口
     * @param p
     * @return
     * @throws Exception
     */
    public String skInvoiceQuery(String p)throws Exception;

    /**
     * 凯盈作废发票
     * @param p
     * @return
     * @throws Exception
     */
    public String InvalidateInvoice(String p)throws Exception;

    /**
     * 凯盈获取当前发票段信息
     * @param p
     * @return
     * @throws Exception
     */
    public String GetCurrentInvoiceInfo(String p)throws Exception;


    /**
     * 凯盈获取未打印发票列表
     * @param p
     * @return
     * @throws Exception
     */
    public String GetInvoicesToPrint(String p)throws Exception;
}
