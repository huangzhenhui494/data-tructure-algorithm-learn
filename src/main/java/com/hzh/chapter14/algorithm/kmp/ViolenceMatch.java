package com.hzh.chapter14.algorithm.kmp;

/**
 * https://www.zhihu.com/question/21923021
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/15 9:27
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        System.out.println(violenceMatch("XXXXXXXXX ABDEASDD ADFVCX ABDEASDE ADVED", "FVC"));
    }

    /**
     *
     * 如果用暴力匹配的思路，并假设现在 str1 匹配到 i 位置，子串 str2 匹配到 j 位置，则有:
     * 1) 如果当前字符匹配成功（即 str1[i] == str2[j]），则 i++，j++，继续匹配下一个字符
     * 2) 如果失配（即 str1[i]! = str2[j]），令 i = i - (j - 1)，j = 0。相当于每次匹配失败时，i 回溯，j 被置为 0。
     * 3) 用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量
     * 的时间。(不可行!)
     * @param text 文本
     * @param pattern 待匹配的字符串
     * @return 成功返回匹配到的首字母在文中索引, 没有返回-1
     */
    public static int violenceMatch(String text, String pattern) {

        int tLen = text.length();
        int pLen = pattern.length();
        char[] t = text.toCharArray();
        char[] p = pattern.toCharArray();
        int i = 0;  // t的索引
        int j = 0;  // p的索引

        /**
         * i = i - (j - 1)
         * 33 - 7  26
         * SBEADFACV ABDEASDD ADFVCX ABDEASDE ADVED
         * ABDEASDE  6
         */
        while (i < tLen && j < pLen) { // 保证匹配时，不越界
            // str1[i] == str2[j]，则 i++，j++，继续匹配下一个字符
            if(t[i] == p[j]) { // 匹配成功
                i++;
                j++;
            } else { // 匹配失败
                // str1[i]! = str2[j]，令 i = i - (j - 1)，j = 0。相当于每次匹配失败时，i 回溯，j 被置为 0。
                i = i - (j - 1);
                j = 0;
            }
        }

        // 判断是否匹配成功, 成功返回匹配到的首字母在文中索引, 没有返回-1
        if (j == pLen) {
            return i - j;
        } else {
            return -1;
        }
    }


}
