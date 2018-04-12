package com.rjxx.taxeasy.dubbo.sksocket.command;

/**
 *@ClassName LoginService
 *@Description TODO
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
public interface LoginService {

    public String loginUser(String data) throws Exception;


    public String getConnectInfo(String data) throws Exception;


}
