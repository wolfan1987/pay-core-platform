package org.andrew.commons.web.tag;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;


/**
 * 数据字典自定义标签--text显示。
 * Created by andrewliu on 2017/6/5.
 */
public final class SolarHiddenTagProcessor extends AbstractStandardExpressionAttributeTagProcessor {

   // private ItemDao itemDao;

    private RedisTemplate redisTemplate;

    public static final int    PRECEDENCE = 1400;
    public static final String ATTR_NAME  = "hidden";

    /**
     *构造。
     */
    public SolarHiddenTagProcessor(final String dialectPrefix, RedisTemplate redisTemplate, Object itemDao) {
        super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, PRECEDENCE, true);
        this.redisTemplate = redisTemplate;
       // this.itemDao = itemDao;
    }

    @Override
    protected void doProcess(
        final ITemplateContext context, final IProcessableElementTag tag,
        final AttributeName attributeName, final String attributeValue,
        final Object expressionResult, final IElementTagStructureHandler structureHandler) {
        String textHtml = "";
        if (attributeValue != null && !"".equals(attributeValue)) {
            textHtml = textHtml(attributeValue);
        }
        structureHandler.setBody(textHtml, false);

    }

    /**
     * 获取数据字典生成input隐藏域。
     *
     * @param category 类型
     * @return input隐藏域html
     */
    private String textHtml(String category) {
//        ValueOperations<String, ItemVo> valueOps = redisTemplate.opsForValue();
//        ItemVo itemDto = valueOps.get(category);
//        if (itemDto == null) {
//            List<ItemVo> list = itemDao.findByCategory(category);
//            if (list != null && list.size() == 1) {
//                itemDto = list.get(0);
//                valueOps.set(category, itemDto);
//            } else {
//                return "";
//            }
//        }
//        List<ItemDetail> itemDetails = itemDto.getItemDetails();
//        StringBuilder html = new StringBuilder();
//        if (itemDetails != null && itemDetails.size() > 0) {
//            for (ItemDetail bean : itemDetails) {
//                html.append("<input type='hidden' id='");
//                html.append(category);
//                html.append("_");
//                html.append(bean.getCode());
//                html.append("' value='");
//                html.append(bean.getName());
//                html.append("'/>");
//            }
//        }
      //  return html.toString();
        return null;
    }
}
