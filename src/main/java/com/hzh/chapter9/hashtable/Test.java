package com.hzh.chapter9.hashtable;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/1 15:57
 */
public class Test {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(5);
        hashTable.add(new Employee(1, "嗯嗯1"));
        hashTable.add(new Employee(2, "嗯嗯2"));
        hashTable.add(new Employee(3, "嗯嗯3"));
        hashTable.add(new Employee(4, "嗯嗯4"));
        hashTable.add(new Employee(5, "嗯嗯5"));
        hashTable.add(new Employee(6, "嗯嗯6"));
        hashTable.add(new Employee(7, "嗯嗯7"));
        hashTable.add(new Employee(8, "嗯嗯8"));
        hashTable.add(new Employee(9, "嗯嗯9"));
        hashTable.add(new Employee(10, "嗯嗯10"));
        hashTable.add(new Employee(11, "嗯嗯11"));
        hashTable.add(new Employee(12, "嗯嗯12"));
        hashTable.add(new Employee(13, "嗯嗯13"));
        hashTable.add(new Employee(14, "嗯嗯14"));
        hashTable.add(new Employee(15, "嗯嗯15"));
        hashTable.add(new Employee(16, "嗯嗯16"));
        hashTable.add(new Employee(17, "嗯嗯17"));
        hashTable.add(new Employee(18, "嗯嗯18"));
        hashTable.add(new Employee(19, "嗯嗯19"));
        hashTable.add(new Employee(20, "嗯嗯20"));
        hashTable.add(new Employee(21, "嗯嗯21"));

        hashTable.showList();
        Employee employee = hashTable.findById(10);
        System.out.println(employee);
        hashTable.showList();
    }
}
