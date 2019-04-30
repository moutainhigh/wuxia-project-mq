package cn.wuxia.project.mq.common;

import java.io.IOException;

public interface ZbusMessageHandler {

    void handle(ZbusMessage message) throws IOException;
}
