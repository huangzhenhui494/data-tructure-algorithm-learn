package com.hzh.chapter4.josephu;

import org.junit.Test;

/**
 * @description: 约瑟夫问题
 * @Author huangzhenhui
 * @Date 2021/2/20 22:07
 */
public class Josephu {

    public static CircleSingleLinkedList linkedList;

    static {
        linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(25);
    }

    /**
     * 1 需求创建一个辅助指针(变量)helper, 事先应该指向环形链表的最后这个结点
     * 2 当小孩报数时, 让first和helper指针同时的移动 m - 1 次
     * 3 这时就可以将first指向的小孩结点出圈
     * first = first.next
     * helper.next = first
     * 原来first指向的结点就没有任何引用, 就会被回收
     */
    @Test
    public void test1() {
        linkedList.countBoy(1,2,5);
    }

}
