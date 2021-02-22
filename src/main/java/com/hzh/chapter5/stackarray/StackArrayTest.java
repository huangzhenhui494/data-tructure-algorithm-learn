package com.hzh.chapter5.stackarray;

import java.util.Scanner;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/22 9:44
 */
public class StackArrayTest {

    public static void main(String[] args) {

        StackArray stack = new StackArray(5);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show" :
                    try {
                        stack.showList();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push" :
                    try {
                        System.out.println("请输入一个数");
                        int nextInt = scanner.nextInt();
                        stack.push(nextInt);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pop" :
                    try {
                        System.out.println("出栈: " + stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit" :
                    scanner.close();
                    loop = false;
                    break;
                    default : break;
            }
            System.out.println("exit");
        }
    }
}
