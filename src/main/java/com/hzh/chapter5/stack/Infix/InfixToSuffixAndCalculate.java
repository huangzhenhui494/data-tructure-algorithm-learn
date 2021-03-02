package com.hzh.chapter5.stack.Infix;

import com.hzh.chapter5.stack.stackarray.StackArray;
import com.hzh.chapter5.stack.stackarray.StackArray2;

import java.util.Stack;

/**
 * @description: 中缀表达式
 * 来自 https://blog.csdn.net/sgbfblog/article/details/8001651?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.control&dist_request_id=ecb97cc5-3727-45a3-899a-aa4a67d70d13&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.control
 * @Author huangzhenhui
 * @Date 2021/2/22 11:34
 */
public class InfixToSuffixAndCalculate {

    /**
     * 来自 https://blog.csdn.net/sinat_27908213/article/details/80273557
     * ① 准备一个数字栈和一个符号栈
     * ② 从左到右遍历中缀表达式。如果遇到数字，入数字栈。
     * ③ 如果遇到符号（四个运算符以及括号），跟前面的“中缀表达式转后缀表达式”过程一样，对符号栈进行处理。处理过程中，
     *  对每一个出栈的运算符：+ - * /，根据“计算后缀表达式”的方法，计算结果（跟数字栈配合）。
     *  如果遍历完中缀表达式后符号栈还非空，就继续出符号栈的运算符，计算，直到符号栈为空。最后数字栈剩下的数字就是结果。
     * @return
     */
    public int calCulateDirect(String infix) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> symbolStack = new Stack<>();

        for(int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            // 1 数字
            if (c >= '0' && c <= '9') {
                numStack.push(c - '0');
                continue;
            }
            // 2 符号
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!symbolStack.empty()) {
                    if (getPriority(c) > getPriority(symbolStack.peek()) || symbolStack.peek() == '(') {  // 校验和上一位的优先级, 从栈中弹出元素直到遇到发现更低优先级的元素(或者栈为空)为止
                        symbolStack.push(c);
                        break;
                    } else {
                        // 符号出栈, 计算
                        char pop = symbolStack.pop();
                        numStack.push(calculateBySymbol(numStack.pop(), numStack.pop(), pop));
                    }
                }
                if (symbolStack.empty()) { // 如果最后还是空或者一开始就为空, 压栈
                    symbolStack.push(c);
                }
            }
            // 3 ( 优先级最大直接压栈
            if(c == '(') {
                symbolStack.push(c);
                continue;
            }
            // 4 只有遇到)时, (才会弹出, 中间的全部出栈计算
            if (c == ')') {
                while (!symbolStack.empty()) {
                    char pop = symbolStack.pop();
                    if (pop != '(') {
                        // 符号出栈, 计算
                        int result = calculateBySymbol(numStack.pop(), numStack.pop(), pop);
                        numStack.push(result);
                    } else {
                        break;
                    }
                }
            }
        }
        // 5 如果符号栈还有, 继续出栈计算
        while (!symbolStack.empty()) {
            numStack.push(calculateBySymbol(numStack.pop(), numStack.pop(), symbolStack.pop()));
        }
        return numStack.pop();
    }

    /**
     * 假定公式格式正确
     * @param num2
     * @param num1
     * @param c
     * @return
     */
    private int calculateBySymbol(Integer num2, Integer num1, char c) {
        if(c == '+') {
            return num1 + num2;
        } else if(c == '-') {
            return num1 - num2;
        } else if(c == '*') {
            return num1 * num2;
        } else if(c == '/') {
            return num1 / num2;
        }
        throw new RuntimeException("???");
    }

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
                // 注意, char数字转int可以用 - '0'
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
        int calculate1 = infixToSuffixAndCalculate.calculate("(1+(2-3)*4+4/2+2+2*(6/2)+9-2+6/3)*8/2");
        int calculate2 = infixToSuffixAndCalculate.calCulateDirect("(1+(2-3)*4+4/2+2+2*(6/2)+9-2+6/3)*8/2");
        System.out.println(calculate1);
        System.out.println(calculate2);
    }
}
