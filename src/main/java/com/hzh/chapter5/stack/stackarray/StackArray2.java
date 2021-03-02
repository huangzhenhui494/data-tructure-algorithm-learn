package com.hzh.chapter5.stack.stackarray;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/22 9:28
 */
public class StackArray2 {

    private int top;

    private int maxSize;

    private char[] stack;

    public StackArray2(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.stack = new char[maxSize];
    }

    /**
     * 入栈
     */
    public void push(char element) {
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
    public char pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("empty");
        }
        char temp = stack[top];
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

    public char getTop() {
        return stack[top];
    }
}
