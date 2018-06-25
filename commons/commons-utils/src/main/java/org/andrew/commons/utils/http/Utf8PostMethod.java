package org.andrew.commons.utils.http;

import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Created by longshuzhen on 2017/7/5。
 */
public class Utf8PostMethod extends PostMethod {
    public Utf8PostMethod(String url) {
        super(url);
    }

    @Override
    public String getRequestCharSet() {
        return "UTF-8";
    }
}
