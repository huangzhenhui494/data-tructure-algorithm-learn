package com.hzh.chapter14.algorithm.dijkstra;

import org.omg.CORBA.OMGVMCID;

import java.util.Arrays;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/20 7:01
 */
public class Graph {

    /**
     * 顶点数组
     */
    private char[] vertex;

    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    /**
     * 已经访问顶点的集合
     */
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 迪杰斯特拉算法实现
     * @param index 表示出发顶点对应的下标
     */
    public void djs(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index); // 更新index顶点到周围顶点的距离和前驱顶点
        for(int j = 1; j < vertex.length; j++) {
            index = vv.updateArr(); // 选择并返回新的访问顶点
            update(index); // 更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱点
     * @param index
     */
    private void update(int index) {
        int len;
        // 遍历邻接矩阵的matrix[index]
        for(int j = 0; j < matrix[index].length; j++) {
            // len: 出发顶点到index顶点的距离 + 从index顶点到j
            len = vv.getDis(index) + matrix[index][j];
            // 如果j顶点没有被访问过, 并且len小于出发顶点到j顶点的距离, 就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index); // 更新j顶点的前驱为index顶点
                vv.updateDis(j, len);   // 更新出发顶点到j顶点的距离
            }
        }
    }

    /**
     * 显示结果
     */
    public void showDijkstra() {
        vv.show();
    }

}
