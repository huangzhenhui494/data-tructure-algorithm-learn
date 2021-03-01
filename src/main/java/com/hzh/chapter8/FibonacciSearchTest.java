package com.hzh.chapter8;

import java.util.Arrays;

/**
 * @description: 斐波那契  http://www.voidcn.com/article/p-wcfwjfdm-bcp.html
 * @Author huangzhenhui
 * @Date 2021/3/1 9:15
 */
public class FibonacciSearchTest {

    private static int[] arr = {-1,0,1,2,3,4,5,6,7,9,11,13};

    private static final int MAX_SIZE = 20;

    public static void main(String[] args) {
        int i = fibonacciSearch(-1);
        System.out.println(i);
    }

    /**
     * 斐波那契查找
     * 首先要明确：如果一个有序表的元素个数为n,并且n正好是（某个斐波那契数 - 1），即n=F[k]-1时，才能用斐波那契查找法。
     * 如果有序表的元素个n不等于（某个斐波那契数 - 1），即n≠F[k]-1，这时必须要将有序表的元素扩展到大于n的那个斐波那契数 - 1才行
     * ①
     * @param value
     * @return
     */
    public static int fibonacciSearch(int value) {

        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int[] F = fib();
        // 问题1 这个查找n在斐波那契数列中的位置，为什么是F[k] - 1,而不是F[k]?
        while (arr.length > F[k] - 1) {
            k++;
        }
        // 问题2 扩容到F[k] - 1,这个地方怎么控制?
        int temp[] = Arrays.copyOf(arr, F[k] - 1);
        for (int i = arr.length; i < F[k] - 1; i++) {
            temp[i] = arr[arr.length - 1];
        }
        // 左右查找
        while (low <= high) {
            int mid = low + F[k - 1] - 1;   // mid左边长度为low + F[k - 1] - 1, mid的索引为 low + F[k - 1] - 1
            if(value < temp[mid]) {   // 左边长度为 low + F[k - 1] - 1 = low + F[k - 2] + F[k - 3] - 1  ==>  F[k - 1] - 1 =
                                      // F[k - 2] -1 + 1 + F[k - 3] - 1  此时的索引为 min = low + F[k - 1] - 1 =  F[k - 2] -1 即k--
                high = mid - 1;
                k -= 1;               // f[k-2] = f[k-3] + f[k-4], 索引为 F[k - 3] -1， 现在是f[k - 1] -1， 所以 k -= 2
            } else if (value > temp[mid]) {
                low = mid + 1;
                k -= 2;  // 用索引来确认
            } else { // 找到,确定是哪个下标(注意, 这边右边扩容后有相同的)
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;

    }

    /**
     * 1 这是为了能正确递归计算mid值，可发现 f[k]-1 = (f[k-1] + f[k-2]) - 1 = (f[k-1]-1) + 1 + (f[k-2]-1)，
     * 中间的1就是我们二分的锚点mid，如果目标在左区，数组长度就缩到(f[k-1]-1)，如果在右区，数组长度就缩到(f[k-2]-1)，
     * 否则就等于mid完成查找。而(f[k-1]-1)又能拆成(f[k-2]-1)+1+(f[k-3]-1)，这样递归分割下去就能不断的缩小区间直至找到目标。
     * 假如扩充到f[k]，f[k] = f[k - 1] + f[k - 2]，少了一个mid点。
     * 假如扩充到f[k + 1]点，f[k + 1] = f[k] + f[k - 1]，少了一个mid点。
     * 假如扩充到f[k] + 1点，f[k] + 1 = f[k - 1] + 1 + f[k - 2]，这里mid点有了，但是接下来f[k - 1]和f[k - 2]都无法拆分出mid点。
     *
     * 2 a的长度其实很好估算，比如你定义了有10个元素的有序数组a[20]，n=10，那么n就位于8和13，即F[6]和F[7]之间，所以 k=7，
     * 此时数组a的元素个数要被扩充，为：F[7] - 1 = 12个； 再如你定义了一个b[20]，且b有12个元素，即n=12,那么很好办了，
     * n = F[7]-1 = 12, 用不着扩充了； 又或者n=8或9或11，则它一定会被扩充到12； 再如你举的例子，n=13，最后得出n位于13和21，
     * 即F[7]和F[8]之间，此时k=8，那么F[8]-1 = 20,数组a就要有20个元素了。 所以，n = x（13<=x<=20）时，最后都要被扩充到20；
     * 类推，如果n=25呢，则数组a的元素个数肯定要被扩充到 34 - 1 = 33个（25位于21和34，即F[8]和F[9]之间，此时k=9，F[9]-1 = 33），
     * 所以，n = x（21<=x<=33）时，最后都要被扩充到33。也就是说，最后数组的元素个数一定是（某个斐波那契数 - 1），
     * 这就是一开始说的n与F[k]-1的关系因为能否用斐波那契查找法是由F[k]-1决定的
     *
     * 3 对于二分查找，分割是从mid= (low+high)/2开始；而对于斐波那契查找，分割是从mid = low + F[k-1] - 1开始的；
     * 通过上面知道了，数组a现在的元素个数为F[k]-1个，即数组长为F[k]-1，mid把数组分成了左右两部分， 左边的长度为：F[k-1] - 1，
     * 那么右边的长度就为（数组长-左边的长度-1）， 即：（F[k]-1） - （F[k-1] - 1） = F[k] - F[k-1] - 1 = F[k-2] - 1。
     * 斐波那契查找的核心是：
     *   1）当key=a[mid]时，查找成功；
     *   2）当key<a[mid]时，新的查找范围是第low个到第mid-1个，此时范围个数为F[k-1] - 1个，即数组左边的长度，所以要在[low, F[k - 1] - 1]范围内查找；
     *   3）当key>a[mid]时，新的查找范围是第mid+1个到第high个，此时范围个数为F[k-2] - 1个，即数组右边的长度，所以要在[F[k - 2] - 1]范围内查找。
     *
     *        1. 全部元素 = 前面的元素 + 后边元素
     *        2. f[k] = f[k-1] + f[k-2]
     *        因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
     *        即 在 f[k-1] 的前面继续查找 k--
     *        即下次循环 mid = f[k-1-1]-1
     *
     *
     *        1. 全部元素 = 前面的元素 + 后边元素
     *        2. f[k] = f[k-1] + f[k-2]
     *        3. 因为后面我们有 f[k-2] 所以可以继续拆分 f[k-2] = f[k-3] + f[k-4]
     *        4. 即在 f[k-2] 的前面进行查找 k -=2
     *        5. 即下次循环 mid = f[k - 1 - 2] - 1
     *
     *
     */

    /**
     * 斐波那契数列
     * @return
     */
    private static int[] fib() {
        int[] arr = new int[MAX_SIZE];
        arr[0] = 1;
        arr[1] = 1;
        for(int i = 2; i < MAX_SIZE; i++) {
            arr[i] = arr[i - 2] + arr[i - 1];
        }
        return arr;
    }
}
