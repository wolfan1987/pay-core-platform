package org.andrew.commons.web.util.listener;

/**
 * 上传文件进度条对象。
 * Created by andrewliu on 2017/7/10.
 */
public class Progress {

    private long bytesRead;
    private long contentLength;
    private long items;

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Progress [bytesRead=" + bytesRead + ", contentLength=" + contentLength +
               ", items=" + items + "]";
    }

}
