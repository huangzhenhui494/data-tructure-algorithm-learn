package com.hzh.chapter7.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @description: @ https://mp.weixin.qq.com/s/vn3KiV-ez79FmbZ36SX9lg
 * @Author huangzhenhui
 * @Date 2021/2/24 11:14
 */
public class SortTest {

//    private static int[] arr = {3,10,11,-1,1,7,2,-5,3,5,-2,0,1,2,5,-1,2,-5,-11,24,0,7,-7,6,9,3,4,9,2,8,0,1,4,7};
//    private static int[] arr = {5,6,1,2,4,3,7,9};
    private static int[] arr = {6,1,2,7,9,3,4,5,10,8,2,4,5,6,10,2,22,33};
    private static int[] testTimeArr;

    static {
        testTimeArr = new int[8000000];
        for(int i = 0; i < 8000000; i++) {
            testTimeArr[i] = (int) (Math.random() * 800000000); // 生成一个[0, 8000000)数
        }
    }


    public static void main(String[] args) {
//        bubbleSort(arr);
//        selectSort(arr);
//        insertSort(arr);
//        shellSwapSort(arr);
//        shellExchangeIndexSort(arr);
//        quickSort(arr, 0, arr.length - 1);
//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void bubbleSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        bubbleSort(testTimeArr);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 9445
    }

    @Test
    public void selectSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        selectSort(testTimeArr);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 1830
    }

    @Test
    public void insertSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        insertSort(testTimeArr);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 507
    }

    @Test
    public void shellSwapSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        shellSwapSort(testTimeArr);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 2129
    }

    @Test
    public void shellExchangeIndexSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        shellExchangeIndexSort(testTimeArr);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 1732   800w
    }

    @Test
    public void quickSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        quickSort(testTimeArr, 0, testTimeArr.length - 1);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 800w 864
    }

    @Test
    public void mergeSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        mergeSort(testTimeArr, 0, testTimeArr.length - 1, new int[testTimeArr.length]);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 800w 1362
    }

    @Test
    public void radixSortTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        radixSort(testTimeArr);
        System.out.println("结束");
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 800w 965
    }



    /**
     * 冒泡排序 (n^2)
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
     * 选择排序 (n^2)
     * 1 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * 2 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 3 重复第二步，直到所有元素均排序完毕。
     */
    public static void selectSort(int[] arr) {
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
     *
     */
    public static void insertSort(int[] arr) {
        // 10,3,10,6,9,3,4,9,2,8,1,4,7
        for(int i = 1; i < arr.length; i++) {
            // i=0默认有序, 从第二个数开始, 拿数和前面的比较
            int j = i;
            int temp = arr[i];
            while (j - 1 >= 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 交换
            arr[j] = temp;
        }
    }

    /**
     * 二分插入排序
     */
    public static void binaryInsertSort() {}

    /**
     * 希尔排序-交换式 **** 一个个比较后交换的, 速度有点慢
     * 1 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
     * 2 按增量序列个数 k，对序列进行 k 趟排序；
     * 3 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。
     *   仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     *   采取的是**增量序列每次减半**的策略
     *
     */
    public static void shellSwapSort(int[] arr) {
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
     * 对交换式的希尔排序进行优化 -> 移位法: 交换的时候不要发现一个就交换一个
     * @param arr
     */
    public static void shellExchangeIndexSort(int[] arr) {
        for(int g = arr.length / 2; g > 0; g /= 2) {
            for(int i = g; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                while (j - g >= 0 && temp < arr[j - g]) {
                    // 移动
                    arr[j] = arr[j - g];
                    j -= g;
                }
                // 当while退出后, 已经找到位置了
                arr[j] = temp;
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
    public static void quickSort(int[] arr, int left, int right) {
        // 以左边第一个数为基准, right向左移动, 直到找到一个比基准数小的数停下, left向右移动, 直到找到一个比基准数大的数停下
        int p = left;
        int l = left;
        int r = right;
        while (left < right) {
            // right左移
            while (arr[right] > arr[p]) {
                right--;
            }
            // left右移动, 停下时, 若和right不重合则交换, 重合则交换基准数, 进入下一轮
            while (arr[left] <= arr[p] && left < right) {
                left++;
            }
            if (left == right) {
                swap(arr, p, left);
                p = left;
                quickSort(arr, l, p-1);
                quickSort(arr, p + 1, r);
            } else {
                swap(arr, left, right);
            }
        }
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
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (right +left) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;     // 左边待合并数组的起始索引
        int j = mid + 1;  // 右边待合并数组的起始索引
        int t = 0;        // 临时数组的索引

        // 比较, 小的那个移动到临时数组
        while (i <= mid && j <= right) {
            if(arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        // 如果还有数据, 按顺序移动过去
        while (i <= mid) {
            temp[t] = arr[i];
            i += 1;
            t += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        // 合并
        t = 0;
        while (left <= right) {
            arr[left] = temp[t];
            t += 1;
            left += 1;
        }

    }

    /**
     * 基数排序
     *
     * 二维数组, 为了防止在放入数的时候, 数据溢出, 则每一个一维数组(桶), 大小定为arr.length
     * 使用空间换时间的经典算法
     * 负数的话分开, 取负数后再取逆序  十位 (arr[i] / 10) % 10
     * 数据量太大可能堆溢出
     */
    public static void radixSort(int[] arr) {

        // 得到数组中最大数的位数
        int max = arr[0]; // 假设第一数就是最大数
        for(int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大数是几位数
        int maxLength = (max + "").length();

        // 定义一个二维数组, 表示10个桶
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个同种, 实际存放了多少个数据, 我们定义一个一维数组来记录每个桶的每次放入的数据个数
        int[] elementCounts = new int[10];

        for(int j = 0; j < maxLength; j++) {
            // 第一轮, 对每个元素个位数进行排序后取出, 第二轮对每个元素的十位数排序放入桶后取出, ...
            for(int i = 0; i < arr.length; i++) {
                int digitOfElement = arr[i] / (int) Math.pow(10, j) % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][elementCounts[digitOfElement]] = arr[i];
                elementCounts[digitOfElement] += 1;
            }
            // 按照这个桶的顺序(一维数组的下标一次取出数据, 放入原来数组)
            int arrIndex = 0;
            // 遍历每一个桶, 并将桶中是数量, 放入到原数组
            for(int k = 0; k < elementCounts.length; k++) {
                // 如果桶中有数据,
                if (elementCounts[k] != 0) {
                    // 循环该桶即第k个桶(即第k个一维数组), 放入
                    for(int l = 0; l < elementCounts[k]; l++) {
                        arr[arrIndex] = bucket[k][l];
                        arrIndex++;
                    }
                }
                // 每一轮处理后, 需要将每个elementCounts[k] = 0
                elementCounts[k] = 0;
            }
        }
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
