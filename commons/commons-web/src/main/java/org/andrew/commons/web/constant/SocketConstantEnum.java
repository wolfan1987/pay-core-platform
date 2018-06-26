package org.andrew.commons.web.constant;

/**
 * socket接口网点和渠道常量。
 *
 * @author
 */
public enum SocketConstantEnum {

    H5ORGCODE("JHBS", "H5网点"), H5CHANNELS("WX001", "H5渠道"), MANAGECHANNELS(
        "MANAGE001", "管理渠道"), APPORGCODE("JHBS", "APP网点"), APP1CHANNEL("app001",
                                                                       "新华联合APP"), APP2CHANNEL(
        "app002", "泉州通APP"), VIPORGCODE("JHBS", "小程序网点"), VIPCHANNEL("VIP",
                                                                     "会员卡小程序"), CLIENTCHANNEL(
        "CLIENT", "客户端小程序"), CLIENTORGCODE("JHBS", "客户端小程序网点");

    private String code;

    private String desn;

    SocketConstantEnum(String code, String desn) {
        this.code = code;
        this.desn = desn;
    }

    public String getCode() {
        return code;
    }

    public String getDesn() {
        return desn;
    }
}
