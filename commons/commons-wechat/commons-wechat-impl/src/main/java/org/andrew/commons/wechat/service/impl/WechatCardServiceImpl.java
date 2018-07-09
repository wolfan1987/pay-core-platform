package org.andrew.commons.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;

import org.andrew.commons.wechat.constants.CardUrlConstant;
import org.andrew.commons.wechat.constants.CodeTypeEnum;
import org.andrew.commons.wechat.model.card.*;
import org.andrew.commons.wechat.model.message.TemplateMsg;
import org.andrew.commons.wechat.service.WechatCardService;
import org.andrew.commons.wechat.vo.ActivateCardVo;
import org.andrew.commons.wechat.vo.CardCreateReqVo;
import org.andrew.commons.wechat.vo.CardInfoUpdateReqVo;
import org.andrew.commons.wechat.vo.card.update.BaseinfoUpdateVo;
import org.andrew.commons.wechat.vo.card.update.CardUpdateInfoVo;
import org.andrew.commons.wechat.vo.card.update.MemberCardVo;
import org.andrew.commons.wechat.vo.member.update.UserInfoUpdateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卡券相关操作。
 *
 * @author andrewliu
 */
@Service("wechatCardService")
public class WechatCardServiceImpl implements WechatCardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatCardServiceImpl.class);

    @Override
    public String uploadImg(File file, String token) {
        WritableResource resource = new FileSystemResource(file);
        MultiValueMap<String, WritableResource> data = new LinkedMultiValueMap<>();
        data.add("buffer", resource);
        String urlString = CardUrlConstant.UPLOAD_IMG;
        RestTemplate rest = new RestTemplate();
        String result = rest.postForObject(urlString, data, String.class, token);
        Map<String, String> map = JSONObject.parseObject(result, Map.class);
        String path = map.get("url");
        if (null == path) {
            throw new RuntimeException(String.format("上传文件失败%s", JSONObject.toJSONString(map)));
        }
        return path;
    }

    @Override
    public String createCard(String token, CardCreateReqVo cardCreateReqVo) {
        Map cardMap = packageCardData(cardCreateReqVo);
        RestTemplate rest = new RestTemplate();
        String json = JSONObject.toJSONString(cardMap);
        cardMap = JSONObject.parseObject(json, Map.class);
        LOGGER.info("转换后的json值:{}", JSONObject.toJSONString(cardMap));
        Map map = rest.postForObject(CardUrlConstant.CARD_CREATE_URL, cardMap, Map.class, token);
        int errorCode = map.get("errcode") == null ? -1 : (int) map.get("errcode");
        if (0 != errorCode) {
            String msg = map.get("errmsg").toString();
            LOGGER.error("创建卡出错:{}", msg);
            throw new RuntimeException("创建卡出错:" + msg);
        }
        return map.get("card_id").toString();
    }

    private Map<String, Object> packageCardData(CardCreateReqVo cardCreateReqVo) {
        BaseInfo baseInfo = new BaseInfo.Builder(CodeTypeEnum.CODE_TYPE_TEXT).logoUrl(
            cardCreateReqVo.getLogoUrl()).brandName(cardCreateReqVo.getBrandName()).title(
            cardCreateReqVo.getCardTitle()).color(cardCreateReqVo.getColor()).notice(
            cardCreateReqVo.getNotice()).servicePhone(
            cardCreateReqVo.getServicePhone()).description(
            cardCreateReqVo.getDescription()).dateInfo(cardCreateReqVo.getDateInfo()).sku(
            cardCreateReqVo.getQuantity()).getLimit(cardCreateReqVo.getLimit()).useCustomCode(
            cardCreateReqVo.isUseCustomCode()).canGiveFriend(
            cardCreateReqVo.isCanGiveFriend()).customUrlName(
            cardCreateReqVo.getCustomUrlName()).customUrl(
            cardCreateReqVo.getCustomUrl()).customUrlSubTitle(
            cardCreateReqVo.getCustomUrlSubTitle()).promotionUrlName(
            cardCreateReqVo.getPromotionUrlName()).promotionUrl(
            cardCreateReqVo.getPromotionUrl()).centerTitle(
            cardCreateReqVo.getCenterName()).centerUrl(
            cardCreateReqVo.getCenterUrl()).centerSubTitle(
            cardCreateReqVo.getCenterSubTitle()).useCustomCode(
            cardCreateReqVo.isUseCustomCode()).bindOpenId(
            cardCreateReqVo.isBindOpenId()).needPushOnView(true).build();

        MemberCard memberCard = new MemberCard.Builder().baseInfo(baseInfo).supplyBonus(
            false).customCell(cardCreateReqVo.getCustomCellName(),
                              cardCreateReqVo.getCustomCellTips(),
                              cardCreateReqVo.getCustomCellUrl()).supplyBalance(
            cardCreateReqVo.isSupplyBalance()).balanceUrl(
            cardCreateReqVo.getBalanceUrl()).prerogative(
            cardCreateReqVo.getPrerogative()).autoActivate(
            cardCreateReqVo.isAutoActivate()).backgroundPicUrl(
            cardCreateReqVo.getBackgroundPicUrl()).activateUrl(
            cardCreateReqVo.getActivateUrl()).disCount(cardCreateReqVo.getDiscount()).build();
        Card card = new Card();
        card.setCardType(cardCreateReqVo.getCardType());
        card.setMemberCard(memberCard);
        Map<String, Object> map = new HashMap<>();
        map.put("card", card);
        return map;
    }

    /**
     * 获取微信会员卡信息。
     *
     * @param token  token令牌
     * @param cardId 微信会员卡id，cardId
     * @return 微信会员卡信息
     * @throws Exception excception
     */
    public Card getByCardId(String token, String cardId) {
        String url = CardUrlConstant.CARD_GET_URL;
        RestTemplate rest = new RestTemplate();
        Map<String, String> param = new HashMap<>();
        param.put("card_id", cardId);
        Map<String, Object> map = rest.postForObject(url, param, Map.class, token);
        int errorCode = map.get("errcode") == null ? -1 : (int) map.get("errcode");
        if (0 != errorCode) {
            String msg = map.get("errmsg").toString();
            LOGGER.error("获取卡信息出错:{}", msg);
            throw new RuntimeException("获取卡信息出错:" + msg);
        }
        Object obj = map.get("card");
        String cardInfoStr = JSONObject.toJSONString(obj);
        return JSONObject.parseObject(cardInfoStr, Card.class);
    }

    /**
     * 设置微信测试白名单。
     *
     * @param token   token令牌
     * @param openIds opendId列表
     */
    public void setTestWhiteListByOpenId(String token, List<String> openIds) {
        RestTemplate rest = new RestTemplate();
        Map<String, List<String>> paramMap = new HashMap<>();
        paramMap.put("openid", openIds);
        Map resultMap = rest.postForObject(
            CardUrlConstant.TEST_WHITE_LIST, paramMap, Map.class, token);
        int errorCode = resultMap.get("errcode") == null ? -1 : (int) resultMap.get("errcode");
        if (0 != errorCode) {
            String msg = resultMap.get("errmsg").toString();
            LOGGER.error("设置测试白名单失败:{}", msg);
            throw new RuntimeException(String.format("设置测试白名单失败:%s", msg));
        }
    }

    /**
     * 激活会员卡。
     *
     * @param userCardCode 用户卡号
     */
    public void activate(String userCardCode, String cardId, String token) {
        RestTemplate rest = new RestTemplate();
        ActivateCardVo activateCard = new ActivateCardVo();
        activateCard.setCode(userCardCode);
        activateCard.setInitBalance("0");
        activateCard.setMembershipNumber(userCardCode);
        activateCard.setCardId(cardId);
        String jsonStr = JSONObject.toJSONString(activateCard);
        Map param = JSONObject.parseObject(jsonStr, Map.class);
        String resultStr = rest.postForObject(CardUrlConstant.ACTIVATE_URL, param, String.class,
                                              token);
        Map resultMap = JSONObject.parseObject(resultStr, Map.class);
        int errorCode = resultMap.get("errcode") == null ? -1 : (int) resultMap.get("errcode");
        if (0 != errorCode) {
            String msg = resultMap.get("errmsg").toString();
            LOGGER.error("会员卡:{},激活失败:{}", userCardCode, msg);
            throw new RuntimeException(String.format("会员卡:{%s},激活失败:{%s}", userCardCode, msg));
        }
    }

    /**
     * 更新用户信息。
     *
     * @param token        token令牌
     * @param updateCardVo 会员信息更新实体
     * @return 更新后的信息
     * @throws Exception exception
     */
    public Map<String, Object> updateUser(String token, @Valid UserInfoUpdateVo updateCardVo) {
        String code = updateCardVo.getCode();
        String jsonStr = JSONObject.toJSONString(updateCardVo);
        RestTemplate rest = new RestTemplate();
        Map param = JSONObject.parseObject(jsonStr, Map.class);
        String resultStr = rest.postForObject(CardUrlConstant.UPDATE_URL, param, String.class,
                                              token);
        LOGGER.debug("会员卡更新微信返回结果：{}", resultStr);
        Map resultMap = JSONObject.parseObject(resultStr, Map.class);
        int errorCode = resultMap.get("errcode") == null ? -1 : (int) resultMap.get("errcode");
        if (0 != errorCode) {
            String msg = resultMap.get("errmsg").toString();
            LOGGER.error("会员信息:{},更新失败:{}", code, msg);
            throw new RuntimeException(String.format("会员信息更新失败:%s", msg));
        }
        return resultMap;
    }

    /**
     * 更新会员卡信息。
     *
     * @param token             微信token令牌
     * @param cardId            微信cardId
     * @param cardInfoUpdateReq 微信需要更新的字段
     * @return {"errcode":0,"errmsg":"ok"}
     */
    public Map<String, Object> updateCard(
        String token, String cardId, CardInfoUpdateReqVo cardInfoUpdateReq) {
        CardUpdateInfoVo card = new CardUpdateInfoVo();
        card.setCardId(cardId);
        MemberCardVo memberCardVo = new MemberCardVo();
        memberCardVo.setDiscount(cardInfoUpdateReq.getDiscount());

        MemberCardVo memberCard = new MemberCardVo();
        memberCard.setWxActivate(cardInfoUpdateReq.getWxActivate());
        memberCard.setActivateUrl(cardInfoUpdateReq.getActivateUrl());
        memberCard.setSupplyBonus(cardInfoUpdateReq.getSupplyBonus());
        memberCard.setPrerogative(cardInfoUpdateReq.getPrerogative());
        memberCard.setDiscount(cardInfoUpdateReq.getDiscount());
        memberCard.setCustomField3(cardInfoUpdateReq.getCustomField3());
        memberCard.setCustomField2(cardInfoUpdateReq.getCustomField2());
        memberCard.setCustomField1(cardInfoUpdateReq.getCustomField1());
        memberCard.setCustomCell1(cardInfoUpdateReq.getCustomCell());
        memberCard.setBonusUrl(cardInfoUpdateReq.getBonusUrl());
        memberCard.setBonusRules(cardInfoUpdateReq.getBonusRules());
        memberCard.setBonusRule(cardInfoUpdateReq.getBonusRule());
        memberCard.setBonusCleared(cardInfoUpdateReq.getBonusCleared());
        memberCard.setBalanceUrl(cardInfoUpdateReq.getBalanceUrl());
        memberCard.setBackgroundPicUrl(cardInfoUpdateReq.getBackgroundPicUrl());

        BaseinfoUpdateVo baseinfoUpdateVo = new BaseinfoUpdateVo();
        baseinfoUpdateVo.setUseAllLocations(cardInfoUpdateReq.getUseAllLocations());
        baseinfoUpdateVo.setTitle(cardInfoUpdateReq.getTitle());
        baseinfoUpdateVo.setServicePhone(cardInfoUpdateReq.getServicePhone());
        baseinfoUpdateVo.setPromotionUrlSubTitle(cardInfoUpdateReq.getPromotionUrlSubTitle());
        baseinfoUpdateVo.setPromotionUrlName(cardInfoUpdateReq.getPromotionUrlName());
        baseinfoUpdateVo.setPromotionUrl(cardInfoUpdateReq.getPromotionUrl());
        PayInfo payInfo = new PayInfo();
        //支付结构体
        SwipCard swipCard = new SwipCard();
        swipCard.setSwipeCard(cardInfoUpdateReq.getSwipeCard());
        swipCard.setPayAndQrcode(cardInfoUpdateReq.getPayAndQrcode());
        payInfo.setSwipCard(swipCard);
        baseinfoUpdateVo.setPayInfo(payInfo);
        baseinfoUpdateVo.setNotice(cardInfoUpdateReq.getNotice());
        baseinfoUpdateVo.setLogoUrl(cardInfoUpdateReq.getLogoUrl());
        baseinfoUpdateVo.setLocalIdList(cardInfoUpdateReq.getLocationIdList());
        baseinfoUpdateVo.setGetLimit(cardInfoUpdateReq.getGetLimit());
        baseinfoUpdateVo.setDescription(cardInfoUpdateReq.getDescription());
        baseinfoUpdateVo.setDateInfo(cardInfoUpdateReq.getDateInfo());
        baseinfoUpdateVo.setCustomUrlSubTitle(cardInfoUpdateReq.getCustomUrlSubTitle());
        baseinfoUpdateVo.setCustomUrlName(cardInfoUpdateReq.getCustomUrlName());
        baseinfoUpdateVo.setCustomUrl(cardInfoUpdateReq.getCustomUrl());
        LOGGER.info("customUrl：" + cardInfoUpdateReq.getCustomUrl());
        baseinfoUpdateVo.setColor(cardInfoUpdateReq.getColor());
        baseinfoUpdateVo.setCodeType(cardInfoUpdateReq.getCodeType());
        baseinfoUpdateVo.setCenterUrl(cardInfoUpdateReq.getCenterUrl());
        baseinfoUpdateVo.setCenterTitle(cardInfoUpdateReq.getCenterTitle());
        baseinfoUpdateVo.setCenterSubTitle(cardInfoUpdateReq.getCenterSubTitle());
        baseinfoUpdateVo.setCanShare(cardInfoUpdateReq.getCanShare());
        baseinfoUpdateVo.setCanGiveFriend(cardInfoUpdateReq.getCanGiveFriend());
        memberCard.setBaseinfoUpdateVo(baseinfoUpdateVo);

        card.setMemberCard(memberCard);
        String jsonStr = JSONObject.toJSONString(card);
        LOGGER.info("更新会员卡报文内容：{}", jsonStr);
        Map param = JSONObject.parseObject(jsonStr, Map.class);
        RestTemplate rest = new RestTemplate();
        String resultStr = rest.postForObject(
            CardUrlConstant.UPDATE_CARD_URL, param, String.class, token);
        Map resultMap = JSONObject.parseObject(resultStr, Map.class);
        int errorCode = resultMap.get("errcode") == null ? -1 : (int) resultMap.get("errcode");
        if (0 != errorCode) {
            String msg = resultMap.get("errmsg").toString();
            LOGGER.error("会员卡:{},更新失败:{}", cardId, msg);
            throw new RuntimeException(String.format("会员卡更新失败:%s", msg));
        }
        return resultMap;
    }

    /**
     * 删除会员卡。
     * 删除卡券后，该卡券对应已生成的领取用二维码、添加到卡包JS API均会失效，
     * 但是不会删除已经被用户添加到客户端的卡券,这些卡券依旧有效。
     *
     * @param token  token令牌
     * @param cardId cardId 卡券id
     */
    public void delete(String token, String cardId) {
        RestTemplate rest = new RestTemplate();
        Map<String, String> param = new HashMap<>();
        param.put("card_id", cardId);
        String resultStr = rest.postForObject(
            CardUrlConstant.DELETE_CARD_URL, param, String.class, token);
        Map<String, Object> resultMap = JSONObject.parseObject(resultStr, Map.class);
        int errorCode = resultMap.get("errcode") == null ? -1 : (int) resultMap.get("errcode");
        if (0 != errorCode) {
            String msg = resultMap.get("errmsg").toString();
            LOGGER.error("会员卡:{},删除会员卡失败:{}", cardId, msg);
            throw new RuntimeException(String.format("删除会员卡失败:%s", msg));
        }
    }

    /**
     * 设置会员卡失效。
     *
     * @param token  token令牌
     * @param cardId cardId 卡券id
     * @param code   code 设置失效的Code码。
     * @param reason reason 失效理由
     */
    public void unavailable(String token, String cardId, String code, String reason) {
        RestTemplate rest = new RestTemplate();
        Map<String, String> param = new HashMap<>();
        param.put("card_id", cardId);
        param.put("code", code);
        param.put("reason", reason);
        String resultStr = rest.postForObject(
            CardUrlConstant.UNAVAILABLE_URL, param, String.class, token);
        Map resultMap = JSONObject.parseObject(resultStr, Map.class);
        LOGGER.info("设置卡券失效返回内容：" + resultStr);
        int errorCode = resultMap.get("errcode") == null ? -1 : (int) resultMap.get("errcode");
        LOGGER.info("返回码：" + errorCode);
        if (0 != errorCode) {
            String msg = resultMap.get("errmsg").toString();
            LOGGER.error("会员卡:{},设置会员卡失效失败:{}", cardId, msg);
            throw new RuntimeException(String.format("设置会员卡失效失败:%s", msg));
        }
    }

    @Override
    public Map<String, Object> getBatchCardId(String token, Integer offset, Integer count) {
        RestTemplate rest = new RestTemplate();
        Map<String, Object> param = new HashMap<>();
        param.put("offset", offset);
        param.put("count", count);
        String resultStr = rest.postForObject(
            CardUrlConstant.BATCH_CARD_LIST, param, String.class, token);
        Map<String, Object> resultMap = JSONObject.parseObject(resultStr, Map.class);
        int errorCode = resultMap.get("errcode") == null ? -1 : (int) resultMap.get("errcode");
        if (0 != errorCode) {
            String msg = resultMap.get("errmsg").toString();
            throw new RuntimeException(String.format("批量查询会员卡失败:%s", msg));
        }
        return resultMap;
    }

    @Override
    public void sendTemplateMsg(String token, TemplateMsg templateMsg) {
        RestTemplate rest = new RestTemplate();
        String requestUrl = CardUrlConstant.MOUDAL_URL;

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(
            "application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> requestEntity = new HttpEntity<String>(
            JSONObject.toJSONString(templateMsg), headers);

        String resultStr = rest.postForObject(
            requestUrl, requestEntity, String.class, token);
        Map resultMap = JSONObject.parseObject(resultStr, Map.class);
        LOGGER.info("发送模板消息返回内容：" + resultStr);
        int errorCode = resultMap.get("errcode") == null ? -1 : (int) resultMap.get("errcode");
        LOGGER.info("返回码：" + errorCode);
        if (0 != errorCode) {
            String msg = resultMap.get("errmsg").toString();
            LOGGER.error("发送模板消息失败:{}", msg);
            throw new RuntimeException(String.format("发送模板消息失败:%s", msg));
        }
    }

}
