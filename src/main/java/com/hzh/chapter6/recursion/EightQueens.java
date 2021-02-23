package com.hzh.chapter6.recursion;

/**
 * @description: 八皇后问题 https://www.bilibili.com/video/BV1E4411H73v?p=49&spm_id_from=pageDriver
 * @Author huangzhenhui
 * @Date 2021/2/23 23:03
 */
public class EightQueens {

    static int count;
    // 定义一个max表示共有多少个皇后
    int max = 8;
    // 定义数组array,保存皇后放置位置的结果, 比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    public static void main(String[] args) {

        EightQueens eightQueens = new EightQueens();
        eightQueens.check(0);
        System.out.println(count);

    }

    /**
     * judge后放置皇后
     * 特别注意: check是每一次递归时, 进入到check都有一次循环, 因此会有回溯
     * @param n
     */
    private void check(int n) {
         if(n == max) { // n = 8准备放置第9个皇后, 此时8个皇后已经放好了
            print();
            return;
         }
         // 依次放入皇后, 并判断是否冲突
        for(int i = 0; i < max; i++) {
            // 先把当前这个皇后n, 放到该行的第1列 (n=0, i=0) , 第几个皇后就是第 n+1行
            array[n] = i;
            // 判断当前放置第n个皇后到i列时, 是否冲突
            if (!judge(n)) {
                // 不冲突, 接着放n+1个皇后即开始递归
                check(n + 1);
            }
            // 如果冲突, continue, 列数i++; 即将第n个皇后放置在本行的后移的一个位置
        }
    }

    /**
     * 是否冲突
     * 查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            /**
             * 说明:
             * 1 array[i] == array[n] 表示判断 第n个皇后是否和前面的n-1个皇后在同一列
             * 2 Math.abs 绝对值, 这里用了一维数组, 用值和index, 斜率
             */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 写一个方法, 可以将皇后摆放的位置输出
     */
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        count++;
    }
}
