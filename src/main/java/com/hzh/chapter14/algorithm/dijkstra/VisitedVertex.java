package com.hzh.chapter14.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/20 7:08
 */
public class VisitedVertex {

    // 记录各个顶点是否访问过 1表示访问过, 0未访问, 动态更新
    public int[] alreadyArr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] preVisited;
    // 记录出发顶点到其他所有顶点的距离, 比如G为出发顶点, 就会记录G到其他顶点的距离, 会动态更新, 求的最短距离就会存放到dis
    public int[] dis;

    /**
     * 构造器
     * @param length 表示顶点的个数
     * @param index  出发顶点对应的下标, 比如G顶点, 下标就是6
     */
    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];
        // 初始化dis数组
        Arrays.fill(dis, 65535);
        this.alreadyArr[index] = 1; // 设置出发顶点被访问过
        this.dis[index] = 0; // 设置出发顶点的访问距离为0
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return 如果访问过, 就返回true, 否则访问false
     */
    public boolean in(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 功能, 更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新顶点的前驱顶点为index顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点, 比如这里的G完后, 就是A点作为新的访问顶点(注意不是出发点)
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for(int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        // 更新index顶点被访问过
        alreadyArr[index] = 1;
        return index;
    }

    // 显示最后的结果
    // 即将三个数组的情况输出
    public void show() {
        System.out.println("------------------------------------");
        // 输出already_arr
        for(int i : alreadyArr) {
            System.out.print(i);
        }
        System.out.println("------------------------------------");
        // 输出pre
        for(int i : preVisited) {
            System.out.print(i);
        }
        System.out.println("------------------------------------");
        // 输出dis
        for(int i : dis) {
            System.out.print(i);
        }
    }
}
