package com.hzh.chapter11.binarysorttree;

import org.junit.Test;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/11 6:14
 */
public class BinarySortTreeTest {

    /**
     *
     *               7
     *        3            10
     *     1      5     9         12
     *          2
     */
    @Test
    public void test1() {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环添加
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        // 中序遍历
        binarySortTree.infixOrder();

        System.out.println("-------------------");

//        // 测试删除叶子节点, 一个个来 2,5,9,12
//        binarySortTree.delNode(2);
//        System.out.println("-------------------");
//        binarySortTree.infixOrder();
//        binarySortTree.delNode(1);
//        System.out.println("-------------------");
//        binarySortTree.infixOrder();
//        binarySortTree.delNode(5);
//        System.out.println("-------------------");
//        binarySortTree.infixOrder();
//        binarySortTree.delNode(3);
//        System.out.println("-------------------");
//        binarySortTree.infixOrder();
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        binarySortTree.infixOrder();
    }


}
