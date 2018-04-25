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
}
