package com.hzh.chapter10.arrbinarytree;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/3 10:04
 */
public class ArrBinaryTree {

    /**
     * 顺序存储二叉树的特点:
     * 1) 顺序二叉树通常只考虑完全二叉树
     * 2) 第 n 个元素的左子结点为 2 * n + 1
     * 3) 第 n 个元素的右子结点为 2 * n + 2
     * 4) 第 n 个元素的父结点为 (n-1) / 2
     * 5) n : 表示二叉树中的第几个元素(按 0 开始编号如图所示)
     */
    private int[] arr; // 存储数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     * 要求以二叉树前序遍历的方式进行遍历。 前序遍历的结果应当为
     * 1,2,4,5,3,6,7
     */
    public void preOrder() {
        if(arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空");
        }
        this.preOrder(0);
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if(arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空");
        }
        this.infixOrder(0);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if(arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空");
        }
        this.postOrder(0);
    }

    /**
     * 前序遍历重载
     * @param index
     */
    private void preOrder(int index) {
        // 输出当前结点元素
        System.out.println(arr[index]);
        // 1, 2, 3, 4, 5, 6, 7}
        /**
         *            1(0)8
         *        2(1)    3 (2)
         *     4(3) 5(4) 6(5) 7(6)
         *
         *  前序遍历结果 1245367
         */
        // 向左递归遍历
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 中序遍历重载
     * 结果 4251637
     * @param index
     */
    private void infixOrder(int index) {
        // 向左查找
        if((2 * index + 1) < arr.length) {
            infixOrder((2 * index + 1));
        }
        // 打印结点
        System.out.println(arr[index]);
        // 向右查找
        if ((2 * index + 2) < arr.length) {
            infixOrder((2 * index + 2));
        }
    }


    /**
     * 后序遍历重载
     * 4526731
     * @param index
     */
    private void postOrder(int index) {
        // 向左查找
        if((2 * index + 1) < arr.length) {
            postOrder((2 * index + 1));
        }
        // 向右查找
        if ((2 * index + 2) < arr.length) {
            postOrder((2 * index + 2));
        }
        // 打印结点
        System.out.println(arr[index]);
    }
}
