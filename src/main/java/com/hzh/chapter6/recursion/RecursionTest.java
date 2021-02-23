package com.hzh.chapter6.recursion;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/23 15:23
 */
public class RecursionTest {

    public static void main(String[] args) {
        test(4);
        int factorial = factorial(5);
        System.out.println(factorial);

    }

    // 打印问题
    public static void test(int n) {
        if(n > 2) {
            test(n - 1);
        }
        System.out.println(n);
    }

    // 阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n; // 1 * 2 * 3
        }
    }
}
