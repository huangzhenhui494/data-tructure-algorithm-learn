package com.hzh.chapter3.linkedlist.doublelinked;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/20 20:54
 */
public class DoubleLinkedList {

    // 先初始化一个头结点, 头结点不要动, 不存放具体的数据
    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    // 返回头结点

    public DoubleHeroNode getHead() {
        return head;
    }

    /**
     * 遍历
     */
    public void showList() {
        //判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("list is empty");
        }
        // 因为头结点, 不能动, 因此需要一个辅助变量来遍历
        DoubleHeroNode temp = head;
        while (temp.next != null) {
            // 输出
            temp = temp.next;
            System.out.println(temp);
        }
        System.out.println("---------------------------");
    }

    /**
     * 添加
     * @param node
     */
    public void add(DoubleHeroNode node) {

        // 因为node结点不能动, 因此我们需要一个辅助变量temp
        DoubleHeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 找到链表最后一个, 形成一个双向链表
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 修改结点信息, 根据no编号来修改, 即no编号不能改
     * @param node
     */
    public void update(DoubleHeroNode node) {
        // 判断是否空链表
        if(head.next == null) throw new RuntimeException("链表为空");
        // 找到需要修改的结点
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            if (temp.no == node.no) {
                temp.name = node.name;
                temp.nickname = node.nickname;
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("找不到编号");
    }

    /**
     * 从双向链表中删除一个结点
     * 1 对于双向链表, 我们可以直接找到要删除的这个结点
     * 2 找到后, 自我删除即可
     * @param no
     */
    public void del(int no) {
        // 判断是否空链表
        if(head.next == null) throw new RuntimeException("链表为空");
        // 查找要删除的结点
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            if (temp.no == no) {
                temp.pre.next = temp.next;
                if (temp.next != null) {  // 如果是最后一个结点会空指针
                    temp.next.pre = temp.pre;
                }
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("找不到编号");
    }

    /**
     * 按顺序添加
     * @param node
     */
    public void addByOrder(DoubleHeroNode node) {
        DoubleHeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no > node.no) {
                if(temp.no == node.no) { // 这里有点问题, 理论上头结点不应该拿来比较, 不管了
                    throw new RuntimeException("编号已存在");
                }
                temp.next.pre = node;
                node.next = temp.next;
                temp.next = node;
                node.pre = temp;
                return;
            }
            temp = temp.next;
        }
        // 找到最后一个结点还没有
        temp.next = node;
        node.pre = temp;
    }
}
