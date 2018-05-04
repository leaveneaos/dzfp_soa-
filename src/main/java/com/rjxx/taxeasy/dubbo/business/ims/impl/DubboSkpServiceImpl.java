package com.rjxx.taxeasy.dubbo.business.ims.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.bizcomm.tcs.SocketService;
import com.rjxx.taxeasy.dubbo.business.ims.service.DubboSkpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName DubboSkpServiceImpl
 * @Description TODO
 * @Author 许黎明
 * @Date 2018-04-25 15:24
 * @Version 1.0
 **/
@Service(version = "1.0.0",group = "ims",timeout = 12000,retries = '0')
@Component("dubboSkpService")
public class DubboSkpServiceImpl implements DubboSkpService{

    @Autowired
    private SocketService socketService;
    @Override
    public String deviceAuth(int skpid) throws Exception {
        return socketService.deviceAuth(skpid);
    }

    @Override
    public String deviceState(int skpid) throws Exception {
        return socketService.deviceState(skpid);
    }

    @Override
    public String inputUDiskPassword(int skpid) throws Exception {
        return socketService.inputUDiskPassword(skpid);
    }

    @Override
    public String GetUploadStates(int skpid) throws Exception {
        return socketService.GetUploadStates(skpid);
    }

    @Override
    public String TriggerUpload(int skpid) throws Exception {
        return socketService.TriggerUpload(skpid);
    }

    @Override
    public String GetDeclareTaxStates(int skpid) throws Exception {
        return socketService.GetDeclareTaxStates(skpid);
    }

    @Override
    public String TriggerDeclareTax(int skpid) throws Exception {
        return socketService.TriggerDeclareTax(skpid);
    }

    @Override
    public String UDiskInfo(int skpid) throws Exception {
        return socketService.UDiskInfo(skpid);
    }

    @Override
    public String InvoiceControlInfo(int skpid) throws Exception {
        return socketService.InvoiceControlInfo(skpid);
    }

    @Override
    public String GetAllInvoiceSections(int skpid) throws Exception {
        return socketService.GetAllInvoiceSections(skpid);
    }

    @Override
    public String InvoiceDistribute(Map map) throws Exception {
        return socketService.InvoiceDistribute(map);
    }

    @Override
    public String UDiskBinding(int skpid) throws Exception {
        return socketService.UDiskBinding(skpid);
    }

    @Override
    public String SwitchUDisk(int skpid) throws Exception {
        return socketService.SwitchUDisk(skpid);
    }

    @Override
    public String DeviceInfo(int skpid) throws Exception {
        return socketService.DeviceInfo(skpid);
    }

    @Override
    public String FactoryReset(int skpid) throws Exception {
        return socketService.FactoryReset(skpid);
    }
}
