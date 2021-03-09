package com.hzh.chapter10.threadbianrytree;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/3 16:10
 */
public class HeroNode {

    private int no;

    private String name;

    /** 左结点, 默认null */
    private HeroNode left;

    /** 右结点, 默认null */
    private HeroNode right;

    /** 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点 */
    private int leftType;

    /** 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点 */
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }
}
