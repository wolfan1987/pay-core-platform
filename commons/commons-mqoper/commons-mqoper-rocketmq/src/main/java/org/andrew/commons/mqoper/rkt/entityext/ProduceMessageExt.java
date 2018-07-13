package org.andrew.commons.mqoper.rkt.entityext;

import org.andrew.commons.mqoper.entitys.ProduceMessage;
import org.apache.rocketmq.common.message.Message;

import java.util.List;
import java.util.Map;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/9 15:39
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class ProduceMessageExt extends ProduceMessage{

    private  String  tags;
    
    private List<String> listKey;

    private  List<Message>   listMessage;

    private Map<String,String> userProperty;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getListKey() {
        return listKey;
    }

    public void setListKey(List<String> listKey) {
        this.listKey = listKey;
    }

    public List<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<Message> listMessage) {
        this.listMessage = listMessage;
    }

    public Map<String, String> getUserProperty() {
        return userProperty;
    }

    public void setUserProperty(Map<String, String> userProperty) {
        this.userProperty = userProperty;
    }
}
