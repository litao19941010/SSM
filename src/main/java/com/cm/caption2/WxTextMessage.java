package com.cm.caption2;

public class WxTextMessage extends ReqBaseMsg {

    public WxTextMessage(){}

    public String content;

    protected String safe;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public WxTextMessage(String touser,String toparty,String totag,
                         String msgtype,String agentid,String content,
                         String safe){
        super();
        super.touser = touser;
        super.toparty = toparty;
        super.totag = totag;
        super.msgtype = msgtype;
        super.agentid = agentid;
        this.content = content;
        this.safe = safe;
    }

    @Override
    public String toJsonStr() {
        StringBuffer jsonStr = new StringBuffer("{");
        StringBuffer str_tmp = new StringBuffer("");
        if(null!=touser&&!"".equals(touser)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"touser\": \""+touser+"\"");
        }
        if(null!=toparty&&!"".equals(toparty)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"toparty\": \""+toparty+"\"");
        }
        if(null!=totag&&!"".equals(totag)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"totag\": \""+totag+"\"");
        }
        if(null!=msgtype&&!"".equals(msgtype)){
            //去除空格
            msgtype=msgtype.trim();
            //判断是否加逗号
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"msgtype\": \""+msgtype+"\"");
            //文本消息
            if(null!=content&&!"".equals(content)){
                if(!"".equals(str_tmp.toString())){
                    str_tmp.append(",");
                }
                str_tmp.append("\"text\": {\"content\": \""+content+"\"}");
            }

        }
        if(null!=agentid&&!"".equals(agentid)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"agentid\": \""+agentid+"\"");
        }

        if(null!=safe&&!"".equals(safe)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"safe\": \""+safe+"\"");
        }
        jsonStr.append(str_tmp);
        jsonStr.append("}");
        return jsonStr.toString();
    }
}
