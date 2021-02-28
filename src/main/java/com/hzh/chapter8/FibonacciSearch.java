package com.hzh.chapter8;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/28 23:11
 */
public class FibonacciSearch {

    private static final int MAX_SIZE = 20;

    /**
     * 斐波那契查找
     * @param arr
     * @return
     */
    public static int fibonacciSearch(int[] arr) {

        return 0;
    }

    /**
     * 因为后面我们mid = low + F(k-1)-1, 需要使用到斐波那契数列, 因此我们需要先获取到一个斐波那契数列
     * 非递归的方式获取
     * @return
     */
    private static int[] fib() {
        int[] f = new int[MAX_SIZE];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < MAX_SIZE; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
