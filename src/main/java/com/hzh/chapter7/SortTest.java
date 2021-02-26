package com.hzh.chapter7;

import java.util.Arrays;

/**
 * @description: @ https://mp.weixin.qq.com/s/vn3KiV-ez79FmbZ36SX9lg
 * @Author huangzhenhui
 * @Date 2021/2/24 11:14
 */
public class SortTest {

//    private static int[] arr = {3,10,11,1,7,2,3,5,6,9,3,4,9,2,8,1,4,7};
//    private static int[] arr = {5,6,1,2,4,3,7,9,8,0};
    private static int[] arr = {6,1,2,7,9,3,4,5,10,8};

    public static void main(String[] args) {
//        bubbleSort(arr);
//        selectSort(arr);
//        insertSort(arr);
//        shellSort(arr);
        quickSort(arr, 0, arr.length - 1);



        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * 1 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 2 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 3 针对所有的元素重复以上的步骤，除了最后一个。
     * 4 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    public static void bubbleSort(int[] arr) {

        for(int i = arr.length - 1; i > 0; i--) {
            boolean isSort = true;
            for(int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]) {
                    // 存在需要交换的值则需要进入下一轮, 本轮不需要交换则已经排序完成
                    isSort = false;
                    swap(arr, j, j+1);
                }
            }
            if(isSort) return;
        }
    }

    /**
     *
     * 选择排序
     * 1 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * 2 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 3 重复第二步，直到所有元素均排序完毕。
     */
    private static void selectSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 插入排序
     * 1 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     * 2 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
     * （如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
     */
    private static void insertSort(int[] arr) {
        // 10,3,10,6,9,3,4,9,2,8,1,4,7
        for(int i = 1; i < arr.length; i++) {
            // i=0默认有序, 从第二个数开始, 拿数和前面的比较
            int temp = arr[i];
            for(int j = i - 1; j >= 0; j--) {
                if(temp < arr[j]) {
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 二分插入排序
     */
    private static void binaryInsertSort() {}

    /**
     * 希尔排序 ****
     * 1 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
     * 2 按增量序列个数 k，对序列进行 k 趟排序；
     * 3 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。
     *   仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     *   采取的是**增量序列每次减半**的策略
     *
     *   5,6,1,2,4 3,7,9,8,0
     */
    private static void shellSort(int[] arr) {
        for(int g = arr.length / 2; g > 0; g /= 2) {  // 当g=1时, 整除2=0
            // 从增量开始遍历, 按照步长比较
            for(int i = g; i < arr.length; i++) {
                // 比如当g=5时,i=5时, arr[5]和arr[i-g]即arr[0]比较, 进入下一个循环arr[6]和arr[1]比较(此时是另外一组了)
                // 当g=2时,i=2时, arr[2]和arr[i-g]即arr[0]比较形成有效序列后, 换下一组的[3][1], 再换下一组的[4][2][0]
                int temp = arr[i];
                for(int j = i - g; j >= 0; j -= g) {
                    if(temp < arr[j]) {
                        arr[j + g] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 快速排序  https://blog.csdn.net/shujuelin/article/details/82423852
     *          https://blog.csdn.net/lkp1603645756/article/details/85008715
     * 1 从数列中挑出一个元素，称为 “基准”（pivot）;
     * 2 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     *   在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 3 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int p = findPivot(arr, left, right);
            quickSort(arr, left, p-1);
            quickSort(arr, p + 1,right);
        }
    }

    /**
     * 快速排序找基准值
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int findPivot(int[] arr, int left, int right) {

        return 1;
    }

    /**
     * 归并排序 O(n log n)
     * 1 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列;
     * 2 设定两个指针，最初位置分别为两个已经排序序列的起始位置;
     * 3 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置;
     * 4 重复步骤 3 直到某一指针达到序列尾;
     *
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     * @param arr
     */
    private static void mergeSort(int[] arr) {
        // 6 1 2 7 9 3 4 5 10 8
    }





    /**
     * 交换数组中的元素
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
}
