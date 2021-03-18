package com.hzh.chapter14.algorithm.kruskal;

import java.util.Arrays;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/18 7:03
 */
public class KruskalTest {

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
         int matrix[][] =
                 { /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                   /*A*/{ 0, 12, KruskalCase.INF, KruskalCase.INF, KruskalCase.INF, 16, 14},
                   /*B*/ { 12, 0, 10, KruskalCase.INF, KruskalCase.INF, 7, KruskalCase.INF},
                   /*C*/ { KruskalCase.INF, 10, 0, 3, 5, 6, KruskalCase.INF},
                   /*D*/ { KruskalCase.INF, KruskalCase.INF, 3, 0, 4, KruskalCase.INF, KruskalCase.INF},
                   /*E*/ { KruskalCase.INF, KruskalCase.INF, 5, 4, 0, 2, 8},
                   /*F*/ { 16, 7, 6, KruskalCase.INF, 2, 0, 9},
                   /*G*/ { 14, KruskalCase.INF, KruskalCase.INF, KruskalCase.INF, 8, 9, 0}};

        //创建 KruskalCase 对象实例
         KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        // 输出构建的
//         kruskalCase.print();
//        System.out.println(Arrays.toString(edges));
        kruskalCase.kruskal();

    }
}
