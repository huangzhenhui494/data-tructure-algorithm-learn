package com.hzh.chapter10.binarytree;

/**
 * @description: 二叉树
 * @Author huangzhenhui
 * @Date 2021/3/2 14:13
 */
public class BinaryTree {

    /**
     * 根节点
     */
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历(根节点在前)
     * 前序遍历首先访问根结点然后遍历左子树，最后遍历右子树
     * 在遍历左、右子树时，仍然先访问 根结点，然后遍历左子树，最后遍历右子树
     */
    public void preOrder() {
        isEmpty();
        System.out.println("前序遍历");
        this.root.preOrder();
    }

    /**
     * 中序遍历(根节点在中)
     * 中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树
     */
    public void infixOrder() {
        isEmpty();
        System.out.println("中序遍历");
        this.root.infixOrder();
    }

    /**
     * 后序遍历(根节点在后)
     * 后序遍历首先遍历左子树，然后遍历右子树，最后访问根结点，在遍历左、右子树时，仍然先遍历左子树，然后遍历右子树，最后遍历根结点。
     */
    public void postOrder() {
        isEmpty();
        System.out.println("后序遍历");
        this.root.postOrder();
    }

    /**
     * 前序查找
     * @param no
     */
    public HeroNode preOrderSearch(int no) {
        isEmpty();
        System.out.println("前序查找");
        return this.root.preOrderSearch(no);
    }

    /**
     * 中序查找
     * @param no
     */
    public HeroNode infixOrderSearch(int no) {
        isEmpty();
        System.out.println("中序查找");
        return this.root.infixOrderSearch(no);
    }

    /**
     * 后序查找
     * @param no
     */
    public HeroNode postOrderSearch(int no) {
        isEmpty();
        System.out.println("后序查找");
        return this.root.postOrderSearch(no);
    }

    /**
     * 删除节点,非叶子节点全删除
     * @param no
     */
    public void deleteNode(int no) {
        isEmpty();
        System.out.println("删除节点");
        // 如果只有一个 root 结点, 这里立即判断 root 是不是就是要删除结点
        if (this.root.getNo() == no) {
            this.root = null;
            return;
        }
        // 递归删除
        this.root.deleteNode(no);
    }

    private void isEmpty() {
        if(root == null) throw new RuntimeException("二叉树为空");
    }
}
