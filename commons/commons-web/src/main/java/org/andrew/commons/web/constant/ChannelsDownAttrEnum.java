package org.andrew.commons.web.constant;



import java.util.HashMap;
import java.util.Map;

/**
 * 系统定义常量。
 *
 * @author
 */
public enum ChannelsDownAttrEnum {

    BUY_FLAG("BUY_FLAG", "禁用购卡"), RECHARGE_FLAG("RECHARGE_FLAG", "禁用充值"),
    PAY_FLAG("PAY_FLAG", "禁用付款"), CARD_SEND_FLAG("CARD_SEND_FLAG", "禁用转赠");

    private String code;

    private String desn;

    ChannelsDownAttrEnum(String code, String desn) {
        this.code = code;
        this.desn = desn;
    }

    /**
     * 数组转map。
     *
     * @param values 枚举数组
     * @return map
     */
    public static Map<String, String> arrayToMap(ChannelsDownAttrEnum[] values) {
      //  Map<String, String> map = Maps.newHashMap();
        Map<String, String> map = new HashMap<String,String>();
        for (int i = 0; i < values.length; i++) {
            map.put(values[i].name(), values[i].code);
        }
        return map;
    }

    public String getCode() {
        return code;
    }

    public String getDesn() {
        return desn;
    }
}
