/*
* Created on :2017年2月12日
* Author     :songlin
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.isharetech.cn All right reserved.
*/
package cn.wuxia.project.mq.common;

public class ZbusMessage {
    private String body;

    private byte[] content;

    private String tag;
    public ZbusMessage() {

    }

    public ZbusMessage(String body) {
        this.body = body;
    }

    public ZbusMessage(byte[] content) {
        this.content = content;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
