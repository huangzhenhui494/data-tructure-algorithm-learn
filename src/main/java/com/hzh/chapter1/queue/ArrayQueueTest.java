package com.hzh.chapter1.queue;

/**
 * @description: 使用数组模拟队列写一个ArrayQueue类
 * @Author huangzhenhui
 * @Date 2021/2/18 9:14
 */
public class ArrayQueueTest {

    public static void main(String[] args) {
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(3);
//
//        System.out.println(arrayQueue.toString());
//        System.out.println(arrayQueue.isEmpty());
//        System.out.println(arrayQueue.isFull());
//
//        arrayQueue.addQueue(1);
//        arrayQueue.addQueue(2);
//        System.out.println(arrayQueue.isFull());
//        arrayQueue.addQueue(3);
//        System.out.println(arrayQueue.isEmpty());
//        System.out.println(arrayQueue.isFull());
//
//        System.out.println(arrayQueue.headQueue());
//        System.out.println(arrayQueue.getQueue());
//        System.out.println(arrayQueue.getQueue());
//        System.out.println(arrayQueue.headQueue());

        CircleQueue circleQueue = new CircleQueue(8);

        System.out.println(circleQueue.isEmpty());
        circleQueue.addQueue(1);
        circleQueue.addQueue(2);
        circleQueue.addQueue(3);
        circleQueue.addQueue(4);
        circleQueue.addQueue(5);
        circleQueue.addQueue(6);
        System.out.println(circleQueue.isFull());
        System.out.println(circleQueue.isEmpty());
        System.out.println(circleQueue.headQueue());
        circleQueue.addQueue(7);
        System.out.println(circleQueue.isFull());
        System.out.println(circleQueue.isEmpty());
        System.out.println(circleQueue.headQueue());

        System.out.println("**");
        System.out.println(circleQueue.getQueue());
        System.out.println(circleQueue.isFull());
        System.out.println(circleQueue.isEmpty());
        System.out.println(circleQueue.headQueue());


        circleQueue.getQueue();
        circleQueue.getQueue();
        circleQueue.addQueue(11);
        circleQueue.getQueue();
        circleQueue.getQueue();
        circleQueue.addQueue(12);

        System.out.println(circleQueue.toString());
//        circleQueue.showQueue();

        // (12)234567(11)  front 5 rear 1

        circleQueue.getQueue();
        circleQueue.addQueue(13);
        circleQueue.getQueue();
        circleQueue.addQueue(14);
        circleQueue.getQueue();
        circleQueue.addQueue(15);
        circleQueue.getQueue();
        circleQueue.addQueue(16);

        // (12)(13)(14)(15)(16)67(11) front 1, rear5
        System.out.println(circleQueue.toString());
        System.out.println(circleQueue.getFront());
        System.out.println(circleQueue.getRear());

        System.out.println("show");
        circleQueue.showQueue();

    }


}


