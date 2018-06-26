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
 * 券码格式化。
 * 格式化前：123456789
 * 格式化后：123-456-789
 * Created by andrewliu on 2017/11/15.
 */
public final class SolarCouponTagProcessor extends AbstractStandardExpressionAttributeTagProcessor {


    public static final int    PRECEDENCE = 1102;
    public static final String ATTR_NAME  = "coupon";

    private static final Logger log = LoggerFactory.getLogger(SolarCouponTagProcessor.class);

    public SolarCouponTagProcessor(final String dialectPrefix) {
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

        String textValue = TransformUtil.transform(text.toString(), 3, "-");
        // Report the result to the engine, whichever the type of process we have applied
        structureHandler.setBody(textValue, false);
    }
}
