package cn.wuxia.project.mq.common;

import java.io.IOException;

import cn.wuxia.common.util.StringUtil;
import io.zbus.mq.Broker;
import io.zbus.mq.Message;
import io.zbus.mq.Producer;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

public class ZbusProducer {

    private Producer producer;

    private String topic;

    private String tag;

    public ZbusProducer(Producer producer) {
        this.producer = producer;
    }

    public ZbusProducer(Producer producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    public static ZbusProducer create(ZbusBroker zbusBroker) {
        //        Broker是对zbus服务器的本地抽象，多地址支持HA
        Broker broker = zbusBroker.getBroker();
        Producer p = new Producer(broker);
        return new ZbusProducer(p);
    }

    public static ZbusProducer create(ZbusAccount zbusAccount) throws IOException, InterruptedException {
        return create((ZbusBroker) zbusAccount).topic(zbusAccount.getTopic()).tag(zbusAccount.getTag());
    }

    public ZbusProducer topic(String topic) throws IOException, InterruptedException {
        this.producer.declareTopic(topic); //当确定队列不存在需创建
        this.topic = topic;
        return this;
    }

    public ZbusProducer tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void send(ZbusMessage message) throws IOException, InterruptedException {
        Assert.notNull(this.topic, "topic不能为空");
        Message msg = new Message();
        msg.setTopic(this.topic); //设置消息主题
        msg.setTag(this.tag); //可以设置消息标签
        if (StringUtil.isNotBlank(message.getBody())) {
            msg.setBody(message.getBody());
        } else if (ArrayUtils.isNotEmpty(message.getContent()))
            msg.setBody(message.getContent());

        Message res = this.producer.publish(msg);
        System.out.println(res);
    }

    public static void main(String[] args) {
        try {
            create(new ZbusAccount("192.168.1.10:15555", "mytopic")).tag("mytag").send(new ZbusMessage("123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
