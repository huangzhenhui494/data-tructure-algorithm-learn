package com.hzh.chapter5.Infix;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @description: 多位数计算
 * @Author huangzhenhui
 * @Date 2021/2/23 9:10
 */
public class MultipleDigitsCalculate {

    /** 匹配 + - * / ( ) 运算符 */
    private static final String SYMBOL_REGEX = "[+\\-*/()]";

    /***/
    private static final String NUMBER_REGEX = "";

    /** 运算符 */
    private static final String L_BRACKETS = "(";
    private static final String R_BRACKETS = ")";
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";

    /** 截断数字和字符 */
    private static final String NUM_SYMBOL = "[]";


    public static String calculateInfix(String s) {
        if (s == null || "".equals(s.trim())) throw new RuntimeException("data is empty");
        // TODO 校验数据没写, 假定中缀表达式的格式正确, 开头第一个字符为数字
        s = replaceAllBlank(s);
        Stack<String> numStack = new Stack<>();
        Stack<String> symbolStack = new Stack<>();
        // 假定格式正确, 遍历去除空格后的表达式
        StringBuilder num = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            String c = s.charAt(i) + "";
            // 遇到运算符之前均视为数字
            if (!isSymbol(c)) {
                num.append(c); continue;
            } else {
                // 遇到符号,num入数字栈,符号按规则入符号栈,清空num,符号栈为空时直接入栈
                if (!num.toString().equals("")) {
                    numStack.push(num.toString());
                    num = new StringBuilder();
                }
            }
            if (symbolStack.empty()) {
                symbolStack.push(c);
            } else {
                // 非空根据规则出入站
                if (R_BRACKETS.equals(c)) {
                    // 1 数字栈出栈2个,符号栈出栈1个计算结果入数字栈, 直到遇到大括号出栈, 注意先后, 如-号, 左边是后面pop的值
                    String pop = symbolStack.pop();
                    while (!L_BRACKETS.equals(pop)) {
                        numStack.push(arithmeticCal(numStack.pop(), numStack.pop(), pop));
                        pop = symbolStack.pop();
                    }
                } else if(L_BRACKETS.equals(symbolStack.peek()) || levelCal(c) > levelCal(symbolStack.peek())) {
                    // 2 优先级大或者peek到左括号直接入栈
                    symbolStack.push(c);
                } else {
                    // 3 优先级小于等于则出栈计算直到peek到优先级比自己小的或空栈时再入栈
                    while (!symbolStack.empty() && levelCal(c) <= levelCal(symbolStack.peek()) && !symbolStack.peek().equals(L_BRACKETS)) {
                        numStack.push(arithmeticCal(numStack.pop(), numStack.pop(), symbolStack.pop()));
                    }
                    symbolStack.push(c);
                }
            }
        }
        // 如果最后一位是数字的话, 再入数字栈
        if (!num.toString().equals("")) {
            numStack.push(num.toString());
        }
        // 此时如果符号栈不为空, 继续出栈计算
        while (!symbolStack.empty()) {
            numStack.push(arithmeticCal(numStack.pop(), numStack.pop(), symbolStack.pop()));
        }
        return numStack.pop();
    }




    /**
     * 获取符号优先级
     * @param s
     * @return
     */
    private static int levelCal(String s) {
        if (L_BRACKETS.equals(s)) {
            return 3;
        } else if (MUL.equals(s) || DIV.equals(s)) {
            return 2;
        } else if (ADD.equals(s) || SUB.equals(s)) {
            return 1;
        }
        throw new IllegalArgumentException("level calculate operator error");
    }

    /**
     * 四则运算
     * @param n1 pop出的第二位
     * @param n2 pop出的第一位
     * @param op 运算符
     * @return
     */
    private static String arithmeticCal(String n1, String n2, String op) {
        BigDecimal result;
        switch (op) {
            case ADD:
                result = new BigDecimal(n2).add(new BigDecimal(n1))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
                break;
            case SUB:
                result = new BigDecimal(n2).subtract(new BigDecimal(n1))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
                break;
            case MUL:
                result = new BigDecimal(n2).multiply(new BigDecimal(n1))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
                break;
            case DIV:
                result = new BigDecimal(n2).divide(new BigDecimal(n1),
                        2, BigDecimal.ROUND_HALF_UP);
                break;
            default:
                throw new IllegalArgumentException("arithmetic calculate operator error");
        }
        return result.toString();
    }

    /**
     * 判断是不是运算符
     * @param s
     * @return
     */
    public static boolean isSymbol(String s) {
        return s.matches(SYMBOL_REGEX);
    }

    /**
     * 去除所有空白符
     * @param s
     * @return
     */
    public static String replaceAllBlank(String s) {
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
        return s.replaceAll("\\s+", "");
    }

    public static void main(String[] args) {
        //String math = "9+(3-1)*3+10/2";
        try {
            String s = calculateInfix("(7/2-5+(1+(2-3)*4+4/2)+2+2*(6/2)-(9-2+6/3*8/2)+22/3+1)*2/5-44/2*4");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
