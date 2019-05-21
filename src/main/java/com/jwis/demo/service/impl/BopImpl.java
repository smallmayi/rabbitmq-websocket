package com.jwis.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jwis.demo.dao.BopDao;
import com.jwis.demo.repo.BopRepo;
import com.jwis.demo.service.inter.BopInter;
import com.jwis.demo.util.ExcelUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@Service
@RabbitListener(queues = "messagechange")
public class BopImpl implements BopInter {
    @Autowired
    BopRepo bopRepo;

    @Override
    public List<Map> createBop() throws Exception {
        //1.解析bop
        File file2 = new File("file\\mpmbop.xlsx");
        String name2 = file2.getName();
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        List<Map> listbop = ExcelUtil.readExcel(fileInputStream2, name2);
        //2.放入数据库
        for (int i = 1; i < listbop.size(); i++) {
            Map bopmap = listbop.get(i);
            BopDao bopDao = JSON.parseObject(JSON.toJSONString(bopmap), BopDao.class);
            bopRepo.save(bopDao);
        }
        return listbop;
    }

    @Override
    public void updateBop(BopDao bopDao) {
        bopRepo.updateBop(bopDao);
    }

    @Override
    public BopDao findByName(String name) {
        BopDao bopDao = bopRepo.findByName(name);
        return bopDao;
    }

    public BopDao JsonToPart(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject mpm = jsonObject.getJSONObject("mpm");
        BopDao bopDao = JSONObject.toJavaObject(mpm, BopDao.class);
        return bopDao;
    }

}
