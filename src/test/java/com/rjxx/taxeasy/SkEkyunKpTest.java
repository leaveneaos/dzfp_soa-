package com.rjxx.taxeasy;

import com.rjxx.Application;
import com.rjxx.taxeasy.dal.JylsService;
import com.rjxx.taxeasy.dal.KplsService;
import com.rjxx.taxeasy.dal.KpspmxService;
import com.rjxx.taxeasy.dubbo.business.ims.impl.DubboSkpServiceImpl;
import com.rjxx.taxeasy.dubbo.business.ims.impl.DubboXfServiceImpl;
import com.rjxx.taxeasy.dubbo.business.tcs.impl.DubboInvoiceServiceImpl;
import com.rjxx.utils.DesUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @ClassName SkEkyunKpTest
 * @Description TODO
 * @Author 许黎明
 * @Date 2018-04-19 16:33
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SkEkyunKpTest {

    @Autowired
    private DubboXfServiceImpl dubboXfService;
    @Autowired
    private DubboSkpServiceImpl dubboSkpService;
    @Autowired
    private DubboInvoiceServiceImpl dubboInvoiceService;
    @Autowired
    private KplsService kplsService;
    @Autowired
    private JylsService jylsService;
    @Autowired
    private KpspmxService kpspmxService;
    @Test
    public void skekyunKp(){

        try {
            //String token=dubboXfService.registerXf(647);
            //String refreshToken=dubboXfService.refreshToken(647);
            //dubboInvoiceService.skEkyunKP(encryptSkServerParameter(8624 + ""));
            //dubboInvoiceService.skEkyunGetFpData(encryptSkServerParameter(8624 + ""));
            /*Kpls kpls=kplsService.findOne(8624);
            Jyls jyls=jylsService.findOne(kpls.getDjh());
            Map params = new HashMap();
            params.put("kplsh", kpls.getKplsh());
            List<Kpspmx> kpspmxList = kpspmxService.findMxList(params);
            String  newInvoice= PacketBody.getInstance().Packet_Invoice_Json(kpls,jyls,kpspmxList);
            System.out.println(newInvoice);*/
            //dubboSkpService.deviceAuth(647);
            dubboSkpService.deviceState(647);
            //dubboInvoiceService.skBoxKP(encryptSkServerParameter(8650 + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 加密税控服务参数
     *
     * @param params
     * @return
     */
    public String encryptSkServerParameter(String params) throws Exception {
        return DesUtils.DESEncrypt(params, "R1j2x3x4");
    }
}
