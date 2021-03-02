package com.hzh.chapter5.stack.stacklinkedlist;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/22 11:13
 */
public class StackLinkedListTest {

    public static void main(String[] args) {
        StackLinkedList stackLinkedList = new StackLinkedList();

        stackLinkedList.push(5);
        stackLinkedList.push(1);
        stackLinkedList.push(2);
        stackLinkedList.push(3);
        stackLinkedList.push(4);

        stackLinkedList.showList();

        System.out.println("ppp");
        System.out.println(stackLinkedList.pop());
        System.out.println("ppp");
        System.out.println(stackLinkedList.pop());
        System.out.println("ppp");
        System.out.println(stackLinkedList.pop());

        System.out.println("***");
        stackLinkedList.showList();

    }
}
