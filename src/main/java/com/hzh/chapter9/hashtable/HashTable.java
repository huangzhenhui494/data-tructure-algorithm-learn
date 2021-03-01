package com.hzh.chapter9.hashtable;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/1 13:42
 */
public class HashTable {

    private int size;

    private EmployeeLinkedList[] employeeLinkedListArr;

    public HashTable(int size) {
        employeeLinkedListArr = new EmployeeLinkedList[size];
        this.size = size;
        // 留一个坑, 这时不要分别初始化每个链表?
        for(int i = 0; i < size; i++) {
            employeeLinkedListArr[i] = new EmployeeLinkedList();
        }
    }

    /**
     * 添加雇员
     * @param e
     */
    public void add(Employee e) {
        // 获取哪一个链表索引
        int index = hashFun(e.getId());
        employeeLinkedListArr[index].add(e);
    }

    /**
     * 遍历
     */
    public void showList() {
        for (int i = 0; i < employeeLinkedListArr.length; i++) {
            employeeLinkedListArr[i].showList(i);
        }
    }

    /**
     * 查找雇员
     * @return
     */
    public Employee findById(int id) {
        Employee employee = employeeLinkedListArr[hashFun(id)].findById(id);
        if(employee != null) return employee;
        throw new RuntimeException("找不到雇员");
    }

    /**
     * 编写散列函数, 使用一个简单取模法
     * @param id
     * @return
     */
    private int hashFun(int id) {
        return id % size;
    }
}
