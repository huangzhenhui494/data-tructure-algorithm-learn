package com.hzh.clone;

import org.junit.Test;

import java.io.IOException;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/4 14:39
 */
public class CloneTest {


    /**
     * 1 引用拷贝-指向同一个对象的引用
     */
    @Test
    public void test1() {
        User user = new User(25, 1000, "小白", new Address(13179999999L, "地址1"));
        User user2 = user;
        System.out.println(user);   // com.hzh.clone.User@30f1c0
        System.out.println(user2);  // com.hzh.clone.User@30f1c0
    }

    /**
     * 2 浅拷贝-不拷贝对象里面的对象   浅拷贝可以拷贝基础数据类型（包含封装类）、String、日期类型
     */
    @Test
    public void test2() throws CloneNotSupportedException {
        User user = new User(25, 1000, "小白", new Address(13179999999L, "地址1"));
        User user2 = (User) user.clone();
        System.out.println(user);  // com.hzh.clone.User@30f1c0     User{age=25, no=1000, name='小白', address=com.hzh.clone.Address@30f1c0}
        System.out.println(user2); // com.hzh.clone.User@1ed3c8d    User{age=25, no=1000, name='小白', address=com.hzh.clone.Address@30f1c0}

        // 浅拷贝可以拷贝基础数据类型（包含封装类）、String、日期类型
        user.setAge(99);
        System.out.println(user);   // User{age=99, no=1000, name='小白', address=Address{phone=13179999999, address='地址1'}}
        System.out.println(user2);  // User{age=25, no=1000, name='小白', address=Address{phone=13179999999, address='地址1'}}

        // 不拷贝自定义类
        user.getAddress().setAddress("地址2");
        user.getAddress().setPhone(123);
        System.out.println(user);   // User{age=99, no=1000, name='小白', address=Address{phone=123, address='地址2'}}
        System.out.println(user2);  // User{age=25, no=1000, name='小白', address=Address{phone=123, address='地址2'}}
    }

    /**
     * 3 深拷贝
     */
    @Test
    public void test3() throws CloneNotSupportedException {
        User user = new User(25, 1000, "小白", new Address(13179999999L, "地址1"));
        User user2 = user.deepClone();
        System.out.println(user);  // User{age=25, no=1000, name='小白', address=Address{phone=13179999999, address='地址1'}}
        System.out.println(user2); // User{age=25, no=1000, name='小白', address=Address{phone=13179999999, address='地址1'}}
        user.getAddress().setPhone(12313123);
        user.getAddress().setAddress("地址2");
        System.out.println(user);  // User{age=25, no=1000, name='小白', address=Address{phone=12313123, address='地址2'}}
        System.out.println(user2); // User{age=25, no=1000, name='小白', address=Address{phone=13179999999, address='地址1'}}
    }

    /**
     * 深拷贝-序列化
     */
    @Test
    public void test4() throws IOException, ClassNotFoundException {
        User user = new User(25, 1000, "小白", new Address(13179999999L, "地址1"));
        User user2 = user.deepClone2();
        System.out.println(user);  // User{age=25, no=1000, name='小白', address=Address{phone=13179999999, address='地址1'}}
        System.out.println(user2); // User{age=25, no=1000, name='小白', address=Address{phone=13179999999, address='地址1'}}
        user.getAddress().setPhone(12313123);
        user.getAddress().setAddress("地址2");
        System.out.println(user);  // User{age=25, no=1000, name='小白', address=Address{phone=12313123, address='地址2'}}
        System.out.println(user2); // User{age=25, no=1000, name='小白', address=Address{phone=13179999999, address='地址1'}}
    }
}
