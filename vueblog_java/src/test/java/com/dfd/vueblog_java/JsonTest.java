package com.dfd.vueblog_java;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Map;

public class JsonTest {
    @Test
    public void jsonToMap() {
        String user = "{username:'Deng',password:'admin'}";
        Map map = (Map) JSON.parse(user);
        System.out.println(map);
        System.out.println(map.get("username"));
    }
}
