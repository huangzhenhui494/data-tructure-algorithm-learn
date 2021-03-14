package com.hzh.chapter14.algorithm.binarysearchnorecursion;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/14 10:08
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 8, 8, 8, 9, 9, 10, 11, 67, 100};
        int i = binadySearch(arr, 0);
        System.out.println(i);
    }

    /**
     * 二分查找非递归实现  arr是升序排列
     * @param arr 待查找的数组
     * @param target 需要查找的数
     * @return 返回对应下标, -1表示没有找到
     */
    public static int binadySearch(int[] arr, int target) {

        int start = 0;
        int end = arr.length  - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if(arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;   // 向左查找
            } else if (arr[mid] < target) {
                start = mid + 1; // 向右查找
            }
        }
        return -1;
    }
}

