package com.cm.caption3;

public class WxMediaMessage extends ReqBaseMsg {
    public WxMediaMessage(){ }

    public String media_id;

    public String mediaType;

    public String title;

    public String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected String safe;

    public String getMedia_id() {
        return media_id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public WxMediaMessage(String touser, String toparty, String totag, String msgtype, String mediaType, String agentid, String media_id, String safe){
        super();
        super.touser = touser;
        super.toparty= toparty;
        super.totag = totag;
        super.agentid = agentid;
        super.msgtype = msgtype;
        this.media_id = media_id;
        this.safe = safe;
        this.mediaType = mediaType;
    }

    public WxMediaMessage(String touser, String toparty, String totag,
                          String msgtype, String mediaType, String agentid,
                          String media_id,
                          String title,
                          String description,
                          String safe){
        super();
        super.touser = touser;
        super.toparty= toparty;
        super.totag = totag;
        super.agentid = agentid;
        super.msgtype = msgtype;
        this.media_id = media_id;
        this.title = title;
        this.description = description;
        this.safe = safe;
        this.mediaType = mediaType;
    }

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
            //图片
            if(null!=media_id&&!"".equals(media_id)){
                if(!"".equals(str_tmp.toString())){
                    str_tmp.append(",");
                }
                if("video".equals(mediaType)){
                    str_tmp.append("\""+mediaType+"\": {\"media_id\": \""+media_id+"\"," +
                            "\"title\":" +"\""+title+"\","+
                            "\"description\":" +"\""+description+"\""+
                            "}");
                }else {
                    str_tmp.append("\""+mediaType+"\": {\"media_id\": \""+media_id+"\"}");
                }

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
