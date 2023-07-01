package com.example.demotest.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


public class SampleCode {
    public static void main(String[] args) {

        Signer signer = new Signer("lIh0BcZraY24XG9maDY8R4nD", "CqBg9ZpEkgc2HnKhAFowPEhhetDXmkm8");
        // post
        String response = null;
        try {
            response = createDeviceSpaceSample(signer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("createDeviceSpaceSample response: " + response);
        //将json字符串转化为JSONObject
        JSONObject jsonObject = JSONObject.parseObject(response);
        //通过getString("")分别取出里面的信息
        String code = jsonObject.getString("code");
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray faceInfoArray = data.getJSONArray("face_info");
        String mask = null;
        if (faceInfoArray.size() > 0) {
            for (int i = 0; i < faceInfoArray.size(); i++) {
                JSONObject faceInfo = faceInfoArray.getJSONObject(i);
                mask = faceInfo.getString("mask");
            }
        }
        System.out.println(mask);
    }

    /**
     * 创建设备空间 POST接口示例
     *
     * @param signer
     * @return
     */

    public static String createDeviceSpaceSample(Signer signer) throws UnsupportedEncodingException {
        String API_CREATE_DEVICESPACE = "/openapi/v1/faces/detect";
        SortedMap<String, String> headers = new TreeMap<String, String>();
        SortedMap<String, String> params = new TreeMap<String, String>();
        headers.put(URLEncoder.encode("content-type", "UTF-8"), URLEncoder.encode("application/json", "UTF-8"));
        headers.put(Settings.HOST, "openapi.perf.fd.horizon.ai");
        try {
            String authorization = signer.Sign(RequestParam.HTTP_METHOD_POST, API_CREATE_DEVICESPACE, params, headers);
            String path = "http://openapi.perf.fd.horizon.ai/openapi/v1/faces/detect?authorization=" + authorization;
            URL url = new URL(path); // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // connection.setRequestProperty("设置请求头key", "请求头value");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(RequestParam.HTTP_METHOD_POST);
            connection.setRequestProperty("authorization", authorization);
            connection.setRequestProperty(URLEncoder.encode("content-type", "UTF-8"), URLEncoder.encode("application/json", "UTF-8"));
            connection.setRequestProperty(Settings.HOST, "openapi.perf.fd.horizon.ai");
            connection.connect();
            // 修改为自己要创建的设备空间信息
            //String body = "{\"image_type\":0,\"image_url\":\"https://vasm-qa-public.obs.cn-east-2.myhwclouds.com/a992dc56fcdb46e48ab0bd5505224021.png\",\"attributes\":\"mask\"}";
            String body = "{\"image_type\":0,\"image_url\":\"https://vasm-qa-public.obs.cn-east-2.myhwclouds.com/56a11b14f38846848322d997ac015417.jpeg\",\"attributes\":\"mask\"}";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            return sb.toString();
            //return doPost(path,params,authorization);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求失败!");
            return "error";
        }
    }

    public static String doPost(String url, SortedMap<String, String> paramMap, String authorization) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("content-type", "application/json");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
