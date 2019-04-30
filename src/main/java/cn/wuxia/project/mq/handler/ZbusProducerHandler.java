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
import java.util.Map;

import cn.wuxia.project.mq.common.ZbusProducer;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class ZbusProducerHandler extends SupportZbusHandler {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, ZbusProducer> zbusProducers = Maps.newConcurrentMap();

    public ZbusProducer create(String topic) throws IOException, InterruptedException {
        ZbusProducer zbusProducer = MapUtils.getObject(zbusProducers, topic);
        if (zbusProducer != null) {
            return zbusProducer;
        }
        Assert.notNull(getBroker(), "地址不能为空");
        logger.info("zbus producer 初始化{}---{}", topic, ToStringBuilder.reflectionToString(this));
        zbusProducer = ZbusProducer.create(this).topic(topic);
        zbusProducers.put(topic, zbusProducer);
        return zbusProducer;
    }

    public ZbusProducer create(String topic, String tag) throws IOException, InterruptedException {
        return create(topic).tag(tag);
    }


}
