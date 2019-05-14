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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**微信企业号公用类
 * @author  LiTao
 */
public class WxUtil {
    //主动调用：发送消息获得token
    public static String access_token;
    //主动调用：请求token的时间
    public static Date access_token_date;

    public static long accessTokenInvalidTime = 7200L;
    //企业号corpID
    public static final String MESSAGE_CORPID = "wwdd9c4fefd2b707b6";
    //企业公众号 密钥
    public static final String MESSAGE_SECRET = "wTYoOULNMZUZq5CF3zrBahnPHHBzkWIREzi32UT3Prg";


    //错误信息
    public static final String ESSOR_MESAGE = "Unexpected response status: ";

    public static void main(String[] args) {
//       JSONObject jsonObject = sendImageTemp("video", "e:/media/3.mp4");
       String media_id = "3nwm9fwbPwvkGj94II06nuuwOdHmlCQlCDSB6QVPRZVPdUCmgc9H_F0RC9f-cLDb-";
        WxMediaMessage wxImageMessage = new WxMediaMessage(
               "LiuLiu","","",
                "video","video","1000003",
                media_id,
                "测试",
                "李韬无聊测试的",
                "0"
        );
       new WxUtil().sendReqMsg(wxImageMessage);




    }

    public JSONObject sendReqMsg(ReqBaseMsg mess) {
        String context = mess.toJsonStr();
        //消息json格式
        JSONObject result = null;
        //获得token
        String token = getTokenFromWx();
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://qyapi.weixin.qq.com" +
                    "/cgi-bin/message/send?access_token=" + token);
            //发送json格式的数据
            StringEntity myEntity = new StringEntity(context,
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
                        if (null != entity) {
                            String result = EntityUtils.toString(entity);
                            //根据字符串生成JSON对象
                            JSONObject resultObj = JSONObject.fromObject(result);
                            return resultObj;
                        } else {
                            return null;
                        }
                    } else {
                        throw new ClientProtocolException(WxUtil.ESSOR_MESAGE + status);
                    }
                }

            };
            //返回的json对象
            JSONObject responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println(responseBody.toString());
            result = responseBody;
            httpclient.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject sendReqMsg(String jsonContext) {
        //消息json格式
        JSONObject result = null;
        //获得token
        String token = getTokenFromWx();
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://qyapi.weixin.qq.com" +
                    "/cgi-bin/message/send?access_token=" + token);
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
                        if (null != entity) {
                            String result = EntityUtils.toString(entity);
                            //根据字符串生成JSON对象
                            JSONObject resultObj = JSONObject.fromObject(result);
                            return resultObj;
                        } else {
                            return null;
                        }
                    } else {
                        throw new ClientProtocolException(WxUtil.ESSOR_MESAGE + status);
                    }
                }

            };
            //返回的json对象
            JSONObject responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println(responseBody.toString());
            result = responseBody;
            httpclient.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从微信获得access_token
     *
     * @return
     */
    public String getTokenFromWx() {
        //微信企业号标识
        String corpid = MESSAGE_CORPID;
        //企业公众号 密钥
        String corpsecret = MESSAGE_SECRET;
        //获取的标识
        String token = "";

        //1.判断token是否存在，不存在直接申请
        //2.判断时间是否过期，（过期>=7200秒）申请,否则不用请求直接返回以后的token
        if (null == access_token || "".equals(access_token) ||
                (new Date().getTime() - access_token_date.getTime() >= (accessTokenInvalidTime - 200L) * 1000L)) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                //利用get形式获得token
                HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="
                        + corpid + "&corpsecret=" + corpsecret);
                ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {
                    public JSONObject handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                        int statusCode = response.getStatusLine().getStatusCode();
                        if (statusCode >= 200 && statusCode < 300) {
                            HttpEntity entity = response.getEntity();
                            if (null != entity) {
                                String result = EntityUtils.toString(entity);
                                //根据字符串生成JSON对象
                                JSONObject resultObj = JSONObject.fromObject(result);
                                return resultObj;
                            } else {
                                return null;
                            }
                        } else {
                            throw new ClientProtocolException(WxUtil.ESSOR_MESAGE + statusCode);
                        }
                    }
                };
                //返回的json对象
                JSONObject responseBody = httpClient.execute(httpGet, responseHandler);
                if (null != responseBody) {
                    token = (String) responseBody.get("access_token");//返回的token
                }
                httpClient.close();
                //设置全局变量
                access_token = token;
                access_token_date = new Date();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            token = access_token;
        }

        return token;
    }
    /**
     * 图片永久上传到服务器
     * @param fileType 文件类型 image
     * @param filePath 文件路径
     * @return JSONObject
     * @throws Exception
     */
    public  static JSONObject sendImageForever(String fileType, String filePath)  {
        try{
            String result = null;
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new IOException("文件不存在");
            }
            String token= new WxUtil().getTokenFromWx();
            /**
             * 第一部分
             */
//	        //获得临时素材media_id
//	        URL urlObj = new URL("https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token="+ token + "&type="+fileType+"");

//            //获得永久素材mediaId
            URL urlObj = new URL("https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+ token
                    + "&type="+fileType+"");


            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            // 设置边界
            String boundary = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ boundary);
            // 请求正文信息
            // 第一部分：
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // 必须多两道线
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"media\";id=\"media\";filename=\""+ file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            // 结尾部分
            byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            try {
                // 定义BufferedReader输入流来读取URL的响应
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    buffer.append(line);
                }
                if(result==null){
                    result = buffer.toString();
                }
            } catch (IOException e) {
                System.out.println("发送POST请求出现异常！" + e);
                e.printStackTrace();
                throw new IOException("数据读取异常");
            } finally {
                if(reader!=null){
                    reader.close();
                }
            }
            JSONObject jsonObj =JSONObject.fromObject(result);
            System.out.println(jsonObj);
            return jsonObj;
        }catch (Exception e) {
            return null;
        }
    }
    public  static JSONObject sendImageTemp(String fileType, String filePath)  {
        try{
            String result = null;
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new IOException("文件不存在");
            }
            String token= new WxUtil().getTokenFromWx();
            /**
             * 第一部分
             */
	        //获得临时素材media_id
	        URL urlObj = new URL("https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token="+ token + "&type="+fileType+"");

////            //获得永久素材mediaId
//            URL urlObj = new URL("https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+ token
//                    + "&type="+fileType+"");


            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            // 设置边界
            String boundary = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ boundary);
            // 请求正文信息
            // 第一部分：
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // 必须多两道线
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"media\";id=\"media\";filename=\""+ file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            // 结尾部分
            byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            try {
                // 定义BufferedReader输入流来读取URL的响应
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    buffer.append(line);
                }
                if(result==null){
                    result = buffer.toString();
                }
            } catch (IOException e) {
                System.out.println("发送POST请求出现异常！" + e);
                e.printStackTrace();
                throw new IOException("数据读取异常");
            } finally {
                if(reader!=null){
                    reader.close();
                }
            }
            JSONObject jsonObj =JSONObject.fromObject(result);
            System.out.println(jsonObj);
            return jsonObj;
        }catch (Exception e) {
            return null;
        }
    }

}
