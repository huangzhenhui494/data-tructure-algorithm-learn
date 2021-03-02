package com.hzh.chapter5.stack.stackarray;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/22 9:28
 */
public class StackArray {

    private int top;

    private int maxSize;

    private int[] stack;

    public StackArray(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.stack = new int[maxSize];
    }

    /**
     * 入栈
     */
    public void push(int element) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("栈满了");
        }
        top++;
        stack[top] = element;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("empty");
        }
        int temp = stack[top];
        top--;
        return temp;
    }

    /**
     * 展示
     */
    public void showList() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("empty");
        }
        // 需要从栈顶开始展示数据
        for(int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int getTop() {
        return stack[top];
    }
}
