import org.andrew.commons.exception.context.ContextException;
import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.api.MQSimpleContext;
import org.andrew.commons.mqoper.api.Producer;
import org.andrew.commons.mqoper.config.MQContextEnvConfig;
import org.andrew.commons.mqoper.config.ProduceConfig;
import org.andrew.commons.mqoper.emnus.LoggerEnums;
import org.andrew.commons.mqoper.entitys.ProduceMessage;
import org.andrew.commons.mqoper.rkt.entityext.ProduceMessageExt;
import org.andrew.commons.mqoper.rkt.impl.DefaultMQSimpleContext;
import org.andrew.commons.mqoper.rkt.impl.TestMQProducer;
import org.andrew.commons.mqoper.rkt.model.TestConsumeMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/13 15:48
 * @Modifyed By:
 * @Other: A Lucky Man
 */

public class TestProducer {

    MQSimpleContext  mqSimpleContext = null;
    MQContextEnvConfig   contextEnvConfig = null;
    TestMQProducer  testMQProducer = null;
    ProduceConfig  produceConfig = null;
    TestConsumeMessage   testConsumeMessage = null;
    Map<String,Producer> producerMap = new HashMap<String,Producer>();
    private  static  final String namesrvName = "172.16.94.221:9876;172.16.94.224:9876;172.16.94.225:9876";

    @Before
    public void  setUp(){
        //初始化MQ上下文
        mqSimpleContext = new DefaultMQSimpleContext();
        //初始化上下配置
        contextEnvConfig = new MQContextEnvConfig();
        contextEnvConfig.setLoggerName(LoggerEnums.LOGGER4j2_CLASSPATH.name());
        contextEnvConfig.setMqNameServers(namesrvName);
        mqSimpleContext.setMQContextEnvConfig(contextEnvConfig);
        //初始化生产配置
        produceConfig = new ProduceConfig();
        produceConfig.setGroupName("lda");
        produceConfig.setTopic("testtopic");
        //初始化生产者交绑定配置
        testMQProducer = new TestMQProducer(produceConfig);
        //将Producer放到MQContext中
        producerMap.put("lda".concat("#").concat("testtopic"),testMQProducer);
        mqSimpleContext.setProducerMap(producerMap);
    }

    @Test
    public void  testStartMQContext(){
        System.out.println("start  mq context..........");
        try {
            mqSimpleContext.init();
            ProduceMessageExt  produceMessageExt = new ProduceMessageExt();
            produceMessageExt.setGroupName("lda");
            produceMessageExt.setTopic("testtopic2");
            produceMessageExt.setContent("Hello  MQ222.....");
            produceMessageExt.setMsgId(UUID.randomUUID().toString());
            mqSimpleContext.produce(produceMessageExt);
        } catch (ContextException e) {
            e.printStackTrace();
        } catch (ProducerException e) {
            e.printStackTrace();
        }

    }




    @After
    public void setDown(){
        mqSimpleContext.destroy();
    }

}
