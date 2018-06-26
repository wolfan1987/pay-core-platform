package org.andrew.commons.web.util;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * XML方式生成EXCEL文件。
 * 主要用途：只适合生成列表数据EXCEL，提高效率
 *
 * @author andrewliu on 2017年5月23日
 * @version 1.0.0
 */
public class XssfExcelHandler {
    /**
     * 编码集。
     */
    private static final String XML_ENCODING          = "UTF-8";
    /**
     * 生成EXCEL文件路径。
     */
    private static final String REPORT_OUTPUT         =
        File.separator + "report" + File.separator + "output";
    /**
     * 生成的模板临时文件名称。
     */
    private static final String TEMPLATE              = "template.xlsx";
    /**
     * 导出EXCEL结果。
     */
    private static final String EXPORT_SUCCESS_CODE   = "1";
    private static final String EXPORT_SUCCESS_MSG    = "成功";
    private static final String TITLE_NULL_CODE       = "2";
    private static final String TITLE_NULL_MSG        = "标题数组不能为空";
    private static final String CONTEXT_NULL_CODE     = "3";
    private static final String CONTEXT_NULL_MSG      = "条件范围内无数据可导";
    private static final String EXPORT_EXCEPTION_CODE = "4";
    private static final String EXPORT_EXCEPTION_MSG  = "导出出错,请联系系统管理员";

    private static Logger log = LoggerFactory.getLogger(XssfExcelHandler.class);

    private String   sheetRef;
    /**
     * 分步生成EXCEL工具对象。
     */
    private XssFUtil xssfUtil;

    /**
     * 分步生成EXCEL---生成表头。
     *
     * @param headerArray 表头数组
     * @param fileName    生成文件名称
     * @return 对象
     */
    public XssFUtil headerExcel(String[] headerArray, String fileName, ApplicationContext context) {

        this.xssfUtil = new XssFUtil();

        if (headerArray == null || headerArray.length == 0) {
            xssfUtil.setCode(TITLE_NULL_CODE);
            xssfUtil.setMsg(TITLE_NULL_MSG);
            return xssfUtil;
        }
        try {
            File tmp = File.createTempFile("sheet", ".xml");
            Writer fw = new OutputStreamWriter(new FileOutputStream(tmp), XML_ENCODING);
            XSSFWorkbook wb = new XSSFWorkbook();
            Map<String, XSSFCellStyle> styles = createStyles(wb);  //保存文件单元格样式
            FileOutputStream os = new FileOutputStream(TEMPLATE);//保存为默认文件
            wb.write(os);
            os.close();
            int rowNum = generateHeader(headerArray, styles, fw);//生成表头并返回下一个行号
            xssfUtil.setApplicationContext(context);
            xssfUtil.setNum(rowNum);
            xssfUtil.setWriter(fw);
            xssfUtil.setStyles(styles);
            XSSFSheet sheet = wb.createSheet(fileName);
            xssfUtil.setSheet(sheet);
            xssfUtil.setCode(EXPORT_SUCCESS_CODE);
            xssfUtil.setMsg(EXPORT_SUCCESS_MSG);
            xssfUtil.setTempFile(tmp);
        } catch (Exception exception) {
            xssfUtil.setCode(EXPORT_EXCEPTION_CODE);
            xssfUtil.setMsg(EXPORT_EXCEPTION_MSG);
            log.error("", exception);
        }
        return xssfUtil;
    }

    /**
     * 分步生成EXCEL---填充数据内容。
     *
     * @param dataList 数据集合
     * @return 对象
     */
    public XssFUtil fillExcel(List<List<Object>> dataList) {
        if (xssfUtil != null && xssfUtil.getCode().equals(EXPORT_SUCCESS_CODE)) {
            if (dataList == null || dataList.size() == 0) {
                xssfUtil.setCode(CONTEXT_NULL_CODE);
                xssfUtil.setMsg(CONTEXT_NULL_MSG);
                return xssfUtil;
            }
            try {
                int nextRowNum = generateContext(dataList, xssfUtil.getStyles(), xssfUtil.getNum(),
                                                 xssfUtil.getWriter());
                xssfUtil.setNum(nextRowNum);//记录下一次需要开始生成的行号
            } catch (Exception exception) {
                xssfUtil.setCode(EXPORT_EXCEPTION_CODE);
                xssfUtil.setMsg(EXPORT_EXCEPTION_MSG);
                log.error("", exception);
            }
        } else {
            xssfUtil.setCode(EXPORT_EXCEPTION_CODE);
            xssfUtil.setMsg(EXPORT_EXCEPTION_MSG);
        }
        return xssfUtil;
    }

