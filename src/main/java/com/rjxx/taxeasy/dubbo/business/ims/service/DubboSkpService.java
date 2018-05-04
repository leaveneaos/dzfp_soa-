package com.rjxx.taxeasy.dubbo.business.ims.service;

import java.util.Map;

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

    /**
     * 获取指定税控装置（当前已载入的）内指定票种的全部发票段信息
     * @param skpid
     * @return
     * @throws Exception
     */
    public String GetAllInvoiceSections(int skpid) throws  Exception;

    /**
     * 在本地已载入税控盘/金税盘与对应的报税盘之间分发、回收发票，
     * 或从局端下载、退回发票到本地 盘，或从本地报税盘分发至远程税控盘
     * @param map
     * @return
     * @throws Exception
     */
    public String InvoiceDistribute(Map map)throws Exception;

    /**
     * 。本指令可用于清空绑定列表，使终端可以绑定新的税控装置或纳税人。
     * @param skpid
     * @return
     * @throws Exception
     */
    public String UDiskBinding(int skpid)throws Exception;

    /**
     * 切换至终端连接的另一个税控装置（不包括报税盘）并重新初始化
     * @param skpid
     * @return
     * @throws Exception
     */
    public String SwitchUDisk(int skpid)throws Exception;

    /**
     * 获取设备信息。
     * @param skpid
     * @return
     * @throws Exception
     */
    public String DeviceInfo(int skpid)throws Exception;

    /**
     * 将终端恢复出厂设置。
     * @return
     * @throws Exception
     */
    public String FactoryReset(int skpid)throws Exception;
}
