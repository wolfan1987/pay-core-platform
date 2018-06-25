package org.andrew.commons.utils.http;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Http接口工具类。
 *
 * @author andrewliu on 2017-7-21
 */
public class HttpUtil {

    private static final String URL_PARAM_CONNECT_FLAG = "&";
    private static final int    SIZE                   = 1024 * 1024;
    private static       Logger logger                 = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * GET METHOD。
     *
     * @param strUrl String
     * @param map    Map
     * @return List
     * @throws IOException 异常。
     */
    public static String urlGet(String strUrl, Map map) throws IOException {
        String params = getUrlParam(map);
        String strTotalUrl;
        if (!strUrl.contains("?")) {
            strTotalUrl = StringUtils.isBlank(params) ? strUrl : (strUrl + "?" + params);
        } else {
            strTotalUrl = StringUtils.isBlank(params) ? strUrl : (strUrl + "&" + params);
        }
        logger.info("Get Url:{}", strTotalUrl);
        URL url = new URL(strTotalUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setFollowRedirects(true);
        con.setRequestMethod("GET");
        InputStream is;
        int status = con.getResponseCode();
        logger.info("响应状态码：{}", status);
        if (status == 200) {
            is = con.getInputStream();
        } else {
            is = con.getErrorStream();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"), SIZE);
        StringBuilder result = new StringBuilder();
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            } else {
                result.append(line);
            }
        }
        in.close();
        is.close();
        con.disconnect();
        return result.toString();
    }

    /**
     * POST METHOD。
     *
     * @param strUrl String
     * @param map    Map
     * @return List 。
     * @throws IOException 异常。
     */
    public static String urlPost(String strUrl, Map map) throws IOException {
        String params;
        params = getUrlParam(map);
        String totalUrl;
        if (!strUrl.contains("?")) {
            totalUrl = strUrl + (StringUtils.isBlank(params) ? "" : "?" + params);
        } else {
            totalUrl = strUrl + (StringUtils.isBlank(params) ? "" : "&" + params);
        }
        logger.info("Post Url:{}", totalUrl);
        URL url = new URL(strUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setAllowUserInteraction(false);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
        con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        con.getOutputStream().write(params.getBytes("UTF-8"));
        con.getOutputStream().flush();
        con.getOutputStream().close();
        InputStream is = con.getInputStream();
        BufferedReader bin = new BufferedReader(new InputStreamReader(is, "UTF-8"), SIZE);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = bin.readLine();
            if (line == null) {
                break;
            } else {
                stringBuilder.append(line);
            }
        }
        bin.close();
        is.close();
        con.disconnect();
        return stringBuilder.toString();
    }


    /**
     * POST METHOD。
     *
     * @param strUrl String
     * @param params params
     * @throws IOException 异常。
     */
    public static String jsonPost(String strUrl, String params) throws IOException {
        URL url = new URL(strUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setAllowUserInteraction(false);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
        con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //con.setRequestProperty("appid", appid);
        con.getOutputStream().write(params.getBytes("UTF-8"));
        con.getOutputStream().flush();
        con.getOutputStream().close();
        InputStream is;
        int status = con.getResponseCode();
        logger.info("响应状态码：" + status);
        if (status == 200) {
            is = con.getInputStream();
        } else {
            is = con.getErrorStream();
        }
        BufferedReader bin = new BufferedReader(new InputStreamReader(is, "UTF-8"), SIZE);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = bin.readLine();
            if (line == null) {
                break;
            } else {
                stringBuilder.append(line);
            }
        }
        bin.close();
        is.close();
        con.disconnect();
        return stringBuilder.toString();
    }


    /**
     * POST METHOD。
     *
     * @param strUrl String
     * @param params params
     * @throws IOException 异常。
     */
    public static String jsonPost(String strUrl, String params, boolean encode) throws IOException {
        if (encode) {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
            con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
            con.setRequestProperty("Content-Type",
                                   "application/x-www-form-urlencoded;charset=utf-8");
            con.getOutputStream().write(URLEncoder.encode(params, "UTF-8").getBytes("UTF-8"));
            con.getOutputStream().flush();
            con.getOutputStream().close();
            InputStream is;
            int status = con.getResponseCode();
            logger.info("响应状态码：" + status);
            if (status == 200) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }
            BufferedReader bin = new BufferedReader(new InputStreamReader(is, "UTF-8"), SIZE);
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String line = bin.readLine();
                if (line == null) {
                    break;
                } else {
                    stringBuilder.append(line);
                }
            }
            bin.close();
            is.close();
            con.disconnect();
            return stringBuilder.toString();
        } else {
            return jsonPost(strUrl, params);
        }
    }


    /**
     * 组装请求参数。
     *
     * @param map Map
     * @return String
     */
    private static String getUrlParam(Map map) {
        if (null == map || map.keySet().size() == 0) {
            return "";
        }
        StringBuilder url = new StringBuilder();
        Set keys = map.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            String key = String.valueOf(i.next());
            if (map.containsKey(key)) {
                Object val = map.get(key);
                String str = val != null ? val.toString() : "";
                try {
                    str = URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    logger.error("拼接参数错误：{}", ex);
                }
                //System.out.print(key+":"+val+"\t");
                url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strUrl = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals("" + strUrl.charAt(strUrl.length() - 1))) {
            strUrl = strUrl.substring(0, strUrl.length() - 1);
        }
        return (strUrl);
    }

    /**
     * 请求方法。
     *
     * @param strUrl 地址
     * @param params 参数
     * @return 返回值
     * @throws IOException 异常
     */
    public static String jsonPostToScan(String strUrl, String params) throws IOException {
        URL url = new URL(strUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setAllowUserInteraction(false);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
        con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
        con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        con.getOutputStream().write(params.getBytes("utf-8"));
        con.getOutputStream().flush();
        con.getOutputStream().close();
        InputStream is;
        int status = con.getResponseCode();
        logger.info("响应状态码：" + status);
        if (status == 200) {
            is = con.getInputStream();
        } else {
            is = con.getErrorStream();
        }
        BufferedReader bin = new BufferedReader(new InputStreamReader(is, "UTF-8"), SIZE);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = bin.readLine();
            if (line == null) {
                break;
            } else {
                stringBuilder.append(line);
            }
        }
        bin.close();
        is.close();
        con.disconnect();
        return stringBuilder.toString();
    }

}
