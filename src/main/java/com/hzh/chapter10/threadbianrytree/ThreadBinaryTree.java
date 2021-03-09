package com.hzh.chapter10.threadbianrytree;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.rmi.NotBoundException;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/3 11:29
 */
public class ThreadBinaryTree {

    private HeroNode root;

    // 为了实现线索化, 需要创建给指向当前节点的前驱节点的指针
    // 在递归进行线索化时，pre 总是保留前一个结点
    private HeroNode pre;

    /**
     * 设置根节点
     * @param root
     */
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 中序遍历
     */
    public void infixThreadList() {

        HeroNode node = root;
        while (node != null) {
            // 先找到最左节点即当leftType = 1时的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 输出节点
            System.out.println(node);
            // 输出后继节点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 后继节点输出完毕找到下一个节点
            node = node.getRight();
        }
    }

    /**
     * 前序线索化
     */
    public void preThreadOrder() {
        this.preThreadOrder(root);
    }

    /**
     * 中序线索化
     */
    public void infixThreadOrder() {
        this.infixThreadOrder(root);
    }

    /**
     * 后序线索化
     */
    public void postThreadOrder() {
        this.postThreadOrder(root);
    }

    /**
     * 遍历中序线索二叉树
     */
    public void threadNodesList() {

    }

    /**
     * 前序线索化
     * 1 3 8 10 6 14
     * @param node
     */
    private void preThreadOrder(HeroNode node) {

        // node为null吧不能线索化
        if (node == null) {
            return;
        }
        // 后继节点和前一个节点的前驱
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // pre = 6时,  原来的节点6.left=14, 如果6.right也=14, 线索化右子树时会进入死循环
        if (pre != null && pre.getRight() == null && pre.getLeft() != node) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        // node.getLeftType()==0是为了避免出现死循环
        if (node.getLeftType() == 0) {
            preThreadOrder(node.getLeft());
        }
        // 线索化右子树
        preThreadOrder(node.getRight());
    }

    /**
     * 中序线索化
     * 重载, 画个图自己摸索, 注意非空的才能指向, 画的时候先画原来的二叉树指向
     *          1
     *      3      6
     *    8  10  14
     *
     *  中序: 8 3 10 1 14 6
     * @param node
     */
    private void infixThreadOrder(HeroNode node) {

        // 如果 node == null, 不能线索化
        if (node == null) {
            return;
        }
        // 先线索化左子树
        infixThreadOrder(node.getLeft());

        // 处理当前节点的前驱节点, leftType = 1表示节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            // 1代表指向前驱节点
            node.setLeftType(1);
        }
        // 处理上一个节点的后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        // 当前节点设置为下一个节点的前驱节点
        pre = node;

        // 线索化右子树
        infixThreadOrder(node.getRight());
    }

    /**
     * 后序线索化
     *          1
     *      3      6
     *    8  10  14
     *
     *  后序: 8 10 3 14 6 1
     * @param node
     */
    private void postThreadOrder(HeroNode node) {

        // 如果node == null, 不能线索化
        if (node == null) {
            return;
        }
        // 线索化左子树
        postThreadOrder(node.getLeft());
        // 线索化右子树
        postThreadOrder(node.getRight());

        // 处理前驱节点和上一个节点的后继节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }
}
