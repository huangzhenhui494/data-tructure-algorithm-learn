package com.hzh.chapter1.linkedlist;

import org.junit.Test;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/19 21:13
 */
// 定义SingleLinkedList 管理英雄
public class SingleLinkedList {

    // 先初始化一个头节点, 头节点不要动, 不存放具体
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单向链表

    /**
     * ① 当不考虑编号顺序时
     *  1 找到当前链表的最后节点
     *  2 将最后这个节点的next指向新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode) {

        // 因为head节点不能动, 因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // temp指向新节点
        temp.next = heroNode;
    }

    /**
     * 第二种方式在添加英雄时, 根据排名将英雄插入到指定位置
     * 如果有这个排名则添加失败, 并给出提示
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no > heroNode.no) { // 升序, 位置找到了, 新的英雄应该放temp.next和temp中间
                if (temp.no == heroNode.no) { // 已经有了
                    throw new RuntimeException("排名已存在");
                }
                heroNode.next = temp.next;
                temp.next = heroNode;
                return;
            }
            temp = temp.next;
        }
        // 循环结束, 当前只有头节点或者新排名是最靠后的
        temp.next = heroNode;
    }

    // 修改节点信息, 根据no编号来修改, 即no编号不能改
    public void update(HeroNode updateNode) {
        // 判断是否空链表
        if(head.next == null) throw new RuntimeException("链表为空");
        // 找到需要修改的节点
        HeroNode temp = head.next;
        while (temp != null) {
            if (temp.no == updateNode.no) {
                temp.name = updateNode.name;
                temp.nickname = updateNode.nickname;
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("找不到编号");
    }

    /**
     * 显示链表[遍历]
     */
    public void showList() {
        System.out.println("---------------------------");
        //判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("list is empty");
        }
        // 因为头节点, 不能动, 因此需要一个辅助变量来遍历
        HeroNode temp = head;
        while (temp.next != null) {
             // 输出
            temp = temp.next;
            System.out.println(temp);
        }
    }

    /**
     * 删除节点
     * head不能动
     * 我们比较时, 是temp.next.no和需要删除的节点的no比较
     * @param no
     */
    public void del(int no) {
        //判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("list is empty");
        }
        // 查找前一个节点
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("找不到编号");
    }


    @Test
    public void test1() {
        // 先创建几个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        // 显示
        singleLinkedList.showList();
    }

    @Test
    public void test2() {
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
        SingleLinkedList singleLinkedList = new SingleLinkedList();
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
        // 显示
        singleLinkedList.showList();

        HeroNode newHeroNode = new HeroNode(2, "ad", "asdf");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.showList();

        singleLinkedList.del(2);
        singleLinkedList.del(1);
        singleLinkedList.showList();
        singleLinkedList.del(11);
        singleLinkedList.showList();
    }
}
