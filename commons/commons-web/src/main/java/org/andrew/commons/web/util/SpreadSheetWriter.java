package org.andrew.commons.web.util;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * XML工具类。
 *
 * @author andrewliu
 * @version 1.0.0
 */
public class SpreadSheetWriter {
    private final Writer out;
    private       int    rowNum;   //行的序号
    /**
     * 编码集。
     */
    private static final String XML_ENCODING = "UTF-8";

    public SpreadSheetWriter(Writer out) {
        this.out = out;
    }

    /**
     * 开始一个Excel的表。
     *
     * @throws IOException 异常
     */
    public void beginSheet() throws IOException {
        out.write("<?xml version='1.0' encoding='" + XML_ENCODING + "' standalone='yes'?>" +
                  //  "<worksheet xmlns='http://schemas.openxmlformats.org/spreadsheetml/2006/main'>" );
                  "<worksheet xmlns='http://schemas.openxmlformats.org/spreadsheetml/2006/main' " +
                  "xmlns:r='http://schemas.openxmlformats.org/officeDocument/2006/relationships' " +
                  "xmlns:mc='http://schemas.openxmlformats.org/markup-compatibility/2006' " +
                  "mc:Ignorable='x14ac' xmlns:x14ac='http://schemas.microsoft.com/office/spreadsheetml/2009/9/ac'>");
        out.write(
            "<sheetViews><sheetView tabSelected='1' workbookViewId='0'></sheetView></sheetViews>");
        out.write("<sheetFormatPr defaultRowHeight='15.5' x14ac:dyDescent='0.15'/>");
        out.write("<sheetData>\n");

    }

    /**
     * 结束一个Excel的表。
     *
     * @throws IOException 异常
     */
    public void endSheet() throws IOException {
        out.write("</sheetData>");
        out.write("<phoneticPr fontId='5' type='noConversion'/>");
        out.write("<pageMargins left='0.7' right='0.7' top='0.75' ");
        out.write("bottom='0.75' header='0.3' footer='0.3'/>");
        out.write("<pageSetup paperSize='0' orientation='portrait' ");
        out.write("horizontalDpi='0' verticalDpi='0' copies='0'/>");
        out.write("</worksheet>");
    }

    /**
     * 开始一行。
     *
     * @param rowNum //行的序号
     * @throws IOException 异常
     */
    public void insertRow(int rowNum) throws IOException {
        out.write("<row r='" + (rowNum + 1) + "'>\n");
        this.rowNum = rowNum;
    }

    /**
     * 结束一行。
     *
     * @throws IOException 异常
     */
    public void endRow() throws IOException {
        out.write("</row>\n");
    }

    /**
     * 创建一个单元格。
     *
     * @param columnIndex 列的序号
     * @param value       单元格的值
     * @param styleIndex  单元格的格式
     * @throws IOException 异常
     */
    public void createCellString(int columnIndex, Object value, int styleIndex) throws IOException {
        if (value != null && value.toString() != null) {
            value = value.toString().replaceAll("&", "&amp;").replaceAll("'", "&apos;").replaceAll(
                "'", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        } else {
            value = "";
        }
        String ref = new CellReference(rowNum, columnIndex).formatAsString();
        out.write("<c r='" + ref + "' t='inlineStr'");
        if (styleIndex != -1) {
            out.write(" s='" + styleIndex + "'");
        }
        out.write(">");
        out.write("<is><t>" + value + "</t></is>");
        out.write("</c>");
    }

    /**
     * 创建一个单元格。
     *
     * @param columnIndex 列的序号
     * @param value       单元格的值
     * @throws IOException 异常
     */
    public void createCell(int columnIndex, Object value, int styleIndex) throws IOException {
        if (value instanceof Double) {
            createCellNumber(columnIndex, ((Double) value).doubleValue(), styleIndex);
        } else if (value instanceof Integer) {
            createCellNumber(columnIndex, ((Integer) value).intValue(), styleIndex);
        } else if (value instanceof Float) {
            createCellNumber(columnIndex, ((Float) value).floatValue(), styleIndex);
        } else if (value instanceof Date) {
            String valueFormat = "";
            if (!"".equals(value)) {
                valueFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
            }
            createCellString(columnIndex, valueFormat, styleIndex);
        } else {
            createCellString(columnIndex, value, styleIndex);
        }
    }

    /**
     * 创建一个单元格。
     *
     * @param columnIndex 列的序号
     * @param value       单元格的值
     * @throws IOException 异常
     */
    public void createCell(int columnIndex, Object value) throws IOException {
        createCell(columnIndex, value, -1);
    }

    /**
     * 创建一个单元格。
     *
     * @param columnIndex 列的序号
     * @param value       单元格的值
     * @param styleIndex  单元格的格式
     * @throws IOException 异常
     */
    public void createCell(int columnIndex, Calendar value, int styleIndex) throws IOException {
        createCell(columnIndex, DateUtil.getExcelDate(value, false), styleIndex);
    }

    /**
     * 创建一个单元格。
     *
     * @param columnIndex 列的序号
     * @param value       单元格的值
     * @param styleIndex  单元格的格式
     * @throws IOException 异常
     */
    public void createCellNumber(int columnIndex, double value, int styleIndex) throws IOException {
        String ref = new CellReference(rowNum, columnIndex).formatAsString();
        out.write("<c r='" + ref + "' t='n'");
        if (styleIndex != -1) {
            out.write(" s='" + styleIndex + "'");
        }
        out.write(">");
        out.write("<v>" + value + "</v>");
        out.write("</c>");
    }


}
