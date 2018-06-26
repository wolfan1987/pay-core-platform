package org.andrew.commons.web.tag;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.util.LazyEscapingCharSequence;
import org.unbescape.html.HtmlEscape;
import org.unbescape.xml.XmlEscape;

/**
 * Created by zxy on 2018/4/4.。
 */
public class TagUtil {

    /**
     * getCharSequence。
     */
    public static CharSequence getCharSequence(
        ITemplateContext context, Object expressionResult, TemplateMode templateMode) {
        CharSequence text;
        if (templateMode != TemplateMode.JAVASCRIPT && templateMode != TemplateMode.CSS) {
            final String input = (expressionResult == null ? "" : expressionResult.toString());
            if (templateMode == TemplateMode.RAW) {
                text = input;
            } else {
                if (input.length() > 100) {
                    text = new LazyEscapingCharSequence(
                        context.getConfiguration(), templateMode, input);
                } else {
                    text = produceEscapedOutput(templateMode, input);
                }

            }
        } else {
            text = new LazyEscapingCharSequence(
                context.getConfiguration(), templateMode, expressionResult);

        }
        return text;
    }

    /**
     * produceEscapedOutput。
     */
    private static String produceEscapedOutput(
        final TemplateMode templateMode, final String input) {
        switch (templateMode) {
            case TEXT:
                // fall-through
            case HTML:
                return HtmlEscape.escapeHtml4Xml(input);
            case XML:
                return XmlEscape.escapeXml10(input);
            default:
                throw new TemplateProcessingException("Unrecognized template mode " + templateMode +
                                                      ". Cannot produce escaped output for " +
                                                      "this template mode.");
        }

    }
}
