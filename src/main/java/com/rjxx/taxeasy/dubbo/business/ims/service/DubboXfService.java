package com.rjxx.taxeasy.dubbo.business.ims.service;

/**
 * @ClassName DubboXfService
 * @Description TODO
 * @Author 许黎明
 * @Date 2018-04-18 15:01
 * @Version 1.0
 **/
public interface DubboXfService {

    /**
     * 盟度注册销方方法并获取token
     * @param skpid
     * @return
     * @throws Exception
     */
    public String registerXf(int  skpid) throws Exception;


    public String refreshToken(int  skpid)throws  Exception;
}
