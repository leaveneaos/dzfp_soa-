package com.rjxx.taxeasy.dubbo.sksocket.command;

import java.util.Map;

/**
 *@ClassName LoginCommandService
 *@Description TODO
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
public interface LoginCommandService {

    public Map checklogin(String data)throws Exception;

    public String getKpdid(String kpdid)throws Exception;

    public Map islogin(String kpdid)throws Exception;


}
