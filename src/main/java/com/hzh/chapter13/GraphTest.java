package com.hzh.chapter13;

import org.junit.Test;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/13 16:38
 */
public class GraphTest {

    /**
     * graph.png
     */
    @Test
    public void test1() {
        // 测试一把图是否创建ok
        int n = 5; // 节点的个数
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(n);
        // 循环添加顶点
        for (String s : vertexValue) {
            graph.insertVertex(s);
        }
        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();

        graph.dfs();
        graph.bfs();
    }

    /**
     * graph2.png
     * 深度优先(DFS): 纵向 1,2,4,8,5,3,6,7, 好好想想, 就是递归回溯
     * 广度优先(BFS): 横向 1,2,3,4,5,6,7,8, 就是一层层来
     */
    @Test
    public void test2() {
        // 测试一把图是否创建ok
        int n = 8; // 节点的个数
        String[] vertexValue = {"1", "2", "3", "4", "5", "6", "7", "8"};
        // 创建图对象
        Graph graph = new Graph(n);
        // 循环添加顶点
        for (String s : vertexValue) {
            graph.insertVertex(s);
        }
        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();
        graph.dfs();
        graph.bfs();
    }
}
