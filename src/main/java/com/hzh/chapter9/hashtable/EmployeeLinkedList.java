package com.hzh.chapter9.hashtable;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/1 13:55
 */
public class EmployeeLinkedList {

    /**
     * 第一个就是员工, 没有头结点
     */
    private Employee head;

    /**
     * 添加雇员
     * @param e
     */
    public void add(Employee e) {
        if(head == null) {
            head = e;
        } else {
            // 辅助指针找到最后一个
            Employee temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(e);
        }
    }

    /**
     * 遍历当前链表, no只是传入当前数组的key位置
     * @param no
     */
    public void showList(int no) {
        if(head == null) {
            System.out.println(String.format("第%s条链表为空", no + 1));
            return;
        }
        System.out.println(String.format("第%s条链表的信息为: ", no + 1));
        Employee temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    /**
     * 根据id查找雇员
     * @return
     */
    public Employee findById(int id) {
        Employee temp = head;
        while (temp != null) {
            if (temp.getId() == id) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    public static void main(String[] args) {
        EmployeeLinkedList employeeLinkedList = new EmployeeLinkedList();
        employeeLinkedList.add(new Employee(1, "嗯嗯1"));
        employeeLinkedList.add(new Employee(2, "嗯嗯2"));
        employeeLinkedList.add(new Employee(3, "嗯嗯3"));
        employeeLinkedList.add(new Employee(4, "嗯嗯4"));
        employeeLinkedList.add(new Employee(5, "嗯嗯5"));
        employeeLinkedList.add(new Employee(6, "嗯嗯6"));
        employeeLinkedList.add(new Employee(7, "嗯嗯7"));
        employeeLinkedList.add(new Employee(8, "嗯嗯8"));
        employeeLinkedList.add(new Employee(9, "嗯嗯9"));
        employeeLinkedList.add(new Employee(10, "嗯嗯10"));
        employeeLinkedList.add(new Employee(11, "嗯嗯11"));
        employeeLinkedList.add(new Employee(12, "嗯嗯12"));
        employeeLinkedList.add(new Employee(13, "嗯嗯13"));

        employeeLinkedList.showList(1);

        Employee byId = employeeLinkedList.findById(14);
        System.out.println(byId);
    }
}
