package org.andrew.commons.wechat.service;



import org.andrew.commons.wechat.model.card.Card;
import org.andrew.commons.wechat.model.message.TemplateMsg;
import org.andrew.commons.wechat.vo.CardCreateReqVo;
import org.andrew.commons.wechat.vo.CardInfoUpdateReqVo;
import org.andrew.commons.wechat.vo.member.update.UserInfoUpdateVo;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 微信卡券相关接口。
 *
 * @author andrewliu
 */
public interface WechatCardService {

    /**
     * 创建卡券上传图片到微信。
     *
     * @param file  准备上传的文件
     * @param token 微信token
     * @return 返回上传后的微信图片路径
     */
    String uploadImg(File file, String token);

    /**
     * 创建会员卡。
     *
     * @param token           token令牌
     * @param cardCreateReqVo card信息
     * @return cardId
     * @throws Exception exception
     */
    String createCard(String token, CardCreateReqVo cardCreateReqVo);

    /**
     * 获取会员卡信息。
     *
     * @param token  token令牌
     * @param cardId 微信会员卡id/cardId
     * @return 微信会员卡信息
     */
    Card getByCardId(String token, String cardId);

    /**
     * 设置测试白名单。
     *
     * @param token   微信会员卡令牌
     * @param openIds openid列表
     */
    void setTestWhiteListByOpenId(String token, List<String> openIds);

    /**
     * 激活会员卡。
     *
     * @param userCardCode 用户会员卡号
     */
    void activate(String userCardCode, String cardId, String token);

    /**
     * 更新会员信息。
     *
     * @param token          微信token令牌
     * @param userInfoUpdate 会员信息更新实体
     * @return {"errcode": 0,"errmsg": "ok", "result_bonus": 100,
     "result_balance": 200,"openid": "oFS7Fjl0WsZ9AMZqrI80nbIq8xrA"} errcode=0标识成功
     */
    Map updateUser(String token, UserInfoUpdateVo userInfoUpdate);

    /**
     * 更新会员卡信息。
     *
     * @param token             微信token令牌
     * @param cardId            微信cardId
     * @param cardInfoUpdateReq 微信需要更新的字段
     * @return {"errcode":0,"errmsg":"ok","send_check"：true} errcode =0标识成功
     */
    Map<String, Object> updateCard(
            String token, String cardId, CardInfoUpdateReqVo cardInfoUpdateReq);

    /**
     * 设置会员卡失效。
     *
     * @param token  token令牌
     * @param cardId cardId
     * @param code   设置失效的Code码。
     * @param reason 失效理由
     */
    void unavailable(String token, String cardId, String code, String reason);

    /**
     * 删除会员卡。
     *
     * @param token  token令牌
     * @param cardId 卡券id
     */
    void delete(String token, String cardId);


    /**
     * 批量查询微信卡ID。
     *
     * @param offset 偏移量
     * @param count  数量
     * @return map
     */
    Map<String, Object> getBatchCardId(String token, Integer offset, Integer count);

    /**
     * 提醒会员卡失效日期。
     * @param token token令牌
     * @param templateMsg 模板消息
     */
    void sendTemplateMsg(String token, TemplateMsg templateMsg);
}
