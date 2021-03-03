package com.hzh.chapter10.threadbianrytree;

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
     * @param node
     */
    private void preThreadOrder(HeroNode node) {

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
        }
        // 处理上一个节点的后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
        }
        // 当前节点设置为下一个节点的前驱节点
        pre = node;

        // 线索化右子树
        infixThreadOrder(node.getRight());
    }

    /**
     * 后序线索化
     * @param node
     */
    private void postThreadOrder(HeroNode node) {

    }
}
