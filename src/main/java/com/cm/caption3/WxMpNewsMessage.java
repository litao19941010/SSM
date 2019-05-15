package com.cm.caption3;

import java.util.List;

/**
 * @author litao
 */
public class WxMpNewsMessage extends ReqBaseMsg {

    private WxArticleList mpnews;

    public WxMpNewsMessage(String touser, String toparty, String totag,
                           String msgtype, String agentid, List<WxMpArticle> mpnews,String safe){
        super();
        super.touser = touser;
        super.toparty = toparty;
        super.totag = totag;
        super.msgtype = msgtype;
        super.agentid = agentid;
        super.safe = safe;
        this.mpnews = new WxArticleList(mpnews);

    }

    public WxArticleList getMpnews() {
        return mpnews;
    }

    public void setMpnews(WxArticleList mpnews) {
        this.mpnews = mpnews;
    }

    public WxMpNewsMessage(){}

    public String toJsonStr() {
        StringBuffer jsonStr = new StringBuffer("{");
        StringBuffer str_tmp = new StringBuffer("");

        if(null!=touser && !"".equals(touser)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"touser\":"+"\""+touser+"\"");
        }
        if(null!=toparty && !"".equals(toparty)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"toparty\":"+"\""+toparty+"\"");
        }
        if(null!=totag && !"".equals(totag)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"totag\":"+"\""+totag+"\"");
        }
        if(null!=msgtype && !"".equals(msgtype)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"msgtype\":"+"\""+msgtype+"\"");
            //新闻消息
            StringBuffer content = new StringBuffer("");
            for (int i = 0;i < mpnews.getArticles().size();i++){
                if(i != 0){
                    content.append(",");
                }
                content.append("{");
                //获得一条信息
                WxMpArticle info = mpnews.getArticles().get(i);
                StringBuffer artileContent = new StringBuffer("");
                if(null != info.getTitle() && !"".equals(info.getTitle())) {

                    if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                        artileContent.append(",");
                    }
                    //标题
                    artileContent.append("\"title\":\""+info.getTitle()+"\"");
                }
                if(null != info.getThumb_media_id() && !"".equals(info.getThumb_media_id())) {

                    if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                        artileContent.append(",");
                    }
//                    图文消息缩略图的media_id
                    artileContent.append("\"thumb_media_id\":\""+info.getThumb_media_id()+"\"");
                }
                if(null != info.getAuthor() && !"".equals(info.getAuthor())) {

                    if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                        artileContent.append(",");
                    }
                    //作者
                    artileContent.append("\"author\":\""+info.getAuthor()+"\"");
                }
                if(null != info.getContent_source_url() && !"".equals(info.getContent_source_url())) {

                    if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                        artileContent.append(",");
                    }
                    //图文消息点击“阅读全文”之后的页面链接
                    artileContent.append("\"content_source_url\":\""+info.getContent_source_url()+"\"");
                }
                if(null != info.getContent() && !"".equals(info.getContent())) {

                    if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                        artileContent.append(",");
                    }
                    //内容，支持html标签，不超过666个字节
                    artileContent.append("\"content\":\""+info.getContent()+"\"");
                }
                if(null != info.getDigest() && !"".equals(info.getDigest())) {

                    if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                        artileContent.append(",");
                    }
                    //图文消息的描述，不超过512个字节，超过会自动截断
                    artileContent.append("\"digest\":\""+info.getDigest()+"\"");
                }
                if(null != info.getShow_cover_pic() && !"".equals(info.getShow_cover_pic())) {

                    if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                        artileContent.append(",");
                    }
                    //是否显示封面，1为显示，0为不显示
                    artileContent.append("\"show_cover_pic\":\""+info.getShow_cover_pic()+"\"");
                }
                content.append(artileContent.toString()+"}");
            }
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"mpnews\":{\"articles\":["+content+"]}");
        }

        if(null!=safe && !"".equals(safe)){
            if(!"".equals(str_tmp.toString())){
                str_tmp.append(",");
            }
            str_tmp.append("\"safe\":"+"\""+safe+"\"");
        }
        if(null!=agentid && !"".equals(agentid)){
            if(!"".equals(str_tmp.equals(str_tmp.toString()))){
                str_tmp.append(",");
            }
            str_tmp.append("\"agentid\":"+"\""+agentid+"\"");
        }
        jsonStr.append(str_tmp);
        jsonStr.append("}");

        return jsonStr.toString();
    }
}
