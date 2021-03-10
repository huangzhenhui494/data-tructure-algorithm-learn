package com.hzh.chapter11.binarysorttree;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/10 22:04
 */
public class Node {

    private int value;

    private Node left;

    private Node right;

    public Node(int value) {
        this.value = value;
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

    /**
     * 添加节点, 满足二叉排序树
     * 递归形式
     * @param node
     */
    public void add(Node node) {
        // 空return
        if(node == null) {
            return;
        }
        // 和当前节点比较值
        if (node.getValue() < this.value) {
            if (this.left == null) {
                // 当前节点左节点为空则直接放入左节点
                this.left = node;
            } else { // 向左递归
                this.left.add(node);
            }
        } else { // 大于等于当前节点
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 查找要删除的节点
     * @param value 删除节点的值
     * @return 如果找到返回该节点, 否则返回null
     */
    public Node search(int value) {

        if(this.value == value) { // 找到返回节点
            return this;
        }
        if (this.value > value) { // 如果要查找的值比当前节点小, 向左递归
            if(this.left == null) return null;
            // 向左递归
            return this.left.search(value);
        } else {
            if(this.right == null) return null;
            // 向右递归
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要找到的节点的值
     * @return      返回的是要删除的节点的父节点, 如果没有就返回null
     */
    public Node searchParent(int value) {
        // 如果当前节点就是要删除的节点的父节点, 则返回
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前节点的值, 并且当前节点的左子节点不为空
            if (this.value > value && this.left != null) {
                return this.left.searchParent(value);  // 向左子树递归找
            } else if (this.value < value && this.right != null) { // 这边不用<=, 上边找到就返回了
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null;
            }
        }

    }
}
