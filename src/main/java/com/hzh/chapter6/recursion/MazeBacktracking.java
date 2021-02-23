package com.hzh.chapter6.recursion;

import java.util.Arrays;

/**
 * @description: 把将要走的节点赋值为此节点值+1，走过的路径也可以重复行走。条件是本节点值+1必须小于已走过的节点的值(墙不能走)，这样遍历所有的节点，便可以记录最短的路径。
 * @Author huangzhenhui
 * @Date 2021/2/23 15:45
 */
public class MazeBacktracking {

    /**
     * 1为墙, 2为走过的, 回溯为3
     */
    private static final int ROW = 10;
    private static final int COL = 12;
    // 初始化一个迷宫
    private static int[][] maze = new int[ROW][COL];

    static {
        for(int i = 0; i < ROW; i++) {
            maze[i][0] = 1;
            maze[i][COL - 1] = 1;
        }
        for(int j = 0; j < COL; j++) {
            maze[0][j] = 1;
            maze[ROW - 1][j] = 1;
        }
        maze[2][1] = 1;
        maze[2][2] = 1;
        maze[4][4] = 1;
        maze[3][4] = 1;
        maze[4][2] = 1;
        maze[4][3] = 1;
        maze[5][6] = 1;
        maze[7][7] = 1;
        maze[6][8] = 1;
        maze[6][3] = 1;
        maze[8][2] = 1;
        maze[8][9] = 1;
    }

    /**
     * 下一步
     * @param maze 按照下右上左走
     * @param i 行
     * @param j 列
     */
    public static boolean setWay(int[][] maze, int i, int j) {
        // 按照策略 下->右->上->左 走
        // 起点为[1][1], 终点为[9][11]
        if(maze[ROW - 2][COL - 2] == 2) {
            return true; // 已经找到通路
        }
        if (maze[i][j] == 0) {
            maze[i][j] = 2;
            if (setWay(maze, i + 1, j)) {       // 下
                return true;
            } else if(setWay(maze, i, j + 1)) { // 右
                return true;
            } else if(setWay(maze, i - 1, j)) { // 上
                return true;
            } else if(setWay(maze, i, j - 1)) { // 左
                return true;
            } else {
                maze[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean setWay2(int[][] maze, int i, int j) {
        // 按照策略 上左下右
        // 起点为[1][1], 终点为[9][11]
        if(maze[ROW - 2][COL - 2] == 2) {
            return true; // 已经找到通路
        }
        if (maze[i][j] == 0) {
            maze[i][j] = 2;
            if (setWay2(maze, i - 1, j)) {
                return true;
            } else if(setWay2(maze, i, j - 1)) {
                return true;
            } else if(setWay2(maze, i + 1, j)) {
                return true;
            } else if(setWay2(maze, i, j + 1)) {
                return true;
            } else {
                maze[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        setWay2(maze, 1, 1);
        for (int[] ints : maze) {
            System.out.println(Arrays.toString(ints));
        }
    }







}
