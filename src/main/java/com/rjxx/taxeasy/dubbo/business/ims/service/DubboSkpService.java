package com.rjxx.taxeasy.dubbo.business.ims.service;

/**
 * @ClassName DubboSkpService
 * @Description TODO
 * @Author 许黎明
 * @Date 2018-04-25 15:20
 * @Version 1.0
 **/
public interface DubboSkpService {

    /**
     * 凯盈盒子终端授权
     * @param skpid
     * @return
     */
    public String deviceAuth(int skpid)throws Exception;

    /**
     * 应用向凯盈平台查询终端状态
     * @param skpid
     * @return
     */
    public String deviceState (int skpid)throws Exception;

    /**
     * 应用向凯盈平台设置税控装置密码
     * @param skpid
     * @return
     */
    public String inputUDiskPassword (int skpid)throws Exception;

    /**
     * 凯盈查询发票上传状态
     * @param skpid
     * @return
     * @throws Exception
     */
    public String GetUploadStates(int skpid)throws Exception;
    /**
     * 凯盈立即上传发票
     * @param skpid
     * @return
     * @throws Exception
     */
    public String TriggerUpload (int skpid)throws Exception;

    /**
     * 凯盈抄报状态查询
     * @return
     * @throws Exception
     */
    public String GetDeclareTaxStates(int skpid) throws Exception;

    /**
     * 立即抄报
     * @param skpid
     * @return
     * @throws Exception
     */
    public String TriggerDeclareTax(int skpid) throws Exception;

    /**
     * 获取税控装置信息
     * @param skpid
     * @return
     * @throws Exception
     */
    public String UDiskInfo(int skpid) throws Exception;

    /**
     * 获取当前税控装置内发票的监控管理信息
     * @param skpid
     * @return
     * @throws Exception
     */
    public String InvoiceControlInfo(int skpid) throws Exception;
}
