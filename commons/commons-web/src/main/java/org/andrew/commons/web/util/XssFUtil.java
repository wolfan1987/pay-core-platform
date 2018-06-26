package org.andrew.commons.web.util;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.Writer;
import java.util.Map;

/**
 * 分步生成EXCEL工具类。
 *
 * @author create by andrewliu on 2017-5-23
 */
public class XssFUtil {
    /**
     * 状态码。
     */
    private String                     code;
    /**
     * 状态文字。
     */
    private String                     msg;
    /**
     * 行号。
     */
    private int                        num;
    /**
     * 表格样式。
     */
    private Map<String, XSSFCellStyle> styles;
    /**
     * XML临时文件输出流。
     */
    private Writer                     writer;
    /**
     * XML临时文件工作空间。
     */
    private XSSFSheet                  sheet;
    /**
     * XML临时文件。
     */
    private File                       tempFile;

    private ApplicationContext applicationContext;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Map<String, XSSFCellStyle> getStyles() {
        return styles;
    }

    public void setStyles(Map<String, XSSFCellStyle> styles) {
        this.styles = styles;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public File getTempFile() {
        return tempFile;
    }

    public void setTempFile(File tempFile) {
        this.tempFile = tempFile;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
