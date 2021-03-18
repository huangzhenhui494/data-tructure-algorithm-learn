package com.hzh.chapter14.algorithm.prim;

import org.junit.Test;

/**
 * @description: 普里姆算法
 * @Author huangzhenhui
 * @Date 2021/3/17 21:00
 */
public class PrimAlgorithm {

    @Test
    public void test1() {
        // 测试图是否创建OK
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵的关系使用二维数组表示
        int [][]weight = new int[][]{  // 10000表示点不连通
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};

        // 创建图
        Mgraph graph = new Mgraph(data, weight);
        // 创建最小生成树
        MinTree minTree = new MinTree(graph);
//        // 打印图
//        minTree.printGraph();
        minTree.prim(5);

        // TODO https://blog.csdn.net/qq_38415505/article/details/83687207

    }


}
