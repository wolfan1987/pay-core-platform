package org.andrew.commons.utils.excel.excel.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.andrew.commons.utils.excel.constant.ConstString;
import org.andrew.commons.utils.excel.excel.annotation.ExcelField;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * excel导入。
 * Created by maqiang on 2017/8/28.
 */
public class ExcelImport {
    /**
     * 日志。
     */
    private static final Logger logger = LoggerFactory.getLogger(ExcelImport.class);

    /**
     * excel转list。
     *
     * @param inputStream 流
     * @param clazz       对象
     * @param headRow     标题行数
     * @param checkTitle  标题是否可以为空 true：跳过数据转换 false：不跳过数据转换
     * @return list
     */
    public static <T> Map<String, Object> sheet2List(
        InputStream inputStream, Class<T> clazz, Integer headRow, Boolean checkTitle) {
        if (headRow == null) {
            headRow = 0;//默认为首行为标题行
        }
        if (checkTitle == null) {
            checkTitle = true;//默认标题不可为空
        }
        Map<String, Object> result = Maps.newHashMap();
        List<T> list = Lists.newArrayList();
        if (inputStream != null) {
            try {
                final Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheetAt(0);//默认只加载第一个sheet
                Row headerRow = sheet.getRow(headRow);//取第一行为标题

                //excel列位置
                Map<String, Integer> headerClum = Maps.newHashMap();
                for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                    if (headerRow.getCell(i) != null) {
                        try {
                            headerClum.put(headerRow.getCell(i).getStringCellValue(), i);
                        } catch (Exception ex) {
                            logger.error("员工福利文件标题暂未处理数字", ex);
                            return null;
                        }
                    }
                }

                result.put("headerClum", headerClum);

                //标题为空是否跳过数据获取
                if (checkTitle) {
                    boolean hasHeader = false;

                    for (int i = 0; i < headerClum.size(); i++) {
                        Iterator<String> iterator = headerClum.keySet().iterator();
                        if (iterator.hasNext()) {
                            if (!StringUtils.isEmpty(iterator.next())) {
                                hasHeader = true;
                                break;
                            }
                        }
                    }

                    if (headerClum.size() == 0 || !hasHeader) {
                        return null;
                    }
                }

                //字段与注解关联信息
                Map<Field, ExcelField> fields = Maps.newHashMap();
                for (Field field : clazz.getDeclaredFields()) {
                    ExcelField tag = field.getAnnotation(ExcelField.class);
                    if (tag != null) {
                        fields.put(field, tag);
                    }
                }

                ExpressionParser parser = new SpelExpressionParser();
                list = IntStream.range(
                    ++headRow, sheet.getPhysicalNumberOfRows()).parallel().mapToObj(x -> {
                        T entity = parser.parseExpression("new " + clazz.getName() + "()").getValue(
                            clazz);
                        StandardEvaluationContext context = new StandardEvaluationContext(entity);
                        Row row = sheet.getRow(x);
                        fields.forEach((key, tag) -> {
                            //是否有重新定义excel取值类型(默认Object为未手动设置类型取字段类型)
                            Class<?> type = tag.clazz().equals(
                                Object.class) ? key.getType() : tag.clazz();
                            //是否获取excel行信息
                            if (headerClum.containsKey(tag.name())) {
                                //行信息
                                Cell cell = row.getCell(headerClum.get(tag.name()));
                                if (cell != null) {
                                    cell.setCellType(1);
                                    String value = cell.getStringCellValue();
                                    if (type.equals(String.class)) {
                                        parser.parseExpression(key.getName())
                                            .setValue(context, value);
                                    } else if (type.equals(Date.class)) {
                                        //时间类型 yyyy/MM/dd
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                            "yyyy/MM/dd");
                                        try {
                                            parser.parseExpression(key.getName()).setValue(
                                                context, simpleDateFormat.parse(value));
                                        } catch (ParseException ex) {
                                            logger.error("date format is error", ex);
                                        }
                                    } else if (type.equals(Boolean.class)) {
                                        parser.parseExpression(key.getName()).setValue(
                                            context, Boolean.getBoolean(value));
                                    } else if (type.equals(Double.class) ||
                                               key.getType().equals(Long.class) ||
                                               key.getType().equals(BigDecimal.class)) {
                                        parser.parseExpression(key.getName())
                                            .setValue(context, value);
                                    }
                                }
                            }
                        });
                        return entity;
                    }).collect(Collectors.toList());
            } catch (Exception ex) {
                logger.error("excel analysis is error ", ex);
                result.put("msg", "请勿修改模板文件格式");
                return result;
            }
        }
        result.put("list", list);
        return result;
    }

    /**
     * 验证文件是否为xls/xlsx文件。
     *
     * @param file 文件
     * @return true：是，false：不是
     */
    public static boolean isExcel(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (StringUtils.isEmpty(prefix) ||
            (!ConstString.EXCEL_SUFFIX_XLS.equalsIgnoreCase(prefix) &&
             !ConstString.EXCEL_SUFFIX_XLSX.equalsIgnoreCase(prefix))) {
            return false;
        }
        return true;
    }

}
