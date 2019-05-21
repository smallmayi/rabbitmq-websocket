package com.jwis.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.jwis.demo.dao.BopDao;
import com.jwis.demo.dao.PartDao;
import com.jwis.demo.repo.PartRepo;
import com.jwis.demo.service.inter.BopInter;
import com.jwis.demo.service.inter.PartInter;
import com.jwis.demo.util.ExcelUtil;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PartImpl implements PartInter, RabbitTemplate.ConfirmCallback {
    @Autowired
    PartRepo partRepo;
    @Autowired
    BopInter bopInter;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Map> createPart() throws IOException {
        partRepo.deleteAllNode();
        //1.解析part
        File file1 = new File("file\\plmpart.xlsx");
//        File file1 = new File("classpath:/static/file/plmpart.xlsx");
        String name = file1.getName();
        FileInputStream fileInputStream = new FileInputStream(file1);
        List<Map> listpart = ExcelUtil.readExcel(fileInputStream, name);
        //2.存入数据库
        for (int i = 1; i < listpart.size(); i++) {
            Map partmap = listpart.get(i);
            PartDao partDao = JSON.parseObject(JSON.toJSONString(partmap), PartDao.class);
            partRepo.save(partDao);
        }

        return listpart;
    }

    @Override
    public PartDao findByName(String name) {
        return partRepo.findByName(name);
    }

    @Override
    public List<PartDao> findParts(String name) {
        String name1 = ".*" + name + ".*";
        return partRepo.findParts(name1);
    }

    @Override
    public void updatePart(PartDao partDao) {
        partRepo.updatePart(partDao);
    }

    @Override
    public PartDao findPart() {
        List<PartDao> part = partRepo.findPart();
        PartDao partDao = part.get(0);
        return partDao;
    }

    @Override
    public void partToBop(String partName, String bopName) {
        partRepo.relationPartToBop(partName, bopName);
    }

    @Override
    public void send(PartDao partDao) throws AmqpRejectAndDontRequeueException {
        updatePart(partDao);
        BopDao bopDao = new BopDao();
        bopDao.setPartNumber(partDao.getPartNumber());
        bopDao.setPartSupplier(partDao.getPartSupplier());
        bopDao.setProcedure(partDao.getProcedure());
        //发送消息

        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend("exchange","messagechange", bopDao);

    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功");
        } else {
            System.out.println("消息发送失败:" + cause + "\n重新发送");

        }
    }
}
