package com.hzh.chapter14.algorithm.kruskal;

import org.apache.logging.log4j.message.ReusableMessage;
import org.omg.CORBA.INTERNAL;

import java.util.Arrays;

/**
 * @description: 克鲁斯卡尔算法
 * @Author huangzhenhui
 * @Date 2021/3/18 6:51
 */
public class KruskalCase {

    /**
     * 边的个数
     */
    private int edgeNum;

    /**
     * 订单数组
     */
    private char[] vertexs;

    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    public static final int INF = Integer.MAX_VALUE;

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }

    public char[] getVertexs() {
        return vertexs;
    }

    public void setVertexs(char[] vertexs) {
        this.vertexs = vertexs;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * 构造器初始化
     * @param vertexs
     * @param matrix
     */
    public KruskalCase(char[] vertexs, int[][] matrix) {
        // 初始化顶大数和边的个数
        int vlen = vertexs.length;
        // 初始化顶点, 复制拷贝的方式, 防止外部引用修改数组
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        // 初始化边, 复制拷贝方式
        this.matrix = new int[vlen][vlen];
        for(int i = 0; i < vlen; i++) {
            for(int j = 0; j< vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        // 统计边
        for(int i = 0; i < vertexs.length; i++) {
            for(int j = i + 1; j < vlen; j++) {
                if(this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0; // 表示最后结果数组的索引
        int[] ends = new int[edgeNum]; // 用于保存"已有最小生成树" 中的每个订单在最小生成树中的终点
        // 创建结果数组, 保存最后的最小生成树
        EData[] rets = new EData[edgeNum];
        // 获取途中所有的边的集合, 一共有12条边
        EData[] edges = getEdges();
        // 按照边的权值大小从小到大排序
        sortEdges(edges);
//        for (EData edge : edges) {
//            System.out.println(edge);
//        }
        // 遍历edges数组, 将边添加到最小生成树中时, 判断准备的边是否形成了回路, 如果没有就加入到结果数组
         for(int i = 0; i < edgeNum; i++) {
             // 获取到第i条边的第一个顶点(起点)
             int p1 = getPosition(edges[i].start);
             // 获取到第i条边的第二个顶点
             int p2 = getPosition(edges[i].end);
             // 获取p1这个顶点在已有最小生成树中的终点
             int m = getEnd(ends, p1);
             // 获取p2这个顶点在已有最小生成树中的终点
             int n = getEnd(ends, p2);
             // 是否构成回路
             if(m != n) { // 没有构成回路
                 ends[m] = n; // 设置m在 已有最小生成树 中的终点
                 rets[index++] = edges[i];
                 /**
                  * 初始化时ends为  [0,0,0,0,0,0,0,0,0,0,0,0]
                  * <E,F>
                  * 当p1 = 4, p2 = 5时,  m = 4(终点是自己),  n = 5
                  * [0,0,0,0,5,0,0,0,0,0,0,0], 即start的终点是end, 即E的终点是F
                  * 此时, getEnd, 传入4和5拿到的顶点都是自己
                  */
             }
         }
         // 统计并打印最小生成树, 输出rets数组
        for(int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    /**
     * 打印
     */
    public void print() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 排序, 冒泡
     * @param edges 边的集合
     */
    public void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            boolean isSort = true;
            for(int j = 0; j < edges.length - i - 1; j++) {
                if(edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j + 1];
                    edges[j + 1] = edges[j];
                    edges[j] = temp;
                    isSort = false;
                }
            }
            if (isSort) return;
        }
    }

    /**
     * 找下标
     * @param ch 顶点的值, 比如 'A', 'B'
     * @return 返回ch订单对应的下标, 如果找不到, 返回-1
     */
    private int getPosition(char ch) {
        for(int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 遍历右上三角
     * 获取图中的边, 放到EData[] 数组中, 后面我们需要遍历该数组, 通过matrix 邻接矩阵来获取
     * @return 形式大概 [['A','B',12], ['B', 'F', 7] ...]
     */
    public EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for(int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if(matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点, 用于后面判断两个顶点的终点是否相同
     * @param ends 记录了各个顶点对应的终点是哪个, ends数组是在遍历过程中逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是 下标为i的这个顶点对应的终点的下标
     * DEF
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}
