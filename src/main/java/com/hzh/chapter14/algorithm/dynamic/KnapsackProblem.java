package com.hzh.chapter14.algorithm.dynamic;

/**
 * @description: 背包问题
 *
 * 算法的主要思想，利用动态规划来解决。每次遍历到的第 i 个物品，根据 w[i]和 v[i]来确定是否需要将该物品 放入背包中。即对于给定的 n 个物品，
 * 设 v[i]、w[i]分别为第 i 个物品的价值和重量，C 为背包的容量。再令 v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值。
 * @Author huangzhenhui
 * @Date 2021/3/15 6:19
 */
public class KnapsackProblem {

    /**
     * 设val[i]为第i个商品的价值
     */
    private int[] val;

    /**
     * 设v[i]为第i个商品的重量
     */
    private int[] w;

    /**
     * v[i][j]表示前i个物品中能够装入容量为j的背包中的最大价值
     */
    private int[][] v;

    /**
     * 背包容量
     */
    private int m;

    /**
     * 路径
     */
    private int[][] path;

    /**
     * 构造器
     * @param val 物品的价值, 三个物品就是(0, 1, 2)
     * @param w 物品的重量, 三个物品就是(0, 1, 2)
     * @param m 背包容量(重量)
     */
    public KnapsackProblem(int[] val, int[] w, int m) {
        this.val = val;
        this.w = w;
        this.m = m;
        this.v = new int[val.length + 1][m + 1]; // 物品的容量+1, 列重量从0开始
        this.path = new int[val.length + 1][m + 1];
        // 0行0列均为0, 这边是int二维数组就不用弄了
    }

    public static void main(String[] args) {
        int[] v = {1500, 3000, 2000};
        int[] w = {1, 4, 3};
        KnapsackProblem knapsackProblem = new KnapsackProblem(v, w, 4);
        knapsackProblem.kanpsack();


    }

    /**
     * j为列
     */
    public void kanpsack() {

        for(int i = 1; i < v.length; i++) {    // 都不处理第一列
            for(int j = 1; j < v[0].length; j++) {  // 都不处理第一列
                if(w[i - 1] > j) {  // 当前物品重量大于该列重量, 装不下, 拿上一个的, 程序从1开始, 所以用i-1表示商品
                    v[i][j] = v[i - 1][j];
                } else {
                    // 当前重量大于等于该列重量, 比较[该列上一个 和 (重量==则直接放入当前商品, 重量>=则直接放入商品再拿该行相应剩余价值)]的价值
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]); // 但是math不能记录位置
                    // 为了记录存到背包的情况
                    if(v[i - 1][j] >= val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        // 最优解记录path
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }
                }
            }
        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if(path[i][j] == 1) {
                System.out.println(String.format("第%s个商品放入到背包", i));
                j -= w[i - 1];
            }
            i--; // 每次while都是寻找第i个商品是否放了进去
            // 比如第三个商品放进去了, j -= w[i - 1] , 商品重量剩下了1了, 此时只有当商品重量是1的时候才能放进去了
        }

    }
}
