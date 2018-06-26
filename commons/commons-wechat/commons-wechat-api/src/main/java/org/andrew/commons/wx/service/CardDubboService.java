package org.andrew.commons.wx.service;



import org.andrew.commons.wechat.model.card.Card;
import org.andrew.commons.wechat.model.message.TemplateMsg;
import org.andrew.commons.wechat.vo.CardCreateReqVo;
import org.andrew.commons.wechat.vo.CardInfoUpdateReqVo;
import org.andrew.commons.wechat.vo.member.update.UserInfoUpdateVo;

import java.io.File;
import java.util.Map;

/**
 * 微信会员卡相关dubbo服务接口。
 *
 * @author andrewliu
 * @author andrewliu update
 */
public interface CardDubboService {

    /**
     * 创建会员卡。
     *
     * @param cardCreateReq 创建卡的定义字段
     * @return cardId
     */
    String create(CardCreateReqVo cardCreateReq);

    /**
     * 上传图片。
     *
     * @param file 要上传的文件
     * @return 上传后图片在微信中的url
     */
    String uploadImg(File file);

    /**
     * 激活会员卡。
     *
     * @param cardCode 卡号
     * @param cardId   cardId
     */
    void activate(String cardCode, String cardId);

    /**
     * 更新会员信息。
     * true成功 false失败
     */
    boolean updateUser(UserInfoUpdateVo updateCardVo);

    /**
     * 更新会员卡信息。
     * 更新成功后，查询会员卡信息，返回给调用端。
     *
     * @param cardId            cardId
     * @param cardCode          卡号
     * @param cardInfoUpdateReq 会员卡更新字段
     * @return Card 更新成功  null更新失败
     */
    Card updateCard(String cardId, String cardCode, CardInfoUpdateReqVo cardInfoUpdateReq);

    /**
     * 更新会员卡信息。
     * 更新成功后，查询会员卡信息，返回给调用端。
     *
     * @param cardId            cardId
     * @param cardInfoUpdateReq 会员卡更新字段
     * @return true 更新成功  false更新失败
     */
    boolean updateCardByCardId(String cardId, CardInfoUpdateReqVo cardInfoUpdateReq);

    /**
     * 查询会员卡信息。
     *
     * @param cardId 卡号
     * @return Card会员卡信息
     */
    Card getByCardId(String cardId);

    /**
     * 更新用户余额。
     *
     * @param backGroundUrl 背景图片地址，如果为空表示不更新背景
     * @param cardCode      卡号
     * @param balance       需要设置的余额全量值，传入的数值会直接显示在卡面
     * @param addBalance    本次余额变动值，传负数代表减少
     * @param recordBalance 商家自定义金额消耗记
     * @return true成功 false失败
     */
    boolean updateBalance(
            String backGroundUrl, String cardCode, long balance, long addBalance, String recordBalance);

    /**
     * 删除会员卡。
     * 删除卡券后，该卡券对应已生成的领取用二维码、添加到卡包JS API均会失效，
     * 但是不会删除已经被用户添加到客户端的卡券,这些卡券依旧有效。
     *
     * @param cardId 卡券id
     */
    void delete(String cardId);

    /**
     * 设置会员卡失效。
     *
     * @param code   设置失效的Code码。
     * @param reason 失效理由
     */
    void unavailable(String code, String reason);

    /**
     * 批量查询微信卡ID。
     *
     * @param offset 偏移量
     * @param count  数量
     * @return map
     */
    Map<String, Object> getBatchCardId(Integer offset, Integer count);


    /**
     * 发送模板消息。
     *
     * @param templateMsg 模板消息对象参数
     */
    void sendTemplateMsg(TemplateMsg templateMsg);

}
