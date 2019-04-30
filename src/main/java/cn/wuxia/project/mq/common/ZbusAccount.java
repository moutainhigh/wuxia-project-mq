package cn.wuxia.project.mq.common;

public class ZbusAccount extends ZbusBroker {

    private String topic;

    private String tag;

    public ZbusAccount() {
    }

    public ZbusAccount(String address) {
        super();
        super.address = address;
    }

    public ZbusAccount(String address, String topic) {
        super();
        super.address = address;
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
