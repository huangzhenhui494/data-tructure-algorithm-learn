package com.hzh.chapter14.algorithm.dijkstra;

import org.junit.Test;

/**
 * @description: 迪杰斯特拉算法
 * @Author huangzhenhui
 * @Date 2021/3/19 7:55
 */
public class DijkstraAlgorithm {

    @Test
    public void test() {
        // 顶点
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建 Graph 对象
        Graph graph = new Graph(vertex, matrix);
        // 测试, 看看图的邻接矩阵
        graph.showGraph();
        // 测试迪杰斯特拉算法 graph.dsj(2);
        graph.djs(6);
        graph.showDijkstra();
    }
}
