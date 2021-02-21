package com.hzh.chapter3.linkedlist.doublelinked;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/20 20:55
 */
public class DoubleHeroNode {

    public int no;

    public String name;

    public String nickname;

    public DoubleHeroNode pre;  // 指向下一个节点, 默认为null

    public DoubleHeroNode next; // 指向前一个节点, 默认为null

    public DoubleHeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
