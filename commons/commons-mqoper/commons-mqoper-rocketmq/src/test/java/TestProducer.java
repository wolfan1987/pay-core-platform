import org.andrew.commons.mqoper.api.MQSimpleContext;
import org.andrew.commons.mqoper.config.MQContextEnvConfig;
import org.andrew.commons.mqoper.emnus.LoggerEnums;
import org.andrew.commons.mqoper.rkt.impl.DefaultMQSimpleContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    private  static  final String namesrvName = "";

    @Before
    public void  setUp(){
        mqSimpleContext = new DefaultMQSimpleContext();
        contextEnvConfig = new MQContextEnvConfig();
        contextEnvConfig.setLoggerEnums(LoggerEnums.LOGGER4j2_CLASSPATH);
        contextEnvConfig.setMqNameServers(namesrvName);

    }

    @Test
    public void  testStartMQContext(){

    }


    @After
    public void setDown(){

    }

}
