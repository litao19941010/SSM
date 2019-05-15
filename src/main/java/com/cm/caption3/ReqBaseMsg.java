package com.cm.caption3;

/**
 * 所有主动调用模式下信息推送的父类
 */
public abstract class ReqBaseMsg {
    /**
     * 此处为微信中添加的账户，并非个人微信号
     * 成员id列表，消息接收者，个接收者用'|'分隔，最多支持1000个，
     * 当touser为@all时，向企业应用所有的成员发送
     */
    protected String touser;
    /**
     * 部门id列表，多个接收者用'|'分隔，最多支持100个，当touser为@all时忽略本参数
     */
    protected String toparty;
    /**
     * 标签id列表，多个接收者用'|'分隔，当touser为@all时忽略本参数
     */
    protected String totag;
    /**
     * 消息类型,类型为
     * text,image,voice,video,file,news,mapnews
     */
    protected String msgtype;
    /**
     * 企业公众号 ID
     */
    protected String agentid;

    /**
     * 是否保密
     */
    protected String safe;


    /**
     * 定义抽象方法
     * @return
     */

    public abstract String toJsonStr();
}
