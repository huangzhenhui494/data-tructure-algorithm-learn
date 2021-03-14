package com.hzh.chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/13 16:12
 */
public class Graph {

    /** 存储顶点集合 */
    private List<String> vertexList;

    /** 存储图对应的领结矩阵 */
    private int[][] edges;

    /** 表示边的数目 */
    private int numOfEdges;

    private boolean[] isVisited;

    /**
     * 初始化矩阵和vertextList;
     * @param n
     */
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>();
        numOfEdges = 0;
    }

    /**
     * 广度优先遍历(非连通图)
     */
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        System.out.println("广度优先遍历");
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                bfs(i);
            }
        }
        System.out.println();
    }

    /**
     * 广度优先遍历 Broad First Search
     * @param i
     */
    private void bfs(int i) {
        int u; // 表示队列的头节点对应下标
        int w; // 邻接节点
        // 队列, 记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();// 当做队列用
        // 1 访问该节点v并标记该节点为已访问
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;
        // 2 节点v入队
        queue.addLast(i);
        // 3 当队列非空时, 继续执行
        while (!queue.isEmpty()) {
            // 4 出队列, 取得队列头节点u
            u = queue.removeFirst();
            // 5 查找u的第一个领结节点w
            w = getFirstNeighbor(u);
            while (w != -1) { // 6 w节点存在
                // 6.1 节点w存在且尚未访问, 则访问并标记已访问
                if(!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " -> ");
                    isVisited[w] = true;
                    // 6.2 节点w入队
                    queue.addLast(w);
                }
                // 6.3 查找u节点的继w邻接节点的下一个邻接节点w, 转到步骤6
                w = getNextNeighbor(u, w);  // 体现广度优先
            }
        }
    }


    /**
     * 对dfs 进行一个重载, 遍历所有的节点(非连通图)  Depth First Search
     */
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        System.out.println("深度优先遍历");
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                dfs(i);
            }
        }
        System.out.println();
    }

    /**
     * 深度优先遍历算法(连通图)
     * i 第一次就是0
     */
    private void dfs(int i) {
        // 首先我们访问该节点, 输出
        System.out.print(getValueByIndex(i) + " -> ");
        // 设置该节点为已经访问
        isVisited[i] =  true;
        // 访问完i后, 以i这个节点为当前节点进行深度遍历
        // 获取该节点i 的第一个邻接节点的 w 的下标
        int w = getFirstNeighbor(i);
        while (w != -1) { // 有邻接节点
            if(!isVisited[w]) { // 没有被访问过
                dfs(w);
            }
            // 如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点  [行][列]
     *    A  B  C  D  E
     * A [0, 1, 1, 0, 0]
     * B [1, 0, 1, 1, 1]
     * C [1, 1, 0, 0, 0]
     * D [0, 1, 0, 0, 0]
     * E [0, 1, 0, 0, 0]
     *
     *  如果此时v1,v2 为 1,0 => BA
     *  B的下一个节点就是1,2  => BC  返回C的下标2
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        // 比如 [1, 0, 1, 1, 1], 这一行为B, 列为ABCDE, 此时如果是在BA=1, 其下一个节点为BC=1
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if(edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 得到第一个邻接节点的下标 w
     * @param index
     * @return 如果存在就返回对应的下标
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 显示图矩阵
     */
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 获取节点个数
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 获取边数目
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点i(下标)对应的数据  例如 "0" -> "A"   1 -> "B"
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 添加节点
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 表示点的下标   ABCDE(012345)   AB连接时就是  [0][1] = 1, [1][0] =1
     * @param v2 表示点的下标
     * @param weight 权值(1或0) 1代表
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}
