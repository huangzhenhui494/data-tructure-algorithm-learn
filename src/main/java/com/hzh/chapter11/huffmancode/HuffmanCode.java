package com.hzh.chapter11.huffmancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/9 20:33
 */
public class HuffmanCode {

    private static Map<Byte, String> huffmanCodes = new HashMap<>();

     private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);
        Node huffmanTree = createHuffmanTree(getNodes(contentBytes));
        preOrder(huffmanTree);
    }

    /*
    生成赫夫曼树对应的赫夫曼编码
    思路:
    1 将赫夫曼编码表存放在Map<Byte, String> 形式类似 32->01, 97->100
    2 在生成赫夫曼编码表示, 需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
       */

    /**
     * 将传入的node结点的所有叶子结点的赫夫曼编码得到, 并放入到huffmanCodes
     * @param node 传入结点
     * @param code 路径: 左子结点是0, 右子结点1
     * @param sb   用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb) {

    }

    /**
     * 前序遍历
     * @param root
     */
    private static void preOrder(Node root) {
        if (root == null) {
            throw new RuntimeException("空");
        }
        root.preOrder();
    }

    /**
     * @param bytes  接收字节数组
     * @return       返回nodes
     */
    private static List<Node> getNodes(byte[] bytes) {

        // ① 创建一个list
        List<Node> nodes = new ArrayList<>();
        // ② 遍历bytes, 统计每一个byte出现的次数
        Map<Byte, Integer> bMap = new HashMap<>();
        for (byte b : bytes) {
            if (bMap.get(b) == null) {
                bMap.put(b, 1);
            } else {
                bMap.put(b, bMap.get(b) + 1);
            }
        }
        // ③ 遍历Map, 把每个键值对转成一个node对象,并加入nodes
        for (Map.Entry<Byte, Integer> entry : bMap.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * @param nodes 创建赫夫曼树
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        // ④ 生成赫夫曼树
        while (nodes.size() > 1) {
            // 排序, 从小到大
            Collections.sort(nodes);
            // 取出第一,二颗最小的二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            // 创建一颗新的二叉树, 根结点没有data只有权值
            Node parent = new Node(null, left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            // 将已经处理的两颗二叉树移除
            nodes.remove(left);
            nodes.remove(right);
            // 加入新结点
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
