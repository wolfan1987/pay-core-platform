package org.andrew.commons.wechat.constants;

/**
 * 审核状态。
 *
 * @author andrewliu
 */
public enum StatusEnum {

    CARD_STATUS_NOT_VERIFY("待审核"), CARD_STATUS_VERIFY_FALL("审核失败"), CARD_STATUS_VERIFY_OK(
        "通过审核"), CARD_STATUS_USER_DELETE("卡券被用户删除"), CARD_STATUS_USER_DISPATCH("在公众平台投放过的卡券");

    private String description;

    StatusEnum(String description) {
        this.description = description;
    }

    /**
     * 返回审核描述。
     *
     * @param code 审核码
     * @return 返回描述
     */
    public static String getDescription(String code) {
        if (null == code || "".equals(code)) {
            return null;
        }
        String desc = null;
        for (StatusEnum statusEnum : StatusEnum.values()) {
            String name = statusEnum.name();
            if (code.equals(name)) {
                desc = statusEnum.description;
            }
        }
        return desc;
    }

    public String getDescription() {
        return description;
    }
}
