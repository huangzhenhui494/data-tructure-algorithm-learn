package com.hzh.chapter14.algorithm.horse;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/22 6:50
 */
public class HorseChessboard {

    /**
     * X轴, 代表列
     */
    private static int X;

    /**
     * Y轴, 代表行, 自己画个坐标看看就知道
     */
    private static int Y;

    /**
     * 创建一个数组, 标记棋盘的各个位置是否被访问过
     */
    private static boolean visited[];

    /**
     * 使用一个属性, 标记是否棋盘的所有位置都被访问过了, 如果为true, 表示成功
     */
    private static boolean finished;

    public static void main(String[] args) {
        // 测试骑士周游算法
        X = 8;
        Y = 8;
        int row = 1; // 马儿初始位置的行, 从1开始
        int column = 1; // 马儿初始位置的列, 从1开始
        // 创建棋盘
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y]; // 初始值都是false
        // 测试耗时
        long start = System.currentTimeMillis();
        traversalChessborad(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 25040
        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 贪心算法优化
     */
    @Test
    public void test() {
        // 测试骑士周游算法
        X = 8;
        Y = 8;
        int row = 1; // 马儿初始位置的行, 从1开始
        int column = 1; // 马儿初始位置的列, 从1开始
        // 创建棋盘
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y]; // 初始值都是false
        // 测试耗时
        long start = System.currentTimeMillis();
        traversalChessborad2(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 25040
        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 完成骑士周游问题的算法
     * @param chessBoard 棋盘
     * @param row 马儿当前的位置的行, 从0开始计
     * @param column 马儿当前位置的列, 从0开始计
     * @param step 是第几步, 初始位置就是第一步
     */
    public static void traversalChessborad(int[][] chessBoard, int row, int column, int step) {
        chessBoard[row][column] = step;
        visited[row * X + column] = true; // 标记该位置已经访问, 这边的X固定, 只是代表每行有8个
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        // 遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); // 取出下一个可以走的位置
            // 判断该点是否已经访问过
            if(!visited[p.y * X + p.x]) {
                traversalChessborad(chessBoard, p.y, p.x, step + 1);
            }
        }
        // 判断马儿是否完成了任务, 使用step和应该走的步数比较, 如果没有达到数量, 则没有完成任务, 棋盘置0
        /*
        说明: step < X * Y 成立的情况有两种
        1.棋盘到目标位置但还没走完
        2.棋盘处于一个回溯过程,  如果finished, 会一路绿灯直到所有方法出栈
         */
        if(step < X * Y && !finished) { // 未完成
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 贪心算法优化
     * @param chessBoard
     * @param row
     * @param column
     * @param step
     */
    public static void traversalChessborad2(int[][] chessBoard, int row, int column, int step) {
        chessBoard[row][column] = step;
        visited[row * X + column] = true; // 标记该位置已经访问, 这边的X固定, 只是代表每行有8个
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        // 贪心算法, 优化, 对ps的所有Point对象的下一步的位置的数目进行非递减排序
        sort(ps);
        // 遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); // 取出下一个可以走的位置
            // 判断该点是否已经访问过
            if(!visited[p.y * X + p.x]) {
                traversalChessborad2(chessBoard, p.y, p.x, step + 1);
            }
        }
        // 判断马儿是否完成了任务, 使用step和应该走的步数比较, 如果没有达到数量, 则没有完成任务, 棋盘置0
        /*
        说明: step < X * Y 成立的情况有两种
        1.棋盘到目标位置但还没走完
        2.棋盘处于一个回溯过程,  如果finished, 会一路绿灯直到所有方法出栈
         */
        if(step < X * Y && !finished) { // 未完成
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 左上角为坐标原点0,0, 向下向右都为正轴, (0,0) 也需要, 所以X,Y要用<
     * 根据当前(Point对象), 计算马儿还能走到哪些位置(Point), 并放入到一个集合中, 最多有8个位置
     * @param curpoint
     * @return
     */
    public static ArrayList<Point> next(Point curpoint) {
        // 创建一个list
        ArrayList<Point> ps = new ArrayList<>();
        // 创建一个Point
        Point p1 = new Point();
        // p1赋值成向左2,向上1的位置, 且不能超出空间, 第一次表示可以走图中的5 com/hzh/chapter14/algorithm/horse/horse.png
        if((p1.x = curpoint.x - 2) >= 0 && (p1.y = curpoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿是否可以走6
        if((p1.x = curpoint.x - 1) >= 0 && (p1.y = curpoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿是否可以走7
        if((p1.x = curpoint.x + 1) < X && (p1.y = curpoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿是否可以走0
        if((p1.x = curpoint.x + 2) < X && (p1.y = curpoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿是否可以走1
        if((p1.x = curpoint.x + 2) < X && (p1.y = curpoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿是否可以走2
        if((p1.x = curpoint.x + 1) < X && (p1.y = curpoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿是否可以走3
        if((p1.x = curpoint.x - 1) >= 0 && (p1.y = curpoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿是否可以走4
        if((p1.x = curpoint.x - 2) >= 0 && (p1.y = curpoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    /**
     * 根据当前这一步的所有的下一步选择位置, 进行非递减排序
     */
    public static void sort(ArrayList<Point> points) {
        // points.sort(Comparator.comparingInt(o -> next(o).size()));
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }

}
