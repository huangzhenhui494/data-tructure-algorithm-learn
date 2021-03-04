package com.hzh.clone;

import java.io.Serializable;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/4 14:30
 */
public class Address implements Cloneable, Serializable {

    /**
     * 电话-基本数据类型
     */
    private long phone;

    /**
     * 地址
     */
    private String address;

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address() {
    }

    public Address(long phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
