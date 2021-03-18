package com.hzh.chapter14.algorithm.kruskal;

/**
 * @description: 表示一条边
 * @Author huangzhenhui
 * @Date 2021/3/18 7:09
 */
public class EData {

    /**
     * 边的一点
     */
    char start;

    /**
     * 边的另一点
     */
    char end;

    /**
     * 边的权值
     */
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    // 输出边
    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
