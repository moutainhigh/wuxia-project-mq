package cn.wuxia.project.mq.common;

import java.io.IOException;

import org.springframework.util.Assert;

import io.zbus.mq.*;

public class ZbusConsumer {

    private ConsumerConfig config;

    private String topic;

    public ZbusConsumer(ConsumerConfig config) {
        this.config = config;
    }

    public static ZbusConsumer create(ZbusBroker zbusBroker) {
        //        Broker是对zbus服务器的本地抽象，多地址支持HA
        Broker broker = zbusBroker.getBroker();
        ConsumerConfig config = new ConsumerConfig(broker);
        return new ZbusConsumer(config);
    }

    public static ZbusConsumer create(ZbusAccount zbusAccount) {
        return create((ZbusBroker) zbusAccount).topic(zbusAccount.getTopic());
    }

    public ZbusConsumer topic(String topic) {
        this.topic = topic;
        this.config.setTopic(topic);
        return this;
    }

    public void handler(ZbusMessageHandler messageHandler) throws IOException {
        Assert.notNull(this.topic, "topic不能为空");
        new Consumer(this.config).start(new MessageHandler() {
            @Override
            public void handle(Message msg, MqClient client) throws IOException {
                ZbusMessage message = new ZbusMessage();
                message.setContent(msg.getBody());
                message.setBody(msg.getBodyString());
                message.setTag(msg.getTag());
                messageHandler.handle(message);
            }
        });
    }

    public static void handler(ZbusAccount zbusAccount, ZbusMessageHandler messageHandler) throws Exception {
        ZbusConsumer.create(zbusAccount).handler(messageHandler);
    }

    public static void main(String[] args) {
        try {
            ZbusConsumer.create(new ZbusAccount("192.168.1.10:15555", "mytopic")).handler(new ZbusMessageHandler() {
                @Override
                public void handle(ZbusMessage message) throws IOException {
                    System.out.println(message.getBody());
                    //throw new IOException("bbbbbb");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
