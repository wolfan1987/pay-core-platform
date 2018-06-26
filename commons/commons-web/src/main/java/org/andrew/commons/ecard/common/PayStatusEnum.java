package org.andrew.commons.ecard.common;

/**
 * 支付状态转换。
 *
 * @author andrewliu
 */
public enum PayStatusEnum {

    SUCCESS("1", "支付成功");

    private String status;

    private String desn;

    PayStatusEnum(String status, String desn) {
        this.status = status;
        this.desn = desn;
    }

    /**
     * 状态代码转换为中文。
     *
     * @param status 状态
     * @return 中文
     */
    public static String getDesnByStatus(String status) {
        PayStatusEnum[] enums = PayStatusEnum.values();
        for (PayStatusEnum e : enums) {
            if (status.equals(e.status)) {
                return e.desn;
            }
        }
        return "";
    }

    public String getStatus() {
        return status;
    }

    public String getDesn() {
        return desn;
    }
}
