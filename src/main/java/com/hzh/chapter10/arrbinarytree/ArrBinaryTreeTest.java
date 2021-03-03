package com.hzh.chapter10.arrbinarytree;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/3 10:04
 */
public class ArrBinaryTreeTest {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.postOrder();
    }
}
