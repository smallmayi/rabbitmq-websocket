package com.jwis.demo.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jwis.demo.dao.BopDao;
import com.jwis.demo.dao.PartDao;
import com.jwis.demo.service.inter.BopInter;
import com.jwis.demo.service.inter.PartInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api
@Slf4j
@RequestMapping("/test")
//@RabbitListener(queues = "messagechange")
public class TestCtrl {

    static boolean flag = false;
    @Autowired
    PartInter partInter;
    @Autowired
    BopInter bopInter;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    static List<Map> part;
    static List<Map> bop;

    @GetMapping("/addData")
    @ApiOperation("解析两张excel,添加part,bop数据到数据库")
    public JSONArray addData() throws Exception {
        part = partInter.createPart();
        System.out.println("part:"+part.size());
        bop = bopInter.createBop();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject01 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray01 = new JSONArray();
        JSONArray jsonArray02 = new JSONArray();
        JSONObject dataObject;
        //放入part数据
        for (int i = 1; i < part.size(); i++) {
            dataObject = new JSONObject();
            String value = part.get(i).get("name").toString();
            dataObject.put("label", "name");
            dataObject.put("value", value);
            jsonArray01.add(i-1, dataObject);
        }
        jsonObject.put("label", "PLM");
        jsonObject.put("options", jsonArray01);
        //放入bop数据
        for (int i = 1; i < bop.size(); i++) {
            dataObject = new JSONObject();
            String value = bop.get(i).get("name").toString();
            dataObject.put("label", "name");
            dataObject.put("value", value);
            jsonArray02.add(i-1, dataObject);
        }
        jsonObject01.put("options", jsonArray02);
        jsonObject01.put("label", "MPM");
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject01);
        return jsonArray;
    }

    @GetMapping("/findPart")
    @ApiOperation("通过name查询part")
    public List<PartDao> findPart(String[] name) throws Exception {
        List<PartDao> partDaos = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {

            PartDao byName = partInter.findByName(name[i]);
            partDaos.add(byName);
        }
        PartDao partDao = JSON.parseObject(JSON.toJSONString(part.get(0)), PartDao.class);
        partDaos.add(0,partDao);
        return partDaos;
    }
//    public PartDao findPart(String name) throws Exception {
//            PartDao byName = partInter.findByName(name);
//        return byName;
//    }

//    @GetMapping("/findParts")
//    @ApiOperation("模糊查询part")
//    public List<PartDao> findParts(String name) throws Exception {
//
//        return partInter.findParts(name);
//    }

    @GetMapping("/findBop")
    @ApiOperation("通过name查询part")
    public List<BopDao> findBop(String[] name) throws Exception {
        List<BopDao> bopDaos = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            BopDao byName = bopInter.findByName(name[i]);
            bopDaos.add(byName);
        }
        BopDao bopDao = JSON.parseObject(JSON.toJSONString(bop.get(0)), BopDao.class);
        bopDaos.add(0, bopDao);
        return bopDaos;
    }

//    @PostMapping("/send")
//    public void send1( String part) throws Exception{
//        System.out.println("send:"+part);
//        rabbitTemplate.convertAndSend("messagechange", part);
//    }

    @PostMapping("/updatePart")
    public void send(@RequestBody String part) throws Exception {
        System.out.println();
        JSONObject jsonObject = JSONObject.parseObject(part);
        PartDao partDao = JSONObject.toJavaObject(jsonObject, PartDao.class);
        log.info("接收part:" + part);
        partInter.send(partDao);
    }

    @ApiOperation("创建Bop和关系PartToBop")
    @PostMapping("/partToBop")
    public void partToBop(String partName, String[] bopNames) throws Exception {
        for (int i = 0; i < bopNames.length; i++) {
            partInter.partToBop(partName, bopNames[i]);

        }
//        List<BopDao> bopList = findBop(bopNames);
//        return  bopList;
    }

    @ApiOperation("假装订阅")
    @PostMapping("/sub")
    public static boolean subBop() throws Exception {
        flag = true;
        return flag;
    }

    public Map objToMap(Object obj) throws IllegalAccessException {
        Class clazz = obj.getClass();
        Map map = new HashMap();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

}
