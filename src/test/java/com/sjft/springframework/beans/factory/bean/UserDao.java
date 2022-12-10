package com.sjft.springframework.beans.factory.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sjft
 * @date 2022-12-07 15:46
 */
public class UserDao {
    private Map<Integer, String> userMap = new HashMap<Integer, String>(){{
        put(1, "aaa");
        put(2, "bbb");
        put(3, "ccc");
    }};

    public String queryUserName(int uId) {
        return userMap.get(uId);
    }
}
