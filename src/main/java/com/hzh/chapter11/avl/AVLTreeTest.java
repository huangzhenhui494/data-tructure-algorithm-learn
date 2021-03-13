package com.hzh.chapter11.avl;

import org.junit.Test;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/13 7:24
 */
public class AVLTreeTest {

    /**
     * 左旋转测试
     */
    @Test
    public void leftRotate() {
        // 左旋转
        int[] arr = {4, 3, 6, 5, 7, 8};
        // 创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());         // 4
        System.out.println(avlTree.getRoot().leftHeight());     // 1
        System.out.println(avlTree.getRoot().rightHeight());    // 3  右子树的高度大于左子树, 进行左旋转
        System.out.println(avlTree.getRoot());
    }

    /**
     * 右旋转测试, 左-右>1
     */
    @Test
    public void rightRotate() {
        // 右旋转
        int[] arr = {10, 12, 8, 9, 7, 6};
        // 创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());         // 4
        System.out.println(avlTree.getRoot().leftHeight());     // 1
        System.out.println(avlTree.getRoot().rightHeight());    // 3  右子树的高度大于左子树, 进行左旋转
        System.out.println(avlTree.getRoot());
    }

    /**
     * 双旋转
     *              10
     *         7         11
     *      6     8
     *              9
     * 1 当符合右旋转的条件时
     * 2 如果它的左子树的右子树大于它的左子树的高度即 8树高大于6树高,  此时先将7向左旋转
     * 3 再对当前节点10进行右旋转的操作
     *
     * 先将7树左旋转为
     *
     *       8                      10                                8
     *    7    9   =>            8     11   => 右旋转为             7     10
     *  6                      7   9                             6     9  11
     *                       6
     */
    @Test
    public void test() {
        int[] arr = {10, 11, 7, 6, 8, 9};
        // 创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());         // 4
        System.out.println(avlTree.getRoot().leftHeight());     // 1
        System.out.println(avlTree.getRoot().rightHeight());    // 3  右子树的高度大于左子树, 进行左旋转
        System.out.println(avlTree.getRoot());
    }
}
