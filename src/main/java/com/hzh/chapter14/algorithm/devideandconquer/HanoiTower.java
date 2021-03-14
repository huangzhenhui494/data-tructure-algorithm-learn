package com.hzh.chapter14.algorithm.devideandconquer;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/14 15:27
 */
public class HanoiTower {

    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔移动的方法: 使用分治算法
     * 汉诺塔游戏的演示和思路分析
     * 1) 如果是有一个盘， A->C
     * 如果我们有 n >= 2 情况，我们总是可以看做是两个盘1.最下边的盘 2. 上面的盘
     * 2)先把 最上面的盘 A->B
     * 3) 把最下边的盘 A->C
     * 4) 把 B 塔的所有盘 从 B->C
     *
     *
     * 第一步:
     *      把n-1个模块, 从塔1移动到塔二
     *      把第n个模块, 从塔1移动到塔三
     * 第二步: 把n-1个模块, 从塔2移动到塔三
     *
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + " -> " + c);
        } else {
             // 多个盘, 看成只有两个盘, 只有两个盘就是 1 最下边的一个盘 2 上面的所有盘
            // 1 先把最上面的所有盘 A -> B, 移动过程会使用到c
            hanoiTower(num - 1, a, c, b);
            // 2 把最下边的盘A -> C
            System.out.println("第" + num + "个盘从" + a + " -> " + c);
            // 3 把B塔的所有盘从B -> C, 移动过程使用到a塔
            hanoiTower(num - 1, b, a, c);
        }
    }

}
