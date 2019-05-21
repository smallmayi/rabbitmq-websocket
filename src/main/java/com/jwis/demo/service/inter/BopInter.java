package com.jwis.demo.service.inter;

import com.jwis.demo.dao.BopDao;

import java.util.List;
import java.util.Map;

public interface BopInter {
    List<Map> createBop() throws Exception;

    void updateBop(BopDao bopDao);

    BopDao findByName(String name);

}
