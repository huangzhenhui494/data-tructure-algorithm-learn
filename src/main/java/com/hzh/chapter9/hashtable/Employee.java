package com.hzh.chapter9.hashtable;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/1 13:52
 */
public class Employee {

    /** id */
    private int id;

    /** 名称 */
    private String name;

    /** next */
    private Employee next;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getNext() {
        return next;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
