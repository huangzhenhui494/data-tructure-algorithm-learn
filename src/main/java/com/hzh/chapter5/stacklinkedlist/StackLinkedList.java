package com.hzh.chapter5.stacklinkedlist;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/22 10:56
 */
public class StackLinkedList {

    private StackNode head;

    public StackLinkedList() {
        this.head = new StackNode(0, null);
    }

    /**
     * 入栈
     */
    public void push(int element) {
        StackNode stackNode = new StackNode(element, head.getNext());
        head.setNext(stackNode);
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("empty");
        }
        StackNode temp = head.getNext();
        head = head.getNext();
        return temp.getElement();
    }

    /**
     * 展示
     */
    public void showList() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("empty");
        }
        // 需要从栈顶开始展示数据
        StackNode temp = head.getNext();
        while (temp != null) {
            System.out.println(temp.getElement());
            temp = temp.getNext();
        }
    }

    public boolean isEmpty() {
        return head.getNext() == null;
    }
}
