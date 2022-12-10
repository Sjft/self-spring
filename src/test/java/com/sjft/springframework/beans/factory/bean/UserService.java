package com.sjft.springframework.beans.factory.bean;

/**
 * @author sjft
 * @date 2022-12-03 16:34
 */
public class UserService {

    private String uId;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息:" + userDao.queryUserName(Integer.parseInt(uId)));
    }

    public static void main(String[] args) {
        System.out.println(Integer.getInteger("1"));
    }
}
