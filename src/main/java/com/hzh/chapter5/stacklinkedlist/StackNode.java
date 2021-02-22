package com.hzh.chapter5.stacklinkedlist;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/22 10:04
 */
public class StackNode {

    private int element;

    private StackNode next;

    public StackNode(int element, StackNode next) {
        this.element = element;
        this.next = next;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}
