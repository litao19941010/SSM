package com.cm.caption2;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.plaf.basic.BasicTextUI;
import java.io.IOException;

public class HelloWord {


    public static void main(String[] args) {
        HelloWord helloWord= new HelloWord();
        String jsonContext="{"
                +"\"touser\": \"LiTao\","
                +"\"toparty\": \"\","
                +"\"totag\": \"\","
                +"\"msgtype\": \"text\","
                +"\"agentid\": 1000003,"
                +"\"text\": {"
                +"\"content\": \"XXX,Hello Word!!!\""
                +"},"
                +"\"safe\":0"
                +"}";
        String corpId = "wwdd9c4fefd2b707b6";
        String corpsecret = "wTYoOULNMZUZq5CF3zrBahU1la-FSu8P4ZMmb-1qQD0";
        helloWord.sendReqMsg(jsonContext,corpId,corpsecret);
    }

    /**
     * 发送消息
     * @param jsonContext  json字符串
     * @param corpId  微信企业号标识
     * @param corpsecret  管理组凭证密钥
     * @return
     */
    public  JSONObject sendReqMsg(String jsonContext,String corpId,String corpsecret){
        //消息json格式
        JSONObject result =null;
        //获得token
        String token=getTokenFromWx(corpId, corpsecret);
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost= new HttpPost("https://qyapi.weixin.qq.com" +
                    "/cgi-bin/message/send?access_token="+token);
            //发送json格式的数据
            StringEntity myEntity = new StringEntity(jsonContext,
                    ContentType.create("text/plain", "UTF-8"));
            //设置需要传递的数据
            httpPost.setEntity(myEntity);
            // Create a custom response handler
            ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {
                //对访问结果进行处理
                public JSONObject handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        if(null!=entity){
                            String result= EntityUtils.toString(entity);
                            //根据字符串生成JSON对象
                            JSONObject resultObj = JSONObject.fromObject(result);
                            return resultObj;
                        }else{
                            return null;
                        }
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            //返回的json对象
            JSONObject responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println(responseBody.toString());
            result=responseBody;
            httpclient.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 获取token
     * @param corpId
     * @param corpsecret
     * @return
     */
    public  String getTokenFromWx(String corpId,String corpsecret){
        //获取的标识
        String token="";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            //利用get形式获得token
            HttpGet httpget = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+corpsecret);
            // Create a custom response handler
            ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {
                public JSONObject handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        if(null!=entity){
                            String result= EntityUtils.toString(entity);
                            //根据字符串生成JSON对象
                            JSONObject resultObj = JSONObject.fromObject(result);
                            return resultObj;
                        }else{
                            return null;
                        }
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            //返回的json对象
            JSONObject responseBody = httpclient.execute(httpget, responseHandler);
            if(null!=responseBody){
                token= (String) responseBody.get("access_token");//返回token
            }
            httpclient.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

}
