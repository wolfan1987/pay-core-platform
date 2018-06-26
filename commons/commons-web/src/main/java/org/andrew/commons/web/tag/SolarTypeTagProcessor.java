package org.andrew.commons.web.tag;

import org.thymeleaf.standard.processor.AbstractStandardAttributeModifierTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * 自定义标签--数据字典类型。
 * Created by andrewliu on 2017/6/5.
 */
public final class SolarTypeTagProcessor extends AbstractStandardAttributeModifierTagProcessor {
    public static final int    PRECEDENCE = 1000;
    public static final String ATTR_NAME  = "type";

    public SolarTypeTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, PRECEDENCE, false);
    }
}
