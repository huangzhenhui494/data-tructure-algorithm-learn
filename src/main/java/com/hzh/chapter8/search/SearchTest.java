package com.hzh.chapter8.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/28 16:49
 */
public class SearchTest {

//    private static int[] arr = {1,1,1,1,3,5,6,7,9,11,22,55,55,55,55,63,69,77,78,89,101};
    private static int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

    public static void main(String[] args) {
        System.out.println(seqSearch(arr, 7));
    }

    @Test
    public void seqSearchTest() {

    }

    @Test
    public void binarySearchTest() {
        System.out.println(binarySearch(arr, 0, arr.length - 1, 1));
    }

    @Test
    public void insertValueTest() {
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 17));
    }


    /**
     * 这里实现找到一个就返回的
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        // 线性查找就是逐一对比, 发现有相同值, 就返回下标
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找: 使用二分查找的前提是 该数组是有序的
     * @param arr    数组
     * @param left   左边的索引
     * @param right  右边的索引
     * @param value  要查找的值
     * @return 找到返回下标, 没有找到返回-1
     */
    public static List<Integer> binarySearch(int[] arr, int left, int right, int value) {
        if(left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        int minVal = arr[mid];

        if (value > minVal) {  // 向右递归
            return binarySearch(arr, mid + 1, right, value);
        } else if (value < minVal) { // 向左递归
            return binarySearch(arr, left, mid - 1, value);
        } else {
            // 找到后, 向左右扫描返回所有
            List<Integer> result = new ArrayList<>();
            int temp = mid;
            while (temp >= 0 && arr[temp] == value) {
                result.add(temp);
                temp -= 1;
            }
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == value) {
                result.add(temp);
                temp += 1;
            }
            return result;
        }
    }

    /**
     * 插值查找法, 也要求数组有序   int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
     * @param arr      数组
     * @param left     左边索引
     * @param right    右边索引
     * @param findVal  查找值
     * @return  找到返回下标, 没有找到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        // 注意: findVal < arr[0] || findVal > arr[arr.length - 1] 必须要
        // 否则得到的mid可能越界
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 求出mid, 插值法
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int minVal = arr[mid];
        if (findVal > minVal) {        // 说明向右递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if(findVal < minVal) {  // 说明向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
