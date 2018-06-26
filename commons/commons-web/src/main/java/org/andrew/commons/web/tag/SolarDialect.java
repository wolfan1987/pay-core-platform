package org.andrew.commons.web.tag;

import com.alibaba.dubbo.config.annotation.Reference;
//import com.zjht.solar.ecard.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 自定义标签。
 * Created by andrewliu on 2017/6/5.
 */
public class SolarDialect extends AbstractProcessorDialect {

    /**
     * 标签名称。
     */
    private static final String NAME = "Solar";

    //    private static final String PREFIX = "shiro";
    /**
     * 标签前缀。
     */
    private static final String PREFIX = "sl";

    @Autowired
    private RedisTemplate redisTemplate;

//    @Reference(version = "1.0.0")
//    private ItemDao itemDao;

    public SolarDialect() {
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        return createStandardProcessorsSet(dialectPrefix);
    }

    /**
     * 有先后顺序。
     * SolarXXXProcessor中PRECEDENCE值越小越先执行
     *
     * @param dialectPrefix 前缀
     * @return 返回值
     */
    private Set<IProcessor> createStandardProcessorsSet(String dialectPrefix) {
        LinkedHashSet<IProcessor> processors = new LinkedHashSet<>();
        processors.add(new SolarTypeTagProcessor(dialectPrefix));
//        processors.add(new SolarTextTagProcessor(dialectPrefix, redisTemplate, itemDao));
//        processors.add(new SolarHiddenTagProcessor(dialectPrefix, redisTemplate, itemDao));
        processors.add(new SolarPreTagProcessor(dialectPrefix));
        processors.add(new SolarSufTagProcessor(dialectPrefix));
        processors.add(new SolarHideTagProcessor(dialectPrefix));
        processors.add(new SolarHideTextTagProcessor(dialectPrefix));
        processors.add(new SolarPhoneTagProcessor(dialectPrefix));
        processors.add(new SolarPanTagProcessor(dialectPrefix));
        processors.add(new SolarCouponTagProcessor(dialectPrefix));
        return processors;
    }
}