package org.andrew.commons.web.tag;

import org.thymeleaf.standard.processor.AbstractStandardAttributeModifierTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * 加密保留前位。
 * Created by andrewliu on 2017/6/27.
 */
public class SolarPreTagProcessor extends AbstractStandardAttributeModifierTagProcessor {
    public static final int    PRECEDENCE = 1001;
    public static final String ATTR_NAME  = "pre";

    public SolarPreTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, PRECEDENCE, false);
    }
}
