package org.andrew.commons.web.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;


/**
 * 数据字典自定义标签--text显示。
 * Created by andrewliu on 2017/6/5.
 */
public final class SolarHideTagProcessor extends AbstractStandardExpressionAttributeTagProcessor {


    public static final int PRECEDENCE   = 1200;
    public static final String ATTR_NAME = "hide";

    private static final Logger log = LoggerFactory.getLogger(SolarHideTagProcessor.class);

    public SolarHideTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, PRECEDENCE, true);
    }


    @Override
    protected void doProcess(
        final ITemplateContext context, final IProcessableElementTag tag,
        final AttributeName attributeName, final String attributeValue,
        final Object expressionResult, final IElementTagStructureHandler structureHandler) {

        final TemplateMode templateMode = getTemplateMode();

        final CharSequence text;
        text = TagUtil.getCharSequence(context, expressionResult, templateMode);

        String preText = ThymeleafFacade.getRawValue(tag, "pre");//前保留位
        int pre = 4;
        if (preText != null && !"".equals(preText)) {
            try {
                pre = Integer.parseInt(preText);
            } catch (NumberFormatException ex) {
                pre = 4;
                log.info("加密前置pre({})：{}", preText, ex);
            }
        }
        int suf = 3;
        String sufText = ThymeleafFacade.getRawValue(tag, "suf");//后保留位
        if (sufText != null && !"".equals(sufText)) {
            try {
                suf = Integer.parseInt(sufText);
            } catch (NumberFormatException ex) {
                suf = 3;
                log.info("加密后置suf({})：{}", sufText, ex);
            }
        }
        String hideText = null;
        try {
            hideText = ThymeleafFacade.getRawValue(tag, "hideText");
        } catch (Exception ex) {
            log.debug("未明确定义加密替换字符hideText,默认使用*替换");
        }
        String textValue = HideUtil.getHideString(pre, suf, text.toString(), hideText);
        // Report the result to the engine, whichever the type of process we have applied
        structureHandler.setBody(textValue, false);
    }


}
