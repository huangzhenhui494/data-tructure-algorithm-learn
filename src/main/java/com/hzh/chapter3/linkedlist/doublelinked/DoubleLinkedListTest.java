package com.hzh.chapter3.linkedlist.doublelinked;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/20 21:18
 */
public class DoubleLinkedListTest {

    private static DoubleLinkedList doubleLinkedList;

    static {
        doubleLinkedList = new DoubleLinkedList();
        // 先创建几个节点
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "柴进", "小旋风");
        DoubleHeroNode hero2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "索超", "急先锋");
        DoubleHeroNode hero4 = new DoubleHeroNode(4, "武松", "行者");
        DoubleHeroNode hero5 = new DoubleHeroNode(5, "林冲", "豹子头");
        DoubleHeroNode hero6 = new DoubleHeroNode(6, "宋江", "及时雨");
        DoubleHeroNode hero7 = new DoubleHeroNode(7, "吴用", "智多星");
        DoubleHeroNode hero8 = new DoubleHeroNode(8, "花荣", "小李广");
        DoubleHeroNode hero9 = new DoubleHeroNode(9, "鲁智深", "花和尚");

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero5);
        doubleLinkedList.add(hero6);
        doubleLinkedList.add(hero7);
        doubleLinkedList.add(hero8);
        doubleLinkedList.add(hero9);
    }

    public static void main(String[] args) {

//        doubleLinkedList.showList();
//        doubleLinkedList.update(new DoubleHeroNode(4, "132", "123"));
//        doubleLinkedList.showList();
//        doubleLinkedList.del(7);
//        doubleLinkedList.showList();

        doubleLinkedList = new DoubleLinkedList();
// 先创建几个节点
        DoubleHeroNode hero1 = new DoubleHeroNode(5, "柴进", "小旋风");
        DoubleHeroNode hero2 = new DoubleHeroNode(3, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(7, "索超", "急先锋");
        DoubleHeroNode hero4 = new DoubleHeroNode(1, "武松", "行者");
        DoubleHeroNode hero5 = new DoubleHeroNode(6, "林冲", "豹子头");
        DoubleHeroNode hero6 = new DoubleHeroNode(8, "宋江", "及时雨");
        DoubleHeroNode hero7 = new DoubleHeroNode(9, "吴用", "智多星");
        DoubleHeroNode hero8 = new DoubleHeroNode(4, "花荣", "小李广");
        DoubleHeroNode hero9 = new DoubleHeroNode(2, "鲁智深", "花和尚");

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero5);
        doubleLinkedList.addByOrder(hero6);
        doubleLinkedList.addByOrder(hero7);
        doubleLinkedList.addByOrder(hero8);
        doubleLinkedList.addByOrder(hero9);
        doubleLinkedList.showList();
    }
}
