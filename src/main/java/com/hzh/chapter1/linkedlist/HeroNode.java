package com.hzh.chapter1.linkedlist;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/19 21:14
 */
public class HeroNode {

    public int no;

    public String name;

    public String nickname;

    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    /**
     * 有next域, 会递归调用
     * @return
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
//                ", next=" + next +    有next域, 会递归调用
                '}';
    }
}
