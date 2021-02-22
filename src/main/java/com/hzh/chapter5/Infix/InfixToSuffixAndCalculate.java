package com.hzh.chapter5.Infix;

import com.hzh.chapter5.stackarray.StackArray;
import com.hzh.chapter5.stackarray.StackArray2;

/**
 * @description: 中缀表达式
 * 来自@https://blog.csdn.net/sgbfblog/article/details/8001651?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.control&dist_request_id=ecb97cc5-3727-45a3-899a-aa4a67d70d13&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.control
 * @Author huangzhenhui
 * @Date 2021/2/22 11:34
 */
public class InfixToSuffixAndCalculate {

    /**
     * 中缀表达式转后缀， 计算后缀表达式(逆波兰表达式)
     * @param infix
     * @return
     */
    public int calculate(String infix) {
        String suffix = convertToSuffix(infix);
        return calculateSuffix(suffix);
    }

    /**
     * 计算后缀表达式
     * 步骤:
     * 1 遍历表达式，遇到的数字首先放入栈中
     * 2 接着读到“+”，则弹出3和2，执行3+2，计算结果等于5，并将5压入到栈中
     * 3 读到8，将其直接放入栈中
     * 4 读到“*”，弹出8和5，执行8*5，并将结果40压入栈中。而后过程类似，读到“+”，将40和5弹出，将40+5的结果45压入栈...以此类推
     * @param suffix
     * @return
     */
    private int calculateSuffix(String suffix) {
        StackArray stack = new StackArray(suffix.length());
        for (int i = 0; i < suffix.length(); i++) {
            char c = suffix.charAt(i);
            if (c >= '0' && c <= '9') {
                stack.push(c - '0');
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                if(c == '+') {
                    stack.push(num1 + num2);
                } else if(c == '-') {
                    stack.push(num1 - num2);
                } else if(c == '*') {
                    stack.push(num1 * num2);
                } else if(c == '/') {
                    stack.push(num1 / num2);
                }
            }
        }
        return stack.pop();
    }

    /**
     * 中缀转后缀
     * @param infix
     * @return
     */
    private String convertToSuffix(String infix) {

        StringBuilder suffix = new StringBuilder();
        StackArray2 stack = new StackArray2(infix.length());
        /**
         * 前提
         * （1）只考虑+、-、*、/这四种运算符，中缀表达式中只有一种括号：（）；
         * （2）输入的中缀表达式中只有整数，没有小数；
         * （3）假定输入是合法的。
         *
         * 中缀表达式a + b*c + (d * e + f) * g，其转换成后缀表达式则为a b c * + d e * f  + g * +。
         * 转换过程需要用到栈，具体过程如下：
         * 1）如果遇到操作数，我们就直接将其添加到后缀表达式末尾
         * 2）如果遇到操作符，则我们将其放入到栈中，遇到左括号时我们也将其放入栈中
         * 3）如果遇到一个右括号，则将栈元素弹出，将弹出的操作符输出直到遇到左括号为止。注意，左括号只弹出并不输出。
         * 4）如果遇到任何其他的操作符，如（“+”， “*”，“（”）等，从栈中弹出元素直到遇到发现更低优先级的元素(或者栈为空)为止。
         * 弹出完这些元素后，才将遇到的操作符压入到栈中。有一点需要注意，只有在遇到" ) "的情况下我们才弹出" ( "，其他情况我们都不会弹出" ( "。
         * 5）如果我们读到了输入的末尾，则将栈中所有元素依次弹出。
         * @see #getPriority(char)
         */
        for(int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if(c >= '0' && c <= '9') {
                // 如果遇到操作数，我们就直接将其放后缀表达式末尾
                suffix.append(c);
                continue;
            }
            if(c == '+' || c == '-' || c == '*' || c == '/') {
                // 校验和上一位的优先级
                while (!stack.isEmpty()) {
                    // 从栈中弹出元素直到遇到发现更低优先级的元素(或者栈为空)为止,
                    /**
                     * 1 +  压栈
                     * 2 *  *的优先级比+大直接压栈
                     * 3 +  +的优先级小于等于前两个， 前两个出栈， + 入栈
                     * 4 (优先级虽然最大, 但是只有遇到)的时候才有影响, 否则+-*除 都直接压
                     */
                    if (getPriority(c) > getPriority(stack.getTop()) || stack.getTop() == '(') {
                        stack.push(c);
                        break;
                    } else {
                        // 出栈放入到后缀表达式后面
                        suffix.append(stack.pop());
                    }
                }
                // 如果最后还是空或者一开始就为空, 压栈
                if (stack.isEmpty()) {
                    stack.push(c);
                }
            }
            // (优先级最大直接压
            if(c == '(') {
                stack.push(c);
                continue;
            }
            if (c == ')') {
                // 只有遇到)时, (才会弹出, 中间的全部放到后缀中
                while (!stack.isEmpty()) {
                    char pop = stack.pop();
                    if (pop == '(') {
                        break;
                    } else {
                        suffix.append(pop);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            suffix.append(stack.pop());
        }
        return suffix.toString();
    }

    /**
     * 获取优先级
     * @param ch
     * @return
     */
    private int getPriority(char ch) {
        // 获取优先级, 越大越优先
        if (ch == '(') return 4;
        else if (ch == '*' || ch == '/') return 3;
        else if (ch == '+' || ch == '-') return 2;
        else return 1;
    }

    public static void main(String[] args) {
        InfixToSuffixAndCalculate infixToSuffixAndCalculate = new InfixToSuffixAndCalculate();
        int calculate = infixToSuffixAndCalculate.calculate("3+2*6-2");
        System.out.println(calculate);
    }
}
