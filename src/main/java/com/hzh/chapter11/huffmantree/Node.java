package com.hzh.chapter11.huffmantree;

 /**
 * @description: 节点
 * @Author huangzhenhui
 * @Date 2021/3/9 6:45
 */
public class Node implements Comparable<Node> {

    private int value; // 节点权值

    private Node left; // 指向左子节点

    private Node right; // 指向右子节点

    public Node(int value) {
        this.value = value;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.value = left.getValue() + right.getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排(我减他升, 他减我降)
        return this.value - o.value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }
}
