package com.hzh.chapter14.algorithm.prim;

import java.util.Arrays;

/**
 * @description: 最小生成树
 * @Author huangzhenhui
 * @Date 2021/3/17 21:28
 */
public class MinTree {

    private Mgraph graph;

    public MinTree(Mgraph graph) {
        this.graph = graph;
    }

    public Mgraph getMgraph() {
        return graph;
    }

    /**
     * 编写prim算法, 得到最小生成树
     * @param v 传进来需要规划的节点
     */
    public void prim(int v) {

        int totalWeight = 0;
        // 默认都是0, 表示没有访问过
        int visited[] = new int[graph.verxs];
        // 传进来的是v, 需要标志为已访问
        visited[v] = 1;
        // h1和h2记录两个节点的下标
        int h1 = -1;
        int h2 = -1;
        // 初始化minWeight, 遍历过程中会被替换
        int minWeight = 10000;
        // 共有graph.data.length - 1条边, 所以要遍历 data.length - 1 次
        for(int k = 1; k < graph.data.length; k++) {
            // 每次遍历都将已经连线的节点和另一个未连线的节点进行权值比较, 取最小的进行连接
            for(int i = 0; i < graph.verxs; i++) {
                for(int j = 0; j < graph.verxs && visited[i] == 1; j++) { // 找到已经访问过的节点和未访问过的节点尝试连线
                    // 确认i访问过且j未访问过, 并比较权值
                    if(visited[j] == 0 && graph.getWeight()[i][j] < minWeight) {
                        minWeight = graph.getWeight()[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 连线成功
            visited[h2] = 1;
            // 重置minWeight
            minWeight = 10000;
            // 打印
            System.out.println(String.format("第%s次大循环, 对应边<%s,%s>, 权值: %s", k, graph.data[h1], graph.data[h2], graph.weight[h1][h2]));
            totalWeight += graph.weight[h1][h2];
        }
        System.out.println("权值:" + totalWeight);
    }

    /**
     * 打印图
     */
    public void printGraph() {
        for (int[] s : graph.getWeight()) {
            System.out.println(Arrays.toString(s));
        }
    }
}
