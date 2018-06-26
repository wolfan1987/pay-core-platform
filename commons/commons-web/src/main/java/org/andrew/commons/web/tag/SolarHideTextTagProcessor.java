package org.andrew.commons.web.tag;

import org.thymeleaf.standard.processor.AbstractStandardAttributeModifierTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * 加密保留前位。
 * Created by andrewliu on 2017/6/27.
 */
public class SolarHideTextTagProcessor extends AbstractStandardAttributeModifierTagProcessor {
    public static final int    PRECEDENCE = 1003;
    public static final String ATTR_NAME  = "hideText";

    public SolarHideTextTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, PRECEDENCE, false);
    }
}
