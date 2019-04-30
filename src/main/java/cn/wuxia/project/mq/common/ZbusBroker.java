/*
* Created on :2017年2月12日
* Author     :songlin
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.isharetech.cn All right reserved.
*/
package cn.wuxia.project.mq.common;

import java.io.IOException;

import cn.wuxia.common.util.StringUtil;
import io.zbus.mq.Broker;

public class ZbusBroker {
    private Broker broker;

    protected String address;

    public ZbusBroker(String address) {
        broker = new Broker(address);
    }

    public ZbusBroker() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Broker getBroker() {
        if (broker == null && StringUtil.isNotBlank(getAddress())) {
            broker = new Broker(getAddress());
        }
        return broker;
    }

    public void shutdown() {
        try {
            this.broker.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
