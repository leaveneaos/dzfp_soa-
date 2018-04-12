package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.dubbo.sksocket.command.ReturnGetCodeAndNoCommandService;
import com.rjxx.utils.DesUtils;
import com.rjxx.utils.HtmlUtils;
import com.rjxx.utils.StringUtils;

import java.util.Map;

/**
 *@ClassName ReturnGetCodeAndNoCommandImpl
 *@Description 获取发票代码号码的实现类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = 0)
public class ReturnGetCodeAndNoCommandImpl implements ReturnGetCodeAndNoCommandService{

    /**
     * 方法
     * @param data
     * @return
     * @throws Exception
     */
    @Override
    public Map returnGetCodeAndNo(String data) throws Exception{
        if (StringUtils.isBlank(data)) {
            throw new Exception("参数不能为空");
        }
        String params = this.decryptSkServerParameter(data);
        Map<String, String> map = HtmlUtils.parseQueryString(params);
        return map;
    }
    public static final String SK_SERVER_DES_KEY = "R1j2x3x4";

    /**
     * 加密税控服务参数
     *
     * @param params
     * @return
     */
    public String encryptSkServerParameter(String params) throws Exception {
        return DesUtils.DESEncrypt(params, SK_SERVER_DES_KEY);
    }

    public String decryptSkServerParameter(String encryptParams) throws Exception {
        return DesUtils.DESDecrypt(encryptParams, SK_SERVER_DES_KEY);
    }
}
