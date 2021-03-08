package com.hzh.chapter7.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @description: 堆排序  N logN
 * @Author huangzhenhui
 * @Date 2021/3/8 20:46
 */
public class HeapSort {

    private static int[] testTimeArr;

    static {
        testTimeArr = new int[8000000];
        for(int i = 0; i < 8000000; i++) {
            testTimeArr[i] = (int) (Math.random() * 80000000); // 生成一个[0, 8000000)数
        }
    }

    /**
     * 要求将数组进行升序排序
     */
    @Test
    public void heapSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        heapSort(testTimeArr);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 1859
    }

    public static void main(String[] args) {
        int[] arr = {3,10,11,-1,1,7,2,-5,3,5,-2,0,1,2,5,-1,2,-5,-11,24,0,7,-7,6,9,3,4,9,2,8,0,1,4,7};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆排序-自己画圈, 从arr.length / 2 - 1开始, 从右到左从下到上画圈
     * @param arr
     */
    public static void heapSort(int[] arr) {
        // ① 将无序序列构建成一个堆, 根据升序降序需求选择大顶堆或小顶堆
        // 从右到左, 从下到上进行调整堆(在adjustHeap中会从上到下调整)(自己画图, 不动手想屁吃)
        for(int i = arr.length / 2 - 1; i >= 0; i--) {
            // 这里要用arr.length, 因为堆顶放置后下面的会被打乱
            adjustHeap(arr, i, arr.length);
        }
        // ② 将堆顶元素与末尾元素交换,将最大元素"沉"到数组末端
        for(int j = arr.length - 1; j > 0; j--) {
            // 堆顶元素和末尾元素替换(沉下去的不算)
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            // ③ 重新调整结构, 使其满足堆定义, 然后继续交换堆顶元素与当前末尾元素, 反复执行调整+交换步骤, 直到整个序列有序
            // 此时arr[0]是堆顶元素且是底部的小元素, 只要将其从上到下放到该放的位置就行, 一次调整
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 调整堆
     * 完成-> 将以 i 对应的非叶子节点的树调整成大顶堆
     * 举例:
     *  int[] arr[] = {4, 6, 8, 5, 9}; =>
     *     ① i = arr.length / 2 - 1 = 1, 以i的树调整成大顶堆(6,5,9, 9最大, 和6换) =>  {4,9,8,5,6}
     *     ② 该节点的父节点为 (n - 1) / 2 = 0, 以0的树调成大顶堆(4,9,8, 9最大, 和4换, 但此时下面混乱了继续调整
     *     (也就是说调整后自身后面的节点要继续调)) => {9,6,8,5,4} 大顶堆
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整
     */
    private static void adjustHeap(int arr[], int i, int length) {

        int temp = arr[i];
        // 开始比较其左后方的树
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 比较i左右孩子节点的大小, k指向大的, 且k+1不能越界
            if(k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            // 比较k和temp的大小(主要不要用arr[i], i会后移), 如果k指向的比i大, i的值替换成k, i后移
            if(arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break; // 此时一开始的i处已经是该循环后的最大值, 结束循环
            }
        }
        // 最后将此时i处的值替换成temp
        arr[i] = temp;
    }
}
