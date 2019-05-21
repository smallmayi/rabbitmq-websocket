package com.jwis.demo.service.inter;

import com.jwis.demo.dao.PartDao;

import java.util.List;
import java.util.Map;

public interface PartInter {
    List<Map> createPart() throws Exception;

    PartDao findByName(String name);

    List<PartDao> findParts(String name);

    void updatePart(PartDao partDao);

    PartDao findPart();

    void partToBop(String partName,String bopName);

    void send(PartDao partDa);
}
