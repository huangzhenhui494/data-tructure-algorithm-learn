package com.hzh.chapter14.algorithm.floyd;

import java.util.Arrays;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/21 11:07
 */
public class Graph {

    /**
     * 存放顶点的数组
     */
    private char[] vertex;

    /**
     * 保存从各个顶点出发到其他顶点的距离, 最后的结果, 也是保留在该数组
     */
    private int[][] dis;

    /**
     * 保存到达目标顶点的前驱顶点
     */
    private int[][] pre;

    /**
     * 构造器
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对pre数组初始化, 注意存放的是前驱节点的下标(不是ABCD)
        for(int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示pre数组和dis数组
    public void show() {
        System.out.println("vertex");
        System.out.println(Arrays.toString(vertex));
        System.out.println("------------------------");
        System.out.println("dis");
        for (int[] di : dis) {
            System.out.println(Arrays.toString(di));
        }
        System.out.println("------------------------");
        System.out.println("pre");
        for (int[] ints : pre) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        int len; // 变量保存距离
        for(int k = 0; k < dis.length; k++) { // 对中间顶点的遍历, k就是中间顶点的下标 ['A','B','C'....]
            for (int i = 0; i < dis.length; i++) { // 从i顶点开始出发 ['A','B','C'....]
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j]; // i到k的距离 + k到j的距离  => 求出从i出发经过k中间顶点到达j顶点的距离
                    if (len < dis[i][j]) { // 如果len小于 dis[i][j]直连距离
                        dis[i][j] = len; // 更新距离
                        pre[i][j] = pre[k][j]; // 该节点的前驱节点存储的是该循环下中间节点的下标, 如AD距离为12的, 中间节点是F而不是G
                    }
                }
            }
        }
    }
}
