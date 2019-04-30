/*
* Created on :2017年2月12日
* Author     :songlin
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.isharetech.cn All right reserved.
*/
package cn.wuxia.project.mq.handler;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import cn.wuxia.project.mq.common.ZbusConsumer;
import cn.wuxia.project.mq.common.ZbusMessageHandler;

public class ZbusConsumerHandler extends SupportZbusHandler {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void handler(String topic, ZbusMessageHandler messageHandler) throws IOException {
        Assert.notNull(getBroker(), "地址不能为空");
        logger.info("zbus consumer 初始化{}---{}, handler={}",topic, ToStringBuilder.reflectionToString(this), messageHandler.getClass().getName());
        ZbusConsumer.create(this).topic(topic).handler(messageHandler);
    }

}
