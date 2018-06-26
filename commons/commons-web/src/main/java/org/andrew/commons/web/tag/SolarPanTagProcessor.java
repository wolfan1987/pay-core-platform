package org.andrew.commons.web.tag;


import org.andrew.commons.utils.other.TransformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;


/**
 * 卡号格式化。
 * 格式化前：6220202266667777888
 * 格式化后：6220 2022 6666 7777 888
 * Created by andrewliu on 2017/11/15.
 */
public final class SolarPanTagProcessor extends AbstractStandardExpressionAttributeTagProcessor {


    public static final int PRECEDENCE   = 1100;
    public static final String ATTR_NAME = "pan";

    private static final Logger log = LoggerFactory.getLogger(SolarPanTagProcessor.class);

    public SolarPanTagProcessor(final String dialectPrefix) {
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
        String textValue = TransformUtil.transform(text.toString());
        // Report the result to the engine, whichever the type of process we have applied
        structureHandler.setBody(textValue, false);
    }


}
