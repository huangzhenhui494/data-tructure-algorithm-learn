package com.hzh.chapter11.avl;

import javax.swing.*;

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
     *          4
     *      3       6
     *            5   7
     *                  8
     * 当前节点为根节点进行左旋转
     */
    public void leftRotate() {

        // 1 创建一个新的节点newNode(以根节点4这个值创建)
        Node newNode = new Node(value);
        // 2 把新节点的左子树设置成当前节点的左子树
        newNode.setLeft(this.left);
        // 3 把新节点的右子树设置成当前节点右子树的左子树
        newNode.setRight(this.right.left);
        // 4 把当前节点的值换成右子节点的值
        this.value = this.right.value;
        // 5 把当前节点的右子树设置成右子树的右子树
        this.right = this.right.right;
        // 6 把当前节点的左子树设置为新节点
        this.left = newNode;
    }

    /**
     *          10
     *      8       12               新的中间树为              8
     *    7  9                         10                  7   10
     *   6                          9      12             6   9   12
     *   右旋转, 左边大于右边, 左边右旋转, 和左旋转对称
     */
    public void rightRotate() {

        // 1 创建一个新的节点newNode(以根节点4这个值创建)
        Node newNode = new Node(value);
        // 2 把新节点的右子树设置成当前节点的右子树
        newNode.setRight(right);
        // 3 把新节点的左子树设置成当前节点左子树的右子树
        newNode.setLeft(left.right);
        // 4 把当前节点的值换成左子节点的值
        value = left.value;
        // 5 把当前节点的左子树设置成左子树的左子树
        left = left.left;
        // 6 把当前节点的右子树设置为新节点
        right = newNode;
    }

    /**
     * 返回左子树的高度
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 返回以该节点为根节点的树的高度, 以该节点为根节点的树的高度
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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

        // 当添加完一个节点后, 如果右子树的高度-左子树的高度 > 1, 左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树的高度它的右子树的右子树的高度
            if (right.leftHeight() > right.rightHeight()) {
                // 先对它的右子树进行右旋转
                right.rightRotate();
            }
            // 左旋转
            leftRotate();

            return; // 走进来这个, 执行了就必须return, 不然下面还需要递归计算高度, 没意思;
        }

        // 当添加完一个节点后, 如果左子树的高度-右子树的高度 > 1, 右旋转
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树高度大于它的左子树的左子树的高度
            if (left.rightHeight() > left.leftHeight()) {
                // 先将它的左子树即this.left进行左旋转
                left.leftRotate();
            }
            // 最后再对当前节点右旋转
            rightRotate();
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
