package com.hzh.chapter4;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/20 22:07
 */
public class Boy {

    private int no;

    private Boy next; // 指向下一个节点, 默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
