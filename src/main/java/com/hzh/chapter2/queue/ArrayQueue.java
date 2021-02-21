package com.hzh.chapter2.queue;

import java.util.Arrays;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/18 14:11
 */
public class ArrayQueue<T> {

    private int maxSize; // 数组的最大容量

    private int front;   // 队列头

    private int rear;    // 队列尾

    private int[] arr;   // 改数据用于存放数据, 模拟队列

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部, 分析出front是指向队列头的前一个位置
        rear = -1;  // 指向队列尾, 指向队列尾的数据(即就是队列最后一个数据)
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     * @param element
     */
    public void addQueue(int element) {
        // 判断队列是否满
        if (isFull()) {
            throw new IndexOutOfBoundsException("Queue full");
        }

        rear++; // 让rear后移
        arr[rear] = element;
    }

    /**
     * 获取队列的数据, 出队列
     * @return
     */
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空, 不能取数据");
        }
        front++; // front后移
        return arr[front];
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空, 不能取数据");
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 显示队列的头数据, 不是取出
     * @return
     */
    public int headQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空, 不能取数据");
        }
        return arr[front + 1];
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "maxSize=" + maxSize +
                ", front=" + front +
                ", rear=" + rear +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }
}