    /**
     * 分步生成EXCEL---生成EXCEL。
     *
     * @return 结果集合
     */
    public Map<String, String> completeExcel() {
        Map<String, String> reMap = new HashMap<>();
        if (xssfUtil != null && (EXPORT_SUCCESS_CODE).equals(xssfUtil.getCode())) {
            try {
                Resource outs = xssfUtil.getApplicationContext().getResource(REPORT_OUTPUT);
                File outPath = outs.getFile();
                if (!outPath.exists()) {
                    outPath.mkdirs();
                }
                Writer fw = xssfUtil.getWriter();
                XSSFSheet sheet = xssfUtil.getSheet();
                String fileName = sheet.getSheetName();
                reMap.put("fileName", fileName + ".xlsx");
                sheetRef = sheet.getPackagePart().getPartName().getName();
                SpreadSheetWriter sw = new SpreadSheetWriter(fw);
                sw.endSheet();
                fw.close();
                //临时文件名称
                String temFileNmae = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(
                    System.currentTimeMillis());
                reMap.put("tempName", temFileNmae + ".xlsx");
                FileOutputStream out = new FileOutputStream(
                    outPath.getAbsoluteFile() + "/" + temFileNmae + ".xlsx");
                File temp = xssfUtil.getTempFile();
                substitute(new File(TEMPLATE), temp, sheetRef.substring(1), out);
                out.close();
                temp.delete();
                reMap.put("code", EXPORT_SUCCESS_CODE);
                reMap.put("msg", EXPORT_SUCCESS_MSG);
            } catch (Exception exception) {
                reMap.put("code", EXPORT_EXCEPTION_CODE);
                reMap.put("msg", EXPORT_EXCEPTION_MSG);
                log.error("", exception);
            }
        } else {
            reMap.put("code", EXPORT_EXCEPTION_CODE);
            reMap.put("msg", EXPORT_EXCEPTION_MSG);
        }
        return reMap;
    }

    /**
     * 根据数据生成EXCEL。
     *
     * @param headerArray 表头标题
     * @param dataList    需要导出的数据集合
     * @param fileName    导出文件名称（不需要填写后缀，后缀默认为.xlsx）
     * @return 结果集合
     */
    public Map<String, String> createExcel(
        String[] headerArray, List<List<Object>> dataList, String fileName,
        ApplicationContext context) {

        Map<String, String> reMap = new HashMap<>();

        if (headerArray == null || headerArray.length == 0) {
            reMap.put("code", TITLE_NULL_CODE);
            reMap.put("msg", TITLE_NULL_MSG);
            return reMap;
        }

        if (dataList == null || dataList.size() == 0) {
            reMap.put("code", CONTEXT_NULL_CODE);
            reMap.put("msg", CONTEXT_NULL_MSG);
            return reMap;
        }

        try {
            Resource outs = context.getResource(REPORT_OUTPUT);
            File outPath = outs.getFile();
            if (!outPath.exists()) {
                outPath.mkdirs();
            }
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(fileName);
            sheetRef = sheet.getPackagePart().getPartName().getName();
            Map<String, XSSFCellStyle> styles = createStyles(wb);  //保存文件单元格样式
            FileOutputStream os = new FileOutputStream(TEMPLATE);//保存为默认文件
            wb.write(os);
            os.close();
            File tmp = File.createTempFile("sheet", ".xml");
            Writer fw = new OutputStreamWriter(new FileOutputStream(tmp), XML_ENCODING);
            //生成表头
            int nextRowNum = generateHeader(headerArray, styles, fw);
            //生成内容
            generateContext(dataList, styles, nextRowNum, fw);
            SpreadSheetWriter sw = new SpreadSheetWriter(fw);
            sw.endSheet();
            fw.close();
            //临时文件名称
            String temFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(
                System.currentTimeMillis());
            reMap.put("tempName", temFileName + ".xlsx");
            FileOutputStream out = new FileOutputStream(
                outPath.getAbsoluteFile() + "/" + temFileName + ".xlsx");
            substitute(new File(TEMPLATE), tmp, sheetRef.substring(1), out);
            out.close();
            tmp.delete();
            reMap.put("fileName", fileName + ".xlsx");
            reMap.put("code", EXPORT_SUCCESS_CODE);
            reMap.put("msg", EXPORT_SUCCESS_MSG);
        } catch (Exception exception) {
            reMap.put("code", EXPORT_EXCEPTION_CODE);
            reMap.put("msg", EXPORT_EXCEPTION_MSG);
            log.error("", exception);
        }
        return reMap;
    }

