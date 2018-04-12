package com.rjxx.taxeasy.dubbo.sksocket.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.dal.CszbService;
import com.rjxx.taxeasy.dal.SkpService;
import com.rjxx.taxeasy.dao.bo.Cszb;
import com.rjxx.taxeasy.dao.bo.Skp;
import com.rjxx.taxeasy.dao.dto.LoginInfo;
import com.rjxx.taxeasy.dubbo.sksocket.command.LoginCommandService;
import com.rjxx.utils.DesUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;


/**
 *@ClassName LoginCommandImpl
 *@Description 客户端登陆获取销方开票点的类
 *@Author 许黎明
 *@Date 2018/3/29.
 *@Version 1.0
 **/
@Service(version = "1.0.0",group = "socket",timeout = 12000,retries = 0)
public class LoginCommandImpl implements LoginCommandService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SkpService skpService;
    @Autowired
    private CszbService cszbService;

    /**
     * 校验开票点是否存在
     * @param data
     * @return
     */
    @Override
    public Map checklogin(String data) {

            Map resultMap=new HashMap();
        try{
            data = DesUtils.DESDecrypt(data, DesUtils.GLOBAL_DES_KEY);
            LoginInfo loginInfo = XmlJaxbUtils.convertXmlStrToObject(LoginInfo.class, data);
            String kpdid = loginInfo.Kpdid;
            String sessionId = loginInfo.SessionId;
            String macAddr = loginInfo.MacAddr;
            resultMap.put("kpdid",kpdid);
            if (kpdid.equals("0") || StringUtils.isBlank(sessionId) || StringUtils.isBlank(macAddr)) {
                resultMap.put("islogin",false);
            }else{
                resultMap.put("islogin",true);
            }
            //校验开票点
            Skp skp = skpService.findOne(Integer.parseInt(kpdid));
            if (skp == null) {
                logger.info("kpdid:" + kpdid + " not exists,client will logout!!!");
                resultMap.put("isskp",false);
            }else{
                resultMap.put("isskp",true);
            }
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return resultMap;
        }
    }

    /**
     * 判断是否时多开票点开票
     * @param kpdid
     * @return
     */
    @Override
    public String getKpdid(String kpdid) {
        Skp skp = skpService.findOne(Integer.parseInt(kpdid));
        Cszb cszb = cszbService.getSpbmbbh(skp.getGsdm(), skp.getXfid(), skp.getId(), "sfzcdkpdkp");
        String sfzcdkpdkp = cszb.getCsz();
        if(sfzcdkpdkp.equals("是")){
            kpdid=skp.getSkph();
        }
        return kpdid;
    }
}
