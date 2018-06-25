package org.andrew.commons.utils.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by longshuzhen on 2017/7/5。
 */
public class HttpRequester {

    private static final Logger log = LoggerFactory.getLogger(HttpRequester.class);

    private static final int timeout = 45000;

    public String sendPost(String url, Map<String, String> params) {
        return send(url, "POST", params);
    }

    public String sendGet(String url, Map<String, String> params) {
        return send(url, "GET", params);
    }

    private String send(String url, String method, Map<String, String> params) {
        StringBuffer temp = new StringBuffer();
        HttpMethodBase httpMethod = null;
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
            if ("GET".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (params != null) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        sb = sb.length() > 0 ? sb.append("&").append(
                            URLEncoder.encode(entry.getKey(), "UTF-8")).append("=").append(
                            URLEncoder.encode(entry.getValue(), "UTF-8")) : sb.append(
                            URLEncoder.encode(entry.getKey(), "UTF-8")).append("=").append(
                            URLEncoder.encode(entry.getValue(), "UTF-8"));
                    }
                }
                if (StringUtils.isNotBlank(sb.toString())) {
                    url = url + "?" + sb.toString();
                }
                log.info("支付网关订单查询接口请求url" + url);
                httpMethod = new GetMethod(url);
            } else {
                Utf8PostMethod postMethod = new Utf8PostMethod(url);
                if (params != null) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        //postMethod.setParameter(varName, params.get(varName));
                        String value = StringUtils.trimToNull(entry.getValue());
                        postMethod.setParameter(entry.getKey(), value);
                    }
                }
                httpMethod = postMethod;
            }
            log.debug(httpMethod.getURI().toString());
            int code = httpClient.executeMethod(httpMethod);
            if (code != HttpStatus.SC_OK) {
                throw new RuntimeException("服务器错误：" + code);
            }
            InputStream in = httpMethod.getResponseBodyAsStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (SocketTimeoutException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            if (httpMethod != null) {
                httpMethod.releaseConnection();
            }
        }
        return temp.toString();
    }
}
