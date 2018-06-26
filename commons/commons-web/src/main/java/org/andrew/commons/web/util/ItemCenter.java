package org.andrew.commons.web.util;



import java.util.HashMap;

import java.util.Map;

/**
 * 数据字典code转为name内存。
 *
 * @author andrewliu 2017-5-22
 * @version 1.0.1
 */
public class ItemCenter {

    /**
     * item表内存。
     */
  // private static Map<String, ItemVo> itemMap = new HashMap<>();

    /**
     * 地区表内存。
     */
    private static Map<String, Map<String, String>> areaMap = new HashMap<>();

//    /**
//     * 获取数据字段name值。
//     *
//     * @param value    code
//     * @param category 类型
//     * @return name
//     */
//    public static String getName(Map<String, Map<String, String>> areaMap,
//                                 Map<String, ItemVo> itemMap, String value, String category) {
//        String reName = null;
//        if (category != null) {
//            if ("AREAP".equals(category) || "AREAC".equals(category)) {
//                reName = getAreaName(areaMap, value, category);
//            } else {
//                reName = getItemName(itemMap, value, category);
//            }
//        }
//        return reName;
//    }

    /**
     * 获取数据字段name值。
     *
     * @param value    code
     * @param category 类型
     * @return name
     */
//    public static String getName(String value, String category) {
//        return getName(areaMap, itemMap, value, category);
//    }

    /**
     * 将code转为name。
     *
     * @param value    数据字段code
     * @param category 数据字段类型
     * @return 数据字段name，转换不了时返回null
     */
    public static String getAreaName(Map<String, Map<String, String>> areaMap,
                                     String value, String category) {
        String reName = value;
        if (value != null && !"".equals(value) && category != null && !"".equals(category)) {
            if (areaMap.containsKey(category)) {
                Map<String, String> aream = areaMap.get(category);
                if (aream != null) {
                    reName = aream.get(value);
                }
            }
        }
        return reName;
    }

//    /**
//     * 获取数据字段name值。
//     *
//     * @param value    name值
//     * @param category code值
//     * @return name值
//     */
//    public static String getItemName(Map<String, ItemVo> itemMap, String value, String category) {
//        String reName = value;
//        if (value != null && !"".equals(value) && category != null && !"".equals(category)) {
//            if (itemMap.containsKey(category)) {
//                ItemVo itemDto = itemMap.get(category);
//                if (itemDto != null) {
//                    List<ItemDetail> itemDetails = itemDto.getItemDetails();
//                    if (itemDetails != null && itemDetails.size() > 0) {
//                        for (ItemDetail bean : itemDetails) {
//                            if (value.equals(bean.getCode())) {
//                                reName = bean.getName();
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return reName;
//    }
//
//    /**
//     * 更新数据字典内存对象。
//     *
//     * @param items 集合
//     */
//    public static void putItem(List<ItemVo> items) {
//        if (items != null && items.size() > 0) {
//            for (ItemVo item : items) {
//                itemMap.put(item.getCategory(), item);
//            }
//        }
//    }
//
//    /**
//     * 更新数据字典内存对象。
//     *
//     * @param item 集合
//     */
//    public static void putItem(ItemVo item) {
//        if (item != null) {
//            itemMap.put(item.getCategory(), item);
//        }
//    }
//
//    /**
//     * 更新地区内存对象。
//     *
//     * @param areas 地区集合
//     */
//    public static void putArea(List<AreaInfo> areas) {
//        Map<String, String> aream;
//        if (areas != null && areas.size() > 0) {
//            for (AreaInfo areaInfo : areas) {
//                putArea(areaInfo);
//            }
//        }
//    }
//
//    /**
//     * 更新地区内存对象。
//     *
//     * @param areaInfo 地区对象
//     */
//    public static void putArea(AreaInfo areaInfo) {
//        Map<String, String> aream;
//        if (areaInfo != null) {
//            if ("0".equals(areaInfo.getSupperCode())) {
//                aream = areaMap.computeIfAbsent("AREAP", k -> new HashMap<>());
//            } else {
//                aream = areaMap.computeIfAbsent("AREAC", k -> new HashMap<>());
//            }
//            aream.put(areaInfo.getCode(), areaInfo.getName());
//        }
//    }
//
//    /**
//     * 移除地区内存对象。
//     *
//     * @param areas 地区集合
//     */
//    public static void removeArea(List<AreaInfo> areas) {
//        Map<String, String> aream;
//        if (areas != null && areas.size() > 0) {
//            for (AreaInfo areaInfo : areas) {
//                removeArea(areaInfo);
//            }
//        }
//    }
//
//    /**
//     * 移除地区内存对象。
//     *
//     * @param areaInfo 地区对象
//     */
//    public static void removeArea(AreaInfo areaInfo) {
//        Map<String, String> aream;
//        if (areaInfo != null) {
//            if ("0".equals(areaInfo.getSupperCode())) {
//                aream = areaMap.computeIfAbsent("AREAP", k -> new HashMap<>());
//            } else {
//                aream = areaMap.computeIfAbsent("AREAC", k -> new HashMap<>());
//            }
//            aream.remove(areaInfo.getCode());
//        }
//    }
//
//
//    /**
//     * 移除数据字典内存对象。
//     *
//     * @param items 集合
//     */
//    public static void removeItem(List<ItemVo> items) {
//        if (items != null && items.size() > 0) {
//            for (ItemVo item : items) {
//                itemMap.remove(item.getCategory(), item);
//            }
//        }
//    }
//
//    /**
//     * 移除数据字典内存对象。
//     *
//     * @param item 集合
//     */
//    public static void removeItem(ItemVo item) {
//        if (item != null) {
//            itemMap.remove(item.getCategory(), item);
//        }
//    }
//
//    public static Map<String, ItemVo> getItemMap() {
//        return itemMap;
//    }

    public static Map<String, Map<String, String>> getAreaMap() {
        return areaMap;
    }

}