    /**
     * 根据数据生成EXCEL。
     *
     * @param headerArray 表头标题
     * @param fileName    导出文件名称（不需要填写后缀，后缀默认为.xlsx）
     * @return 结果集合
     */
    public Map<String, String> createExcel(
        String[] headerArray, Map<Integer, List<List<Object>>> dataMap, String fileName,
        ApplicationContext context) {

        Map<String, String> reMap = new HashMap<>();

        if (headerArray == null || headerArray.length == 0) {
            reMap.put("code", TITLE_NULL_CODE);
            reMap.put("msg", TITLE_NULL_MSG);
            return reMap;
        }

        if (dataMap == null || dataMap.size() == 0) {
            reMap.put("code", CONTEXT_NULL_CODE);
            reMap.put("msg", CONTEXT_NULL_MSG);
            return reMap;
        }

        try {
            Resource outs = context.getResource(REPORT_OUTPUT);
            File outPath = outs.getFile();
            if (!outPath.exists()) {
                outPath.mkdirs();
            }
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(fileName);
            sheetRef = sheet.getPackagePart().getPartName().getName();
            FileOutputStream os = new FileOutputStream(TEMPLATE);//保存为默认文件
            Map<String, XSSFCellStyle> styles = createStyles(wb);  //保存文件单元格样式
            wb.write(os);
            os.close();
            File tmp = File.createTempFile("sheet", ".xml");
            Writer fw = new OutputStreamWriter(new FileOutputStream(tmp), XML_ENCODING);
            //生成表头
            int nextRowNum = generateHeader(headerArray, styles, fw);
            //生成内容
            for (Entry<Integer, List<List<Object>>> entry : dataMap.entrySet()) {
                List<List<Object>> dataList = entry.getValue();
                if (dataList != null && dataList.size() > 0) {
                    nextRowNum = generateContext(dataList, styles, nextRowNum, fw);
                }
            }
            SpreadSheetWriter sw = new SpreadSheetWriter(fw);
            sw.endSheet();
            fw.close();
            //临时文件名称
            String temFileNmae = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(
                System.currentTimeMillis());
            FileOutputStream out = new FileOutputStream(
                outPath.getAbsoluteFile() + "/" + temFileNmae + ".xlsx");
            substitute(new File(TEMPLATE), tmp, sheetRef.substring(1), out);
            tmp.delete();
            out.close();
            reMap.put("tempName", temFileNmae + ".xlsx");
            reMap.put("fileName", fileName + ".xlsx");
            reMap.put("code", EXPORT_SUCCESS_CODE);
            reMap.put("msg", EXPORT_SUCCESS_MSG);
        } catch (Exception exception) {
            reMap.put("code", EXPORT_EXCEPTION_CODE);
            reMap.put("msg", EXPORT_EXCEPTION_MSG);
            log.error("", exception);
        }
        return reMap;
    }

