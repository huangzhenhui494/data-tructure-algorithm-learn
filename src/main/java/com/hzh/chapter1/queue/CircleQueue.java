package com.hzh.chapter1.queue;

import java.util.Arrays;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/18 14:40
 */
public class CircleQueue {

    private int maxSize; // 数组的最大容量

    private int front;   // 变量含义调整: front就指向队列的第一个元素, 也就是说arr[front]就是队列的第一个元素 初始为0

    private int rear;    // 变量含义调整: rear指向队列的最后一个元素的后一个位置, 因为希望空出一个空间做为约定  初始为0

    private int[] arr;   // 改数据用于存放数据, 模拟队列

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    /**
     * 判断队列是否满
     * 循环队列为什么要空一个元素的位置呢?
     * 循环队列中，由于入队时尾指针向前追赶头指针；出队时头指针向前追赶尾指针，造成队空和队满时头尾指针均相等。因此，无法通过条件front==rear来判别队列是"空"还是"满"。
     * 解决这个问题的方法至少有三种：
     * ① 另设一布尔变量以区别队列的空和满；
     * ② 少用一个元素的空间。约定入队前，测试尾指针在循环意义下加1后是否等于头指针，若相等则认为队满（注意：rear所指的单元始终为空）;
     * ③ 使用一个计数器记录队列中元素的总数（即队列长度）。
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;  // 指针, 比如时钟,  (24 + 1) % 24 = 1, 当rear的指针也在1时, 满了
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
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
        // 直接将数据加入
        arr[rear] = element;
        // 将rear后移, 这里必须考虑取模(否则超过了maxSize咋办??????), 要回到0
        rear = (rear + 1) % maxSize;
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
        // 直接取出, 索引向rear移动
        int element = arr[front];
        front = (front + 1) % maxSize;
        return element;
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
        // 只将有的打印, 从front开始遍历, 遍历 (rear - front + maxSize) % maxSize 个数据
        for(int i = front; i < front + (rear - front + maxSize) % maxSize; i++) {
            System.out.println(arr[i % maxSize]);
        }
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
        return arr[front];
    }

    /**
     * 取模和取余
     */
    public static void takeRemainder() {

        /**
         * 1.求 整数商： c = [a/b];
         * 2.计算模或者余数： r = a - c*b.
         */
        // % 取余, 向0方向舍入
        // Math.floorMod() 取模, 向负无穷方向舍入
        System.out.println(7 % 3);                    // 1
        System.out.println(-7 % 3);                   // -1
        System.out.println(7 % -3);                   // 1
        System.out.println(-7 % -3);                  // -1

        System.out.println(Math.floorMod(7, 3));      // 1
        System.out.println(Math.floorMod(-7, 3));     // 2
        System.out.println(Math.floorMod(7, -3));     // -2
        System.out.println(Math.floorMod(-7, -3));    // -1
    }

    @Override
    public String toString() {
        return "CircleQueue{" +
                "maxSize=" + maxSize +
                ", front=" + front +
                ", rear=" + rear +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }
}
