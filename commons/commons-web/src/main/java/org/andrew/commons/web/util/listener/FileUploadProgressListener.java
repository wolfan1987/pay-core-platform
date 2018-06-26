package org.andrew.commons.web.util.listener;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * 文件上传监听。
 * Created by andrewliu on 2017/7/10.
 */
@Component
public class FileUploadProgressListener implements ProgressListener {

    private HttpSession session;

    /**
     * 设置session。
     * @param session 请求session
     */
    public void setSession(HttpSession session) {
        this.session = session;
        Progress status = new Progress();
        session.setAttribute("status", status);
    }

    @Override
    public void update(long bytesRead, long contentLength, int items) {
        Progress status = (Progress) session.getAttribute("status");
        status.setBytesRead(bytesRead);
        status.setContentLength(contentLength);
        status.setItems(items);
    }

}
