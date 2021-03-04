package com.hzh.clone;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/4 14:30
 */
public class User implements Cloneable, Serializable {

    /**
     * 年龄-基本数据类型
     */
    private int age;

    /**
     * 编号-引用数据类型-基本类型封装类
     */
    private Integer no;

    /**
     * 名称-引用数据类型
     */
    private String name;

    /**
     * 地址-引用类型
     */
    private Address address;

    public User() {
    }

    public User(int age, Integer no, String name, Address address) {
        this.age = age;
        this.no = no;
        this.name = name;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", no=" + no +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * 深克隆
     * @return
     * @throws CloneNotSupportedException
     */
    protected User deepClone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.setAddress((Address) user.getAddress().clone());
        return user;
    }

    /**
     * 深克隆-序列化
     * @return
     * @throws IOException
     */
    protected User deepClone2() throws IOException, ClassNotFoundException {
        ByteOutputStream bos = new ByteOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (User) ois.readObject();
    }
}
