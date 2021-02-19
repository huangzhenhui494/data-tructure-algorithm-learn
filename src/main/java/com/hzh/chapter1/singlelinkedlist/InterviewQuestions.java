package com.hzh.chapter1.singlelinkedlist;

import org.junit.Test;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/19 23:27
 */
public class InterviewQuestions {

    /**
     * 1 求单链表中有效节点的个数
     * 2 查找单链表中的倒数第k个节点
     * 3 单链表的反转
     * 4 从尾到头打印单链表  要求方式1:反向遍历, 方式2: stack栈
     * 5 合并两个有序的单链表, 合并之后的链表依然有序
     */

    private static SingleLinkedList singleLinkedList;

    static {
        // 先创建几个节点
        HeroNode hero1 = new HeroNode(5, "柴进", "小旋风");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(8, "索超", "急先锋");
        HeroNode hero4 = new HeroNode(9, "武松", "行者");
        HeroNode hero5 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero7 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero8 = new HeroNode(6, "花荣", "小李广");
        HeroNode hero9 = new HeroNode(7, "鲁智深", "花和尚");

        // 创建链表
        singleLinkedList = new SingleLinkedList();
        // 加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero6);
        singleLinkedList.addByOrder(hero7);
        singleLinkedList.addByOrder(hero8);
        singleLinkedList.addByOrder(hero9);
    }


    @Test
    public void test1() {
        System.out.println(singleLinkedList.getLength());
    }

    @Test
    public void test2() {
        int k = 9;
        singleLinkedList.showList();
        System.out.println(singleLinkedList.getKthFromTheBottom(k));
        System.out.println(singleLinkedList.getKthFromTheBottom2(k));
    }

    @Test
    public void test3() {

    }

    @Test
    public void test4() {

    }

    @Test
    public void test5() {

    }


}
