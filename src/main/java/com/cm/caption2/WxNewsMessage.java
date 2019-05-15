package com.cm.caption2;

import java.util.List;

public class WxNewsMessage extends ReqBaseMsg {

    public WxNewsMessage(){}
    //消息
    public List<WxArticle> news;

    public List<WxArticle> getNews() {
        return news;
    }

    public void setNews(List<WxArticle> news) {
        this.news = news;
    }
    public WxNewsMessage(String touser,String toparty,String totag,
                         String msgtype,String agentid){
        super();
        super.touser = touser;
        super.toparty = toparty;
        super.totag = totag;
        super.msgtype = msgtype;
        super.agentid = agentid;

    }
    @Override
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
           for (int i = 0;i < news.size();i++){
               if(i != 0){
                   content.append(",");
               }
               content.append("{");
               //获得一条信息
               WxArticle info = news.get(i);
               StringBuffer artileContent = new StringBuffer("");
               if(null != info.getTitle() && !"".equals(info.getTitle())) {

                   if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                       artileContent.append(",");
                   }
                   //标题
                   artileContent.append("\"title\":\""+info.getTitle()+"\"");
               }
               if(null != info.getDescription() && !"".equals(info.getDescription())) {

                   if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                       artileContent.append(",");
                   }
                   //描述
                   artileContent.append("\"description\":\""+info.getDescription()+"\"");
               }
               if(null != info.getUrl() && !"".equals(info.getUrl())) {

                   if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                       artileContent.append(",");
                   }
                   //详细地址
                   artileContent.append("\"url\":\""+info.getUrl()+"\"");
               } if(null != info.getPicurl() && !"".equals(info.getPicurl())) {

                   if(null != artileContent.toString()&&!"".equals(artileContent.toString())){
                       artileContent.append(",");
                   }
                   //图片地址
                   artileContent.append("\"picurl\":\""+info.getPicurl()+"\"");
               }
               content.append(artileContent.toString()+"}");
           }
           if(!"".equals(str_tmp.toString())){
               str_tmp.append(",");
           }
           str_tmp.append("\"news\":{\"articles\":["+content+"]}");
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
