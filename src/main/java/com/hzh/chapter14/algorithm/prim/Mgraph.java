package com.hzh.chapter14.algorithm.prim;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/17 21:22
 */
public class Mgraph {

    /**
     * 表示图的节点个数
     */
    int verxs;

    /**
     * 存放节点数据
     */
    char[] data;

    /**
     * 存放边, 就是我们的邻接矩阵
     */
    int[][] weight; // 存放边

    public Mgraph(char[] data, int[][] weight) {
        this.verxs = data.length;
        this.data = data;
        this.weight = weight;
    }

    public int getVerxs() {
        return verxs;
    }

    public char[] getData() {
        return data;
    }

    public int[][] getWeight() {
        return weight;
    }
}
