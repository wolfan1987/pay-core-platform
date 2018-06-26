package org.andrew.commons.web.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.andrew.commons.utils.excel.constant.ConstString;
import org.andrew.commons.utils.excel.excel.utils.ExcelImport;
import org.andrew.commons.utils.string.StringUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExcelUtil implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String REPORT_OUTPUT = "/report/output";

    @SuppressWarnings("unused")
    private static final String REPORT_ZIP_OUTPUT = "/report/output/zip/";

    private static final String TEMPLATE_PATH = "/templates";

    private static final String TEMPLAE_PATH = "/templates/xlsx";

    /**
     * Excel 2003。
     */
    private static final String XLS       = "xls";
    /**
     * Excel 2007。
     */
    private static final String XLSX      = "xlsx";
    /**
     * 分隔符。
     */
    private static final String SEPARATOR = "|";

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }


    /**
     * 导出数据最大记录数。
     */
    public static final int maxRecodeDownload = 30000;


    private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    public ExcelUtil() {
    }

    /**
     * 下载模板文件。
     *
     * @param name     名称
     * @param response 响应
     * @param context  上下文
     */
    public static void doDownLoad(
        String name, HttpServletResponse response, ApplicationContext context) {
        try {
            response.reset();
            //String fileName=encodeFilename(name, request);
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + name);// 组装附件名称和格式
            response.resetBuffer();

            Resource resource = context.getResource(TEMPLATE_PATH);
            File path = resource.getFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(path.getAbsoluteFile() + File.separator + name);
            InputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream fos = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            // 弹出下载对话框
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
        } catch (Exception exception) {
            log.error("", exception);
        }
    }

    /**
     * 导出数据时下载数据到本地。
     *
     * @param tempName 临时文件
     * @param name     文件
     * @param response 响应
     * @param context  上下文
     */
    public static void doDownLoad(
        String tempName, String name, HttpServletResponse response, ApplicationContext context) {
        try {
            response.reset();
            //String fileName=encodeFilename(name, request);
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader(
                "Content-disposition", "attachment; filename=" + new String(name.getBytes(),
                                                   "ISO8859-1"));// 组装附件名称和格式
            response.resetBuffer();

            Resource resource = context.getResource(REPORT_OUTPUT);
            File path = resource.getFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(path.getAbsoluteFile() + File.separator + tempName);
            InputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream fos = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            // 弹出下载对话框
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
            file.delete();
        } catch (Exception exception) {
            log.error("", exception);
        }
    }


    /**
     * 下载模板文件。
     *
     * @param name         名称
     * @param realFileName 模板文件名
     * @param response     响应
     * @param context      上下文
     */
    public static void doDownTemplaet(
        String realFileName, String name, HttpServletResponse response,
        ApplicationContext context) {
        try {
            response.reset();
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader(
                "Content-disposition", "attachment; filename=" + new String(name.getBytes(),
                                        "ISO8859-1"));// 组装附件名称和格式
            response.resetBuffer();

            Resource resource = context.getResource(TEMPLAE_PATH);
            File pathDir = resource.getFile();
            if (!pathDir.exists()) {
                pathDir.mkdirs();
            }
            File file = new File(pathDir.getAbsolutePath() + File.separator + realFileName);
            InputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream fos = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            // 弹出下载对话框
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
        } catch (Exception exception) {
            log.error("", exception);
        }
    }


    /**
     * 由Excel文件的Sheet导出至List。
     *
     * @param file     文件
     * @param sheetNum sheet坐标
     * @return excel内容列表
     */
    public static List<String> exportListFromExcel(File file, int sheetNum, int maxCol)
        throws IOException {
        return exportListFromExcel(new FileInputStream(file),
                                   FilenameUtils.getExtension(file.getName()), sheetNum, maxCol);
    }

    /**
     * 由Excel流的Sheet导出至List。
     *
     * @param is            输出流
     * @param extensionName 扩展名称
     * @param sheetNum      sheet坐标
     * @return 内容列表
     * @throws IOException 异常
     */
    public static List<String> exportListFromExcel(
        InputStream is, String extensionName, int sheetNum, int maxCol) throws IOException {

        Workbook workbook = null;

        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return exportListFromExcel(workbook, sheetNum, maxCol);
    }

    /**
     * 由指定的Sheet导出至List。(适用于一般从第三行开始读取数据,第一列和第二列字段不能为空)
     *
     * @param workbook 内容
     * @param sheetNum sheet页坐标
     * @return 文档内容
     */
    private static List<String> exportListFromExcel(Workbook workbook, int sheetNum, int maxCol) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        List<String> list = new ArrayList<String>();
        int minRowIx = sheet.getFirstRowNum();
        int startReadRow = minRowIx + 2; //读取第一二行，是字段名称和注意，所以从第三行开始读
        //  用列数判断是不是指定的導出的模版
        try {
            Row rowSecond = sheet.getRow(minRowIx + 1);
            rowSecond.getCell(6);
            StringBuilder stringBuilder = new StringBuilder();
            if (rowSecond.getCell(7) == null || (rowSecond.getCell(7) != null && !rowSecond.getCell(
                7).getStringCellValue().contains("tid"))) {
                log.debug("ERROR请使用指定的模版！即下载的模板！");
                stringBuilder.append("ERROR请使用指定的模版  即下载的模板");
                list.add(stringBuilder.toString());
                return list;
            }
        } catch (Exception ex) {
            StringBuilder stringBuilder = new StringBuilder();
            log.debug("ERROR请使用指定的模版！即下载的模板！" + ex);
            stringBuilder.append("ERROR请使用指定的模版  即下载的模板 ");
            list.add(stringBuilder.toString());
            return list;
        }

        int maxRowIx = sheet.getLastRowNum();
        Row rowData = sheet.getRow(minRowIx + 1); //获取字段名称
        for (int rowIx = startReadRow; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            if (row != null) {
                StringBuilder sb = new StringBuilder();
                short minColIx = 0;
                short maxColIx = 0;
                if (maxCol == 0) {
                    maxColIx = row.getLastCellNum();
                } else {
                    maxColIx = (short) maxCol;
                }
                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(new Integer(colIx)); //第一列和第二列不能为空
                    CellValue cellValue = evaluator.evaluate(cell);
                    //                    if (cellValue != null) {
                    if (colIx == 0) { //校验第1列数据 终端号
                        if (cell == null || cellValue == null ||
                            StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append("ERROR" + evaluator.evaluate(
                                rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                      SEPARATOR);
                            continue;
                        } else {
                            sb.append(getValue(cellValue, cell) + SEPARATOR);
                        }
                    } else if (colIx == 1) { //校验第2列数据 终端名称
                        if (cell == null || cellValue == null ||
                            StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append("ERROR" + evaluator.evaluate(
                                rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                      SEPARATOR);
                            continue;
                        } else {
                            sb.append(getValue(cellValue, cell) + SEPARATOR);
                        }
                    } else if (colIx == 5) { //校验第5列数据  生效标志
                        if (cell == null || cellValue == null ||
                            StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append("1" + SEPARATOR);
                            continue;
                        } else {
                            sb.append(getValue(cellValue, cell) + SEPARATOR);
                        }
                    } else {
                        if (cell == null || cellValue == null ||
                            StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append(SEPARATOR);
                            continue;
                        } else {
                            sb.append(getValue(cellValue, cell) + SEPARATOR);
                        }
                    }
                    //                    } else {
                    //                        sb.append(SEPARATOR);
                    //                    }
                }
                if (StringUtil.isNotBlank(sb.toString()) && !"||||||".equals(sb.toString())) {
                    list.add(sb.toString());
                }
            } else {
                continue;
            }
        }
        return list;
    }

    /**
     * 获取单元格内容。
     *
     * @param cellValue 单元格
     * @param cell      单元格
     * @return value
     */
    // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
    // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
    public static String getValue(CellValue cellValue, Cell cell) {
        if (cellValue.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cellValue.getCellType() == cell.CELL_TYPE_NUMERIC) {
            HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
            String cellFormatted = dataFormatter.formatCellValue(cell);
            return cellFormatted;
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * 由Excel文件的Sheet导出至List(只用于收款凭证管理批量导入)。
     *
     * @param file     文件
     * @param sheetNum sheet坐标
     * @return excel内容列表
     */
    public static List<String> exportListFromExcelForReceipt(File file, int sheetNum)
        throws IOException {
        return exportListFromExcelForReceipt(new FileInputStream(file),
                                             FilenameUtils.getExtension(file.getName()), sheetNum);
    }

    /**
     * 由Excel流的Sheet导出至List (只用于收款凭证管理批量导入)。
     *
     * @param is            输出流
     * @param extensionName 扩展名称
     * @param sheetNum      sheet坐标
     * @return 内容列表
     * @throws IOException 异常
     */
    public static List<String> exportListFromExcelForReceipt(
        InputStream is, String extensionName, int sheetNum) throws IOException {

        Workbook workbook = null;

        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return exportListFromExcelForReceipt(workbook, sheetNum);
    }

    /**
     * 由指定的Sheet导出至List(只用于收款凭证管理批量导入)。(适用于一般从第三行开始读取数据,第一列和第二列字段不能为空)
     *
     * @param workbook 内容
     * @param sheetNum sheet页坐标
     * @return 文档内容
     */
    private static List<String> exportListFromExcelForReceipt(Workbook workbook, int sheetNum) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        List<String> list = new ArrayList<String>();
        int minRowIx = sheet.getFirstRowNum();
        int startReadRow = minRowIx + 2; //读取第一二行，是字段名称和注意，所以从第三行开始读

        //  用列数判断是不是指定的導出的模版
        try {
            Row rowSecond = sheet.getRow(minRowIx + 1);
            rowSecond.getCell(8);
            if (rowSecond.getCell(8) == null || (rowSecond.getCell(8) != null && !rowSecond.getCell(
                8).getStringCellValue().contains("muban"))) {
                StringBuilder stringBuilder = new StringBuilder();
                log.debug("ERROR请使用指定的模版！即下载的模板！");
                stringBuilder.append("ERROR请使用指定的模版  即下载的模板");
                list.add(stringBuilder.toString());
                return list;
            }
        } catch (Exception ex) {
            StringBuilder stringBuilder = new StringBuilder();
            log.debug("ERROR请使用指定的模版！即下载的模板！" + ex);
            stringBuilder.append("ERROR请使用指定的模版  即下载的模板");
            list.add(stringBuilder.toString());
            return list;
        }

        int maxRowIx = sheet.getLastRowNum();
        Row rowData = sheet.getRow(minRowIx + 1); //获取字段名称
        for (int rowIx = startReadRow; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            if (row != null) {
                StringBuilder sb = new StringBuilder();
                short minColIx = 0;
                short maxColIx = 7;
                for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
                    Cell cell = row.getCell(new Integer(colIx)); //第一列和第二列不能为空 第七列不能为空
                    CellValue cellValue = evaluator.evaluate(cell);
                    //                    if (cellValue != null) {
                    if (colIx == 0 || colIx == 1 || colIx == 6) { //校验第1列数据 终端号
                        if (cellValue == null || StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append("ERROR" + evaluator.evaluate(
                                rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                      SEPARATOR);
                            continue;
                        } else {
                            if (colIx == 0) {
                                if (getValue(cellValue, cell).length() < 20) {
                                    sb.append(getValue(cellValue, cell) + SEPARATOR);
                                } else {
                                    sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                    continue;
                                }
                            } else if (colIx == 6) {
                                if (getValue(cellValue, cell).length() <= 30) {
                                    sb.append(getValue(cellValue, cell) + SEPARATOR);
                                } else {
                                    sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                    continue;
                                }
                            } else {
                                Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
                                Matcher isNum = pattern.matcher(getValue(cellValue, cell));
                                if (!isNum.matches()) {
                                    sb.append("ERROR" + "不是数字或者字母，请正确填写" + SEPARATOR);
                                    continue;
                                } else {
                                    if (getValue(cellValue, cell).length() <= 10) {
                                        sb.append(getValue(cellValue, cell) + SEPARATOR);
                                    } else {
                                        sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                        continue;
                                    }
                                }
                            }

                        }
                    } else if (colIx == 4 || colIx == 5 || colIx == 3) { //校验第4,5,6列数据
                        if (cellValue == null ||
                            (StringUtil.isBlank(String.valueOf(cellValue.getNumberValue())) &&
                             StringUtil.isBlank(cellValue.getStringValue()))) {
                            sb.append("ERROR" + evaluator.evaluate(
                                rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                      SEPARATOR);
                            continue;
                        } else {
                            Pattern pattern = Pattern.compile(
                                "^([1-9][\\d]{0,9}|0)(\\.[\\d]{1,2})?$");
                            if (!StringUtil.isBlank(getValue(cellValue, cell))) {
                                Matcher isNum = pattern.matcher(getValue(cellValue, cell));
                                if (!isNum.matches()) {
                                    sb.append("ERROR" + "金额必须是小于12位的正数,精确到分" + SEPARATOR);
                                    continue;
                                } else {
                                    sb.append(getValue(cellValue, cell) + SEPARATOR);
                                }
                            } else {
                                sb.append("ERROR" + evaluator.evaluate(
                                    rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                          SEPARATOR);
                                continue;
                            }
                        }
                    } else {
                        if (cellValue == null || StringUtil.isBlank(getValue(cellValue, cell))) {
                            if (colIx != 7) {
                                sb.append(SEPARATOR);
                                continue;
                            }
                        } else {
                            if (colIx == 7) {
                                if (getValue(cellValue, cell).length() <= 166) {
                                    sb.append(getValue(cellValue, cell) + SEPARATOR);
                                } else {
                                    sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                    continue;
                                }
                            } else if (colIx == 2) {
                                Pattern pattern = Pattern.compile("[0-9]*");
                                if (!StringUtil.isBlank(getValue(cellValue, cell))) {
                                    Matcher isNum = pattern.matcher(getValue(cellValue, cell));
                                    if (!isNum.matches()) {
                                        sb.append("ERROR" + "不是数字，请正确填写" + SEPARATOR);
                                        continue;
                                    } else {
                                        if (getValue(cellValue, cell).length() <= 10) {
                                            sb.append(getValue(cellValue, cell) + SEPARATOR);
                                        } else {
                                            sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                            continue;
                                        }
                                    }

                                } else {
                                    sb.append(getValue(cellValue, cell) + SEPARATOR);
                                }
                            } else {
                                sb.append(getValue(cellValue, cell) + SEPARATOR);
                            }

                        }
                    }
                    //                    } else {
                    //                        sb.append(SEPARATOR);
                    //                    }
                }
                if (StringUtil.isNotBlank(sb.toString())) {
                    list.add(sb.toString());
                }
            } else {
                continue;
            }
        }
        return list;
    }

    /**
     * 模板下载。
     *
     * @param path     模板文件绝对路径
     * @param fileName 文件名称
     * @param response 响应
     */
    public static void doDownLoadFile(
        String path, String fileName, HttpServletResponse response) {

        try {
            response.reset();
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader(
                "Content-disposition", "attachment; filename=" + new String(fileName.getBytes(),
                                  "ISO8859-1"));// 组装附件名称和格式

            response.resetBuffer();
            File file = new File(path);
            InputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream fos = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            // 弹出下载对话框
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
        } catch (Exception exception) {
            log.error("", exception);
        }
    }

    private static final String[] header = new String[] {"姓名", "邮箱", "充值金额", "手机号码", "员工编号"};

//    /**
//     * 员工福利excel解析。
//     *
//     * @param file 福利文件
//     * @return 返回数据
//     */
//    public static Map<String, Object> welfareExcel(MultipartFile file, double maxAmt)
//        throws IOException {
//        Map<String, Object> result = Maps.newHashMap();
//        result.put("status", false);
//        //未选择文件
//        if (file.getSize() == 0L) {
//            return result;
//        }
//
//        //文件类型错误
//        if (!ExcelImport.isExcel(file)) {
//            result.put("msg", ConstString.EXCEL_NO_XLSORXLSX);
//            return result;
//        }
//
//        //解析excel
//        Map<String, Object> data = ExcelImport.sheet2List(
//            file.getInputStream(), null, null, null);
//        if (data == null) {
//            result.put("msg", ConstString.SELECT_TEMPLATE);
//            return result;
//        } else {
//            if (data.get("msg") != null) {
//                result.put("msg", data.get("msg"));
//                result.put("status", false);
//                return result;
//            }
//        }
//        Map<String, Object> headerClum = (Map<String, Object>) data.get("headerClum");
//        Iterator<String> iterator = headerClum.keySet().iterator();
//
//        for (int i = 0; i < header.length; i++) {
//            if (!header[i].equals(iterator.next())) {
//                result.put("msg", ConstString.SELECT_TEMPLATE);
//                return result;
//            }
//        }
//
//        List<WelfareRecord> welfareRecords = (List<WelfareRecord>) data.get("list");
//
//        if (welfareRecords == null) {
//            result.put("msg", ConstString.IS_NULL_FILE);
//            return result;
//        }
//
//        List<WelfareRecord> rmWelfareRecord = Lists.newArrayList();
//        for (WelfareRecord w : welfareRecords) {
//            if (StringUtils.isEmpty(w.getEmpNo()) && StringUtils.isEmpty(w.getEmpName()) &&
//                StringUtils.isEmpty(w.getPhone()) && StringUtils.isEmpty(w.getAmt()) &&
//                StringUtils.isEmpty(w.getEmail())) {
//                rmWelfareRecord.add(w);
//            }
//        }
//        welfareRecords.removeAll(rmWelfareRecord);
//
//        if (welfareRecords.size() == 0) {
//            result.put("msg", ConstString.NO_CONTENT);
//            return result;
//        }
//
//        //验证数据
//        List<Object> empNos = Lists.newArrayList();
//        List<Object> phones = Lists.newArrayList();
//        List<WelfareRecordVo> welfareRecordVos = Lists.newArrayList();
//        for (WelfareRecord w : welfareRecords) {
//            WelfareRecordVo welfareRecordVo = new WelfareRecordVo();
//            String empNo = w.getEmpNo();
//
//            if (StringUtils.isEmpty(empNo)) {
//                welfareRecordVo.setEmpNo(ConstString.EMP_NO_CONNOT_NULL);
//            } else {
//                welfareRecordVo.setEmpNo(empNo);
//            }
//            if (empNos.contains(empNo)) {
//                welfareRecordVo.setObjEmpNo(ConstString.EMP_NO_CONNOT_REPEAT);
//            }
//            //获取员工卡号的充值单笔上线和余额上线和卡状态
//
//            empNos.add(empNo);
//            String empName = w.getEmpName();
//            if (StringUtils.isEmpty(empName)) {
//                welfareRecordVo.setEmpName(ConstString.EMP_NAME_CONNOT_NULL);
//            } else {
//                welfareRecordVo.setEmpName(empName);
//            }
//
//            String phone = w.getPhone();
//            if (StringUtils.isEmpty(phone)) {
//                welfareRecordVo.setPhone(ConstString.PHONE_CONNOT_NULL);
//            } else if (phones.contains(phone)) {
//                welfareRecordVo.setPhone(phone);
//                welfareRecordVo.setObjPhone(ConstString.PHONE_CONNOT_REPEAT);
//            } else {
//                welfareRecordVo.setPhone(phone);
//                welfareRecordVo.setObjPhone(true);
//                String phoneRegex = "^(13|14|15|17|18)\\d{9}$";
//                if (!Pattern.matches(phoneRegex, phone)) {
//                    welfareRecordVo.setObjPhone(false);
//                }
//            }
//            phones.add(phone);
//            String amt = w.getAmt();
//            if (StringUtils.isEmpty(amt)) {
//                welfareRecordVo.setAmt(ConstString.AMT_CONNOT_NULL);
//                welfareRecordVo.setObjRechargeAmt(false);
//            } else {
//                welfareRecordVo.setAmt(amt);
//                welfareRecordVo.setObjRechargeAmt(false);
//                String amtRegex =
//                    "^(?:0\\.\\d{1,2}|[1-9]\\d{0,4}(?:\\.\\d{1,2})?|" + maxAmt + "|0)$";
//                /* String amtRegex1 = "^([1-9]+[0-9]*)(\\d*|\\.\\d*[0]+|\\.\\d+)|[0]$";
//                String amtRegex2 = "^([0-9]\\.\\d*)([1-9]|[1-9]\\d*[0]*)$";*/
//                if (Pattern.matches(amtRegex, amt) && Double.parseDouble(amt) <= maxAmt) {
//                    welfareRecordVo.setRechargeAmt(Double.parseDouble(amt));
//                    welfareRecordVo.setObjRechargeAmt(true);
//                } else {
//                    welfareRecordVo.setObjRechargeAmt("福利卡余额上限为：" + maxAmt + "元");
//                }
//            }
//            String email = w.getEmail();
//            welfareRecordVo.setEmail(email);
//            if (StringUtils.isEmpty(email)) {
//                welfareRecordVo.setObjEmail(true);
//            } else {
//                welfareRecordVo.setObjEmail(false);
//                String emailRegex =
//                    "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
//                if (Pattern.matches(emailRegex, email)) {
//                    welfareRecordVo.setObjEmail(true);
//                }
//            }
//
//            welfareRecordVos.add(welfareRecordVo);
//        }
//        double bal = 0;
//        boolean dataStatus = true;//记录数据状态
//        result.put("dataStatus", true);
//        for (WelfareRecordVo welfareRecordVo : welfareRecordVos) {
//            if (StringUtils.isEmpty(welfareRecordVo.getAmt())) {
//                result.put("dataStatus", false);
//                bal = -1;
//                break;
//            }
//
//            bal += welfareRecordVo.getRechargeAmt();
//
//            if ("false".equals(welfareRecordVo.getObjPhone() + "")) {
//                result.put("dataStatus", false);
//            }
//            if ("false".equals(welfareRecordVo.getObjRechargeAmt() + "")) {
//                result.put("dataStatus", false);
//            }
//        }
//        result.put("status", true);
//        result.put("bal", bal);
//        result.put("result", welfareRecordVos);
//        return result;
//    }


    /**
     * 由Excel文件的Sheet导出至List。
     *
     * @param file     文件
     * @param sheetNum sheet坐标
     * @return excel内容列表
     */
    public static List<String> exportListFromMchdenyExcel(File file, int sheetNum, int maxCol)
        throws IOException {
        return exportListFromMchdenyExcel(new FileInputStream(file),
                                          FilenameUtils.getExtension(file.getName()), sheetNum,
                                          maxCol);
    }

    /**
     * 由Excel流的Sheet导出至List。
     *
     * @param is            输出流
     * @param extensionName 扩展名称
     * @param sheetNum      sheet坐标
     * @return 内容列表
     * @throws IOException 异常
     */
    public static List<String> exportListFromMchdenyExcel(
        InputStream is, String extensionName, int sheetNum, int maxCol) throws IOException {

        Workbook workbook = null;

        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return exportListFromMchdenyExcel(workbook, sheetNum, maxCol);
    }

    /**
     * 由指定的Sheet导出至List。(适用于一般从第三行开始读取数据,第一列和第二列字段不能为空)
     *
     * @param workbook 内容
     * @param sheetNum sheet页坐标
     * @return 文档内容
     */
    private static List<String> exportListFromMchdenyExcel(
        Workbook workbook, int sheetNum, int maxCol) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        List<String> list = new ArrayList<String>();
        int minRowIx = sheet.getFirstRowNum();
        int startReadRow = minRowIx + 2; //读取第一二行，是字段名称和注意，所以从第三行开始读
        //  用列数判断是不是指定的導出的模版
        try {
            Row rowSecond = sheet.getRow(minRowIx + 1);
            rowSecond.getCell(0);
            StringBuilder stringBuilder = new StringBuilder();
            if (rowSecond.getCell(1) == null || (rowSecond.getCell(1) != null && !rowSecond.getCell(
                1).getStringCellValue().contains("cid"))) {
                log.debug("ERROR请使用指定的模版！即下载的模板！");
                stringBuilder.append("ERROR请使用指定的模版  即下载的模板");
                list.add(stringBuilder.toString());
                return list;
            }
        } catch (Exception ex) {
            StringBuilder stringBuilder = new StringBuilder();
            log.debug("ERROR请使用指定的模版！即下载的模板！" + ex);
            stringBuilder.append("ERROR请使用指定的模版  即下载的模板 ");
            list.add(stringBuilder.toString());
            return list;
        }

        int maxRowIx = sheet.getLastRowNum();
        Row rowData = sheet.getRow(minRowIx + 1); //获取字段名称
        for (int rowIx = startReadRow; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            if (row != null) {
                StringBuilder sb = new StringBuilder();
                short minColIx = 0;
                short maxColIx = 0;
                if (maxCol == 0) {
                    maxColIx = row.getLastCellNum();
                } else {
                    maxColIx = (short) maxCol;
                }
                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(new Integer(colIx)); //第一列和第二列不能为空
                    CellValue cellValue = evaluator.evaluate(cell);
                    //                    if (cellValue != null) {
                    if (colIx == 0) { //校验第1列数据 卡号
                        if (cell == null || cellValue == null ||
                            StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append("ERROR" + evaluator.evaluate(
                                rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                      SEPARATOR);
                            continue;
                        } else {
                            Pattern pattern = Pattern.compile("[0-9]*");
                            if (!StringUtil.isBlank(getValue(cellValue, cell))) {
                                Matcher isNum = pattern.matcher(getValue(cellValue, cell));
                                if (!isNum.matches()) {
                                    sb.append("ERROR" + "不是数字，请正确填写" + SEPARATOR);
                                    continue;
                                } else {
                                    if (getValue(cellValue, cell).length() <= 20) {
                                        sb.append(getValue(cellValue, cell) + SEPARATOR);
                                    } else {
                                        sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                        continue;
                                    }
                                }
                            }
                            /*sb.append(getValue(cellValue, cell) + SEPARATOR);*/
                        }
                    }
                }
                if (StringUtil.isNotBlank(sb.toString()) && !"".equals(sb.toString())) {
                    list.add(sb.toString());
                }
            } else {
                continue;
            }
        }
        return list;
    }

    /**
     * 由Excel文件的Sheet导出至List（只适用于门店分组文件上传）。
     *
     * @param file     文件
     * @param sheetNum sheet坐标
     * @return excel内容列表
     */
    public static List<String> exportShopGrpDefListFromExcel(File file, int sheetNum, int maxCol)
        throws IOException {
        return exportShopGrpDefListFromExcel(new FileInputStream(file),
                                             FilenameUtils.getExtension(file.getName()), sheetNum,
                                             maxCol);
    }

    /**
     * 由Excel流的Sheet导出至List（只适用于门店分组文件上传）。
     *
     * @param is            输出流
     * @param extensionName 扩展名称
     * @param sheetNum      sheet坐标
     * @return 内容列表
     * @throws IOException 异常
     */
    public static List<String> exportShopGrpDefListFromExcel(
        InputStream is, String extensionName, int sheetNum, int maxCol) throws IOException {

        Workbook workbook = null;

        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return exporShopGrpDeftListFromExcel(workbook, sheetNum, maxCol);
    }

    /**
     * 由指定的Sheet导出至List。(适用于一般从第三行开始读取数据,第一列和第二列字段不能为空，只适用于门店分组文件上传)
     *
     * @param workbook 内容
     * @param sheetNum sheet页坐标
     * @return 文档内容
     */
    private static List<String> exporShopGrpDeftListFromExcel(
        Workbook workbook, int sheetNum, int maxCol) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        List<String> list = new ArrayList<String>();
        int minRowIx = sheet.getFirstRowNum();
        int startReadRow = minRowIx + 2; //读取第一二行，是字段名称和注意，所以从第三行开始读
        //  用列数判断是不是指定的導出的模版
        try {
            Row rowSecond = sheet.getRow(minRowIx + 1);
            rowSecond.getCell(1);
            StringBuilder stringBuilder = new StringBuilder();
            if (rowSecond.getCell(2) == null || (rowSecond.getCell(2) != null && !rowSecond.getCell(
                2).getStringCellValue().contains("shopGrpDef"))) {
                log.debug("ERROR请使用指定的模版！即下载的模板！");
                stringBuilder.append("ERROR请使用指定的模版  即下载的模板");
                list.add(stringBuilder.toString());
                return list;
            }
        } catch (Exception ex) {
            StringBuilder stringBuilder = new StringBuilder();
            log.debug("ERROR请使用指定的模版！即下载的模板！" + ex);
            stringBuilder.append("ERROR请使用指定的模版  即下载的模板 ");
            list.add(stringBuilder.toString());
            return list;
        }

        int maxRowIx = sheet.getLastRowNum();
        Row rowData = sheet.getRow(minRowIx + 1); //获取字段名称
        for (int rowIx = startReadRow; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            if (row != null) {
                StringBuilder sb = new StringBuilder();
                short minColIx = 0;
                short maxColIx = 0;
                if (maxCol == 0) {
                    maxColIx = row.getLastCellNum();
                } else {
                    maxColIx = (short) maxCol;
                }
                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(new Integer(colIx)); //第一列和第二列不能为空
                    CellValue cellValue = evaluator.evaluate(cell);
                    //                    if (cellValue != null) {
                    if (colIx == 0) { //校验第1列数据 门店分组
                        if (cell == null || cellValue == null ||
                            StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append("ERROR" + evaluator.evaluate(
                                rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                      SEPARATOR);
                            continue;
                        } else {
                            if (getValue(cellValue, cell).length() < 20) {
                                sb.append(getValue(cellValue, cell) + SEPARATOR);
                            } else {
                                sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                continue;
                            }
                        }
                    } else if (colIx == 1) { //校验第2列数据 门店编号
                        if (cell == null || cellValue == null ||
                            StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append("ERROR" + evaluator.evaluate(
                                rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                      SEPARATOR);
                            continue;
                        } else {
                            if (getValue(cellValue, cell).length() < 20) {
                                sb.append(getValue(cellValue, cell) + SEPARATOR);
                            } else {
                                sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                continue;
                            }
                        }
                    } else {
                        if (cell == null || cellValue == null ||
                            StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append(SEPARATOR);
                            continue;
                        } else {
                            sb.append(getValue(cellValue, cell) + SEPARATOR);
                        }
                    }
                }
                if (StringUtil.isNotBlank(sb.toString()) && !"||||||".equals(sb.toString())) {
                    list.add(sb.toString());
                }
            } else {
                continue;
            }
        }
        return list;
    }

    /**
     * 由Excel文件的Sheet导出至List(只用于批量充值批量导入)。
     *
     * @param file     文件
     * @param sheetNum sheet坐标
     * @return excel内容列表
     */
    public static List<String> exportListFromExcelForBatchRecharge(File file, int sheetNum)
        throws IOException {
        return exportListFromExcelForBatchRecharge(new FileInputStream(file),
                                                   FilenameUtils.getExtension(file.getName()),
                                                   sheetNum);
    }

    /**
     * 由Excel流的Sheet导出至List (只用于批量充值批量导入)。
     *
     * @param is            输出流
     * @param extensionName 扩展名称
     * @param sheetNum      sheet坐标
     * @return 内容列表
     * @throws IOException 异常
     */
    public static List<String> exportListFromExcelForBatchRecharge(
        InputStream is, String extensionName, int sheetNum) throws IOException {

        Workbook workbook = null;

        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return exportListFromExcelForBatchRecharge(workbook, sheetNum);
    }

    /**
     * 由指定的Sheet导出至List(只用于收款凭证管理批量导入)。(适用于一般从第三行开始读取数据,第一列和第二列字段不能为空)
     *
     * @param workbook 内容
     * @param sheetNum sheet页坐标
     * @return 文档内容
     */
    private static List<String> exportListFromExcelForBatchRecharge(
        Workbook workbook, int sheetNum) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        List<String> list = new ArrayList<String>();
        int minRowIx = sheet.getFirstRowNum();
        int startReadRow = minRowIx + 2; //读取第一二行，是字段名称和注意，所以从第三行开始读

        //  用列数判断是不是指定的導出的模版
        try {
            Row rowSecond = sheet.getRow(minRowIx + 1);
            if (rowSecond.getCell(2) == null || (rowSecond.getCell(2) != null && !rowSecond.getCell(
                2).getStringCellValue().contains("batchRecharge"))) {
                StringBuilder stringBuilder = new StringBuilder();
                log.debug("ERROR请使用指定的模版！即下载的模板！");
                stringBuilder.append("ERROR请使用指定的模版  即下载的模板");
                list.add(stringBuilder.toString());
                return list;
            }
        } catch (Exception ex) {
            StringBuilder stringBuilder = new StringBuilder();
            log.debug("ERROR请使用指定的模版！即下载的模板！" + ex);
            stringBuilder.append("ERROR请使用指定的模版  即下载的模板");
            list.add(stringBuilder.toString());
            return list;
        }

        int maxRowIx = sheet.getLastRowNum();
        Row rowData = sheet.getRow(minRowIx + 1); //获取字段名称
        for (int rowIx = startReadRow; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            if (row != null) {
                StringBuilder sb = new StringBuilder();
                short minColIx = 0;
                short maxColIx = 1;
                for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
                    Cell cell = row.getCell(new Integer(colIx)); //第一列和第二列不能为空
                    CellValue cellValue = evaluator.evaluate(cell);
                    //                    if (cellValue != null) {
                    if (colIx == 0 || colIx == 1) { //校验第1列数据 终端号
                        if (cellValue == null || StringUtil.isBlank(getValue(cellValue, cell))) {
                            sb.append("ERROR" + evaluator.evaluate(
                                rowData.getCell(new Integer(colIx))).getStringValue() + "不能为空" +
                                      SEPARATOR);
                            continue;
                        } else {
                            if (colIx == 0) {
                                if (getValue(cellValue, cell).length() < 20) {
                                    sb.append(getValue(cellValue, cell) + SEPARATOR);
                                } else {
                                    sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                    continue;
                                }
                            } else {
                                Pattern pattern = Pattern.compile(
                                    "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
                                if (!StringUtil.isBlank(cellValue.getStringValue())) {
                                    Matcher isNum = pattern.matcher(cellValue.getStringValue());
                                    if (!isNum.matches()) {
                                        sb.append("ERROR" + "不是金额，请正确填写" + SEPARATOR);
                                        continue;
                                    }
                                } else {
                                    if (getValue(cellValue, cell).length() <= 12) {
                                        sb.append(getValue(cellValue, cell) + SEPARATOR);
                                    } else {
                                        sb.append("ERROR" + "长度过长，请做修改" + SEPARATOR);
                                        continue;
                                    }
                                }
                            }

                        }
                    }
                }
                if (StringUtil.isNotBlank(sb.toString())) {
                    list.add(sb.toString());
                }
            } else {
                continue;
            }
        }
        return list;
    }


}
