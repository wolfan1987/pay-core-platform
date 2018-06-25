package org.andrew.commons.utils.mail;

import java.io.Serializable;

/**
 * 邮件对象。
 *
 * @author andrewliu
 */
public class MailModel implements Serializable {
    /**
     * 发件人。
     */
    private String from = "dev@chinaexpresscard.com";
    /**
     * 收件人。
     */
    private String[] to;
    /**
     * 抄送人（隐藏邮件信息）。
     */
    private String[] bcc;
    /**
     * 抄送人。
     */
    private String[] cc;

    /**
     * 邮件主题。
     */
    private String title;
    /**
     * 邮件内容。
     */
    private String text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
