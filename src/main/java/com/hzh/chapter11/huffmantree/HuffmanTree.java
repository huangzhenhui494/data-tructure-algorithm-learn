package com.hzh.chapter11.huffmantree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @description: 赫夫曼树(WPL最小的)
 * @Author huangzhenhui
 * @Date 2021/3/9 6:45
 */
public class HuffmanTree {

    @Test
    public void test() {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node node = HuffmanTree.createHuffmanTree(arr);
        HuffmanTree.preOrder(node);

    }

    /**
     * 创建赫夫曼树的方法
     * @param arr 需要创建成赫夫曼树的数组
     * @return    返回的Node
     */
    public static Node createHuffmanTree(int[] arr) {
        // ① 将arr转Node并放入list排序
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            // ① 从小到大排序
            nodes.sort(Comparator.comparingInt(Node::getValue)); // 或者实现Comparable Collections.sort(nodes);
            // ② 取出根节点权值最小的两颗二叉树, 最小和第二小
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            // ③ 构建一颗新的二叉树
            Node parent = new Node(left, right);
            // ④ 干掉list中删除处理过的二叉树, 加入parent
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        // 返回赫夫曼树的root节点
        return nodes.get(0);
    }

    /**
     * 前序遍历
     * @param root
     */
    public static void preOrder(Node root) {
        if(root == null) throw new RuntimeException("空树");
        root.preOrder();
    }

}
