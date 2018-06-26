package org.andrew.commons.wechat.constants;

/**
 * 卡券相关的url。
 *
 * @author andrewliu
 */
public interface CardUrlConstant {

    /**
     * 上传卡券logo
     * 上传的图片限制文件大小限制1MB，像素为300*300，仅支持JPG、PNG格式。
     */
    String UPLOAD_IMG = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token={token}";

    /**
     * 创建会员卡。
     */
    String CARD_CREATE_URL = "https://api.weixin.qq.com/card/create?access_token={token}";

    /**
     * 查看卡券详情。
     */
    String CARD_GET_URL = "https://api.weixin.qq.com/card/get?access_token={token}";

    /**
     * 设置测试白名单。
     */
    String TEST_WHITE_LIST = "https://api.weixin.qq.com/card/testwhitelist/set?access_token={token}";

    /**
     * 激活会员卡。
     */
    String ACTIVATE_URL = "https://api.weixin.qq.com/card/membercard/activate?access_token={token}";

    /**
     * 更新会员信息。
     */
    String UPDATE_URL = "https://api.weixin.qq.com/card/membercard/updateuser?access_token={token}";

    /**
     * 更改会员卡信息。
     */
    String UPDATE_CARD_URL = "https://api.weixin.qq.com/card/update?access_token={token}";

    /**
     * 删除卡券接口。
     * 删除卡券后，该卡券对应已生成的领取用二维码、添加到卡包JS API均会失效，
     * 但是不会删除已经被用户添加到客户端的卡券,这些卡券依旧有效。
     */
    String DELETE_CARD_URL = "https://api.weixin.qq.com/card/delete?access_token={token}";

    /**
     * 设置卡券失效接口。
     * 为满足改票、退款等异常情况，可调用卡券失效接口将用户的卡券设置为失效状态。
     */
    String UNAVAILABLE_URL = "https://api.weixin.qq.com/card/code/unavailable?access_token={token}";

    /**
     * 用户已领取的卡券。
     */
    String GET_CARD_LIST = "https://api.weixin.qq.com/card/user/getcardlist?access_token={token}";

    /**
     * 批量查询卡券列表。
     */
    String BATCH_CARD_LIST = "https://api.weixin.qq.com/card/batchget?access_token={token}";

    /**
     * 模板消息提醒会员卡失效。
     */
    String MOUDAL_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={token}";


}
