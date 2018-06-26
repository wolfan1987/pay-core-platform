package org.andrew.commons.web.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 查询统计模块的交易流水查询工具类。
 */
public class TransUtil {

    /**
     * 日志输出。
     */
    private static final Logger logger = LoggerFactory.getLogger(TransUtil.class);

//    @Reference(version = "1.0.0")
//    private static MinstService minstService;
//
//    @Reference(version = "1.0.0")
//    private static TxnIdService txnIdService;

//    /**
//     * 组装根据数据字典拿数据。
//     *
//     * @param itemMap .
//     * @param transData 当前集合
//     * @param str       需要查询的数据值
//     * @param type      需要查询的类型值
//     */
//    public static void installDataByItem(
//        Map<String, ItemVo> itemMap, List<Object> transData, Object str, String type) {
//        if (str != null && !str.equals("")) {
//            transData.add(ItemCenter.getName(null, itemMap, str + "", type));
//        } else {
//            transData.add("");
//        }
//    }

    /**
     * 判断导出的数据是否过多或者没有导出的数据。
     *
     * @param reslut 导出的数据结果是否过多
     * @param size   导出的数据大小
     * @param reMap  返回页面的提示
     * @return json提示
     */
    public static String isOrerFlowOrNull(
        Map<String, Object> reslut, Integer size, Map<String, String> reMap) {
        if (!(boolean) reslut.get("result")) {
            reslut.put("code", "5");
            return JSON.toJSONString(reslut);
        }

        if (size == 0) {
            reMap.put("msg", "条件范围内无数据可导");
            return JSON.toJSONString(reMap);
        }
        return null;
    }

    /**
     * 判断导出的数据是否过多或者没有导出的数据。
     *
     * @param size  导出的数据大小
     * @param reMap 返回页面的提示
     * @return json提示
     */
    public static String isOrerFlowOrNull(Integer size, Map<String, String> reMap) {
        if (size == 0) {
            reMap.put("msg", "条件范围内无数据可导");
            return JSON.toJSONString(reMap);
        }
        return null;
    }

    /**
     * 组装统计数据。
     *
     * @param orgTransData     当前list集合
     * @param orgTransVoSize   当前集合大小
     * @param currentDetail    当前数据
     * @param currentDetailSum 当前数据总和
     */
    public static void installTransDataByFlag(
        List<Object> orgTransData, int orgTransVoSize, Object currentDetail,
        Object currentDetailSum, Boolean isNumSum) {
        if (orgTransVoSize == 0) {
            if (isNumSum) {
                orgTransData.add(currentDetailSum);
            } else {
                orgTransData.add(currentDetailSum != null ? currentDetailSum : 0.0);
            }
        } else {
            if (isNumSum) {
                orgTransData.add(currentDetail);
            } else {
                orgTransData.add(currentDetail != null ? currentDetail : 0.0);
            }
        }
    }

    /**
     * 根据日期组装数据。
     *
     * @param transData 当前集合
     * @param str       需要转换的时间类型
     * @param type      需要转换的对象类型
     */
    public static void installDataByDate(List<Object> transData, Object str, String type) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfUploadDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (str != null && !str.equals("")) {
                if (type.equals("txnDate") || type.equals("startDate") || type.equals("endDate")) {
                    transData.add(sdf.format(str));
                } else if (type.equals("uploadDate") || type.equals("createTime")) {
                    transData.add(sdfUploadDate.format(str));
                } else if (type.equals("settleDate")) {
                    Date date = new SimpleDateFormat("yyyyMMdd").parse(str.toString());
                    transData.add(sdf.format(date));
                } else {
                    transData.add(str);
                }
            } else {
                transData.add("");
            }
        } catch (Exception ex) {
            logger.error("时间或日期转换失败", ex);
        }

    }
}

