package com.jwis.demo.util;

import com.alibaba.fastjson.JSON;
import com.jwis.demo.dao.BopDao;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * websocket 传递对象需要编码
 */

public class ServerEncoder implements Encoder.Text<BopDao> {

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public String encode(BopDao bopDao) throws EncodeException {
        String s = JSON.toJSONString(bopDao);
        return s;

    }
}
