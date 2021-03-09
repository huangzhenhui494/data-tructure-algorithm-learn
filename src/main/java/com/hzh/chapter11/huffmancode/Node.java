package com.hzh.chapter11.huffmancode;

/**
 * @description: 带数据和权值
 * @Author huangzhenhui
 * @Date 2021/3/9 20:33
 */
public class Node implements Comparable<Node> {

    private Byte data;  // 存放数据(字符)本身, 比如'a' => 97  ' ' => 32

    private int weight;  // 结点权值, 表示字符出现的次数

    private Node left;  // 指向左子结点

    private Node right; // 指向右子结点

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
    public int compareTo(Node o) {
        // 从小到大
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
