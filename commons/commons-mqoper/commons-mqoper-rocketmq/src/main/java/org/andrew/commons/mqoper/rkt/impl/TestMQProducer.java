package org.andrew.commons.mqoper.rkt.impl;

import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.rkt.abstracts.AbstractProducer;
import org.andrew.commons.mqoper.rkt.model.MQProduceResult;
import org.andrew.commons.mqoper.rkt.model.TestProduceMessage;
import org.andrew.commons.mqoper.config.ProduceConfig;
import org.andrew.commons.mqoper.entitys.ProduceResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/10 18:55
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class TestMQProducer  extends AbstractProducer<TestProduceMessage,ProduceConfig,MQProduceResult>{

//    @Override
//    public MQProduceResult doSend(TestProduceMessage produceMessage) throws ProducerException {
//        try {
//            if(preProcessor(produceMessage)){
//                SendResult   sendResult = this.send(sendMessage);
//                MQProduceResult produceResult = new MQProduceResult();
//                produceResult.setProduceResult(sendResult);
//                return produceResult;
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (RemotingException e) {
//            e.printStackTrace();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        } catch (MQBrokerException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public void setConfig(ProduceConfig config) {
        this.config = config;
    }

    @Override
    public ProduceConfig getConfig() {
       return this.config;
    }

}