    /**
     * 生成Excel文件的样式。
     *
     * @param wb 指定的Excel的XSSFWorkbook对象
     * @return 结果集合
     */
    private Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb) {
        XSSFCellStyle style1 = wb.createCellStyle();
        XSSFFont fontNormal = wb.createFont();
        fontNormal.setFontName("微软雅黑");
        fontNormal.setFontHeight(9);
        style1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        style1.setFont(fontNormal);
        Map<String, XSSFCellStyle> styles = new HashMap<>();
        styles.put("normal", style1);

        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        XSSFDataFormat fmt = wb.createDataFormat();
        style2.setDataFormat(fmt.getFormat("0.0%"));
        styles.put("percent", style2);

        XSSFCellStyle style3 = wb.createCellStyle();
        style3.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style3.setDataFormat(fmt.getFormat("0.0X"));
        styles.put("coeff", style3);

        XSSFCellStyle style4 = wb.createCellStyle();
        style4.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style4.setDataFormat(fmt.getFormat("$#,##0.00"));
        styles.put("currency", style4);

        XSSFCellStyle style5 = wb.createCellStyle();
        style5.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style5.setDataFormat(fmt.getFormat("yyyy-mm-dd hh:mm:ss"));
        styles.put("date", style5);

        XSSFCellStyle style6 = wb.createCellStyle();
        XSSFFont headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontName("微软雅黑");
        style6.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style6.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style6.setFont(headerFont);
        styles.put("header", style6);

        XSSFFont titleFont = wb.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeight((short) 300);
        titleFont.setFontName("微软雅黑");
        //titleFont.setColor((short)10);
        XSSFCellStyle style7 = wb.createCellStyle();
        style7.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style7.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style7.setFont(titleFont);
        style7.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        styles.put("title", style7);

        return styles;
    }

    /**
     * 生成保存数据的sheet1.xml文件， 没有对xml中的特殊字符进行处理。
     *
     * @param out    sheet1.xml文件的Writer对象
     * @param styles 单元格格式
     * @throws Exception 异常
     */
    public int generateHeader(String[] headerArray, Map<String, XSSFCellStyle> styles, Writer out)
        throws Exception {
        SpreadSheetWriter sw = new SpreadSheetWriter(out);
        sw.beginSheet();
        if (headerArray == null || headerArray.length == 0) {
            return 0;
        }
        sw.insertRow(0);
        for (int h = 0; h < headerArray.length; h++) {
            sw.createCell(h, headerArray[h], styles.get("header").getIndex());
        }
        sw.endRow();
        return 1;
    }

    /**
     * 生成保存数据的sheet1.xml文件， 没有对xml中的特殊字符进行处理。
     *
     * @param out    sheet1.xml文件的Writer对象
     * @param list   数据
     * @param styles 单元格格式
     * @throws Exception 异常
     */
    public int generateContext(
        List<List<Object>> list, Map<String, XSSFCellStyle> styles, int nextRowNum, Writer out)
        throws Exception {
        SpreadSheetWriter sw = new SpreadSheetWriter(out);
        if (list != null && list.size() > 0) {
            for (List<Object> listObject : list) {
                sw.insertRow(nextRowNum);
                for (int col = 0; col < listObject.size(); col++) {
                    sw.createCell(col, listObject.get(col), styles.get("normal").getIndex());
                }
                sw.endRow();
                nextRowNum++;
            }
        }
        return nextRowNum;
    }

    /**
     * 替换sheet1.xml的函数。
     *
     * @param zipfile 目标Excel文件
     * @param tmpfile sheet1.xml文件
     * @param entry   sheet1.xml文件路径
     * @param out     要生成的最终的Excel文件的输出流
     * @throws IOException 异常
     */
    public void substitute(File zipfile, File tmpfile, String entry, OutputStream out)
        throws IOException {
        ZipFile zip = new ZipFile(zipfile);

        ZipOutputStream zos = new ZipOutputStream(out);
        Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
        while (en.hasMoreElements()) {
            ZipEntry ze = en.nextElement();
            if (!ze.getName().equals(entry)) {
                zos.putNextEntry(new ZipEntry(ze.getName()));
                InputStream is = zip.getInputStream(ze);
                copyStream(is, zos);
                is.close();
            }
        }
        zos.putNextEntry(new ZipEntry(entry));
        InputStream is = new FileInputStream(tmpfile);
        copyStream(is, zos);
        is.close();
        zos.close();
        zip.close();
    }

    /**
     * 将指定的Excel中的sheet1.xml文件替换掉。
     *
     * @param in  输入流
     * @param out 输出流
     * @throws IOException 异常
     */
    private void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] chunk = new byte[1024];
        int count;
        while ((count = in.read(chunk)) >= 0) {
            out.write(chunk, 0, count);
        }
    }
}
