package com.hzh.chapter4;

import java.util.Currency;

/**
 * @description: 环形单向链表
 * @Author huangzhenhui
 * @Date 2021/2/20 22:09
 */
public class CircleSingleLinkedList {

    // 创建一个first节点,当前没有编号
    private Boy first = null;

    /**
     * 添加小孩, 构建成环形链表
     * @param nums
     */
    public void addBoy(int nums) {

        if (nums < 1) {
            throw new IllegalArgumentException("num的值不正确");
        }
        Boy curBoy = null;
        // 使用for来创建我们的环形链表
        for(int i = 1; i <= nums; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                curBoy = curBoy.getNext();
            }
        }
        curBoy.setNext(first);
    }

    /**
     * 遍历
     */
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            throw new RuntimeException("empty");
        }
        // 因为first不能动, 因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        // 用doWhile, 第一个可能next也是first
        do {
            System.out.println(curBoy);
            curBoy = curBoy.getNext();
        } while (curBoy != first);
    }

    /**
     * 出圈
     * @param start 表示从第几个小孩开始数数
     * @param count 表示数几下
     * @param nums 表示最初由多少小孩
     */
    public void countBoy(int start, int count, int nums) {
        // 先对数据进行校验
        if (first == null || start < 1 || start > nums) {
            throw new RuntimeException("参数有误");
        }
        // 创建要给辅助指针, 帮助完成小孩出圈
        Boy helper = first;
        first = helper.getNext();



    }
}
