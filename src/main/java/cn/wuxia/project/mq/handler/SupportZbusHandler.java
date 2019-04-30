package cn.wuxia.project.mq.handler;

import cn.wuxia.project.mq.common.ZbusBroker;
import cn.wuxia.common.util.StringUtil;
import io.zbus.mq.Broker;

public class SupportZbusHandler extends ZbusBroker {

    private ZbusBroker zbusBroker;

    public ZbusBroker getZbusBroker() {
        return zbusBroker;
    }

    public void setZbusBroker(ZbusBroker zbusBroker) {
        this.zbusBroker = zbusBroker;
    }

    @Override
    public Broker getBroker() {
        if (zbusBroker != null) {
            return zbusBroker.getBroker();
        } else if (super.getBroker() != null) {
            return super.getBroker();
        } else if (StringUtil.isNotBlank(getAddress())) {
            return new Broker(getAddress());
        } else {
            return null;
        }
    }
}
