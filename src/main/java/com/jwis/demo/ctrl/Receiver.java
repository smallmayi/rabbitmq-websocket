package com.jwis.demo.ctrl;

import com.alibaba.fastjson.JSON;
import com.jwis.demo.dao.BopDao;
import com.jwis.demo.service.inter.BopInter;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@RabbitListener(queues = "messagechange")
@Controller
public class Receiver {

    @Autowired
    BopInter bopInter;
    //右键监听结果
    @RabbitHandler
    public void receiver(BopDao bopDao,Message message,Channel channel) throws Exception {
        log.info("<=============监听到队列消息============>");
        boolean flag = TestCtrl.flag;
        log.info("flag:"+flag);
        if(bopDao!=null&&flag==true){
            for ( WsgCtrl item : WsgCtrl.webSocketSet ){

                item.sendMessage(JSON.toJSONString(bopDao));
            }
            bopInter.updateBop(bopDao);
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);

        }
//        else {
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
//        }

    }
}
