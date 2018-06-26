package org.andrew.commons.ecard.scan;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.TimeZone;

/**
 * 解决使用Jackson的@JsonFormat注解 差8小时的问题。
 * 比如数据库存的日期是2015-01-05,转成json则变成了2015-01-04
 * @author andrewliu
 */
@Component("jacksonObjectMapper")
public class JacksonObjectMapper extends ObjectMapper {

    private static final Locale CHINA = Locale.CHINA;

    /**
     * jackson转换。
     */
    public JacksonObjectMapper() {
        this.setLocale(CHINA);
        //this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", CHINA));
        this.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

}
