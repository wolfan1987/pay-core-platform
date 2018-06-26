package org.andrew.commons.wechat.constants;

/**
 * Code展示类型。
 *
 * @author andrewliu
 */
public enum CodeTypeEnum {

    /**
     * 显示在卡的左下角。
     */
    CODE_TYPE_TEXT("文本"),
    CODE_TYPE_BARCODE("一维码"),
    CODE_TYPE_QRCODE("二维码"),
    CODE_TYPE_ONLY_QRCODE("二维码无Code显示"),
    CODE_TYPE_ONLY_BARCODE("一维码无Code显示"),
    /**
     * 如果为none，卡号将不显示在卡上。
     */
    CODE_TYPE_NONE("不显示code和条形码");

    private String msg;

    CodeTypeEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
